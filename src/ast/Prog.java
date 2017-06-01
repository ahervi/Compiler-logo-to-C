package ast;

import errors.*;
import state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noye on 05/05/2017.
 */
public class Prog extends AST {
    public String filename;
    public List<ProcDecl> procDecls;
    public List<Cmd> cmds;
    public State<Type> globals;
    public State<ArrayList<Type>> procedures;
    public Prog(List<ProcDecl> procDecls, List<Cmd> cmds) {
        this.procDecls = procDecls;
        this.cmds = cmds;
        this.globals = new State<Type>();
        this.procedures = new State<ArrayList<Type>>();
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, FuncCallLookupError, UndefinedProcError, NumberOfArgumentsError {
        for(ProcDecl proc: this.procDecls) {

            if (this.procedures.containsKey(proc.name)) {
                throw new ProcedureAlreadyDefinedError("La procédure " + proc.name + " est déjà définie.");
            } else {
                ArrayList<Type> formalsTypes = new ArrayList<Type>();
                formalsTypes.add(Type.VOID);
                if (proc.formals.size() != 0) {
                    for (String formal : proc.formals) {
                        formalsTypes.add(Type.UNKNOWN);
                    }
                } else {
                    formalsTypes.add(Type.VOID);
                }

                this.procedures.bind(proc.name, formalsTypes);
            }
        }

        for(ProcDecl proc: this.procDecls) {
            proc.check1(this.procedures);
        }
        for(Cmd cmd: cmds) {
            cmd.check1(this.procedures);
        }
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        for(Cmd cmd: cmds) {
            cmd.check2(true, this.globals, locals);
        }
        for(ProcDecl proc: this.procDecls) {
            proc.check2(false, this.globals, locals);
        }

    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {

        for(Cmd cmd: cmds) {
            cmd.check3(true, this.procedures,  this.globals, locals);
        }
        for(int j = procDecls.size() - 1; j >= 0; j--){
            procDecls.get(j).check3(false, this.procedures,  this.globals, locals);

        }

    }

    @Override
    public String gen() throws CompilationError {
        StringBuilder procsCode = new StringBuilder();

        for(ProcDecl proc: this.procDecls)
            procsCode.append("  " + proc.gen() + "\n");
        StringBuilder cmdsCode = new StringBuilder();
        for(Cmd cmd: cmds)
            cmdsCode.append("  " + cmd.gen() + "\n");
        return PRELUDE +
                genMAINPRELUDE() +
                procsCode.toString() +
                cmdsCode.toString() +
                MAINPOSTLUDE;
    }

    String PRELUDE =
        "#include <stdio.h>\n" +
        "#include <stdlib.h>\n" +
        "#include <stdbool.h>\n" +
        "#include <math.h>\n" +
        "#include <stdbool.h>\n" +
        "#define SDL_MAIN_HANDLED\n" +
        "#include <SDL2/SDL.h>\n" +
        "#include <SDL2/SDL_image.h>\n" +
        "#include \"logo.h\"" +
        "\n" +
        "const int WIN_WIDTH = 640;\n" +
        "const int WIN_HEIGHT = 480;\n\n";

    String genMAINPRELUDE() {
        StringBuilder res  = new StringBuilder("int main(int argc, char ** argv) {\n" +
                "  bool quit = false;\n" +
                "  SDL_Event event;\n" +
                "  SDL_Init(SDL_INIT_VIDEO);\n" +
                "  SDL_Window * window = SDL_CreateWindow(\"" + filename + "\",\n" +
                "  SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, WIN_WIDTH, WIN_HEIGHT, 0);\n" +
                "  SDL_Renderer * renderer = SDL_CreateRenderer(window, -1, 0);\n" +
                "  SDL_SetRenderDrawColor(renderer, 255, 255, 255, SDL_ALPHA_OPAQUE);\n" +
                "  SDL_RenderClear(renderer);\n" +
                "  SDL_SetRenderDrawColor(renderer, 0, 0, 0, SDL_ALPHA_OPAQUE);\n" +
                "  float xcor = WIN_WIDTH/2;\n" +
                "  float ycor = WIN_HEIGHT/2;\n" +
                "  float x;\n" +
                "  float y;\n" +
                "  double pi = M_PI;\n" +
                "  double rotation = pi/2;\n"

        ) ;

        for (String global : globals.keySet()) {
            if(!global.equals("xcor") && !global.equals("ycor")) {
                res.append("  " + globals.lookup(global).toString(global) + ";");
            }
        }
        return res.toString();
    }
    String MAINPOSTLUDE =
        "  SDL_RenderPresent(renderer);\n" +
        "  while (!quit){\n" +
        "    SDL_Delay(10);\n" +
        "    SDL_PollEvent(&event);\n" +
        "    switch (event.type){\n" +
        "       case SDL_QUIT:\n" +
        "       quit = true;\n" +
        "       break;\n" +
        "    }\n" +
        "  }\n" +
        "  SDL_DestroyRenderer(renderer);\n" +
        "  SDL_DestroyWindow(window);\n" +
        "  SDL_Quit();\n" +
        "  return 0;\n" +
        "}\n";
}

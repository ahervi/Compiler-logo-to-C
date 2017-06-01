package logo;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by noye on 22/05/2017.
 */
public class Test {
    static final String SHELL = "bash";
    static final String LINKER_OPTION = "-framework SDL2";

    public static void main(String[] args) throws Exception {
        String filename = args[0];
        String[] args0 = new String[2];
        args0[0] = filename;
        args0[1] = "-v";
        // compile in C
        Logo.main(args0);
        // try to compile C file
        String CFilename = filename.replaceFirst("\\.logo\\z", ".c");
        String OFilename = filename.replaceFirst("\\.logo\\z", "");
        String shellCmd = "gcc " + CFilename + " " + "test/logo.c " + "-o " + OFilename + " " + LINKER_OPTION;
        execute(shellCmd);
        // try to execute object file
        shellCmd = "./" + OFilename;
        execute(shellCmd);
    }

    static void execute(String shellCmd) throws IOException, InterruptedException {
        String[] cmd = {SHELL, "-c", shellCmd};
        System.out.println(shellCmd);
        Runtime.getRuntime().exec(cmd).waitFor();
    }
}

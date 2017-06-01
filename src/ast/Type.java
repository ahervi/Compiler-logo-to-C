package ast;

import errors.CompilationError;

/**
 * Created by Antoine on 16/05/2017.
 */
public enum Type {
    NUM, BOOL, UNKNOWN, LIST, STR, ANY, VOID;

    public String toString(String name) {

    StringBuilder res = new StringBuilder();
        switch(this){
        case STR:
            res.append( "char " + name + "[]");
            break;
        case NUM:
            res.append( "float " + name);

            break;
        case BOOL:
            res.append( "bool " + name);
            break;
        case LIST:
            res.append("plist " + name);

    }
    return res.toString();


    }}

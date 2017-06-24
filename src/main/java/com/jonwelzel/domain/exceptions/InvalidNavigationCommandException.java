package com.jonwelzel.domain.exceptions;

public class InvalidNavigationCommandException extends Exception {
    public InvalidNavigationCommandException(String navigationCommands) {
        super("Invalid navigation command(s): \"" + navigationCommands + "\"");
    }
}

package com.jonwelzel.domain.enums;

public enum RobotActions {
    TURN_LEFT("L"),
    TURN_RIGHT("R"),
    MOVE("M");

    private String letter;

    RobotActions(String letter) {
        this.letter = letter;
    }

    public String letter() {
        return letter;
    }

    public static boolean contains(String value) {
        for(RobotActions robotActions : RobotActions.values()) {
            if(robotActions.letter().equals(value)) {
                return true;
            }
        }

        return false;
    }
}

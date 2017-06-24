package com.jonwelzel.domain.enums;

public enum MoveDirection {
    LEFT("L"),
    RIGHT("R"),
    MOVE("M");

    private String letter;

    MoveDirection(String letter) {
        this.letter = letter;
    }

    public String letter() {
        return letter;
    }
}

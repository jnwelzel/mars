package com.jonwelzel.domain.enums;

public enum Compass {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private String letter;

    Compass(String letter) {
        this.letter = letter;
    }

    public String letter() {
        return letter;
    }
}

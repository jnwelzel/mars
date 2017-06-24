package com.jonwelzel.domain.models;

import com.jonwelzel.domain.enums.Compass;

public class RobotPosition {
    private CartesianCoordinate coordinate;
    private Compass facingDirection;

    public RobotPosition(CartesianCoordinate coordinate, Compass facingDirection) {
        this.coordinate = coordinate;
        this.facingDirection = facingDirection;
    }

    public CartesianCoordinate getCoordinate() {
        return coordinate;
    }

    public Compass getFacingDirection() {
        return facingDirection;
    }

    public String toString() {
        return "(" + coordinate.getXAxisPosition() + ", " + coordinate.getYAxisPosition() + ", " + facingDirection.letter() + ")";
    }
}

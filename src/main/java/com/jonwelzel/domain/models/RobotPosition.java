package com.jonwelzel.domain.models;

import com.jonwelzel.domain.enums.Compass;

public class RobotPosition {
    private String xAxisPosition;
    private String yAxisPosition;
    private Compass facingDirection;

    public RobotPosition(String xAxisPosition, String yAxisPosition, Compass facingDirection) {
        this.xAxisPosition = xAxisPosition;
        this.yAxisPosition = yAxisPosition;
        this.facingDirection = facingDirection;
    }

    public String toString() {
        return "(" + xAxisPosition + "," + yAxisPosition + "," + facingDirection.letter() + ")";
    }
}

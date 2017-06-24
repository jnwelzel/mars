package com.jonwelzel.domain.models;

public class CartesianCoordinate {
    private int xAxisPosition;
    private int yAxisPosition;

    public CartesianCoordinate(int xAxisPosition, int yAxisPosition) {
        this.xAxisPosition = xAxisPosition;
        this.yAxisPosition = yAxisPosition;
    }

    public int getXAxisPosition() {
        return xAxisPosition;
    }

    public int getYAxisPosition() {
        return yAxisPosition;
    }

    public String toString() {
        return "(" + xAxisPosition + "," + yAxisPosition + ")";
    }
}

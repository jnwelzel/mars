package com.jonwelzel.domain.exceptions;

import com.jonwelzel.domain.models.CartesianCoordinate;

public class NavigationCommandOutOfBoundsException extends Exception {
    public NavigationCommandOutOfBoundsException(CartesianCoordinate coordinates) {
        super("Coordinates out of bounds: " + coordinates.toString());
    }
}

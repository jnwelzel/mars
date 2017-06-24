package com.jonwelzel.application;

import com.jonwelzel.domain.enums.Compass;
import com.jonwelzel.domain.models.CartesianCoordinate;
import com.jonwelzel.domain.models.RobotPosition;

public class NavigationService {
    private static final RobotPosition INITIAL_POSITION = new RobotPosition(new CartesianCoordinate(0, 0), Compass.NORTH);
}

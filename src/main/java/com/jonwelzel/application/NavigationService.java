package com.jonwelzel.application;

import com.jonwelzel.domain.entities.NavigationEntity;
import com.jonwelzel.domain.enums.Compass;
import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import com.jonwelzel.domain.models.CartesianCoordinate;
import com.jonwelzel.domain.models.NavInstruction;
import com.jonwelzel.domain.models.RobotPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NavigationService {
    private static final RobotPosition INITIAL_POSITION = new RobotPosition(new CartesianCoordinate(0, 0), Compass.NORTH);

    @Autowired
    NavigationEntity navigationEntity;

    public RobotPosition navigate(String commands) throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        return navigationEntity.processNavigationInstruction(INITIAL_POSITION, new NavInstruction(commands));
    }
}

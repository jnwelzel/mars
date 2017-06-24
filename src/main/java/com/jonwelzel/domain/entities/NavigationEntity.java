package com.jonwelzel.domain.entities;

import com.jonwelzel.domain.enums.Compass;
import com.jonwelzel.domain.enums.RobotActions;
import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import com.jonwelzel.domain.models.CartesianCoordinate;
import com.jonwelzel.domain.models.NavInstruction;
import com.jonwelzel.domain.models.RobotPosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NavigationEntity {
    public RobotPosition processNavigationInstruction(RobotPosition initialPosition, NavInstruction instruction) throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        if(!instruction.isValid())
            throw new InvalidNavigationCommandException(instruction.getText());

        List<RobotActions> individualCommands = instruction.individualCommands();
        RobotPosition currentRobotPosition = initialPosition;

        for(RobotActions command : individualCommands) {
            currentRobotPosition = executeIndividualCommand(currentRobotPosition, command);
        }

        return currentRobotPosition;
    }

    public RobotPosition executeIndividualCommand(RobotPosition currentRobotPosition, RobotActions command) throws NavigationCommandOutOfBoundsException {
        if(command.equals(RobotActions.MOVE)) {
            return new RobotPosition(moveRobot(currentRobotPosition.getFacingDirection(), currentRobotPosition.getCoordinate()), currentRobotPosition.getFacingDirection());
        } else {
            if(command.equals(RobotActions.TURN_LEFT)) {
                return new RobotPosition(currentRobotPosition.getCoordinate(), turnRobotLeft(currentRobotPosition.getFacingDirection()));
            } else {
                return new RobotPosition(currentRobotPosition.getCoordinate(), turnRobotRight(currentRobotPosition.getFacingDirection()));
            }
        }
    }

    public Compass turnRobotLeft(Compass facingDirection) {
        if(facingDirection.equals(Compass.EAST)) {
            return Compass.NORTH;
        }
        if(facingDirection.equals(Compass.WEST)) {
            return Compass.SOUTH;
        }
        if(facingDirection.equals(Compass.NORTH)) {
            return Compass.WEST;
        }

        return Compass.EAST;
    }

    public Compass turnRobotRight(Compass facingDirection) {
        if(facingDirection.equals(Compass.EAST)) {
            return Compass.SOUTH;
        }
        if(facingDirection.equals(Compass.WEST)) {
            return Compass.NORTH;
        }
        if(facingDirection.equals(Compass.NORTH)) {
            return Compass.EAST;
        }

        return Compass.WEST;
    }

    public CartesianCoordinate moveRobot(Compass currentFacingDirection, CartesianCoordinate currentCoordinates) throws NavigationCommandOutOfBoundsException {
        CartesianCoordinate newCoordinates = new CartesianCoordinate(currentCoordinates.getXAxisPosition(), currentCoordinates.getYAxisPosition());

        if(currentFacingDirection.equals(Compass.EAST)) {
            // X + 1
            newCoordinates = new CartesianCoordinate(currentCoordinates.getXAxisPosition() + 1, currentCoordinates.getYAxisPosition());
        } else if (currentFacingDirection.equals(Compass.WEST)) {
            // X - 1
            newCoordinates = new CartesianCoordinate(currentCoordinates.getXAxisPosition() - 1, currentCoordinates.getYAxisPosition());
        } else if (currentFacingDirection.equals(Compass.NORTH)) {
            // Y + 1
            newCoordinates = new CartesianCoordinate(currentCoordinates.getXAxisPosition(), currentCoordinates.getYAxisPosition() + 1);
        } else if (currentFacingDirection.equals(Compass.SOUTH)) {
            // Y - 1
            newCoordinates = new CartesianCoordinate(currentCoordinates.getXAxisPosition(), currentCoordinates.getYAxisPosition() - 1);
        }

        if(newCoordinates.getXAxisPosition() > 4 || newCoordinates.getYAxisPosition() > 4 || newCoordinates.getXAxisPosition() < 0 || newCoordinates.getYAxisPosition() < 0)
            throw new NavigationCommandOutOfBoundsException(newCoordinates);

        return newCoordinates;
    }
}

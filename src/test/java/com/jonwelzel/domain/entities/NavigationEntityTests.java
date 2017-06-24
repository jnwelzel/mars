package com.jonwelzel.domain.entities;

import com.jonwelzel.domain.enums.Compass;
import com.jonwelzel.domain.enums.RobotActions;
import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import com.jonwelzel.domain.models.CartesianCoordinate;
import com.jonwelzel.domain.models.NavInstruction;
import com.jonwelzel.domain.models.RobotPosition;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationEntityTests {

    private NavigationEntity navigationEntity;
    private RobotPosition initialPosition;

    @Before
    public void setUp() {
        navigationEntity = new NavigationEntity();
        initialPosition = new RobotPosition(new CartesianCoordinate(0, 0), Compass.NORTH);
    }

    @Test
    public void moveRobotWhenFacingNorthTest() throws NavigationCommandOutOfBoundsException {
        CartesianCoordinate newCoordinates = navigationEntity.moveRobot(initialPosition.getFacingDirection(), initialPosition.getCoordinate());

        assertThat(newCoordinates.getXAxisPosition()).isEqualTo(0);
        assertThat(newCoordinates.getYAxisPosition()).isEqualTo(1);
    }

    @Test
    public void moveRobotWhenFacingSouthTest() throws NavigationCommandOutOfBoundsException {
        CartesianCoordinate newCoordinates = navigationEntity.moveRobot(Compass.SOUTH, new CartesianCoordinate(0, 1));

        assertThat(newCoordinates.getXAxisPosition()).isEqualTo(0);
        assertThat(newCoordinates.getYAxisPosition()).isEqualTo(0);
    }

    @Test
    public void moveRobotWhenFacingEastTest() throws NavigationCommandOutOfBoundsException {
        CartesianCoordinate newCoordinates = navigationEntity.moveRobot(Compass.EAST, initialPosition.getCoordinate());

        assertThat(newCoordinates.getXAxisPosition()).isEqualTo(1);
        assertThat(newCoordinates.getYAxisPosition()).isEqualTo(0);
    }

    @Test
    public void moveRobotWhenFacingWestTest() throws NavigationCommandOutOfBoundsException {
        CartesianCoordinate newCoordinates = navigationEntity.moveRobot(Compass.WEST, new CartesianCoordinate(1, 0));

        assertThat(newCoordinates.getXAxisPosition()).isEqualTo(0);
        assertThat(newCoordinates.getYAxisPosition()).isEqualTo(0);
    }

    @Test
    public void turnRobotRightWhenFacingNorthTest() {
        Compass newDirection = navigationEntity.turnRobotRight(Compass.NORTH);

        assertThat(newDirection).isEqualTo(Compass.EAST);
    }

    @Test
    public void turnRobotRightWhenFacingSouthTest() {
        Compass newDirection = navigationEntity.turnRobotRight(Compass.SOUTH);

        assertThat(newDirection).isEqualTo(Compass.WEST);
    }

    @Test
    public void turnRobotRightWhenFacingEastTest() {
        Compass newDirection = navigationEntity.turnRobotRight(Compass.EAST);

        assertThat(newDirection).isEqualTo(Compass.SOUTH);
    }

    @Test
    public void turnRobotRightWhenFacingWestTest() {
        Compass newDirection = navigationEntity.turnRobotRight(Compass.WEST);

        assertThat(newDirection).isEqualTo(Compass.NORTH);
    }

    @Test
    public void turnRobotLeftWhenFacingNorthTest() {
        Compass newDirection = navigationEntity.turnRobotLeft(Compass.NORTH);

        assertThat(newDirection).isEqualTo(Compass.WEST);
    }

    @Test
    public void turnRobotLeftWhenFacingSouthTest() {
        Compass newDirection = navigationEntity.turnRobotLeft(Compass.SOUTH);

        assertThat(newDirection).isEqualTo(Compass.EAST);
    }

    @Test
    public void turnRobotLeftWhenFacingEastTest() {
        Compass newDirection = navigationEntity.turnRobotLeft(Compass.EAST);

        assertThat(newDirection).isEqualTo(Compass.NORTH);
    }

    @Test
    public void turnRobotLeftWhenFacingWestTest() {
        Compass newDirection = navigationEntity.turnRobotLeft(Compass.WEST);

        assertThat(newDirection).isEqualTo(Compass.SOUTH);
    }

    @Test
    public void executeIndividualCommandMoveTest() throws NavigationCommandOutOfBoundsException {
        RobotPosition newRobotPosition = navigationEntity.executeIndividualCommand(initialPosition, RobotActions.MOVE);

        assertThat(newRobotPosition.getCoordinate().getXAxisPosition()).isEqualTo(0);
        assertThat(newRobotPosition.getCoordinate().getYAxisPosition()).isEqualTo(1);
        assertThat(newRobotPosition.getFacingDirection()).isEqualTo(initialPosition.getFacingDirection());
    }

    @Test
    public void executeIndividualCommandTurnRightTest() throws NavigationCommandOutOfBoundsException {
        RobotPosition newRobotPosition = navigationEntity.executeIndividualCommand(initialPosition, RobotActions.TURN_RIGHT);

        assertThat(newRobotPosition.getCoordinate().getXAxisPosition()).isEqualTo(0);
        assertThat(newRobotPosition.getCoordinate().getYAxisPosition()).isEqualTo(0);
        assertThat(newRobotPosition.getFacingDirection()).isEqualTo(Compass.EAST);
    }

    @Test
    public void executeIndividualCommandTurnLeftTest() throws NavigationCommandOutOfBoundsException {
        RobotPosition newRobotPosition = navigationEntity.executeIndividualCommand(initialPosition, RobotActions.TURN_LEFT);

        assertThat(newRobotPosition.getCoordinate().getXAxisPosition()).isEqualTo(0);
        assertThat(newRobotPosition.getCoordinate().getYAxisPosition()).isEqualTo(0);
        assertThat(newRobotPosition.getFacingDirection()).isEqualTo(Compass.WEST);
    }

    @Test
    public void processNavigationInstructionTest() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "MMRMMRMM";

        RobotPosition finalRobotPosition = navigationEntity.processNavigationInstruction(initialPosition, new NavInstruction(commands));

        assertThat(finalRobotPosition.toString()).isEqualTo("(2, 0, S)");
    }

    @Test(expected = InvalidNavigationCommandException.class)
    public void processNavigationInstructionWithInvalidCommandsTest() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "AAA";

        navigationEntity.processNavigationInstruction(initialPosition, new NavInstruction(commands));
    }

    @Test(expected = NavigationCommandOutOfBoundsException.class)
    public void processNavigationInstructionWithOutOfBoundsCommandsTest() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "LM";

        navigationEntity.processNavigationInstruction(initialPosition, new NavInstruction(commands));
    }

}

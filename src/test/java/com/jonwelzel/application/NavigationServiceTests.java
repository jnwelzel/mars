package com.jonwelzel.application;

import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import com.jonwelzel.domain.models.RobotPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationServiceTests {

    @Autowired
    NavigationService navigationService;

    @Test
    public void navigateWithValidCommands() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "MMRMMRMM";

        RobotPosition finalRobotPosition = navigationService.navigate(commands);

        assertThat(finalRobotPosition.toString()).isEqualTo("(2, 0, S)");
    }

    @Test(expected = InvalidNavigationCommandException.class)
    public void navigateWithInvalidCommands() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "AAA";

        navigationService.navigate(commands);
    }

    @Test(expected = NavigationCommandOutOfBoundsException.class)
    public void navigateWithOutOfBoundsCommands() throws InvalidNavigationCommandException, NavigationCommandOutOfBoundsException {
        String commands = "LM";

        navigationService.navigate(commands);
    }
}

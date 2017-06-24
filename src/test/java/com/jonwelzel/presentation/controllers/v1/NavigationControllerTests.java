package com.jonwelzel.presentation.controllers.v1;

import com.jonwelzel.application.NavigationService;
import com.jonwelzel.domain.enums.Compass;
import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import com.jonwelzel.domain.models.CartesianCoordinate;
import com.jonwelzel.domain.models.RobotPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NavigationController.class)
public class NavigationControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NavigationService navigationService;

    @Test
    public void postCommandsTest() throws Exception {
        String commands = "MMRMMRMM";
        RobotPosition finalRobotPosition = new RobotPosition(new CartesianCoordinate(2, 0), Compass.SOUTH);

        given(this.navigationService.navigate(commands)).willReturn(finalRobotPosition);
        this.mvc.perform(post("/rest/mars/" + commands)).andExpect(status().isOk()).andExpect(content().string("(2, 0, S)"));
    }

    @Test
    public void postInvalidCommandsTest() throws Exception {
        String commands = "AAA";

        given(this.navigationService.navigate(commands)).willThrow(InvalidNavigationCommandException.class);
        this.mvc.perform(post("/rest/mars/" + commands)).andExpect(status().isBadRequest()).andExpect(content().string("400 Bad Request"));
    }

    @Test
    public void postOutOfBoundsCommandsTest() throws Exception {
        String commands = "LM";

        given(this.navigationService.navigate(commands)).willThrow(NavigationCommandOutOfBoundsException.class);
        this.mvc.perform(post("/rest/mars/" + commands)).andExpect(status().isBadRequest()).andExpect(content().string("400 Bad Request"));
    }
}

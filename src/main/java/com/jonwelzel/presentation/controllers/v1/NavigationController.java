package com.jonwelzel.presentation.controllers.v1;

import com.jonwelzel.application.NavigationService;
import com.jonwelzel.domain.exceptions.InvalidNavigationCommandException;
import com.jonwelzel.domain.exceptions.NavigationCommandOutOfBoundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    @RequestMapping(value = "/rest/mars/{commands}", method = RequestMethod.POST)
    ResponseEntity<String> navigate(@PathVariable String commands) {
        try {
            return ResponseEntity.ok().body(navigationService.navigate(commands).toString());
        } catch (InvalidNavigationCommandException | NavigationCommandOutOfBoundsException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("400 Bad Request");
        }
    }
}

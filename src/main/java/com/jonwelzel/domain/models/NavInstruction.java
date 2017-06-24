package com.jonwelzel.domain.models;

import com.jonwelzel.domain.enums.RobotActions;

import java.util.ArrayList;
import java.util.List;

public class NavInstruction {
    private String text;

    public NavInstruction(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isValid() {
        if(text == null || text.trim().equals("")) {
            return false;
        }

        for(String character: text.split("")) {
            if(!RobotActions.contains(character)) {
                return false;
            }
        }

        return true;
    }

    public List<RobotActions> individualCommands() {
        List<RobotActions> commands = new ArrayList<RobotActions>();
        String[] splitCommands = text.split("");

        for(int i = 0; i < splitCommands.length; i++) {
            commands.add(i, RobotActions.valueOf(splitCommands[i]));
        }

        return commands;
    }
}

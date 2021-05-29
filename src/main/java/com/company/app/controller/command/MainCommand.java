package com.company.app.controller.command;

import com.company.app.controller.Controller;
import com.company.app.controller.ICommand;
/**
 * Displays general information about the application.
 */
public class MainCommand implements ICommand {
    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Controller controller = new Controller();
        return controller.intro();
    }
}

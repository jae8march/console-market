package com.company.app.controller.command;

import com.company.app.controller.ICommand;

/**
 * Command does not exist, input error.
 */
public class NotCommand implements ICommand {
    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        if (data.length == 0 || data[0].hashCode() == 0) {
            return "You need to take the command. " +
                    "For more information on a specific command, type -help command-name";
        }
        return data[0] + " is not recognized as an internal command. " +
                "For more information on a specific command, type -help command-name";
    }
}

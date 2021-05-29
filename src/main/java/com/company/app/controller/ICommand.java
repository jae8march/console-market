package com.company.app.controller;

/**
 * Command line interface.
 */
public interface ICommand {
    /**
     * Executes a command, processing input.
     * @param data input, command attribute
     * @return data from program, result of work
     */
    String command(String[] data);
}

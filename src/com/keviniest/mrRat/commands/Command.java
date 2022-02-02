package com.keviniest.mrRat.commands;

/**
 * Improved command handling system
 * https://www.youtube.com/watch?v=o8fuBYUB6cI
 */
public abstract class Command {

    /**
     * Short name (alias) of a command
     */
    public String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void onCommand(String[] args, String command);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

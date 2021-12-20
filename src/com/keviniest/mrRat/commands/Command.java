package com.keviniest.mrRat.commands;

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

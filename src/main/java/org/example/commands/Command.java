package org.example.commands;

public abstract class Command {
    protected String CommandName;
    protected String CommandDescription;


    public Command(String name, String description){
        this.CommandName = name.split(" ",2)[0];
        this.CommandDescription = description;
    }

    /**
     * Executes the Command
     * @param arg The argument passed to the Command
     *
     * @return true, if command executes successfully, else - false
     */

    public abstract boolean go(String arg);
    public String getCommandName(){
        return this.CommandName;
    }
    protected String getCommandDescription(){
        return this.CommandDescription;
    }

    @Override
    public String toString() {
        return  CommandName + " : " + CommandDescription;
    }
}


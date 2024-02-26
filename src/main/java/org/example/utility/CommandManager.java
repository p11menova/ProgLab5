package org.example.utility;

import org.example.commands.AbstractAddCommand;
import org.example.commands.Command;
import org.example.exceptions.NoSuchCommandException;

import java.util.*;

public class CommandManager {
    static HashMap<String,? super Command> commands = new HashMap<>();
    static int HISTORY_SIZE = 10;
    public static final List<String> commandsHistory = new ArrayList<>();
    public static void registerCommand(Command command){
        commands.put(command.getCommandName(), command);
    }
    public static HashMap<String, ? super Command> getCommands(){
        return commands;
    }
    public static void addToHistory(String commandName){
        commandsHistory.add(commandName);
    }
    public static String getHistory(){
        StringBuilder history = new StringBuilder();
        if (!(commandsHistory.isEmpty())){
            for (int i = Math.max(0, commandsHistory.size() - HISTORY_SIZE); i < commandsHistory.size(); i++){
                if (!(commandsHistory.get(i)==null)){ history.append("\n").append(commandsHistory.get(i));}
            }
        }
        return  (history.toString().isEmpty())? "история еще пуста:( " : "последние команд пользователя (<= 10):" + history.toString();
    }
    public static boolean go(String CommandName, String args) {
        try {
            if (commands.get(CommandName) == null) throw new NoSuchCommandException();
            if (commands.get(CommandName).getClass().getSuperclass() == AbstractAddCommand.class && (Console.in.equals(Console.script_in))){
                Console.notPrinting = true;
            }
            return ((Command) commands.get(CommandName)).go(args);
        } catch (NoSuchCommandException e) {
            Console.print_error(e.getMessage());
        }
        return false;
    }
}

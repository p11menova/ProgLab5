package org.example.utility;

import org.example.commands.*;

import java.util.Scanner;

public class RunManager {
    public static void go() {

        String path = System.getenv("FILE_PATH");
        if(path == null || path.isBlank()){
            Console.print_error("puffа нет)");
            System.exit(1);
        }

        FileManager fileManager = new FileManager(path);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        collectionManager.loadCollectionFromFile();

        CommandManager.registerCommand(new ExitCommand());
        CommandManager.registerCommand(new HelpCommand());
        CommandManager.registerCommand(new InfoCommand());
        CommandManager.registerCommand(new HistoryCommand());
        CommandManager.registerCommand(new InsertCommand(collectionManager));
        CommandManager.registerCommand(new ShowCommand(collectionManager));
        CommandManager.registerCommand(new UpdateCommand(collectionManager));
        CommandManager.registerCommand(new RemoveByKeyCommand(collectionManager));
        CommandManager.registerCommand(new ClearCommand(collectionManager));
        CommandManager.registerCommand(new RemoveIfGreaterCommand(collectionManager));
        CommandManager.registerCommand(new ReplaceIfGreaterCommand(collectionManager));
        CommandManager.registerCommand(new ReplaceIfLowerCommand(collectionManager));
        CommandManager.registerCommand(new RemoveAllByPersonCommand(collectionManager));
        CommandManager.registerCommand(new GroupCountingByCoordinatesCommand(collectionManager));
        CommandManager.registerCommand(new SaveCommand(collectionManager, fileManager));
        CommandManager.registerCommand(new ExecuteScriptCommand());


        Console.in = new Scanner(System.in);
        Console.readUserInput();
    }
}
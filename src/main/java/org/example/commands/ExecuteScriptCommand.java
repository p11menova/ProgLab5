package org.example.commands;

import org.example.utility.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public boolean go(String arg) {
        try {
            File file = new File(arg.trim());
            Scanner in = new Scanner(file);
           // Console.readingScript = true;
            Console.readScriptInput(in, file.getName());
            Console.denyFileMode();
            return true;
        } catch (FileNotFoundException e) {
            Console.print_error("файл не найден..");
        }

        return false;
    }
}

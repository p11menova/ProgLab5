package org.example.utility;

import org.example.commands.ExecuteScriptCommand;

import java.util.Objects;
import java.util.Scanner;

public class Console  {
    public static Scanner in; // текущий сканнер
    public static Scanner script_in; // сканнер из скрипта
    public static String promt1 = "$ ";
    public static String promt2 = " ?>";
    public static boolean notPrinting = false;


    public static void println(Object obj) {
        if (!notPrinting) System.out.println(obj);
    }
    public static void printf(Object obj) { if (!notPrinting) System.out.printf((String) obj);
    }

    public static void print_error(Object obj) {
        if (!notPrinting) System.out.println("error: " + obj);}
    public static void println_with_t(Object obj){if (!notPrinting) System.out.println("\t"+obj);}
    public static void printf_with_t(Object obj){if (!notPrinting) System.out.printf("\t"+obj);}

    public static void readUserInput() {
        while (true) {
            printf(Console.promt1);
            String in_data = in.nextLine().trim() + " ";
            //Console.println(in_data);
            if (in_data.equals(" "))
                println("пустой ввод :(( для получения информации о возможных командах введите 'help'");
            else {
                String[] in_data_splited = in_data.split(" ", 2);
                String currentCommandName = in_data_splited[0];
                String currentCommandArgs = in_data_splited[1];
                if (CommandManager.go(currentCommandName, currentCommandArgs))
                    CommandManager.addToHistory(currentCommandName);
            }
        }
    }

    public static void setScriptScanner(Scanner in1) {
        script_in = in1;
    }
    public static void setFileMode(){
        in = script_in;
    }
    public static void denyFileMode(){
        notPrinting = false;
        in = new Scanner(System.in);
    }
    public static void readScriptInput(Scanner in, String filename){
        while (in.hasNextLine()){

            Console.setScriptScanner(in);
            Console.setFileMode();

            String in_data = Console.script_in.nextLine().trim() + " ";

            if (!in_data.equals(" ")){
                String[] in_data_splited = in_data.split(" ", 2);
                String currentCommandName = in_data_splited[0];
                String currentCommandArgs = in_data_splited[1];

                if (Objects.equals(currentCommandName, (new ExecuteScriptCommand()).getCommandName()) && filename.equals(currentCommandArgs.trim())){
                    Console.print_error("рекурсивный вызов скрипта запрещён.(\n прерывание выполнения execute_script");
                    break;
                }
                if (CommandManager.go(currentCommandName, currentCommandArgs)){
                    CommandManager.addToHistory(currentCommandName);
                    Console.notPrinting = false;

                }
            }
        }

    }


}

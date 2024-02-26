package org.example.exceptions;

public class NoSuchCommandException extends Exception{
    public NoSuchCommandException(){
        super("нет такой команды ;( \nдля получения списка команд введите 'help'");
    }
}

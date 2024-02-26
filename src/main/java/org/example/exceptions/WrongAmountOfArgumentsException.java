package org.example.exceptions;

public class WrongAmountOfArgumentsException extends Exception{
    public WrongAmountOfArgumentsException(){
        super("неправильное число аргументов команды.");
    }

}

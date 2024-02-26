package org.example.exceptions;

public class NoSuchElementException extends Exception{
    public NoSuchElementException(){
        super("элемента с таким айди не существует(");
    }
}

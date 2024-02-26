package org.example.exceptions;

public class CollectionIdIsTakenException extends Exception{
    public CollectionIdIsTakenException(){
        super("элемент с таким айди уже существует( попробуйте еще раз!");
    }
}

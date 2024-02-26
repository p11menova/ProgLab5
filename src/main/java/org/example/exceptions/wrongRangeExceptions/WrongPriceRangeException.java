package org.example.exceptions.wrongRangeExceptions;

public class WrongPriceRangeException extends Exception{
    public WrongPriceRangeException(){
        super("Поле price должно быть >0");
    }
}

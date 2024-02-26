package org.example.exceptions;

import org.example.models.TicketType;
import org.example.utility.Console;

public class NoSuchTicketTypeException extends Exception{
    public NoSuchTicketTypeException(){
        super("нет такого типа билета(\nвозможные варианты:");

    }
}

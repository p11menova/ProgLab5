package org.example.exceptions.wrongRangeExceptions;

import org.example.models.Coordinates;

public class WrongXCoordRangeException extends Exception{
    public WrongXCoordRangeException(){
        super("значение поля x не может быть меньше "  + Coordinates.xMin);
    }
}

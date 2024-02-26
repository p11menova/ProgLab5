package org.example.exceptions.wrongRangeExceptions;

import org.example.models.Coordinates;

public class WrongYCoordRangeException extends Exception{
    public WrongYCoordRangeException(){
        super("значение поля y не должно превышать " + Coordinates.yMax);
    }
}

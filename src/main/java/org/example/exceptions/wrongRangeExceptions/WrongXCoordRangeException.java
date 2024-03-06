package org.example.exceptions.wrongRangeExceptions;

import org.example.models.Coordinates;

/**
 * Исключение неверного диапазона х-координаты. Выбрасывается при попытке задать х-координате значение, меньше минимально допустимого.
 */
public class WrongXCoordRangeException extends Exception{
    public WrongXCoordRangeException(){
        super("значение поля x не может быть меньше "  + Coordinates.xMin);
    }
}

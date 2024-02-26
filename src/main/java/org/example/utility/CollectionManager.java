package org.example.utility;

import org.example.models.Person;
import org.example.models.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    private static final Hashtable<Integer, Ticket> TicketsCollection = new Hashtable<>();
    private static LocalDateTime InitilizationDate = LocalDateTime.now();
    private final FileManager fileParsingManager;
    public CollectionManager(FileManager fileParsingManager){
        this.fileParsingManager = fileParsingManager;
    }
    public Ticket getById(int id){
        return TicketsCollection.get(id);
    }
    public void addToCollection(Ticket ticket){
        TicketsCollection.put(ticket.get_id(), ticket);

    }
    public void loadCollectionFromFile(){
        fileParsingManager.addDataToCollection(this);
        InitilizationDate = LocalDateTime.now();
    }
    public static int getCollectionLength(){
        return TicketsCollection.size();
    }
    public static String getCreationDate(){
        return DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss").format(InitilizationDate);
    }
    public static String getInfo() {
        return "информация о коллекции:\n-хранимый тип:" + Ticket.class.toString() +
                "\n-количество элементов:"+CollectionManager.getCollectionLength()
                +"\n-дата инициализации:"+CollectionManager.getCreationDate();
    }
    public boolean isIdTaken(int i){
        return !(TicketsCollection.get(i) == null);
    }
    public void removeWithId(int id){
        TicketsCollection.remove(id);
    }
    public void clear(){
        TicketsCollection.clear();
    }

    public Hashtable<Integer, Ticket> getTicketsCollection() {
        return TicketsCollection;
    }

    @Override
    public String toString() {
        if (getCollectionLength() == 0) return "коллекция еще пуста(";
        StringBuilder info = new StringBuilder();
        info.append("TicketsCollection: [ \n");
        Hashtable<Integer, Ticket> ht = TicketsCollection;
        ht.forEach((key,value) -> info.append(key).append(" -> ").append(ht.get(key).toString()).append("\n\n"));
        ;
        return info.toString().trim()+"\n]";
    }
}

package org.example.models;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import org.example.utility.CollectionManager;
import org.example.utility.ModelsAskers.NewTicketAsker;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@JacksonXmlRootElement(localName = "tickets")
public class Tickets {
    @JacksonXmlProperty(localName = "ticket")
    @JacksonXmlElementWrapper(useWrapping = false)

    private List<Ticket> tickets = new ArrayList<>();
    public void addToList(Ticket ticket){
        this.tickets.add(ticket);
    }

    public boolean addToCollectionIfOkay(CollectionManager collectionManager){
        AtomicBoolean are_added = new AtomicBoolean(false);
        for (Ticket ticket: tickets){
            if (new NewTicketAsker().validateTicket(ticket)){
                collectionManager.addToCollection(ticket);
            are_added.set(true);}
        }
        return are_added.get();
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Ticket ticket: tickets){
            result.append("\n").append(ticket.toString());
        }
        return result.toString();
    }
}

package org.example.commands;

import org.example.models.Ticket;
import org.example.utility.CollectionManager;
import org.example.utility.Console;
import org.example.utility.ModelsAskers.NewTicketAsker;

public abstract class AbstractAddCommand extends Command {
    protected CollectionManager collectionManager;

    public AbstractAddCommand(String CommandName, String CommandDescription, CollectionManager collectionManager) {

        super(CommandName, CommandDescription);
        this.collectionManager = collectionManager;
    }

    public Ticket makeNewTicket(int id) {
        NewTicketAsker newTicketAsker = new NewTicketAsker();
        if (!Console.notPrinting){return newTicketAsker.validateTicketFromInteractiveMode(id);}
        return newTicketAsker.validateTicketFromScript(id);
    }
}

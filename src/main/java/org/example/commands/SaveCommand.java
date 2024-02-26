package org.example.commands;

import org.example.utility.CollectionManager;
import org.example.utility.FileManager;

public class SaveCommand extends Command{
    public CollectionManager collectionManager;
    public FileManager fileManager;
    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public boolean go(String arg) {
        return this.fileManager.writeCollectionToFile(this.collectionManager);

    }
}

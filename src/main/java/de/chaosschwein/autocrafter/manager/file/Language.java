package de.chaosschwein.autocrafter.manager.file;


import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

public class Language {

    private final FileManager fileManager;

    public String noPermission;
    public String noItemInHand;
    public String error;
    public String playerNotFound;
    public String invalidNumber;
    public String playerOnly;
    public String reload;

    public String CrafterInsertItem;
    public String CrafterInsertItemError;
    public String CrafterIsFalse;
    public String CrafterAlreadyExists;
    public String CrafterNoRezept;
    public String CrafterCreated;
    public String CrafterDeleted;

    public String ReceiverCreated;
    public String ReceiverDeleted;
    public String ReceiverIsFalse;
    public String ReceiverAlreadyExists;
    public String ReceiverMaterialAlreadyExists;

    public String SenderCreated;
    public String SenderDeleted;
    public String SenderIsFalse;
    public String SenderAlreadyExists;

    public Language() {
        this.fileManager = new FileManager("language");
        setDefault();

        noPermission = fileManager.read("noPermission");
        noItemInHand = fileManager.read("noItemInHand");
        error = fileManager.read("error");
        playerNotFound = fileManager.read("playerNotFound");
        invalidNumber = fileManager.read("invalidNumber");
        playerOnly = fileManager.read("playerOnly");
        reload = fileManager.read("reload");

        CrafterInsertItem = fileManager.read("autocrafter.insertItem");
        CrafterInsertItemError = fileManager.read("autocrafter.insertItemError");
        CrafterIsFalse = fileManager.read("autocrafter.isFalse");
        CrafterAlreadyExists = fileManager.read("autocrafter.alreadyExists");
        CrafterNoRezept = fileManager.read("autocrafter.noRezept");
        CrafterCreated = fileManager.read("autocrafter.created");
        CrafterDeleted = fileManager.read("autocrafter.deleted");

        ReceiverCreated = fileManager.read("receiver.created");
        ReceiverDeleted = fileManager.read("receiver.deleted");
        ReceiverIsFalse = fileManager.read("receiver.isFalse");
        ReceiverAlreadyExists = fileManager.read("receiver.alreadyExists");
        ReceiverMaterialAlreadyExists = fileManager.read("receiver.materialAlreadyExists");

        SenderCreated = fileManager.read("sender.created");
        SenderDeleted = fileManager.read("sender.deleted");
        SenderIsFalse = fileManager.read("sender.isFalse");
        SenderAlreadyExists = fileManager.read("sender.alreadyExists");
    }

    public void setDefault() {
        fileManager.writeDefault(new HashMap<>(){{
            put("noPermission", "&cDu hast keine Rechte dazu!");
            put("noItemInHand", "&cDu musst ein Item in der Hand haben!");
            put("error", "&cEin Fehler ist aufgetreten!");
            put("playerNotFound", "&cDer Spieler wurde nicht gefunden!");
            put("invalidNumber", "&cDie Zahl ist ungültig!");
            put("playerOnly", "&cNur Spieler können diesen Befehl ausführen!");
            put("reload", "&7Das Plugin wurde neu geladen!");

            put("autocrafter.insertItem", "&7Bitte legen Sie das Item, in den Slot oben in der mitte!");
            put("autocrafter.insertItemError", "&7Du musst ein Item reinlegen!");
            put("autocrafter.isFalse", "&7Dieser Block ist kein Crafter!");
            put("autocrafter.alreadyExists", "&7Dieser Crafter ist bereits registriert!");
            put("autocrafter.noRezept", "&7Es gibt kein Rezept für dieses Item!");
            put("autocrafter.created", "&7Der Crafter wurde erstellt!");
            put("autocrafter.deleted", "&7Der Crafter wurde gelöscht!");

            put("receiver.created", "&7Der Receiver wurde erstellt!");
            put("receiver.deleted", "&7Der Receiver wurde gelöscht!");
            put("receiver.isFalse", "&7Dieser Block ist kein Receiver!");
            put("receiver.alreadyExists", "&7Dieser Receiver ist bereits registriert!");
            put("receiver.materialAlreadyExists", "&7Dieses Material ist bereits registriert!");

            put("sender.created", "&7Der Sender wurde erstellt!");
            put("sender.deleted", "&7Der Sender wurde gelöscht!");
            put("sender.isFalse", "&7Dieser Block ist kein Sender!");
            put("sender.alreadyExists", "&7Dieser Sender ist bereits registriert!");
        }});
    }
}

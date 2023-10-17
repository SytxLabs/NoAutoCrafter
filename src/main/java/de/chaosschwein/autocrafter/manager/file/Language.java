package de.chaosschwein.autocrafter.manager.file;


import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

@SuppressWarnings("SpellCheckingInspection")
public class Language {

    public final String noPermission;
    public final String error;
    public final String reload;
    public final String CrafterInsertItem;
    public final String CrafterInsertItemError;
    public final String CrafterIsFalse;
    public final String CrafterAlreadyExists;
    public final String CrafterCreated;
    public final String CrafterDeleted;
    public final String ReceiverCreated;
    public final String ReceiverDeleted;
    public final String ReceiverIsFalse;
    public final String ReceiverAlreadyExists;
    public final String ReceiverMaterialAlreadyExists;
    public final String SenderCreated;
    public final String SenderDeleted;
    public final String SenderIsFalse;
    public final String SenderAlreadyExists;
    public final String InventoryBack;
    public final String InventoryAddItem;
    public final String InventoryAddItemLore;
    public final String InventoryCreateReceiver;
    public final String InventoryCreateSender;
    private final FileManager fileManager;

    public Language() {
        this.fileManager = new FileManager("language");
        setDefault();

        noPermission = fileManager.read("noPermission");
        error = fileManager.read("error");
        reload = fileManager.read("reload");

        CrafterInsertItem = fileManager.read("autocrafter.insertItem");
        CrafterInsertItemError = fileManager.read("autocrafter.insertItemError");
        CrafterIsFalse = fileManager.read("autocrafter.isFalse");
        CrafterAlreadyExists = fileManager.read("autocrafter.alreadyExists");
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

        InventoryBack = fileManager.read("inventory.back");
        InventoryAddItem = fileManager.read("inventory.addItem");
        InventoryAddItemLore = fileManager.read("inventory.addItemLore");
        InventoryCreateReceiver = fileManager.read("inventory.createReceiver");
        InventoryCreateSender = fileManager.read("inventory.createSender");
    }

    public void setDefault() {
        fileManager.writeDefault(new HashMap<>() {{
            put("noPermission", "&cDu hast keine Rechte dazu!");
            put("error", "&cEin Fehler ist aufgetreten!");
            put("playerNotFound", "&cDer Spieler wurde nicht gefunden!");
            put("invalidNumber", "&cDie Zahl ist ungültig!");
            put("reload", "&7Das Plugin wurde neu geladen!");

            put("autocrafter.insertItem", "&7Bitte legen Sie das Item, in den Slot oben in der mitte!");
            put("autocrafter.insertItemError", "&7Du musst ein Item reinlegen!");
            put("autocrafter.isFalse", "&7Dieser Block ist kein Crafter!");
            put("autocrafter.alreadyExists", "&7Dieser Crafter ist bereits registriert!");
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

            put("inventory.back", "&cZurück");
            put("inventory.addItem", "&aItem Reinlegen");
            put("inventory.addItemLore", "&5Lege das Item Rein\n&5Was Erstellt werden sollen!");
            put("inventory.createReceiver", "&aReceiver Erstellen");
            put("inventory.createSender", "&aSender Erstellen");
        }});
    }
}

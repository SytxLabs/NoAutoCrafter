package de.chaosschwein.autocrafter.manager.file;


import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

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
    public final String InventoryNext;
    public final String InventoryAddItem;
    public final String InventoryAddItemLore;
    public final String InventoryCreateSender;
    public final String InventoryCreateChannel;
    public final String InventoryAddChannel;
    public final String InventoryDeleteChannel;
    public final String InventoryUseChannel;
    public final String InventoryNoItem;
    public final String InventorySelectChannel;
    public final String InventoryNoChannel;

    public final String ChannelNotFound;
    public final String ChannelAlreadyExists;

    public final String ChannelTypePrivate;
    public final String ChannelTypeProtected;
    public final String ChannelTypePublic;

    public final String SenderTypeRoundRobin;
    public final String SenderTypeRandom;
    public final String SenderTypeOverflow;

    public final String WrongPassword;

    public final String NewVersionFound;

    public final String ChunkLoaderAlreadyExists;
    public final String ChunkLoaderCreated;
    public final String ChunkLoaderIsFalse;

    public final FileManager fileManager;

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
        InventoryNext = fileManager.read("inventory.next");
        InventoryAddItem = fileManager.read("inventory.addItem");
        InventoryAddItemLore = fileManager.read("inventory.addItemLore");
        InventoryCreateSender = fileManager.read("inventory.createSender");
        InventoryCreateChannel = fileManager.read("inventory.createChannel");
        InventoryAddChannel = fileManager.read("inventory.addChannel");
        InventoryDeleteChannel = fileManager.read("inventory.deleteChannel");
        InventoryUseChannel = fileManager.read("inventory.useChannel");
        InventoryNoItem = fileManager.read("inventory.noItem");
        InventorySelectChannel = fileManager.read("inventory.selectChannel");
        InventoryNoChannel = fileManager.read("inventory.noChannel");

        ChannelNotFound = fileManager.read("channel.notFound");
        ChannelAlreadyExists = fileManager.read("channel.alreadyExists");

        ChannelTypePrivate = fileManager.read("channel_type.private");
        ChannelTypeProtected = fileManager.read("channel_type.protected");
        ChannelTypePublic = fileManager.read("channel_type.public");

        SenderTypeRoundRobin = fileManager.read("sender_type.round_robin");
        SenderTypeRandom = fileManager.read("sender_type.random");
        SenderTypeOverflow = fileManager.read("sender_type.overflow");

        WrongPassword = fileManager.read("wrongPassword");

        NewVersionFound = fileManager.read("newVersionFound");

        ChunkLoaderAlreadyExists = fileManager.read("chunkloader.alreadyExists");
        ChunkLoaderCreated = fileManager.read("chunkloader.created");
        ChunkLoaderIsFalse = fileManager.read("chunkloader.isFalse");
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
            put("inventory.next", "&aWeiter");
            put("inventory.addItem", "&aItem Reinlegen");
            put("inventory.addItemLore", "&5Lege das Item Rein\n&5Was Erstellt werden sollen!");
            put("inventory.createSender", "&aSender Erstellen");
            put("inventory.createChannel", "&aChannel Erstellen");
            put("inventory.addChannel", "&aChannel Hinzufügen");
            put("inventory.deleteChannel", "&cChannel Löschen");
            put("inventory.useChannel", "&aChannel Nutzen");
            put("inventory.noItem", "&cEs wurde kein Item gefunden!");
            put("inventory.selectChannel", "&aChannel Auswählen");
            put("inventory.noChannel", "&cBitte Wähle einen Channel aus!");

            put("channel.notFound", "&cDer Channel wurde nicht gefunden!");
            put("channel.alreadyExists", "&cDer Channel ist bereits registriert!");

            put("sender_type.round_robin", "&7Round Robin");
            put("sender_type.random", "&7Random");
            put("sender_type.overflow", "&7Overflow");

            put("channel_type.private", "&7Private");
            put("channel_type.protected", "&7Protected");
            put("channel_type.public", "&7Public");

            put("wrongPassword", "&cDas Passwort ist falsch!");

            put("newVersionFound", "&7Es wurde eine neue Version gefunden!");

            put("chunkloader.alreadyExists", "&7Dieser ChunkLoader existiert bereits!");
            put("chunkloader.created", "&7Der ChunkLoader wurde erstellt!");
            put("chunkloader.isFalse", "&7Dieser Block ist kein ChunkLoader!");
        }});
    }
}

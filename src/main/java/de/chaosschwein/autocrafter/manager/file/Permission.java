package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

public class Permission {

    public final String CrafterAdmin;
    public final String CrafterCreate;
    public final String ReceiverCreate;
    public final String SenderCreate;
    public final String ChunkLoaderCreate;
    private final FileManager fileManager;

    public Permission() {
        fileManager = new FileManager("permission");

        setDefault();

        CrafterAdmin = fileManager.read("autocrafter.admin");
        CrafterCreate = fileManager.read("autocrafter.create");

        ReceiverCreate = fileManager.read("receiver.create");
        ChunkLoaderCreate = fileManager.read("chunkloader.create");

        SenderCreate = fileManager.read("sender.create");
    }

    public void setDefault() {
        fileManager.writeDefault(new HashMap<>() {{
            put("autocrafter.admin", "autocrafter.admin");
            put("autocrafter.create", "");

            put("receiver.create", "");
            put("chunkloader.create", "");

            put("sender.create", "");
        }});
    }
}

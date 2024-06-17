package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

public class Config {
    private final FileManager config;

    public final String prefix;
    public final boolean crafter;
    public final boolean breaker;
    public final boolean placer;
    public final boolean transport;
    public final boolean farming_station;
    public final boolean entityInteractor;
    public final boolean breeder;
    public final int breederRange;
    public final boolean chunkLoader;
    public final int chunkLoaderRange;
    public final int chunkLoaderBurnTime;

    public Config() {
        config = new FileManager("config");
        setDefaults();
        prefix = config.read("prefix");
        crafter = (boolean) config.read("crafter", true);
        breaker = (boolean) config.read("breaker", true);
        placer = (boolean) config.read("placer", true);
        transport = (boolean) config.read("transport", true);
        farming_station = (boolean) config.read("farming_station", true);
        entityInteractor = (boolean) config.read("entity_interactor", true);
        breeder = (boolean) config.read("breeder.enable", true);
        breederRange = (int) config.read("breeder.range", true);
        chunkLoader = (boolean) config.read("chunk_loader.enable", true);
        chunkLoaderRange = (int) config.read("chunk_loader.range", true);
        chunkLoaderBurnTime = ((int) config.read("chunk_loader.burn_time", true)) * 60 * 20;
    }

    public void setDefaults() {
        config.writeDefault(new HashMap<>() {{
            put("prefix", "&8[&aNoAutoCrafter§8] &7");
            put("crafter", true);
            put("breaker", true);
            put("placer", true);
            put("transport", true);
            put("farming_station", true);
            put("entity_interactor", true);
            put("breeder.enable", true);
            put("breeder.range", 3);
            put("chunk_loader.enable", true);
            put("chunk_loader.range", 3);
            put("chunk_loader.burn_time", 1);
        }});
    }

}

package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.types.ChunkLoader;
import org.bukkit.configuration.ConfigurationSection;


import java.util.ArrayList;
import java.util.List;

public class ChunkLoaderFile {

    public final List<ChunkLoader> chunks = new ArrayList<>();

    private final FileManager fileManager;

    public ChunkLoaderFile() {
        fileManager = new FileManager("data", "chunks");
        load();
    }

    public void load() {
        chunks.clear();
        ConfigurationSection section = fileManager.getConfig().getConfigurationSection("Chunks");
        if (section != null) {
            for (String chunkString : section.getKeys(false)) {
                ChunkLoader c = ChunkLoader.fromString(chunkString);
                if (c != null) {
                    chunks.add(c);
                }
            }
        }
    }

    public void save() {
        fileManager.write("Chunks", null);
        for (ChunkLoader chunk : chunks) {
            String chunkString = chunk.toString();
            fileManager.write("Chunks." + chunkString, true);
        }
    }

    public void addChunk(ChunkLoader chunk) {
        chunks.add(chunk);
        save();
    }

    public void removeChunk(ChunkLoader chunk) {
        chunks.remove(chunk);
        save();
    }

    public boolean containsChunk(ChunkLoader chunk) {
        return chunks.contains(chunk);
    }

    public void clearChunks() {
        chunks.clear();
        save();
    }

    public List<ChunkLoader> getChunks() {
        return chunks;
    }

    public void loadChunks(List<ChunkLoader> chunks) {
        this.chunks.clear();
        this.chunks.addAll(chunks);
        save();
    }
}

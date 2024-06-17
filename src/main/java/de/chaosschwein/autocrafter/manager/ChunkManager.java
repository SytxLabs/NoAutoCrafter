package de.chaosschwein.autocrafter.manager;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;

public class ChunkManager {
    public boolean chunkLoad = true;
    private final HashMap<Chunk, BukkitTask> chunkList = new HashMap<>();

    public void loadChunk(List<Chunk> chunks) {
        for (Chunk chunk : chunks) {
            loadChunk(chunk);
        }
    }

    public void loadChunk(Chunk chunk) {
        if (chunkList.containsKey(chunk)) {
            return;
        }
        if (Bukkit.getWorlds().contains(chunk.getWorld())) {
            try {
                chunk.addPluginChunkTicket(AutoMain.instance);
            } catch (IllegalStateException ignored) {
            }
            chunkList.put(chunk, Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> {
                if (!chunkLoad) {
                    return;
                }
                try {
                    chunk.getWorld().setChunkForceLoaded(chunk.getX(), chunk.getZ(), true);
                } catch (NoSuchMethodError e) {
                    chunk.getWorld().loadChunk(chunk.getX(), chunk.getZ());
                }
            }, 1));
        }
    }

    public void disableChunkLoad() {
        chunkLoad = false;
        for (Chunk chunk : chunkList.keySet()) {
            unloadChunk(chunk);
        }
        chunkList.clear();
    }

    public void unloadChunk(List<Chunk> chunks) {
        unloadChunk(chunks, true);
    }

    public void unloadChunk(List<Chunk> chunks, boolean unload) {
        for (Chunk chunk : chunks) {
            unloadChunk(chunk, unload);
        }
    }

    public void unloadChunk(Chunk chunk) {
        unloadChunk(chunk, true);
    }

    public void unloadChunk(Chunk chunk, boolean unload) {
        if (!chunkList.containsKey(chunk)) {
            return;
        }
        chunk.removePluginChunkTicket(AutoMain.instance);
        chunkList.get(chunk).cancel();
        chunkList.remove(chunk);
        if (!unload) {
            return;
        }
        try {
            chunk.getWorld().setChunkForceLoaded(chunk.getX(), chunk.getZ(), false);
        } catch (NoSuchMethodError e) {
            chunk.getWorld().unloadChunk(chunk);
        }
    }
}
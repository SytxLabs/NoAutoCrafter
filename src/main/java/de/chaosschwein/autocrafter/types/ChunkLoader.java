package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoader {
    private boolean isChunkLoader = false;

    public Beacon Beacon;
    public Block Hopper;
    public Furnace Furnace;

    public List<Chunk> Chunks;

    public ChunkLoader(Block beacon) {
        if (!new CheckBlocks(beacon).isChunkLoader()) {
            return;
        }
        isChunkLoader = true;
        this.Beacon = (Beacon) beacon.getState();
        this.Hopper = beacon.getWorld().getBlockAt(beacon.getX(), beacon.getY() - 1, beacon.getZ());
        this.Furnace = (Furnace) beacon.getWorld().getBlockAt(beacon.getX(), beacon.getY() - 2, beacon.getZ()).getState();
    }

    private List<Chunk> loadChunks() {
        if (this.Beacon == null) {
            return null;
        }
        if (this.Chunks != null && !this.Chunks.isEmpty()) {
            return this.Chunks;
        }
        this.Chunks = new ArrayList<>();
        Chunk c = this.Beacon.getChunk();
        int x = c.getX();
        int z = c.getZ();
        int radius = (AutoMain.config.chunkLoaderRange / 2);
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                Chunk chunk = c.getWorld().getChunkAt(x + (i * 16), z + (j * 16));
                if (!this.Chunks.contains(chunk)) {
                    this.Chunks.add(chunk);
                }
            }
        }
        return this.Chunks;
    }

    public boolean isChunkLoader() {
        if (this.Beacon == null) {
            return false;
        }
        this.isChunkLoader = new CheckBlocks(this.Beacon.getBlock()).isChunkLoader();
        return this.isChunkLoader;
    }

    public void enableChunkLoader() {
        if (!isChunkLoader) {
            return;
        }
        if (Beacon.getPrimaryEffect() != null && Beacon.getSecondaryEffect() != null) {
            return;
        }
        Furnace.setCookTime((short) (AutoMain.config.chunkLoaderBurnTime + 1));
        Furnace.setBurnTime((short) AutoMain.config.chunkLoaderBurnTime);

        List<Chunk> chunks = loadChunks();
        if (chunks == null) {
            return;
        }
        AutoMain.chunkManager.loadChunk(chunks);
    }

    public void disableChunkLoader() {
        if (!isChunkLoader) {
            return;
        }
        List<Chunk> chunks = loadChunks();
        if (chunks == null) {
            return;
        }
        AutoMain.chunkManager.unloadChunk(chunks);
    }


    @Override
    public String toString() {
        return Beacon.getWorld().getName() + ";" + Beacon.getX() + ";" + Beacon.getY() + ";" + Beacon.getZ();
    }

    public static ChunkLoader fromString(String s) {
        String[] split = s.split(";");
        if (split.length != 4) {
            return null;
        }
        World world = Bukkit.getWorld(split[0]);
        if (world == null) {
            return null;
        }
        Block block = world.getBlockAt(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
        ChunkLoader chunkLoader = new ChunkLoader(block);
        if (!chunkLoader.isChunkLoader) {
            return null;
        }
        return chunkLoader;
    }
}

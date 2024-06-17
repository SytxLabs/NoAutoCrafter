package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.ChunkLoader;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ChunkLoaderListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.AIR) {
            return;
        }
        if (block.getType() == Material.HOPPER) {
            block = block.getWorld().getBlockAt(block.getLocation().add(0, 1, 0));
        }
        if (block.getType() == Material.FURNACE) {
            block = block.getWorld().getBlockAt(block.getLocation().add(0, 2, 0));
        }
        if (block.getType() == Material.BEACON) {
            ChunkLoader cL = new ChunkLoader(block);
            if (cL.isChunkLoader() && AutoMain.chunkLoaderFile.containsChunk(cL)) {
                AutoMain.chunkLoaderFile.removeChunk(cL);
                cL.disableChunkLoader();
            }
        }
    }
}

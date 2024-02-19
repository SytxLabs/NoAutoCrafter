package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.Crafter;
import de.chaosschwein.autocrafter.types.CraftingRezept;
import de.chaosschwein.autocrafter.types.Placer;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.DataCache;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlacerListener implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            Dispenser dispenser = (Dispenser) e.getBlock().getState();
            if (new CheckBlocks(dispenser.getBlock()).isPlacer()) {
                Placer p = new Placer(dispenser.getLocation()).getPlacer();
                if (p.piston == null || !AutoMain.placer) {
                    return;
                }
                ItemStack item = e.getItem();
                if (item.getType() != Material.AIR) {
                    if (item.getType().isBlock()) {
                        if (p.placeLoc.getBlock().getType() != Material.AIR) {
                            return;
                        }
                        p.placeLoc.getBlock().setType(item.getType());
                        p.piston.getWorld().playSound(p.piston.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                    }
                }
            }
        }
    }
}

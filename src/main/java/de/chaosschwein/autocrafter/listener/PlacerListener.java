package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.Placer;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

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
                e.setCancelled(true);
                ItemStack item = e.getItem();
                if (item.getType() == Material.AIR || !item.getType().isBlock()) {
                    return;
                }
                if (p.placeLoc.getBlock().getType() != Material.AIR) {
                    return;
                }
                p.placeLoc.getBlock().setType(item.getType());
                p.piston.getWorld().playSound(p.piston.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> {
                    Utils.removeItem((Dispenser) e.getBlock().getState(), item.getType(), 1);
                    e.getBlock().getState().update();
                }, 2L);
            }
        }
    }
}

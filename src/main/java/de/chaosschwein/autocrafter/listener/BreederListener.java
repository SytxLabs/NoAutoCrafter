package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.Breeder;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class BreederListener implements Listener {
    @EventHandler
    public void onBreeder(BlockDispenseEvent e) {
        if (e.getBlock().getType() != Material.DISPENSER) return;
        if (!AutoMain.breeder) return;
        if (!new CheckBlocks(e.getBlock()).isBreeder()) return;
        Breeder b = new Breeder(e.getBlock().getLocation());
        if (!b.isBreeder) return;
        ItemStack item = e.getItem();
        e.setCancelled(true);
        if (item.getType() == Material.AIR) return;
        if (breedingMaterials.containsKey(item.getType())) {
            Block block = e.getBlock();
            EntityType entityType = breedingMaterials.get(item.getType());
            List<Entity> entities = block.getWorld().getNearbyEntities(b.boundingBox, entity -> {
                if (entity.getType() != entityType) return false;
                if (entity instanceof Animals animal) {
                    return animal.canBreed();
                }
                return false;
            }).stream().limit(2).toList();
            if (entities.size() < 2) return;
            entities.forEach(entity -> ((Animals)entity).setLoveModeTicks(600));
            b.blinkLamp();
            Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> Utils.removeItem(b.dispenser, item.getType(), 2), 5L);
        }
    }

    public static HashMap<Material, EntityType> breedingMaterials = new HashMap<>() {{
        put(Material.WHEAT, EntityType.COW);
        put(Material.CARROT, EntityType.PIG);
        put(Material.POTATO, EntityType.PIG);
        put(Material.BEETROOT, EntityType.PIG);
        put(Material.APPLE, EntityType.HORSE);
        put(Material.GOLDEN_APPLE, EntityType.HORSE);
        put(Material.ENCHANTED_GOLDEN_APPLE, EntityType.HORSE);
        put(Material.GOLDEN_CARROT, EntityType.HORSE);
    }};
}

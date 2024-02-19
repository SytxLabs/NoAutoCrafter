package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.EntityInteractor;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.HashMap;
import java.util.List;

public class EntityInteractiorListener implements Listener {
    @EventHandler
    public void onEntityInteractor(BlockDispenseEvent e) {
        if (e.getBlock().getType() != Material.DISPENSER) return;
        if (!AutoMain.entityInteractor) return;
        if (!new CheckBlocks(e.getBlock()).isEntityInteractor()) return;
        EntityInteractor ei = new EntityInteractor(e.getBlock().getLocation());
        if (!ei.isEntityInteractor) return;
        ItemStack item = e.getItem();
        e.setCancelled(true);
        if (item.getType() == Material.AIR) return;
        if (EntityInteractor.itemForEntity.containsKey(item.getType())) {
            Block block = ei.hopper.getLocation().add(0, 2, 0).getBlock();
            HashMap<EntityType, Material> entityType = EntityInteractor.itemForEntity.get(item.getType());
            BoundingBox boundingBox = new BoundingBox(block.getX() -1, block.getY() - 1, block.getZ() - 1, block.getX() + 1, block.getY() + 1, block.getZ() + 1);
            List<Entity> entities = block.getWorld().getNearbyEntities(boundingBox, entity -> entityType.containsKey(entity.getType())).stream().limit(1).toList();
            if (entities.isEmpty()) return;
            Entity entity = entities.get(0);
            Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> Utils.removeItem(((Dispenser) ei.dispenser.getBlock().getState()).getInventory(), item.getType(), 1), 5L);
            Utils.addItem(((Hopper)ei.hopper.getBlock().getState()).getInventory(), entityType.get(entity.getType()), 1);
        }
    }
}

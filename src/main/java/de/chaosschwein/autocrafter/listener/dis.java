package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.DataCach;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class dis implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                e.setCancelled(true);
                Crafter c = new Crafter(e.getBlock().getLocation()).getCrafter();
                if (c.craftingTable != null && c.hopper != null) {
                    if (!DataCach.isCrafter(c.dispenser.getLocation())) {
                        DataCach.addCrafter(c.dispenser.getLocation());
                    }
                    CrafingRezept rezept = c.getRezept();
                    if (rezept != null) {
                        HashMap<String, Integer> ingredients = rezept.getIngredients();
                        Inventory dispensorinventory = ((Dispenser) c.dispenser.getState()).getInventory();
                        for (int i = 0; i < dispensorinventory.getSize(); i++) {
                            if (ingredients.size() < 1) {
                                break;
                            }
                            ItemStack item = dispensorinventory.getItem(i);
                            if (item != null && item.getType() != Material.AIR) {
                                String itemName = item.getType().toString();
                                if (ingredients.containsKey(itemName)) {
                                    int amount = ingredients.get(itemName);
                                    if (amount <= item.getAmount()) {
                                        item.setAmount(item.getAmount() - amount);
                                        if (item.getAmount() < 1) {
                                            dispensorinventory.setItem(i, null);
                                        }
                                        ingredients.remove(itemName);
                                    }
                                }
                            }
                            c.dispenser.getState().update();
                            if (ingredients.size() < 1) {
                                Hopper hopper = (Hopper) c.hopper.getState();
                                Material m = Material.getMaterial(rezept.getResults());
                                if (m == null) {
                                    return;
                                }
                                hopper.getInventory().addItem(new ItemStack(m, rezept.getAmount()));
                            }
                        }
                    }
                }
            }
        }
    }
}
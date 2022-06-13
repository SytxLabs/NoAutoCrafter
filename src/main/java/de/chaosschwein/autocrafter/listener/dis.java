package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.DataCach;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class dis implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                e.setCancelled(true);

                Message msg = new Message();
                Crafter c = new Crafter(e.getBlock().getLocation()).getCrafter();
                if (c.craftingTable != null && c.hopper != null) {
                    if (!DataCach.isCrafter(c.dispenser.getLocation())) {
                        DataCach.addCrafter(c.dispenser.getLocation());
                    }
                    c = DataCach.getCrafter(e.getBlock().getLocation());

                    CrafingRezept rezept = c.getRezept();

                    if (rezept != null) {
                        HashMap<String, Integer> ingredients = rezept.getIngredients();
                        Inventory dispensorinventory = ((Dispenser) c.dispenser.getState()).getSnapshotInventory();
                        for (int i = 0; i < dispensorinventory.getSize(); i++) {
                            if (ingredients.size() < 1) {
                                break;
                            }
                            ItemStack item = dispensorinventory.getItem(i);
                            if (item != null && item.getType() != Material.AIR) {
                                String itemName = DataCach.PLANKS.containsKey(item.getType()) ? DataCach.PLANKS.get(item.getType()) : item.getType().toString();
                                int itemamount = item.getAmount() + 1;
                                if (ingredients.containsKey(itemName)) {
                                    int amount = ingredients.get(itemName);
                                    if (amount <= itemamount) {
                                        item.setAmount(item.getAmount() - amount);
                                        if (item.getAmount() < 1) {
                                            ((Dispenser) c.dispenser.getState()).getSnapshotInventory().setItem(i, null);
                                        } else {
                                            ((Dispenser) c.dispenser.getState()).getSnapshotInventory().setItem(i, item);
                                        }
                                        ingredients.remove(itemName);
                                    }
                                }
                            }
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                (e.getBlock().getState()).update();
                            }
                        }.runTaskLater(AutoMain.instand, 1);
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                Crafter c = new Crafter(e.getBlock().getLocation()).getAllData();
                DataCach.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send("§aDer Crafter wurde gelöscht.");
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.CRAFTING_TABLE) {
            if (e.getBlock().getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                return;
            }
            Block dispenser = null;
            if ((e.getBlock().getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(+1, 0, 0).getBlock();}
            if ((e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)){dispenser = e.getBlock().getLocation().add(-1, 0, 0).getBlock();}
            if ((e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)){dispenser = e.getBlock().getLocation().add(0, 0, -1).getBlock();}
            if ((e.getBlock().getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(0, 0, +1).getBlock();}
            if ((e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(0, +1, 0).getBlock();}
            if (dispenser == null) {
                return;
            }
            if (new CheckBlocks(dispenser).isCrafter()) {
                Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                DataCach.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send("§aDer Crafter wurde gelöscht.");
                }
            }
            return;
        }
        if(e.getBlock().getType() == Material.HOPPER){
            if (e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                return;
            }
            Block dispenser = null;
            if ((e.getBlock().getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(+1, +1, 0).getBlock();}
            if ((e.getBlock().getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)){dispenser = e.getBlock().getLocation().add(-1, +1, 0).getBlock();}
            if ((e.getBlock().getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)){dispenser = e.getBlock().getLocation().add(0, +1, -1).getBlock();}
            if ((e.getBlock().getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(0, +1, +1).getBlock();}
            if ((e.getBlock().getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = e.getBlock().getLocation().add(0, +2, 0).getBlock();}
            if (dispenser == null) {
                return;
            }
            if (new CheckBlocks(dispenser).isCrafter()) {
                Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                DataCach.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send("§aDer Crafter wurde gelöscht.");
                }
            }
            return;
        }
    }
}
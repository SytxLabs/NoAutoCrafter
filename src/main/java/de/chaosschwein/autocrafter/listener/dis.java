package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.DataCach;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class dis implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if(!AutoMain.crafter) {
            return;
        }
        if (e.getBlock().getType() == Material.DISPENSER) {
            Dispenser dispenser = (Dispenser) e.getBlock().getState();
            if (new CheckBlocks(dispenser.getBlock()).isCrafter()) {
                e.setCancelled(true);

                Message msg = new Message();
                Crafter c = new Crafter(dispenser.getLocation()).getCrafter();
                if (c.craftingTable != null && c.hopper != null) {
                    if (!DataCach.isCrafter(c.dispenser.getLocation())) {
                        DataCach.addCrafter(c.dispenser.getLocation());
                    }
                    c = DataCach.getCrafter(dispenser.getLocation());

                    CrafingRezept rezept = c.getRezept();

                    if (rezept != null) {
                        Inventory di = dispenser.getInventory();
                        HashMap<String, Integer> ingredients = new HashMap<>();
                        for (String type : rezept.getIngredients().keySet()) {
                            ingredients.put(type, rezept.getIngredients().get(type));
                        }
                        HashMap<Integer, Integer> itemsToRemove = new HashMap<>();
                        for (int i = 0; i < di.getSize(); i++) {
                            if (ingredients.size() < 1) {
                                break;
                            }
                            ItemStack item = di.getItem(i);
                            if (item != null && item.getType() != Material.AIR) {
                                String itemName = DataCach.PLANKS.containsKey(item.getType()) ? DataCach.PLANKS.get(item.getType()) : item.getType().name();

                                if (ingredients.containsKey(itemName)) {
                                    int amount = ingredients.get(itemName);
                                    if ((amount-1) <= item.getAmount()) {
                                        itemsToRemove.put(i, amount);
                                        ingredients.remove(itemName);
                                    }
                                }
                            }
                        }
                        if (ingredients.size() < 1) {
                            for (int slot: itemsToRemove.keySet()) {
                                ItemStack item = dispenser.getInventory().getItem(slot);
                                if(item == null ) {
                                    continue;
                                }
                                int newAmount = item.getAmount() - itemsToRemove.get(slot);
                                if(newAmount <= 0) {
                                    ((Dispenser) e.getBlock().getState()).getInventory().remove(item);
                                } else {
                                    item.setAmount(newAmount);
                                    ((Dispenser) e.getBlock().getState()).getInventory().setItem(slot, item);
                                }
                                e.getBlock().getState().update();
                            }
                            Hopper hopper = (Hopper) c.hopper.getState();
                            Material m = Material.getMaterial(rezept.getResults());
                            if (m == null) {
                                return;
                            }
                            hopper.getInventory().addItem(new ItemStack(m, rezept.getAmount()));
                        }else{
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
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
        }
    }

    @EventHandler
    public void onBlockExpload(BlockExplodeEvent e){
        if(e.blockList().size()>0) {
            for (Block b : e.blockList()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        Crafter c = new Crafter(b.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.blockList().remove(b);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        if (new CrafterFile().contains(c)) {
                            e.blockList().remove(b);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.HOPPER) {
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +2, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.blockList().remove(b);
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    public void onPushEvent(BlockPistonExtendEvent e){
        if(e.getBlocks().size()>0) {
            for (Block b : e.getBlocks()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        Crafter c = new Crafter(b.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.HOPPER) {
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +2, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    public void onRetriveEvent(BlockPistonRetractEvent e){
        if(e.getBlocks().size()>0) {
            for (Block b : e.getBlocks()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        Crafter c = new Crafter(b.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, 0, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, 0, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                    return;
                }
                if (b.getType() == Material.HOPPER) {
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(+1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(-1, +1, 0).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, -1).getBlock();
                    }
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +1, +1).getBlock();
                    }
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {
                        dispenser = b.getLocation().add(0, +2, 0).getBlock();
                    }
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getCrafter();
                        if (new CrafterFile().contains(c)) {
                            e.setCancelled(true);
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    public void onFire(BlockBurnEvent e){
        if(e.getBlock().getType() == Material.DISPENSER){
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                Crafter c = new Crafter(e.getBlock().getLocation()).getCrafter();
                if(new CrafterFile().contains(c)) {
                    e.setCancelled(true);
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
                if(new CrafterFile().contains(c)) {
                    e.setCancelled(true);
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
                Crafter c = new Crafter(dispenser.getLocation()).getCrafter();
                if(new CrafterFile().contains(c)) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
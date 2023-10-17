package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.enums.*;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.*;
import de.chaosschwein.autocrafter.utils.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.*;

import java.util.HashMap;
import java.util.Objects;

public class CrafterListener implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            Dispenser dispenser = (Dispenser) e.getBlock().getState();
            if (new CheckBlocks(dispenser.getBlock()).isCrafter()) {
                if(!AutoMain.crafter) {
                    return;
                }
                Crafter c = DataCache.getCrafter(dispenser.getLocation());
                if (c.craftingTable != null && c.hopper != null && c.isRegistered()) {
                    e.setCancelled(true);
                    CraftingRezept rezept = c.getRezept();
                    if (rezept != null) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(AutoMain.instance, () -> {
                            ItemStack[] di = Utils.getBlockInventory(dispenser.getBlock()).getContents();
                            HashMap<Material, Integer> ingredients = new HashMap<>();
                            for (ItemStack i: rezept.getIngredients()) {
                                if (i == null || i.getType() == Material.AIR) {
                                    continue;
                                }
                                if (ingredients.containsKey(i.getType())) {
                                    ingredients.put(i.getType(), ingredients.get(i.getType()) + i.getAmount());
                                    continue;
                                }
                                ingredients.put(i.getType(), i.getAmount());
                            }
                            HashMap<Material, Integer> itemsToRemove = new HashMap<>();

                            for (ItemStack itemStack : di) {
                                if (ingredients.isEmpty()) {
                                    break;
                                }
                                if (itemStack == null || itemStack.getType() == Material.AIR) {
                                    continue;
                                }
                                if (ingredients.containsKey(itemStack.getType())) {
                                    int amount = ingredients.get(itemStack.getType());
                                    if (amount <= itemStack.getAmount()) {
                                        ingredients.remove(itemStack.getType());
                                        if (itemsToRemove.containsKey(itemStack.getType())) {
                                            itemsToRemove.put(itemStack.getType(), itemsToRemove.get(itemStack.getType()) + amount);
                                        } else {
                                            itemsToRemove.put(itemStack.getType(), amount);
                                        }
                                    } else {
                                        ingredients.put(itemStack.getType(), amount - itemStack.getAmount());
                                        if (itemsToRemove.containsKey(itemStack.getType())) {
                                            itemsToRemove.put(itemStack.getType(), itemsToRemove.get(itemStack.getType()) + itemStack.getAmount());
                                        } else {
                                            itemsToRemove.put(itemStack.getType(), itemStack.getAmount());
                                        }
                                    }
                                }
                            }

                            if (ingredients.isEmpty()) {
                                dispenser.update();
                                for (Material m: itemsToRemove.keySet()) {
                                    Utils.removeItem(dispenser, m, itemsToRemove.get(m) - 1);
                                }
                                dispenser.update();

                                HashMap<Material, Integer> results = rezept.getResults();
                                for (Material m : results.keySet()) {
                                    Utils.addItem(Utils.getBlockInventory(c.hopper), m, results.get(m));
                                }
                                c.hopper.getState().update();
                            }
                        }, 5);
                    }
                }
            }

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

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isPlacer()) {
                Placer p = new Placer(e.getBlock().getLocation()).getPlacer();
                if (p.piston == null || !AutoMain.placer) {
                    return;
                }
                e.getItems().clear();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                Crafter c = new Crafter(e.getBlock().getLocation()).getAllData();
                DataCache.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send(AutoMain.language.CrafterDeleted);
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
                DataCache.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send(AutoMain.language.CrafterDeleted);
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
                DataCache.removeCrafter(c.dispenser.getLocation());
                if(new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send(AutoMain.language.CrafterDeleted);
                }
            }
        }
    }

    @EventHandler
    public void onBlockReceiver(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
            if (new CheckBlocks(e.getBlock()).isReceiver()) {
                if(new Transporter().containsReceiver(e.getBlock().getLocation())) {
                    new Transporter().removeReceivers(e.getBlock().getLocation());
                    msg.send(AutoMain.language.ReceiverDeleted);
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.END_ROD) {
            if (e.getBlock().getLocation().add(0, -1, 0).getBlock().getType() != Material.TRAPPED_CHEST) {
                return;
            }
            Block dispenser = e.getBlock().getLocation().add(0, -1, 0).getBlock();
            if (new CheckBlocks(dispenser).isReceiver()) {
                if(new Transporter().containsReceiver(dispenser.getLocation())) {
                    new Transporter().removeReceivers(dispenser.getLocation());
                    msg.send(AutoMain.language.ReceiverDeleted);
                }
            }
            return;
        }
        if(e.getBlock().getType() == Material.HOPPER){
            if (e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() != Material.TRAPPED_CHEST) {
                return;
            }
            Block dispenser = e.getBlock().getLocation().add(0, +1, 0).getBlock();
            if (new CheckBlocks(dispenser).isReceiver()) {
                if(new Transporter().containsReceiver(dispenser.getLocation())) {
                    new Transporter().removeReceivers(dispenser.getLocation());
                    msg.send(AutoMain.language.ReceiverDeleted);
                }
            }
        }
    }

    @EventHandler
    public void onBlockSender(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
            if (new CheckBlocks(e.getBlock()).isSender()) {
                if(new Transporter().containsSender(e.getBlock().getLocation())) {
                    new Transporter().removeSender(e.getBlock().getLocation());
                    msg.send(AutoMain.language.SenderDeleted);
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.END_ROD) {
            if (e.getBlock().getLocation().add(0, -1, 0).getBlock().getType() != Material.TRAPPED_CHEST) {
                return;
            }
            Block dispenser = e.getBlock().getLocation().add(0, -1, 0).getBlock();
            if (new CheckBlocks(dispenser).isSender()) {
                if(new Transporter().containsSender(dispenser.getLocation())) {
                    new Transporter().removeSender(dispenser.getLocation());
                    msg.send(AutoMain.language.SenderDeleted);
                }
            }
            return;
        }
        if(e.getBlock().getType() == Material.HOPPER){
            Block dispenser;
            if (e.getBlock().getLocation().add(0, 0, +1).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(0, 0, +1).getBlock();
            }
            else if (e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(0, 0, -1).getBlock();
            }
            else if (e.getBlock().getLocation().add(+1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(+1, 0, 0).getBlock();
            }
            else if (e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(-1, 0, 0).getBlock();
            }
            else {
                return;
            }
            if (new CheckBlocks(dispenser).isSender()) {
                if(new Transporter().containsSender(dispenser.getLocation())) {
                    new Transporter().removeSender(dispenser.getLocation());
                    msg.send(AutoMain.language.SenderDeleted);
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e){
        if(!e.blockList().isEmpty()) {
            for (Block b : e.blockList()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        Crafter c = new Crafter(b.getLocation()).getAllData();
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if(new CrafterFile().contains(c)) {
                            new CrafterFile().delete(c);
                            Player p = Bukkit.getPlayer(c.getOwnerUUID());
                            if (p != null && p.isOnline()) {
                                new Message(p).send(AutoMain.language.CrafterDeleted);
                            }
                        }
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, 0, 0).getBlock();}
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, 0, 0).getBlock();}
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, 0, -1).getBlock();}
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, 0, +1).getBlock();}
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if(new CrafterFile().contains(c)) {
                            new CrafterFile().delete(c);
                            Player p = Bukkit.getPlayer(c.getOwnerUUID());
                            if (p != null && p.isOnline()) {
                                new Message(p).send(AutoMain.language.CrafterDeleted);
                            }
                        }
                    }
                    return;
                }
                if(b.getType() == Material.HOPPER){
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, +1, 0).getBlock();}
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, +1, 0).getBlock();}
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, +1, -1).getBlock();}
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, +1).getBlock();}
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +2, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if(new CrafterFile().contains(c)) {
                            new CrafterFile().delete(c);
                            Player p = Bukkit.getPlayer(c.getOwnerUUID());
                            if (p != null && p.isOnline()) {
                                new Message(p).send(AutoMain.language.CrafterDeleted);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPushEvent(BlockPistonExtendEvent e){
        if(!e.getBlocks().isEmpty()) {
            for (Block b : e.getBlocks()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        e.setCancelled(true);
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, 0, 0).getBlock();}
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, 0, 0).getBlock();}
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, 0, -1).getBlock();}
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, 0, +1).getBlock();}
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        e.setCancelled(true);
                    }
                    return;
                }
                if(b.getType() == Material.HOPPER){
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, +1, 0).getBlock();}
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, +1, 0).getBlock();}
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, +1, -1).getBlock();}
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, +1).getBlock();}
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +2, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRetrieveEvent(BlockPistonRetractEvent e){
        if(!e.getBlocks().isEmpty()) {
            for (Block b : e.getBlocks()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        e.setCancelled(true);
                    }
                    return;
                }
                if (b.getType() == Material.CRAFTING_TABLE) {
                    if (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.HOPPER) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, 0, 0).getBlock();}
                    if ((b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, 0, 0).getBlock();}
                    if ((b.getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, 0, -1).getBlock();}
                    if ((b.getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, 0, +1).getBlock();}
                    if ((b.getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        e.setCancelled(true);
                    }
                    return;
                }
                if(b.getType() == Material.HOPPER){
                    if (b.getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                        return;
                    }
                    Block dispenser = null;
                    if ((b.getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(+1, +1, 0).getBlock();}
                    if ((b.getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(-1, +1, 0).getBlock();}
                    if ((b.getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)){dispenser = b.getLocation().add(0, +1, -1).getBlock();}
                    if ((b.getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +1, +1).getBlock();}
                    if ((b.getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {dispenser = b.getLocation().add(0, +2, 0).getBlock();}
                    if (dispenser == null) {
                        return;
                    }
                    if (new CheckBlocks(dispenser).isCrafter()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void breakerEvent(BlockPistonExtendEvent e) {
        if (e.getBlock().getType() == Material.STICKY_PISTON) {
            Block piston = e.getBlock();
            if (new CheckBlocks(piston).isBreaker()) {
                Breaker b = new Breaker(piston.getLocation()).getBreaker();
                if (b.endRod == null || b.endRod.getType() != Material.END_ROD || !AutoMain.placer) {
                    return;
                }
                Block breakBlock = b.breakLoc.getBlock();
                if (breakBlock.getType() == Material.AIR || breakBlock.getType().getHardness() < 0 || breakBlock.getType().getHardness() > 10 || breakBlock.getType() == Material.SPAWNER) {
                    return;
                }
                if (new CheckBlocks(breakBlock).isCrafter() || new CheckBlocks(breakBlock).isPlacer() || new CheckBlocks(breakBlock).isBreaker()) {
                    e.setCancelled(true);
                    return;
                }
                breakBlock.breakNaturally();
                breakBlock.setType(Material.AIR);

                Location locAfterMove = b.breakLoc.clone().add(e.getDirection().getModX(), e.getDirection().getModY(), e.getDirection().getModZ());

                Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> locAfterMove.getBlock().setType(Material.AIR), 2);
            }
        }
    }

    @EventHandler
    public void senderEvent(InventoryMoveItemEvent e) {
        Inventory inv = e.getDestination();
        Location loc = inv.getLocation();
        if (loc == null) {
            return;
        }
        if (loc.getBlock().getType() == Material.TRAPPED_CHEST) {
            Transporter transporter = new Transporter();
            Block chest = loc.getBlock();
            if (chest.getType() != Material.TRAPPED_CHEST || !AutoMain.transport || !new CheckBlocks(chest).isSender() || !transporter.containsSender(loc)) {
                return;
            }
            Location receiverLoc = transporter.getReceiver(transporter.getSender(loc), transporter.getSenderOwner(loc));
            if (receiverLoc == null || receiverLoc.getBlock().getType() != Material.TRAPPED_CHEST || !new CheckBlocks(receiverLoc.getBlock()).isReceiver()) {
                return;
            }
            Chest receiver = (Chest) receiverLoc.getBlock().getState();
            ItemStack item = e.getItem();
            if (Utils.hasNotEnoughPlace(receiver.getInventory(), item.getType(), item.getAmount())) {
                return;
            }

            ((Chest)receiverLoc.getBlock().getState()).getBlockInventory().addItem(item);
            receiverLoc.getBlock().getState().update();
            ((Chest)receiverLoc.getBlock().getState()).getBlockInventory().getViewers().forEach(player -> {
                if (player instanceof Player) {
                    try {
                        //noinspection UnstableApiUsage
                        ((Player)player).updateInventory();
                    } catch (Exception ignored) {}
                }
            });

            Utils.removeItem(e.getSource(), item.getType(), item.getAmount());

            e.setItem(new ItemStack(Material.AIR));
        }
    }


    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Location loc = inv.getLocation();
        if (loc == null) {
            return;
        }
        if (loc.getBlock().getType() == Material.TRAPPED_CHEST) {
            Transporter transporter = new Transporter();
            Block chest = loc.getBlock();
            if (chest.getType() != Material.TRAPPED_CHEST || !AutoMain.transport || !new CheckBlocks(chest).isSender() || !transporter.containsSender(loc)) {
                return;
            }
            Location receiverLoc = transporter.getReceiver(transporter.getSender(loc), transporter.getSenderOwner(loc));
            if (receiverLoc == null || receiverLoc.getBlock().getType() != Material.TRAPPED_CHEST || !new CheckBlocks(receiverLoc.getBlock()).isReceiver()) {
                return;
            }
            Chest receiver = (Chest) receiverLoc.getBlock().getState();
            ItemStack item = event.getCurrentItem();
            if (item == null) {
                return;
            }
            if (Utils.hasNotEnoughPlace(receiver.getInventory(), item.getType(), item.getAmount())) {
                return;
            }

            ((Chest)receiverLoc.getBlock().getState()).getBlockInventory().addItem(item);
            receiverLoc.getBlock().getState().update();
            ((Chest)receiverLoc.getBlock().getState()).getBlockInventory().getViewers().forEach(player -> {
                if (player instanceof Player) {
                    try {
                        //noinspection UnstableApiUsage
                        ((Player)player).updateInventory();
                    } catch (Exception ignored) {}
                }
            });

            Utils.removeItem(Objects.requireNonNull(event.getClickedInventory()), item.getType(), item.getAmount());
        }
    }
}
package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.types.Breaker;
import de.chaosschwein.autocrafter.types.Crafter;
import de.chaosschwein.autocrafter.types.CraftingRezept;
import de.chaosschwein.autocrafter.types.Placer;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.DataCache;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class CrafterListener implements Listener {
    private final List<Block> waitingForFinish = new ArrayList<>();
    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() != Material.DISPENSER) return;
        if (!AutoMain.crafter) return;

        Dispenser dispenser = (Dispenser) e.getBlock().getState();
        if (!new CheckBlocks(dispenser.getBlock()).isCrafter()) return;
        Crafter c = DataCache.getCrafter(dispenser.getLocation());
        if (c.craftingTable == null || c.hopper == null || !c.isRegistered()) return;
        e.setCancelled(true);
        if (waitingForFinish.contains(e.getBlock())) return;
        CraftingRezept craftingRezept = c.getRezept();
        if (craftingRezept == null) return;
        ItemStack[] di = Utils.getBlockInventory(dispenser.getBlock()).getContents();
        HashMap<Material, Integer> ingredients = new HashMap<>();
        for (ItemStack i : craftingRezept.getIngredients()) {
            if (i == null || i.getType() == Material.AIR) continue;
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
        if (!ingredients.isEmpty()) return;
        waitingForFinish.add(e.getBlock());
        Bukkit.getScheduler().scheduleSyncDelayedTask(AutoMain.instance, () -> {
            for (Material m : itemsToRemove.keySet()) {
                Utils.removeItem((Dispenser) e.getBlock().getState(), m, itemsToRemove.get(m) - 1);
            }
            e.getBlock().getState().update();
            HashMap<Material, Integer> results = craftingRezept.getResults();
            for (Material m : results.keySet()) {
                Utils.addItem(Utils.getBlockInventory(c.hopper), m, results.get(m));
            }
            c.hopper.getState().update();
            waitingForFinish.remove(e.getBlock());
        }, 5);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.DISPENSER) {
            if (new CheckBlocks(e.getBlock()).isCrafter()) {
                Crafter c = new Crafter(e.getBlock().getLocation()).getAllData();
                DataCache.removeCrafter(c.dispenser.getLocation());
                if (new CrafterFile().contains(c)) {
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
            if ((e.getBlock().getLocation().add(+1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(+1, 0, 0).getBlock();
            }
            if ((e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(-1, 0, 0).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, 0, -1).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, 0, +1).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, 0, +1).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, +1, 0).getBlock();
            }
            if (dispenser == null) {
                return;
            }
            if (new CheckBlocks(dispenser).isCrafter()) {
                Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                DataCache.removeCrafter(c.dispenser.getLocation());
                if (new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send(AutoMain.language.CrafterDeleted);
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.HOPPER) {
            if (e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() != Material.CRAFTING_TABLE) {
                return;
            }
            Block dispenser = null;
            if ((e.getBlock().getLocation().add(+1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(+1, +1, 0).getBlock();
            }
            if ((e.getBlock().getLocation().add(-1, +1, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(-1, +1, 0).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, +1, -1).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, +1, -1).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, +1, +1).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, +1, +1).getBlock();
            }
            if ((e.getBlock().getLocation().add(0, +2, 0).getBlock().getType() == Material.DISPENSER)) {
                dispenser = e.getBlock().getLocation().add(0, +2, 0).getBlock();
            }
            if (dispenser == null) {
                return;
            }
            if (new CheckBlocks(dispenser).isCrafter()) {
                Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                DataCache.removeCrafter(c.dispenser.getLocation());
                if (new CrafterFile().contains(c)) {
                    new CrafterFile().delete(c);
                    msg.send(AutoMain.language.CrafterDeleted);
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        if (!e.blockList().isEmpty()) {
            for (Block b : e.blockList()) {
                if (b.getType() == Material.DISPENSER) {
                    if (new CheckBlocks(b).isCrafter()) {
                        Crafter c = new Crafter(b.getLocation()).getAllData();
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if (new CrafterFile().contains(c)) {
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
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if (new CrafterFile().contains(c)) {
                            new CrafterFile().delete(c);
                            Player p = Bukkit.getPlayer(c.getOwnerUUID());
                            if (p != null && p.isOnline()) {
                                new Message(p).send(AutoMain.language.CrafterDeleted);
                            }
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
                        Crafter c = new Crafter(dispenser.getLocation()).getAllData();
                        DataCache.removeCrafter(c.dispenser.getLocation());
                        if (new CrafterFile().contains(c)) {
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
    public void onPushEvent(BlockPistonExtendEvent e) {
        if (!e.getBlocks().isEmpty()) {
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
                        e.setCancelled(true);
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
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRetrieveEvent(BlockPistonRetractEvent e) {
        if (!e.getBlocks().isEmpty()) {
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
                        e.setCancelled(true);
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
                if (b.endRod == null || b.endRod.getType() != Material.END_ROD || !AutoMain.breaker) {
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
}
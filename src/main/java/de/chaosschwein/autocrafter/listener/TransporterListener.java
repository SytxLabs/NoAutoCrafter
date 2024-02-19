package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.Transporter;
import de.chaosschwein.autocrafter.types.Sender;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TransporterListener implements Listener {
    @EventHandler
    public void onBlockReceiverBreak(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
            if (new CheckBlocks(e.getBlock()).isReceiver()) {
                if (AutoMain.transporter.isReceiver(e.getBlock().getLocation())) {
                    AutoMain.transporter.removeReceiver(e.getBlock().getLocation());
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
                if (AutoMain.transporter.isReceiver(e.getBlock().getLocation())) {
                    AutoMain.transporter.removeReceiver(e.getBlock().getLocation());
                    msg.send(AutoMain.language.ReceiverDeleted);
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.HOPPER) {
            if (e.getBlock().getLocation().add(0, +1, 0).getBlock().getType() != Material.TRAPPED_CHEST) {
                return;
            }
            Block dispenser = e.getBlock().getLocation().add(0, +1, 0).getBlock();
            if (new CheckBlocks(dispenser).isReceiver()) {
                if (AutoMain.transporter.isReceiver(e.getBlock().getLocation())) {
                    AutoMain.transporter.removeReceiver(e.getBlock().getLocation());
                    msg.send(AutoMain.language.ReceiverDeleted);
                }
            }
        }
    }

    @EventHandler
    public void onBlockSenderBreak(BlockBreakEvent e) {
        Message msg = new Message(e.getPlayer());
        if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
            if (new CheckBlocks(e.getBlock()).isSender()) {
                if (AutoMain.transporter.isSender(e.getBlock().getLocation())) {
                    AutoMain.transporter.removeSender(e.getBlock().getLocation());
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
                if (AutoMain.transporter.isSender(dispenser.getLocation())) {
                    AutoMain.transporter.removeSender(dispenser.getLocation());
                    msg.send(AutoMain.language.SenderDeleted);
                }
            }
            return;
        }
        if (e.getBlock().getType() == Material.HOPPER) {
            Block dispenser;
            if (e.getBlock().getLocation().add(0, 0, +1).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(0, 0, +1).getBlock();
            } else if (e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(0, 0, -1).getBlock();
            } else if (e.getBlock().getLocation().add(+1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(+1, 0, 0).getBlock();
            } else if (e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
                dispenser = e.getBlock().getLocation().add(-1, 0, 0).getBlock();
            } else {
                return;
            }
            if (new CheckBlocks(dispenser).isSender()) {
                if (AutoMain.transporter.isSender(dispenser.getLocation())) {
                    AutoMain.transporter.removeSender(dispenser.getLocation());
                    msg.send(AutoMain.language.SenderDeleted);
                }
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
            Transporter transporter = AutoMain.transporter;
            Block chest = loc.getBlock();
            if (chest.getType() != Material.TRAPPED_CHEST || !AutoMain.transport || !new CheckBlocks(chest).isSender() || !transporter.isSender(loc)) {
                return;
            }
            ItemStack item = e.getItem();
            Sender sender = transporter.senders.get(loc);

            if (sender.hasNotValidReceivers(item)) {
                return;
            }
            if (!sender.moveItems(item)) {
                return;
            }
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> {
                Utils.removeItem(e.getSource(), item.getType(), item.getAmount());

                if (e.getDestination().getViewers().isEmpty()) {
                    e.getDestination().getViewers().forEach(player -> {
                        if (player instanceof Player) {
                            try {
                                //noinspection UnstableApiUsage
                                ((Player) player).updateInventory();
                            } catch (Exception ignored) {
                            }
                        }
                    });
                }
            }, 2L);
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
            Transporter transporter = AutoMain.transporter;
            Block chest = loc.getBlock();
            if (chest.getType() != Material.TRAPPED_CHEST || !AutoMain.transport || !new CheckBlocks(chest).isSender() || !transporter.isSender(loc)) {
                return;
            }
            ItemStack item = event.getCurrentItem();
            if (item == null || item.getType() == Material.AIR) {
                return;
            }
            Sender sender = transporter.senders.get(loc);

            if (sender.hasNotValidReceivers(item)) {
                return;
            }
            if (!sender.moveItems(item)) {
                return;
            }
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(AutoMain.instance, () -> {
                Utils.removeItem(event.getClickedInventory(), item.getType(), item.getAmount());
            }, 2L);
        }
    }


}

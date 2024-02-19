package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.types.Channel;
import de.chaosschwein.autocrafter.types.CraftingRezept;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.InventoryCreator;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryAutoCraftClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) {
            return;
        }
        String title = e.getView().getTitle();
        ItemStack item = e.getCurrentItem();
        Message msg = new Message(p);
        if (!("§d§lAutoCrafter".equals(title) && item != null && item.getType() != Material.AIR && e.getClickedInventory() == p.getOpenInventory().getTopInventory() && e.getSlot() == 0)) {
            return;
        }
        ItemStack[] items = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            ItemStack iStack = e.getInventory().getItem(i + 1);
            if (iStack == null || iStack.getType() == Material.AIR) {
                items[i] = null;
                continue;
            }
            items[i] = new ItemStack(iStack.getType(), iStack.getAmount());
        }
        CraftingRezept rezept = new CraftingRezept(items, new HashMap<>() {{
            put(item.getType(), item.getAmount());
        }});
        CrafterFile crafterFile = new CrafterFile();
        crafterFile.save(p, AutoCommand.crafter.get(p), rezept);

        msg.send(AutoMain.language.CrafterCreated);
        p.closeInventory();
    }

    @EventHandler
    public void onInventoryChannelViewerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            String title = e.getView().getTitle();
            Inventory inv = e.getClickedInventory();
            ItemStack item = e.getCurrentItem();
            if (item == null || item.getType() == Material.AIR || title.isEmpty() || !item.hasItemMeta() || item.getItemMeta() == null) {
                return;
            }
            if (inv == null) {
                return;
            }
            String itemName = item.getItemMeta().getDisplayName();
            if (!"§d§lChannelViewer".equals(title)) {
                return;
            }
            e.setCancelled(true);
            if (e.getClickedInventory() != p.getOpenInventory().getTopInventory()) {
                return;
            }
            InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
            if (item.getType() == Material.GRAY_STAINED_GLASS_PANE && itemName.equals("§e")) {
                return;
            }
            if (itemName.equals(AutoMain.language.InventoryBack)) {
                inventoryCreator.openChannelViewer(inventoryCreator.channelViewerPage - 1);
                return;
            }
            if (itemName.equals(AutoMain.language.InventoryNext)) {
                inventoryCreator.openChannelViewer(inventoryCreator.channelViewerPage + 1);
                return;
            }
            if (itemName.equals(AutoMain.language.InventoryAddChannel)) {
                inventoryCreator.openAddChannel();
                return;
            }
            if (itemName.equals(AutoMain.language.InventoryCreateChannel)) {
                inventoryCreator.openCreateChannel();
                return;
            }
            List<Channel> channels = AutoMain.transporter.channels.values().stream().filter(channel -> channel.isOwner(p.getUniqueId().toString()) || channel.getUsers().contains(p.getUniqueId().toString())).toList();
            for (Channel channel : channels) {
                if (ChatColor.stripColor(itemName).equals(channel.name) && item.getType() == channel.material()) {
                    inventoryCreator.openChannel(channel);
                    return;
                }
            }
        }
    }
}

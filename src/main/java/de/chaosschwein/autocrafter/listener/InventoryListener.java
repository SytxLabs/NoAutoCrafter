package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.cmd.ReceiverCommand;
import de.chaosschwein.autocrafter.cmd.SenderCommand;
import de.chaosschwein.autocrafter.enums.CraftingRezept;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.manager.file.Transporter;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@SuppressWarnings("UnstableApiUsage")
public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            String title = e.getView().getTitle();
            ItemStack item = e.getCurrentItem();
            Message msg = new Message(p);
            if ("§d§lAutoCrafter".equals(title) && item != null && item.getType() != Material.AIR && e.getClickedInventory() == p.getOpenInventory().getTopInventory() && e.getSlot() == 0) {
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
                return;
            }
            if (item == null || item.getType() == Material.AIR || title.isEmpty() || !item.hasItemMeta() || item.getItemMeta() == null) {
                return;
            }
            String itemName = item.getItemMeta().getDisplayName();
            if ("§d§lReceiver".equals(title) || "§d§lSender".equals(title)) {
                if (itemName.equalsIgnoreCase("§e")) {
                    e.setCancelled(true);
                }
                if (itemName.equals(AutoMain.language.InventoryBack)) {
                    e.setCancelled(true);
                    p.closeInventory();
                }
                if (itemName.equalsIgnoreCase(AutoMain.language.InventoryAddItem)) {
                    e.setCancelled(true);
                    msg.send(AutoMain.language.CrafterInsertItem);
                }
                if (itemName.equals(AutoMain.language.InventoryCreateReceiver)) {
                    e.setCancelled(true);
                    Material mat;
                    ItemStack item1 = e.getInventory().getItem(4);
                    ItemStack item2 = e.getInventory().getItem(22);
                    if ((item1 != null && item1.getType() != Material.AIR)) {
                        mat = item1.getType();
                        p.getInventory().addItem(item1);
                        p.updateInventory();
                    } else if (item2 != null && item2.getType() != Material.AIR) {
                        mat = item2.getType();
                        p.getInventory().addItem(item2);
                        p.updateInventory();
                    } else {
                        p.closeInventory();
                        msg.send(AutoMain.language.CrafterInsertItemError);
                        return;
                    }
                    if (new Transporter().containsReceiver(mat, p.getUniqueId().toString())) {
                        p.closeInventory();
                        msg.send(AutoMain.language.ReceiverMaterialAlreadyExists);
                        return;
                    }
                    if (!ReceiverCommand.receiver.containsKey(p)) {
                        p.closeInventory();
                        return;
                    }
                    if (new Transporter().addReceiver(p, ReceiverCommand.receiver.get(p), mat)) {
                        p.closeInventory();
                        msg.send(AutoMain.language.ReceiverCreated);
                    } else {
                        p.closeInventory();
                        msg.send(AutoMain.language.error);
                    }
                }
                if (itemName.equals(AutoMain.language.InventoryCreateSender)) {
                    e.setCancelled(true);
                    Material mat;
                    ItemStack item1 = e.getInventory().getItem(4);
                    ItemStack item2 = e.getInventory().getItem(22);
                    if ((item1 != null && item1.getType() != Material.AIR)) {
                        mat = item1.getType();
                        p.getInventory().addItem(item1);
                        p.updateInventory();
                    } else if (item2 != null && item2.getType() != Material.AIR) {
                        mat = item2.getType();
                        p.getInventory().addItem(item2);
                        p.updateInventory();
                    } else {
                        p.closeInventory();
                        msg.send(AutoMain.language.CrafterInsertItemError);
                        return;
                    }
                    if (!SenderCommand.sender.containsKey(p)) {
                        p.closeInventory();
                        return;
                    }
                    if (new Transporter().addSender(p, SenderCommand.sender.get(p), mat)) {
                        p.closeInventory();
                        msg.send(AutoMain.language.SenderCreated);
                    } else {
                        p.closeInventory();
                        msg.send(AutoMain.language.error);
                    }
                }
            }
        }
    }

}

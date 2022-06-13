package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            String title = e.getView().getTitle();
            ItemStack item = e.getCurrentItem();
            if(item == null || item.getType() == Material.AIR || title.equals("") || !item.hasItemMeta() ||item.getItemMeta() == null) {return;}
            String itemname = item.getItemMeta().getDisplayName();
            Message msg = new Message(p);
            switch (title){
                case "§d§lAutoCrafter":{
                    if(itemname.equalsIgnoreCase("§e")){
                        e.setCancelled(true);
                    }
                    if(itemname.equals("§cZurück")) {
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                    if(itemname.equalsIgnoreCase("§dItem Reinlegen")){
                        e.setCancelled(true);
                        msg.send("§7Bitte legen Sie das Item, in den Slot oben in der mitte!");
                    }
                    if(itemname.equals("§aAutoCrafter Erstellen")) {
                        e.setCancelled(true);
                        CrafingRezept cr = null;
                        ItemStack item1 = e.getInventory().getItem(4);
                        ItemStack item2 = e.getInventory().getItem(22);
                        if((item1 != null && item1.getType() != Material.AIR)){
                            cr = CrafingRezept.getRezept(item1.getType());
                            p.getInventory().addItem(item1);
                            p.updateInventory();
                        }else if((item2 != null && item2.getType() != Material.BOOK)) {
                            cr = CrafingRezept.getRezept(item2.getType());
                            p.getInventory().addItem(item2);
                            p.updateInventory();
                        }else{
                            p.closeInventory();
                            msg.send("§7Du musst ein Item reinlegen!");
                            return;
                        }
                        if(cr == null){
                            p.closeInventory();
                            msg.send("§7Es gibt kein Rezept für dieses Item!");
                            return;
                        }
                        if(!AutoCommand.crafter.containsKey(p)){
                            p.closeInventory();
                            msg.send("§7Du musst einen Crafter auswählen!");
                            return;
                        }
                        if(new CrafterFile().save(p, AutoCommand.crafter.get(p).getCrafter(),cr)) {
                            p.closeInventory();
                            msg.send("§7Crafter erstellt!");
                        }else {
                            p.closeInventory();
                            msg.send("§7Crafter konnte nicht erstellt werden!");
                        }
                    }
                }
            }
        }
    }

}

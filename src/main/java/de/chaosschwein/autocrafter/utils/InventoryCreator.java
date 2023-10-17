package de.chaosschwein.autocrafter.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCreator {

    private final Player player;

    public InventoryCreator(Player player) {
        this.player = player;
    }

    private void fillInv(Inventory inv){
        for(int i = 0; i < inv.getSize(); i++){
            ItemStack is = inv.getItem(i);
            if(is == null || is.getType() == Material.AIR){
                inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§e").build());
            }
        }
    }

    public void open() {
        if(player != null) {
            player.openWorkbench(player.getLocation(), true);
            player.getOpenInventory().setTitle("§d§lAutoCrafter");
            player.updateInventory();
        }
    }

    public void openReceiver() {
        if(player != null) {
            Inventory inv = Bukkit.createInventory(null, 36, "§d§lReceiver");
            fillInv(inv);
            inv.setItem(4, null);
            inv.setItem(20, new ItemBuilder(Material.RED_CONCRETE).setName("§cZurück").build());
            inv.setItem(22, new ItemBuilder(Material.BOOK).setName("§dItem Reinlegen").setLore("§5Lege das Item Rein\n§5Was Erstellt werden sollen!").build());
            inv.setItem(24, new ItemBuilder(Material.LIME_CONCRETE).setName("§aReceiver Erstellen").build());
            player.openInventory(inv);
        }
    }

    public void openSender() {
        if(player != null) {
            Inventory inv = Bukkit.createInventory(null, 36, "§d§lSender");
            fillInv(inv);
            inv.setItem(4, null);
            inv.setItem(20, new ItemBuilder(Material.RED_CONCRETE).setName("§cZurück").build());
            inv.setItem(22, new ItemBuilder(Material.BOOK).setName("§dItem Reinlegen").setLore("§5Lege das Item Rein\n§5Was Erstellt werden sollen!").build());
            inv.setItem(24, new ItemBuilder(Material.LIME_CONCRETE).setName("§aSender Erstellen").build());
            player.openInventory(inv);
        }
    }
}

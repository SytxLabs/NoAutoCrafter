package de.chaosschwein.autocrafter.utils;

import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.Objects;

public class Utils {

    public static Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

    public static ArrayList<ItemStack> getItemsInBlock(Block b) {
        ArrayList<ItemStack> istack = new ArrayList<>();
        Inventory inv = null;
        switch (b.getType()) {
            case DISPENSER:
                inv = ((Dispenser) b.getState()).getInventory();
                break;
            case CHEST:
                inv = ((Chest) b.getState()).getInventory();
                break;
            case FURNACE:
                inv = ((Furnace) b.getState()).getInventory();
                break;
            case BLAST_FURNACE:
                inv = ((BlastFurnace) b.getState()).getInventory();
                break;
            case SMOKER:
                inv = ((Smoker) b.getState()).getInventory();
                break;
            case BREWING_STAND:
                inv = ((BrewingStand) b.getState()).getInventory();
                break;
            case HOPPER:
                inv = ((Hopper) b.getState()).getInventory();
                break;
            case DROPPER:
                inv = ((Dropper) b.getState()).getInventory();
                break;
        }
        if(inv == null) {
            return istack;
        }
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack is = inv.getItem(i);
            if (is != null && is.getType() != Material.AIR) {
                istack.add(inv.getItem(i));

            }
        }
        return istack;
    }
}

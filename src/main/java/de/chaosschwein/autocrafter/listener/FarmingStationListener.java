package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.types.*;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FarmingStationListener implements Listener {

    @EventHandler
    public void onDis(BlockDispenseEvent e) {
        if (e.getBlock().getType() == Material.DISPENSER) {
            Dispenser dispenser = (Dispenser) e.getBlock().getState();
            if (new CheckBlocks(dispenser.getBlock()).isFarmingStation()) {
                if (!AutoMain.farming_station) {
                    return;
                }
                FarmingStation f = new FarmingStation(dispenser.getLocation());
                if (f.getLamp() != null && f.getHopper() != null && f.getDispenser() != null && f.getFarmLand() != null) {
                    e.setCancelled(true);

                    if (f.getBlockToFarm().getType() != Material.AIR) {
                        if (f.isFullyGrown()) {
                            FarmType farmType = getFarmType(f);
                            if (farmType == null) {
                                return;
                            }
                            HashMap<Material, ArrayList<Integer>> returnMap = farmType.returnMap;
                            if (returnMap == null) {
                                return;
                            }
                            for (Material material : returnMap.keySet()) {
                                ArrayList<Integer> returnList = returnMap.get(material);
                                int amount = !returnList.isEmpty() ? returnList.get(0) : 0;
                                if (returnList.size() > 1) {
                                    amount = (new Random()).nextInt(returnList.get(1) - returnList.get(0) + 1) + returnList.get(0);
                                }
                                if (amount > 0) {
                                    if(Utils.hasNotEnoughPlace(f.getHopper().getInventory(), material, amount)) {
                                        return;
                                    }
                                    f.getHopper().getInventory().addItem(new ItemStack(material, amount));
                                    f.getBlockToFarm().setType(Material.AIR);
                                    f.getDispenser().getWorld().playSound(f.getDispenser().getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                                }
                            }
                        }
                    }

                    ItemStack item = e.getItem();
                    FarmType type = FarmType.getFromMaterial(item.getType());
                    if (type == null) {
                        for (ItemStack i : dispenser.getInventory().getContents()) {
                            if (i != null) {
                                type = FarmType.getFromMaterial(i.getType());
                                if (type != null) {
                                    item = i;
                                    break;
                                }
                            }
                        }
                    }
                    if (type == null || !type.enable) {
                        return;
                    }
                    if (type.soulSandRequired && f.getFarmLand().getType() != Material.SOUL_SAND) {
                        return;
                    }
                    if (f.getBlockToFarm().getType() == Material.AIR) {
                        FarmType finalType = type;
                        ItemStack finalItem = item;
                        Bukkit.getScheduler().scheduleSyncDelayedTask(AutoMain.instance, () -> {
                            if (finalType.material == Material.NETHER_WART) {
                                if (f.getFarmLand().getType() == Material.SOUL_SAND) {
                                    f.getBlockToFarm().setType(finalType.material);
                                    f.getDispenser().getWorld().playSound(f.getDispenser().getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                                    Utils.removeItem(dispenser, finalItem.getType(), 1);
                                    dispenser.update();
                                }
                            } else {
                                switch (finalType.material) {
                                    case SWEET_BERRIES:
                                        f.getBlockToFarm().setType(Material.SWEET_BERRY_BUSH);
                                        break;
                                    case WHEAT_SEEDS:
                                        f.getBlockToFarm().setType(Material.WHEAT);
                                        break;
                                    case CARROT:
                                        f.getBlockToFarm().setType(Material.CARROTS);
                                        break;
                                    case POTATO:
                                        f.getBlockToFarm().setType(Material.POTATOES);
                                        break;
                                    case BEETROOT_SEEDS:
                                        f.getBlockToFarm().setType(Material.BEETROOTS);
                                        break;
                                    default:
                                        f.getBlockToFarm().setType(finalType.material);
                                        break;
                                }
                                f.getDispenser().getWorld().playSound(f.getDispenser().getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                                dispenser.update();
                                Utils.removeItem(dispenser, finalItem.getType(), 1);
                                dispenser.update();
                            }
                        }, 5);
                    }
                }
            }
        }
    }

    private static FarmType getFarmType(FarmingStation f) {
        FarmType farmType = FarmType.getFromMaterial(f.getBlockToFarm().getType());
        if (farmType != null) {
            return farmType;
        }
        return switch (f.getBlockToFarm().getType()) {
            case SWEET_BERRY_BUSH -> FarmType.getFromMaterial(Material.SWEET_BERRIES);
            case WHEAT -> FarmType.getFromMaterial(Material.WHEAT_SEEDS);
            case CARROTS -> FarmType.getFromMaterial(Material.CARROT);
            case POTATOES -> FarmType.getFromMaterial(Material.POTATO);
            case BEETROOTS -> FarmType.getFromMaterial(Material.BEETROOT_SEEDS);
            default -> null;
        };
    }
}

package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.enums.*;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
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
                            FarmType farmType = FarmType.getFromMaterial(f.getBlockToFarm().getType());
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
                        if (type.material == Material.NETHER_WART) {
                            if (f.getFarmLand().getType() == Material.SOUL_SAND) {
                                f.getBlockToFarm().setType(type.material);
                                f.getDispenser().getWorld().playSound(f.getDispenser().getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                                if (item.getAmount() > 1) {
                                    item.setAmount(item.getAmount() - 1);
                                } else {
                                    dispenser.getInventory().remove(item);
                                }
                            }
                        } else {
                            f.getBlockToFarm().setType(type.material);
                            f.getDispenser().getWorld().playSound(f.getDispenser().getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
                            if (item.getAmount() > 1) {
                                item.setAmount(item.getAmount() - 1);
                            } else {
                                dispenser.getInventory().remove(item);
                            }
                        }
                    }
                }
            }
        }
    }
}

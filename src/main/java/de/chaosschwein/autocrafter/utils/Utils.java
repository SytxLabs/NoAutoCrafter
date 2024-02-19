package de.chaosschwein.autocrafter.utils;

import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Utils {

    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyHash(String password, String hash) {
        return bcrypt.verifyHash(password, hash);
    }

    public static Block getTargetBlock(Player player, int range) {
        BlockIterator blockIterator = new BlockIterator(player, range);
        Block lastBlock = blockIterator.next();
        while (blockIterator.hasNext()) {
            lastBlock = blockIterator.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

    public static ArrayList<ItemStack> getItemsInBlock(Block b) {
        ArrayList<ItemStack> iStack = new ArrayList<>();
        Inventory inv = getBlockInventory(b);
        if (inv == null) {
            return iStack;
        }
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack is = inv.getItem(i);
            if (is != null && is.getType() != Material.AIR) {
                iStack.add(inv.getItem(i));
            }
        }
        return iStack;
    }

    public static Inventory getBlockInventory(Block b) {
        return switch (b.getType()) {
            case DISPENSER -> ((Dispenser) b.getState()).getInventory();
            case CHEST -> ((Chest) b.getState()).getInventory();
            case FURNACE -> ((Furnace) b.getState()).getInventory();
            case BLAST_FURNACE -> ((BlastFurnace) b.getState()).getInventory();
            case SMOKER -> ((Smoker) b.getState()).getInventory();
            case BREWING_STAND -> ((BrewingStand) b.getState()).getInventory();
            case HOPPER -> ((Hopper) b.getState()).getInventory();
            case DROPPER -> ((Dropper) b.getState()).getInventory();
            default -> null;
        };
    }

    public static void removeItem(Inventory inventory, Material material, int amount) {
        if (inventory == null) {
            return;
        }
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null || itemStack.getType() != material) {
                continue;
            }
            int newAmount = itemStack.getAmount() - amount;
            amount -= itemStack.getAmount();
            if (newAmount <= 0) {
                inventory.remove(itemStack);
            } else {
                itemStack.setAmount(newAmount);
            }
            if (amount <= 0) {
                break;
            }
        }
        inventory.getViewers().forEach(player -> {
            try {
                //noinspection UnstableApiUsage
                ((Player) player).updateInventory();
            } catch (Exception ignored) {
            }
        });
    }

    public static void removeItem(Dispenser dispenser, Material material, int amount) {
        if (dispenser == null) {
            return;
        }
        for (int i = 0; i < dispenser.getSnapshotInventory().getSize(); i++) {
            ItemStack itemStack = dispenser.getSnapshotInventory().getItem(i);
            if (itemStack == null || itemStack.getType() != material) {
                continue;
            }
            int newAmount = itemStack.getAmount() - amount;
            amount -= itemStack.getAmount();
            itemStack.setAmount(newAmount);
            dispenser.getSnapshotInventory().setItem(i, itemStack.getAmount() <= 0 ? null : itemStack);
            dispenser.update();
            if (amount <= 0) {
                break;
            }
        }
        dispenser.getInventory().getViewers().forEach(player -> {
            try {
                //noinspection UnstableApiUsage
                ((Player) player).updateInventory();
            } catch (Exception ignored) {
            }
        });
    }

    public static void addItem(Inventory inventory, Material material, int amount) {
        if (inventory == null) {
            return;
        }
        inventory.addItem(new ItemStack(material, amount));
        inventory.getViewers().forEach(player -> {
            try {
                //noinspection UnstableApiUsage
                ((Player) player).updateInventory();
            } catch (Exception ignored) {
            }
        });
    }

    public static void addItem(Inventory inventory, ItemStack i) {
        if (inventory == null) {
            return;
        }
        inventory.addItem(i);
        inventory.getViewers().forEach(player -> {
            try {
                //noinspection UnstableApiUsage
                ((Player) player).updateInventory();
            } catch (Exception ignored) {
            }
        });
    }

    public static boolean hasNotEnoughPlace(Inventory inventory, Material material, int amount) {
        int freeSlots = 0;
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                freeSlots++;
                continue;
            }
            if (itemStack.getType() == material) {
                int newAmount = itemStack.getAmount() + amount;
                if (newAmount <= itemStack.getMaxStackSize()) {
                    return false;
                }
            }
        }
        return freeSlots <= 0;
    }

    public static boolean hasNotEnoughPlace(Inventory inventory, ItemStack i) {
        int freeSlots = 0;
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                freeSlots++;
                continue;
            }
            if (itemStack.getType() == i.getType()) {
                int newAmount = itemStack.getAmount() + i.getAmount();
                if (newAmount <= itemStack.getMaxStackSize()) {
                    return false;
                }
            }
        }
        return freeSlots <= 0;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean hasPermission(Player player, String permission) {
        return hasPermission(player, permission, true);
    }

    public static boolean hasPermission(Player player, String permission, boolean sendMessage) {
        if (permission.equalsIgnoreCase("") || player.hasPermission(permission)) {
            return true;
        }
        if (sendMessage) {
            (new Message(player)).noPermission();
        }
        return false;
    }
}

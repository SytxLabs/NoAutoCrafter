package de.chaosschwein.autocrafter.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;

public class CheckBlocks {

    private final Block block;

    public CheckBlocks(Block block) {
        this.block = block;
    }

    public boolean isCrafter() {
        Block b = this.block;
        boolean isCrafter = false;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case DOWN:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                case NORTH:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.HOPPER);
                    break;
                case SOUTH:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.HOPPER);
                    break;
                case WEST:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                case EAST:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                default:
                    break;
            }
        }
        return isCrafter;
    }
}

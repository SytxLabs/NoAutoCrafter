package de.chaosschwein.autocrafter.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.List;

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

    public boolean isPlacer() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case NORTH:
                    return (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 1).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.STICKY_PISTON);
                case SOUTH:
                    return (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 1).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.STICKY_PISTON);
                case WEST:
                    return (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON);
                case EAST:
                    return (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON);
                case UP:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ()).getType() == Material.STICKY_PISTON;
                default:
                    break;
            }
        }
        return false;
    }

    public boolean isBreaker() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.STICKY_PISTON) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case DOWN:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.END_ROD;
                case NORTH:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.END_ROD;
                case SOUTH:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.END_ROD;
                case WEST:
                    return b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.END_ROD;
                case EAST:
                    return b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.END_ROD;
                case UP:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.END_ROD;
                default:
                    break;
            }
        }
        return false;
    }

    public boolean isSender() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.TRAPPED_CHEST) {
            return false;
        }
        if (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() != Material.END_ROD) {
            return false;
        }
        return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.HOPPER;
    }

    public boolean isReceiver() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.TRAPPED_CHEST) {
            return false;
        }
        return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.END_ROD &&
                b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER;
    }

    public boolean isFarmingStation() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            return switch (face) {
                case NORTH ->
                        (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 1).getType() == Material.REDSTONE_LAMP &&
                                (
                                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.FARMLAND ||
                                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.SOUL_SAND
                                ) && b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ() - 1).getType() == Material.HOPPER);
                case SOUTH ->
                        (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 1).getType() == Material.REDSTONE_LAMP &&
                                (
                                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.FARMLAND ||
                                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.SOUL_SAND
                                ) && b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ() + 1).getType() == Material.HOPPER);
                case WEST ->
                        (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.REDSTONE_LAMP &&
                                (
                                        b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.FARMLAND ||
                                        b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.SOUL_SAND
                                ) && b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER);
                case EAST ->
                        (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.REDSTONE_LAMP &&
                                (
                                        b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.FARMLAND ||
                                        b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.SOUL_SAND
                                ) && b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER);
                default -> false;
            };
        }
        return false;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isBreeder() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            return switch (face) {
                case NORTH -> (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.COARSE_DIRT &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 1).getType() == Material.REDSTONE_LAMP);
                case SOUTH -> (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.COARSE_DIRT &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 1).getType() == Material.REDSTONE_LAMP);
                case WEST -> (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.COARSE_DIRT &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ()).getType() == Material.REDSTONE_LAMP);
                case EAST ->
                        (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.COARSE_DIRT &&
                                b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER &&
                                b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ()).getType() == Material.REDSTONE_LAMP);
                default -> false;
            };
        }
        return false;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isEntityInteractor() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            List<Material> materials = List.of(Material.DIRT, Material.GRASS_BLOCK, Material.COARSE_DIRT, Material.PODZOL, Material.FARMLAND, Material.DIRT_PATH, Material.ROOTED_DIRT);
            return switch (face) {
                case NORTH -> (materials.contains(b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType()) &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ() - 1).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER);
                case SOUTH -> (materials.contains(b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType()) &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ() + 1).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER);
                case WEST -> (materials.contains(b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType()) &&
                        b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER);
                case EAST -> (materials.contains(b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType()) &&
                        b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.HOPPER);
                default -> false;
            };
        }
        return false;
    }
}

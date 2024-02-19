package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.util.BoundingBox;

public class Breeder {
    public final boolean isBreeder;

    public final Dispenser dispenser;
    public Block lamp;

    public BoundingBox boundingBox;

    public Breeder(Location dispenser) {
        if (!new CheckBlocks(dispenser.getBlock()).isBreeder()) {
            this.dispenser = null;
            this.lamp = null;
            this.isBreeder = false;
            return;
        }
        this.dispenser = (Dispenser) dispenser.getBlock().getState();
        if (this.dispenser.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) this.dispenser.getBlockData()).getFacing();
            this.lamp = switch (face) {
                case NORTH -> this.dispenser.getWorld().getBlockAt(dispenser.getBlockX(), dispenser.getBlockY() + 2, dispenser.getBlockZ() - 1);
                case SOUTH -> this.dispenser.getWorld().getBlockAt(dispenser.getBlockX(), dispenser.getBlockY() + 2, dispenser.getBlockZ() + 1);
                case WEST -> this.dispenser.getWorld().getBlockAt(dispenser.getBlockX() - 1, dispenser.getBlockY() + 2, dispenser.getBlockZ());
                case EAST -> this.dispenser.getWorld().getBlockAt(dispenser.getBlockX() + 1, dispenser.getBlockY() + 2, dispenser.getBlockZ());
                default -> null;
            };
        }
        this.isBreeder = true;
        registerBlocksInRange();
    }

    private void registerBlocksInRange() {
        if (dispenser.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) dispenser.getBlockData()).getFacing();
            int range = AutoMain.breederRange;
            int rangeHalf = range / 2;
            switch (face) {
                case NORTH -> boundingBox = new BoundingBox(dispenser.getX() - rangeHalf, dispenser.getY() - 1, (dispenser.getZ() - range) - 1, dispenser.getX() + rangeHalf, dispenser.getY() + 3, dispenser.getZ());
                case SOUTH -> boundingBox = new BoundingBox(dispenser.getX() - rangeHalf, dispenser.getY() - 1, dispenser.getZ(), dispenser.getX() + rangeHalf, dispenser.getY() + 3, (dispenser.getZ() + range) + 1);
                case WEST -> boundingBox = new BoundingBox((dispenser.getX() - range) - 1, dispenser.getY() - 1, dispenser.getZ() - rangeHalf, dispenser.getX(), dispenser.getY() + 3, dispenser.getZ() + rangeHalf);
                case EAST -> boundingBox = new BoundingBox(dispenser.getX(), dispenser.getY() - 1, dispenser.getZ() - rangeHalf, (dispenser.getX() + range) + 1, dispenser.getY() + 3, dispenser.getZ() + rangeHalf);
                default -> boundingBox = new BoundingBox(dispenser.getX() - rangeHalf, dispenser.getY() - 1, dispenser.getZ() - rangeHalf, dispenser.getX() + rangeHalf, dispenser.getY() + 3, dispenser.getZ() + rangeHalf);
            }
        }
    }

    public void blinkLamp() {
        if (lamp == null || lamp.getType() != Material.REDSTONE_LAMP) {
            return;
        }
        Lightable lightable = (Lightable) lamp.getBlockData();
        lightable.setLit(true);
        lamp.setBlockData(lightable);
        AutoMain.instance.getServer().getScheduler().runTaskLater(AutoMain.instance, () -> {
            lightable.setLit(false);
            lamp.setBlockData(lightable);
        }, 20L);
    }
}

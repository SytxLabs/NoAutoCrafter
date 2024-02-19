package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.block.data.*;

public class FarmingStation {

    private Dispenser dispenser = null;
    private Hopper hopper = null;
    private Block farmLand = null;
    private Block lamp = null;
    private Block blockToFarm = null;
    public boolean isFarmingStation = false;

    public FarmingStation(Location dispenser) {
        if (!(new CheckBlocks(dispenser.getBlock())).isFarmingStation()) {
            return;
        }
        this.isFarmingStation = true;
        this.dispenser = (Dispenser) dispenser.getBlock().getState();
        if (this.dispenser.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) this.dispenser.getBlockData()).getFacing();
            switch (face) {
                case NORTH:
                    this.farmLand = dispenser.getBlock().getLocation().add(0, -1, -1).getBlock();
                    this.hopper = (Hopper) dispenser.getBlock().getLocation().add(0, -2, -1).getBlock().getState();
                    this.lamp = dispenser.getBlock().getLocation().add(0, +1, -1).getBlock();
                    this.blockToFarm = dispenser.getBlock().getLocation().add(0, 0, -1).getBlock();
                    break;
                case SOUTH:
                    this.farmLand = dispenser.getBlock().getLocation().add(0, -1, +1).getBlock();
                    this.hopper = (Hopper) dispenser.getBlock().getLocation().add(0, -2, +1).getBlock().getState();
                    this.lamp = dispenser.getBlock().getLocation().add(0, +1, +1).getBlock();
                    this.blockToFarm = dispenser.getBlock().getLocation().add(0, 0, +1).getBlock();
                    break;
                case WEST:
                    this.farmLand = dispenser.getBlock().getLocation().add(-1, -1, 0).getBlock();
                    this.hopper = (Hopper) dispenser.getBlock().getLocation().add(-1, -2, 0).getBlock().getState();
                    this.lamp = dispenser.getBlock().getLocation().add(-1, +1, 0).getBlock();
                    this.blockToFarm = dispenser.getBlock().getLocation().add(-1, 0, 0).getBlock();
                    break;
                case EAST:
                    this.farmLand = dispenser.getBlock().getLocation().add(+1, -1, 0).getBlock();
                    this.hopper = (Hopper) dispenser.getBlock().getLocation().add(+1, -2, 0).getBlock().getState();
                    this.lamp = dispenser.getBlock().getLocation().add(+1, +1, 0).getBlock();
                    this.blockToFarm = dispenser.getBlock().getLocation().add(+1, 0, 0).getBlock();
                    break;
            }
        }
    }

    public Dispenser getDispenser() {
        return dispenser;
    }

    public Hopper getHopper() {
        return hopper;
    }

    public Block getFarmLand() {
        return farmLand;
    }

    public Block getLamp() {
        return lamp;
    }

    public Block getBlockToFarm() {
        return blockToFarm;
    }

    public boolean isFullyGrown() {
        Block block = this.blockToFarm;
        BlockData bData = block.getBlockData();
        if (block.getType() == Material.ACACIA_SAPLING || block.getType() == Material.BIRCH_SAPLING || block.getType() == Material.DARK_OAK_SAPLING || block.getType() == Material.JUNGLE_SAPLING || block.getType() == Material.OAK_SAPLING || block.getType() == Material.SPRUCE_SAPLING) {
            return false;
        }
        if (this.farmLand.getType() != Material.FARMLAND && this.farmLand.getType() != Material.SOUL_SAND) {
            return false;
        }
        if (bData instanceof Ageable age) {
            int progress = age.getMaximumAge() - age.getAge();
            Material material = block.getType();
            if (material == Material.CACTUS || material == Material.SUGAR_CANE) {
                return false;
            }

            return progress <= 0;
        } else if (bData instanceof Levelled levelled) {
            int progress = levelled.getMaximumLevel() - levelled.getLevel();
            return progress <= 0;

        } else if (bData instanceof Hatchable hatchable) {
            int progress = hatchable.getMaximumHatch() - hatchable.getHatch();
            return progress <= 0;
        }
        return false;
    }

    public void blinkLamp() {
        Lightable lightable = (Lightable) lamp.getBlockData();
        lightable.setLit(true);
        lamp.setBlockData(lightable);
        AutoMain.instance.getServer().getScheduler().runTaskLater(AutoMain.instance, () -> {
            lightable.setLit(false);
            lamp.setBlockData(lightable);
        }, 20L);
    }
}

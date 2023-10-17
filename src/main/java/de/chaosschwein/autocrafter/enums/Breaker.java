package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

public class Breaker {
    public Block piston = null;
    public Block endRod = null;
    public Location breakLoc = null;
    public boolean isBreaker = false;

    public Breaker(Location piston) {
        if (piston.getBlock().getType() != Material.AIR && piston.getBlock().getType() == Material.STICKY_PISTON) {
            this.piston = piston.getBlock();
            this.isBreaker = new CheckBlocks(this.piston).isBreaker();
        }
    }

    public boolean isBreaker() {
        this.isBreaker = new CheckBlocks(this.piston).isBreaker();
        return this.isBreaker;
    }

    public Breaker getBreaker() {
        if (this.isBreaker()) {
            if (this.piston.getBlockData() instanceof Directional) {
                BlockFace face = ((Directional) this.piston.getBlockData()).getFacing();
                switch (face) {
                    case NORTH:
                        this.endRod = this.piston.getLocation().add(0, 0, -1).getBlock();
                        this.breakLoc = this.piston.getLocation().add(0, 0, -2);
                        break;
                    case SOUTH:
                        this.endRod = this.piston.getLocation().add(0, 0, +1).getBlock();
                        this.breakLoc = this.piston.getLocation().add(0, 0, +2);
                        break;
                    case EAST:
                        this.endRod = this.piston.getLocation().add(+1, 0, 0).getBlock();
                        this.breakLoc = this.piston.getLocation().add(+2, 0, 0);
                        break;
                    case WEST:
                        this.endRod = this.piston.getLocation().add(-1, 0, 0).getBlock();
                        this.breakLoc = this.piston.getLocation().add(-2, 0, 0);
                        break;
                    case DOWN:
                        this.endRod = this.piston.getLocation().add(0, -1, 0).getBlock();
                        this.breakLoc = this.piston.getLocation().add(0, -2, 0);
                        break;
                    case UP:
                        this.endRod = this.piston.getLocation().add(0, +1, 0).getBlock();
                        this.breakLoc = this.piston.getLocation().add(0, +2, 0);
                        break;
                }
            }
        }
        return this;
    }
}

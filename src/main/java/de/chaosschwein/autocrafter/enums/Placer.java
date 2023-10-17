package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

public class Placer {
    public Block piston = null;
    public Block dispenser = null;
    public Location placeLoc = null;
    public boolean isPlacer = false;

    public Placer(Location dispenser) {
        if (dispenser.getBlock().getType() != Material.AIR && dispenser.getBlock().getType() == Material.DISPENSER) {
            this.dispenser = dispenser.getBlock();
            this.isPlacer = new CheckBlocks(this.dispenser).isPlacer();
        }
    }

    public boolean isPlacer() {
        this.isPlacer = new CheckBlocks(this.dispenser).isPlacer();
        return this.isPlacer;
    }

    public Placer getPlacer() {
        if (this.isPlacer()) {
            if (this.dispenser.getBlockData() instanceof Directional) {
                BlockFace face = ((Directional) this.dispenser.getBlockData()).getFacing();
                switch (face) {
                    case NORTH:
                        this.placeLoc = this.dispenser.getLocation().add(0, 0, -1);
                        if (this.dispenser.getLocation().add(0, 1, -1).getBlock().getType() == Material.PISTON) {
                            this.piston = this.dispenser.getLocation().add(0, 1, -1).getBlock();
                            break;
                        }
                        this.piston = this.dispenser.getLocation().add(0, -1, -1).getBlock();
                        break;
                    case SOUTH:
                        this.placeLoc = this.dispenser.getLocation().add(0, 0, +1);
                        if (this.dispenser.getLocation().add(0, 1, +1).getBlock().getType() == Material.PISTON) {
                            this.piston = this.dispenser.getLocation().add(0, 1, +1).getBlock();
                            break;
                        }
                        this.piston = this.dispenser.getLocation().add(0, -1, +1).getBlock();
                        break;
                    case WEST:
                        this.placeLoc = this.dispenser.getLocation().add(-1, 0, 0);
                        if (this.dispenser.getLocation().add(-1, 1, 0).getBlock().getType() == Material.PISTON) {
                            this.piston = this.dispenser.getLocation().add(-1, 1, 0).getBlock();
                            break;
                        }
                        this.piston = this.dispenser.getLocation().add(-1, -1, 0).getBlock();
                        break;
                    case EAST:
                        this.placeLoc = this.dispenser.getLocation().add(+1, 0, 0);
                        if (this.dispenser.getLocation().add(+1, 1, 0).getBlock().getType() == Material.PISTON) {
                            this.piston = this.dispenser.getLocation().add(+1, 1, 0).getBlock();
                            break;
                        }
                        this.piston = this.dispenser.getLocation().add(+1, -1, 0).getBlock();
                        break;
                    case UP:
                        this.placeLoc = this.dispenser.getLocation().add(0, 1, 0);
                        this.piston = this.dispenser.getLocation().add(0, 2, 0).getBlock();
                        break;
                }
            }
        }
        return this;
    }
}

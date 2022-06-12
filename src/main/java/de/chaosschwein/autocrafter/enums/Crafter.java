package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Crafter {

    public Block craftingTable = null;
    public Block hopper = null;
    public Block dispenser = null;
    public boolean isCrafter = false;
    public CrafingRezept rezept = null;
    public String ownerUUID = "";
    public String ownerName = "";
    public long createtAT = 0;

    public Crafter(Location dispenser) {
        if (dispenser.getBlock().getType() != Material.AIR && dispenser.getBlock().getType() != Material.DISPENSER) {
            this.dispenser = dispenser.getBlock();
            this.isCrafter = new CheckBlocks(this.dispenser).isCrafter();
        }
    }

    public boolean isCrafter() {
        this.isCrafter = new CheckBlocks(this.dispenser).isCrafter();
        return this.isCrafter;
    }
    public Crafter getCrafter() {
        if (this.isCrafter) {
            BlockFace face = this.dispenser.getFace(this.dispenser);
            if (face == null) {
                return this;
            }
            switch (face) {
                case DOWN:
                    this.craftingTable = this.dispenser.getLocation().add(0, -1, 0).getBlock();
                    this.hopper = this.dispenser.getLocation().add(0, -2, 0).getBlock();
                    break;
                case NORTH:
                    this.craftingTable = this.dispenser.getLocation().add(0, 0, -1).getBlock();
                    this.hopper = this.dispenser.getLocation().add(0, -1, -1).getBlock();
                    break;
                case SOUTH:
                    this.craftingTable = this.dispenser.getLocation().add(0, 0, +1).getBlock();
                    this.hopper = this.dispenser.getLocation().add(0, -1, +1).getBlock();
                    break;
                case WEST:
                    this.craftingTable = this.dispenser.getLocation().add(-1, 0, 0).getBlock();
                    this.hopper = this.dispenser.getLocation().add(-1, -1, 0).getBlock();
                    break;
                case EAST:
                    this.craftingTable = this.dispenser.getLocation().add(+1, 0, 0).getBlock();
                    this.hopper = this.dispenser.getLocation().add(+1, -1, 0).getBlock();
                    break;
            }
        }

        return this;
    }

    public CrafingRezept getRezept() {
        if (this.rezept == null) {
            this.rezept = new CrafterFile().getRezept(this);
        }
        return this.rezept;
    }

    public String getOwnerUUID() {
        if (this.ownerUUID.equals("")) {
            this.ownerUUID = new CrafterFile().getOwnerUUID(this);
        }
        return this.ownerUUID;
    }

    public String getOwnerName() {
        if (this.ownerName.equals("")) {
            this.ownerName = new CrafterFile().getOwnerName(this);
        }
        return this.ownerName;
    }

    public long getCreatetAT() {
        if (this.createtAT == 0) {
            this.createtAT = new CrafterFile().getCreatetAT(this);
        }
        return this.createtAT;
    }

    public Crafter getAllData(){
        this.getCrafter();
        this.getRezept();
        this.getOwnerUUID();
        this.getOwnerName();
        this.getCreatetAT();
        return this;
    }
}

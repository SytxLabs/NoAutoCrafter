package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.enums.Crafter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;

import java.util.Objects;

public class CheckBlocks {

    private final Block block;

    public CheckBlocks(Block block){
        this.block = block;
    }

    public boolean isCrafter(){
        Block b = this.block;
        boolean iscrafter = false;
        Location loc = b.getLocation();
        if(b.getType() != Material.DISPENSER){
            return false;
        }
        BlockFace face = b.getFace(b);
        if(face == null){
            return false;
        }
        switch (face) {
            case DOWN:
                iscrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).getType()==Material.CRAFTING_TABLE &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-2, loc.getBlockZ()).getType()==Material.HOPPER);
                break;
            case NORTH:
                iscrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-1).getType()==Material.CRAFTING_TABLE &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()-1).getType()==Material.HOPPER);
                break;
            case SOUTH:
                iscrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+1).getType()==Material.CRAFTING_TABLE &&
                        b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()+1).getType()==Material.HOPPER);
                break;
            case WEST:
                iscrafter = (b.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()).getType()==Material.CRAFTING_TABLE &&
                        b.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()-1, loc.getBlockZ()).getType()==Material.HOPPER);
                break;
            case EAST:
                iscrafter = (b.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()).getType()==Material.CRAFTING_TABLE &&
                        b.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()-1, loc.getBlockZ()).getType()==Material.HOPPER);
                break;
            default:
                break;
        }

        return iscrafter;
    }


}

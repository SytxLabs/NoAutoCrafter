package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Hopper;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.List;

public class EntityInteractor {

    public boolean isEntityInteractor;
    public Dispenser dispenser;
    public Hopper hopper;
    public EntityInteractor(Location loc) {
        if (!new CheckBlocks(loc.getBlock()).isEntityInteractor()) {
            this.dispenser = null;
            this.isEntityInteractor = false;
            return;
        }
        this.dispenser = (Dispenser) loc.getBlock().getState();
        if (this.dispenser.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) this.dispenser.getBlockData()).getFacing();
            Block hopper = switch (face) {
                case NORTH -> this.dispenser.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
                case SOUTH -> this.dispenser.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ() + 1);
                case WEST -> this.dispenser.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 2, loc.getBlockZ());
                case EAST -> this.dispenser.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 2, loc.getBlockZ());
                default -> null;
            };
            if (hopper != null && hopper.getType() == Material.HOPPER) {
                this.hopper = (Hopper) hopper.getState();
            }
        }
        this.isEntityInteractor = true;
    }

    public static HashMap<Material, HashMap<EntityType, Material>> itemForEntity = new HashMap<>() {{
        put(Material.BUCKET, new HashMap<>() {{
            put(EntityType.COW, Material.MILK_BUCKET);
            put(EntityType.MUSHROOM_COW, Material.MILK_BUCKET);
            put(EntityType.LLAMA, Material.MILK_BUCKET);
        }});
        put(Material.BOWL, new HashMap<>() {{
            put(EntityType.MUSHROOM_COW, Material.MUSHROOM_STEW);
        }});
    }};
}

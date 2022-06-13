package de.chaosschwein.autocrafter.enums.data;

import de.chaosschwein.autocrafter.enums.Crafter;
import org.bukkit.Material;

import java.util.HashMap;

public enum CrafingRezept {
    OAK_PLANKS(new HashMap<String,Integer>(){{put(Material.OAK_LOG.name(), 1);}}, Material.OAK_PLANKS.name(), 4,1),
    SPRUCE_PLANKS(new HashMap<String,Integer>(){{put(Material.SPRUCE_LOG.name(), 1);}}, Material.SPRUCE_PLANKS.name(), 4,1),
    BIRCH_PLANKS(new HashMap<String,Integer>(){{put(Material.BIRCH_LOG.name(), 1);}}, Material.BIRCH_PLANKS.name(), 4,1),
    JUNGLE_PLANKS(new HashMap<String,Integer>(){{put(Material.JUNGLE_LOG.name(), 1);}}, Material.JUNGLE_PLANKS.name(), 4,1),
    ACACIA_PLANKS(new HashMap<String,Integer>(){{put(Material.ACACIA_LOG.name(), 1);}}, Material.ACACIA_PLANKS.name(), 4,1),
    DARK_OAK_PLANKS(new HashMap<String,Integer>(){{put(Material.DARK_OAK_LOG.name(), 1);}}, Material.DARK_OAK_PLANKS.name(), 4,1),
    CRIMSON_PLANKS(new HashMap<String,Integer>(){{put(Material.CRIMSON_STEM.name(), 1);}}, Material.CRIMSON_PLANKS.name(), 4,1),
    WARPED_PLANKS(new HashMap<String,Integer>(){{put(Material.WARPED_STEM.name(), 1);}}, Material.WARPED_PLANKS.name(), 4,1),


    OAK_SLAB(new HashMap<String,Integer>(){{put(Material.OAK_PLANKS.name(), 3);}}, Material.OAK_SLAB.name(), 6,1),
    SPRUCE_SLAB(new HashMap<String,Integer>(){{put(Material.SPRUCE_PLANKS.name(), 3);}}, Material.SPRUCE_SLAB.name(), 6,1),
    BIRCH_SLAB(new HashMap<String,Integer>(){{put(Material.BIRCH_PLANKS.name(), 3);}}, Material.BIRCH_SLAB.name(), 6,1),
    JUNGLE_SLAB(new HashMap<String,Integer>(){{put(Material.JUNGLE_PLANKS.name(), 3);}}, Material.JUNGLE_SLAB.name(), 6,1),
    ACACIA_SLAB(new HashMap<String,Integer>(){{put(Material.ACACIA_PLANKS.name(), 3);}}, Material.ACACIA_SLAB.name(), 6,1),
    DARK_OAK_SLAB(new HashMap<String,Integer>(){{put(Material.DARK_OAK_PLANKS.name(), 3);}}, Material.DARK_OAK_SLAB.name(), 6,1),
    CRIMSON_SLAB(new HashMap<String,Integer>(){{put(Material.CRIMSON_PLANKS.name(), 3);}}, Material.CRIMSON_SLAB.name(), 6,1),
    WARPED_SLAB(new HashMap<String,Integer>(){{put(Material.WARPED_PLANKS.name(), 3);}}, Material.WARPED_SLAB.name(), 6,1),
    STONE_SLAB(new HashMap<String,Integer>(){{put(Material.STONE.name(), 3);}}, Material.STONE_SLAB.name(), 6,1),
    SANDSTONE_SLAB(new HashMap<String,Integer>(){{put(Material.SANDSTONE.name(), 3);}}, Material.SANDSTONE_SLAB.name(), 6,1),
    COBBLESTONE_SLAB(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 3);}}, Material.COBBLESTONE_SLAB.name(), 6,1),
    BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.BRICK.name(), 3);}}, Material.BRICK_SLAB.name(), 6,1),
    STONE_BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.STONE_BRICKS.name(), 3);}}, Material.STONE_BRICK_SLAB.name(), 6,1),
    NETHER_BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.NETHER_BRICK.name(), 3);}}, Material.NETHER_BRICK_SLAB.name(), 6,1),
    QUARTZ_SLAB(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 3);}}, Material.QUARTZ_SLAB.name(), 6,1),
    RED_SANDSTONE_SLAB(new HashMap<String,Integer>(){{put(Material.RED_SANDSTONE.name(), 3);}}, Material.RED_SANDSTONE_SLAB.name(), 6,1),
    PURPUR_SLAB(new HashMap<String,Integer>(){{put(Material.PURPUR_BLOCK.name(), 3);}}, Material.PURPUR_SLAB.name(), 6,1),
    PRISMARINE_SLAB(new HashMap<String,Integer>(){{put(Material.PRISMARINE.name(), 3);}}, Material.PRISMARINE_SLAB.name(), 6,1),
    PRISMARINE_BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.PRISMARINE_BRICKS.name(), 3);}}, Material.PRISMARINE_BRICK_SLAB.name(), 6,1),
    DARK_PRISMARINE_SLAB(new HashMap<String,Integer>(){{put(Material.DARK_PRISMARINE.name(), 3);}}, Material.DARK_PRISMARINE_SLAB.name(), 6,1),
    POLISHED_GRANITE_SLAB(new HashMap<String,Integer>(){{put(Material.POLISHED_GRANITE.name(), 3);}}, Material.POLISHED_GRANITE_SLAB.name(), 6,1),
    POLISHED_DIORITE_SLAB(new HashMap<String,Integer>(){{put(Material.POLISHED_DIORITE.name(), 3);}}, Material.POLISHED_DIORITE_SLAB.name(), 6,1),
    POLISHED_ANDESITE_SLAB(new HashMap<String,Integer>(){{put(Material.POLISHED_ANDESITE.name(), 3);}}, Material.POLISHED_ANDESITE_SLAB.name(), 6,1),
    GRANITE_SLAB(new HashMap<String,Integer>(){{put(Material.GRANITE.name(), 3);}}, Material.GRANITE_SLAB.name(), 6,1),
    DIORITE_SLAB(new HashMap<String,Integer>(){{put(Material.DIORITE.name(), 3);}}, Material.DIORITE_SLAB.name(), 6,1),
    ANDESITE_SLAB(new HashMap<String,Integer>(){{put(Material.ANDESITE.name(), 3);}}, Material.ANDESITE_SLAB.name(), 6,1),
    RED_NETHER_BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.RED_NETHER_BRICK_WALL.name(), 3);}}, Material.RED_NETHER_BRICK_SLAB.name(), 6,1),


    //Stairs
    OAK_STAIRS(new HashMap<String,Integer>(){{put(Material.OAK_PLANKS.name(), 6);}}, Material.OAK_STAIRS.name(), 4,1),
    SPRUCE_STAIRS(new HashMap<String,Integer>(){{put(Material.SPRUCE_PLANKS.name(), 6);}}, Material.SPRUCE_STAIRS.name(), 4,1),
    BIRCH_STAIRS(new HashMap<String,Integer>(){{put(Material.BIRCH_PLANKS.name(), 6);}}, Material.BIRCH_STAIRS.name(), 4,1),
    JUNGLE_STAIRS(new HashMap<String,Integer>(){{put(Material.JUNGLE_PLANKS.name(), 6);}}, Material.JUNGLE_STAIRS.name(), 4,1),
    ACACIA_STAIRS(new HashMap<String,Integer>(){{put(Material.ACACIA_PLANKS.name(), 6);}}, Material.ACACIA_STAIRS.name(), 4,1),
    DARK_OAK_STAIRS(new HashMap<String,Integer>(){{put(Material.DARK_OAK_PLANKS.name(), 6);}}, Material.DARK_OAK_STAIRS.name(), 4,1),
    COBBLESTONE_STAIRS(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 6);}}, Material.COBBLESTONE_STAIRS.name(), 4,1),
    BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.BRICKS.name(), 6);}}, Material.BRICK_STAIRS.name(), 4,1),
    STONE_STAIRS(new HashMap<String,Integer>(){{put(Material.STONE.name(), 6);}}, Material.STONE_STAIRS.name(), 4,1),
    NETHER_BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.NETHER_BRICK.name(), 6);}}, Material.NETHER_BRICK_STAIRS.name(), 4,1),
    SANDSTONE_STAIRS(new HashMap<String,Integer>(){{put(Material.SANDSTONE.name(), 6);}}, Material.SANDSTONE_STAIRS.name(), 4,1),
    QUARTZ_STAIRS(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 6);}}, Material.QUARTZ_STAIRS.name(), 4,1),
    PRISMARINE_STAIRS(new HashMap<String,Integer>(){{put(Material.PRISMARINE.name(), 6);}}, Material.PRISMARINE_STAIRS.name(), 4,1),
    PRISMARINE_BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.PRISMARINE_BRICKS.name(), 6);}}, Material.PRISMARINE_BRICK_STAIRS.name(), 4,1),
    DARK_PRISMARINE_STAIRS(new HashMap<String,Integer>(){{put(Material.DARK_PRISMARINE.name(), 6);}}, Material.DARK_PRISMARINE_STAIRS.name(), 4,1),
    POLISHED_GRANITE_STAIRS(new HashMap<String,Integer>(){{put(Material.POLISHED_GRANITE.name(), 6);}}, Material.POLISHED_GRANITE_STAIRS.name(), 4,1),
    SMOOTH_RED_SANDSTONE_STAIRS(new HashMap<String,Integer>(){{put(Material.SMOOTH_RED_SANDSTONE.name(), 6);}}, Material.SMOOTH_RED_SANDSTONE_STAIRS.name(), 4,1),
    MOSSY_STONE_BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.MOSSY_STONE_BRICKS.name(), 6);}}, Material.MOSSY_STONE_BRICK_STAIRS.name(), 4,1),
    POLISHED_DIORITE_STAIRS(new HashMap<String,Integer>(){{put(Material.POLISHED_DIORITE.name(), 6);}}, Material.POLISHED_DIORITE_STAIRS.name(), 4,1),
    MOSSY_COBBLESTONE_STAIRS(new HashMap<String,Integer>(){{put(Material.MOSSY_COBBLESTONE.name(), 6);}}, Material.MOSSY_COBBLESTONE_STAIRS.name(), 4,1),
    END_STONE_BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.END_STONE_BRICKS.name(), 6);}}, Material.END_STONE_BRICK_STAIRS.name(), 4,1),
    SMOOTH_SANDSTONE_STAIRS(new HashMap<String,Integer>(){{put(Material.SMOOTH_SANDSTONE.name(), 6);}}, Material.SMOOTH_SANDSTONE_STAIRS.name(), 4,1),
    SMOOTH_QUARTZ_STAIRS(new HashMap<String,Integer>(){{put(Material.SMOOTH_QUARTZ.name(), 6);}}, Material.SMOOTH_QUARTZ_STAIRS.name(), 4,1),
    GRANITE_STAIRS(new HashMap<String,Integer>(){{put(Material.GRANITE.name(), 6);}}, Material.GRANITE_STAIRS.name(), 4,1),
    ANDESITE_STAIRS(new HashMap<String,Integer>(){{put(Material.ANDESITE.name(), 6);}}, Material.ANDESITE_STAIRS.name(), 4,1),
    POLISHED_ANDESITE_STAIRS(new HashMap<String,Integer>(){{put(Material.POLISHED_ANDESITE.name(), 6);}}, Material.POLISHED_ANDESITE_STAIRS.name(), 4,1),
    RED_NETHER_BRICK_STAIRS(new HashMap<String,Integer>(){{put(Material.RED_NETHER_BRICKS.name(), 6);}}, Material.RED_NETHER_BRICK_STAIRS.name(), 4,1),
    SMOOTH_RED_SANDSTONE_SLAB(new HashMap<String,Integer>(){{put(Material.SMOOTH_RED_SANDSTONE.name(), 6);}}, Material.SMOOTH_RED_SANDSTONE_SLAB.name(), 4,1),
    MOSSY_STONE_BRICK_SLAB(new HashMap<String,Integer>(){{put(Material.MOSSY_STONE_BRICKS.name(), 6);}}, Material.MOSSY_STONE_BRICK_SLAB.name(), 4,1),



    STICKS(new HashMap<String,Integer>(){{put("PLANKS", 1);}}, Material.STICK.name(), 4,1),
    TORCHES(new HashMap<String,Integer>(){{put(Material.STICK.name(), 1);put(Material.COAL.name(), 1);}}, Material.TORCH.name(), 4,1),
    CRAFTING_TABLE(new HashMap<String,Integer>(){{put("PLANKS", 4);}}, Material.CRAFTING_TABLE.name(), 1,1),
    CHEST(new HashMap<String,Integer>(){{put("PLANKS", 8);}}, Material.CHEST.name(), 1,1),
    FURNACE(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 8);}}, Material.FURNACE.name(), 1,1),
    LADDER(new HashMap<String,Integer>(){{put(Material.STICK.name(), 7);}}, Material.LADDER.name(), 3,1),
    BOAT(new HashMap<String,Integer>(){{put("PLANKS", 5);}}, Material.OAK_BOAT.name(), 1,1),
    SIGN(new HashMap<String,Integer>(){{put(Material.STICK.name(), 1);put("PLANKS", 6);}}, Material.OAK_SIGN.name(), 1,1),
    DOOR(new HashMap<String,Integer>(){{put("PLANKS", 6);}}, Material.OAK_DOOR.name(), 1,1),
    FENCE(new HashMap<String,Integer>(){{put("PLANKS", 4);put(Material.STICK.name(), 2);}}, Material.OAK_FENCE.name(), 3,1),
    FENCE_GATE(new HashMap<String,Integer>(){{put("PLANKS", 2);put(Material.STICK.name(), 4);}}, Material.OAK_FENCE_GATE.name(), 1,1),
    PAINTING(new HashMap<String,Integer>(){{put(Material.STICK.name(), 8);put(Material.WHITE_WOOL.name(),1);}}, Material.PAINTING.name(), 1,1),
    BED(new HashMap<String,Integer>(){{put("PLANKS", 3);put("WOOL",3);}}, Material.WHITE_BED.name(), 1,1),


    GLOWSTONE(new HashMap<String,Integer>(){{put(Material.GLOWSTONE_DUST.name(), 4);}}, Material.GLOWSTONE.name(), 1,1),
    SNOW(new HashMap<String,Integer>(){{put(Material.SNOWBALL.name(), 4);}}, Material.SNOW.name(), 1,1),
    STONE_BRICK(new HashMap<String,Integer>(){{put(Material.STONE.name(), 4);}}, Material.STONE_BRICKS.name(), 4,1),
    BRICKS(new HashMap<String,Integer>(){{put(Material.BRICK.name(), 4);}}, Material.BRICKS.name(), 1,1),
    SANDSTONE(new HashMap<String,Integer>(){{put(Material.SAND.name(), 4);}}, Material.SANDSTONE.name(), 1,1),
    RED_SANDSTONE(new HashMap<String,Integer>(){{put(Material.RED_SAND.name(), 4);}}, Material.RED_SANDSTONE.name(), 1,1),
    SMOOTH_SANDSTONE(new HashMap<String,Integer>(){{put(Material.SANDSTONE.name(), 4);}}, Material.SMOOTH_SANDSTONE.name(), 4,1),
    GOLD_BLOCK(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 9);}}, Material.GOLD_BLOCK.name(), 1,1),
    IRON_BLOCK(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 9);}}, Material.IRON_BLOCK.name(), 1,1),
    DIAMOND_BLOCK(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 9);}}, Material.DIAMOND_BLOCK.name(), 1,1),
    EMERALD_BLOCK(new HashMap<String,Integer>(){{put(Material.EMERALD.name(), 9);}}, Material.EMERALD_BLOCK.name(), 1,1),
    LAPIS_BLOCK(new HashMap<String,Integer>(){{put(Material.LAPIS_LAZULI.name(), 9);}}, Material.LAPIS_BLOCK.name(), 1,1),
    COAL_BLOCK(new HashMap<String,Integer>(){{put(Material.COAL.name(), 9);}}, Material.COAL_BLOCK.name(), 1,1),
    REDSTONE_BLOCK(new HashMap<String,Integer>(){{put(Material.REDSTONE.name(), 9);}}, Material.REDSTONE_BLOCK.name(), 1,1),
    WOOL_BLOCK(new HashMap<String,Integer>(){{put(Material.STRING.name(), 4);}}, Material.WHITE_WOOL.name(), 1,1),
    BOOKSHELF(new HashMap<String,Integer>(){{put(Material.BOOK.name(), 3);put("PLANKS",6);}}, Material.BOOKSHELF.name(), 1,1),
    NOTE_BLOCK(new HashMap<String,Integer>(){{put(Material.REDSTONE.name(), 1);put("PLANKS",8);}}, Material.NOTE_BLOCK.name(), 1,1),
    CLAY_BLOCK(new HashMap<String,Integer>(){{put(Material.CLAY_BALL.name(), 4);}}, Material.CLAY.name(), 1,1),
    TNT(new HashMap<String,Integer>(){{put(Material.SAND.name(), 4);put(Material.GUNPOWDER.name(), 4);}}, Material.TNT.name(), 1,1),
    COBBLESTONE_WALLS(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 6);}}, Material.COBBLESTONE_WALL.name(), 6,1),
    MOSSY_COBBLESTONE_WALLS(new HashMap<String,Integer>(){{put(Material.MOSSY_COBBLESTONE.name(), 6);}}, Material.MOSSY_COBBLESTONE_WALL.name(), 6,1),
    NETHER_BRICK(new HashMap<String,Integer>(){{put(Material.NETHER_BRICK.name(), 4);}}, Material.NETHER_BRICKS.name(), 1,1),
    RED_NETHER_BRICK(new HashMap<String,Integer>(){{put(Material.NETHER_BRICK.name(), 2);put(Material.NETHER_WART.name(), 2);}}, Material.RED_NETHER_BRICKS.name(), 1,1),
    CHISELD_NETHER_BRICKS(new HashMap<String,Integer>(){{put(Material.NETHER_BRICK_SLAB.name(), 2);}}, Material.CHISELED_NETHER_BRICKS.name(), 1,1),
    NETHER_WART_BLOCK(new HashMap<String,Integer>(){{put(Material.NETHER_WART.name(), 9);}}, Material.NETHER_WART_BLOCK.name(), 1,1),
    QUARTZ_BLOCK(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 4);}}, Material.QUARTZ_BLOCK.name(), 1,1),
    CHISELED_QUARTZ_BLOCK(new HashMap<String,Integer>(){{put(Material.QUARTZ_SLAB.name(), 2);}}, Material.CHISELED_QUARTZ_BLOCK.name(), 1,1),
    QUARTZ_PILLAR(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 2);}}, Material.QUARTZ_PILLAR.name(), 1,1),
    QUARTZ_BRICK(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 4);}}, Material.QUARTZ_BRICKS.name(), 4,1),
    HAY_BALE(new HashMap<String,Integer>(){{put(Material.WHEAT.name(), 9);}}, Material.HAY_BLOCK.name(), 1,1),

    //Glass
    WHITE_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.BONE_MEAL.name(), 1);}}, Material.WHITE_STAINED_GLASS.name(), 8,1),
    RED_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.RED_DYE.name(), 1);}}, Material.RED_STAINED_GLASS.name(), 8,1),
    YELLOW_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.YELLOW_DYE.name(), 1);}}, Material.YELLOW_STAINED_GLASS.name(), 8,1),
    CYAN_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.CYAN_DYE.name(), 1);}}, Material.CYAN_STAINED_GLASS.name(), 8,1),
    PINK_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.PINK_DYE.name(), 1);}}, Material.PINK_STAINED_GLASS.name(), 8,1),
    PURPLE_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.PURPLE_DYE.name(), 1);}}, Material.PURPLE_STAINED_GLASS.name(), 8,1),
    MAGENTA_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.MAGENTA_DYE.name(), 1);}}, Material.MAGENTA_STAINED_GLASS.name(), 8,1),
    LIME_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.LIME_DYE.name(), 1);}}, Material.LIME_STAINED_GLASS.name(), 8,1),
    LIGHT_BLUE_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.LIGHT_BLUE_DYE.name(), 1);}}, Material.LIGHT_BLUE_STAINED_GLASS.name(), 8,1),
    ORANGE_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.ORANGE_DYE.name(), 1);}}, Material.ORANGE_STAINED_GLASS.name(), 8,1),
    GREEN_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.GREEN_DYE.name(), 1);}}, Material.GREEN_STAINED_GLASS.name(), 8,1),
    BROWN_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.BROWN_DYE.name(), 1);}}, Material.BROWN_STAINED_GLASS.name(), 8,1),
    BLACK_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.BLACK_DYE.name(), 1);}}, Material.BLACK_STAINED_GLASS.name(), 8,1),
    GRAY_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.GRAY_DYE.name(), 1);}}, Material.GRAY_STAINED_GLASS.name(), 8,1),
    LIGHT_GRAY_STAINED_GLASS(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 8);put(Material.LIGHT_GRAY_DYE.name(), 1);}}, Material.LIGHT_GRAY_STAINED_GLASS.name(), 8,1),

    //GLASS PANE
    WHITE_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.WHITE_STAINED_GLASS.name(), 6);}}, Material.WHITE_STAINED_GLASS_PANE.name(), 16,1),
    RED_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.RED_STAINED_GLASS.name(), 6);}}, Material.RED_STAINED_GLASS_PANE.name(), 16,1),
    YELLOW_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.YELLOW_STAINED_GLASS.name(), 6);}}, Material.YELLOW_STAINED_GLASS_PANE.name(), 16,1),
    CYAN_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.CYAN_STAINED_GLASS.name(), 6);}}, Material.CYAN_STAINED_GLASS_PANE.name(), 16,1),
    PINK_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.PINK_STAINED_GLASS.name(), 6);}}, Material.PINK_STAINED_GLASS_PANE.name(), 16,1),
    PURPLE_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.PURPLE_STAINED_GLASS.name(), 6);}}, Material.PURPLE_STAINED_GLASS_PANE.name(), 16,1),
    MAGENTA_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.MAGENTA_STAINED_GLASS.name(), 6);}}, Material.MAGENTA_STAINED_GLASS_PANE.name(), 16,1),
    LIME_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.LIME_STAINED_GLASS.name(), 6);}}, Material.LIME_STAINED_GLASS_PANE.name(), 16,1),
    LIGHT_BLUE_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.LIGHT_BLUE_STAINED_GLASS.name(), 6);}}, Material.LIGHT_BLUE_STAINED_GLASS_PANE.name(), 16,1),
    ORANGE_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.ORANGE_STAINED_GLASS.name(), 6);}}, Material.ORANGE_STAINED_GLASS_PANE.name(), 16,1),
    GREEN_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.GREEN_STAINED_GLASS.name(), 6);}}, Material.GREEN_STAINED_GLASS_PANE.name(), 16,1),
    BROWN_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.BROWN_STAINED_GLASS.name(), 6);}}, Material.BROWN_STAINED_GLASS_PANE.name(), 16,1),
    BLACK_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.BLACK_STAINED_GLASS.name(), 6);}}, Material.BLACK_STAINED_GLASS_PANE.name(), 16,1),
    GRAY_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.GRAY_STAINED_GLASS.name(), 6);}}, Material.GRAY_STAINED_GLASS_PANE.name(), 16,1),
    LIGHT_GRAY_STAINED_GLASS_PANE(new HashMap<String,Integer>(){{put(Material.LIGHT_GRAY_STAINED_GLASS.name(), 6);}}, Material.LIGHT_GRAY_STAINED_GLASS_PANE.name(), 16,1),

    //WOOL
    WHITE_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.WHITE_DYE.name(), 1);}}, Material.WHITE_WOOL.name(), 1,1),
    ORANGE_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.ORANGE_DYE.name(), 1);}}, Material.ORANGE_WOOL.name(), 1,1),
    MAGENTA_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.MAGENTA_DYE.name(), 1);}}, Material.MAGENTA_WOOL.name(), 1,1),
    LIGHT_BLUE_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.LIGHT_BLUE_DYE.name(), 1);}}, Material.LIGHT_BLUE_WOOL.name(), 1,1),
    YELLOW_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.YELLOW_DYE.name(), 1);}}, Material.YELLOW_WOOL.name(), 1,1),
    LIME_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.LIME_DYE.name(), 1);}}, Material.LIME_WOOL.name(), 1,1),
    LIGHT_GREEN_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.LIME_DYE.name(), 1);}}, Material.LIME_WOOL.name(), 1,1),
    PINK_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.PINK_DYE.name(), 1);}}, Material.PINK_WOOL.name(), 1,1),
    GRAY_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.GRAY_DYE.name(), 1);}}, Material.GRAY_WOOL.name(), 1,1),
    LIGHT_GRAY_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.LIGHT_GRAY_DYE.name(), 1);}}, Material.LIGHT_GRAY_WOOL.name(), 1,1),
    CYAN_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.CYAN_DYE.name(), 1);}}, Material.CYAN_WOOL.name(), 1,1),
    PURPLE_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.PURPLE_DYE.name(), 1);}}, Material.PURPLE_WOOL.name(), 1,1),
    BLUE_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.BLUE_DYE.name(), 1);}}, Material.BLUE_WOOL.name(), 1,1),
    BROWN_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.BROWN_DYE.name(), 1);}}, Material.BROWN_WOOL.name(), 1,1),
    GREEN_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.GREEN_DYE.name(), 1);}}, Material.GREEN_WOOL.name(), 1,1),
    RED_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.RED_DYE.name(), 1);}}, Material.RED_WOOL.name(), 1,1),
    BLACK_WOOL(new HashMap<String,Integer>(){{put("WOOL", 1);put(Material.BLACK_DYE.name(), 1);}}, Material.BLACK_WOOL.name(), 1,1),


    GRANITE(new HashMap<String,Integer>(){{put(Material.DIORITE.name(), 1);put(Material.QUARTZ.name(),1);}}, Material.GRANITE.name(), 1,1),
    ANDESITE(new HashMap<String,Integer>(){{put(Material.DIORITE.name(), 1);put(Material.COBBLESTONE.name(),1);}}, Material.ANDESITE.name(), 2,1),
    DIORITE(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 2);put(Material.COBBLESTONE.name(),2);}}, Material.DIORITE.name(), 2,1),
    POLISHED_GRANITE(new HashMap<String,Integer>(){{put(Material.GRANITE.name(), 4);}}, Material.POLISHED_GRANITE.name(), 4,1),
    POLISHED_ANDESITE(new HashMap<String,Integer>(){{put(Material.ANDESITE.name(), 4);}}, Material.POLISHED_ANDESITE.name(), 4,1),
    POLISHED_DIORITE(new HashMap<String,Integer>(){{put(Material.DIORITE.name(), 4);}}, Material.POLISHED_DIORITE.name(), 4,1),
    PRISMARINE(new HashMap<String,Integer>(){{put(Material.PRISMARINE_SHARD.name(), 4);}}, Material.PRISMARINE.name(), 1,1),
    PRISMARINE_BRICKS(new HashMap<String,Integer>(){{put(Material.PRISMARINE_SHARD.name(), 9);}}, Material.PRISMARINE_BRICKS.name(), 1,1),
    DARK_PRISMARINE(new HashMap<String,Integer>(){{put(Material.PRISMARINE_SHARD.name(), 8);put(Material.BLACK_DYE.name(), 1);}}, Material.DARK_PRISMARINE.name(), 1,1),
    SEA_LANTERN(new HashMap<String,Integer>(){{put(Material.PRISMARINE_SHARD.name(), 4);put(Material.PRISMARINE_CRYSTALS.name(), 5);}}, Material.SEA_LANTERN.name(), 1,1),
    COARSE_DIRT(new HashMap<String,Integer>(){{put(Material.DIRT.name(), 2);put(Material.GRAVEL.name(), 2);}}, Material.COARSE_DIRT.name(), 4,1),
    SLIME_BLOCK(new HashMap<String,Integer>(){{put(Material.SLIME_BALL.name(), 9);}}, Material.SLIME_BLOCK.name(), 1,1),
    MOSS_COBBLESTONE(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 1);put(Material.VINE.name(),1);}}, Material.MOSSY_COBBLESTONE.name(), 1,1),
    MOSSY_STONE_BRICK(new HashMap<String,Integer>(){{put(Material.STONE_BRICKS.name(), 1);put(Material.VINE.name(),1);}}, Material.MOSSY_STONE_BRICKS.name(), 1,1),
    PURPUR_BLOCK(new HashMap<String,Integer>(){{put(Material.POPPED_CHORUS_FRUIT.name(), 4);}}, Material.PURPUR_BLOCK.name(), 1,1),
    PURPUR_PILLAR(new HashMap<String,Integer>(){{put(Material.PURPUR_SLAB.name(), 2);}}, Material.PURPUR_PILLAR.name(), 1,1),
    MAGMA_BLOCK(new HashMap<String,Integer>(){{put(Material.MAGMA_CREAM.name(), 4);}}, Material.MAGMA_BLOCK.name(), 1,1),
    BONE_BLOCK(new HashMap<String,Integer>(){{put(Material.BONE_MEAL.name(), 9);}}, Material.BONE_BLOCK.name(), 1,1),
    DRIED_KELP_BLOCK(new HashMap<String,Integer>(){{put(Material.DRIED_KELP.name(), 9);}}, Material.DRIED_KELP_BLOCK.name(), 1,1),
    PACKED_ICE(new HashMap<String,Integer>(){{put(Material.ICE.name(), 9);}}, Material.PACKED_ICE.name(), 1,1),
    BLUE_ICE(new HashMap<String,Integer>(){{put(Material.PACKED_ICE.name(), 9);}}, Material.BLUE_ICE.name(), 1,1),
    HONEY_BLOCK(new HashMap<String,Integer>(){{put(Material.HONEY_BOTTLE.name(), 4);}}, Material.HONEY_BLOCK.name(), 1,1),
    HONEYCOMB_BLOCK(new HashMap<String,Integer>(){{put(Material.HONEYCOMB.name(), 4);}}, Material.HONEYCOMB_BLOCK.name(), 1,1),
    POLISHED_BASALT(new HashMap<String,Integer>(){{put(Material.BASALT.name(), 4);}}, Material.POLISHED_BASALT.name(), 4,1),
    POLISHED_BLACKSTONE(new HashMap<String,Integer>(){{put(Material.BLACKSTONE.name(), 4);}}, Material.POLISHED_BLACKSTONE.name(), 4,1),
    CHISELD_POLISHED_BLACKSTONE(new HashMap<String,Integer>(){{put(Material.POLISHED_BLACKSTONE_SLAB.name(), 2);}}, Material.CHISELED_POLISHED_BLACKSTONE.name(), 1,1),
    POLISHED_BLACKSTONE_BRICKS(new HashMap<String,Integer>(){{put(Material.POLISHED_BLACKSTONE.name(), 4);}}, Material.POLISHED_BLACKSTONE_BRICKS.name(), 4, 1),
    NETHERITE_BLOCK(new HashMap<String,Integer>(){{put(Material.NETHERITE_INGOT.name(), 9);}}, Material.NETHERITE_BLOCK.name(), 1,1),


    //HELMETS
    LEATHER_HELMET(new HashMap<String,Integer>(){{put(Material.LEATHER.name(), 5);}}, Material.LEATHER_HELMET.name(), 1,1),
    GOLDEN_HELMET(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 5);}}, Material.GOLDEN_HELMET.name(), 1,1),
    IRON_HELMET(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 5);}}, Material.IRON_HELMET.name(), 1,1),
    DIAMOND_HELMET(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 5);}}, Material.DIAMOND_HELMET.name(), 1,1),

    //CHESTPLATES
    LEATHER_CHESTPLATE(new HashMap<String,Integer>(){{put(Material.LEATHER.name(), 8);}}, Material.LEATHER_CHESTPLATE.name(), 1,1),
    GOLDEN_CHESTPLATE(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 8);}}, Material.GOLDEN_CHESTPLATE.name(), 1,1),
    IRON_CHESTPLATE(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 8);}}, Material.IRON_CHESTPLATE.name(), 1,1),
    DIAMOND_CHESTPLATE(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 8);}}, Material.DIAMOND_CHESTPLATE.name(), 1,1),

    //LEGGINGS
    LEATHER_LEGGINGS(new HashMap<String,Integer>(){{put(Material.LEATHER.name(), 7);}}, Material.LEATHER_LEGGINGS.name(), 1,1),
    GOLDEN_LEGGINGS(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 7);}}, Material.GOLDEN_LEGGINGS.name(), 1,1),
    IRON_LEGGINGS(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 7);}}, Material.IRON_LEGGINGS.name(), 1,1),
    DIAMOND_LEGGINGS(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 7);}}, Material.DIAMOND_LEGGINGS.name(), 1,1),

    //BOOTS
    LEATHER_BOOTS(new HashMap<String,Integer>(){{put(Material.LEATHER.name(), 4);}}, Material.LEATHER_BOOTS.name(), 1,1),
    GOLDEN_BOOTS(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 4);}}, Material.GOLDEN_BOOTS.name(), 1,1),
    IRON_BOOTS(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 4);}}, Material.IRON_BOOTS.name(), 1,1),
    DIAMOND_BOOTS(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 4);}}, Material.DIAMOND_BOOTS.name(), 1,1),

    //SWORDS
    STONE_SWORD(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 2);put(Material.STICK.name(), 1);}}, Material.STONE_SWORD.name(), 1,1),
    IRON_SWORD(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 2);put(Material.STICK.name(), 1);}}, Material.IRON_SWORD.name(), 1,1),
    DIAMOND_SWORD(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 2);put(Material.STICK.name(), 1);}}, Material.DIAMOND_SWORD.name(), 1,1),
    GOLDEN_SWORD(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 2);put(Material.STICK.name(), 1);}}, Material.GOLDEN_SWORD.name(), 1,1),
    WOODEN_SWORD(new HashMap<String,Integer>(){{put("PLANKS", 2);put(Material.STICK.name(), 1);}}, Material.WOODEN_SWORD.name(), 1,1),

    //AXES
    STONE_AXE(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 3);put(Material.STICK.name(), 2);}}, Material.STONE_AXE.name(), 1,1),
    IRON_AXE(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 3);put(Material.STICK.name(), 2);}}, Material.IRON_AXE.name(), 1,1),
    DIAMOND_AXE(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 3);put(Material.STICK.name(), 2);}}, Material.DIAMOND_AXE.name(), 1,1),
    GOLDEN_AXE(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 3);put(Material.STICK.name(), 2);}}, Material.GOLDEN_AXE.name(), 1,1),
    WOODEN_AXE(new HashMap<String,Integer>(){{put("PLANKS", 3);put(Material.STICK.name(), 2);}}, Material.WOODEN_AXE.name(), 1,1),

    //PICKAXES
    STONE_PICKAXE(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 3);put(Material.STICK.name(), 2);}}, Material.STONE_PICKAXE.name(), 1,1),
    IRON_PICKAXE(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 3);put(Material.STICK.name(), 2);}}, Material.IRON_PICKAXE.name(), 1,1),
    DIAMOND_PICKAXE(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 3);put(Material.STICK.name(), 2);}}, Material.DIAMOND_PICKAXE.name(), 1,1),
    GOLDEN_PICKAXE(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 3);put(Material.STICK.name(), 2);}}, Material.GOLDEN_PICKAXE.name(), 1,1),
    WOODEN_PICKAXE(new HashMap<String,Integer>(){{put("PLANKS", 3);put(Material.STICK.name(), 2);}}, Material.WOODEN_PICKAXE.name(), 1,1),

    //SHOVELS
    STONE_SHOVEL(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 1);put(Material.STICK.name(), 2);}}, Material.STONE_SHOVEL.name(), 1,1),
    IRON_SHOVEL(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 1);put(Material.STICK.name(), 2);}}, Material.IRON_SHOVEL.name(), 1,1),
    DIAMOND_SHOVEL(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 1);put(Material.STICK.name(), 2);}}, Material.DIAMOND_SHOVEL.name(), 1,1),
    GOLDEN_SHOVEL(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 1);put(Material.STICK.name(), 2);}}, Material.GOLDEN_SHOVEL.name(), 1,1),
    WOODEN_SHOVEL(new HashMap<String,Integer>(){{put("PLANKS", 1);put(Material.STICK.name(), 2);}}, Material.WOODEN_SHOVEL.name(), 1,1),

    //HOE
    STONE_HOE(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 2);put(Material.STICK.name(), 2);}}, Material.STONE_HOE.name(), 1,1),
    IRON_HOE(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 2);put(Material.STICK.name(), 2);}}, Material.IRON_HOE.name(), 1,1),
    DIAMOND_HOE(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 2);put(Material.STICK.name(), 2);}}, Material.DIAMOND_HOE.name(), 1,1),
    GOLDEN_HOE(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 2);put(Material.STICK.name(), 2);}}, Material.GOLDEN_HOE.name(), 1,1),
    WOODEN_HOE(new HashMap<String,Integer>(){{put("PLANKS", 2);put(Material.STICK.name(), 2);}}, Material.WOODEN_HOE.name(), 1,1),

    BOW(new HashMap<String,Integer>(){{put(Material.STICK.name(), 3);put(Material.STRING.name(), 3);}}, Material.BOW.name(), 1,1),
    ARROW(new HashMap<String,Integer>(){{put(Material.STICK.name(), 1);put(Material.FEATHER.name(), 1);put(Material.FLINT.name(),1);}}, Material.ARROW.name(), 4,1),
    SHIELD(new HashMap<String,Integer>(){{put("PLANKS", 6);put(Material.IRON_INGOT.name(), 1);}}, Material.SHIELD.name(), 1,1),
    SPECTRAL_ARROR(new HashMap<String,Integer>(){{put(Material.ARROW.name(), 1);put(Material.GLOWSTONE_DUST.name(), 4);}}, Material.SPECTRAL_ARROW.name(), 2,1),
    TURTLE_SHELL(new HashMap<String,Integer>(){{put(Material.SCUTE.name(), 5);}}, Material.TURTLE_HELMET.name(), 1,1),
    CROSSBOW(new HashMap<String,Integer>(){{put(Material.STICK.name(), 3);put(Material.STRING.name(), 2);put(Material.TRIPWIRE_HOOK.name(),1);put(Material.IRON_INGOT.name(),1);}}, Material.CROSSBOW.name(), 1,1),

    //FOOD
    CAKE(new HashMap<String,Integer>(){{put(Material.MILK_BUCKET.name(), 3);put(Material.SUGAR.name(), 2);put(Material.EGG.name(), 1);put(Material.WHEAT.name(),3);}}, Material.CAKE.name(), 1,1),
    BREAD(new HashMap<String,Integer>(){{put(Material.WHEAT.name(), 3);}}, Material.BREAD.name(), 1,1),
    COOKIE(new HashMap<String,Integer>(){{put(Material.WHEAT.name(), 2);put(Material.COCOA_BEANS.name(), 1);}}, Material.COOKIE.name(), 8,1),
    BOWL(new HashMap<String,Integer>(){{put("PLANKS", 3);}}, Material.BOWL.name(), 1,1),
    MUSHROOM_STEW(new HashMap<String,Integer>(){{put(Material.RED_MUSHROOM.name(), 1);put(Material.BROWN_MUSHROOM.name(), 1);put(Material.BOWL.name(), 1);}}, Material.MUSHROOM_STEW.name(), 1,1),
    GOLDEN_APPLE(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 8);put(Material.APPLE.name(), 1);}}, Material.GOLDEN_APPLE.name(), 1,1),
    MELON_BLOCK(new HashMap<String,Integer>(){{put(Material.MELON_SLICE.name(), 9);}}, Material.MELON.name(), 1,1),
    PUMPKIN_SEED(new HashMap<String,Integer>(){{put(Material.PUMPKIN.name(), 1);}}, Material.PUMPKIN_SEEDS.name(), 1,1),
    MELON_SEED(new HashMap<String,Integer>(){{put(Material.MELON_SLICE.name(), 1);}}, Material.MELON_SEEDS.name(), 1,1),
    SUGAR(new HashMap<String,Integer>(){{put(Material.SUGAR_CANE.name(), 1);}}, Material.SUGAR.name(), 1,1),
    GOLDEN_CARROT(new HashMap<String,Integer>(){{put(Material.GOLD_NUGGET.name(), 8);put(Material.CARROT.name(), 1);}}, Material.GOLDEN_CARROT.name(), 1,1),
    PUMPKIN_PIE(new HashMap<String,Integer>(){{put(Material.PUMPKIN.name(), 1);put(Material.SUGAR.name(), 1);put(Material.EGG.name(), 1);}}, Material.PUMPKIN_PIE.name(), 1,1),
    RABBIT_STEW(new HashMap<String,Integer>(){{put(Material.RABBIT.name(), 1);put(Material.BOWL.name(), 1);put(Material.CARROT.name(),1);put(Material.BAKED_POTATO.name(),1);put(Material.RED_MUSHROOM.name(),1);}}, Material.RABBIT_STEW.name(), 1,1),
    GLISTERING_MELON(new HashMap<String,Integer>(){{put(Material.MELON_SLICE.name(), 1);put(Material.GOLD_NUGGET.name(), 8);}}, Material.GLISTERING_MELON_SLICE.name(), 1,1),


    //MISC
    FISHING_ROD(new HashMap<String,Integer>(){{put(Material.STICK.name(), 3);put(Material.STICK.name(), 2);}}, Material.FISHING_ROD.name(), 1,1),
    FLINT_AND_STEEL(new HashMap<String,Integer>(){{put(Material.FLINT.name(), 1);put(Material.STICK.name(), 1);}}, Material.FLINT_AND_STEEL.name(), 1,1),
    SHEARS(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 2);}}, Material.SHEARS.name(), 1,1),
    BUCKET(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 3);}}, Material.BUCKET.name(), 1,1),
    CLOCK(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 4);put(Material.REDSTONE.name(), 1);}}, Material.CLOCK.name(), 1,1),
    COMPASS(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 4);put(Material.REDSTONE.name(), 1);}}, Material.COMPASS.name(), 1,1),
    MAP(new HashMap<String,Integer>(){{put(Material.COMPASS.name(), 1);put(Material.PAPER.name(), 8);}}, Material.MAP.name(), 1,1),


    //OTHER
    PAPER(new HashMap<String,Integer>(){{put(Material.SUGAR_CANE.name(), 3);}}, Material.PAPER.name(), 1,1),
    BOOK(new HashMap<String,Integer>(){{put(Material.PAPER.name(), 3);put(Material.LADDER.name(),1);}}, Material.BOOK.name(), 1,1),
    IRON_BARS(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 6);}}, Material.IRON_BARS.name(), 16,1),
    GOLD_INGOT(new HashMap<String,Integer>(){{put(Material.GOLD_NUGGET.name(), 9);}}, Material.GOLD_INGOT.name(), 1,1),
    GOLD_NUGGET(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 1);}}, Material.GOLD_NUGGET.name(), 9,1),
    EYE_OF_ENDER(new HashMap<String,Integer>(){{put(Material.ENDER_PEARL.name(), 1);put(Material.BLAZE_POWDER.name(), 1);}}, Material.ENDER_EYE.name(), 1,1),
    ENCHANTMENT_TABLE(new HashMap<String,Integer>(){{put(Material.DIAMOND.name(), 2);put(Material.OBSIDIAN.name(), 4);put(Material.BOOK.name(), 1);}}, Material.ENCHANTING_TABLE.name(), 1,1),
    FIRE_CHARGE(new HashMap<String,Integer>(){{put(Material.BLAZE_POWDER.name(), 1);put(Material.COAL.name(), 1);put(Material.GUNPOWDER.name(), 1);}}, Material.FIRE_CHARGE.name(), 3,1),
    ENDER_CHEST(new HashMap<String,Integer>(){{put(Material.ENDER_EYE.name(), 1);put(Material.OBSIDIAN.name(), 8);}}, Material.ENDER_CHEST.name(), 1,1),
    BEACON(new HashMap<String,Integer>(){{put(Material.NETHER_STAR.name(), 1);put(Material.OBSIDIAN.name(), 3);put(Material.GLASS.name(), 5);}}, Material.BEACON.name(), 1,1),
    ANVIL(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 4);put(Material.IRON_BLOCK.name(), 3);}}, Material.ANVIL.name(), 1,1),
    ITEM_FRAME(new HashMap<String,Integer>(){{put(Material.STICK.name(), 8);put(Material.LEATHER.name(), 1);}}, Material.ITEM_FRAME.name(), 1,1),
    FIREWORK(new HashMap<String,Integer>(){{put(Material.GUNPOWDER.name(), 1);put(Material.PAPER.name(), 1);}}, Material.FIREWORK_ROCKET.name(), 1,1),
    LEAD(new HashMap<String,Integer>(){{put(Material.STRING.name(), 4);put(Material.SLIME_BALL.name(), 1);}}, Material.LEAD.name(), 2,1),
    ARMOR_STAND(new HashMap<String,Integer>(){{put(Material.SMOOTH_STONE_SLAB.name(), 1);put(Material.STICK.name(), 6);}}, Material.ARMOR_STAND.name(), 1,1),
    END_ROD(new HashMap<String,Integer>(){{put(Material.BLAZE_ROD.name(), 1);put(Material.POPPED_CHORUS_FRUIT.name(), 1);}}, Material.END_ROD.name(), 4,1),
    END_CRYSTAL(new HashMap<String,Integer>(){{put(Material.GHAST_TEAR.name(), 1);put(Material.ENDER_EYE.name(), 1);put(Material.GLASS.name(), 7);}}, Material.END_CRYSTAL.name(), 1,1),
    IRON_NUGGET(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 1);}}, Material.IRON_NUGGET.name(), 9,1),
    IRON_INGOT(new HashMap<String,Integer>(){{put(Material.IRON_NUGGET.name(), 9);}}, Material.IRON_INGOT.name(), 1,1),
    CAMPFIRE(new HashMap<String,Integer>(){{put(Material.COAL.name(), 1);put(Material.STICK.name(), 3);put(Material.JUNGLE_LOG.name(), 3);}}, Material.CAMPFIRE.name(), 1,1),
    SOUL_CAMPFIRE(new HashMap<String,Integer>(){{put(Material.SOUL_SAND.name(), 1);put(Material.STICK.name(), 3);put(Material.JUNGLE_LOG.name(), 3);}}, Material.SOUL_CAMPFIRE.name(), 1,1),
    SCAFFOLDING(new HashMap<String,Integer>(){{put(Material.STICK.name(), 1);put(Material.BAMBOO.name(), 6);}}, Material.SCAFFOLDING.name(), 6,1),
    BARREL(new HashMap<String,Integer>(){{put(Material.OAK_PLANKS.name(), 6);put(Material.OAK_SLAB.name(), 2);}}, Material.BARREL.name(), 1,1),
    BLAST_FURNACE(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 5);put(Material.FURNACE.name(), 1);put(Material.SMOOTH_STONE.name(), 3);}}, Material.BLAST_FURNACE.name(), 1,1),
    SMOKER(new HashMap<String,Integer>(){{put(Material.FURNACE.name(), 1);put(Material.OAK_LOG.name(), 4);}}, Material.SMOKER.name(), 1,1),
    CARTOGRAPHY_TABLE(new HashMap<String,Integer>(){{put("PLANKS", 4);put(Material.PAPER.name(), 2);}}, Material.CARTOGRAPHY_TABLE.name(), 1,1),
    COMPOSTER(new HashMap<String,Integer>(){{put(Material.OAK_SLAB.name(), 7);}}, Material.COMPOSTER.name(), 1,1),
    FLETCHING_TABLE(new HashMap<String,Integer>(){{put("PLANKS", 4);put(Material.FLINT.name(), 2);}}, Material.FLETCHING_TABLE.name(), 1,1),
    SMITHING_TABLE(new HashMap<String,Integer>(){{put("PLANKS", 4);put(Material.IRON_INGOT.name(), 2);}}, Material.SMITHING_TABLE.name(), 1,1),
    STONECUTTER(new HashMap<String,Integer>(){{put(Material.STONE.name(), 3);put(Material.IRON_INGOT.name(), 1);}}, Material.STONECUTTER.name(), 1,1),
    GRINDSTONE(new HashMap<String,Integer>(){{put("PLANKS", 2);put(Material.STONE_SLAB.name(), 1);put(Material.STICK.name(), 2);}}, Material.GRINDSTONE.name(), 1,1),
    LANTERN(new HashMap<String,Integer>(){{put(Material.IRON_NUGGET.name(), 8);put(Material.TORCH.name(), 1);}}, Material.LANTERN.name(), 1,1),
    LECTERN(new HashMap<String,Integer>(){{put(Material.OAK_SLAB.name(), 4);put(Material.BOOKSHELF.name(), 1);}}, Material.LECTERN.name(), 1,1),
    LOOM(new HashMap<String,Integer>(){{put("PLANKS", 2);put(Material.STRING.name(), 2);}}, Material.LOOM.name(), 1,1),
    BEEHIVE(new HashMap<String,Integer>(){{put("PLANKS", 6);put(Material.HONEYCOMB.name(), 3);}}, Material.BEEHIVE.name(), 1,1),
    CHAIN(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 1);put(Material.IRON_NUGGET.name(), 2);}}, Material.CHAIN.name(), 1,1),
    NETHERITE_INGOT(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 4);put(Material.NETHERITE_SCRAP.name(), 4);}}, Material.NETHERITE_INGOT.name(), 1,1),
    SOUL_TORCH(new HashMap<String,Integer>(){{put(Material.SOUL_SAND.name(), 1);put(Material.STICK.name(), 1);put(Material.COAL.name(), 1);}}, Material.SOUL_TORCH.name(), 4,1),
    RESPAWN_ANCHOR(new HashMap<String,Integer>(){{put(Material.CRYING_OBSIDIAN.name(), 6);put(Material.GLOWSTONE.name(), 3);}}, Material.RESPAWN_ANCHOR.name(), 1,1),
    LODESTONE(new HashMap<String,Integer>(){{put(Material.CHISELED_STONE_BRICKS.name(), 8);put(Material.NETHERITE_INGOT.name(), 1);}}, Material.LODESTONE.name(), 1,1),

    //BREWING
    GLASS_BOTTLE(new HashMap<String,Integer>(){{put(Material.GLASS.name(), 3);}}, Material.GLASS_BOTTLE.name(), 1,1),
    CAULDRON(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 7);}}, Material.CAULDRON.name(), 1,1),
    BREWING_STAND(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 3);put(Material.BLAZE_ROD.name(), 1);}}, Material.BREWING_STAND.name(), 1,1),
    BLAZE_POWDER(new HashMap<String,Integer>(){{put(Material.BLAZE_ROD.name(), 1);}}, Material.BLAZE_POWDER.name(), 2,1),
    FERMENTED_SPIDER_EYE(new HashMap<String,Integer>(){{put(Material.SPIDER_EYE.name(), 1);put(Material.BROWN_MUSHROOM.name(), 1);put(Material.SUGAR.name(), 1);}}, Material.FERMENTED_SPIDER_EYE.name(), 1,1),
    MAGMA_CREAM(new HashMap<String,Integer>(){{put(Material.SLIME_BALL.name(), 1);put(Material.BLAZE_POWDER.name(), 1);}}, Material.MAGMA_CREAM.name(), 1,1),

    //CONCRET POWDER
    WHITE_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.WHITE_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.WHITE_CONCRETE_POWDER.name(), 8,1),
    ORANGE_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.ORANGE_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.ORANGE_CONCRETE_POWDER.name(), 8,1),
    MAGENTA_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.MAGENTA_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.MAGENTA_CONCRETE_POWDER.name(), 8,1),
    LIGHT_BLUE_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.LIGHT_BLUE_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.LIGHT_BLUE_CONCRETE_POWDER.name(), 8,1),
    YELLOW_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.YELLOW_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.YELLOW_CONCRETE_POWDER.name(), 8,1),
    LIME_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.LIME_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.LIME_CONCRETE_POWDER.name(), 8,1),
    PINK_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.PINK_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.PINK_CONCRETE_POWDER.name(), 8,1),
    GRAY_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.GRAY_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.GRAY_CONCRETE_POWDER.name(), 8,1),
    LIGHT_GRAY_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.LIGHT_GRAY_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.LIGHT_GRAY_CONCRETE_POWDER.name(), 8,1),
    CYAN_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.CYAN_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.CYAN_CONCRETE_POWDER.name(), 8,1),
    PURPLE_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.PURPLE_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.PURPLE_CONCRETE_POWDER.name(), 8,1),
    BLUE_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.BLUE_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.BLUE_CONCRETE_POWDER.name(), 8,1),
    BROWN_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.BROWN_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.BROWN_CONCRETE_POWDER.name(), 8,1),
    GREEN_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.GREEN_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.GREEN_CONCRETE_POWDER.name(), 8,1),
    RED_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.RED_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.RED_CONCRETE_POWDER.name(), 8,1),
    BLACK_CONCRETE_POWDER(new HashMap<String,Integer>(){{put(Material.BLACK_DYE.name(), 1);put(Material.SAND.name(), 4);put(Material.GRAVEL.name(), 4);}}, Material.BLACK_CONCRETE_POWDER.name(), 8,1),


    //CARPETS
    WHITE_CARPET(new HashMap<String,Integer>(){{put(Material.WHITE_WOOL.name(), 2);}}, Material.WHITE_CARPET.name(), 3,1),
    ORANGE_CARPET(new HashMap<String,Integer>(){{put(Material.ORANGE_WOOL.name(), 2);}}, Material.ORANGE_CARPET.name(), 3,1),
    MAGENTA_CARPET(new HashMap<String,Integer>(){{put(Material.MAGENTA_WOOL.name(), 2);}}, Material.MAGENTA_CARPET.name(), 3,1),
    LIGHT_BLUE_CARPET(new HashMap<String,Integer>(){{put(Material.LIGHT_BLUE_WOOL.name(), 2);}}, Material.LIGHT_BLUE_CARPET.name(), 3,1),
    YELLOW_CARPET(new HashMap<String,Integer>(){{put(Material.YELLOW_WOOL.name(), 2);}}, Material.YELLOW_CARPET.name(), 3,1),
    LIME_CARPET(new HashMap<String,Integer>(){{put(Material.LIME_WOOL.name(), 2);}}, Material.LIME_CARPET.name(), 3,1),
    PINK_CARPET(new HashMap<String,Integer>(){{put(Material.PINK_WOOL.name(), 2);}}, Material.PINK_CARPET.name(), 3,1),
    GRAY_CARPET(new HashMap<String,Integer>(){{put(Material.GRAY_WOOL.name(), 2);}}, Material.GRAY_CARPET.name(), 3,1),
    LIGHT_GRAY_CARPET(new HashMap<String,Integer>(){{put(Material.LIGHT_GRAY_WOOL.name(), 2);}}, Material.LIGHT_GRAY_CARPET.name(), 3,1),
    CYAN_CARPET(new HashMap<String,Integer>(){{put(Material.CYAN_WOOL.name(), 2);}}, Material.CYAN_CARPET.name(), 3,1),
    PURPLE_CARPET(new HashMap<String,Integer>(){{put(Material.PURPLE_WOOL.name(), 2);}}, Material.PURPLE_CARPET.name(), 3,1),
    BLUE_CARPET(new HashMap<String,Integer>(){{put(Material.BLUE_WOOL.name(), 2);}}, Material.BLUE_CARPET.name(), 3,1),
    BROWN_CARPET(new HashMap<String,Integer>(){{put(Material.BROWN_WOOL.name(), 2);}}, Material.BROWN_CARPET.name(), 3,1),
    GREEN_CARPET(new HashMap<String,Integer>(){{put(Material.GREEN_WOOL.name(), 2);}}, Material.GREEN_CARPET.name(), 3,1),
    RED_CARPET(new HashMap<String,Integer>(){{put(Material.RED_WOOL.name(), 2);}}, Material.RED_CARPET.name(), 3,1),
    BLACK_CARPET(new HashMap<String,Integer>(){{put(Material.BLACK_WOOL.name(), 2);}}, Material.BLACK_CARPET.name(), 3,1),



    //REDSTONE
    WOOD_PRESSURE_PLATES(new HashMap<String,Integer>(){{put("PLANKS", 2);}}, Material.OAK_PRESSURE_PLATE.name(), 1,1),
    STONE_PRESSURE_PLATES(new HashMap<String,Integer>(){{put(Material.STONE.name(), 2);}}, Material.STONE_PRESSURE_PLATE.name(), 1,1),
    IRON_PRESSURE_PLATES(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 2);}}, Material.HEAVY_WEIGHTED_PRESSURE_PLATE.name(), 1,1),
    GOLD_PRESSURE_PLATES(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 2);}}, Material.LIGHT_WEIGHTED_PRESSURE_PLATE.name(), 1,1),
    LEVER(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 1);put(Material.STICK.name(), 1);}}, Material.LEVER.name(), 1,1),
    WOOD_BUTTON(new HashMap<String,Integer>(){{put("PLANKS", 1);}}, Material.OAK_BUTTON.name(), 1,1),
    STONE_BUTTON(new HashMap<String,Integer>(){{put(Material.STONE.name(), 1);}}, Material.STONE_BUTTON.name(), 1,1),
    TRAPDOOR(new HashMap<String,Integer>(){{put("PLANKS", 1);}}, Material.OAK_TRAPDOOR.name(), 1,1),
    PISTON(new HashMap<String,Integer>(){{put("PLANKS", 3);put(Material.COBBLESTONE.name(), 4);put(Material.IRON_INGOT.name(), 1);put(Material.REDSTONE.name(), 1);}}, Material.PISTON.name(), 1,1),
    STICKY_PISTON(new HashMap<String,Integer>(){{put(Material.PISTON.name(), 4);put(Material.SLIME_BALL.name(), 1);}}, Material.STICKY_PISTON.name(), 1,1),
    REDSTONE_REPEATER(new HashMap<String,Integer>(){{put(Material.REDSTONE.name(), 1);put(Material.STONE.name(), 3);put(Material.REDSTONE_TORCH.name(), 2);}}, Material.REPEATER.name(), 1,1),
    DISPENSER(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 7);put(Material.BOW.name(), 1);put(Material.REDSTONE.name(), 1);}}, Material.DISPENSER.name(), 1,1),
    JUKEBOX(new HashMap<String,Integer>(){{put("PLANKS", 8);put(Material.DIAMOND.name(), 1);}}, Material.JUKEBOX.name(), 1,1),
    TRACK(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 6);put(Material.STICK.name(), 1);}}, Material.RAIL.name(), 16,1),
    POWERED_RAIL(new HashMap<String,Integer>(){{put(Material.GOLD_INGOT.name(), 6);put(Material.STICK.name(), 1);put(Material.REDSTONE.name(), 1);}}, Material.POWERED_RAIL.name(), 6,1),
    DETECTOR_RAIL(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 6);put(Material.STONE_PRESSURE_PLATE.name(), 1);put(Material.REDSTONE.name(), 1);}}, Material.DETECTOR_RAIL.name(), 6,1),
    ACTIVATOR_RAIL(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 6);put(Material.STICK.name(), 2);put(Material.REDSTONE_TORCH.name(), 1);}}, Material.ACTIVATOR_RAIL.name(), 6,1),
    REDSTONE_TORCH(new HashMap<String,Integer>(){{put(Material.REDSTONE.name(), 1);put(Material.STICK.name(), 1);}}, Material.REDSTONE_TORCH.name(), 1,1),
    REDSTONE_LAMP(new HashMap<String,Integer>(){{put(Material.REDSTONE.name(), 4);put(Material.GLOWSTONE.name(), 1);}}, Material.REDSTONE_LAMP.name(), 1,1),
    TRIPWIRE_HOOK(new HashMap<String,Integer>(){{put(Material.STICK.name(), 1);put(Material.IRON_INGOT.name(), 1);put("PLANKS", 1);}}, Material.TRIPWIRE_HOOK.name(), 2,1),
    DAYLIGHT_DETECTOR(new HashMap<String,Integer>(){{put(Material.OAK_SLAB.name(), 3);put(Material.QUARTZ.name(), 3);put(Material.GLASS.name(), 3);}}, Material.DAYLIGHT_DETECTOR.name(), 1,1),
    DROPPER(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(), 7);put(Material.REDSTONE.name(), 1);}}, Material.DROPPER.name(), 1,1),
    HOPPER(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 5);put(Material.CHEST.name(), 1);}}, Material.HOPPER.name(), 1,1),
    REDSTONE_COMPARATOR(new HashMap<String,Integer>(){{put(Material.QUARTZ.name(), 1);put(Material.STONE.name(), 3);put(Material.REDSTONE_TORCH.name(), 3);}}, Material.COMPARATOR.name(), 1,1),
    TRAPPED_CHEST(new HashMap<String,Integer>(){{put(Material.CHEST.name(), 1);put(Material.TRIPWIRE_HOOK.name(), 1);}}, Material.TRAPPED_CHEST.name(), 1,1),
    IRON_TRAPDOOR(new HashMap<String,Integer>(){{put(Material.IRON_INGOT.name(), 4);}}, Material.IRON_TRAPDOOR.name(), 1,1),
    OBSERVER(new HashMap<String,Integer>(){{put(Material.COBBLESTONE.name(),6);put(Material.REDSTONE.name(),2);put(Material.QUARTZ.name(),1);}}, Material.OBSERVER.name(), 1,1),
    TARGET(new HashMap<String,Integer>(){{put(Material.HAY_BLOCK.name(),1);put(Material.REDSTONE.name(),4);}}, Material.TARGET.name(), 1,1);














    public HashMap<String,Integer> ingredients;
    public String results;
    public int amount;
    public int time;

    CrafingRezept(HashMap<String,Integer> ingredients, String results,int amount, int time) {
        this.ingredients = ingredients;
        this.results = results;
        this.time = time;
        this.amount = amount;
    }

    public static CrafingRezept getRezept(Material name){
        for(CrafingRezept rezept : CrafingRezept.values()){
            if(rezept.results.equals(name.name())){
                return rezept;
            }
        }
        return null;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public String getResults() {
        return results;
    }

    public int getAmount() {
        return amount;
    }

    public int getTime() {
        return time;
    }

    public static CrafingRezept getByName(String name){
        for(CrafingRezept rezept : CrafingRezept.values()){
            if(rezept.name().equalsIgnoreCase(name)){
                return rezept;
            }
        }
        return null;
    }

    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

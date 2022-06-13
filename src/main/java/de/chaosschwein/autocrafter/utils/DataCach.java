package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.enums.Crafter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
public class DataCach {

    //region Crafter
    public static HashMap<Location, Crafter> crafter = new HashMap<>();



    public static Crafter getCrafter(Location loc){
        return crafter.get(loc);
    }

    public static boolean isCrafter(Location loc){
        return crafter.containsKey(loc);
    }

    public static void removeCrafter(Location loc){
        if(isCrafter(loc)){crafter.remove(loc);}
    }

    public static void addCrafter(Location loc){
        if(isCrafter(loc)){
            removeCrafter(loc);
        }
        Crafter c = new Crafter(loc).getAllData();
        if(c.isCrafter()) {
            crafter.put(loc, c);
        }
    }
    //endregion

    //region PLANKS
    public static HashMap<Material,String> PLANKS = new HashMap<Material,String>(){{
        put(Material.ACACIA_PLANKS,"PLANKS");
        put(Material.BIRCH_PLANKS,"PLANKS");
        put(Material.DARK_OAK_PLANKS,"PLANKS");
        put(Material.JUNGLE_PLANKS,"PLANKS");
        put(Material.OAK_PLANKS,"PLANKS");
        put(Material.SPRUCE_PLANKS,"PLANKS");

        //WOOL
        put(Material.WHITE_WOOL,"WOOL");
        put(Material.ORANGE_WOOL,"WOOL");
        put(Material.MAGENTA_WOOL,"WOOL");
        put(Material.LIGHT_BLUE_WOOL,"WOOL");
        put(Material.YELLOW_WOOL,"WOOL");
        put(Material.LIME_WOOL,"WOOL");
        put(Material.PINK_WOOL,"WOOL");
        put(Material.GRAY_WOOL,"WOOL");
        put(Material.LIGHT_GRAY_WOOL,"WOOL");
        put(Material.CYAN_WOOL,"WOOL");
        put(Material.PURPLE_WOOL,"WOOL");
        put(Material.BLUE_WOOL,"WOOL");
        put(Material.BROWN_WOOL,"WOOL");
        put(Material.GREEN_WOOL,"WOOL");
        put(Material.RED_WOOL,"WOOL");
        put(Material.BLACK_WOOL,"WOOL");

    }};
}

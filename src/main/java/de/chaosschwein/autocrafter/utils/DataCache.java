package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.types.Channel;
import de.chaosschwein.autocrafter.types.Crafter;
import de.chaosschwein.autocrafter.types.Receiver;
import org.bukkit.Location;

import java.util.HashMap;

public class DataCache {

    //region Crafter
    public static final HashMap<Location, Crafter> crafter = new HashMap<>();


    public static Crafter getCrafter(Location loc) {
        if (!isCrafter(loc)) {
            addCrafter(loc);
        }
        return crafter.get(loc);
    }

    public static boolean isCrafter(Location loc) {
        return crafter.containsKey(loc);
    }

    public static void removeCrafter(Location loc) {
        if (isCrafter(loc)) {
            crafter.remove(loc);
        }
    }

    public static void addCrafter(Location loc) {
        if (isCrafter(loc)) {
            removeCrafter(loc);
        }
        Crafter c = new Crafter(loc).getAllData();
        if (c.isCrafter()) {
            crafter.put(loc, c);
        }
    }

    //endregion
}

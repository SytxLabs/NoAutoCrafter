package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.enums.CraftingRezept;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.utils.DataCache;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CrafterFile {

    private final FileManager file;

    public CrafterFile() {
        this.file = new FileManager("data", "crafter");
    }

    public void save(Player p, Crafter crafter, CraftingRezept rezept) {
        if (crafter.dispenser == null) {
            return;
        }
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Crafter." + locstring + ".Rezept", rezept.toFileString());
        file.write("Crafter." + locstring + ".OwnerUUID", p.getUniqueId().toString());
    }

    public CraftingRezept getRezept(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return CraftingRezept.fromFileString(file.read("Crafter." + locstring + ".Rezept"));
    }

    public String getOwnerUUID(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return file.read("Crafter." + locstring + ".OwnerUUID");
    }

    public void delete(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Crafter." + locstring + ".Rezept", null);
        file.write("Crafter." + locstring + ".OwnerUUID", null);
        file.write("Crafter." + locstring, null);
    }

    public boolean contains(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return false;
        }
        return file.getConfig().contains("Crafter." + (loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ()));
    }

    public void cache() {
        DataCache.crafter.clear();
        if (file.getConfig().getConfigurationSection("Crafter") != null) {
            ConfigurationSection o = file.getConfig().getConfigurationSection("Crafter");
            if (o == null) {
                return;
            }
            for (String key : o.getKeys(false)) {
                String[] locString = key.split(",");
                Location loc = new Location(Bukkit.getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
                if (loc.getWorld() == null) {
                    continue;
                }
                Crafter crafter = new Crafter(loc).getAllData();
                if (crafter.dispenser == null) {
                    continue;
                }
                DataCache.crafter.put(loc, crafter);
            }
        }
    }
}

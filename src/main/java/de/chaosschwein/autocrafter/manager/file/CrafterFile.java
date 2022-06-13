package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.utils.DataCach;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CrafterFile {

    private String dir = "data";
    private String filename = "crafter";

    private FileManager file;

    public CrafterFile() {
        this.file = new FileManager(dir, filename);
    }

    public boolean save(Player p, Crafter crafter, CrafingRezept rezept) {
        if (crafter.dispenser == null) {
            return false;
        }
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Crafter." + locstring + ".Rezept", rezept.name());
        file.write("Crafter." + locstring + ".OwnerUUID", p.getUniqueId().toString());
        file.write("Crafter." + locstring + ".OwnerName", p.getName());
        file.write("Crafter." + locstring + ".CreatetAT", System.currentTimeMillis());
        return true;
    }

    public CrafingRezept getRezept(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return CrafingRezept.getByName((String) file.read("Crafter." + locstring + ".Rezept"));
    }

    public String getOwnerUUID(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return (String) file.read("Crafter." + locstring + ".OwnerUUID");
    }

    public String getOwnerName(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return (String) file.read("Crafter." + locstring + ".OwnerName");
    }

    public long getCreatetAT(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return 0;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return Long.parseLong(file.read("Crafter." + locstring + ".CreatetAT"));
    }

    public void delete(Crafter crafter) {
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Crafter." + locstring + ".Rezept", null);
        file.write("Crafter." + locstring + ".OwnerUUID", null);
        file.write("Crafter." + locstring + ".OwnerName", null);
        file.write("Crafter." + locstring + ".CreatetAT", null);
        file.write("Crafter." + locstring, null);
    }

    public boolean contains(Crafter crafter){
        Location loc = crafter.dispenser.getLocation();
        if (loc.getWorld() == null) {
            return false;
        }
        return file.getConfig().contains("Crafter." + (loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ()));
    }

    public void cache() {
        DataCach.crafter.clear();
        if (file.getConfig().getConfigurationSection("Crafter") != null) {
            for (String key : file.getConfig().getConfigurationSection("Crafter").getKeys(false)) {
                String[] locs = key.split(",");
                Location loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
                if (loc.getWorld() == null) {continue;}
                String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
                Crafter crafter = new Crafter(loc).getAllData();
                if (crafter.dispenser == null) {continue;}
                DataCach.crafter.put(loc, crafter);
            }
        }
    }
}

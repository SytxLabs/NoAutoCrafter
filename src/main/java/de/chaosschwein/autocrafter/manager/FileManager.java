package de.chaosschwein.autocrafter.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class FileManager {

    public String dir = "plugins/AutoCrafter/";
    public String filename = "config.yml";

    @SuppressWarnings("unused")
    public FileManager() {
    }

    public FileManager(String filename) {
        this.filename = filename + ".yml";
    }

    public FileManager(String dir, String filename) {
        this.dir = "plugins/AutoCrafter/" + dir + "/";
        this.filename = filename + ".yml";
    }

    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public File getFile() {
        return new File(dir, filename);
    }

    public void saveConfig(FileConfiguration config) {
        try {
            config.save(getFile());
        } catch (IOException ignored) {
            System.out.println("§c[AutoCrafter] Error while saving config file: " + filename);
        }
    }

    public String read(String key) {
        if (getConfig().get(key) instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString(key)));
        }
        return getConfig().getString(key);
    }

    public Object read(String key, boolean object) {
        if (object) {
            return getConfig().get(key);
        }
        if (getConfig().get(key) instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString(key)));
        }
        return getConfig().getString(key);
    }

    public void write(String key, Object value) {
        FileConfiguration cfg = getConfig();
        cfg.set(key, value);
        saveConfig(cfg);
    }

    public void writeDefault(HashMap<String, Object> defaultSet) {
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);
        for (String key : defaultSet.keySet()) {
            cfg.addDefault(key, defaultSet.get(key));
        }
        saveConfig(cfg);
    }

    public void remove(String key) {
        FileConfiguration cfg = getConfig();
        cfg.set(key, null);
        saveConfig(cfg);
    }

    public boolean contains(String key) {
        return getConfig().contains(key);
    }

    public String locToString(Location loc) {
        return Objects.requireNonNull(loc.getWorld()).getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ();
    }

    public Location stringToLoc(String locString) {
        String[] loc = locString.split(";");
        return new Location(Bukkit.getWorld(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]), Integer.parseInt(loc[3]));
    }
}

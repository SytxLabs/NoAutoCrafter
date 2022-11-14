package de.chaosschwein.autocrafter.manager;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Objects;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

    public String dir = "plugins/AutoCrafter/";
    public String filename = "config.yml";

    public FileManager(){}

    public FileManager(String filename) {
        this.filename = filename+".yml";
    }

    public FileManager(String dir, String filename) {
        this.dir = "plugins/AutoCrafter/"+dir+"/";
        this.filename = filename+".yml";
    }

    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public File getFile() {
        return new File(dir,filename);
    }

    public void saveConfig(FileConfiguration config) {
        try {
            config.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String key){
        if(getConfig().get(key) instanceof String){
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString(key)));
        }
        return getConfig().getString(key);
    }

    public Object read(String key, boolean objekt){
        if (objekt) {
            return getConfig().get(key);
        }
        if(getConfig().get(key) instanceof String){
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString(key)));
        }
        return getConfig().getString(key);
    }

    public void write(String key,Object value){
        FileConfiguration cfg = getConfig();
        cfg.set(key, value);
        saveConfig(cfg);
    }

    public void writeDefault(HashMap<String,Object> dset){
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);
        for(String key : dset.keySet()){
            cfg.addDefault(key,dset.get(key));
        }
        saveConfig(cfg);
    }
}

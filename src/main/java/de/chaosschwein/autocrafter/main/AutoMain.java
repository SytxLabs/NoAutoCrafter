package de.chaosschwein.autocrafter.main;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.listener.InventoryListener;
import de.chaosschwein.autocrafter.listener.dis;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.manager.file.CrafingRezeptFile;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public final class AutoMain extends JavaPlugin {

    public static FileManager config;
    public static String prefix = "&8[&aAutoCrafter§8] &7";
    public static AutoMain instand;
    @Override
    public void onEnable() {
        instand = this;
        config = new FileManager("config");
        config.writeDefault(new HashMap<String,Object>(){{
            put("prefix", "&8[&aAutoCrafter§8] &7");
        }});
        prefix = config.read("prefix");


        Bukkit.getPluginManager().registerEvents(new dis(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(),this);

        new CrafingRezeptFile().init();
        new CrafterFile().cache();
        getCommand("autocrafter").setExecutor(new AutoCommand());
        if(Bukkit.getPluginManager().isPluginEnabled(instand)) {
            Bukkit.getConsoleSender().sendMessage(prefix+"§a--------------------------");
            Bukkit.getConsoleSender().sendMessage(prefix+"§a");
            Bukkit.getConsoleSender().sendMessage(prefix+"§aAutoCraft wurde aktiviert");
            Bukkit.getConsoleSender().sendMessage(prefix+"§a");
            Bukkit.getConsoleSender().sendMessage(prefix+"§a--------------------------");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void reload(){
        new Message().send("§aAutoCrafter wird neugeladen!");
        Bukkit.getPluginManager().disablePlugin(instand);
        Bukkit.getPluginManager().enablePlugin(instand);
    }
}

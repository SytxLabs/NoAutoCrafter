package de.chaosschwein.autocrafter.main;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.cmd.ReceiverCommand;
import de.chaosschwein.autocrafter.cmd.SenderCommand;
import de.chaosschwein.autocrafter.listener.CrafterListener;
import de.chaosschwein.autocrafter.listener.InventoryListener;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.manager.file.Language;
import de.chaosschwein.autocrafter.manager.file.Permission;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public final class AutoMain extends JavaPlugin {

    public static final Language language = new Language();
    public static final Permission permission = new Permission();
    public static FileManager config;
    public static String prefix = "&8[&aAutoCrafter§8] &7";
    public static boolean crafter = true;
    public static boolean breaker = true;
    public static boolean placer = true;
    public static boolean transport = true;

    public static AutoMain instance;

    public static void reload() {
        new Message().send("§aAutoCrafter reloading!");
        Bukkit.getPluginManager().disablePlugin(instance);
        Bukkit.getPluginManager().enablePlugin(instance);
    }

    @Override
    public void onEnable() {
        instance = this;
        config = new FileManager("config");
        config.writeDefault(new HashMap<>() {{
            put("prefix", "&8[&aAutoCrafter§8] &7");
            put("crafter", true);
            put("breaker", true);
            put("placer", true);
            put("transport", true);
        }});
        prefix = config.read("prefix");
        crafter = (boolean) config.read("crafter", true);
        breaker = (boolean) config.read("breaker", true);
        placer = (boolean) config.read("placer", true);
        transport = (boolean) config.read("transport", true);

        Bukkit.getPluginManager().registerEvents(new CrafterListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);

        new CrafterFile().cache();
        Objects.requireNonNull(getCommand("autocrafter")).setExecutor(new AutoCommand());
        Objects.requireNonNull(getCommand("ac")).setExecutor(new AutoCommand());
        Objects.requireNonNull(getCommand("receiver")).setExecutor(new ReceiverCommand());
        Objects.requireNonNull(getCommand("rc")).setExecutor(new ReceiverCommand());
        Objects.requireNonNull(getCommand("sender")).setExecutor(new SenderCommand());
        Objects.requireNonNull(getCommand("sc")).setExecutor(new SenderCommand());


        if (Bukkit.getPluginManager().isPluginEnabled(instance)) {
            Bukkit.getConsoleSender().sendMessage(prefix + "§a--------------------------");
            Bukkit.getConsoleSender().sendMessage(prefix + "§a");
            Bukkit.getConsoleSender().sendMessage(prefix + "§aAutoCraft enabled!");
            Bukkit.getConsoleSender().sendMessage(prefix + "§a");
            Bukkit.getConsoleSender().sendMessage(prefix + "§a--------------------------");
        }

    }

    @Override
    public void onDisable() {
        if (Bukkit.getPluginManager().isPluginEnabled(instance)) {
            Bukkit.getConsoleSender().sendMessage(prefix + "§c--------------------------");
            Bukkit.getConsoleSender().sendMessage(prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(prefix + "§cAutoCraft disabled!");
            Bukkit.getConsoleSender().sendMessage(prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(prefix + "§c--------------------------");
        }
    }
}

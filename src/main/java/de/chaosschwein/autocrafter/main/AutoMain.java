package de.chaosschwein.autocrafter.main;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.cmd.ReceiverCommand;
import de.chaosschwein.autocrafter.cmd.SenderCommand;
import de.chaosschwein.autocrafter.listener.*;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.manager.file.*;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

import static de.chaosschwein.autocrafter.main.UpdateChecker.isVersionGreater;

public final class AutoMain extends JavaPlugin {

    public static Language language = new Language();
    public static Permission permission = new Permission();
    public static FarmingTypes farmingTypes = new FarmingTypes();
    public static Transporter transporter = new Transporter();
    public static FileManager config;
    public static String prefix = "&8[&aAutoCrafter§8] &7";
    public static boolean crafter = true;
    public static boolean breaker = true;
    public static boolean placer = true;
    public static boolean transport = true;
    public static boolean farming_station = true;
    public static boolean breeder = true;
    public static int breederRange = 3;
    public static boolean entityInteractor = true;

    public static AutoMain instance;

    public static void reload() {
        new Message().send("§aAutoCrafter reloading!");
        Bukkit.getPluginManager().disablePlugin(instance);
        Bukkit.getPluginManager().enablePlugin(instance);
        AutoMain.language = new Language();
        AutoMain.permission = new Permission();
        AutoMain.farmingTypes = new FarmingTypes();
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
            put("farming_station", true);
            put("entity_interactor", true);
            put("breeder.enable", true);
            put("breeder.range", 3);
        }});
        prefix = config.read("prefix");
        crafter = (boolean) config.read("crafter", true);
        breaker = (boolean) config.read("breaker", true);
        placer = (boolean) config.read("placer", true);
        transport = (boolean) config.read("transport", true);
        farming_station = (boolean) config.read("farming_station", true);
        entityInteractor = (boolean) config.read("entity_interactor", true);
        breeder = (boolean) config.read("breeder.enable", true);
        breederRange = (int) config.read("breeder.range", true);


        Bukkit.getPluginManager().registerEvents(new CrafterListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new FarmingStationListener(), this);
        Bukkit.getPluginManager().registerEvents(new TransporterListener(), this);
        Bukkit.getPluginManager().registerEvents(new BreederListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityInteractiorListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlacerListener(), this);

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

        new UpdateChecker(this, 113127).getVersion(version -> {
            if (isVersionGreater(version, this.getDescription().getVersion())) {
                Bukkit.getConsoleSender().sendMessage(prefix + "§cThere is a new update available ("+version+"): §6https://www.spigotmc.org/resources/autocrafter.113127/");
            } else {
                Bukkit.getConsoleSender().sendMessage(prefix + "Up to date!");
            }
        });

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

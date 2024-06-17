package de.chaosschwein.autocrafter.main;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.cmd.ChunkLoaderCommand;
import de.chaosschwein.autocrafter.cmd.ReceiverCommand;
import de.chaosschwein.autocrafter.cmd.SenderCommand;
import de.chaosschwein.autocrafter.listener.*;
import de.chaosschwein.autocrafter.manager.ChunkManager;
import de.chaosschwein.autocrafter.manager.file.*;
import de.chaosschwein.autocrafter.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static de.chaosschwein.autocrafter.main.UpdateChecker.isVersionGreater;

public final class AutoMain extends JavaPlugin {

    public static Config config;
    public static Language language;
    public static Permission permission;
    public static FarmingTypes farmingTypes;
    public static Transporter transporter;
    public static ChunkLoaderFile chunkLoaderFile;

    public static ChunkManager chunkManager;

    public static boolean hasUpdate = false;

    public static AutoMain instance;

    public static void reload() {
        new Message().send("§aAutoCrafter reloading!");
        Bukkit.getPluginManager().disablePlugin(instance);
        Bukkit.getPluginManager().enablePlugin(instance);
        AutoMain.config = new Config();
        AutoMain.language = new Language();
        AutoMain.permission = new Permission();
        AutoMain.farmingTypes = new FarmingTypes();
        AutoMain.transporter = new Transporter();
        AutoMain.chunkManager = new ChunkManager();
        AutoMain.chunkLoaderFile = new ChunkLoaderFile();
    }

    @Override
    public void onEnable() {
        instance = this;
        AutoMain.config = new Config();
        AutoMain.language = new Language();
        AutoMain.permission = new Permission();
        AutoMain.farmingTypes = new FarmingTypes();
        AutoMain.chunkLoaderFile = new ChunkLoaderFile();

        AutoMain.chunkManager = new ChunkManager();

        Bukkit.getPluginManager().registerEvents(new CrafterListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new FarmingStationListener(), this);
        Bukkit.getPluginManager().registerEvents(new TransporterListener(), this);
        Bukkit.getPluginManager().registerEvents(new BreederListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityInteractiorListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlacerListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChunkLoaderListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        new CrafterFile().cache();
        Objects.requireNonNull(getCommand("autocrafter")).setExecutor(new AutoCommand());
        Objects.requireNonNull(getCommand("ac")).setExecutor(new AutoCommand());
        Objects.requireNonNull(getCommand("receiver")).setExecutor(new ReceiverCommand());
        Objects.requireNonNull(getCommand("rc")).setExecutor(new ReceiverCommand());
        Objects.requireNonNull(getCommand("sender")).setExecutor(new SenderCommand());
        Objects.requireNonNull(getCommand("sc")).setExecutor(new SenderCommand());
        Objects.requireNonNull(getCommand("chunkloader")).setExecutor(new ChunkLoaderCommand());
        Objects.requireNonNull(getCommand("cl")).setExecutor(new ChunkLoaderCommand());



        if (Bukkit.getPluginManager().isPluginEnabled(instance)) {
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§a--------------------------");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§a");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§aAutoCraft enabled!");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§a");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§a--------------------------");
        } else {
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c--------------------------");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§cAutoCraft failed to enable!");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c--------------------------");
        }

        new UpdateChecker(this, 113127).getVersion(version -> {
            if (isVersionGreater(version, this.getDescription().getVersion())) {
                Bukkit.getConsoleSender().sendMessage(config.prefix + "§cThere is a new update available ("+version+"): §6https://www.spigotmc.org/resources/autocrafter.113127/");
            } else {
                Bukkit.getConsoleSender().sendMessage(config.prefix + "Up to date!");
            }
        });
        transporter = new Transporter();
    }

    @Override
    public void onDisable() {
        if (Bukkit.getPluginManager().isPluginEnabled(instance)) {
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c--------------------------");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§cAutoCraft disabled!");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c");
            Bukkit.getConsoleSender().sendMessage(config.prefix + "§c--------------------------");
            chunkManager.disableChunkLoad();
        }
    }
}

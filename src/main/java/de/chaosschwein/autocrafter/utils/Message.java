package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

@SuppressWarnings("unused")
public class Message {
    private CommandSender player = null;

    public Message(Player player) {
        this.player = player;
    }

    public Message() {
    }

    public Message(CommandSender player) {
        this.player = player;
    }

    public static void send(CommandSender player, String message) {
        Objects.requireNonNullElseGet(player, Bukkit::getConsoleSender).sendMessage(ChatColor.translateAlternateColorCodes('&', AutoMain.prefix + message));
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', AutoMain.prefix + message));
    }

    public void send(String message) {
        Objects.requireNonNullElseGet(player, Bukkit::getConsoleSender).sendMessage(ChatColor.translateAlternateColorCodes('&', AutoMain.prefix + message));
    }

    public void noPermission() {
        send(AutoMain.language.noPermission);
    }
}

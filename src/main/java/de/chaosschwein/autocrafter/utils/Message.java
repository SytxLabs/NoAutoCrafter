package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Message {


    private CommandSender player = null;
    public Message(Player player){
        this.player = player;
    }
    public Message(){}
    public Message(CommandSender player) {
        this.player = player;
    }

    public void send(String message) {
        if(player != null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoMain.prefix+message));
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',AutoMain.prefix+message));
        }
    }

    public static void send(CommandSender player, String message) {
        if(player != null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoMain.prefix+message));
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',AutoMain.prefix+message));
        }
    }

    public void noPermission() {
        send(AutoMain.language.noPermission);
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',AutoMain.prefix+message));
    }
}

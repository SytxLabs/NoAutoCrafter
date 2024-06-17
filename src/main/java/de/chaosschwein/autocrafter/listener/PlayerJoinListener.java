package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (AutoMain.hasUpdate) {
            if (player.isOp() || Utils.hasPermission(player, AutoMain.permission.CrafterAdmin)) {
                Message msg = new Message(player);
                msg.send(AutoMain.language.NewVersionFound);
                msg.send("&ahttps://www.spigotmc.org/resources/noautocrafter.113127/");
            }
        }
    }
}

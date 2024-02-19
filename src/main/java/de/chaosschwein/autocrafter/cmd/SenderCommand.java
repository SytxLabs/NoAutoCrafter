package de.chaosschwein.autocrafter.cmd;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.InventoryCreator;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SenderCommand implements CommandExecutor {

    public static final HashMap<Player, Block> sender = new HashMap<>();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (AutoCommand.reload(sender, args)) {
                return true;
            }
            if (!AutoMain.transport) {
                return true;
            }
            if (!Utils.hasPermission((Player) sender, AutoMain.permission.SenderCreate)) {
                return true;
            }
            Block block = Utils.getTargetBlock(p, 5);
            Message msg = new Message(p);
            if (new CheckBlocks(block).isSender()) {
                if (!AutoMain.transporter.isSender(block.getLocation())) {
                    SenderCommand.sender.put(p, block);
                    new InventoryCreator(p).openSender();
                } else {
                    msg.send(AutoMain.language.SenderAlreadyExists);
                }
            } else {
                msg.send(AutoMain.language.SenderIsFalse);
            }

            return true;
        }
        return false;
    }
}

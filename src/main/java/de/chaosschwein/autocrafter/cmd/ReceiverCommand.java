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

public class ReceiverCommand implements CommandExecutor {

    public static final HashMap<Player, Block> receiver = new HashMap<>();

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
            if (!Utils.hasPermission((Player) sender, AutoMain.permission.ReceiverCreate)) {
                return true;
            }
            Block block = Utils.getTargetBlock(p, 5);
            Message msg = new Message(p);
            if (new CheckBlocks(block).isReceiver()) {
                if (!AutoMain.transporter.isReceiver(block.getLocation())) {
                    receiver.put(p, block);
                    new InventoryCreator(p).openChannelViewer();
                } else {
                    msg.send(AutoMain.language.ReceiverAlreadyExists);
                }
            } else {
                msg.send(AutoMain.language.ReceiverIsFalse);
            }

            return true;
        }
        return false;
    }
}

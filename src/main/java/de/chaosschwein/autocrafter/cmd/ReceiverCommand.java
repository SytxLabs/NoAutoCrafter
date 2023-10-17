package de.chaosschwein.autocrafter.cmd;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.Transporter;
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

    public static HashMap<Player, Block> receiver = new HashMap<>();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!Utils.hasPermission((Player) sender, AutoMain.permission.CrafterAdmin)) {
                    return true;
                }
                (new Message(sender)).send(AutoMain.language.reload);
                AutoMain.reload();
                return true;
            }
            if(!AutoMain.crafter) {
                return true;
            }
            if (!Utils.hasPermission((Player) sender, AutoMain.permission.ReceiverCreate)) {
                return true;
            }
            Player p = (Player) sender;
            Block block = Utils.getTargetBlock(p,5);
            Message msg = new Message(p);
            if(new CheckBlocks(block).isReceiver()) {
                if(!new Transporter().containsReceiver(block.getLocation())) {
                    receiver.put(p, block);
                    new InventoryCreator(p).openReceiver();
                }else{
                    msg.send(AutoMain.language.ReceiverAlreadyExists);
                }
            }else{
                msg.send(AutoMain.language.ReceiverIsFalse);
            }

            return true;
        }
        return false;
    }
}

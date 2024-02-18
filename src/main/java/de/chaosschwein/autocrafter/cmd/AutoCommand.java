package de.chaosschwein.autocrafter.cmd;

import de.chaosschwein.autocrafter.types.Crafter;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
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

public class AutoCommand implements CommandExecutor {

    public static final HashMap<Player, Crafter> crafter = new HashMap<>();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!Utils.hasPermission((Player) sender, AutoMain.permission.CrafterAdmin)) {
                    return true;
                }
                (new Message(sender)).send(AutoMain.language.reload);
                AutoMain.reload();
                return true;
            }
            if (!AutoMain.crafter) {
                return true;
            }
            if (!Utils.hasPermission((Player) sender, AutoMain.permission.CrafterCreate)) {
                return true;
            }
            Block block = Utils.getTargetBlock(p, 5);
            Message msg = new Message(p);
            if (new CheckBlocks(block).isCrafter()) {
                Crafter c = new Crafter(block.getLocation()).getCrafter();
                if (!new CrafterFile().contains(c)) {
                    crafter.put(p, c);
                    new InventoryCreator(p).open();
                } else {
                    msg.send(AutoMain.language.CrafterAlreadyExists);
                }
            } else {
                msg.send(AutoMain.language.CrafterIsFalse);
            }

            return true;
        }
        return false;
    }
}

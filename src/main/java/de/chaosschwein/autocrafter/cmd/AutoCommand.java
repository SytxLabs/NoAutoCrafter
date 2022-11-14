package de.chaosschwein.autocrafter.cmd;

import de.chaosschwein.autocrafter.enums.Crafter;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.InventoryCreator;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
public class AutoCommand implements CommandExecutor {

    public static HashMap<Player, Crafter> crafter = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(!AutoMain.crafter) {
                return true;
            }
            Player p = (Player) sender;
            Block block = Utils.getTargetBlock(p,5);
            Message msg = new Message(p);
            if(new CheckBlocks(block).isCrafter()) {
                Crafter c = new Crafter(block.getLocation()).getCrafter();
                if(!new CrafterFile().contains(c)) {
                    crafter.put(p, c);
                    new InventoryCreator(p).open();
                    msg.send("§7Dieser Block ist ein Crafter!");
                }else{
                    msg.send("§cDieser Crafter ist bereits registriert!");
                }
            }else{
                msg.send("§7Dieser Block ist kein Crafter!");
            }

            return true;
        }
        return false;
    }
}

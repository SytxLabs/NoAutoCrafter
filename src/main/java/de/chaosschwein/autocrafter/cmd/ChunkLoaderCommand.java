package de.chaosschwein.autocrafter.cmd;

import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.ChunkLoader;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChunkLoaderCommand implements CommandExecutor {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (AutoCommand.reload(sender, args)) {
                return true;
            }
            if (!AutoMain.config.chunkLoader) {
                return true;
            }
            if (!Utils.hasPermission((Player) sender, AutoMain.permission.ChunkLoaderCreate)) {
                return true;
            }
            Block block = Utils.getTargetBlock(p, 5);
            Message msg = new Message(p);
            if (new CheckBlocks(block).isChunkLoader()) {
                ChunkLoader cl = new ChunkLoader(block);
                if (cl.isChunkLoader() && !AutoMain.chunkLoaderFile.containsChunk(cl)) {
                    AutoMain.chunkLoaderFile.addChunk(cl);
                    cl.enableChunkLoader();
                    msg.send(AutoMain.language.ChunkLoaderCreated);
                } else {
                    msg.send(AutoMain.language.ChunkLoaderAlreadyExists);
                }
            } else {
                msg.send(AutoMain.language.ChunkLoaderIsFalse);
            }
        }
        return true;
    }
}

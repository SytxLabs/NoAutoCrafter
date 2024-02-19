package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class Receiver implements Comparable<Receiver> {
    public Location location = null;

    private Block chest = null;

    private Channel channel = null;
    public boolean isReceiver = false;

    private int idInChannel = 0;


    public Receiver(Location chest, Channel channel, int idInChannel) {
        if (!(new CheckBlocks(chest.getBlock())).isReceiver()) {
            return;
        }

        this.location = chest;
        this.chest = chest.getBlock();
        this.channel = channel;
        this.idInChannel = idInChannel;
        this.isReceiver = true;
    }

    public Block getChest() {
        return chest;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getIdInChannel() {
        return idInChannel;
    }

    @Override
    public int compareTo(Receiver o) {
        return Integer.compare(this.idInChannel, o.idInChannel);
    }
}

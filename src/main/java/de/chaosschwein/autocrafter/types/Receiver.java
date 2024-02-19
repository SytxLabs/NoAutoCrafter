package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.utils.CheckBlocks;
import org.bukkit.Location;
import org.bukkit.block.Chest;

public class Receiver implements Comparable<Receiver> {

    private Chest chest = null;

    private Channel channel = null;
    public boolean isReceiver = false;

    private int idInChannel = 0;


    public Receiver(Location chest, Channel channel, int idInChannel) {
        if (!(new CheckBlocks(chest.getBlock())).isReceiver()) {
            return;
        }
        this.chest = (Chest) chest.getBlock().getState();
        this.channel = channel;
        this.idInChannel = idInChannel;
        this.isReceiver = true;
    }

    public Receiver(Location chest, Channel channel) {
        Receiver last = channel.getReceivers().stream().sorted().toList().get(channel.getReceivers().size() - 1);
        new Receiver(chest, channel, last.getIdInChannel() + 1);
    }

    public Chest getChest() {
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

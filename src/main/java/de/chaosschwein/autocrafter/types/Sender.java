package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.enums.SenderType;
import de.chaosschwein.autocrafter.utils.CheckBlocks;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Sender {

    private Chest chest = null;


    private SenderType type = null;
    private Channel channel = null;
    public boolean isSender = false;
    private int lastIdFromChannel = -1;


    public Sender(Location chest, SenderType type, Channel channel) {
        if (!(new CheckBlocks(chest.getBlock())).isSender()) {
            return;
        }
        this.chest = (Chest) chest.getBlock().getState();
        this.type = type;
        this.channel = channel;
        this.isSender = true;
    }

    public Chest getChest() {
        return chest;
    }

    public SenderType getType() {
        return type;
    }

    public Channel getChannel() {
        return channel;
    }

    public boolean moveItems(ItemStack item) {
        List<Receiver> receivers = channel.getReceivers();
        Receiver receiver;
        if (receivers.size() < 2) {
            if (receivers.isEmpty()) {
                return true;
            }
            receiver = receivers.get(0);
        } else {
            receiver = switch (type) {
                case Random -> receivers.get((new Random()).nextInt(0, receivers.size()));
                case Overflow -> receivers.stream().filter(r -> !Utils.hasNotEnoughPlace(((Chest)r.getChest().getState()).getBlockInventory(), item)).sorted().toList().get(0);
                case RoundRobin -> getRoundRobinReceiver(item);
            };
        }
        if (receiver == null || Utils.hasNotEnoughPlace(((Chest)receiver.getChest().getState()).getBlockInventory(), item)) {
            return true;
        }
        ((Chest)receiver.getChest().getState()).getBlockInventory().addItem(item);
        receiver.getChest().getState().update();
        ((Chest)receiver.getChest().getState()).getBlockInventory().getViewers().forEach(player -> {
            if (player instanceof Player) {
                try {
                    //noinspection UnstableApiUsage
                    ((Player) player).updateInventory();
                } catch (Exception ignored) {
                }
            }
        });
        return false;
    }

    private Receiver getRoundRobinReceiver(ItemStack item) {
        Receiver receiver;
        List<Receiver> receivers = channel.getReceivers();
        if (lastIdFromChannel >= receivers.size() + 1) {
            lastIdFromChannel = 1;
        }
        List<Receiver> validReceiver = receivers.stream().filter(r -> !Utils.hasNotEnoughPlace(((Chest)r.getChest().getState()).getBlockInventory(), item)).sorted().toList();
        Optional<Receiver> or; Receiver receiver1;
        if (validReceiver.stream().noneMatch(r -> r.getIdInChannel() == lastIdFromChannel)) {
            or = validReceiver.stream().findFirst();
        } else {
            or = validReceiver.stream().filter(r -> r.getIdInChannel() == lastIdFromChannel).findFirst();
        }
        receiver1 = validReceiver.get(0);
        receiver = or.orElse(receiver1);
        lastIdFromChannel = receiver.getIdInChannel() + 1;
        return receiver;
    }

    public boolean hasNotValidReceivers(ItemStack item) {
        List<Receiver> receivers = channel.getReceivers();
        if (receivers.isEmpty()) {
            return true;
        }
        return receivers.stream().filter(r -> !Utils.hasNotEnoughPlace(((Chest)r.getChest().getState()).getBlockInventory(), item)).toList().isEmpty();
    }
}

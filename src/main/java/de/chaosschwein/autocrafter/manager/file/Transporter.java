package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.enums.ChannelType;
import de.chaosschwein.autocrafter.manager.FileManager;
import de.chaosschwein.autocrafter.types.Channel;
import de.chaosschwein.autocrafter.types.Receiver;
import de.chaosschwein.autocrafter.types.Sender;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class Transporter {

    public final FileManager file;
    public final Map<String, Channel> channels = new HashMap<>();
    public final Map<Location, Sender> senders = new HashMap<>();
    public final Map<Location, Receiver> receivers = new HashMap<>();

    public Transporter() {
        this.file = new FileManager("data", "transporter");

        ConfigurationSection section = file.getConfig().getConfigurationSection("Channel");
        if (section != null) {
            for (String hash : section.getKeys(false)) {
                Channel channel = getChannel(hash);
                if (channel != null) {
                    channels.put(hash, channel);
                }
            }
        }
        section = file.getConfig().getConfigurationSection("Receiver");
        if (section != null) {
            for (String locString : section.getKeys(false)) {
                Location loc = file.stringToLoc(locString);
                if (loc != null) {
                    Channel channel = channels.get(file.read("Receiver." + locString + ".Channel"));
                    Receiver receiver = new Receiver(loc, channel, Integer.parseInt(file.read("Receiver." + locString + ".IdInChannel")));
                    if (receiver.isReceiver) {
                        receivers.put(loc, receiver);
                        channel.addReceiver(receiver);
                    }
                }
            }
        }

        section = file.getConfig().getConfigurationSection("Sender");
        if (section != null) {
            for (String locString : section.getKeys(false)) {
                Location loc = file.stringToLoc(locString);
                if (loc != null) {
                    Channel channel = channels.get(file.read("Sender." + locString + ".Channel"));
                    Sender sender = new Sender(loc, channel);
                    if (sender.isSender) {
                        senders.put(loc, sender);
                        channel.addSender(sender);
                    }
                }
            }
        }
    }

    public boolean addChannel(Channel channel) {
        String hash = channel.getHash(true);
        file.write("Channel." + hash + ".Type", channel.type().toString());
        file.write("Channel." + hash + ".Material", channel.material().toString());
        file.write("Channel." + hash + ".Name", channel.name());
        file.write("Channel." + hash + ".OwnerUUID", channel.ownerUUID());
        file.write("Channel." + hash + ".Password", Utils.hash(channel.password()));
        file.write("Channel." + hash + ".Users", channel.getUsers());
        channels.put(hash, channel);
        return true;
    }

    public boolean removeChannel(Channel channel) {
        String hash = channel.getHash();
        file.remove("Channel." + hash + ".Type");
        file.remove("Channel." + hash + ".Material");
        file.remove("Channel." + hash + ".Name");
        file.remove("Channel." + hash + ".OwnerUUID");
        file.remove("Channel." + hash + ".Password");
        file.remove("Channel." + hash + ".Users");
        file.remove("Channel." + hash);
        channels.remove(hash);
        return true;
    }

    public Channel getChannel(String hash) {
        if (!file.contains("Channel." + hash)) return null;
        Channel channel = new Channel(
                ChannelType.valueOf(file.read("Channel." + hash + ".Type")),
                Material.getMaterial(file.read("Channel." + hash + ".Material")),
                file.read("Channel." + hash + ".Name"),
                file.read("Channel." + hash + ".OwnerUUID"),
                file.read("Channel." + hash + ".Password")
        );
        List<String> users = file.getConfig().getStringList("Channel." + hash + ".Users");
        for (String user : users) {
            channel.addUser(user);
        }
        for (Sender sender : senders.values()) {
            if (Objects.equals(sender.getChannel().getHash(), hash))
                channel.addSender(sender);
        }
        for (Receiver receiver : receivers.values()) {
            if (Objects.equals(receiver.getChannel().getHash(), hash))
                channel.addReceiver(receiver);
        }



        if (channels.containsKey(hash)) {
            channels.replace(hash, channel);
        } else {
            channels.put(hash, channel);
        }
        return channel;
    }

    public boolean addSender(Sender sender) {
        if (!sender.isSender) return false;
        Location loc = sender.getChest().getLocation();
        String locString = file.locToString(loc);
        file.write("Sender." + locString + ".Type", sender.getType().toString());
        file.write("Sender." + locString + ".Channel", sender.getChannel().getHash());
        senders.put(loc, sender);
        Channel channel = channels.get(sender.getChannel().getHash());
        if (channel != null) {
            channel.addSender(sender);
        }
        return true;
    }

    public void removeSender(Sender sender) {
        if (!sender.isSender) return;
        Location loc = sender.getChest().getLocation();
        String locString = file.locToString(loc);
        file.remove("Sender." + locString + ".Type");
        file.remove("Sender." + locString + ".PolicyType");
        file.remove("Sender." + locString + ".Channel");
        file.remove("Sender." + locString);
        senders.remove(loc);
        Channel channel = channels.get(sender.getChannel().getHash());
        if (channel != null) {
            channel.removeSender(sender);
        }
    }

    public void removeSender(Location loc) {
        if (!isSender(loc)) return;
        String locString = file.locToString(loc);
        file.remove("Sender." + locString + ".Type");
        file.remove("Sender." + locString + ".PolicyType");
        file.remove("Sender." + locString + ".Channel");
        file.remove("Sender." + locString);
        senders.remove(loc);
        Channel channel = channels.get(senders.get(loc).getChannel().getHash());
        if (channel != null) {
            channel.removeSender(senders.get(loc));
        }
    }

    public void getSendersByChannel(Channel channel) {
        for (Sender sender : senders.values()) {
            if (Objects.equals(sender.getChannel().getHash(), channel.getHash())) {
                channel.addSender(sender);
            }
        }
    }

    public boolean addReceiver(Receiver receiver) {
        if (!receiver.isReceiver) return false;
        String locString = file.locToString(receiver.getChest().getLocation());
        file.write("Receiver." + locString + ".Channel", receiver.getChannel().getHash());
        file.write("Receiver." + locString + ".IdInChannel", receiver.getIdInChannel());
        receivers.put(receiver.getChest().getLocation(), receiver);
        Channel channel = channels.get(receiver.getChannel().getHash());
        if (channel != null) {
            channel.addReceiver(receiver);
        }
        return true;
    }

    public boolean removeReceiver(Receiver receiver) {
        if (!receiver.isReceiver) return false;
        String locString = file.locToString(receiver.getChest().getLocation());
        file.remove("Receiver." + locString + ".Channel");
        file.remove("Receiver." + locString + ".IdInChannel");
        file.remove("Receiver." + locString);
        receivers.remove(receiver.getChest().getLocation());
        Channel channel = channels.get(receiver.getChannel().getHash());
        if (channel != null) {
            channel.removeReceiver(receiver);
        }
        return true;
    }

    public void removeReceiver(Location loc) {
        if (!isReceiver(loc)) return;
        String locString = file.locToString(loc);
        file.remove("Receiver." + locString + ".Channel");
        file.remove("Receiver." + locString);
        receivers.remove(loc);
        Channel channel = channels.get(receivers.get(loc).getChannel().getHash());
        if (channel != null) {
            channel.removeReceiver(receivers.get(loc));
        }
    }

    public boolean isSender(Location loc) {
        return file.contains("Sender." + file.locToString(loc));
    }

    public boolean isReceiver(Location loc) {
        return file.contains("Receiver." + file.locToString(loc));
    }
}

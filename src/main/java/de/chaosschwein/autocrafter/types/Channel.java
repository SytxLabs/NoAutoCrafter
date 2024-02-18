package de.chaosschwein.autocrafter.types;

import de.chaosschwein.autocrafter.enums.ChannelType;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    public final ChannelType type;
    public final Material material;
    public final String name;
    public final String ownerUUID;
    public final String password;

    private List<Sender> senders = new ArrayList<>();
    private List<Receiver> receivers = new ArrayList<>();

    public Channel(ChannelType type, Material material, String name, String ownerUUID, String password) {
        this.type = type;
        this.material = material;
        this.name = name;
        this.ownerUUID = ownerUUID;
        this.password = password;
    }

    public static Channel PrivateChannel(ChannelType type, Material material, String name, String ownerUUID) {
        return new Channel(type, material, name, ownerUUID, "");
    }

    public static Channel PublicChannel(ChannelType type, Material material, String name) {
        return new Channel(type, material, name, "", "");
    }

    public static Channel ProtectedChannel(ChannelType type, Material material, String name, String password) {
        return new Channel(type, material, name, "", Utils.hash(password));
    }

    public List<Sender> getSenders() {
        return senders;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public ChannelType type() {
        return type;
    }

    public Material material() {
        return material;
    }

    public String name() {
        return name;
    }

    public String ownerUUID() {
        return ownerUUID;
    }

    public String password() {
        return password;
    }

    public String getHash(boolean hashPassword) {
        return switch (type) {
            case Public -> name + type.name() + material.name();
            case Protected -> hashPassword ? name + type.name() + material.name() + Utils.hash(password) : name + type.name() + material.name() + password;
            default -> name + ownerUUID + type.name() + material.name();
        };
    }

    public String getHash() {
        return getHash(false);
    }

    public void addSender(Sender sender) {
        senders.add(sender);
    }

    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    public void removeSender(Sender sender) {
        senders.remove(sender);
    }

    public void removeReceiver(Receiver receiver) {
        receivers.remove(receiver);
    }

    public boolean isPublic() {
        return ownerUUID.isEmpty();
    }

    public boolean isProtected() {
        return !ownerUUID.isEmpty() && !password.isEmpty();
    }

    public boolean isPrivate() {
        return !ownerUUID.isEmpty() && password.isEmpty();
    }

    public boolean isOwner(String uuid) {
        return ownerUUID.equals(uuid);
    }

    public boolean isPassword(String password) {
        return Utils.verifyHash(password, this.password);
    }
}

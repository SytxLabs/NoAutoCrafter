package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class Transporter {

    public final FileManager file;

    public Transporter() {
        this.file = new FileManager("data", "transporter");
    }

    public boolean addReceiver(Player p, Block receiver, Material material) {
        if (receiver == null) {
            return false;
        }
        Location loc = receiver.getLocation();
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();

        file.write("Receiver." + locstring + ".OwnerUUID", p.getUniqueId().toString());
        file.write("Receiver." + locstring + ".OwnerName", p.getName());
        file.write("Receiver." + locstring + ".Material", material.name());
        file.write("Receiver." + locstring + ".CreatedAT", System.currentTimeMillis());
        return true;
    }

    @SuppressWarnings("unused")
    public HashMap<Material, Block> getReceivers(Player p) {
        HashMap<Material, Block> receivers = new HashMap<>();
        if (file.getConfig().getConfigurationSection("Receiver") == null) {
            return receivers;
        }
        for (String key : Objects.requireNonNull(file.getConfig().getConfigurationSection("Receiver")).getKeys(false)) {
            if (file.read("Receiver." + key + ".OwnerUUID").equalsIgnoreCase(p.getUniqueId().toString())) {
                String[] locations = key.split(",");
                Location loc = new Location(Bukkit.getWorld(locations[0]), Integer.parseInt(locations[1]), Integer.parseInt(locations[2]), Integer.parseInt(locations[3]));
                if (loc.getWorld() == null) {
                    continue;
                }
                Block block = loc.getBlock();
                if (block.getType() == Material.AIR) {
                    continue;
                }
                receivers.put(Material.valueOf(file.read("Receiver." + key + ".Material")), block);
            }
        }
        return receivers;
    }

    public Location getReceiver(Material mat, String uuid) {
        if (file.getConfig().getConfigurationSection("Receiver") == null) {
            return null;
        }
        for (String key : Objects.requireNonNull(file.getConfig().getConfigurationSection("Receiver")).getKeys(false)) {
            if (file.read("Receiver." + key + ".OwnerUUID").equalsIgnoreCase(uuid)) {
                if (file.read("Receiver." + key + ".Material").equalsIgnoreCase(mat.name())) {
                    String[] locations = key.split(",");
                    return new Location(Bukkit.getWorld(locations[0]), Integer.parseInt(locations[1]), Integer.parseInt(locations[2]), Integer.parseInt(locations[3]));
                }
            }
        }
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean removeReceivers(Location loc) {
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Receiver." + locstring + ".OwnerUUID", null);
        file.write("Receiver." + locstring + ".OwnerName", null);
        file.write("Receiver." + locstring + ".Material", null);
        file.write("Receiver." + locstring + ".CreatedAT", null);
        file.write("Receiver." + locstring, null);
        return true;
    }

    public boolean containsReceiver(Location loc) {
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return file.read("Receiver." + locstring + ".Material") != null;
    }

    public boolean containsReceiver(Material material, String uuid) {
        if (file.getConfig().getConfigurationSection("Receiver") == null) {
            return false;
        }
        for (String key : Objects.requireNonNull(file.getConfig().getConfigurationSection("Receiver")).getKeys(false)) {
            if (file.read("Receiver." + key + ".OwnerUUID").equalsIgnoreCase(uuid)) {
                if (file.read("Receiver." + key + ".Material").equalsIgnoreCase(material.name())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addSender(Player p, Block sender, Material material) {
        if (sender == null) {
            return false;
        }
        Location loc = sender.getLocation();
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();

        file.write("Sender." + locstring + ".OwnerUUID", p.getUniqueId().toString());
        file.write("Sender." + locstring + ".OwnerName", p.getName());
        file.write("Sender." + locstring + ".Material", material.name());
        file.write("Sender." + locstring + ".CreatedAT", System.currentTimeMillis());
        return true;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean removeSender(Location loc) {
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        file.write("Sender." + locstring + ".OwnerUUID", null);
        file.write("Sender." + locstring + ".OwnerName", null);
        file.write("Sender." + locstring + ".Material", null);
        file.write("Sender." + locstring + ".CreatedAT", null);
        file.write("Sender." + locstring, null);
        return true;
    }

    public Material getSender(Location loc) {
        if (loc.getWorld() == null) {
            return null;
        }
        if (file.read("Sender." + loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + ".Material") == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return Material.valueOf(file.read("Sender." + locstring + ".Material"));
    }

    public String getSenderOwner(Location loc) {
        if (loc.getWorld() == null) {
            return null;
        }
        if (file.read("Sender." + loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + ".OwnerUUID") == null) {
            return null;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return file.read("Sender." + locstring + ".OwnerUUID");
    }

    public boolean containsSender(Location loc) {
        if (loc.getWorld() == null) {
            return false;
        }
        String locstring = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
        return file.read("Sender." + locstring + ".Material") != null;
    }
}

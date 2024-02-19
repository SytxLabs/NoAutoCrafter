package de.chaosschwein.autocrafter.listener;

import de.chaosschwein.autocrafter.cmd.AutoCommand;
import de.chaosschwein.autocrafter.cmd.ReceiverCommand;
import de.chaosschwein.autocrafter.cmd.SenderCommand;
import de.chaosschwein.autocrafter.enums.ChannelType;
import de.chaosschwein.autocrafter.enums.SenderType;
import de.chaosschwein.autocrafter.types.Channel;
import de.chaosschwein.autocrafter.types.CraftingRezept;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.manager.file.CrafterFile;
import de.chaosschwein.autocrafter.types.Receiver;
import de.chaosschwein.autocrafter.types.Sender;
import de.chaosschwein.autocrafter.utils.InventoryCreator;
import de.chaosschwein.autocrafter.utils.ItemBuilder;
import de.chaosschwein.autocrafter.utils.Message;
import de.chaosschwein.autocrafter.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryListener implements Listener {

    public static List<String> functions = new ArrayList<>() {{
        add("§d§lAutoCrafter");
        add("§d§lChannelViewer");
        add("§d§lAddChannel");
        add("§d§lCreateChannel");
        add("§d§lChannelPassword");
        add("§d§lSender");
    }};

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) {
            return;
        }
        String title = e.getView().getTitle();
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        Message msg = new Message(p);
        if (item == null || item.getType() == Material.AIR || title.isEmpty() || !item.hasItemMeta() || item.getItemMeta() == null) {
            return;
        }
        if (inv == null) {
            return;
        }
        if (!functions.contains(title) && !title.startsWith("§d§lChannel: ")) {
            return;
        }
        String itemName = item.getItemMeta().getDisplayName();
        e.setCancelled(true);
        if (item.getType() == Material.GRAY_STAINED_GLASS_PANE && itemName.equals("§e")) {
            return;
        }if (e.getClickedInventory() != p.getOpenInventory().getTopInventory()) {
            return;
        }
        switch (title) {
            case "§d§lAutoCrafter" -> onInventoryAutoCraftClick(e, p, item, msg);
            case "§d§lChannelViewer" -> onInventoryChannelViewerClick(p, item, itemName);
            case "§d§lAddChannel" -> onInventoryAddChannelClick(p, inv, msg, itemName);
            case "§d§lChannelPassword" -> onInventoryChannelPasswordClick(p, inv, msg);
            case "§d§lCreateChannel" -> onInventoryCreateChannelClick(p, inv, msg, itemName);
            case "§d§lSender" -> onInventorySenderClick(p, inv, item, msg, itemName);
        }
        if (title.startsWith("§d§lChannel: ")) {
            onInventoryChannelClick(p, itemName);
        }
    }

    public void onInventoryAutoCraftClick(InventoryClickEvent e, Player p, ItemStack item, Message msg) {
        ItemStack[] items = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            ItemStack iStack = e.getInventory().getItem(i + 1);
            if (iStack == null || iStack.getType() == Material.AIR) {
                items[i] = null;
                continue;
            }
            items[i] = new ItemStack(iStack.getType(), iStack.getAmount());
        }
        CraftingRezept recipe = new CraftingRezept(items, new HashMap<>() {{
            put(item.getType(), item.getAmount());
        }});
        CrafterFile crafterFile = new CrafterFile();
        crafterFile.save(p, AutoCommand.crafter.get(p), recipe);

        msg.send(AutoMain.language.CrafterCreated);
        p.closeInventory();
    }

    public void onInventoryChannelViewerClick(Player p, ItemStack item, String itemName) {
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (itemName.equals(AutoMain.language.InventoryBack)) {
            inventoryCreator.openChannelViewer(inventoryCreator.channelViewerPage - 1);
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryNext)) {
            inventoryCreator.openChannelViewer(inventoryCreator.channelViewerPage + 1);
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryAddChannel)) {
            inventoryCreator.openAddChannel();
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryCreateChannel)) {
            inventoryCreator.openCreateChannel();
            return;
        }
        List<Channel> channels = AutoMain.transporter.channels.values().stream().filter(channel -> channel.isOwner(p.getUniqueId().toString()) || channel.getUsers().contains(p.getUniqueId().toString())).toList();
        for (Channel channel : channels) {
            if (ChatColor.stripColor(itemName).equals(channel.name) && item.getType() == channel.material()) {
                inventoryCreator.openChannel(channel);
                return;
            }
        }
    }

    public void onInventoryAddChannelClick(Player p, Inventory inv, Message msg, String itemName) {
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (itemName.equals(AutoMain.language.InventoryBack)) {
            p.closeInventory();
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryAddChannel)) {
            ItemStack i = inv.getItem(4);
            //noinspection DuplicatedCode
            if (i == null || i.getType() == Material.AIR) {
                msg.send(AutoMain.language.InventoryNoItem);
                return;
            }
            String channelName = i.getType().name();
            if (i.getItemMeta() != null) {
                channelName = i.getItemMeta().getDisplayName();
            }
            String finalChannelName = channelName;
            List<Channel> channels = AutoMain.transporter.channels.values().stream().filter(channel -> channel.material == i.getType() && channel.name.equals(finalChannelName) &&
                    (channel.type == ChannelType.Protected || channel.type == ChannelType.Public) &&
                    !(channel.getUsers().contains(p.getUniqueId().toString()) || channel.isOwner(p.getUniqueId().toString()))).toList();
            if (channels.isEmpty()) {
                msg.send(AutoMain.language.ChannelNotFound);
                return;
            }
            Channel channel = channels.get(0);
            if (channel.isPublic()) {
                channel.addUser(p.getUniqueId().toString());
                channel.save();
                msg.send(AutoMain.language.InventoryUseChannel);
                p.closeInventory();
                useChannelForReceiver(p);
                useChannelForSender(p, channel);
                return;
            }
            if (channel.isProtected()) {
                p.closeInventory();
                inventoryCreator.openPasswordChannel(channel);
            }
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public void onInventoryCreateChannelClick(Player p, Inventory inv, Message msg, String itemName) {
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (itemName.equals(AutoMain.language.InventoryBack)) {
            inventoryCreator.openChannelViewer();
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryCreateChannel)) {
            p.closeInventory();
            inventoryCreator.openCreateChannel();
        }
        ItemStack typeItem = inv.getItem(14);
        if (typeItem == null || typeItem.getType() == Material.AIR || !typeItem.hasItemMeta() || typeItem.getItemMeta() == null) {
            return;
        }
        ChannelType type = switch (typeItem.getType()) {
            case WHITE_DYE -> ChannelType.Public;
            case YELLOW_DYE -> ChannelType.Protected;
            case RED_DYE -> ChannelType.Private;
            default -> null;
        };
        if (type == null) {
            return;
        }
        if (itemName.equals(typeItem.getItemMeta().getDisplayName())) {
            if (type == ChannelType.Private) {
                inv.setItem(14, new ItemBuilder(ChannelType.Protected.material).setName(ChannelType.Protected.getTranslation()).build());
            } else if (type == ChannelType.Protected) {
                inv.setItem(14, new ItemBuilder(ChannelType.Public.material).setName(ChannelType.Public.getTranslation()).build());
            } else {
                inv.setItem(14, new ItemBuilder(ChannelType.Private.material).setName(ChannelType.Private.getTranslation()).build());
            }
            p.updateInventory();
            return;
        }
        ItemStack i = inv.getItem(12);
        //noinspection DuplicatedCode
        if (i == null || i.getType() == Material.AIR) {
            msg.send(AutoMain.language.InventoryNoItem);
            return;
        }
        String channelName = i.getType().name();
        if (i.getItemMeta() != null) {
            channelName = i.getItemMeta().getDisplayName();
        }
        String finalChannelName = channelName;
        List<Channel> channels = AutoMain.transporter.channels.values().stream().filter(channel -> channel.material == i.getType() && channel.name.equals(finalChannelName) &&
                (channel.getUsers().contains(p.getUniqueId().toString()) || channel.isOwner(p.getUniqueId().toString()))).toList();
        if (!channels.isEmpty()) {
            msg.send(AutoMain.language.ChannelAlreadyExists);
            return;
        }
        Channel channel = new Channel(type, i.getType(), channelName, p.getUniqueId().toString(), "");
        if (type == ChannelType.Protected) {
            p.closeInventory();
            inventoryCreator.openPasswordChannel(channel);
        } else {
            channel.addUser(p.getUniqueId().toString());
            channel.save();
            msg.send(AutoMain.language.InventoryUseChannel);
            p.closeInventory();
            useChannelForReceiver(p);
            useChannelForSender(p, channel);
        }
    }

    public void onInventoryChannelPasswordClick(Player p, Inventory inv, Message msg) {
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (inventoryCreator.selectedChannel == null) {
            p.closeInventory();
            return;
        }
        AnvilInventory anvil = (AnvilInventory) inv;
        String password = anvil.getRenameText();
        if (password == null) {
            return;
        }
        Channel channel = inventoryCreator.selectedChannel;
        if (inventoryCreator.isCreatingChannel && channel.password().isEmpty()) {
            channel.setPassword(password);
            channel.addUser(p.getUniqueId().toString());
            channel.save();
            msg.send(AutoMain.language.InventoryUseChannel);
            p.closeInventory();
            useChannelForReceiver(p);
            useChannelForSender(p, inventoryCreator.selectedChannel);
            return;
        }
        if (channel.isPassword(Utils.hash(password))) {
            channel.addUser(p.getUniqueId().toString());
            channel.save();
            msg.send(AutoMain.language.InventoryUseChannel);
            p.closeInventory();
            useChannelForReceiver(p);
            useChannelForSender(p, inventoryCreator.selectedChannel);
        }
    }

    public void onInventoryChannelClick(Player p, String itemName) {
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (itemName.equals(AutoMain.language.InventoryBack)) {
            p.closeInventory();
            inventoryCreator.openChannelViewer();
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryDeleteChannel)) {
            inventoryCreator.selectedChannel.delete();
            inventoryCreator.openChannelViewer();
            return;
        }
        if (itemName.equals(AutoMain.language.InventoryUseChannel)) {
            useChannelForReceiver(p);
            useChannelForSender(p, inventoryCreator.selectedChannel);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public void onInventorySenderClick(Player p, Inventory inv, ItemStack item, Message msg, String itemName) {
        if (itemName.equals(AutoMain.language.InventoryBack)) {
            p.closeInventory();
            return;
        }
        ItemStack channelItem = inv.getItem(12);
        if (channelItem == null || channelItem.getType() == Material.AIR) {
            return;
        }
        if (channelItem == item) {
            InventoryCreator.getInstance(p).openChannelViewer();
        }
        ItemStack typeItem = inv.getItem(14);
        if (typeItem == null || typeItem.getType() == Material.AIR || !typeItem.hasItemMeta() || typeItem.getItemMeta() == null) {
            return;
        }
        SenderType type = switch (typeItem.getType()) {
            case PINK_DYE -> SenderType.RoundRobin;
            case BLACK_DYE -> SenderType.Random;
            case RED_DYE -> SenderType.Overflow;
            default -> null;
        };
        if (type == null) {
            return;
        }
        if (itemName.equals(typeItem.getItemMeta().getDisplayName())) {
            if (type == SenderType.Overflow) {
                inv.setItem(14, new ItemBuilder(SenderType.RoundRobin.material).setName(SenderType.RoundRobin.getTranslation()).build());
            } else if (type == SenderType.Random) {
                inv.setItem(14, new ItemBuilder(SenderType.Overflow.material).setName(SenderType.Overflow.getTranslation()).build());
            } else {
                inv.setItem(14, new ItemBuilder(SenderType.Random.material).setName(SenderType.Random.getTranslation()).build());
            }
            p.updateInventory();
            return;
        }
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        if (inventoryCreator.selectedChannel == null) {
            msg.send(AutoMain.language.InventoryNoChannel);
            return;
        }
        if (!SenderCommand.sender.containsKey(p)) {
            p.closeInventory();
            return;
        }
        Block block = SenderCommand.sender.get(p);
        if (AutoMain.transporter.isSender(block.getLocation())) {
            msg.send(AutoMain.language.SenderAlreadyExists);
            p.closeInventory();
            return;
        }
        Sender sender = new Sender(block.getLocation(), type, inventoryCreator.selectedChannel);
        AutoMain.transporter.addSender(sender);
        SenderCommand.sender.remove(p);
        p.closeInventory();
        msg.send(AutoMain.language.SenderCreated);
    }

    private void useChannelForReceiver(Player p) {
        if (!ReceiverCommand.receiver.containsKey(p)) {
            return;
        }
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        p.closeInventory();
        if (inventoryCreator.selectedChannel == null) {
            new InventoryCreator(p).openChannelViewer();
            return;
        }
        Block block = ReceiverCommand.receiver.get(p);
        Receiver receiver = new Receiver(block.getLocation(), inventoryCreator.selectedChannel);
        AutoMain.transporter.addReceiver(receiver);
        ReceiverCommand.receiver.remove(p);
    }

    private void useChannelForSender(Player p, Channel channel) {
        if (!SenderCommand.sender.containsKey(p)) {
            return;
        }
        InventoryCreator inventoryCreator = InventoryCreator.getInstance(p);
        p.closeInventory();
        if (channel == null) {
            new InventoryCreator(p).openChannelViewer();
            return;
        }
        inventoryCreator.openSender(channel);
    }
}

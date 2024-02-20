package de.chaosschwein.autocrafter.utils;

import de.chaosschwein.autocrafter.enums.ChannelType;
import de.chaosschwein.autocrafter.enums.SenderType;
import de.chaosschwein.autocrafter.main.AutoMain;
import de.chaosschwein.autocrafter.types.Channel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class InventoryCreator {

    private final Player player;

    public InventoryCreator(Player player) {
        this.player = player;
        if (inventoryCreators.containsKey(player)) {
            inventoryCreators.replace(player, this);
        } else {
            inventoryCreators.put(player, this);
        }
    }

    private void fillInv(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack is = inv.getItem(i);
            if (is == null || is.getType() == Material.AIR) {
                inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§e").build());
            }
        }
    }

    public void open() {
        if (player != null) {
            player.openWorkbench(player.getLocation(), true);
            player.getOpenInventory().setTitle("§d§lAutoCrafter");
            //noinspection UnstableApiUsage
            player.updateInventory();
        }
    }

    public void openSender() {
        openSender(null);
    }

    public void openSender(Channel channel) {
        if (player != null) {
            Inventory inv = Bukkit.createInventory(null, 36, "§d§lSender");
            fillInv(inv);
            if (channel != null) {
                selectedChannel = channel;
                inv.setItem(12, new ItemBuilder(channel.material).setName(AutoMain.language.InventorySelectChannel).setLore(channel.getLore()).build());
            } else {
                inv.setItem(12, new ItemBuilder(Material.BARRIER).setName(AutoMain.language.InventorySelectChannel).build());
            }
            inv.setItem(14, new ItemBuilder(SenderType.Random.material).setName(SenderType.Random.translatedName).build());
            inv.setItem(20, new ItemBuilder(Material.RED_CONCRETE).setName(AutoMain.language.InventoryBack).build());
            inv.setItem(22, new ItemBuilder(Material.BOOK).setName(AutoMain.language.InventoryAddItem).setLore(AutoMain.language.InventoryAddItemLore).build());
            inv.setItem(24, new ItemBuilder(Material.LIME_CONCRETE).setName(AutoMain.language.InventoryCreateSender).build());
            player.openInventory(inv);
        }
    }

    public void openChannelViewer() {
        openChannelViewer(1);
    }

    public int channelViewerPage = 1;
    public int channelViewerChannelMax = 0;
    public static HashMap<Player, InventoryCreator> inventoryCreators = new HashMap<>();

    public static InventoryCreator getInstance(Player player) {
        if (inventoryCreators.containsKey(player)) {
            return inventoryCreators.get(player);
        }
        return new InventoryCreator(player);
    }

    public void openChannelViewer(int page) {
        if (player == null) {
            return;
        }
        List<Channel> channels = AutoMain.transporter.channels.values().stream().filter(channel -> channel.isOwner(player.getUniqueId().toString()) || channel.getUsers().contains(player.getUniqueId().toString())).toList();
        int maxPage = (int) Math.ceil((double) channels.size() / 36);
        if (page < 1) {
            player.closeInventory();
        }
        if (maxPage == 0) {
            maxPage = 1;
        }
        if (page > maxPage) {
            page = maxPage;
        }
        if (!channels.isEmpty()) {
            channels = channels.stream().skip((page - 1) * 36L).limit(36).toList();
        }
        channelViewerPage = page;
        channelViewerChannelMax = channels.size();
        int invSize = channels.isEmpty() ? 18 : (channels.size() % 9 == 0 ? channels.size() : channels.size() + (9 - channels.size() % 9)) + 9;
        Inventory inv = Bukkit.createInventory(null, invSize, "§d§lChannelViewer (" + page + " / " + maxPage + ")");
        fillInv(inv);
        for (int i = 0; i < channels.size(); i++) {
            Channel channel = channels.get(i);
            ChatColor color = channel.isPublic() ? ChatColor.GREEN : ChatColor.RED;
            inv.setItem(i, new ItemBuilder(channel.material).setName(color + channel.name).setLore(channel.getLore()).build());
        }
        int lastRowBeginning = (invSize / 9 - 1) * 9;
        if (page > 1) {
            inv.setItem(lastRowBeginning, new ItemBuilder(Material.ARROW).setName(AutoMain.language.InventoryBack).build());
        }
        if (page < maxPage) {
            inv.setItem(lastRowBeginning + 8, new ItemBuilder(Material.ARROW).setName(AutoMain.language.InventoryNext).build());
        }
        inv.setItem(lastRowBeginning + 3, new ItemBuilder(Material.LIME_DYE).setName(AutoMain.language.InventoryCreateChannel).build());
        inv.setItem(lastRowBeginning + 5, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(AutoMain.language.InventoryAddChannel).build());

        player.openInventory(inv);
    }

    public Channel selectedChannel;
    public boolean isCreatingChannel = false;
    public void openAddChannel() {
        if (player == null) {
            return;
        }
        Inventory inv = Bukkit.createInventory(null, 36, "§d§lAddChannel");
        fillInv(inv);
        inv.setItem(4, null);
        inv.setItem(20, new ItemBuilder(Material.RED_CONCRETE).setName(AutoMain.language.InventoryBack).build());
        inv.setItem(22, new ItemBuilder(Material.BOOK).setName(AutoMain.language.InventoryAddItem).setLore(AutoMain.language.InventoryAddItemLore).build());
        inv.setItem(24, new ItemBuilder(Material.LIME_CONCRETE).setName(AutoMain.language.InventoryAddChannel).build());
        player.openInventory(inv);
    }

    public void openCreateChannel() {
        if (player == null) {
            return;
        }
        isCreatingChannel = true;
        Inventory inv = Bukkit.createInventory(null, 36, "§d§lCreateChannel");
        fillInv(inv);
        inv.setItem(12, null);
        inv.setItem(14, new ItemBuilder(ChannelType.Private.material).setName(ChannelType.Private.translatedName).build());
        inv.setItem(20, new ItemBuilder(Material.RED_CONCRETE).setName(AutoMain.language.InventoryBack).build());
        inv.setItem(22, new ItemBuilder(Material.BOOK).setName(AutoMain.language.InventoryAddItem).setLore(AutoMain.language.InventoryAddItemLore).build());
        inv.setItem(24, new ItemBuilder(Material.LIME_CONCRETE).setName(AutoMain.language.InventoryCreateChannel).build());
        player.openInventory(inv);
    }

    public void openChannel(Channel channel) {
        if (player == null) {
            return;
        }
        selectedChannel = channel;
        Inventory inv = Bukkit.createInventory(null, 27, "§d§lChannel: " + channel.name);
        fillInv(inv);
        inv.setItem(12, new ItemBuilder(Material.BARRIER).setName(AutoMain.language.InventoryDeleteChannel).build());
        inv.setItem(14, new ItemBuilder(Material.LIME_DYE).setName(AutoMain.language.InventoryUseChannel).build());
        inv.setItem(18, new ItemBuilder(Material.RED_CONCRETE).setName(AutoMain.language.InventoryBack).build());
        player.openInventory(inv);
    }
}

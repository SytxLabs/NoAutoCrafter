package de.chaosschwein.autocrafter.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("unused")
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;
    private final Material material;
    public ItemBuilder(Material material) {
        this.material = material;
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount, short damage) {
        this.material = material;
        //noinspection deprecation
        this.item = new ItemStack(material, amount, damage);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        this.meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        this.meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        ArrayList<String> l = new ArrayList<String>(){{add(lore);}};
        this.meta.setLore(l);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setItemFlag(ItemFlag itemflag) {
        this.meta.addItemFlags(itemflag);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setItemFlag(ItemFlag itemflag, boolean value) {
        if(value) {
            this.meta.addItemFlags(itemflag);
        } else {
            this.meta.removeItemFlags(itemflag);
        }
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setItemFlag(ArrayList<ItemFlag> itemFlag) {
        for(ItemFlag i : itemFlag) {
            this.meta.addItemFlags(i);
        }
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int level, boolean ignoreLevel) {
        this.meta.addEnchant(enchantment, level, ignoreLevel);
        this.item.setItemMeta(meta);
        return this;
    }



    public ItemBuilder setEnchantment(ArrayList<Enchantment> enchantment, ArrayList<Integer> level) {
        for(int i = 0; i < enchantment.size(); i++) {
            this.meta.addEnchant(enchantment.get(i), level.get(i), true);
        }
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchantment(ArrayList<Enchantment> enchantment, ArrayList<Integer> level, boolean ignoreLevel) {
        for(int i = 0; i < enchantment.size(); i++) {
            this.meta.addEnchant(enchantment.get(i), level.get(i), ignoreLevel);
        }
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if(this.material == Material.LEATHER_BOOTS || this.material == Material.LEATHER_CHESTPLATE || this.material == Material.LEATHER_HELMET || this.material == Material.LEATHER_LEGGINGS) {
            LeatherArmorMeta leatherarmormeta = (LeatherArmorMeta) this.meta;
            leatherarmormeta.setColor(color);
            this.item.setItemMeta(leatherarmormeta);
        }
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        if(Bukkit.getPlayer(owner) == null) {return this;}
        return setSkullOwner(Bukkit.getPlayer(owner));
    }

    public ItemBuilder setSkullOwner(UUID uuid) {
        if(this.material == Material.PLAYER_HEAD || this.material == Material.PLAYER_WALL_HEAD) {
            SkullMeta skullmeta = (SkullMeta) this.meta;
            skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            this.item.setItemMeta(skullmeta);
        }
        return this;
    }

    public ItemBuilder setSkullOwner(Player player) {
        if(this.material == Material.PLAYER_HEAD || this.material == Material.PLAYER_WALL_HEAD) {
            SkullMeta skullmeta = (SkullMeta) this.meta;
            skullmeta.setOwningPlayer(player);
            this.item.setItemMeta(skullmeta);
        }
        return this;
    }

    public ItemStack build() {
        return this.item;
    }

}

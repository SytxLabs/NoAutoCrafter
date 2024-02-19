package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

public enum ChannelType {
    Private(AutoMain.language.ChannelTypePrivate, Material.RED_DYE),
    Public(AutoMain.language.ChannelTypePublic, Material.WHITE_DYE);
    public final String translatedName;
    public final Material material;

    ChannelType(String translatedName, Material material) {
        this.translatedName = translatedName;
        this.material = material;
    }
}

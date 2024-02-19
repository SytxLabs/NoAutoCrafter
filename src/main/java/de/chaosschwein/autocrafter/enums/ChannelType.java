package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

public enum ChannelType {
    Private("channel_type.private", Material.RED_DYE),
    Protected("channel_type.protected", Material.YELLOW_DYE),
    Public("channel_type.public", Material.WHITE_DYE);
    private final String translationKey;
    public final Material material;

    ChannelType(String translationKey, Material material) {
        this.translationKey = translationKey;
        this.material = material;
    }

    public String getTranslation() {
        return AutoMain.language.fileManager.read(translationKey);
    }
}

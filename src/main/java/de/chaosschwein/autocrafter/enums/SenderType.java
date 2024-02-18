package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

public enum SenderType {
    RoundRobin("sender_type.round_robin", Material.PINK_DYE),
    Random("sender_type.random", Material.BLACK_DYE),
    Overflow("sender_type.overflow", Material.RED_DYE);

    private final String translationKey;
    public final Material material;

    SenderType(String translationKey, Material material) {
        this.translationKey = translationKey;
        this.material = material;
    }

    public String getTranslation() {
        return AutoMain.language.fileManager.read(translationKey);
    }
}

package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

public enum SenderType {
    RoundRobin(AutoMain.language.SenderTypeRoundRobin, Material.PINK_DYE),
    Random(AutoMain.language.SenderTypeRandom, Material.BLACK_DYE),
    Overflow(AutoMain.language.SenderTypeOverflow, Material.RED_DYE);

    public final String translatedName;
    public final Material material;

    SenderType(String translatedName, Material material) {
        this.translatedName = translatedName;
        this.material = material;
    }
}

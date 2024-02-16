package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FarmType {
    public Material material;
    public boolean enable;
    public HashMap<Material, ArrayList<Integer>> returnMap;
    public boolean soulSandRequired;

    public FarmType(Material material, boolean enable, HashMap<Material, ArrayList<Integer>> returnMap, boolean soulSandRequired) {
        this.material = material;
        this.enable = enable;
        this.returnMap = returnMap;
        this.soulSandRequired = soulSandRequired;
    }

    public static FarmType getFromMaterial(Material material) {
        List<FarmType> farmTypes = AutoMain.farmingTypes.types;
        for (FarmType farmType : farmTypes) {
            if (farmType.material == material) {
                return farmType;
            }
        }
        return null;
    }
}

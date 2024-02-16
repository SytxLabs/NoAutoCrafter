package de.chaosschwein.autocrafter.manager.file;

import de.chaosschwein.autocrafter.enums.FarmType;
import de.chaosschwein.autocrafter.manager.FileManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FarmingTypes {

    public final List<FarmType> types = new ArrayList<>();

    private final FileManager fileManager;

    public FarmingTypes() {
        fileManager = new FileManager("farm_types");

        setDefault();

        FileConfiguration config = fileManager.getConfig();
        ConfigurationSection configTypes = config.getConfigurationSection("type");
        if (configTypes == null) {
            System.out.println("[AutoCrafter] No farm types found");
            return;
        }
        for (String key : configTypes.getKeys(false)) {
            if (config.getBoolean("type." + key + ".enable")) {
                Material material = Material.getMaterial(key);
                if (material == null) {
                    System.out.println("§c[AutoCrafter] Error while loading farm type: " + key);
                    continue;
                }
                HashMap<Material, ArrayList<Integer>> returnMap = new HashMap<>();
                ConfigurationSection returnSection = config.getConfigurationSection("type." + key + ".return");
                if (returnSection != null) {
                    for (String returnKey : returnSection.getKeys(false)) {
                        ArrayList<Integer> returnList = new ArrayList<>();
                        if (!config.contains("type." + key + ".return." + returnKey + ".min") && !config.contains("type." + key + ".return." + returnKey + ".max") && !config.contains("type." + key + ".return." + returnKey)) {
                            if (config.getInt("type." + key + ".return." + returnKey) > 0) {
                                returnList.add(config.getInt("type." + key + ".return." + returnKey));
                                returnMap.put(Material.getMaterial(returnKey), returnList);
                            }
                        } else {
                            returnList.add(config.getInt("type." + key + ".return." + returnKey + ".min"));
                            returnList.add(config.getInt("type." + key + ".return." + returnKey + ".max"));
                            returnMap.put(Material.getMaterial(returnKey), returnList);
                        }
                    }
                }
                types.add(new FarmType(material, config.getBoolean("type." + key + ".enable"), returnMap, config.getBoolean("type." + key + ".soul_sand_required")));
            }
        }
    }

    public void setDefault() {
        fileManager.writeDefault(new HashMap<>() {{
            put("type.WHEAT_SEEDS.enable", true);
            put("type.WHEAT_SEEDS.return.WHEAT_SEEDS.min", 0);
            put("type.WHEAT_SEEDS.return.WHEAT_SEEDS.max", 2);
            put("type.WHEAT_SEEDS.return.WHEAT.min", 1);
            put("type.WHEAT_SEEDS.return.WHEAT.max", 2);

            put("type.CARROT.enable", true);
            put("type.CARROT.return.CARROT.min", 1);
            put("type.CARROT.return.CARROT.max", 4);

            put("type.POTATO.enable", true);
            put("type.POTATO.return.POTATO.min", 1);
            put("type.POTATO.return.POTATO.max", 4);
            put("type.POTATO.return.POISONOUS_POTATO.min", 0);
            put("type.POTATO.return.POISONOUS_POTATO.max", 1);

            put("type.BEETROOT_SEEDS.enable", true);
            put("type.BEETROOT_SEEDS.return.BEETROOT.min", 1);
            put("type.BEETROOT_SEEDS.return.BEETROOT.max", 4);
            put("type.BEETROOT_SEEDS.return.BEETROOT_SEEDS.min", 1);
            put("type.BEETROOT_SEEDS.return.BEETROOT_SEEDS.max", 2);

            put("type.SWEET_BERRIES.enable", true);
            put("type.SWEET_BERRIES.return.SWEET_BERRIES.min", 1);
            put("type.SWEET_BERRIES.return.SWEET_BERRIES.max", 3);

            put("type.NETHER_WART.enable", true);
            put("type.NETHER_WART.return.NETHER_WART.min", 1);
            put("type.NETHER_WART.return.NETHER_WART.max", 4);
            put("type.NETHER_WART.soul_sand_required", true);
        }});
    }
}

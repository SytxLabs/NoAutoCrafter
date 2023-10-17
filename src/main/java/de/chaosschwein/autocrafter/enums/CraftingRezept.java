package de.chaosschwein.autocrafter.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CraftingRezept {

    private ItemStack[] ingredients = new ItemStack[9];
    private HashMap<Material, Integer> results = new HashMap<>();

    public CraftingRezept() {

    }

    public CraftingRezept(ItemStack[] ingredients, HashMap<Material, Integer> results) {
        this.ingredients = ingredients;
        this.results = results;
    }

    public static CraftingRezept fromFileString(String fileString) {
        CraftingRezept rezept = new CraftingRezept();
        String[] split = fileString.split("\\|\\|");
        String[] ingredients = split[0].split(";");
        String[] results = split[1].split(";");
        for (int i = 0; i < ingredients.length; i++) {
            String[] split1 = ingredients[i].split(":");
            if (split1[0].equalsIgnoreCase("AIR")) {
                rezept.ingredients[i] = null;
                continue;
            }
            Material m = Material.getMaterial(split1[0]);
            if (m == null) {
                rezept.ingredients[i] = null;
                continue;
            }
            rezept.ingredients[i] = new ItemStack(m, Integer.parseInt(split1[1]));
        }
        for (String s : results) {
            String[] split1 = s.split(":");
            rezept.results.put(Material.getMaterial(split1[0]), Integer.parseInt(split1[1]));
        }
        return rezept;
    }

    public ItemStack[] getIngredients() {
        return ingredients;
    }

    public HashMap<Material, Integer> getResults() {
        return results;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();

        for (ItemStack i : ingredients) {
            if (i == null || i.getType() == Material.AIR) {
                sb.append("AIR:0;");
                continue;
            }
            sb.append(i.getType().name()).append(":").append(i.getAmount()).append(";");
        }
        sb.append("||");
        for (Material m : results.keySet()) {
            sb.append(m.name()).append(":").append(results.get(m)).append(";");
        }
        return sb.toString();
    }
}

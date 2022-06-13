package de.chaosschwein.autocrafter.manager.file;

import org.json.simple.JSONObject;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.manager.FileManager;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class CrafingRezeptFile {

    private String dir = "data";
    private String filename = "craftingrezept";

    private FileManager file;

    public CrafingRezeptFile() {
        this.file = new FileManager(dir, filename);
    }

    public void init(){
        if(!file.getFile().exists()){
            for (CrafingRezept cr: CrafingRezept.values()) {
                file.write("Rezept."+cr.name()+".Ingredients", new JSONObject(cr.getIngredients()).toJSONString());
                file.write("Rezept."+cr.name()+".Result", cr.getResults());
                file.write("Rezept."+cr.name()+".Amount", cr.getAmount());
                file.write("Rezept."+cr.name()+".Time", cr.getTime());
            }
        }
    }

    public void read() {
        if (file.getConfig().getConfigurationSection("Rezept") != null) {
            for (String key : file.getConfig().getConfigurationSection("Rezept").getKeys(false)) {
                CrafingRezept cr = CrafingRezept.valueOf(key);
                try {
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(file.read("Rezept." + key + ".Ingredients"));
                    HashMap<String, Integer> ingredients = new HashMap<>();
                    for (Object key1 : jsonObject.keySet()) {
                        ingredients.put(key1.toString(), Integer.parseInt(jsonObject.get(key1).toString()));
                    }
                    cr.setIngredients(ingredients);
                } catch (ParseException ignored) {
                }
                cr.setResults((String) file.read("Rezept." + key + ".Result"));
                cr.setAmount(Integer.parseInt((String) file.read("Rezept." + key + ".Amount")));
                cr.setTime(Integer.parseInt((String) file.read("Rezept." + key + ".Time")));
            }
        }
    }
}

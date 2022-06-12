package de.chaosschwein.autocrafter.manager.file;

import com.google.gson.Gson;
import de.chaosschwein.autocrafter.enums.data.CrafingRezept;
import de.chaosschwein.autocrafter.manager.FileManager;

import java.util.HashMap;

public class CrafingRezeptFile {

    private String dir = "plugins/AutoCrafter/data";
    private String filename = "craftingrezept";

    private FileManager file;

    public CrafingRezeptFile() {
        this.file = new FileManager(dir, filename);
    }

    public void init(){
        if(!file.getFile().exists()){
            for (CrafingRezept cr: CrafingRezept.values()) {
                file.write("Rezept."+cr.name()+".Ingredients", new Gson().toJson(cr.getIngredients()));
                file.write("Rezept."+cr.name()+".Result", cr.getResults());
                file.write("Rezept."+cr.name()+".Amount", cr.getAmount());
                file.write("Rezept."+cr.name()+".Time", cr.getTime());
            }
        }else{
            read();
        }
    }

    public void read(){
        for (String key: file.getConfig().getConfigurationSection("Rezept").getKeys(false)) {
            CrafingRezept cr = CrafingRezept.valueOf(key);
            cr.setIngredients( new Gson().fromJson((String) file.read("Rezept."+key+".Ingredients"), HashMap.class));
            cr.setResults((String) file.read("Rezept."+key+".Result"));
            cr.setAmount(Integer.parseInt((String)file.read("Rezept."+key+".Amount")));
            cr.setTime(Integer.parseInt((String)file.read("Rezept."+key+".Time")));
        }
    }
}

package de.lighty.foxutils.config;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModConfig {
    /**
     * Config will be stored in gameDir/config/[namespace]_[path].properties
    */
    private final String path;
    private Map<String, Object> data = new HashMap<>();

    public ModConfig(String path) {
        this.path = path + ".properties";
    }

    public void Add(String key, Object value) {
        data.put(key, value);
    }

    public void AddIfMissing(String key, Object value) {
        if(!data.containsKey(key)) data.put(key, value);
    }

    public Object Get(String key){
        return data.get(key);
    }

    public void Save(){
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path modConfig = configDir.resolve(path);

        try{
            //Convert Map to list
            List<String> lines = new ArrayList<>();
            data.forEach((key, value) -> {
                if(value instanceof Boolean boolVal){
                    lines.add(key + "=" + (boolVal ? "True":"False"));
                }else
                    lines.add(key + "=" + value.toString());
            });
            Files.write(modConfig, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void LoadIfExists(){
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path modConfig = configDir.resolve(path);
        if(Files.exists(modConfig))
            Load();

    }


    public void Load(){
        data.clear();

        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path modConfig = configDir.resolve(path);

        try{
            var lines = Files.readAllLines(modConfig);
            // Convert lines to Map<String, Object>
            lines.forEach(this::handleLine);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void handleLine(String line){
        var pair = line.split("=");

        var key = pair[0];
        var value = line.substring(line.indexOf("=")+1);

        Integer intValue = null;
        Float floatValue = null;
        Boolean boolValue = null;
        // Try parsing to different types
        try{intValue = Integer.parseInt(value);}catch (Exception e){/* Ignore */}
        try{floatValue = Float.parseFloat(value);}catch (Exception e){/* Ignore */}
        if(value.equals("True")){ boolValue = true;}
        else if(value.equals("False")){ boolValue = false; }

        if(intValue != null) Add(key, intValue);
        else if(floatValue != null) Add(key, floatValue);
        else if(boolValue != null) Add(key, boolValue);
        else Add(key, value); // String as Fallback
    }

    // Returns a Copy of the values
    public Map<String, Object> GetAll() {
        return Map.copyOf(data);
    }
}

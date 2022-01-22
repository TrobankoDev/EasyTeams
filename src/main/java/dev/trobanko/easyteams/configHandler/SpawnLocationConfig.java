package dev.trobanko.easyteams.configHandler;

import dev.trobanko.easyteams.EasyTeams;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnLocationConfig {

    private static File file;
    private static FileConfiguration spawnLocationFile;
    EasyTeams plugin;

    public SpawnLocationConfig(EasyTeams plugin) {
        this.plugin = plugin;
    }

    public static void setup(EasyTeams plugin){
        file = new File(plugin.getDataFolder(), "spawnLocations.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("A problem has occurred creating spawnLocations.yml");
            }
        }
        spawnLocationFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return spawnLocationFile;
    }

    public static void save(){
        try{
            spawnLocationFile.save(file);

        }catch (IOException e){
            System.out.println("A problem occurred while saving spawnLocations.yml");
        }
    }

    public static void reload(){
        spawnLocationFile = YamlConfiguration.loadConfiguration(file);
    }

}

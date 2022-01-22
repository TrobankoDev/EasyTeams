package dev.trobanko.easyteams;

import dev.trobanko.easyteams.commands.setupCommand;
import dev.trobanko.easyteams.commands.startGameCommand;
import dev.trobanko.easyteams.configHandler.SpawnLocationConfig;
import dev.trobanko.easyteams.events.InventoryClickListener;
import dev.trobanko.easyteams.events.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyTeams extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("setup").setExecutor(new setupCommand());
        getCommand("startgame").setExecutor(new startGameCommand(this));

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);

        SpawnLocationConfig.setup(this);
        SpawnLocationConfig.get().options().copyDefaults(true);
        SpawnLocationConfig.save();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

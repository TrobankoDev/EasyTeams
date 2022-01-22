package dev.trobanko.easyteams.GameManager;

import dev.trobanko.easyteams.PlayerHandler.TeamHandler;
import dev.trobanko.easyteams.configHandler.SpawnLocationConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class gameManager {

    public void setupGame(){
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!TeamHandler.getPlayerMap().containsKey(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You have failed to join a team. You will be spectating!");
                p.setGameMode(GameMode.SPECTATOR);
                p.getInventory().clear();
                return;
            }
            if(TeamHandler.getPlayerMap().get(p.getUniqueId()).contains("green")){
                //tp green team
                double x = SpawnLocationConfig.get().getDouble("teams.green.x");
                double y = SpawnLocationConfig.get().getDouble("teams.green.y");
                double z = SpawnLocationConfig.get().getDouble("teams.green.z");
                float pitch = (float) SpawnLocationConfig.get().getDouble("teams.green.pitch");
                float yaw = (float) SpawnLocationConfig.get().getDouble("teams.green.yaw");

                Location loc = new Location(Bukkit.getWorld("world"), x,y,z,yaw,pitch);

                p.teleport(loc);
                p.getInventory().clear();
                p.sendMessage(ChatColor.GREEN + "You have been teleported to the Green Team Spawn!");
            }
            else {
                //tp to red team
                double x = SpawnLocationConfig.get().getDouble("teams.red.x");
                double y = SpawnLocationConfig.get().getDouble("teams.red.y");
                double z = SpawnLocationConfig.get().getDouble("teams.red.z");
                float pitch = (float) SpawnLocationConfig.get().getDouble("teams.red.pitch");
                float yaw = (float) SpawnLocationConfig.get().getDouble("teams.red.yaw");

                Location loc = new Location(Bukkit.getWorld("world"), x,y,z,yaw,pitch);

                p.teleport(loc);
                p.getInventory().clear();
                p.sendMessage(ChatColor.GREEN + "You have been teleported to the Red Team Spawn!");
            }

        }

        TeamHandler.restartTeamColor();
    }

}

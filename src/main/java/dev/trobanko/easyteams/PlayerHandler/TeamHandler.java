package dev.trobanko.easyteams.PlayerHandler;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamHandler {

    private static Map<UUID,String> players_colors = new HashMap<>();

    public static void setTeamColor(Player player, String color){
        players_colors.remove(player.getUniqueId());
        players_colors.put(player.getUniqueId(), color);
    }

    public static Map<UUID, String> getPlayerMap(){
        System.out.println(players_colors);
        return players_colors;
    }

    public static String getTeamColor(Player player){
        return players_colors.get(player.getUniqueId());
    }

    public static void restartTeamColor(){
        players_colors.clear();
    }


}

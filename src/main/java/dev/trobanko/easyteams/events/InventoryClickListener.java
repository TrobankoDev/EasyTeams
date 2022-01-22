package dev.trobanko.easyteams.events;

import dev.trobanko.easyteams.PlayerHandler.TeamHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().contains("Pick your team")){
            switch (e.getCurrentItem().getType()){
                case GREEN_WOOL:
                    TeamHandler.setTeamColor(player,"green");
                    System.out.println("Joined green");
                    System.out.println(TeamHandler.getPlayerMap());
                    player.closeInventory();
                    player.sendMessage(ChatColor.GREEN + "You have joined the Green Team");
                    break;
                case RED_WOOL:
                    TeamHandler.setTeamColor(player,"red");
                    player.sendMessage(ChatColor.GREEN + "You have joined the Red Team");
                    player.closeInventory();
                    break;
            }
            e.setCancelled(true);
        }
    }

}

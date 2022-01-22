package dev.trobanko.easyteams.events;

import dev.trobanko.easyteams.configHandler.SpawnLocationConfig;
import dev.trobanko.easyteams.gui.TeamPickerGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(!player.hasPermission("easyteams.admin")) return;


        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            switch (e.getItem().getType()) {
                case RED_BANNER:
                    // Set spawn for red team
                    double rx = e.getClickedBlock().getX();
                    double ry = e.getClickedBlock().getY()+1;
                    double rz = e.getClickedBlock().getZ();
                    double rpitch = player.getLocation().getPitch()-90;
                    double ryaw = player.getLocation().getYaw();
                    e.setCancelled(true);
                    SpawnLocationConfig.get().set("teams.red.x", rx);
                    SpawnLocationConfig.get().set("teams.red.y", ry);
                    SpawnLocationConfig.get().set("teams.red.z", rz);
                    SpawnLocationConfig.get().set("teams.red.pitch", rpitch);
                    SpawnLocationConfig.get().set("teams.red.yaw", ryaw);
                    SpawnLocationConfig.save();

                    player.sendMessage(ChatColor.RED + "Spawn location for Red Team has been successfully set!");
                    break;
                case GREEN_BANNER:
                    // Set spawn for green team
                    double gx = e.getClickedBlock().getX();
                    double gy = e.getClickedBlock().getY()+1;
                    double gz = e.getClickedBlock().getZ();
                    double gpitch = player.getLocation().getPitch()-90;
                    double gyaw = player.getLocation().getYaw();
                    e.setCancelled(true);
                    SpawnLocationConfig.get().set("teams.green.x", gx);
                    SpawnLocationConfig.get().set("teams.green.y", gy);
                    SpawnLocationConfig.get().set("teams.green.z", gz);
                    SpawnLocationConfig.get().set("teams.green.pitch", gpitch);
                    SpawnLocationConfig.get().set("teams.green.yaw", gyaw);
                    SpawnLocationConfig.save();

                    player.sendMessage(ChatColor.GREEN + "Spawn location for Green Team has been successfully set!");
                    break;
                case COMPASS:
                    player.openInventory(TeamPickerGUI.createTeamPickerGUI(player));
                    e.setCancelled(true);
                    break;
            }
        }
    }
}


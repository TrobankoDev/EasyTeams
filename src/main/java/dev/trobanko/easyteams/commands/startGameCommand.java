package dev.trobanko.easyteams.commands;

import dev.trobanko.easyteams.EasyTeams;
import dev.trobanko.easyteams.GameManager.gameManager;
import dev.trobanko.easyteams.ItemBuilder;
import dev.trobanko.easyteams.PlayerHandler.TeamHandler;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;


public class startGameCommand implements CommandExecutor {

    EasyTeams plugin;

    public startGameCommand(EasyTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if(!player.hasPermission("easyteams.admin")){
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }


        ItemStack teampicker = new ItemBuilder(Material.COMPASS).setName("&aTeam Picker &7(Right Click)").get();

        //TeamHandler.restartTeamColor();

        for(Player p : Bukkit.getOnlinePlayers()){
            if (p.getGameMode().equals(GameMode.SURVIVAL)){
                p.getInventory().clear();
                p.getInventory().setItem(4,teampicker);
                p.sendMessage(ChatColor.GREEN + "Pick your team now!");
            } else if (p.getGameMode().equals(GameMode.SPECTATOR)){
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().clear();
                p.getInventory().setItem(4,teampicker);
                p.sendMessage(ChatColor.GREEN + "Pick your team now!");
            }
        }

        player.sendMessage(ChatColor.GREEN + "Passed out Team Pickers!");

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable()
        {
            int time = 5; //or any other number you want to start countdown from

            @Override
            public void run()
            {
                if (this.time == 0)
                {
                    return;
                }
                String sec = this.time > 1 ? " Seconds" : " Second";
                for (final Player player : Bukkit.getOnlinePlayers())
                {
                    player.sendMessage(ChatColor.YELLOW + "Starting in " + this.time + sec);
                    player.sendTitle(ChatColor.RED + "Starting in", ChatColor.YELLOW + "" + this.time, 10, 0, 10);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1,1);
                }

                this.time--;
            }
        }, 100L, 20L);

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                new gameManager().setupGame();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
                player.sendTitle(ChatColor.GREEN + "STARTED!", null, 10, 5, 5);
            }
        }, 200L);



        return true;
    }
}

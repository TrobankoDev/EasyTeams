package dev.trobanko.easyteams.commands;

import dev.trobanko.easyteams.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class setupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if(!player.hasPermission("easyteams.admin")){
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        ItemStack red = new ItemBuilder(Material.RED_BANNER).setName("&cSet Red Team Spawn").get();
        ItemStack green = new ItemBuilder(Material.GREEN_BANNER).setName("&aSet Green Team Spawn").get();

        Inventory inv = player.getInventory();

        inv.clear();
        inv.addItem(red);
        inv.addItem(green);

        player.sendMessage(ChatColor.GREEN + "You have been given the setup tools to add spawn locations!");

        return true;
    }
}

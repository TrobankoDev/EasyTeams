package dev.trobanko.easyteams.gui;

import dev.trobanko.easyteams.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TeamPickerGUI {

    public static Inventory createTeamPickerGUI(Player player){
        Inventory pickerGUI = Bukkit.createInventory(null, 9, ChatColor.RED + "Pick your team");

        ItemStack red_team = new ItemBuilder(Material.RED_WOOL).setName("&cRed Team").addLore("&4Join the Red Team").get();
        ItemStack green_team = new ItemBuilder(Material.GREEN_WOOL).setName("&aGreen Team").addLore("&2Join the Green Team").get();

        pickerGUI.setItem(3,red_team);
        pickerGUI.setItem(5,green_team);

        return pickerGUI;
    }

}

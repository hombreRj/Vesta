package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.managers.profile.Profile;
import gg.scenarios.vesta.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.command.Command;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.Arrays;

public class ColorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            Profile profile = Profile.getProfileFromUUID(player.getUniqueId());

            Inventory inv = Bukkit.createInventory(null, 18, ChatColor.GREEN + "Name Color");

            ItemStack white = new ItemStack(Material.WOOL);
            ItemMeta whiteMeta = white.getItemMeta();

            ItemStack orange = new ItemStack(Material.WOOL, 1, (byte)1 );
            ItemMeta orangeMeta = orange.getItemMeta();

            ItemStack magenta = new ItemStack(Material.WOOL, 1, (byte)2 );
            ItemMeta magentaMeta = magenta.getItemMeta();

            ItemStack lightblue = new ItemStack(Material.WOOL, 1, (byte)3 );
            ItemMeta lightblueMeta = lightblue.getItemMeta();

            ItemStack yellow = new ItemStack(Material.WOOL, 1, (byte)4 );
            ItemMeta yellowMeta = yellow.getItemMeta();

            ItemStack limegreen = new ItemStack(Material.WOOL, 1, (byte)5 );
            ItemMeta limegreenMeta = limegreen.getItemMeta();

            ItemStack grey = new ItemStack(Material.WOOL, 1, (byte)7 );
            ItemMeta greyMeta = grey.getItemMeta();

            ItemStack lightgrey = new ItemStack(Material.WOOL, 1, (byte)8 );
            ItemMeta lightgreyMeta = lightgrey.getItemMeta();

            ItemStack cyan = new ItemStack(Material.WOOL, 1, (byte)9 );
            ItemMeta cyanMeta = cyan.getItemMeta();

            ItemStack purple = new ItemStack(Material.WOOL, 1, (byte)10 );
            ItemMeta purpleMeta = purple.getItemMeta();

            ItemStack darkblue = new ItemStack(Material.WOOL, 1, (byte)11 );
            ItemMeta darkblueMeta = darkblue.getItemMeta();

            ItemStack green = new ItemStack(Material.WOOL, 1, (byte)13 );
            ItemMeta greenMeta = green.getItemMeta();

            ItemStack red = new ItemStack(Material.WOOL, 1, (byte)14 );
            ItemMeta redMeta = red.getItemMeta();

            whiteMeta.setDisplayName(ChatColor.WHITE + "White Username");
            whiteMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.WHITE + profile.getPlayer().getName()));
            white.setItemMeta(whiteMeta);
            orangeMeta.setDisplayName(ChatColor.GOLD + "Orange Username");
            orangeMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.GOLD + profile.getPlayer().getName()));
            orange.setItemMeta(orangeMeta);
            magentaMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Magenta Username");
            magentaMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.LIGHT_PURPLE + profile.getPlayer().getName()));
            magenta.setItemMeta(magentaMeta);
            lightblueMeta.setDisplayName(ChatColor.AQUA + "Light Blue Username");
            lightblueMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.AQUA + profile.getPlayer().getName()));
            lightblue.setItemMeta(lightblueMeta);
            yellowMeta.setDisplayName(ChatColor.YELLOW + "Yellow Username");
            yellowMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.YELLOW + profile.getPlayer().getName()));
            yellow.setItemMeta(yellowMeta);
            limegreenMeta.setDisplayName(ChatColor.GREEN + "Light Green Username");
            limegreenMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.GREEN + profile.getPlayer().getName()));
            limegreen.setItemMeta(limegreenMeta);
            greyMeta.setDisplayName(ChatColor.DARK_GRAY + "Dark Gray Username");
            greyMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.DARK_GRAY + profile.getPlayer().getName()));
            grey.setItemMeta(greyMeta);
            lightgreyMeta.setDisplayName(ChatColor.GRAY + "Light Gray Username");
            lightgreyMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.GRAY + profile.getPlayer().getName()));
            lightgrey.setItemMeta(lightgreyMeta);
            cyanMeta.setDisplayName(ChatColor.DARK_AQUA + "Cyan Username");
            cyanMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.DARK_AQUA + profile.getPlayer().getName()));
            cyan.setItemMeta(cyanMeta);
            purpleMeta.setDisplayName(ChatColor.DARK_PURPLE + "Purple Username");
            purpleMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.DARK_PURPLE + profile.getPlayer().getName()));
            purple.setItemMeta(purpleMeta);
            darkblueMeta.setDisplayName(ChatColor.DARK_BLUE + "Dark Blue Username");
            darkblueMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.DARK_BLUE + profile.getPlayer().getName()));
            darkblue.setItemMeta(darkblueMeta);
            greenMeta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green Username");
            greenMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.DARK_GREEN + profile.getPlayer().getName()));
            green.setItemMeta(greenMeta);
            redMeta.setDisplayName(ChatColor.RED + "Red Username");
            redMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.RED + profile.getPlayer().getName()));

            red.setItemMeta(redMeta);

            inv.setItem(0, white);
            inv.setItem(1, orange);
            inv.setItem(2, magenta);
            inv.setItem(3, lightblue);
            inv.setItem(4, yellow);
            inv.setItem(5, limegreen);
            inv.setItem(6, grey);
            inv.setItem(7, lightgrey);
            inv.setItem(8, cyan);
            inv.setItem(9, purple);
            inv.setItem(10, darkblue);
            inv.setItem(11, green);
            inv.setItem(12, red);

            player.openInventory(inv);
        }
        return false;
    }
}

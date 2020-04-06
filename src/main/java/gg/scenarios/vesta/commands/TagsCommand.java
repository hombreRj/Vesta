package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.managers.profile.Profile;
import gg.scenarios.vesta.managers.tags.Tag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class TagsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Profile profile = Profile.getProfileFromUUID(player.getUniqueId());
            Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Tags");
            int i = 0;
            for (Tag tag : Tag.tags.values()) {
                if (!(tag.getName().equals("none"))) {
                    ItemStack itemStack = new ItemStack(Material.NAME_TAG);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', tag.getPrefix()));
                    if (!player.hasPermission(tag.getPermission())) {
                        itemMeta.setLore(Collections.singletonList(ChatColor.RED + "Locked"));
                    } else {
                        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "Example: ", ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.translateAlternateColorCodes('&', tag.getPrefix()) + profile.getChatColor() + profile.getPlayer().getName()));

                    }
                    itemStack.setItemMeta(itemMeta);
                    inv.setItem(i, itemStack);
                    i++;
                }
                ItemStack clear = new ItemStack(Material.PAPER);
                ItemMeta clearMeta = clear.getItemMeta();
                clearMeta.setDisplayName(ChatColor.BLUE + "Reset");
                clear.setItemMeta(clearMeta);
                inv.setItem(26, clear);
            }
            player.openInventory(inv);
        }
        return false;
    }

}

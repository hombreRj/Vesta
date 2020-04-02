package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.managers.tags.Tag;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagAdminCommand implements CommandExecutor {

    //tagadmin name
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("scenarios.admin.tag")) {
                if (args.length == 2) {
                    Tag tag = new Tag(args[1], args[0]);
                    tag.setPermission("scenarios.tags." + tag.getName());
                    player.sendMessage(ChatColor.GREEN + "Successfully created the tag " + args[0]);
                    tag.save();
                } else {
                    player.sendMessage(ChatColor.RED + "/tagadmin <name>  <prefix>");
                }
            }
        }
        return false;
    }
}

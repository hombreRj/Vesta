package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.managers.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("reply")) {
            if (args.length > 0) {
                if (Vesta.getInstance().getServerManager().recentlyMessaged.containsKey(player)) {
                    if (Vesta.getInstance().getServerManager().recentlyMessaged.get(player).getPlayer() != null) {
                        Player target = Vesta.getInstance().getServerManager().recentlyMessaged.get(player);
                        StringBuilder message = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        Profile p = Profile.getProfileFromUUID(player.getUniqueId());
                        Profile t = Profile.getProfileFromUUID(target.getUniqueId());
                        player.sendMessage(ChatColor.GRAY + "(To " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', t.getPrefix())+target.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
                        target.sendMessage(ChatColor.GRAY + "(From " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', p.getPrefix()) +player.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
                        Vesta.getInstance().getServerManager().recentlyMessaged.put(target, player);
                    } else {
                        player.sendMessage(ChatColor.RED + "That player is no longer online!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "No recent messages!");
                }
            } else {
                player.sendMessage(ChatColor.RED + " Invalid Usage use /reply <message>");
            }

        }
        return false;
    }
}
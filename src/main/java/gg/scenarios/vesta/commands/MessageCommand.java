package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.managers.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    @SuppressWarnings("deprecated")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("message")) {
            if (args.length >= 2) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    Profile p = Profile.getProfileFromUUID(player.getUniqueId());
                    Profile t = Profile.getProfileFromUUID(target.getUniqueId());
                    player.sendMessage(ChatColor.GRAY + "(To " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', t.getPrefix())+target.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
                    target.sendMessage(ChatColor.GRAY + "(From " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', p.getPrefix()) +player.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
                    Vesta.getInstance().getServerManager().recentlyMessaged.put(target, player);
                } else {
                    player.sendMessage(ChatColor.RED + args[0] + " Is not online!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage.");
            }
        }
        return false;
    }
}
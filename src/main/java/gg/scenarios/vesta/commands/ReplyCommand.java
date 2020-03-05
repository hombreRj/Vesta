package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
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
                    if (Vesta.getInstance().getServerManager().recentlyMessaged.get(player) != null) {
                        Player target = Vesta.getInstance().getServerManager().recentlyMessaged.get(player);
                        StringBuilder message = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        player.sendMessage(ChatColor.GRAY + "(To " + ChatColor.RESET + target.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
                        target.sendMessage(ChatColor.GRAY + "(From " + ChatColor.RESET + player.getDisplayName() + ChatColor.GRAY + ") " + message.toString());
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
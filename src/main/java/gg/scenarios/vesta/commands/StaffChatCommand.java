package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    private Vesta vesta;

    public StaffChatCommand(Vesta vesta) {
        this.vesta = vesta;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot use this command..");
            return false;
        }
        Player player = (Player) sender;
        if (player.hasPermission("vesta.staff")){
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Please enter a valid message");
            }else{
                String message =String.join(" ", args);
                String msg = ChatUtil.format("&8[&5Staff&8] &7[&d" + vesta.getServerManager().getServerName() + "&7] &7[&3" + player.getName() + "&7]: &f" + message);
                vesta.getRedis().getClient().getTopic("staff").publishAsync("vesta.staff;" +msg);
            }
        }else{
            player.sendMessage(ChatColor.RED +"You do not have permission.");
        }

        return false;
    }
}

package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand implements CommandExecutor {

    private Vesta vesta;

    public AnnounceCommand(Vesta vesta) {
        this.vesta = vesta;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot use this command..");
            return false;
        }
        Player player = (Player) sender;
        if (player.hasPermission("vesta.admin")){
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Please enter a valid message");
            }else{
                String message =String.join(" ", args);
                String msg = ChatUtil.format("&8[&5Alert&8] &f" + message);
                vesta.getRedis().getClient().getTopic("global").publishAsync(msg);
            }
        }else{
            player.sendMessage(ChatColor.RED +"You do not have permission.");
        }

        return false;
    }
}

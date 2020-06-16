package gg.scenarios.vesta.commands;

import gg.scenarios.vesta.Vesta;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameModeCommand implements CommandExecutor {

    private final Vesta vesta;
    private HashMap<String, GameMode> gameModeHashMap;

    public GameModeCommand(Vesta vesta) {
        this.vesta = vesta;
        gameModeHashMap = new HashMap<>();
        gameModeHashMap.put("s", GameMode.SURVIVAL);
        gameModeHashMap.put("sp", GameMode.SPECTATOR);
        gameModeHashMap.put("survival", GameMode.SURVIVAL);
        gameModeHashMap.put("c", GameMode.CREATIVE);
        gameModeHashMap.put("creative", GameMode.CREATIVE);
        gameModeHashMap.put("1", GameMode.CREATIVE);
        gameModeHashMap.put("0", GameMode.SURVIVAL);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("vesta.gamemode")){
                if (args.length == 0){
                    player.sendMessage(ChatColor.RED + "/" + label + " <gamemode>");
                }else{
                    if (gameModeHashMap.containsKey(args[0])){
                        player.sendMessage(ChatColor.GRAY + "Updated your gamemode to " + ChatColor.GOLD + gameModeHashMap.get(args[0]));
                        player.setGameMode(gameModeHashMap.get(args[0]));
                    }
                }
            }
        }

        return false;
    }
}

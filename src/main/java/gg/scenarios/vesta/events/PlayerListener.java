package gg.scenarios.vesta.events;

import gg.scenarios.vesta.Vesta;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListener implements Listener {

    private Vesta vesta = Vesta.getInstance();


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if (event.getPlayer() instanceof Player){
            Player player = event.getPlayer();
            if (!(vesta.getServerManager().getOps().contains(player.getUniqueId()))){
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You are not able to have OP on this server!");
            }else{
                player.setOp(true);
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your OP powers have been granted!");

            }
        }
    }
}

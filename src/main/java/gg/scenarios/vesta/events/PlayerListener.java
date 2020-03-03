package gg.scenarios.vesta.events;

import com.google.gson.JsonElement;
import gg.scenarios.vesta.Vesta;

import gg.scenarios.vesta.managers.profile.Profile;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;


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

            Document found = vesta.getProfiles().find(new Document("uuid",event.getPlayer().getUniqueId().toString())).first();
            if (found != null){
                Profile profile = new Profile(player.getUniqueId());
                profile.setLatestIP(player.getAddress().toString());
                profile.setChatColor(ChatColor.getByChar(found.getString("chatColor")));
                profile.setIps(vesta.getGson().fromJson((JsonElement) found.get("ips"), profile.getIps().getClass()));
                player.sendMessage(ChatColor.GREEN + "Loaded old profile.");
            }else{
                Profile profile = new Profile(player.getUniqueId());
                profile.setLatestIP(player.getAddress().toString());
                profile.setChatColor(ChatColor.GREEN);
                profile.getIps().add(profile.getLatestIP());
                player.sendMessage(ChatColor.GREEN + "Created new profile.");
            }
        }
    }
}

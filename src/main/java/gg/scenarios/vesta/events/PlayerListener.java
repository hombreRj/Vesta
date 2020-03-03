package gg.scenarios.vesta.events;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import gg.scenarios.vesta.Vesta;

import gg.scenarios.vesta.managers.profile.Profile;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;


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
                ArrayList<String> ips= (ArrayList<String>) vesta.getGson().fromJson(found.getString("ips"),
                        new TypeToken<ArrayList<String>>() {
                        }.getType()) ;
                ips.stream().forEach(s -> {
                    profile.getIps().add(s);
                });
                player.sendMessage(ChatColor.GREEN + "Loaded old profile.");
            }else{
                Profile profile = new Profile(player.getUniqueId());
                profile.setLatestIP(player.getAddress().toString());
                profile.setChatColor(ChatColor.GREEN);
                profile.getIps().add(profile.getLatestIP());
                profile.addToDatabase();
                player.sendMessage(ChatColor.GREEN + "Created new profile.");
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Profile.getProfileFromUUID(event.getPlayer().getUniqueId()).update();
        Profile.users.remove(event.getPlayer().getUniqueId());
    }
}

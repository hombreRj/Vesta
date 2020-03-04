package gg.scenarios.vesta.listeners;

import com.google.gson.reflect.TypeToken;
import gg.scenarios.vesta.Vesta;

import gg.scenarios.vesta.managers.profile.Profile;
import net.milkbowl.vault.chat.Chat;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;


public class PlayerListener implements Listener {

    private Vesta vesta = Vesta.getInstance();


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if (event.getPlayer() instanceof Player){
            Player player = event.getPlayer();
            if (player.isOp()){
            if (!(vesta.getServerManager().getOps().contains(player.getUniqueId()))){
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You are not able to have OP on this server!");
            }else {
                player.setOp(true);
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your OP powers have been granted!");
            }
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

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Profile profile = Profile.getProfileFromUUID(event.getPlayer().getUniqueId());
        profile.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + profile.getChatColor() + profile.getPlayer().getName());
        event.setFormat(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + profile.getChatColor() + profile.getPlayer().getName() + ChatColor.GRAY  +":" + ChatColor.WHITE+ "%s".replace("%s", event.getMessage()));
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getName().equals(ChatColor.GREEN + "Name Color")){
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "White Username")){
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.WHITE, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Orange Username")){
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GOLD, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Light Blue Username")){
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.AQUA, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Yellow Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.YELLOW, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Light Green Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GREEN, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "Dark Gray Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_GRAY, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Light Gray Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GRAY, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Cyan Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_AQUA, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Purple Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_PURPLE, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Dark Blue Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_BLUE, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Dark Green Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_GREEN, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Red Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.RED, true));
                    event.setCancelled(true);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Magenta Username")) {
                    Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.LIGHT_PURPLE, true));
                    event.setCancelled(true);
                }
            }
        }
    }

}
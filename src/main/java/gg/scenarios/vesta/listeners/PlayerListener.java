package gg.scenarios.vesta.listeners;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import gg.scenarios.vesta.Vesta;

import gg.scenarios.vesta.managers.profile.Profile;
import gg.scenarios.vesta.managers.tags.Tag;
import gg.scenarios.vesta.utils.ChatUtil;
import gg.scenarios.vesta.utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class PlayerListener implements Listener {

    private Vesta vesta = Vesta.getInstance();


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) throws MalformedURLException {
        Player player = event.getPlayer();
        if (player.hasPermission("vesta.staff")){
            String msg = ChatUtil.format("&8[&5Staff&8] &7[&3" + player.getName() + "&7]&b has connected to server: &9" + vesta.getServerManager().getServerName());
            vesta.getRedis().getClient().getTopic("staff").publishAsync("vesta.staff;" +msg);
        }
        if (player.isOp()) {
            if (!(vesta.getServerManager().getOps().contains(player.getUniqueId()))) {
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You are not able to have OP on this server!");
            } else {
                player.setOp(true);
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your OP powers have been granted!");
            }
        }
        Document found = vesta.getProfiles().find(new Document("uuid", event.getPlayer().getUniqueId().toString())).first();
        if (found != null) {
            Profile profile = new Profile(player.getUniqueId());
            profile.setLatestIP(Utils.ip(player.getAddress().toString()));
            profile.getIps().add(profile.getLatestIP());
            if (found.getString("tag").equals("none")) {
                profile.setTagUUID(Tag.getTagByName("none").getUuid());
                profile.setTag(Tag.getTagByName("none"));
            } else {
                profile.setTagUUID(Objects.requireNonNull(Tag.getTagByName(found.getString("tag"))).getUuid());
                profile.setTag(Tag.getTagByUUID(profile.getTagUUID()));
            }
            profile.setChatColor(ChatColor.getByChar(found.getString("chatColor")));
            TypeToken<List<String>> token = new TypeToken<List<String>>() {
            };
            List<String> ips = vesta.getGson().fromJson(found.getString("ips"), token.getType());
            ips.forEach(s -> {
                try {
                    profile.getIps().add(Utils.ip(s));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });

            player.sendMessage(ChatColor.GREEN + "Loaded old profile.");
        } else {
            Profile profile = new Profile(player.getUniqueId());
            profile.setLatestIP(Utils.ip(player.getAddress().toString()));
            profile.setChatColor(ChatColor.GREEN);
            profile.getIps().add(Utils.ip(profile.getLatestIP()));
            profile.setTagUUID(Tag.getTagByName("none").getUuid());
            profile.setTag(Tag.getTagByName("none"));
            profile.addToDatabase();
            player.sendMessage(ChatColor.GREEN + "Created new profile.");
        }
    }


    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Profile.getProfileFromUUID(event.getPlayer().getUniqueId()).update();
        Profile.users.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().startsWith("/me") && !player.hasPermission("uhc.admin")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You do not have access to that command.");
        }

        if (event.getMessage().startsWith("/bukkit:") && !player.hasPermission("uhc.admin")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You do not have access to that command.");
        }

        if (event.getMessage().startsWith("/pl") && !event.getMessage().startsWith("/playsound")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "For information about plugins message Rj on discord");
        }

        if (event.getMessage().startsWith("/plugins")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "For information about plugins message Rj on discord");
        }

        if (event.getMessage().startsWith("/minecraft:") && !player.hasPermission("uhc.admin")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You do not have access to that command.");
        }

        if (event.getMessage().startsWith("//calc")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You have been reported to all online staff members for attempting to crash the server");
            for (Player mods : Bukkit.getOnlinePlayers()) {
                if (mods.hasPermission("vesta.staff.admin")) {
                    mods.sendMessage(ChatColor.GREEN + player.getName() + " has attempted to crash the server! command: //calc");
                }
            }
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().startsWith("!")) {
            if (player.hasPermission("vesta.staff")) {
                String msg = ChatUtil.format("&8[&5Staff&8] &7[&d" + vesta.getServerManager().getServerName() + "&7] &7[&3" + player.getName() + "&7]: &f" + event.getMessage().substring(1));
                vesta.getRedis().getClient().getTopic("staff").publishAsync("vesta.staff;" + msg);
                event.setCancelled(true);
            }
        }


        Profile profile = Profile.getProfileFromUUID(event.getPlayer().getUniqueId());
        if (profile.getTag().getName().equals("none")) {
            profile.getPlayer().setDisplayName(profile.getPlayer().getName());
            event.setFormat(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + profile.getChatColor() + profile.getPlayer().getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + "%s".replace("%s", event.getMessage()));
        } else {
            profile.getPlayer().setDisplayName(profile.getPlayer().getName());
            event.setFormat(ChatColor.translateAlternateColorCodes('&', profile.getPrefix()) + ChatColor.translateAlternateColorCodes('&', profile.getTag().getPrefix() + " ") + profile.getChatColor() + profile.getPlayer().getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + "%s".replace("%s", event.getMessage()));

        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            try {
                if (event.getInventory().getName().equals(ChatColor.GREEN + "Name Color")) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "White Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.WHITE, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Orange Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GOLD, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Light Blue Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.AQUA, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Yellow Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.YELLOW, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Light Green Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GREEN, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "Dark Gray Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_GRAY, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Light Gray Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.GRAY, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Cyan Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_AQUA, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Purple Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_PURPLE, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Dark Blue Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_BLUE, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Dark Green Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.DARK_GREEN, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Red Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.RED, true));
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Magenta Username")) {
                        Profile.getProfileFromUUID(player.getUniqueId(), profile -> profile.setChatColor(ChatColor.LIGHT_PURPLE, true));
                        event.setCancelled(true);
                    }
                } else if (event.getInventory().getName().equals(ChatColor.GREEN + "Tags")) {
                    if (event.getCurrentItem().getType() == Material.NAME_TAG) {
                        Tag tag = Tag.getTagByPrefix(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                        assert tag != null;
                        if (!player.hasPermission(tag.getPermission())) {
                            player.sendMessage(ChatColor.RED + "You don't have access to the " + ChatColor.translateAlternateColorCodes('&', tag.getPrefix()) + ChatColor.RED + "Tag");
                        } else {
                            Profile profile = Profile.getProfileFromUUID(player.getUniqueId());
                            profile.setTagUUID(tag.getUuid());
                            profile.setTag(tag);
                            player.sendMessage(ChatColor.GREEN + "You have equipped the " + ChatColor.translateAlternateColorCodes('&', tag.getPrefix()) + ChatColor.GREEN + "Tag");
                            player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
                        }
                        event.setCancelled(true);
                    } else if (event.getCurrentItem().getType() == Material.PAPER) {
                        Tag tag = Tag.getTagByName("none");
                        Profile profile = Profile.getProfileFromUUID(player.getUniqueId());
                        assert tag != null;
                        profile.setTagUUID(tag.getUuid());
                        profile.setTag(tag);
                        player.sendMessage(ChatColor.GREEN + "You have disabled your tag");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
                        event.setCancelled(true);
                    }
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}

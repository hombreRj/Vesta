package gg.scenarios.vesta.managers.profile;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import gg.scenarios.vesta.Vesta;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Getter
@Setter
public class Profile {

    public static HashMap<UUID, Profile> users = new HashMap<>();
    private Vesta vesta = Vesta.getInstance();

    private UUID uuid;
    private Player player = null;
    private String username;
    private ChatColor chatColor;
    private String prefix;
    private String latestIP;
    private List<String> ips;
    private UUID tagUUID = null;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        ips = new ArrayList<>();
        player = Bukkit.getPlayer(uuid);
        username = player.getName();
        prefix = vesta.getChat().getPlayerPrefix(player);
        users.put(uuid, this);

    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
        player.sendMessage(chatColor + "You username has been updated!");
    }
    public void setChatColor(ChatColor chatColor, boolean b) {
        this.chatColor = chatColor;
        player.sendMessage(chatColor + "You username has been updated!");
    }

    public static Profile getProfileFromUUID(UUID uuid){
        return users.get(uuid);
    }
    public static void getProfileFromUUID(UUID uuid, Consumer<Profile> callback){
        callback.accept(getProfileFromUUID(uuid));
    }

    public void addToDatabase(){
        Profile profile = this;
        Document document = new org.bson.Document();
        document.put("uuid", profile.getUuid().toString());
        document.put("name", profile.getPlayer().getName());
        document.put("chatColor", this.chatColor.getChar());
        document.put("prefix", prefix);
        document.put("ip", this.latestIP);
        document.put("ips", vesta.getGson().toJson(ips));
        vesta.getProfiles().insertOne(document);
    }

    public void update() {
        Profile profile = this;
        Vesta.getInstance().getProfiles().updateMany(
                Filters.eq("uuid", uuid.toString()),
                Updates.combine(
                        Updates.set("name", profile.getPlayer().getName()),
                        Updates.set("chatColor", this.chatColor.getChar()),
                        Updates.set("prefix", this.prefix),
                        Updates.set("ip", profile.getLatestIP()),
                        Updates.set("ips", vesta.getGson().toJson(ips))));
    }


}

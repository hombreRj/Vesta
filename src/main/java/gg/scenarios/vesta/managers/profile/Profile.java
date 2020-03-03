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

@Getter
@Setter
public class Profile {

    private static HashMap<UUID, Profile> users = new HashMap<>();
    private Vesta vesta = Vesta.getInstance();

    private UUID uuid;
    private Player player = Bukkit.getPlayer(uuid);
    private String username;
    private ChatColor chatColor;
    private String prefix = vesta.getChat().getPlayerPrefix(player);
    private String latestIP;
    private List<String> ips;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        username = player.getName();
        ips = new ArrayList<>();
        users.put(uuid, this);
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

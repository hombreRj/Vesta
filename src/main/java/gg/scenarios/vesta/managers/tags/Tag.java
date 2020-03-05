package gg.scenarios.vesta.managers.tags;

import com.mongodb.client.model.Filters;
import gg.scenarios.vesta.Vesta;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class Tag {


    public static HashMap<UUID, Tag> tags = new HashMap<>();
    private Vesta vesta = Vesta.getInstance();

    private UUID uuid;
    private String name;
    private String prefix;
    private String permission;

    public Tag(UUID uuid, String prefix, String name) {
        this.uuid = uuid;
        this.prefix = prefix;
        this.name = name;
        tags.put(uuid, this);
    }


    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }

    public Tag(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
        this.uuid = UUID.randomUUID();
        tags.put(uuid, this);
    }

    public boolean delete(Tag rank) {
        try {
            tags.remove(rank.getUuid());
            vesta.getTags().findOneAndDelete(Filters.eq("UUID", rank.getUuid().toString()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void load() {
        for (Document obj : Vesta.getInstance().getTags().find()) {
            Tag rank = new Tag(UUID.fromString(obj.getString("uuid")), obj.getString("prefix"), obj.getString("name"));
            rank.setPermission(obj.getString("permission"));
            tags.put(UUID.fromString(obj.getString("uuid")), rank);
        }
        System.out.println("All ranks have been loaded");
    }

    public void save() {
        try {
            Document document = new Document();

            document.put("uuid", uuid.toString());
            document.put("prefix", prefix);
            document.put("name", name);
            document.put("permission", permission);
            vesta.getTags().insertOne(document);
        } catch (Exception e) {
            System.out.println("");
        }
    }

    public static Tag getTagByUUID(UUID uuid) {
        return tags.get(uuid);
    }

    public static Tag getTagByName(String name) {
        for (Tag tag : tags.values()) {
            if (tag.getName().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        return null;
    }
    public static Tag getTagByPrefix(String name) {
        for (Tag tag : tags.values()) {
            if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', tag.getPrefix())).equals(name)) {
                return tag;
            }
        }
        return null;
    }
}

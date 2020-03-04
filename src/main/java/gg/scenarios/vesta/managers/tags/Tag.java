package gg.scenarios.vesta.managers.tags;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class Tag {

    public static HashMap<UUID, Tag> tags = new HashMap<>();

    private UUID uuid;
    private String name;
    private String prefix;
    private String permission;

    public Tag(UUID uuid) {
        this.uuid = uuid;
    }

    public Tag(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
        this.uuid = UUID.randomUUID();
        tags.put(uuid, this);
    }
}

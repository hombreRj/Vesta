package gg.scenarios.vesta.managers;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.enums.ServerType;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class ServerManager {

    private Vesta vesta = Vesta.getInstance();
    private FileConfiguration config = vesta.getConfig();

    private ServerType serverType;
    private List<UUID> ops = new ArrayList<>();
    public HashMap<Player, Player> recentlyMessaged;

    public boolean isDev() {
        return serverType.equals(ServerType.DEV);
    }

    public ServerManager() {
        this.serverType = ServerType.valueOf(config.getString("server.type"));
        recentlyMessaged = new HashMap<>();

        for (String uuid : config.getStringList("server.ops")) {
            ops.add(UUID.fromString(uuid));
        }
    }
}

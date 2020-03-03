package gg.scenarios.vesta.managers;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.enums.ServerType;
import lombok.Getter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ServerManager {

    private Vesta vesta = Vesta.getInstance();
    private FileConfiguration config = vesta.getConfig();

    private ServerType serverType;
    private List<UUID> ops = new ArrayList<>();


    public ServerManager() {
        this.serverType = ServerType.valueOf(config.getString("server.type"));
        for (String uuid : config.getStringList("server.ops")){
            ops.add(UUID.fromString(uuid));
        }
    }
}

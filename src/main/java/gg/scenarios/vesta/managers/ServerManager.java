package gg.scenarios.vesta.managers;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.enums.ServerType;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.UUID;

public class ServerManager {

    private Vesta vesta = Vesta.getInstance();
    private FileConfiguration config = vesta.getConfig();

    private ServerType serverType;
    private ArrayList<UUID> ops = new ArrayList<>();


    public ServerManager() {
        this.serverType = ServerType.valueOf(config.getString("server.type"));
    }
}

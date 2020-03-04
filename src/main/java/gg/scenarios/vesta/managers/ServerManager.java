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
    private ArrayList<ChatColor> colors;

    public boolean isDev(){
        if (serverType.equals(ServerType.DEV)){
            return true;
        }else{
            return false;
        }
    }

    public ServerManager() {
        this.serverType = ServerType.valueOf(config.getString("server.type"));
        recentlyMessaged = new HashMap<>();
        colors = new ArrayList<>();
        colors.add(ChatColor.BLACK);
        colors.add(ChatColor.GOLD);
        colors.add(ChatColor.GRAY);
        colors.add(ChatColor.YELLOW);
        colors.add(ChatColor.BLUE);
        colors.add(ChatColor.AQUA);
        colors.add(ChatColor.RED);
        colors.add(ChatColor.GREEN);
        colors.add(ChatColor.DARK_BLUE);
        colors.add(ChatColor.DARK_PURPLE);
        colors.add(ChatColor.DARK_AQUA);
        colors.add(ChatColor.DARK_GRAY);
        colors.add(ChatColor.DARK_GREEN);
        colors.add(ChatColor.DARK_RED);
        colors.add(ChatColor.LIGHT_PURPLE);
        colors.add(ChatColor.DARK_PURPLE);

        for (String uuid : config.getStringList("server.ops")){
            ops.add(UUID.fromString(uuid));
        }
    }
}

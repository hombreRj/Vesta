package gg.scenarios.vesta;

import gg.scenarios.vesta.events.PlayerListener;
import gg.scenarios.vesta.managers.ServerManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Vesta extends JavaPlugin {

    @Getter public static Vesta instance;
    @Getter public ServerManager serverManager;

    @Override
    public void onEnable() {
        instance = this;
        serverManager = new ServerManager();
        registerEvents();
        registerCommands();
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
    private void registerCommands(){

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

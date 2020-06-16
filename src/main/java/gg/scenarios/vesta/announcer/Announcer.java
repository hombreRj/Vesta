package gg.scenarios.vesta.announcer;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Announcer {

    List<String> messages;
    private final String prefix = "&8[&9TIP&8] &f";

    public Announcer(Vesta vesta) {
        System.out.println("Starting Announcer");
        messages = new ArrayList<>();
        messages.addAll(vesta.getServerManager().getAnnounceMessages());
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(vesta, () ->{
            Bukkit.broadcastMessage(ChatUtil.format(prefix + messages.get(new Random().nextInt(messages.size()))));
        }, 0,20*5*60);
    }
}

package gg.scenarios.vesta.database;

import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.utils.ChatUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import static org.bukkit.Bukkit.getConsoleSender;

@Getter
public class Redis {

    private RedissonClient client;
    private final Vesta vesta;

    public Redis(Vesta vesta) {
        this.vesta = vesta;

        Config config = new Config();
        config.useSingleServer().setAddress("157.245.227.156:6379");
        config.useSingleServer().setPassword("Mondosbeach1");

        this.client = Redisson.create(config);

        client.getTopic("global").addListener((s, o) -> {
            Bukkit.broadcastMessage(ChatUtil.format(o.toString()));
        });

        this.client.getTopic("staff").addListener((channel, msg) -> {
            String[] args = msg.toString().split(";");
            String permission = args[0];
            String message = args[1];
            Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(permission)).forEach(player -> player.sendMessage(message));
        });

        getConsoleSender().sendMessage(ChatUtil.format("&aRedis successfully connected."));

    }
}

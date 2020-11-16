package gg.scenarios.vesta.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gg.scenarios.vesta.Vesta;
import gg.scenarios.vesta.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    private final Vesta vesta;

    public HubCommand(Vesta vesta) {
        this.vesta = vesta;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("hub")){
            commandSender.sendMessage(ChatUtil.format("&aConnecting you to hub..."));
            sendToProxy((Player) commandSender, "hub");
        }
        return false;
    }

    public void sendToProxy(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF(server);

        player.sendPluginMessage(vesta, "BungeeCord", out.toByteArray());
    }
}

package gg.scenarios.vesta.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ChatUtil {

    public String format(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Getter
    private final String prefix = format("&5[Permissions] &7");

}

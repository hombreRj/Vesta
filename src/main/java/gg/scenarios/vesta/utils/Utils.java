package gg.scenarios.vesta.utils;

import com.sun.jndi.toolkit.url.Uri;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.net.MalformedURLException;

public class Utils {


    public static String translateChatColorToString(ChatColor chatColor) {
        switch (chatColor) {
            case AQUA:
                return "AQUA";
            case BLACK:
                return "BLACK";
            case BLUE:
                return "BLUE";
            case DARK_AQUA:
                return "DARK_AQUA";
            case DARK_BLUE:
                return "DARK_BLUE";
            case DARK_GRAY:
                return "DARK_GRAY";
            case DARK_GREEN:
                return "DARK_GREEN";
            case DARK_PURPLE:
                return "DARK_PURPLE";
            case DARK_RED:
                return "DARK_RED";
            case GOLD:
                return "GOLD";
            case GRAY:
                return "GRAY";
            case GREEN:
                return "GREEN";
            case LIGHT_PURPLE:
                return "LIGHT_PURPLE";
            case RED:
                return "RED";
            case WHITE:
                return "WHITE";
            case YELLOW:
                return "YELLOW";
            default:
                break;
        }

        return null;
    }

    public static ChatColor translateChatColorToColor(DyeColor chatColor) {
        switch (chatColor) {
            case WHITE:
                return ChatColor.WHITE;
            case ORANGE:
            case RED:
                return ChatColor.RED;
            case MAGENTA:
                return ChatColor.LIGHT_PURPLE;
            case LIGHT_BLUE:
            case BLUE:
                return ChatColor.BLUE;
            case YELLOW:
                return ChatColor.YELLOW;
            case LIME:
                return ChatColor.GREEN;
            case PINK:
                return ChatColor.BLACK;
            case GRAY:
                return ChatColor.GRAY;
            case CYAN:
                return ChatColor.AQUA;
            case PURPLE:
                return ChatColor.DARK_PURPLE;
            case BROWN:
                return ChatColor.DARK_BLUE;
            case GREEN:
                return ChatColor.DARK_GREEN;
            case BLACK:
                return ChatColor.BLACK;
            default:
                break;
        }

        return null;
    }


    public static String ip(String s) throws MalformedURLException {
        String ip = s;
        Uri uri = new Uri("http://" + ip);
        return ip = ip.split(":")[0];
    }
}

package gg.scenarios.vesta.utils;

import com.sun.jndi.toolkit.url.Uri;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.net.MalformedURLException;

public class Utils {


    public static String ip(String s) throws MalformedURLException {
        Uri uri = new Uri("http://" + s);
        return s.split(":")[0];
    }
}

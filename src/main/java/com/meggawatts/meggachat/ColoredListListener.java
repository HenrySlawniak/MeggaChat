package com.meggawatts.meggachat;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;

public class ColoredListListener implements Listener {

    PermissionManager manager = Bukkit.getServicesManager().load(PermissionManager.class);
    public static final Logger log = Logger.getLogger("Minecraft");

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        String oldname = event.getPlayer().getName();
        String newname = oldname;
        if (joiner.getName().length() == 15) {
            int len = oldname.length();
            int removechars = len - 1;
            newname = oldname.substring(0, removechars);
        }
        if (joiner.getName().length() == 16) {
            int len = oldname.length();
            int removechars = len - 2;
            newname = oldname.substring(0, removechars);
        }
        ColorName(joiner, newname);
    }

    public void ColorName(Player player, String name) {
        PermissionUser target = manager.getUser(player.getName());
        String color = "4";
        //String color = target.getOption("ListColor");
        player.setPlayerListName("§" + color + name);
    }
}

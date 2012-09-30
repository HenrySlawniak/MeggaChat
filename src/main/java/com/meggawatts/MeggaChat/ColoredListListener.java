package com.meggawatts.MeggaChat;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.PermissionUser;

public class ColoredListListener implements Listener {

    public static final Logger log = Logger.getLogger("Minecraft");

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        String oldname = event.getPlayer().getName();
        String newname = oldname;
        if (joiner.getName().length() == 15) {
            int len = oldname.length();
            int removechars = len -1;
            newname = oldname.substring(0, removechars);
        }
        if (joiner.getName().length() == 16) {
            int len = oldname.length();
            int removechars = len -2;
            newname = oldname.substring(0, removechars);
        }
        ColorName(joiner, newname);
    }

    public void ColorName(Player player, String name) {
        PermissionUser target = PermissionsEx.getUser(player);
        if (target.getOption("ListColor").equals("0")) {
            player.setPlayerListName(ChatColor.BLACK + name);
        }
        if (target.getOption("ListColor").equals("1")) {
            player.setPlayerListName(ChatColor.DARK_BLUE + name);
        }
        if (target.getOption("ListColor").equals("2")) {
            player.setPlayerListName(ChatColor.DARK_GREEN + name);
        }
        if (target.getOption("ListColor").equals("3")) {
            player.setPlayerListName(ChatColor.DARK_AQUA + name);
        }
        if (target.getOption("ListColor").equals("4")) {
            player.setPlayerListName(ChatColor.DARK_RED + name);
        }
        if (target.getOption("ListColor").equals("5")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + name);
        }
        if (target.getOption("ListColor").equals("6")) {
            player.setPlayerListName(ChatColor.GOLD + name);
        }
        if (target.getOption("ListColor").equals("7")) {
            player.setPlayerListName(ChatColor.GRAY + name);
        }
        if (target.getOption("ListColor").equals("8")) {
            player.setPlayerListName(ChatColor.DARK_GRAY + name);
        }
        if (target.getOption("ListColor").equals("9")) {
            player.setPlayerListName(ChatColor.BLUE + name);
        }
        if (target.getOption("ListColor").equals("a")) {
            player.setPlayerListName(ChatColor.GREEN + name);
        }
        if (target.getOption("ListColor").equals("b")) {
            player.setPlayerListName(ChatColor.AQUA + name);
        }
        if (target.getOption("ListColor").equals("c")) {
            player.setPlayerListName(ChatColor.RED + name);
        }
        if (target.getOption("ListColor").equals("d")) {
            player.setPlayerListName(ChatColor.LIGHT_PURPLE + name);
        }
        if (target.getOption("ListColor").equals("e")) {
            player.setPlayerListName(ChatColor.YELLOW + name);
        }
        if (target.getOption("ListColor").equals("f")) {
            player.setPlayerListName(ChatColor.WHITE + name);
        }
        if (target.getOption("ListColor").equals("k")) {
            player.setPlayerListName(ChatColor.MAGIC + name);
        }
        if (target.getOption("ListColor").equals("l")) {
            player.setPlayerListName(ChatColor.BOLD + name);
        }
        if (target.getOption("ListColor").equals("m")) {
            player.setPlayerListName(ChatColor.STRIKETHROUGH + name);
        }
        if (target.getOption("ListColor").equals("n")) {
            player.setPlayerListName(ChatColor.UNDERLINE + name);
        }
        if (target.getOption("ListColor").equals("o")) {
            player.setPlayerListName(ChatColor.ITALIC + name);
        }
        if (target.getOption("ListColor").equals("r")) {
            player.setPlayerListName(ChatColor.RESET + name);
        }


    }
}

package com.meggawatts.MeggaChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ColoredListListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("meggachat.color.0")) {
            player.setPlayerListName(ChatColor.BLACK + player.getName());
        }
        if (player.hasPermission("meggachat.color.1")) {
            player.setPlayerListName(ChatColor.DARK_BLUE + player.getName());
        }
        if (player.hasPermission("meggachat.color.2")) {
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
        }
        if (player.hasPermission("meggachat.color.3")) {
            player.setPlayerListName(ChatColor.DARK_AQUA + player.getName());
        }
        if (player.hasPermission("meggachat.color.4")) {
            player.setPlayerListName(ChatColor.DARK_RED + player.getName());
        }
        if (player.hasPermission("meggachat.color.5")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
        }
        if (player.hasPermission("meggachat.color.6")) {
            player.setPlayerListName(ChatColor.GOLD + player.getName());
        }
        if (player.hasPermission("meggachat.color.7")) {
            player.setPlayerListName(ChatColor.GRAY + player.getName());
        }
        if (player.hasPermission("meggachat.color.8")) {
            player.setPlayerListName(ChatColor.DARK_GRAY + player.getName());
        }
        if (player.hasPermission("meggachat.color.9")) {
            player.setPlayerListName(ChatColor.BLUE + player.getName());
        }
        if (player.hasPermission("meggachata.color.a")) {
            player.setPlayerListName(ChatColor.GREEN + player.getName());
        }
        if (player.hasPermission("meggachat.color.b")) {
            player.setPlayerListName(ChatColor.AQUA + player.getName());
        }
        if (player.hasPermission("meggachat.color.c")) {
            player.setPlayerListName(ChatColor.RED + player.getName());
        }
        if (player.hasPermission("meggachat.color.d")) {
            player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
        }
        if (player.hasPermission("meggachat.color.e")) {
            player.setPlayerListName(ChatColor.YELLOW + player.getName());
        }
        if (player.hasPermission("meggachat.color.f")) {
            player.setPlayerListName(ChatColor.WHITE + player.getName());
        }
        if (player.hasPermission("meggachat.color.k")) {
            player.setPlayerListName(ChatColor.MAGIC + player.getName());
        }
        if (player.hasPermission("meggachat.color.l")) {
            player.setPlayerListName(ChatColor.BOLD + player.getName());
        }
        if (player.hasPermission("meggachat.color.m")) {
            player.setPlayerListName(ChatColor.STRIKETHROUGH + player.getName());
        }
        if (player.hasPermission("meggachat.color.n")) {
            player.setPlayerListName(ChatColor.UNDERLINE + player.getName());
        }
        if (player.hasPermission("meggachat.color.o")) {
            player.setPlayerListName(ChatColor.ITALIC + player.getName());
        }
        if (player.hasPermission("meggachat.color.r")) {
            player.setPlayerListName(ChatColor.RESET + player.getName());
        }
    }
}

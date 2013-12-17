package com.meggawatts.meggachat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {

    private final String delim = "&";
    private final String colors = "0123456789abcdefklmnor";
    
    
    @EventHandler(priority = EventPriority.HIGH)
    public void signChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        for (int forInt = 0; forInt < 4; forInt++) {
            if (event.getLine(forInt).isEmpty()) {
                continue;
            }
            String[] splitLine = event.getLine(forInt).split(delim);
            String nextLine = splitLine[0];
            for (int i = 1; i < splitLine.length; i++) {
                int col;
                if (splitLine[i].length() == 0 
                        || (col = colors.indexOf(splitLine[i].toLowerCase().charAt(0))) == -1
                        || (!checkAuth(player, col))
                        || splitLine[i].length() <= 1) {
                    nextLine += delim;
                } else {
                    nextLine += "\u00A7";
                }
                nextLine += splitLine[i];
            }
            event.setLine(forInt, nextLine);
        }
    }

    private boolean checkAuth(Player player, int color) {
        char col = colors.charAt(color);
        return (color == 0) || player.hasPermission("meggachat.signcolor." + col) || player.hasPermission("meggachat.signcolor.*");
    }
}

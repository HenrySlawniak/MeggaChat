package com.meggawatts.meggachat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    
        @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player chatter = event.getPlayer();
        if (MeggaChat.adminschatting.containsKey(chatter)) {
            MeggaChat.sendToAdmins(event.getMessage(), chatter);
            event.setCancelled(true);
        }
    }
    
}

package com.meggawatts.MeggaChat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class EnchantmentListener implements Listener{
    
    @EventHandler
    public void onEnchatment(PrepareItemEnchantEvent event){
        if (!event.getEnchanter().hasPermission("meggachat.enchant")){
            event.setCancelled(true);
        }
    }
    
}

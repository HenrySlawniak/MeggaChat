package com.meggawatts.MeggaChat;

import static com.meggawatts.MeggaChat.MeggaChat.dpoton;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PotionListener implements Listener{

    //Blocks use of potions! :D
	@EventHandler(priority = EventPriority.HIGH)
	public void onPotion(PlayerInteractEvent event){
		if(!(event.getPlayer().hasPermission("meggachat.potions"))
				&& (event.hasItem())
				&& (event.getItem().getType().equals(Material.POTION))
			){
			if((event.getAction().equals(Action.RIGHT_CLICK_AIR))
			|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			){
			event.setCancelled(true);
			}
		}
	}

	//Blocks potions from dispensers!
	@EventHandler(priority = EventPriority.HIGH)
	public void onDispense(BlockDispenseEvent  event){
		if(dpoton){
			if(event.getItem().getType().equals(Material.POTION)){
			event.setCancelled(true);
			}
		}
	}
}
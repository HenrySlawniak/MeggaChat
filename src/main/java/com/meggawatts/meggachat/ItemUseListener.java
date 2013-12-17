package com.meggawatts.MeggaChat;

import static com.meggawatts.MeggaChat.MeggaChat.dispblock;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemUseListener implements Listener {

	public static HashSet blockeditems = new HashSet();

	// Blocks use of blacklisted items + removes them from inventory!
	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent event) {
		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR))
				|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (event.hasItem())) {
			int i = event.getItem().getTypeId();
			if (!(event.getPlayer().hasPermission("meggachat.items"))
					&& (blockeditems.contains(i))) {
				event.setCancelled(true);
				event.getPlayer().getInventory().remove(i);
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "Nope!");
			}
		}
	}

	// Prevent dispensing of blacklisted items!
	@EventHandler(priority = EventPriority.HIGH)
	public void onDispense(BlockDispenseEvent event) {
		int i = event.getItem().getTypeId();
		if (dispblock) {
			if (blockeditems.contains(i)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDrop(PlayerDropItemEvent event) {
		int i = event.getItemDrop().getItemStack().getTypeId();
		if (!(event.getPlayer().hasPermission("meggachat.items"))
				&& blockeditems.contains(i)) {
			event.setCancelled(true);
		}
	}
}

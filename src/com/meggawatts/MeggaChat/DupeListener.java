/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meggawatts.MeggaChat;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author meggawatts <megga@mcmiddleearth.com>
 */
public class DupeListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public boolean onclick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material clicked = event.getClickedBlock().getType();
        Byte clickeddat = event.getClickedBlock().getData();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                && event.getItem().getType().equals(Material.FLINT)
                && player.hasPermission("meggachat.nothrow")) {
            if (clicked.equals(Material.WOOL)
                    || clicked.equals(Material.LOG)
                    || clicked.equals(Material.LEAVES)
                    || clicked.equals(Material.SANDSTONE)
                    || clicked.equals(Material.SMOOTH_BRICK)
                    || clicked.equals(Material.STEP)
                    || clicked.equals(Material.WOOD)) {
                player.getInventory().addItem(new ItemStack(clicked.getId(), 64, clickeddat));
                player.updateInventory();
            } else {
                player.getInventory().addItem(new ItemStack(clicked.getId(), 64));
                player.updateInventory();
            }

        }
        else if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
                || event.getAction().equals(Action.LEFT_CLICK_BLOCK)
                || event.getAction().equals(Action.LEFT_CLICK_AIR)){
            return false;
        }
        return false;
    }
}

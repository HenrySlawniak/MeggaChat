/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meggawatts.meggachat;

import java.util.HashSet;

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

    public static HashSet blocked = new HashSet();

    @EventHandler(priority = EventPriority.NORMAL)
    public boolean onclick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasItem()) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                    && event.getItem().getType().equals(Material.FLINT)
                    && player.hasPermission("meggachat.nothrow")
                    && !(blocked.contains(event.getClickedBlock().getTypeId()))) {

                Material clicked = event.getClickedBlock().getType();
                Byte clickeddat = event.getClickedBlock().getData();
                if (clicked.equals(Material.WOOL)
                        || clicked.equals(Material.LOG)
                        || clicked.equals(Material.LEAVES)
                        || clicked.equals(Material.SANDSTONE)
                        || clicked.equals(Material.SMOOTH_BRICK)
                        || clicked.equals(Material.STEP)
                        || clicked.equals(Material.WOOD)) {
                    player.getInventory().addItem(new ItemStack(clicked.getId(), 64, clickeddat));
                    player.updateInventory();
                    //MeggaChat.logDupe(clicked, player);
                } else if (clicked.equals(Material.LONG_GRASS)) {
                    player.getInventory().addItem(new ItemStack(clicked.getId(), 64, clickeddat));
                    player.updateInventory();
                    //MeggaChat.logDupe(clicked, player);
                    event.setCancelled(true);
                } else {
                    player.getInventory().addItem(new ItemStack(clicked.getId(), 64));
                    player.updateInventory();
                    //MeggaChat.logDupe(clicked, player);
                }
            }
        } else {
            return false;
        }
        return false;
    }
}

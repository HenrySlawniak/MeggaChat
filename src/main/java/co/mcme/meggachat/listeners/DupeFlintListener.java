/**
 * This file is part of MeggaChat, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2014 Henry Slawniak <http://mcme.co/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.mcme.meggachat.listeners;

import co.mcme.meggachat.MeggaChatPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DupeFlintListener implements Listener {

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission(MeggaChatPlugin.getPermissionsUtil().getUseDupeFlintPermission())) {
            if (event.hasItem() && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getItem().getType().equals(Material.FLINT)) {
                    Block clicked = event.getClickedBlock();
                    Player target = event.getPlayer();
                    if (!MeggaChatPlugin.getConf().getDupeblacklist().isBlocked(clicked.getType())) {
                        ItemStack item = new ItemStack(clicked.getType(), 64, clicked.getData());
                        target.getInventory().addItem(item);
                        target.updateInventory();
                        target.sendMessage(ChatColor.DARK_PURPLE + "Enjoy your " + clicked.getType());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if (event.getPlayer().hasPermission(MeggaChatPlugin.getPermissionsUtil().getUseDupeFlintPermission())) {
            if (event.getPlayer().getItemInHand().getType().equals(Material.FLINT)) {
                if (event.getRightClicked() instanceof Player) {
                    Player clicked = (Player) event.getRightClicked();
                    event.getPlayer().getInventory().clear();
                    event.getPlayer().getInventory().setContents(clicked.getInventory().getContents());
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.FLINT));
                    event.getPlayer().getInventory().setHelmet(clicked.getInventory().getHelmet());
                    event.getPlayer().getInventory().setChestplate(clicked.getInventory().getChestplate());
                    event.getPlayer().getInventory().setLeggings(clicked.getInventory().getLeggings());
                    event.getPlayer().getInventory().setBoots(clicked.getInventory().getBoots());
                    event.getPlayer().updateInventory();
                }
            }
        }
    }
}

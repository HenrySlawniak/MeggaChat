/*  This file is part of MeggaChat.
 * 
 *  MeggaChat is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MeggaChat is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MeggaChat.  If not, see <http://www.gnu.org/licenses/>.
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

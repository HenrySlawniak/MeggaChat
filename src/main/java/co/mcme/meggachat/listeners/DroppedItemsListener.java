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

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DroppedItemsListener implements Listener {

    Block blok;
    Player executor;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        blok = event.getBlock();
        executor = event.getPlayer();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void removeent(ItemSpawnEvent event) {
        Block spawnloc = event.getLocation().getBlock();
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void noThrow(PlayerDropItemEvent event) {
        Item drop = event.getItemDrop();
        drop.remove();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void noDropOnDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        List<ItemStack> drops = event.getDrops();
        for (ItemStack drop : drops) {
            drop.setAmount(0);
        }
    }
}

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
import java.util.Map.Entry;
import lombok.Getter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ColoredListListener implements Listener {

    @Getter
    public static Permission permissionsInterface = null;

    public ColoredListListener() {
        setupPermissions();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        String oldname = event.getPlayer().getName();
        String newname = oldname;
        if (joiner.getName().length() == 15) {
            int len = oldname.length();
            int removechars = len - 1;
            newname = oldname.substring(0, removechars);
        }
        if (joiner.getName().length() == 16) {
            int len = oldname.length();
            int removechars = len - 2;
            newname = oldname.substring(0, removechars);
        }
        ColorName(joiner, newname);
    }

    public void ColorName(Player player, String name) {
        for (Entry<String, String> entry : MeggaChatPlugin.getConf().getListcolorgroups().entrySet()) {
            if (getPermissionsInterface().getPrimaryGroup(player).equals(entry.getKey())) {
                player.setPlayerListName(ChatColor.valueOf(entry.getValue()) + name);
                return;
            }
        }
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = MeggaChatPlugin.getServerInstance().getServicesManager().getRegistration(Permission.class);
        permissionsInterface = rsp.getProvider();
        return permissionsInterface != null;
    }
}

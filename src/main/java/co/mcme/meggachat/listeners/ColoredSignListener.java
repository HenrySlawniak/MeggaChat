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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ColoredSignListener implements Listener {

    private final String delim = "&";
    private final String colors = "0123456789abcdefklmnor";

    @EventHandler(priority = EventPriority.HIGH)
    public void signChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        for (int forInt = 0; forInt < 4; forInt++) {
            if (event.getLine(forInt).isEmpty()) {
                continue;
            }
            String[] splitLine = event.getLine(forInt).split(delim);
            String nextLine = splitLine[0];
            for (int i = 1; i < splitLine.length; i++) {
                int col;
                if (splitLine[i].length() == 0
                        || (col = colors.indexOf(splitLine[i].toLowerCase().charAt(0))) == -1
                        || (!checkAuth(player, col))
                        || splitLine[i].length() <= 1) {
                    nextLine += delim;
                } else {
                    nextLine += "\u00A7";
                }
                nextLine += splitLine[i];
            }
            event.setLine(forInt, nextLine);
        }
    }

    private boolean checkAuth(Player player, int color) {
        char col = colors.charAt(color);
        return (color == 0) || player.hasPermission("meggachat.signcolor." + col) || player.hasPermission("meggachat.signcolor.*");
    }
}

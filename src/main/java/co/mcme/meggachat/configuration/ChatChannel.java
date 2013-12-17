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
package co.mcme.meggachat.configuration;

import co.mcme.meggachat.MeggaChatPlugin;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.codehaus.jackson.annotate.JsonIgnore;

public class ChatChannel implements Listener {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String channelColor;
    @Getter
    @Setter
    private String messageColor;
    @Getter
    @Setter
    private String senderColor;
    @Getter
    @Setter
    private String command;
    @Getter
    @Setter
    private String permission;
    @Getter
    @Setter
    private String format;
    @Getter
    @Setter
    @JsonIgnore
    private ArrayList<String> toggledPlayers = new ArrayList();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (toggledPlayers.contains(event.getPlayer().getName())) {
            dispatchMessage(event.getMessage(), event.getPlayer());
            event.setCancelled(true);
        }
    }

    public void dispatchMessage(String message, Player p) {
        MeggaChatPlugin.getServerInstance().broadcast(formatMessage(message, p), permission);
    }

    public String formatMessage(String msg, Player p) {
        return format.replaceAll("%reset%", ChatColor.RESET.toString())
                .replaceAll("%channelcolor%", ChatColor.valueOf(channelColor).toString())
                .replaceAll("%name%", name)
                .replaceAll("%sendercolor%", ChatColor.valueOf(senderColor).toString())
                .replaceAll("%sendername%", p.getDisplayName())
                .replaceAll("%messagecolor%", ChatColor.valueOf(messageColor).toString())
                .replaceAll("%message%", msg);
    }

    public void processCommand(String cmd, String fullmsg, Player p) {
        System.out.println(name + " channel processing command.");
        String[] msg = fullmsg.substring(command.length() + 2).split(" ");
        if (msg[0].charAt(0) == '!' && msg[0].length() > 1) {
            System.out.println("Got control character!");
            String control = msg[0].substring(1);
            switch(control) {
                case "on": {
                    toggleOn(p);
                    break;
                }
                case "off": {
                    toggleOff(p);
                    break;
                }
                default: {
                    dispatchMessage(StringUtils.join(msg, " "), p);
                    break;
                }
            }
        } else {
            dispatchMessage(StringUtils.join(msg, " "), p);
        }
    }
    
    public void toggleOn(Player p) {
        if (!toggledPlayers.contains(p.getName())) {
            toggledPlayers.add(p.getName());
            p.sendMessage(ChatColor.GREEN + "Channel '" + name + "' toggled on");
        } else {
            p.sendMessage(ChatColor.RED + "You already had the channel '" + name + "' toggled on");
        }
    }
    
    public void toggleOff(Player p) {
        if (toggledPlayers.contains(p.getName())) {
            toggledPlayers.remove(p.getName());
            p.sendMessage(ChatColor.GREEN + "Channel '" + name + "' toggled off");
        } else {
            p.sendMessage(ChatColor.RED + "You already had the channel '" + name + "' toggled off");
        }
    }
}

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
        return ChatColor.translateAlternateColorCodes('&', format.replaceAll("%reset%", ChatColor.RESET.toString())
                .replaceAll("%channelcolor%", ChatColor.valueOf(channelColor).toString())
                .replaceAll("%name%", name)
                .replaceAll("%sendercolor%", ChatColor.valueOf(senderColor).toString())
                .replaceAll("%sendername%", p.getDisplayName())
                .replaceAll("%messagecolor%", ChatColor.valueOf(messageColor).toString())
                .replaceAll("%message%", msg));
    }

    public void processCommand(String cmd, String fullmsg, Player p) {
        if (p.hasPermission(permission)) {
            System.out.println(name + " channel processing command.");
            String[] msg = fullmsg.substring(command.length() + 2).split(" ");
            if (msg[0].charAt(0) == '!' && msg[0].length() > 1) {
                System.out.println("Got control character!");
                String control = msg[0].substring(1);
                switch (control) {
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

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
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PipeListener implements Listener {

    double distance = 0.7D;

    public Location getSmokeLocation(Location source) {
        double rot_x = (source.getYaw() + 90.0F) % 360.0F;
        double rot_y = source.getPitch() * -1.0F;
        double rot_ycos = Math.cos(Math.toRadians(rot_y));
        double rot_ysin = Math.sin(Math.toRadians(rot_y));
        double rot_xcos = Math.cos(Math.toRadians(rot_x));
        double rot_xsin = Math.sin(Math.toRadians(rot_x));

        double h_length = distance * rot_ycos;
        double y_offset = distance * rot_ysin;
        double x_offset = h_length * rot_xcos;
        double z_offset = h_length * rot_xsin;

        double target_x = x_offset + source.getX();
        double target_y = y_offset + source.getY() + 1.65D;
        double target_z = z_offset + source.getZ();

        return new Location(source.getWorld(), target_x, target_y, target_z);
    }

    @EventHandler
    public void smokePipeItem(PlayerInteractEvent event) {
        if (event.hasItem() && ((event.getAction().equals(Action.RIGHT_CLICK_AIR))
                || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))) {
            Player p = event.getPlayer();
            switch (event.getItem().getType()) {
                case GHAST_TEAR: {
                    if (p.hasPermission(MeggaChatPlugin.getPermissionsUtil().getBasicPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                    }
                    break;
                }
                case BLAZE_ROD: {
                    if (p.hasPermission(MeggaChatPlugin.getPermissionsUtil().getDiggingPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                        event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to dig!");
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 5000, 1));
                    }
                    break;
                }
                case BLAZE_POWDER: {
                    if (p.hasPermission(MeggaChatPlugin.getPermissionsUtil().getRunningPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                        event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to run!");
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5000, 0));
                    }
                    break;
                }
                case NETHER_STAR: {
                    if (p.hasPermission(MeggaChatPlugin.getPermissionsUtil().getPhialPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.ENDER_SIGNAL, 4);
                        togglePhial(p);
                    }
                    break;
                }
                case GOLD_NUGGET: {
                    if (p.hasPermission(MeggaChatPlugin.getPermissionsUtil().getRingPipePermission())) {
                        toggleRing(p);
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    public void togglePhial(Player p) {
        if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            p.sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "The light fades");
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
        } else if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            p.sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "'May it be a light for you in dark places...'");
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 98300, 1, true));
        }
    }

    public void toggleRing(Player p) {
        if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.removePotionEffect(PotionEffectType.CONFUSION);
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            if (MeggaChatPlugin.getConf().getFeatures().isSoundeffects()) {
                World w = p.getWorld();
                w.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
            }
        } else if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 98300, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 98300, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 98300, 0));
            if (MeggaChatPlugin.getConf().getFeatures().isSoundeffects()) {
                World w = p.getWorld();
                w.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                p.playSound(p.getLocation(), Sound.ENDERMAN_STARE, 1, 0);
            }
        }
    }
}

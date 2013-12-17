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
import co.mcme.meggachat.utilities.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PipeListener {

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
                    if (p.hasPermission(Permissions.getBasicPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                    }
                    break;
                }
                case BLAZE_ROD: {
                    if (p.hasPermission(Permissions.getDiggingPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                        event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to dig!");
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 5000, 1));
                    }
                    break;
                }
                case BLAZE_POWDER: {
                    if (p.hasPermission(Permissions.getRunningPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.SMOKE, 4);
                        event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to run!");
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5000, 0));
                    }
                    break;
                }
                case NETHER_STAR: {
                    if (p.hasPermission(Permissions.getPhialPipePermission())) {
                        event.getPlayer().getWorld().playEffect(getSmokeLocation(event.getPlayer().getLocation()), Effect.ENDER_SIGNAL, 4);
                        togglePhial(p);
                    }
                    break;
                }
                case GOLD_NUGGET: {
                    if (p.hasPermission(Permissions.getRingPipePermission())) {
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

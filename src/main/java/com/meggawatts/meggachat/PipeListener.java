package com.meggawatts.meggachat;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import static com.meggawatts.meggachat.MeggaChat.sfx;

public class PipeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void basicSmoke(PlayerInteractEvent event) {
        if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.GHAST_TEAR) && event.getPlayer().hasPermission("meggachat.smoke")) {
            double distance = 0.7D;
            Location player_loc = event.getPlayer().getLocation();
            double rot_x = (player_loc.getYaw() + 90.0F) % 360.0F;
            double rot_y = player_loc.getPitch() * -1.0F;
            double rot_ycos = Math.cos(Math.toRadians(rot_y));
            double rot_ysin = Math.sin(Math.toRadians(rot_y));
            double rot_xcos = Math.cos(Math.toRadians(rot_x));
            double rot_xsin = Math.sin(Math.toRadians(rot_x));

            double h_length = distance * rot_ycos;
            double y_offset = distance * rot_ysin;
            double x_offset = h_length * rot_xcos;
            double z_offset = h_length * rot_xsin;

            double target_x = x_offset + player_loc.getX();
            double target_y = y_offset + player_loc.getY() + 1.65D;
            double target_z = z_offset + player_loc.getZ();


            event.getPlayer().getWorld().playEffect(new Location(player_loc.getWorld(), target_x, target_y, target_z), Effect.SMOKE, 4);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void digSmoke(PlayerInteractEvent event) {
        if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.BLAZE_ROD) && event.getPlayer().hasPermission("meggachat.smoke.digfast") && !(event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING))) {
            double distance = 0.7D;
            Location player_loc = event.getPlayer().getLocation();
            double rot_x = (player_loc.getYaw() + 90.0F) % 360.0F;
            double rot_y = player_loc.getPitch() * -1.0F;
            double rot_ycos = Math.cos(Math.toRadians(rot_y));
            double rot_ysin = Math.sin(Math.toRadians(rot_y));
            double rot_xcos = Math.cos(Math.toRadians(rot_x));
            double rot_xsin = Math.sin(Math.toRadians(rot_x));

            double h_length = distance * rot_ycos;
            double y_offset = distance * rot_ysin;
            double x_offset = h_length * rot_xcos;
            double z_offset = h_length * rot_xsin;

            double target_x = x_offset + player_loc.getX();
            double target_y = y_offset + player_loc.getY() + 1.65D;
            double target_z = z_offset + player_loc.getZ();


            event.getPlayer().getWorld().playEffect(new Location(player_loc.getWorld(), target_x, target_y, target_z), Effect.SMOKE, 4);
            event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to dig!");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 5000, 1));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void runSmoke(PlayerInteractEvent event) {
        if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.BLAZE_POWDER) && event.getPlayer().hasPermission("meggachat.smoke.runfast") && !(event.getPlayer().hasPotionEffect(PotionEffectType.SPEED))) {
            double distance = 0.7D;
            Location player_loc = event.getPlayer().getLocation();
            double rot_x = (player_loc.getYaw() + 90.0F) % 360.0F;
            double rot_y = player_loc.getPitch() * -1.0F;
            double rot_ycos = Math.cos(Math.toRadians(rot_y));
            double rot_ysin = Math.sin(Math.toRadians(rot_y));
            double rot_xcos = Math.cos(Math.toRadians(rot_x));
            double rot_xsin = Math.sin(Math.toRadians(rot_x));

            double h_length = distance * rot_ycos;
            double y_offset = distance * rot_ysin;
            double x_offset = h_length * rot_xcos;
            double z_offset = h_length * rot_xsin;

            double target_x = x_offset + player_loc.getX();
            double target_y = y_offset + player_loc.getY() + 1.65D;
            double target_z = z_offset + player_loc.getZ();


            event.getPlayer().getWorld().playEffect(new Location(player_loc.getWorld(), target_x, target_y, target_z), Effect.SMOKE, 4);
            event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "You take a puff from your trusty pipe, and are ready to run!");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5000, 0));
        }
    }

    //Adds Phial of Galadriel
    @EventHandler(priority = EventPriority.HIGH)
    public void phialLight(PlayerInteractEvent event) {
        if ((event.getPlayer().hasPermission("meggachat.phial")) && (event.hasItem()) && (event.getItem().getType().equals(Material.NETHER_STAR))) {
            if ((event.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION)) && (((event.getAction().equals(Action.RIGHT_CLICK_AIR))) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))) {
                event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "The light fades");
                event.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
            } else if (!(event.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION)) && (((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))))) {
                double distance = 0.7D;
                Location player_loc = event.getPlayer().getLocation();
                double rot_x = (player_loc.getYaw() + 90.0F) % 360.0F;
                double rot_y = player_loc.getPitch() * -1.0F;
                double rot_ycos = Math.cos(Math.toRadians(rot_y));
                double rot_ysin = Math.sin(Math.toRadians(rot_y));
                double rot_xcos = Math.cos(Math.toRadians(rot_x));
                double rot_xsin = Math.sin(Math.toRadians(rot_x));

                double h_length = distance * rot_ycos;
                double y_offset = distance * rot_ysin;
                double x_offset = h_length * rot_xcos;
                double z_offset = h_length * rot_xsin;

                double target_x = x_offset + player_loc.getX();
                double target_y = y_offset + player_loc.getY() + 1.65D;
                double target_z = z_offset + player_loc.getZ();


                event.getPlayer().getWorld().playEffect(new Location(player_loc.getWorld(), target_x, target_y, target_z), Effect.ENDER_SIGNAL, 4);
                event.getPlayer().sendMessage(ChatColor.ITALIC + "" + ChatColor.AQUA + "'May it be a light for you in dark places...'");
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 98300, 1, true));
            }
        }
    }

    //Adds "The One Ring"
    @EventHandler(priority = EventPriority.HIGH)
    public void oneRing(PlayerInteractEvent event) {
        if ((event.getPlayer().hasPermission("meggachat.ring")) && (event.hasItem()) && (event.getItem().getType().equals(Material.GOLD_NUGGET))) {
            if ((event.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) && (((event.getAction().equals(Action.RIGHT_CLICK_AIR))) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))) {
                event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                event.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);
                event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
                if (sfx) {
                    Player p = (Player) event.getPlayer();
                    World w = p.getWorld();
                    w.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
                }
            } else if (!(event.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) && (((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))))) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 98300, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 98300, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 98300, 0));
                if (sfx) {
                    Player p = (Player) event.getPlayer();
                    World w = p.getWorld();
                    w.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_STARE, 1, 0);
                }
            }
        }
    }
}
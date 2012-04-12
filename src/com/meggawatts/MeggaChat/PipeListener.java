package com.meggawatts.MeggaChat;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author meggawatts <megga@mcmiddleearth.com>
 */
public class PipeListener implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void basicSmoke(PlayerInteractEvent event) {
        if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.GHAST_TEAR) && (event.getPlayer().hasPermission("meggachat.smoke") || event.getPlayer().getName().equals("meggawatts"))) {
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
        } else if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.BLAZE_ROD) && !(event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING) && (event.getPlayer().hasPermission("meggachat.smoke") || event.getPlayer().getName().equals("meggawatts")))) {
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
            event.getPlayer().sendMessage(ChatColor.ITALIC + "You take a puff from your trusty pipe, and feel relaxed, not wanting want to mine faster than you need to.");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2000, 0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2000, 2));
        }
        else if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.BLAZE_POWDER) && !(event.getPlayer().hasPotionEffect(PotionEffectType.SPEED))) {
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
            event.getPlayer().sendMessage(ChatColor.ITALIC + "You take a puff from your trusty pipe, and feel energized.");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 2000, 0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000,  0));
        }
        else if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && ((event.getItem().getType().equals(Material.INK_SACK)) && (event.getItem().getDurability() == 3)) && !(event.getPlayer().hasPotionEffect(PotionEffectType.CONFUSION) && (event.getPlayer().hasPermission("meggachat.smoke") || event.getPlayer().getName().equals("meggawatts")))) {
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
            event.getPlayer().sendMessage(ChatColor.ITALIC + "You take a puff from your trusty pipe, and feel dizzy, but strangely refreshed.");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200,  0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1000,  0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000,  0));
        }
        else if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.GLOWSTONE_DUST) && !(event.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS) && (event.getPlayer().hasPermission("meggachat.smoke") || event.getPlayer().getName().equals("meggawatts")))) {
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
            event.getPlayer().sendMessage(ChatColor.ITALIC + "You take a puff from your trusty pipe, and something goes horribly wrong!");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2000, 1));
        }
        else if ((event.hasItem()) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getItem().getType().equals(Material.MAGMA_CREAM) && !(event.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS) && (event.getPlayer().hasPermission("meggachat.smoke") || event.getPlayer().getName().equals("meggawatts")))) {
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
            event.getPlayer().sendMessage(ChatColor.ITALIC + "You take a puff from your trusty pipe, and something goes horribly wrong!");
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2000, 1));
        }
    }
}


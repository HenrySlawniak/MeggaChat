package com.meggawatts.MeggaChat;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
}

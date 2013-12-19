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
package co.mcme.meggachat.commands;

import co.mcme.meggachat.MeggaChatPlugin;
import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MeggaChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission(MeggaChatPlugin.getPermissionsUtil().getReloadPermission())) {
                    File config = new File(MeggaChatPlugin.getPluginDataFolder(), "config.json");
                    if (config.exists()) {
                        MeggaChatPlugin.reloadConfig(config, sender, args.length > 1 && args[1].equalsIgnoreCase("verbose"));
                        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded config.");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Config file could not be found.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have the permissions to run this command.");
                }
                return true;
            }
        }
        return false;
    }

}

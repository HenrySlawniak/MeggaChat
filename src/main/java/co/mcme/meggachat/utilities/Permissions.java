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
package co.mcme.meggachat.utilities;

import co.mcme.meggachat.MeggaChatPlugin;
import co.mcme.meggachat.configuration.ChatChannel;
import java.util.ArrayList;
import lombok.Getter;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {

    @Getter
    private final Permission basicPipePermission = new Permission("meggachat.pipes.basic", PermissionDefault.OP);
    @Getter
    private final Permission diggingPipePermission = new Permission("meggachat.pipes.dig", PermissionDefault.OP);
    @Getter
    private final Permission runningPipePermission = new Permission("meggachat.pipes.run", PermissionDefault.OP);
    @Getter
    private final Permission phialPipePermission = new Permission("meggachat.pipes.phial", PermissionDefault.OP);
    @Getter
    private final Permission ringPipePermission = new Permission("meggachat.pipes.ring", PermissionDefault.OP);
    @Getter
    private final Permission enchantmentPermission = new Permission("meggachat.enchant", PermissionDefault.OP);
    @Getter
    private final Permission useDupeFlintPermission = new Permission("meggachat.dupe", PermissionDefault.OP);
    @Getter
    private final Permission flyPermission = new Permission("meggachat.fly", PermissionDefault.OP);
    @Getter
    private final Permission useItemPermission = new Permission("meggachat.ignoreitemuse", PermissionDefault.OP);
    @Getter
    private final Permission allColorsPermission = new Permission("meggachat.signcolor.*", PermissionDefault.OP);
    @Getter
    private final Permission reloadPermission = new Permission("meggachat.reload", PermissionDefault.OP);
    @Getter
    private final ArrayList<Permission> channelPermissions;

    public Permissions() {
        this.channelPermissions = new ArrayList();
        for (ChatChannel channel : MeggaChatPlugin.getConf().getChannels()) {
            Permission perm = new Permission(channel.getPermission(), PermissionDefault.valueOf(channel.getPermissiondefault().toUpperCase()));
            channelPermissions.add(perm);
            channel.setBukkitPermission(perm);
            perm.recalculatePermissibles();
        }
    }
}

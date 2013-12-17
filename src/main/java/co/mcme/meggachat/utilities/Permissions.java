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

import java.util.HashMap;
import lombok.Getter;
import org.bukkit.ChatColor;
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
    private final HashMap<Character, Permission> colorPermissions = new HashMap();
    @Getter
    private final Permission allColorsPermission = new Permission("meggachat.signcolor.*", PermissionDefault.OP);
    @Getter
    private final HashMap<Character, Permission> tabColorPermissions = new HashMap();

    public Permissions() {
        for (ChatColor col : ChatColor.values()) {
            colorPermissions.put(col.getChar(), new Permission("meggachat.signcolor." + col.getChar(), PermissionDefault.OP));
            tabColorPermissions.put(col.getChar(), new Permission("meggachat.tab." + col.getChar(), PermissionDefault.FALSE));
        }
    }

}

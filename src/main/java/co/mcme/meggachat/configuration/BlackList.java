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

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.codehaus.jackson.annotate.JsonIgnore;

public class BlackList {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private ArrayList<String> items = new ArrayList();

    public ArrayList<Material> toMaterials() {
        ArrayList<Material> materials = new ArrayList();
        for (String n : items) {
            Material mat = Material.valueOf(n);
            Validate.notNull(mat, "Invalid Material name provided!");
            materials.add(mat);
        }
        return materials;
    }

    @JsonIgnore
    public boolean isBlocked(Material mat) {
        return items.contains(mat.name());
    }
}

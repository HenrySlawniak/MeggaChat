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

import lombok.Getter;
import lombok.Setter;

public class Features {

    @Getter
    @Setter
    private boolean adminchat = true;
    @Getter
    @Setter
    private boolean tablist = true;
    @Getter
    @Setter
    private boolean entityblocking = true;
    @Getter
    @Setter
    private boolean itemuseblocking = true;
    @Getter
    @Setter
    private boolean dispenserblocking = true;
    @Getter
    @Setter
    private boolean enchantmentblocking = true;
    @Getter
    @Setter
    private boolean dupeflint = true;
    @Getter
    @Setter
    private boolean pipes = true;
    @Getter
    @Setter
    private boolean flyingpermission = true;
    @Getter
    @Setter
    private boolean coloredsigns = true;
    @Getter
    @Setter
    private boolean soundeffects = true;

    public Features() {

    }
}

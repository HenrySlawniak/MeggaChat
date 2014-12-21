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
package co.mcme.meggachat.utilities;

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

}

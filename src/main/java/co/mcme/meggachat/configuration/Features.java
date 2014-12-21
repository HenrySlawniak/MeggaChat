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

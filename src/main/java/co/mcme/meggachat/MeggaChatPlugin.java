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
package co.mcme.meggachat;

import co.mcme.meggachat.configuration.ChatChannel;
import co.mcme.meggachat.configuration.MeggaChatConfig;
import co.mcme.meggachat.listeners.ColoredSignListener;
import co.mcme.meggachat.listeners.DispenserListener;
import co.mcme.meggachat.listeners.DupeFlintListener;
import co.mcme.meggachat.listeners.EnchantmentListener;
import co.mcme.meggachat.utilities.Logger;
import co.mcme.meggachat.utilities.Permissions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class MeggaChatPlugin extends JavaPlugin implements Listener {

    @Getter
    private static Server serverInstance;
    @Getter
    private static MeggaChatPlugin pluginInstance;
    @Getter
    private static File pluginDataFolder;
    @Getter
    private static ObjectMapper jsonMapper = new ObjectMapper()
            .configure(SerializationConfig.Feature.INDENT_OUTPUT, true)
            .configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, true);
    @Getter
    private static String fileSeperator = System.getProperty("file.separator");
    @Getter
    private static MeggaChatConfig conf = new MeggaChatConfig();
    @Getter
    private HashMap<String, ChatChannel> channelCommands = new HashMap();
    @Getter
    private static Permissions permissionsUtil = new Permissions();

    @Override
    public void onEnable() {
        serverInstance = getServer();
        pluginInstance = this;
        pluginDataFolder = pluginInstance.getDataFolder();
        if (!pluginDataFolder.exists()) {
            pluginDataFolder.mkdirs();
        }
        File config = new File(pluginDataFolder, "config.json");
        if (!config.exists()) {
            writeDefaultConfig(config);
        } else {
            loadConfig(config);
        }
        saveConfig(config);
        registerEvents();
    }

    public void registerEvents() {
        if (conf.getFeatures().isAdminchat()) {
            Logger.info("Enabling chat channels.");
            serverInstance.getPluginManager().registerEvents(this, this);
            for (ChatChannel cc : conf.getChannels()) {
                serverInstance.getPluginManager().registerEvents(cc, this);
                channelCommands.put(cc.getCommand(), cc);
            }
        }
        if (conf.getFeatures().isColoredsigns()) {
            Logger.info("Enabling colored signs.");
            serverInstance.getPluginManager().registerEvents(new ColoredSignListener(), this);
        }
        if (conf.getFeatures().isDispenserblocking()) {
            Logger.info("Enabling dispenser blocking.");
            serverInstance.getPluginManager().registerEvents(new DispenserListener(), this);
        }
        if (conf.getFeatures().isDupeflint()) {
            Logger.info("Enabling dupe flint.");
            serverInstance.getPluginManager().registerEvents(new DupeFlintListener(), this);
            int dupecount = conf.getDupeblacklist().toMaterials().size();
            Logger.info("Blocking " + dupecount + " materials from being duped.");
        }
        if (conf.getFeatures().isEnchantmentblocking()) {
            Logger.info("Enabling enchantment blocking.");
            serverInstance.getPluginManager().registerEvents(new EnchantmentListener(), this);
        }
        if (conf.getFeatures().isEntityblocking()) {
            Logger.info("Enabling entity blocking.");
        }
        if (conf.getFeatures().isFlyingpermission()) {
            Logger.info("Enabling flying permission.");
        }
        if (conf.getFeatures().isItemuseblocking()) {
            Logger.info("Enabling item use blocking.");
            int usecount = conf.getItemuseblacklist().toMaterials().size();
            Logger.info("Blocking " + usecount + " materials from being used.");
        }
        if (conf.getFeatures().isPipes()) {
            Logger.info("Enabling pipes.");
        }
        if (conf.getFeatures().isSoundeffects()) {
            Logger.info("Enabling sound effects.");
        }
        if (conf.getFeatures().isTablist()) {
            Logger.info("Enabling player list coloring.");
        }
    }

    @EventHandler
    public void onCommandPre(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].replaceAll("/", "");
        if (channelCommands.containsKey(command)) {
            channelCommands.get(command).processCommand(command, event.getMessage(), event.getPlayer());
            event.setCancelled(true);
        }
        System.out.println(command);
    }

    public void writeDefaultConfig(File config) {
        InputStream defaultConfigStream = this.getResource("config.json");
        try {
            conf = jsonMapper.readValue(defaultConfigStream, MeggaChatConfig.class);
            jsonMapper.writeValue(config, conf);
        } catch (IOException ex) {
            Logger.severe(ex.getMessage());
        }
    }

    public void loadConfig(File config) {
        try {
            conf = jsonMapper.readValue(config, MeggaChatConfig.class);
        } catch (IOException ex) {
            Logger.severe(ex.getMessage());
        }
    }

    public void saveConfig(File config) {
        try {
            jsonMapper.writeValue(config, conf);
        } catch (IOException ex) {
            Logger.severe(ex.getMessage());
        }
    }
}

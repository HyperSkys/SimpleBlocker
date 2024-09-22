package dev.hyperskys.simpleBlocker.configuration.enums;

import dev.hyperskys.simpleBlocker.SimpleBlocker;
import dev.hyperskys.simpleBlocker.exceptions.ConfigurationAttributeNotFoundException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;

public enum Configuration {

    BLOCK_ALL_COMMANDS("Settings.block-all-commands"),
    BLOCK_NAMESPACED_COMMANDS("Settings.block-namespaced-commands"),
    SOUND("Settings.sound"),
    BLOCKED_COMMANDS("Settings.blocked-commands"),
    GROUPS("Settings.groups"),

    BLOCKED_MESSAGE("Messages.blocked-message"),
    BLOCKED_ACTIONBAR_MESSAGE("Messages.blocked-actionbar"),
    UNKNOWN_COMMAND_MESSAGE("Messages.unknown-command-message"),
    UNKNOWN_COMMAND_ACTIONBAR("Messages.unknown-command-actionbar");

    private final String path;
    Configuration(String path) {
        this.path = path;
    }

    /**
     * Get the value of the path in the configuration as a component.
     * @return The value of the path in the configuration as a component.
     */
    public Component getAsComponent() {
        String message = SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").getString(path);
        if (message == null)
            throw new ConfigurationAttributeNotFoundException("The component with the path '" + path + "' was not found in the configuration file.");
        return MiniMessage.miniMessage().deserialize(message);
    }

    /**
     * Get the value of the path in the configuration as a string array.
     * @return The value of the path in the configuration as a string array.
     */
    public String[] getAsStringArray() {
        String[] messages = SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").getStringList(path).toArray(new String[0]);
        if (messages.length == 0)
            throw new ConfigurationAttributeNotFoundException("The string array with the path '" + path + "' was not found in the configuration file.");
        return messages;
    }

    /**
     * Get the value of the path in the configuration as a boolean.
     * @return The value of the path in the configuration as a boolean.
     */
    public boolean getAsBoolean() {
        if (!SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").contains(path))
            throw new ConfigurationAttributeNotFoundException("The boolean with the path '" + path + "' was not found in the configuration file.");
        return SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").getBoolean(path);
    }

    /**
     * Get the value of the path in the configuration as a map.
     * @return The value of the path in the configuration as a map.
     */
    public HashMap<String, String> getAsMap() {
        if (!SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").contains(path))
            throw new ConfigurationAttributeNotFoundException("The map with the path '" + path + "' was not found in the configuration file.");

        HashMap<String, String> map = new HashMap<>();
        ConfigurationSection section = SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").getConfigurationSection(path);
        if (section == null)
            throw new ConfigurationAttributeNotFoundException("The section with the path '" + path + "' was not found in the configuration file.");

        for (String key : section.getKeys(false)) {
            List<String> values = section.getStringList(key);
            for (String value : values) {
                map.put(key, value);
            }
        }

        return map;
    }

    /**
     * Get the value of the path in the configuration as a sound.
     * @return The value of the path in the configuration as a sound.
     */
    public Sound getAsSound() {
        if (!SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").contains(path))
            throw new ConfigurationAttributeNotFoundException("The sound with the path '" + path + "' was not found in the configuration file.");
        return Sound.valueOf(SimpleBlocker.getInstance().getConfigurationManager().getConfigurationFile("config").getString(path));
    }

    /**
     * Reload the configuration file.
     * @see dev.hyperskys.simpleBlocker.configuration.ConfigurationManager#reloadConfiguration(String)
     */
    public static void reloadConfiguration() {
        SimpleBlocker.getInstance().getConfigurationManager().reloadConfiguration("config");
    }

}

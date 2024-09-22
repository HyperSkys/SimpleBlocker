package dev.hyperskys.simpleBlocker.configuration;

import dev.hyperskys.simpleBlocker.SimpleBlocker;
import dev.hyperskys.simpleBlocker.exceptions.ConfigurationCreateException;
import dev.hyperskys.simpleBlocker.exceptions.ConfigurationNotFoundException;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ConfigurationManager {

    private final Map<String, FileConfiguration> configurationMap;

    public ConfigurationManager() {
        this.configurationMap = new HashMap<>();
    }

    /**
     * Create a configuration file with the specified file name
     * @param fileName The name of the configuration file to create
     */
    public void createConfigurationFile(String fileName) {
        try {
            File file = new File(SimpleBlocker.getInstance().getDataFolder(), fileName + ".yml");

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                InputStream inputStream = SimpleBlocker.getInstance().getResource(fileName + ".yml");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(new String(inputStream.readAllBytes()));
                fileWriter.close();
            }

            FileConfiguration fileConfiguration = new YamlConfiguration();
            fileConfiguration.load(file);

            configurationMap.put(fileName, fileConfiguration);
        } catch (IOException | InvalidConfigurationException exception) {
            throw new ConfigurationCreateException(exception);
        }
    }

    /**
     * Get the configuration file with the specified file name
     * @param fileName The name of the configuration file to get
     * @return The configuration file
     */
    public FileConfiguration getConfigurationFile(String fileName) {
        if (!configurationMap.containsKey(fileName)) {
            throw new ConfigurationNotFoundException("Configuration file not found: " + fileName + ".yml");
        }

        return configurationMap.get(fileName);
    }

    /**
     * Reload the configuration file with the specified file name
     * @param fileName The name of the configuration file to reload
     */
    public void reloadConfiguration(String fileName) {
        File file = new File(SimpleBlocker.getInstance().getDataFolder(), fileName + ".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        configurationMap.replace(fileName, fileConfiguration);
    }

}

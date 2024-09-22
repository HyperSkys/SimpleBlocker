package dev.hyperskys.simpleBlocker.exceptions;

/**
 * Thrown when a configuration file is not found in the plugin's data folder.
 * @see NullPointerException
 * @see dev.hyperskys.simpleBlocker.configuration.ConfigurationManager#getConfigurationFile(String)
 */
public class ConfigurationNotFoundException extends RuntimeException {
    public ConfigurationNotFoundException(String message) {
        super(message);
    }
}

package dev.hyperskys.simpleBlocker.exceptions;

/**
 * Thrown when an error occurs while creating a configuration file.
 * @see java.io.IOException
 * @see org.bukkit.configuration.InvalidConfigurationException
 * @see dev.hyperskys.simpleBlocker.configuration.ConfigurationManager#createConfigurationFile(String)
 */
public class ConfigurationCreateException extends RuntimeException {
    public ConfigurationCreateException(Throwable cause) {
        super(cause);
    }
}

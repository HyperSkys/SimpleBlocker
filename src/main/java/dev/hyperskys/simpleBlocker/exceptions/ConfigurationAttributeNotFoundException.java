package dev.hyperskys.simpleBlocker.exceptions;

/**
 * Thrown when a configuration attribute is not found in the configuration file.
 * @see NullPointerException
 * @see dev.hyperskys.simpleBlocker.configuration.enums.Configuration
 */
public class ConfigurationAttributeNotFoundException extends RuntimeException {
    public ConfigurationAttributeNotFoundException(String message) {
        super(message);
    }
}

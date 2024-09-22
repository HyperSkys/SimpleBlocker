package dev.hyperskys.simpleBlocker;

import dev.hyperskys.simpleBlocker.command.SimpleBlockerCommand;
import dev.hyperskys.simpleBlocker.configuration.ConfigurationManager;
import dev.hyperskys.simpleBlocker.listeners.CommandListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

@Getter
public final class SimpleBlocker extends JavaPlugin {

    private static @Getter SimpleBlocker instance;
    private ConfigurationManager configurationManager;

    @Override
    public void onLoad() {
        instance = this;
        this.configurationManager = new ConfigurationManager();
    }

    @Override
    public void onEnable() {
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);
        commandHandler.register(new SimpleBlockerCommand());
        commandHandler.registerBrigadier();
        configurationManager.createConfigurationFile("config");
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
    }

}

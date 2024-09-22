package dev.hyperskys.simpleBlocker.command;

import dev.hyperskys.simpleBlocker.configuration.enums.Configuration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.DefaultFor;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.Arrays;

@Command("simpleblocker")
@CommandPermission("simpleblocker.command.help")
public class SimpleBlockerCommand {

    /**
     * The default command for the /simpleblocker command, which displays the help message.
     * @param sender Provided by the command framework.
     */
    @DefaultFor("simpleblocker")
    public void onSimpleBlockerCommand(CommandSender sender) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray><st>-------------------------------"));
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<dark_red><bold>SimpleBlocker"));
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray><italic>Version: 1.0.0"));
        sender.sendMessage("");
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Available Commands:"));
        sender.sendMessage(MiniMessage.miniMessage().deserialize(" <gray>- <white>/simpleblocker list"));
        sender.sendMessage(MiniMessage.miniMessage().deserialize(" <gray>- <white>/simpleblocker reload"));
        sender.sendMessage("");
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray><st>-------------------------------"));
    }

    /**
     * The /simpleblocker list command, which lists all blocked commands.
     * @param sender Provided by the command framework.
     */
    @Subcommand("list")
    @CommandPermission("simpleblocker.command.list")
    public void onSimpleBlockerListCommand(CommandSender sender) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray><st>-------------------------------"));
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Blocked Commands:"));
        Arrays.stream(Configuration.BLOCKED_COMMANDS.getAsStringArray()).forEach(blockedCommand -> {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(" <gray>- <white>/" + blockedCommand));
        });
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray><st>-------------------------------"));
    }

    /**
     * The /simpleblocker reload command, which reloads the configuration file.
     * @param sender Provided by the command framework.
     */
    @Subcommand("reload")
    @CommandPermission("simpleblocker.command.reload")
    public void onSimpleBlockerReloadCommand(CommandSender sender) {
        Configuration.reloadConfiguration();
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>SimpleBlocker configuration reloaded!"));
    }

}

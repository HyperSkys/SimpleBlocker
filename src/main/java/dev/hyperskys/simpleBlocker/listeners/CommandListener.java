package dev.hyperskys.simpleBlocker.listeners;

import dev.hyperskys.simpleBlocker.configuration.enums.Configuration;
import dev.hyperskys.simpleBlocker.utilities.GroupUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.command.UnknownCommandEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.Arrays;

public class CommandListener implements Listener {

    /**
     * Listens for the PlayerCommandPreprocessEvent and checks if the command is blocked.
     * @param event Provided by the event handler.
     */
    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().substring(1).split(" ")[0];
        if (isCommandBlocked(command, player)) {
            player.sendMessage(Configuration.BLOCKED_MESSAGE.getAsComponent());
            player.sendActionBar(Configuration.BLOCKED_ACTIONBAR_MESSAGE.getAsComponent());
            if (Configuration.SOUND.getAsSound() != null) {
                player.playSound(player.getLocation(), Configuration.SOUND.getAsSound(), 1, 1);
            }
            event.setCancelled(true);
        }
    }

    /**
     * Listens for the UnknownCommandEvent and sends the unknown command message.
     * @param event Provided by the event handler.
     */
    @EventHandler
    public void onUnknownCommandEvent(UnknownCommandEvent event) {
        event.message(Configuration.UNKNOWN_COMMAND_MESSAGE.getAsComponent());
        if (event.getSender() instanceof Player player) {
            player.sendActionBar(Configuration.UNKNOWN_COMMAND_ACTIONBAR.getAsComponent());
            if (Configuration.SOUND.getAsSound() != null) {
                player.playSound(player.getLocation(), Configuration.SOUND.getAsSound(), 1, 1);
            }
        }
    }

    /**
     * Listens for the TabCompleteEvent and checks if the command is blocked.
     * @param event Provided by the event handler.
     */
    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event) {
        String command = event.getBuffer().split(" ")[0];
        if (isCommandBlocked(command, (Player) event.getSender())) {
            event.setCancelled(true);
        }
    }

    /**
     * Listens for the PlayerCommandSendEvent and removes blocked commands from the list.
     * @param event Provided by the event handler.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandSend(PlayerCommandSendEvent event) {
        event.getCommands().removeIf(command -> isCommandBlocked(command, event.getPlayer()));
    }

    /**
     * Checks if a command is blocked based on the configuration settings.
     * @param command The command to check.
     * @param player The player to check.
     * @return If the command is blocked.
     */
    private boolean isCommandBlocked(String command, Player player) {
        boolean blockNamespaced = Configuration.BLOCK_NAMESPACED_COMMANDS.getAsBoolean();
        boolean blockAllCommands = Configuration.BLOCK_ALL_COMMANDS.getAsBoolean();
        String[] blockedCommands = Configuration.BLOCKED_COMMANDS.getAsStringArray();

        if (command.contains(":") && blockNamespaced && !player.hasPermission("simpleblocker.bypass.namespaced")) {
            return true;
        }

        boolean isBlocked = Arrays.stream(blockedCommands).anyMatch(blockedCommand -> blockedCommand.equalsIgnoreCase(command));
        boolean bypassed = player.hasPermission("simplecommandblocker.bypass." + command);
        boolean groupBypass = GroupUtils.getGroup(player) != null && GroupUtils.groupCanUseCommand(GroupUtils.getGroup(player), command);

        return (isBlocked || blockAllCommands) && !bypassed && !groupBypass;
    }

}

package dev.hyperskys.simpleBlocker.utilities;

import dev.hyperskys.simpleBlocker.configuration.enums.Configuration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GroupUtils {

    /**
     * Get the group that the player is in
     * @param player The player to get the group of
     * @return The first found group that the player is in
     */
    public static String getGroup(Player player) {
        HashMap<String, String> groups = Configuration.GROUPS.getAsMap();
        for (String group : groups.keySet()) {
            if (player.hasPermission("simpleblocker.group." + group)) {
                return group;
            }
        }

        return null;
    }

    /**
     * Check if a group can use a command
     * @param group The group to check if they can use the command
     * @param command The command to check if the group can use
     * @return If the group can use the command
     */
    public static boolean groupCanUseCommand(String group, String command) {
        HashMap<String, String> groups = Configuration.GROUPS.getAsMap();
        return groups.containsKey(group) && groups.get(group).contains(command);
    }

}

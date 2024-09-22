package dev.hyperskys.simpleBlocker.utilities;

import dev.hyperskys.simpleBlocker.configuration.enums.Configuration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GroupUtils {

    public static String getGroup(Player player) {
        HashMap<String, String> groups = Configuration.GROUPS.getAsMap();
        for (String group : groups.keySet()) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }

        return null;
    }

    public static boolean groupCanUseCommand(String group, String command) {
        HashMap<String, String> groups = Configuration.GROUPS.getAsMap();
        return groups.containsKey(group) && groups.get(group).contains(command);
    }

}

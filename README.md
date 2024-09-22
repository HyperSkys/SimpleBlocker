# Simple Blocker
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/github/languages/code-size/HyperSkys/SimpleBlocker?color=cyan&label=Size&labelColor=000000&logo=GitHub&style=for-the-badge)
![GitHub](https://img.shields.io/github/license/HyperSkys/SimpleBlocker?color=violet&logo=GitHub&labelColor=000000&style=for-the-badge)
![Discord](https://img.shields.io/discord/289587909051416579?color=5865F2&label=Discord&logo=Discord&labelColor=23272a&style=for-the-badge)

**Simple Blocker** allows the server owner to block commands from showing when user does not have permission to use those commands.

### Download Instructions

You can download SimpleBlocker and use it on your server by heading to the releases tab and downloading the latest release available, if you download a beta release you may encounter bugs and issues that are not present in the stable release.

### Requirements

- Java 21 or higher
- Paper Server running Minecraft 1.21 or higher

### Installation

1. Download the latest release from the releases tab.
2. Place the downloaded jar file in your plugins folder.
3. Restart your server.
4. Configure the plugin to your liking.
5. Enjoy!

### Configuration

```yaml
# SimpleCommandBlocker - Configuration File
# This file is used to configure the plugin settings and messages.

Settings:
  block-all-commands: false
  block-namespaced-commands: true
  sound: "ENTITY_VILLAGER_NO" # https://jd.papermc.io/paper/1.21.1/org/bukkit/Sound.html
  blocked-commands:
    - version
    - ver
    - plugins
    - pl
    - icanhasbukkit
    - thiscommandisnotblockedforvip
    - thiscommandisnotblockedformvp
    - "?" # If command uses a symbol or special character, use the command in quotes.
  groups:
    mvp: # (Permission: simpleblocker.group.mvp)
      - thiscommandisnotblockedformvp
    vip: # (Permission: simpleblocker.group.vip)
      - thiscommandisnotblockedforvip
Messages:
  blocked-message: "<red>This command is blocked"
  blocked-actionbar: "<red>This command is blocked"
  unknown-command-message: "<red>Unknown command."
  unknown-command-actionbar: "<red>Unknown command."
```

### Mini Message Help

If you require help with MiniMessage formatting (ex: `<red>`) you can visit the [MiniMessage Documentation](https://docs.advntr.dev/minimessage/index.html) for more information.

### Permissions

- `simpleblocker.command.help` - Allows the player to view the help command.
- `simpleblocker.command.list` - Allows the player to view the list of blocked commands.
- `simpleblocker.command.reload` - Allows the player to reload the plugin configuration.
- `simpleblocker.bypass.<command>` - Allows the player to bypass a blocked command.
- `simpleblocker.bypass.namespaced` - Allows the player to bypass blocked namespaced commands. (ex: minecraft:version)
- `simpleblocker.group.<group>` - Allows the player to be in a group and bypass blocked commands for that group.

### Warning

This plugin is only compatible with Paper servers running Minecraft 1.21 or higher, using this plugin on any other server software or version may cause issues and is not recommended. Most of the time, the plugin will not even load on other server software or versions.

### Found a bug?

If you find a bug in the plugin, please report it in the [Issues](https://github.com/HyperSkys/SimpleBlocker/issues) tab on GitHub. Please provide as much information as possible to help us fix the issue.

### Have a suggestion?

If you have a suggestion for the plugin, please create a new issue in the [Issues](https://github.com/HyperSkys/SimpleBlocker/issues) tab on GitHub. Please provide as much information as possible to help us understand your suggestion.

### Pull Requests

If you would like to contribute to the plugin, you can create a pull request, and we will review it. Please make sure to follow the Code of Conduct below when creating a pull request.

### Code of Conduct

- When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

- Please use git commit messages that are clear and concise, this will help us understand the changes you have made.

- Ensure that your code is properly formatted and follows the style of the existing code.

- Do not add any unnecessary dependencies to the project.

- Do not remove any existing code unless it is necessary.

- Use comments to explain your code when necessary.

- Do not use AI to write all of your code for you, we don't want code added that was not understood by the person who wrote it.

- Use common sense when contributing to the project.

## License
This project is licensed under the [Eclipse Public License](https://github.com/HyperSkys/SimpleBlocker/blob/master/LICENSE)
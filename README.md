# Informator
A plugin for [NukkitX][nukkitx] server software for Minecraft Bedrock Edition, which allows customizing messages displayed when a player joins the game, leaves it or dies. The plugin also adds customizable messages about players getting banned or kicked. The customizable properties of messages include the color and the way of displaying (in the chat, as a popup or not displaying at all).

## Installation
- Download the latest .jar release and place it into the _plugins_ folder on your server;
- Start the server. An _Informator_ directory containing the configuration file _config.yml_ will be created inside the _plugins_ folder;
- Modify the configuration file to suit your needs.

## Configuration file
_join_, _quit_, _death_, _ban_, _kick_ parameters define the type of message to be displayed when corresponding events happen. Their values can be set to _chat_ (send the message to chat, which is the default option), _popup_ (the message will be displayed as a popup above the hotbar) or _none_ (the message will not be displayed at all).

_join-color_, _quit-color_, _death-color_, _ban-color_, _kick-color_ parameters define the colors of corresponding messages. Their values can be set to [Minecraft color codes][colors] (a digit or a letter in the range from _a_ to _f_).
  
## Building from sources
`mvn clean package`

## Links
* [Informator on nukkitx.com](https://nukkitx.com/resources/informator.32/)
* [Informator on nukkit.ru](http://forums.voxelwind.com/resources/informator.121/)

[nukkitx]: http://github.com/NukkitX/Nukkit
[colors]: https://minecraft.gamepedia.com/Formatting_codes#Color_codes

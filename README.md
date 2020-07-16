# Informator
**Informator** is a plugin for [NukkitX][nukkitx] server software for Minecraft Bedrock Edition, which allows customizing messages displayed when a player joins the game, leaves it or dies. The plugin also adds customizable messages about players getting banned or kicked. The customizable properties of messages include the color and the way of displaying (in the chat, as a popup or not displaying at all).

### Configuration file
_join_, _quit_, _death_, _ban_, _kick_ parameters define the type of message to be displayed when corresponding events happen. Their values can be set to _chat_ (send the message to chat, which is the default option), _popup_ (the message will be displayed as a popup above the hotbar) or _none_ (the message will not be displayed at all).

_join-color_, _quit-color_, _death-color_, _ban-color_, _kick-color_ parameters define the colors of corresponding messages. Their values can be set to [Minecraft color codes][colors] (a **0-9** digit or an **a-f** letter).
  
### Building from sources
`mvn clean package`

### Links
* [Informator on nukkitx.com](https://nukkitx.com/resources/informator.32/)
* [Informator on nukkit.ru](http://forums.voxelwind.com/resources/informator.121/)

[nukkitx]: http://github.com/NukkitX/Nukkit
[colors]: https://minecraft.gamepedia.com/Formatting_codes#Color_codes

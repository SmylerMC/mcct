# MC Client Tweaks
I sometime feel like the vanilla Minecraft client is missing some things, this is my attempt at fixing it.
MC Client Tweaks is a Minecraft [Fabric](https://fabricmc.net/) mod. It is entirely client side, and the tweaks should work on any server. Only Minecraft 1.15 is supported at the moment. [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) is required.
Each tweak can be toggled on or off and configured in the configuration screen. It can be accessed by pressing the assigned key binding in-game (J by default), or from the mod information page using [Modmenu](https://www.curseforge.com/minecraft/mc-mods/modmenu).

## Tweaks:
### Inventory:
 * The switch hand key can be used in the survival inventory just like the hotbar keys (only for 1.15, 1.16 has that feature in the vanilla game).
 * You can scroll the mouse to turn pages in the recipe book.
 * Get alerts when your inventory is full
 * Get alerts when your item durability is low
### Miscelaneous:
 * The audio output device can be changed in the sound/music options menu without restarting the game.
 * The lava fog density can be adjusted for better visibility under lava (only 1.15, fire resistance already improves visibility under lava in 1.16).
### Toasts:
Toasts are the little cards that show up on the upper right side on your screen on specific occasions such as unlocking new recipes. MCCT allows you to disable specific types of toasts.
### Bugfixes:
* Vanilla bug [MC-200083](https://bugs.mojang.com/browse/MC-200083) : Opening files hangs the game until the file viewer is closed on Linux (e.g. opening screenshots, resource pack folder, etc...).
 
## Compatibility with other mods:
MC Client Tweaks should be compatible with most mods. You can report incompatibility in the issues section. The lava fog tweak may not wotk with some Optifine/Optifabric versions, but has been tested to work with Optifine 15.2_HD_U_G1_pre13 and Optifabric 1.0.0-beta8.

## Screenshots:
![Configuration menu](https://raw.githubusercontent.com/SmylerMC/mcct/1.16.5/images/mcct_config_screenshot.png)
![Audio menu](https://raw.githubusercontent.com/SmylerMC/mcct/1.16.5/images/mcct_audio_screenshot.png)
![Lava fog](https://raw.githubusercontent.com/SmylerMC/mcct/1.16.5/images/mcct_lava_screenshot.png)


package ua.leonidius.informator;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase implements Listener {

    private String joinType;
    private String quitType;
    private String deathType;
    private String banType;
    private String kickType;
    private char joinColor;
    private char quitColor;
    private char deathColor;
    private char banColor;
    private char kickColor;

    @Override
    public void onEnable() {
        initCfg();
        loadCfg();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    private void initCfg() {
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
    }

    private void loadCfg() {
        joinType = this.getConfig().getString("join");
        joinColor = this.getConfig().getString("join-color").charAt(0);
        quitType = this.getConfig().getString("quit");
        quitColor = this.getConfig().getString("quit-color").charAt(0);
        deathType = this.getConfig().getString("death");
        deathColor = this.getConfig().getString("death-color").charAt(0);
        banType = this.getConfig().getString("ban");
        banColor = this.getConfig().getString("ban-color").charAt(0);
        kickType = this.getConfig().getString("kick");
        kickColor = this.getConfig().getString("kick-color").charAt(0);
    }

    private String textFromContainer(TextContainer container) {
        if (container instanceof TranslationContainer) {
            return this.getServer().getLanguage().translateString(container.getText(), ((TranslationContainer) container).getParameters());
        } else {
            return container.getText();
        }
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String msg = TextFormat.clean(this.textFromContainer(e.getJoinMessage()));
        String output = TextFormat.colorize("&" + joinColor + msg);
        if (joinType.equalsIgnoreCase("popup")) {
            e.setJoinMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (joinType.equalsIgnoreCase("none")) {
            e.setJoinMessage("");
        } else if (joinType.equalsIgnoreCase("chat")) {
            e.setJoinMessage(output);
        }
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        String msg = TextFormat.clean(this.textFromContainer(e.getQuitMessage()));
        String output = TextFormat.colorize("&" + quitColor + msg);
        if (joinType.equalsIgnoreCase("popup")) {
            e.setQuitMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (quitType.equalsIgnoreCase("none")) {
            e.setQuitMessage("");
        } else if (quitType.equalsIgnoreCase("chat")) {
            e.setQuitMessage(output);
        }
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        String msg = TextFormat.clean(this.textFromContainer(e.getDeathMessage()));
        String output = TextFormat.colorize("&" + deathColor + msg);
        if (deathType.equalsIgnoreCase("popup")) {
            e.setDeathMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (deathType.equalsIgnoreCase("none")) {
            e.setDeathMessage("");
        } else if (deathType.equalsIgnoreCase("chat")) {
            e.setDeathMessage(output);
        }
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if (e.getReasonEnum() == PlayerKickEvent.Reason.NAME_BANNED || e.getReasonEnum() == PlayerKickEvent.Reason.IP_BANNED) {
            TranslationContainer banInfo = new TranslationContainer("%commands.ban.success", e.getPlayer().getName());
            String msg = this.textFromContainer(banInfo);
            String output = TextFormat.colorize("&" + banColor + msg);
            if (banType.equalsIgnoreCase("popup")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendTip(output);
            } else if (banType.equalsIgnoreCase("chat")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values())
                    onlinePlayer.sendMessage(output);
            }
        } else if (e.getReasonEnum() == PlayerKickEvent.Reason.KICKED_BY_ADMIN) {
            TranslationContainer banInfo = new TranslationContainer("%commands.kick.success", e.getPlayer().getName());
            String msg = this.textFromContainer(banInfo);
            String output = TextFormat.colorize("&" + kickColor + msg);
            if (kickType.equalsIgnoreCase("popup")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendTip(output);
            } else if (kickType.equalsIgnoreCase("chat")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values())
                    onlinePlayer.sendMessage(output);
            }
        }
    }

}

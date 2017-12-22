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

/**
 * Created by lion on 12.03.17.
 */
public class Main extends PluginBase implements Listener{


    private String jointype;
    private String quittype;
    private String deathtype;
    private String bantype;
    private String kicktype;
    private char joincolor;
    private char quitcolor;
    private char deathcolor;
    private char bancolor;
    private char kickcolor;

    @Override
    public void onEnable(){
        initCfg();
        loadCfg();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    private void initCfg(){
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
    }

    private void loadCfg(){
        jointype = this.getConfig().getString("join");
        joincolor = this.getConfig().getString("join-color").charAt(0);
        quittype = this.getConfig().getString("quit");
        quitcolor = this.getConfig().getString("quit-color").charAt(0);
        deathtype = this.getConfig().getString("death");
        deathcolor = this.getConfig().getString("death-color").charAt(0);
        bantype = this.getConfig().getString("ban");
        bancolor = this.getConfig().getString("ban-color").charAt(0);
        kicktype = this.getConfig().getString("kick");
        kickcolor = this.getConfig().getString("kick-color").charAt(0);
    }

    private String textFromContainer(TextContainer container){
        if (container instanceof TranslationContainer){
            return this.getServer().getLanguage().translateString(container.getText(), ((TranslationContainer)container).getParameters());
        } else {
            return container.getText();
        }
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e){
        String msg = TextFormat.clean(this.textFromContainer(e.getJoinMessage()));
        String output = TextFormat.colorize("&"+joincolor+msg);
        if (jointype.equalsIgnoreCase("popup")) {
            e.setJoinMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (jointype.equalsIgnoreCase("none")){
            e.setJoinMessage("");
        } else if (jointype.equalsIgnoreCase("chat")){
            e.setJoinMessage(output);
        }
    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent e){
        String msg = TextFormat.clean(this.textFromContainer(e.getQuitMessage()));
        String output = TextFormat.colorize("&"+quitcolor+msg);
        if (jointype.equalsIgnoreCase("popup")) {
            e.setQuitMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (quittype.equalsIgnoreCase("none")){
            e.setQuitMessage("");
        } else if (quittype.equalsIgnoreCase("chat")){
            e.setQuitMessage(output);
        }
    }

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e){
        String msg = TextFormat.clean(this.textFromContainer(e.getDeathMessage()));
        String output = TextFormat.colorize("&"+deathcolor+msg);
        if (deathtype.equalsIgnoreCase("popup")) {
            e.setDeathMessage("");
            for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendPopup(output);
        } else if (deathtype.equalsIgnoreCase("none")){
            e.setDeathMessage("");
        } else if (deathtype.equalsIgnoreCase("chat")){
            e.setDeathMessage(output);
        }
    }

    @EventHandler
    public void onKick (PlayerKickEvent e){
        if(e.getReasonEnum() == PlayerKickEvent.Reason.NAME_BANNED || e.getReasonEnum() == PlayerKickEvent.Reason.IP_BANNED){
            TranslationContainer baninfo = new TranslationContainer("%commands.ban.success", e.getPlayer().getName());
            String msg = this.textFromContainer(baninfo);
            String output = TextFormat.colorize("&"+bancolor+msg);
            if (bantype.equalsIgnoreCase("popup")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendTip(output);
            } else if (bantype.equalsIgnoreCase("chat")){
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendMessage(output);
            }
        } else if (e.getReasonEnum() == PlayerKickEvent.Reason.KICKED_BY_ADMIN){
            TranslationContainer baninfo = new TranslationContainer("%commands.kick.success", e.getPlayer().getName());
            String msg = this.textFromContainer(baninfo);
            String output = TextFormat.colorize("&"+kickcolor+msg);
            if (kicktype.equalsIgnoreCase("popup")) {
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendTip(output);
            } else if (kicktype.equalsIgnoreCase("chat")){
                for (Player onlinePlayer : this.getServer().getOnlinePlayers().values()) onlinePlayer.sendMessage(output);
            }
        }
    }

}

package at.ldnfuture.gamemodesystem.main;

import at.ldnfuture.gamemodesystem.commands.GMAdventure;
import at.ldnfuture.gamemodesystem.commands.GMCreative;
import at.ldnfuture.gamemodesystem.commands.GMSurvival;
import at.ldnfuture.gamemodesystem.commands.GMSpectator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 03.08.2021
 */
public final class Main extends JavaPlugin {
    public static Main plugin;

    public Main() {
    }

    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        this.getCommand("gmc").setExecutor(new GMCreative());
        this.getCommand("gms").setExecutor(new GMSurvival());
        this.getCommand("gma").setExecutor(new GMAdventure());
        this.getCommand("gmsp").setExecutor(new GMSpectator());
    }
}

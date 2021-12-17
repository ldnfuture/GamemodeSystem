package at.ldnfuture.gamemodesystem.commands;

import at.ldnfuture.gamemodesystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 03.08.2021
 */
public class GMSpectator implements CommandExecutor {
    public GMSpectator() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player target;
        if (sender instanceof Player && args.length == 0) {
            target = (Player)sender;
            this.spectatorPlayerSelf(target);
        } else if (sender instanceof Player) {
            target = Bukkit.getPlayerExact(args[0]);
            Player player = (Player)sender;
            if (player.hasPermission("spectator.others")) {
                if (player == target) {
                    player.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Spectator Mode");
                    player.setGameMode(GameMode.SPECTATOR);
                } else {
                    player.sendMessage("You changed " + target.getDisplayName() + " into " + ChatColor.ITALIC + ChatColor.GRAY + "Spectator Mode");
                    target.setGameMode(GameMode.SPECTATOR);
                    boolean targetSendMessage = Main.plugin.getConfig().getBoolean("targetSendMessage");
                    if (targetSendMessage) {
                        target.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Spectator Mode");
                    }
                }
            }
        }

        if (sender instanceof ConsoleCommandSender && args.length == 0) {
            System.out.println("Please specify a player");
        }

        return false;
    }

    public void spectatorPlayerSelf(Player player) {
        if (player.getGameMode() == GameMode.SPECTATOR) {
            System.out.println("Player is already in Spectator!");
        } else if (player.hasPermission("spectator.self")) {
            player.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Spectator Mode");
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            player.sendMessage(ChatColor.RED + "Insufficient permissions!");
        }

    }
}

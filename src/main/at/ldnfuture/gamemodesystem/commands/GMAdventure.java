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
public class GMAdventure implements CommandExecutor {
    public GMAdventure() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player target;
        if (sender instanceof Player && args.length == 0) {
            target = (Player)sender;
            this.adventurePlayerSelf(target);
        } else if (sender instanceof Player) {
            target = Bukkit.getPlayerExact(args[0]);
            Player player = (Player)sender;
            if (player.hasPermission("adventure.others")) {
                if (player == target) {
                    player.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Adventure Mode");
                    player.setGameMode(GameMode.ADVENTURE);
                } else {
                    player.sendMessage("You changed " + target.getDisplayName() + " into " + ChatColor.ITALIC + ChatColor.GRAY + "Adventure Mode");
                    target.setGameMode(GameMode.ADVENTURE);
                    boolean targetSendMessage = Main.plugin.getConfig().getBoolean("targetSendMessage");
                    if (targetSendMessage) {
                        target.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Adventure Mode");
                    }
                }
            }
        }

        if (sender instanceof ConsoleCommandSender && args.length == 0) {
            System.out.println("Please specify a player");
        }

        return false;
    }

    public void adventurePlayerSelf(Player player) {
        if (player.getGameMode() == GameMode.ADVENTURE) {
            System.out.println("Player is already in Adventure!");
        } else if (player.hasPermission("adventure.self")) {
            player.sendMessage("Your gamemode has been updated to " + ChatColor.ITALIC + ChatColor.GRAY + "Adventure Mode");
            player.setGameMode(GameMode.ADVENTURE);
        } else {
            player.sendMessage(ChatColor.RED + "Insufficient permissions!");
        }

    }
}

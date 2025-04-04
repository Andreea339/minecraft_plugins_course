package com.andreea.test;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("firstplugin.use")) {
                player.sendMessage(ChatColor.GREEN + "You have permission to use this");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }
        }

        return false;
    }
}

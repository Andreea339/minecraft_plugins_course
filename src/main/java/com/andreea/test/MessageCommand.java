package com.andreea.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    // /message <player> <message>
    private PrivateMessageSystem privateMessageSystem;

    public MessageCommand(PrivateMessageSystem privateMessageSystem) {
        this.privateMessageSystem = privateMessageSystem;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length >= 2) {
               if (Bukkit.getPlayerExact(args[0]) != null) {
                   Player target = Bukkit.getPlayerExact(args[0]);

                   StringBuilder builder = new StringBuilder();

                   for (int i = 1; i < args.length; i++) {
                       builder.append(args[i]).append(" ");
                   }

                   player.sendMessage("You -> " + target.getName() + ": " + builder);
                   target.sendMessage(player.getName() + " -> You: " + builder);

                   privateMessageSystem.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

               } else {
                   player.sendMessage(ChatColor.RED + "This player was not found");
               }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid message! Use /message <player> <message>");
            }

        }

        return false;
    }
}

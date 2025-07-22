package com.andreea.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    // /reply <message>

    private PrivateMessageSystem privateMessageSystem;

    public ReplyCommand(PrivateMessageSystem privateMessageSystem) {
        this.privateMessageSystem = privateMessageSystem;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length >= 1) {
                if (privateMessageSystem.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = privateMessageSystem.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();

                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage("You -> " + target.getName() + ": " + builder);
                        target.sendMessage(player.getName() + " -> You: " + builder);
                    } else {
                        player.sendMessage(ChatColor.RED + "This player is not online");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid message! Use /reply <message>");
            }
        }
        return false;
    }
}

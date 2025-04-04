package com.andreea.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            // if first argument is equal to hello, return back hello
            Player player = (Player) commandSender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("hello")) {
                    String name = player.getName();
                    player.sendMessage("Hello my friend, " + name);
                }
            }
        }


        return false;
    }
}

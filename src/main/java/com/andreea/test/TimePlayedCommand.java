package com.andreea.test;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimePlayedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            int ticksPlayed = player.getStatistic(Statistic.PLAY_ONE_MINUTE);

            int totalSeconds = ticksPlayed/20;
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;


            player.sendMessage(player.getName() + " has played " + hours + " h " + minutes + " m");

        }
        return false;
    }
}

package com.andreea.test;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {


        if (args.length == 1) {

            return StringUtil.copyPartialMatches(args[0], Arrays.asList("Melon", "Apple", "Berries"), new ArrayList<>());

        } else if (args.length == 2) {

            List<String> names = new ArrayList<>();


            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());

        }

        return new ArrayList<>();
    }
}

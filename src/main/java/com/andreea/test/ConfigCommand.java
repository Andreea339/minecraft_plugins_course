package com.andreea.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ConfigCommand implements CommandExecutor {

    private Main main;
    public ConfigCommand(Main main) {
        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

//            main.getConfig().set("Word", "Cake");
//
//            List<String> list = main.getConfig().getStringList("String-list");
//            list.add("New Value");
//            main.getConfig().set("String-list", list);
//
//            main.saveConfig();
//
            player.sendMessage(main.getConfig().getString("Word"));
            player.sendMessage(main.getConfig().getInt("Number") + "");

            if (main.getConfig().getBoolean("Enabled")) {
                player.sendMessage("This feature is enabled");
            }

            for (String string : main.getConfig().getStringList("String-list")) {
                player.sendMessage(string);
            }

        }

        return false;
    }
}

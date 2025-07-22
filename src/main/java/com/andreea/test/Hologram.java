package com.andreea.test;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Hologram implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            String[] lines = new String[]{
                    ChatColor.RED + "Line1",
                    ChatColor.GREEN + "Line2",
                    ChatColor.LIGHT_PURPLE + "Line3"};


            Location location = player.getLocation();
            for (String line : lines) {
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location.add(0, 0.3, 0), EntityType.ARMOR_STAND);
                stand.setInvisible(true);
                stand.setInvulnerable(true);
                stand.setGravity(false);

                stand.setCustomNameVisible(true);
                stand.setCustomName(line);
            }
        }
        return false;
    }
}
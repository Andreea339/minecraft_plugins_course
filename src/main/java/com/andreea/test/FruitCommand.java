package com.andreea.test;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class FruitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {



        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack apple = new ItemStack(Material.APPLE);
            ItemMeta appleMeta = (ItemMeta) apple.getItemMeta();

            ItemStack slice = new ItemStack(Material.MELON_SLICE);
            ItemMeta sliceMeta = (ItemMeta) slice.getItemMeta();

            ItemStack berries = new ItemStack(Material.GLOW_BERRIES);
            ItemMeta berriesMeta = (ItemMeta) berries.getItemMeta();

            if (Objects.equals(args[0], "Apple")) {
                apple.setItemMeta(appleMeta);
                player.getInventory().addItem(apple);
            } else if (Objects.equals(args[0],"Melon")) {
                slice.setItemMeta(sliceMeta);
                player.getInventory().addItem(slice);
            } else if (Objects.equals(args[0], "Berries")) {
                berries.setItemMeta(berriesMeta);
                player.getInventory().addItem(berries);
            } else {
                player.sendMessage("Wrong command");
            }

        }

        return false;
    }
}

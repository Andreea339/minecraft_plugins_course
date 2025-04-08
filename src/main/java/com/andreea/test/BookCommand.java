package com.andreea.test;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(ChatColor.RED + "My book");
            meta.setAuthor(ChatColor.YELLOW + "Andreea");
            meta.addPage(
                    ChatColor.LIGHT_PURPLE + "Welcome to my first book" +
                            "\n\n" + ChatColor.LIGHT_PURPLE + "This book is about......."
            );
            book.setItemMeta(meta);
            player.getInventory().addItem(book);
        }

        return false;
    }
}

package com.andreea.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

// This class works only when I change the name to Main and I delete the Main class (as for now)
// because of the project creation
public class GunsPlugin extends JavaPlugin implements Listener {

    // DIAMOND HOE = SNOWBALL LAUNCHER
    // IRON HOE = EGG LAUNCHER

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.hasItem()) {
            Player player = e.getPlayer();
            if (e.getItem().getType().equals(Material.DIAMOND_HOE)) {

                player.launchProjectile(Snowball.class);
                player.sendMessage(ChatColor.GREEN + "You have shot a snowball");

            } else if (e.getItem().getType().equals(Material.IRON_HOE)) {

                player.launchProjectile(Egg.class);
                player.sendMessage(ChatColor.GREEN + "You have shot an egg");

            }
        }

    }
}

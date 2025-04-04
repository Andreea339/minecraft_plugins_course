package com.andreea.test;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;

public final class Main extends JavaPlugin implements Listener {

    private org.bukkit.boss.BossBar bossBar;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this, this);

        // Bar that appears at the top of the screen all the time
        Bukkit.getPluginManager().registerEvents(this, this);
        bossBar = Bukkit.createBossBar(
                ChatColor.LIGHT_PURPLE + "This server is awesome",
                BarColor.PURPLE,
                BarStyle.SEGMENTED_6,
                BarFlag.DARKEN_SKY
        );

        // Some commands

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("console").setExecutor(new ConsoleCommand());
        getCommand("config").setExecutor(new ConfigCommand(this));
        getCommand("permission").setExecutor(new PermissionCommand());


        // To spawn an entity at a specific location
        Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 0, 61, 0), EntityType.BEE);

    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Firework firework = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
        FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA).withColor(Color.YELLOW).with(Type.STAR).withFlicker().build());
        // Power of the firework, from 1-3
        meta.setPower(2);
        firework.setFireworkMeta(meta);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 3));

//         This is for removing or adding potion effects to a player
//        for (PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
//            e.getPlayer().removePotionEffect(effect.getType());
//        }

        // To create some text at connecting to the server
        e.getPlayer().sendTitle(
                ChatColor.RED + "Welcome back",
                ChatColor.GREEN + "Welcome to the server",
                20,
                100,
                20
        );
        bossBar.addPlayer(e.getPlayer());

        // To show on the tab "menu" some information about the server or the player
        // Can be customised

        e.getPlayer().setPlayerListHeaderFooter(ChatColor.AQUA + "Hello", "First Line \nSecondLine");
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§lHELLO MY FRIEND"));


//        // This gives the player some items when joining the server
//
//        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
//        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
//        helmetMeta.setColor(Color.YELLOW);
//        helmet.setItemMeta(helmetMeta);
//
//        e.getPlayer().getInventory().addItem(helmet);
//
//        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
//        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
//        chestplateMeta.setColor(org.bukkit.Color.fromRGB(179, 255, 255));
//        chestplate.setItemMeta(chestplateMeta);
//
//        e.getPlayer().getInventory().addItem(chestplate);


    }
    // This command applies when the player tries to move
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent e) {
//
//        // Player can't move
//        e.setCancelled(true);
//        e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Stop moving, you are frozen :) ");
//
//    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent e) {

        e.getPlayer().sendMessage(ChatColor.AQUA + "You threw and egg :) ");
    }
}

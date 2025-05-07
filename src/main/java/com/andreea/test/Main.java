package com.andreea.test;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
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

        Bukkit.getPluginManager().registerEvents(new ToggleListener(), this);

        Bukkit.getPluginManager().registerEvents(this, this);

        // Bar that appears at the top of the screen all the time
        Bukkit.getPluginManager().registerEvents(this, this);
        bossBar = Bukkit.createBossBar(
                ChatColor.LIGHT_PURPLE + "This server is awesome",
                BarColor.PURPLE,
                BarStyle.SEGMENTED_6,
                BarFlag.DARKEN_SKY
        );

        // Player player = null;
        // player.getWorld().setStorm(true); // this doesn't work as it is
        // Time works different, in ticks
        // 6 am -> 0 or 24000
        // 12pm (midday) -> 6000
        // 6pm -> 12000
        // 12am (midnight) -> 18000
        // player.getWorld().setTime(1200);

        // Some commands

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("console").setExecutor(new ConsoleCommand());
        getCommand("config").setExecutor(new ConfigCommand(this));
        getCommand("permission").setExecutor(new PermissionCommand());

        getCommand("vanish").setExecutor(new VanishCommand());

        getCommand("book").setExecutor(new BookCommand());

        getCommand("banner").setExecutor(new BannerCommand());

        getCommand("punish").setExecutor(new PunishCommand());


        // To spawn an entity at a specific location
        Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 0, 61, 0), EntityType.BEE);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        if (e.getHand().equals(EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (player.getInventory().getItemInMainHand() != null
                        && player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)) {
                    player.launchProjectile(Egg.class, player.getLocation().getDirection());
                }

            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
//        Firework firework = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
//        FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
//        meta.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA).withColor(Color.YELLOW).with(Type.STAR).withFlicker().build());
//        // Power of the firework, from 1-3
//        meta.setPower(2);
//        firework.setFireworkMeta(meta);
        //e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_WOOD_HANGING_SIGN_BREAK, 1.5F, 1.0F);
//        Music block sounds in game and discs
//        if (e.isSneaking()) {
//            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 0.79F);
//            e.getPlayer().playEffect(new Location(Bukkit.getWorld("world"), -60, 64, -9),
//                    Effect.RECORD_PLAY, Material.MUSIC_DISC_CAT);
//        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        // Particles are there, but they need an event to actually activate them
        Player player = e.getPlayer();
        Bukkit.getLogger().info("onJoin fired for " + player.getName());
        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().clone().add(0,2,0), 30);

        // Potion effects
        // Duration is in ticks, so in a second there are 20 ticks
        //e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 3));

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

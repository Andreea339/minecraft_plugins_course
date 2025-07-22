package com.andreea.test;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;

public final class Notes2 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getCommand("fruit").setExecutor(new FruitCommand());
        getCommand("fruit").setTabCompleter(new FruitTab());

        getCommand("menu").setExecutor(new MenuCommand());
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);

        getCommand("boost").setExecutor(new BoostCommand());
        Bukkit.getPluginManager().registerEvents(this, this);
// -------------------------------------------------------------------------------------------------------------------//

        try {
            initiateFile("homes.yml");
            initiateFile("warps.yml");
            initiateFile("data.yml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

// -------------------------------------------------------------------------------------------------------------------//
        Data data = new Data("Andreea", true, new Date());
        try {
            getDataFolder().mkdir();
            File file = new File(getDataFolder(), "data.json");
            if (!file.exists()) {
                file.createNewFile();
            }

            Gson gson = new Gson();
            Reader reader = new FileReader(file);
            Data readData = gson.fromJson(reader, Data.class);
            //Writer writer = new FileWriter(file, false);
            //gson.toJson(data, writer);
            //writer.flush();
            //writer.close();
            //System.out.println("Saved data!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To create custom YML files
    public void initiateFile(String name) throws Exception {
        File file = new File(getDataFolder(), name);
        if (!file.exists()) {
            file.createNewFile();
        }
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);
    }

    //   This method makes a block or a sign change when on shift. THe change happens just for that player, so no one
    // else can see that
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        Player player = e.getPlayer();

        // this checks if the player is looking at a block in 5 blocks
        if (e.isSneaking() && player.getTargetBlockExact(5) != null && player.getTargetBlockExact(5).getType().equals(Material.OAK_SIGN)) {

            player.sendSignChange(player.getTargetBlockExact(5).getLocation(), new String[]{
                    "This is a test",
                    "",
                    "!",
                    "YEEY"
            });
            //player.sendBlockChange(player.getTargetBlockExact(5).getLocation(), Material.REDSTONE_BLOCK.createBlockData());

        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            BlockState state = e.getClickedBlock().getState();
            BlockData data = state.getBlockData();
            if (data instanceof Cake) {
                ((Cake) data).setBites(2);
            } else if (data instanceof GlassPane) {
                ((GlassPane) data).setWaterlogged(true);
            } else if (data instanceof Rail) {
                ((Rail) data).setShape(Rail.Shape.NORTH_SOUTH);
            }
            state.setBlockData(data);
            state.update();
        }
    }

}


//
//    @Override
//    public void onEnable() {
//        Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> {
//            for (Player player : Bukkit.getOnlinePlayers()) {
//                player.sendMessage("Server has been up for 10 seconds!");
//            }
//        }, 200);
//
//        Bukkit.getScheduler().runTaskTimer(this, () -> {
//            for (Player player : Bukkit.getOnlinePlayers()) {
//                player.sendMessage("It's been 15 seconds - and this goes every 1 second");
//            }
//        }, 300, 20);
//    }


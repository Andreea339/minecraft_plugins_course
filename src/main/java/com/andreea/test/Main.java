package com.andreea.test;

import com.sun.org.apache.bcel.internal.generic.NameSignatureInstruction;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        getCommand("perms").setExecutor(new PermsCommand(this));

        World world = Bukkit.getWorld("world");

        // TEXT DISPLAY

        TextDisplay textDisplay = (TextDisplay) world.spawnEntity(world.getBlockAt(21015,70,-1217).getLocation(), EntityType.TEXT_DISPLAY);
        textDisplay.setText(ChatColor.GREEN + "Join me in this adventure");
        textDisplay.setBackgroundColor(Color.AQUA);


        // BLOCK DISPLAY

        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(world.getBlockAt(21015,75,-1217).getLocation(), EntityType.BLOCK_DISPLAY);
        blockDisplay.setBlock(Material.BIRCH_PLANKS.createBlockData());
        blockDisplay.setBrightness(new Display.Brightness(10, 10));
        blockDisplay.setRotation(5,8);

        // ITEM DISPLAY

        ItemDisplay itemDisplay = (ItemDisplay) world.spawnEntity(world.getBlockAt(21015,80,-1217).getLocation(), EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(new ItemStack(Material.WOODEN_SWORD));
        itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.GUI);


        getCommand("cooldown").setExecutor(new CooldownCommand());
        getCommand("hologram").setExecutor(new Hologram());


        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "custom_diamond_sword"),
                new ItemStack(Material.DIAMOND_SWORD));
        recipe.shape(
                " D ",
                " D ",
                " D ");
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);

        ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(this, "barrier"),
                new ItemStack(Material.BARRIER));
        recipe2.shape(
                "R R",
                " R ",
                "R R");
        recipe2.setIngredient('R', Material.REDSTONE_BLOCK);
        Bukkit.addRecipe(recipe2);

        // STICK ITEM

        ItemStack is = new ItemStack(Material.STICK);
        is.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 6);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD + "CUSTOM STICK");
        is.setItemMeta(isMeta);

        ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(this, "custom_stick"),
                is);
        recipe3.shape(
                "GGG",
                "GSG",
                "GGG");
        recipe3.setIngredient('S', Material.STICK);
        recipe3.setIngredient('G', Material.GOLD_BLOCK);
        Bukkit.addRecipe(recipe3);
    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent e) {

        MapView view = e.getMap();
        // This is to remove background
//        for (MapRenderer renderer : view.getRenderers()) {
//            view.removeRenderer(renderer);
//        }

        view.addRenderer(new Renderer());
    }

}

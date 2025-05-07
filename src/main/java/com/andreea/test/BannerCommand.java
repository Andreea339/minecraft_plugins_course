package com.andreea.test;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class BannerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {

        if (sender instanceof Player) {
            ItemStack item = new ItemStack(Material.WHITE_BANNER);
            BannerMeta meta = (BannerMeta) item.getItemMeta();

            List<Pattern> patterns = new ArrayList<>();

            patterns.add(new Pattern(DyeColor.LIGHT_BLUE, PatternType.GRADIENT));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.PIGLIN));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.CIRCLE_MIDDLE));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.CREEPER));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_TOP));

            meta.setPatterns(patterns);
            item.setItemMeta(meta);

            ((Player) sender).getInventory().addItem(item);
        }

        return false;
    }
}

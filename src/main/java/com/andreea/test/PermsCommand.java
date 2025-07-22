package com.andreea.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class PermsCommand implements CommandExecutor {

    private final HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    private final Main main;

    public PermsCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            PermissionAttachment attachment;
            if (!perms.containsKey(player.getUniqueId())) {
                attachment = player.addAttachment(main);
                perms.put(player.getUniqueId(), attachment);
            } else {
                attachment = perms.get(player.getUniqueId());
            }

            Boolean current = attachment.getPermissions().get("worldedit.help");

            if (current != null && current) {
                attachment.setPermission("bukkit.command.help", false);
                attachment.setPermission("worldedit.help", false);
                player.sendMessage("Removed perms");
            } else {
                attachment.unsetPermission("bukkit.command.help");
                attachment.setPermission("worldedit.help", true);
                player.sendMessage("Added perms");
            }


        }

        return true;
    }
}

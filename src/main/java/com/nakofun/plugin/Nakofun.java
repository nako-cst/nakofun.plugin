
package com.nakofun.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Nakofun extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // Create plugins/nakofun/ and config.yml if they don't exist
        saveDefaultConfig();

        // Register the /nf command
        getCommand("nf").setExecutor(this);

        getLogger().info("nakofun plugin has been fully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("nakofun plugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {

        if (command.getName().equalsIgnoreCase("nf")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command!");
                return true;
            }

            Player player = (Player) sender;

            // Only nako_cst can use this command
            if (!player.getName().equals("nako_cst")) {
                player.sendMessage(
                    ChatColor.RED + "You cannot use this command!"
                );
                return true;
            }

            player.sendMessage(ChatColor.GREEN + "It worked!");
            return true;
        }

        return false;
    }
}

package com.nakofun.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Nakofun extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("nf").setExecutor(this);
        getLogger().info("nakofun plugin has been fully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("nakofun plugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("nf")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command!");
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("nako.fun")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }

            if (args.length > 0) {

                if (args[0].equalsIgnoreCase("particles")) {
                    Location loc = player.getLocation();
                    
                    double radius = 1.5;     
                    int points = 30;         

                    for (int i = 0; i < points; i++) {
                        double angle = 2 * Math.PI * i / points;
                        double x = radius * Math.cos(angle);
                        double z = radius * Math.sin(angle);
                        
                        Location particleLoc = loc.clone().add(x, 0.1, z);
                        
                        // Spawns purple redstone particles
                        player.spawnParticle(org.bukkit.Particle.REDSTONE, particleLoc, 0, 1.0, 0.0, 1.0, 1.0);
                    }
                    
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "⭕ Particle circle spawned!");
                    return true;
                }
                
                // If they typed an argument that isn't "particles"
                player.sendMessage(ChatColor.RED + "Unknown argument! Use /nf particles");
                return true;
            } else {
                // If they just typed /nf with no arguments at all
                player.sendMessage(ChatColor.GOLD + "Use: /nf particles");
                return true;
            }
        }

        return false;
    }
}

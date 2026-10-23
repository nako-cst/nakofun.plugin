package com.nakofun.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location; //  0) {

                if (args[0].equalsIgnoreCase("particles")) {
                    Location loc = player.getLocation();
                    
                    double radius = 1.5;     
                    int points = 30;         

                    for (int i = 0; i < points; i++) {
                        double angle = 2 * Math.PI * i / points;
                        double x = radius * Math.cos(angle);
                        double z = radius * Math.sin(angle);
                        
                        Location particleLoc = loc.clone().add(x, 0.1, z);
                        
                        // FIXED: Changed 1.0.0 typo to 1.0. (This spawns Purple!)
                        player.spawnParticle(org.bukkit.Particle.REDSTONE, particleLoc, 0, 1.0, 0.0, 1.0, 1.0);
                    }
                    return true;
                }
            } else {
                // What happens if they just type /nf with no arguments
                player.sendMessage(ChatColor.GOLD + "Use: /nf particles");
                return true;
            }

        }

        return false;
    }
}

package com.nakofun.plugin;

import org.bukkit.*;              
import org.bukkit.command.*;      
import org.bukkit.entity.*;       
import org.bukkit.event.*;        
import org.bukkit.event.inventory.*; // Needed specifically to handle inventory clicks
import org.bukkit.event.player.*; 
import org.bukkit.inventory.*;      // Needed to create inventories and items
import org.bukkit.inventory.meta.*; // Needed to edit item display names and lore
import org.bukkit.plugin.java.JavaPlugin; 

public class Nakofun extends JavaPlugin implements Listener, CommandExecutor {

    // Unique title to easily identify our custom GUI menu screen
    private final String guiTitle = ChatColor.DARK_PURPLE + "Nakofun Shenanigans";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("nf").setExecutor(this);
        getLogger().info("Nakofun GUI plugin has been fully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Nakofun GUI plugin has been disabled.");
    }

    // --- FEATURE 1: COMMAND TO OPEN CHEST GUI (/nf) ---
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nf")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                
                // Create a virtual inventory chest with 9 slots (Must be a multiple of 9)
                Inventory myGui = Bukkit.createInventory(player, 9, guiTitle);
                
                // Create a cool item to put inside the GUI (A diamond sword)
                ItemStack goldenItem = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta meta = goldenItem.getItemMeta();
                
                if (meta != null) {
                    meta.setDisplayName(ChatColor.GOLD + "✨ Nakofun's Mega Sword");
                    goldenItem.setItemMeta(meta);
                }
                
                // Place the diamond sword right in the middle slot (Slot index 4)
                myGui.setItem(4, goldenItem);
                
                // Physically open the inventory menu right on the player's screen
                player.openInventory(myGui);
                player.sendMessage(ChatColor.GRAY + "Opening the Nakofun main menu panel...");
                return true;
            } else {
                sender.sendMessage("Only active players can view user menus!");
                return true;
            }
        }
        return false;
    }

    // --- FEATURE 2: PREVENT PLAYERS FROM STEALING/MOVING GUI ITEMS ---
    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        // Only run our check if the opened inventory title matches our specific GUI title
        if (event.getView().getTitle().equals(guiTitle)) {
            
            // Cancel the event so they can't grab or shift-click the items into their bag
            event.setCancelled(true);
            
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            
            // Safety check: Did they actually click a real item slot?
            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                return;
            }
            
            // Optional action: Trigger something fun if they click our custom Diamond Sword
            if (clickedItem.getType() == Material.DIAMOND_SWORD) {
                player.sendMessage(ChatColor.YELLOW + "You clicked the sword! awesome!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                player.closeInventory(); // Automatically shut the GUI screen
            }
        }
    }
}

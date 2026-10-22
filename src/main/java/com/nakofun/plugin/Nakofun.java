package com.nakofun.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Nakofun extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("nakofun has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("nakofun has been disabled!");
    }
}

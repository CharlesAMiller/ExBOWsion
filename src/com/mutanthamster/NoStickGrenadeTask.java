package com.mutanthamster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class NoStickGrenadeTask extends BukkitRunnable {
	 
    private final JavaPlugin plugin;
 
    Location toDetonate;
    
    public NoStickGrenadeTask(JavaPlugin plugin, Location loc) {
        this.plugin = plugin;
        toDetonate = loc;
    }
 
    public void run() 
    {
    	toDetonate.getWorld().createExplosion(toDetonate.getX(), toDetonate.getY(), toDetonate.getZ(), 4, false, false);
    }
    
}
package com.mutanthamster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class BroadcastTask extends BukkitRunnable 
{
	 
    private final JavaPlugin plugin;
 
    Player player;
    
    public BroadcastTask(JavaPlugin plugin, Player playerStuck) {
        this.plugin = plugin;
        player = playerStuck;
    }
 
    public void run() 
    {
    	player.getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 4, false, false);
    }
    
}
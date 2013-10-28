package com.mutanthamster.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class PlayerDeathTask extends BukkitRunnable 
{
	 
    private final JavaPlugin plugin;
 
    PlayerDeathEvent event;
    
    public PlayerDeathTask(JavaPlugin plugin, PlayerDeathEvent event) 
    {
        this.plugin = plugin;
        this.event = event;
    }
 
    public void run() 
    {
    	Player playerDead = event.getEntity();
    	Player playerKiller = playerDead.getKiller();
    	
    	event.getDrops().clear();
    	event.setDroppedExp(0);
    	
    	playerKiller.setLevel(playerKiller.getLevel()+1);
    	playerDead.setLevel(0);
    }
    
}
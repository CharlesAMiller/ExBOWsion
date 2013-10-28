package com.mutanthamster.tasks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnTask extends BukkitRunnable
{
	
    private final JavaPlugin plugin;
    Player killedPlayer; 
    ArrayList<Location> spawnLocations;
 
    public SpawnTask(JavaPlugin plugin, Player killedPlayer, ArrayList<Location> spawnLocations) 
    {
        this.plugin = plugin;
        this.killedPlayer = killedPlayer;
        this.spawnLocations = spawnLocations;
    }
 
    public void run() 
    {
    	
    	if(killedPlayer.getMetadata("Arena"))
    		
    	Location toCompare = event.getPlayer().getLocation();
    	double temp; temp = toCompare.distance(toCompare);
    	Location toTeleport = event.getPlayer().getLocation();
    	
    	for(int i = 0; i < spawns.size(); i++)
    	{
    		
    		if(temp < spawns.get(i).distance(toCompare))
    		{
    			temp = spawns.get(i).distance(toCompare);
    			toTeleport = spawns.get(i);
    		}
    	}
    	
    	event.setRespawnLocation(toTeleport);
	}
    }
    
}
package com.mutanthamster.tasks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mutanthamster.Arena;

public class SpawnTask extends BukkitRunnable
{
	
    private final JavaPlugin plugin;
    Player killedPlayer; 
    ArrayList<Location> spawnLocations;
    PlayerRespawnEvent event;
    Arena myArena;
    
    public SpawnTask(JavaPlugin plugin, PlayerRespawnEvent event)
    {
    	this.plugin = plugin;
    	this.event = event;
    	this.killedPlayer = event.getPlayer();
    }
    
    public void run() 
    {
    	myArena = (Arena) event.getPlayer().getMetadata("Arena").get(0);
    	
    	int avgPopulators, avgDistance, minThreshHold, spawnPlaceInList = 1000;
    	
    	if(myArena.getGametype() == "Deathmatch" || myArena.getGametype() == "DM")
    	{
    		minThreshHold = 3;
    		avgDistance = 0;
    		
    		BukkitTask task = new AwardInventoryTask(this.plugin, event, "D").runTaskLater(this.plugin, 0);
    		
    		for(int i = 0; i < myArena.getSpawns().size(); i++)
    		{
    			for(int j = 0; j < myArena.getPopulators().size(); j++)
    			{
    				if(myArena.getPopulators().get(j).getLocation().distance(myArena.getSpawns().get(i)) > minThreshHold)
    				{
    					int avgDistanceTemp = (int) (avgDistance + myArena.getPopulators().get(j).getLocation().distance(myArena.getSpawns().get(i))/j + 1);
    					if(avgDistanceTemp > avgDistance)
    					{
    						avgDistance = avgDistanceTemp;
    						spawnPlaceInList = i;
    					}
    						
    				}
    			}
    		}
    	}else if(myArena.getGametype() == "Team Deatmatch" || myArena.getGametype() == "TDM")
    	{
    		//Do TDM Stuff
    	}
    	
    	if(spawnPlaceInList != 1000)
    	{
    		event.setRespawnLocation(myArena.getSpawn(spawnPlaceInList));
    	}
    	
	}
    
    
}
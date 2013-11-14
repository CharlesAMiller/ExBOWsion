package com.mutanthamster;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class Arena
{
	
	String gameType;
	Location p1, p2;
	int minPlayerSize;
	
	Scoreboard myScoreboard;
	
	ArrayList<Player> populators = new ArrayList<>();
	ArrayList<Location> spawns = new ArrayList<>();
	
	public Arena(final Location p1, final Location p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/*public void Arena(Location p1, Location p2, String gameType, int minPlayerSize)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.gameType = gameType;
		this.minPlayerSize = minPlayerSize;
	}*/
	
	public void addSpawn(Location addedSpawn){ spawns.add(addedSpawn); }
	
	public void addSpawns(ArrayList<Location> addedSpawns){ spawns.addAll(addedSpawns); }
	
	public void addPlayerToArena(Player player)
	{
		populators.add(player);
		if(populators.size() >= minPlayerSize)
		{
			for(int i = 0; i < populators.size(); i++)
				populators.get(i).sendMessage(ChatColor.GREEN + "The game will be starting");
		}
		
	}
	
	public String getGametype(){ return gameType; }
	
	public Location getSpawn(int spawn){ return spawns.get(spawn); }
	
	public ArrayList<Location> getArenaPoints()
	{
		ArrayList<Location> toReturn = new ArrayList<>();
		toReturn.add(p1);
		toReturn.add(p2);
		
		return toReturn;
	}
	
	public void setGametype(String gameType){ this.gameType = gameType; }
	
	public ArrayList<Player> getPopulators(){ return populators; }
	
	public ArrayList<Location> getSpawns() { return spawns; }
}

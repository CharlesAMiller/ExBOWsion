package com.mutanthamster.tasks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.mutanthamster.items.ItemBase;

public class AwardInventoryTask extends BukkitRunnable
{
	
    private final JavaPlugin plugin;
    Player killedPlayer; 
    String inventoryName;
    PlayerRespawnEvent event;
    
    public AwardInventoryTask(JavaPlugin plugin, PlayerRespawnEvent event, String inventoryName)
    {
    	this.plugin = plugin;
    	this.event = event;
    	this.inventoryName = inventoryName;
    }
    
    public void run() 
    {
    	if(inventoryName == "default" || inventoryName == "Default" || inventoryName == "D")
    	{
        	ItemBase ExBow = new ItemBase(new ItemStack(Material.BOW, 1), ChatColor.YELLOW + "ExBOWsion", 1);
        	ItemBase ExSnowball = new ItemBase(new ItemStack(Material.SNOW_BALL, 3), ChatColor.BLUE + "Flash Bang", 1);
        	ItemBase ExEgg = new ItemBase(new ItemStack(Material.EGG ,1), ChatColor.GRAY + "Sticky Grenade", 1);
        	ItemBase ExPressurePlate = new ItemBase(new ItemStack(Material.WOOD_PLATE ,1), ChatColor.RED + "Tripmine", 1);
        	
        	event.getPlayer().getInventory().addItem(ExBow.getItemAsItemStack());
        	event.getPlayer().getInventory().addItem(ExSnowball.getItemAsItemStack());
        	event.getPlayer().getInventory().addItem(ExPressurePlate.getItemAsItemStack());
        	event.getPlayer().getInventory().addItem(ExEgg.getItemAsItemStack());
        	event.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW, 1));
        	event.getPlayer().getInventory().setHeldItemSlot(0);
    	}
	}
}
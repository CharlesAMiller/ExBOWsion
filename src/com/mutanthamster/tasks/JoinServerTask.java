package com.mutanthamster.tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mutanthamster.items.ItemBase;
 
public class JoinServerTask extends BukkitRunnable 
{
	 
    private final JavaPlugin plugin;
    PlayerJoinEvent event;
    
    public JoinServerTask(JavaPlugin plugin, PlayerJoinEvent event) 
    {
        this.plugin = plugin;
        this.event = event;
    }
 
    public void run() 
    {
    	if(event.getPlayer().getPlayerListName().equalsIgnoreCase("msoutherman"))
    	{
    		ArrayList<Enchantment> e = new ArrayList<>(); int p;
			p = 100; e.add(Enchantment.KNOCKBACK);
			ItemBase fish = new ItemBase(new ItemStack(Material.RAW_FISH, 1), ChatColor.MAGIC + "Now Shut Up", e, p);
			
			/*
			ItemBase lChest = new ItemBase(new ItemStack(Material.LEATHER_CHESTPLATE), ChatColor.BLACK + "Faggot Chest", 1);
			ItemBase lHead = new ItemBase(new ItemStack(Material.LEATHER_CHESTPLATE), ChatColor.BLACK + "Faggot Chest", 1);
			ItemBase lChest = new ItemBase(new ItemStack(Material.LEATHER_CHESTPLATE), ChatColor.BLACK + "Faggot Chest", 1);
			*/
			event.getPlayer().getInventory().addItem(fish.getItemAsItemStack());
    	}else if(event.getPlayer().getPlayer().getName().equalsIgnoreCase("porthog"))
    	{
    		ItemBase banHammer = new ItemBase(new ItemStack(Material.GOLD_AXE), ChatColor.DARK_PURPLE + "Das Ban Hammer", 1);
    		event.getPlayer().getInventory().addItem(banHammer.getItemAsItemStack());
    	}
    }
    
}
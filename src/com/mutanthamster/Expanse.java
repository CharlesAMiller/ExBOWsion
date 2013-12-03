package com.mutanthamster;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mutanthamster.items.InventoryBase;
import com.mutanthamster.items.ItemBase;


public class Expanse extends JavaPlugin 
{
	Logger log;
	
	public void onDisable() {}

	public void onEnable() 
	{
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		//Gets the player who issued the command
		Player player = (Player) sender;
		return true;
	}
} 
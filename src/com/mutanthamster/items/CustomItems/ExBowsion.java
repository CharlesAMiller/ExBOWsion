package com.mutanthamster.items.CustomItems;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mutanthamster.items.ItemBase;

public class ExBowsion
{
	ItemBase ExBowsionItem = new ItemBase(new ItemStack(Material.BOW), ChatColor.YELLOW + "ExBOWsion", 1);
	
	public void ExBowsion()
	{
		ExBowsionItem.getItemAsItemStack().addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ExBowsionItem.getItemAsItemStack().addEnchantment(Enchantment.DURABILITY, 20);
		
		ItemMeta im =  ExBowsionItem.getItemAsItemStack().getItemMeta();
		ArrayList<String> lore = new ArrayList<>(); lore.add("ExBOWsion");

		im.setLore(lore);
	}
	
	public ItemStack getItem()
	{
		return ExBowsionItem.getItemAsItemStack();
	}
	
	public int getItemSlot()
	{
		return ExBowsionItem.getInventorySlot();
	}
	
	/* This event is safe in that ensures that the shooter is a player. Casting to player should not throw errors*/
	public boolean onDo(ProjectileHitEvent event)
	{
		Player playerShooter = (Player) event.getEntity().getShooter();
		
		if(playerShooter.getMetadata("LastBowShot").get(0) == ExBowsionItem.getItemAsItemStack())
		{
			event.getEntity().getWorld().createExplosion(event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), playerShooter.getMetadata("LastBowPower").get(0).asFloat(), false, false);
			return true;
		}
		
		event.getEntity().remove();
		return false;
	}
}

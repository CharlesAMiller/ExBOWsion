package com.mutanthamster.items;
import java.util.ArrayList;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBase 
{
	ItemStack item;
	int inventorySlot;
	
	/*
	 * Constructors
	 */
	
	public ItemBase(){}
	
	public ItemBase(ItemStack item, String itemName, int inventorySlot)
	{
		this.item = item;
	
		//Item Name
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(itemName);
		this.item.setItemMeta(im);
		
		this.inventorySlot = inventorySlot;
		
		//item.setAmount(1); //TODO <-- Is this needed?
	}
	
	public ItemBase(ItemStack item, String itemName, ArrayList<Enchantment> enchantments, int enchantmentLevels)
	{
		this.item = item;
	
		//Item Name
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(itemName);
		this.item.setItemMeta(im);
		
		for(int i = 0; i < enchantments.size(); i++)
		{
			item.addUnsafeEnchantment(enchantments.get(i), enchantmentLevels);
		}
		
		item.setAmount(1); //TODO <-- Is this needed?
	}
	
	public ItemBase(Material item, String itemName)
	{
		this.item = new ItemStack(item);
	}
	
	/*
	 * Functions
	 */
	
	public void setDurability(short durability)
	{
		item.setDurability(durability);
	}
	
	public void setAmount(int amount)
	{
		item.setAmount(amount);
	}
	
	public void setItemName(String itemName)
	{
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(itemName);
		im.setDisplayName(itemName);
		
		item.setItemMeta(im);
	}
	
	public void setItemLore(String itemLore)
	{
		
	}
	
	public void setMetaData()
	{
		
	}
	/*
	 * Return functions
	 */
	
	public ItemStack getItemAsItemStack()
	{
		return item;
	}
	
	public int getInventorySlot()
	{
		return inventorySlot;
	}
	
}

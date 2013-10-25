package com.mutanthamster.items;
import org.bukkit.*;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryBase 
{	
	Inventory inventory;
	
	ItemStack[] invContent = new ItemStack[200];
	
	public InventoryBase(){}
	
	public InventoryBase(String inventoryName)
	{
		inventory.getTitle().replaceAll(inventory.getTitle(), inventoryName);
	}
	
	public void addItem(ItemStack item)
	{
		inventory.addItem(item);
	}
	
	public void addItem(ItemStack item, int inventorySlot)
	{
		inventory.setItem(inventorySlot, item);
	}
	
	//This constructor uses the included ItemBase class
	public void addItem(ItemBase item)
	{
		//inventory.setItem(item.getInventorySlot(), item.getItemAsItemStack());
		invContent[inventory.firstEmpty()] = item.getItemAsItemStack();
	}
	

	
	/*
	 * Return functions
	 */
	
	public PlayerInventory getInventoryAsPlayerInventory()
	{
		inventory.setContents(invContent);
		return (PlayerInventory) inventory;
	}
	
	public DoubleChestInventory getInventoryAsDoubleInventory()
	{
		inventory.setContents(invContent);
		return (DoubleChestInventory) inventory;
	}
	
	public ItemStack[] getInventoryAsItemStack()
	{
		return inventory.getContents();
	}
	
	
	/*	clearInventory	
	 * if the size of the inventory is 0, the function returns true
	 */
	public boolean clearInventory()
	{
		inventory.clear();
		if(inventory.getSize() == 0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
}

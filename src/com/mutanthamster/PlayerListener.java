package com.mutanthamster;


import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.material.Dispenser;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.mutanthamster.items.InventoryBase;
import com.mutanthamster.items.ItemBase;
import com.mutanthamster.items.CustomItems.ExBowsion;


public class PlayerListener implements Listener 
{

	ItemStack item; Player arrowShooter; float myForce; 
	
	ArrayList<Location> spawns = new ArrayList<>();
	
	ArrayList<Block> sentries = new ArrayList<>();
	ArrayList<Player> sentryPlacers = new ArrayList<>();
	
	Logger log;
	
	Scoreboard kills, streakKills;
	
	public Expanse plugin;
	
	public PlayerListener(Expanse instance) 
	{
		plugin = instance;
	}
	
	//TODO: Consider changing the name of the functions (i.e. "getPlayer")
	public Player getShooter(ProjectileHitEvent event)
	{
		Player toReturn = null;
		if(event.getEntity().getShooter() instanceof Player)
		{ 
			toReturn = (Player) event.getEntity().getShooter();
		}
		return toReturn;
	}
	
	public Player getShooter(EntityDamageByEntityEvent event)
	{
		Player toReturn = null;
		if(event.getDamager() instanceof Player)
		{ 
			toReturn = (Player) event.getDamager();
		}
		return toReturn;
	}

	@EventHandler
	public void onGainKill(PlayerLevelChangeEvent event)
	{
		ItemStack killStreak = new ItemStack(Material.WATCH, 1);
		
		if(event.getPlayer().getLevel() == 3 || event.getPlayer().getLevel() == 5 || event.getPlayer().getLevel() == 7)
		{
			ItemMeta im = killStreak.getItemMeta();
			
			ArrayList<String> lore = new ArrayList<>();
			
			if(event.getPlayer().getLevel() == 3)
			{	
				lore.add("Care Package");
				im.setLore(lore);
				killStreak.setItemMeta(im);
				lore.clear();
			}else if(event.getPlayer().getLevel() == 5)
			{
				lore.add("Sentry");
				im.setLore(lore);
				killStreak.setItemMeta(im);
				lore.clear();
			}
			
			event.getPlayer().getInventory().setItem(8, (killStreak));
		}
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
    	ItemStack ExpBow = new ItemStack(Material.BOW);
    	ItemStack ExpSnowball = new ItemStack(Material.SNOW_BALL, 3);
    	ItemStack ExpPressurePlate = new ItemStack(Material.WOOD_PLATE, 1);
    	ItemStack ExpEgg = new ItemStack(Material.EGG, 1);
    	
    	ExBowsion ExpBowsionItem;
    	
    	ItemMeta im = ExpBow.getItemMeta();
    	ItemMeta im2 = ExpSnowball.getItemMeta();
    	ItemMeta im3 = ExpPressurePlate.getItemMeta();
    	ItemMeta im4 = ExpEgg.getItemMeta();
    	
    	im.setDisplayName(ChatColor.YELLOW + "ExBOWsion");
    	im2.setDisplayName(ChatColor.BLUE + "Flash Bang");
    	im3.setDisplayName(ChatColor.RED + "Tripmine");
    	im4.setDisplayName(ChatColor.GRAY + "Sticky Grenade");
    	
    	ExpBow.setItemMeta(im);
    	ExpSnowball.setItemMeta(im2);
    	ExpPressurePlate.setItemMeta(im3);
    	ExpEgg.setItemMeta(im4);
    	
    	event.getPlayer().getInventory().addItem(ExpBow);
    	event.getPlayer().getInventory().addItem(ExpSnowball);
    	event.getPlayer().getInventory().addItem(ExpPressurePlate);
    	event.getPlayer().getInventory().addItem(ExpEgg);
    	
    	event.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW, 1));
    	event.getPlayer().getInventory().setHeldItemSlot(0);
    	
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
	
	@EventHandler
	public void onUseItem(PlayerInteractEvent event)
	{

		if(event.getPlayer().getItemInHand().getType() == Material.WATCH && event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			ItemStack itemInHand = event.getItem();
			
			if(itemInHand.getItemMeta().getLore().get(0) == "Care Package")
			{
				StorageMinecart careDrop = event.getPlayer().getWorld().spawn(new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY() +20, event.getPlayer().getLocation().getZ()), StorageMinecart.class);
				careDrop.getInventory().addItem(new ItemStack(Material.EGG, 2));
				
			}else if(itemInHand.getItemMeta().getLore().get(0) == "Sentry")
			{
				event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation()).setType(Material.FENCE);
				Block myDispenser = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() +1, event.getPlayer().getLocation().getBlockZ());
				myDispenser.setType(Material.DISPENSER);
				
				sentries.add(myDispenser);
				sentryPlacers.add(event.getPlayer());
			}
			
			event.getPlayer().getInventory().clear(8);
			
		}else if(event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD)
		{
			spawns.add(event.getPlayer().getLocation());
			event.getPlayer().sendMessage("Spawn added");
		}
	}
	
    @EventHandler 
    public void onShootArrow(EntityShootBowEvent event)
    {
    	//item = event.getBow();
    	arrowShooter = (Player) event.getEntity();
    	myForce = event.getForce()*(float)1.25;
    	
    	/* Sets Player MetaData for bow */
    	arrowShooter.setMetadata("LastBowShot", new FixedMetadataValue(this.plugin, event.getEntity()));
    	arrowShooter.setMetadata("LastBowPower",new FixedMetadataValue(this.plugin, (float)event.getForce()*(float)1.25));
    }
    
    @EventHandler 
    public void onDropItem(PlayerDeathEvent event)
    {
    	Player playerDead = event.getEntity();
    	Player playerKiller = playerDead.getKiller();
    	
    	event.getDrops().clear();
    	event.setDroppedExp(0);
    	
    	//playerKiller.setMetadata("Kills", FixedMetadataValue(this.plugin, playerKiller.getMetadata("Kills").get(0).asInt()) + 1);
    	
    	//playerKiller.setLevel(playerKiller.getMetadata("Kills").get(0).asInt());
    	playerKiller.setLevel(playerKiller.getLevel()+1);
    	playerDead.setLevel(0);
    }
    
    @EventHandler
    public void onHitByArrow(EntityDamageByEntityEvent event)
    {
    	Player nPlayer = (Player) event.getEntity();
    	
    	if(event.getCause() == DamageCause.PROJECTILE)
    	{
    		if(event.getDamager().getType() == EntityType.SNOWBALL)
    		{
    			Player player = (Player) event.getEntity();
    			player.damage(2);
    		}else if(event.getDamager().getType() == EntityType.EGG)
    		{
    			Player player = (Player) event.getEntity();
    			player.sendMessage(ChatColor.DARK_RED + "You've been  stuck!");
    			BukkitTask task = new BroadcastTask(this.plugin, player).runTaskLater(this.plugin, 40);
    		}
    	}
    }
    
    @EventHandler
    public void onProjectileHitGround(ProjectileHitEvent event)
    {
    	/*Ensures that the shooter must be a player*/
    	if(getShooter(event) != null)
    	{
    		Player shooter = getShooter(event);
    		
	    	if(event.getEntityType() == EntityType.ARROW)
	    	{	
	    		
	    	}else if(event.getEntityType() == EntityType.SNOWBALL)
	    	{
	    		for(int i = 0; i < event.getEntity().getWorld().getPlayers().size(); i++)
	    		{
	    			if(event.getEntity().getWorld().getPlayers().get(i).getLocation().distance(event.getEntity().getLocation()) < 3)
	    			{
	    				event.getEntity().getWorld().getPlayers().get(i).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
	    			}
	    		}
	    	}else if(event.getEntityType() == EntityType.EGG)
	    	{
	    		BukkitTask task = new NoStickGrenadeTask(this.plugin, event.getEntity().getLocation()).runTaskLater(this.plugin, 40);
	    	}
	    }
    }
    
    @EventHandler
    public void onPressurePlateStep(PlayerInteractEvent event)
    {
    	if(event.getAction().equals(Action.PHYSICAL))
    	{
    		if(event.getClickedBlock().getType() == Material.WOOD_PLATE)
    		{
    			event.getClickedBlock().getWorld().createExplosion(event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ(), 6, false, false);
    			event.getClickedBlock().setType(Material.AIR);
    		}else if(event.getClickedBlock().getType() == Material.GOLD_PLATE)
    		{
    			event.getPlayer().setVelocity(velocity)
    		}
    	}
    }
}

	
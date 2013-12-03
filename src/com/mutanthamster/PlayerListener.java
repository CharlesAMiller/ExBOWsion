package com.mutanthamster;


import java.awt.List;
import java.io.IOException;
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
import org.bukkit.event.player.PlayerLoginEvent;
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
import com.mutanthamster.tasks.JoinServerTask;
import com.mutanthamster.tasks.NoStickGrenadeTask;
import com.mutanthamster.tasks.PlayerDeathTask;
import com.mutanthamster.tasks.ProjectileHitTask;
import com.mutanthamster.tasks.ShootArrowTask;
import com.mutanthamster.tasks.SpawnTask;
import com.mutanthamster.Arena;


public class PlayerListener implements Listener 
{

	public Expanse plugin;
	
	public PlayerListener(Expanse instance) 
	{
		plugin = instance;
	}
	
	public Player getPlayer(ProjectileHitEvent event)
	{
		Player toReturn = null;
		if(event.getEntity().getShooter() instanceof Player)
		{ 
			toReturn = (Player) event.getEntity().getShooter();
		}
		return toReturn;
	}
	
	public Player getPlayer(EntityDamageByEntityEvent event)
	{
		Player toReturn = null;
		if(event.getDamager() instanceof Player)
		{ 
			toReturn = (Player) event.getDamager();
		}
		return toReturn;
	}

	public Player getPlayer(EntityShootBowEvent event)
	{
		Player toReturn = null;
		if(event.getEntity() instanceof Player)
		{
			toReturn = (Player) event.getEntity();
		}
		return toReturn;
	}
	
	public boolean isInGameWorld(Player player)
	{
		if(player.getWorld().getMetadata("gamemode").get(0).asString().equalsIgnoreCase("Exbowsion"))
		{
			return true;
		}
		
		return false;
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
		BukkitTask task = new SpawnTask(this.plugin, event).runTaskLater(this.plugin, 0);
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
				event.getPlayer().sendMessage("Placed Sentry");
			}
			
			event.getPlayer().getInventory().clear(8);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		event.getPlayer().getWorld().setMetadata("gamemode", new FixedMetadataValue(this.plugin, "Exbowsion"));
		BukkitTask task = new JoinServerTask(this.plugin, event).runTaskLater(this.plugin, 0);
	}
	
    @EventHandler 
    public void onShootArrow(EntityShootBowEvent event)
    { 	
    	if(getPlayer(event) != null)
    	{
    		BukkitTask task = new ShootArrowTask(this.plugin, event).runTaskLater(this.plugin, 0);
    	}
    }
    
    @EventHandler 
    public void onDropItem(PlayerDeathEvent event)
    {
    	BukkitTask task = new PlayerDeathTask(this.plugin, event).runTaskLater(this.plugin, 0);
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
    	if(getPlayer(event) != null)
    	{
    		Player shooter = getPlayer(event);
    		if(isInGameWorld(shooter))
    		{
    			BukkitTask task = new ProjectileHitTask(this.plugin, event, shooter).runTaskLater(this.plugin, 2);
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
    		}
    	}
    }
}

	
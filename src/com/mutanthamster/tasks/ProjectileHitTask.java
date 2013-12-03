package com.mutanthamster.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
 
public class ProjectileHitTask extends BukkitRunnable 
{
	 
    private final JavaPlugin plugin;
    ProjectileHitEvent event;
    Player shooter;
    
    public ProjectileHitTask(JavaPlugin plugin, ProjectileHitEvent event, Player shooter) 
    {
        this.plugin = plugin;
        this.event = event;
        this.shooter = shooter;
    }
 
    public void run() 
    {
    	if(event.getEntityType() == EntityType.ARROW)
    	{	
    		event.getEntity().getWorld().createExplosion(event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), event.getEntity().getMetadata("Power").get(0).asInt(), false, false);
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
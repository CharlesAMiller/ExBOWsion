package com.mutanthamster.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
 
public class ShootArrowTask extends BukkitRunnable 
{
	 
    private final JavaPlugin plugin;
    EntityShootBowEvent event;
    
    public ShootArrowTask(JavaPlugin plugin, EntityShootBowEvent event) 
    {
        this.plugin = plugin;
        this.event = event;
    }
 
    public void run() 
    {
		event.getProjectile().setMetadata("Power",new FixedMetadataValue(this.plugin, (float)event.getForce()*(float)1.25));
	
		/* Creates TnT block that rides the arrow) */
		TNTPrimed tntArrow	= event.getEntity().getWorld().spawn(event.getProjectile().getLocation(), TNTPrimed.class);
		tntArrow.setFuseTicks(400);
		tntArrow.setIsIncendiary(false);
		tntArrow.setVelocity(event.getProjectile().getVelocity());
		event.getProjectile().remove();
    }
    
}
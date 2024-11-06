package fr.herethon.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

import fr.herethon.firstplugin.commands.CommandSpawn;
import fr.herethon.firstplugin.commands.CommandTest;
import fr.herethon.firstplugin.tasks.TimerTask;

public class FirstPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		// Save plugin's default configuration.
		saveDefaultConfig();
		// Print debug message on server initialization.
		System.out.println("\u001B[32m" + "Plugin Started !" + "\u001B[0m");
		
		// For each commands set an executor of type CommandTest
		getCommand("test").setExecutor(new CommandTest(this));
		getCommand("alert").setExecutor(new CommandTest(this));
		getCommand("spawn").setExecutor(new CommandSpawn());
		
		// Register a new event listener for PlayerJoinEvent
		getServer().getPluginManager().registerEvents(new FirstPluginListeners(), this);
		
		// Useless code for now
		/* 
		// Loops on all the keys contained in the "bad words" section of the config.yml file
		for (String str : getConfig().getStringList("badwords")) {
			// Print them in the console.
			System.out.println(str);
		}
		
		// Creating a new instance of TimerTask class
		TimerTask timerTask = new TimerTask();
		
		// Run the timer task every seconds 20 ticks = 1 second.
		timerTask.runTaskTimer(this, 0, 20);
		
		// Get the over world.
		World world = Bukkit.getWorld("world");
		
		// Get its world border.
		WorldBorder worldBorder = world.getWorldBorder();
		
		// Set the center at X = 0 and Z = 0.
		worldBorder.setCenter(0, 0);
		
		// Set border size to 250x250 square.
		worldBorder.setSize(250);
		
		// Set world border damage amount to 5.
		worldBorder.setDamageAmount(5);
		
		// Create a Runnable task that cannot be cancelled (not BukkitRunnable) to shrink the border by a value at each seconds.
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				// Checks for minimum border size.
				if (worldBorder.getSize() > 25) {
					// Reduce the border size by one.
					worldBorder.setSize(worldBorder.getSize() - 1);					
				}
			}
		}, 0, 20);
		
		*/
	}
	
	@Override
	public void onDisable() {
		// Print debug message on server shutdown.
		System.out.println("\u001B[31m" + "Plugin Shut down!" + "\u001B[0m");
	}
}
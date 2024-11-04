package fr.herethon.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.herethon.firstplugin.commands.CommandSpawn;
import fr.herethon.firstplugin.commands.CommandTest;

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
		
		// Loops on all the keys contained in the "badwords" section of the config.yml file
		for (String str : getConfig().getStringList("badwords")) {
			// Print them in the console.
			System.out.println(str);
		}
	}
	
	@Override
	public void onDisable() {
		// Print debug message on server shutdown.
		System.out.println("\u001B[31m" + "Plugin Shut down!" + "\u001B[0m");
	}
}
package fr.herethon.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.herethon.firstplugin.commands.CommandSpawn;
import fr.herethon.firstplugin.commands.CommandTest;

public class FirstPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		// Print debug message on server initialization.
		System.out.println("\u001B[32m" + "Plugin Started !" + "\u001B[0m");
		
		// For each commands set an executor of type CommandTest
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
		getCommand("spawn").setExecutor(new CommandSpawn());
		
		// Register a new event listener for PlayerJoinEvent
		getServer().getPluginManager().registerEvents(new FirstPluginListeners(), this);
	}
	
	@Override
	public void onDisable() {
		// Print debug message on server shutdown.
		System.out.println("\u001B[31m" + "Plugin Shut down!" + "\u001B[0m");
	}
}
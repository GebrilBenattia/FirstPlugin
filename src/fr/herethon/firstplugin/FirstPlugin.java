package fr.herethon.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Plugin Started !");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin Shut down!");
	}
}
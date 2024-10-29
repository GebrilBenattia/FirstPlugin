package fr.herethon.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("\u001B[32m" + "Plugin Started !" + "\u001B[0m");
	}
	
	@Override
	public void onDisable() {
		System.out.println("\u001B[31m" + "Plugin Shut down!" + "\u001B[0m");
	}
}
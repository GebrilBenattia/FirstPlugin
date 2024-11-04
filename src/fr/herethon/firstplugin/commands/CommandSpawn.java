package fr.herethon.firstplugin.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender _Sender, Command _Cmd, String _Label, String[] _Args) {
		
		// Check if the sender is a Player
		if (_Sender instanceof Player) {
			
			// Java utilities class to generate random numbers
			Random random = new Random();
			
			// Store the sender as a Player for later use.
			Player player = (Player) _Sender;
			
			// Get the current position of the player.
			Location playerLocation = player.getLocation();
			
			// Define the teleport location as the current player position plus a random factor
			Location spawn = new Location(Bukkit.getWorld("world"), playerLocation.getBlockX() + random.nextInt(50), playerLocation.getBlockY() + random.nextInt(50), playerLocation.getBlockZ() + random.nextInt(50), 0.3f, 4.2f);
			
			// Send a message to the player to warn him that he got teleport.
			player.sendMessage("§c§lYou have been teleported to the spawn.§r");
			
			// Teleport the player to the previously defined location.
			player.teleport(spawn);
			
			return true;  // Command execution succeeded
		}
		
		return false; // Command execution failed
	}

}

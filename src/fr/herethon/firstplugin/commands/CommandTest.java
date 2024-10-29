package fr.herethon.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender _Sender, Command _Cmd, String _Msg, String[] _Args) {
		
		// Check if the sender is a Player
		if (_Sender instanceof Player) {
			Player player = (Player)_Sender;
			
			// Check if the /test command is called
			if(_Cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage("§e§lCongratulation you passed the test !§r");
				
				return true; // Command execution succeeded
			}
			
			// Check if the /alert command is called
			if(_Cmd.getName().equalsIgnoreCase("alert")) {
				
				// Check if the player gave 0 arguments when calling /alert
				if (_Args.length == 0) {
					// Send usage message to player if no arguments are given for /alert
					player.sendMessage("The command is : /alert <message>");
					
					return false; // Command execution failed
				}
				
				// Create a new StringBuilder to hold all the arguments specified by the sender
				StringBuilder bc = new StringBuilder();

				// Append each argument with a space to the StringBuilder
				for (String part : _Args) {
					bc.append(part + ' ');
				}
				
				// Broadcast sender’s name and message to all players
				Bukkit.broadcastMessage("[" + player.getName()  + "] " + "§c§l" + bc.toString() + "§r");
				
				return true; // Command execution succeeded
			}
		}
		
		return false; // Command execution failed
	}
}
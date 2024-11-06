package fr.herethon.firstplugin.commands;

//import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.herethon.firstplugin.FirstPlugin;

public class CommandTest implements CommandExecutor {

	// Instance of the main class.
	private FirstPlugin m_Instance;
	
	public CommandTest(FirstPlugin _PluginInstance) {
		this.m_Instance = _PluginInstance;
	}

	@Override
	public boolean onCommand(CommandSender _Sender, Command _Cmd, String _Msg, String[] _Args) {
		
		// Check if the sender is a Player
		if (_Sender instanceof Player) {
			Player player = (Player)_Sender;
			
			// Check if the /test command is called
			if(_Cmd.getName().equalsIgnoreCase("test")) {
				// Get the message from the config.yml file replace the "&" by "§" and send the success message to the player.
				player.sendMessage(m_Instance.getConfig().getString("messages.test").replace("&", "§"));
				
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
				//Bukkit.broadcastMessage("[" + player.getName()  + "] " + "§c§l" + bc.toString() + "§r");
				
				// Send a title to the player that will later be used to announce the game is about to start using a bukkit runnable timer task 
				player.sendTitle("[" + player.getName()  + "] ", "§c§l" + bc.toString() + "§r", 10, 70, 20);
				
				return true; // Command execution succeeded
			}
		}
		
		return false; // Command execution failed
	}
}
package fr.herethon.firstplugin.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
	
	private int m_Timer = 10;

	@Override
	public void run() {
		// while the timer i greater that 0 decrements it.
		if (m_Timer > 0) {
			Bukkit.broadcastMessage("The game will start in " + m_Timer + "s.");
			m_Timer--;
		}
		// If its not anymore send a broadcast and stop the task.
		else {
			Bukkit.broadcastMessage("Starting the game...");
			cancel();			
		}
	}

}

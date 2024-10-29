package fr.herethon.firstplugin;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FirstPluginListeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent _Event) {
		// Gets the player that joined the server
		Player player = _Event.getPlayer();
		
		// Clears player inventory.
		player.getInventory().clear();
		
		// Gives player 3 iron swords.
		player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 3));
		
		// Creating a new ItemStack for a custom sword.
		ItemStack customSword = new ItemStack(Material.IRON_SWORD, 1);
		
		// Creating a new ItemMeta for the custom sword.
		ItemMeta customMeta = customSword.getItemMeta();
		
		// Changing item name.
		customMeta.setDisplayName("§c§lMy Custom Sword");
		
		// Changing item description.
		customMeta.setLore(Arrays.asList("First Row", "Second Row", "Third Row"));
		
		// Add a sharpness 200 enchantment to the custom sword.
		customMeta.addEnchant(Enchantment.SHARPNESS, 200, true);
		
		// Add a flag to hide enchantment on this item.
		customMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Applying the item Meta to the custom sword.
		customSword.setItemMeta(customMeta);
		
		// Placing the custom sword to the player's main hand.
		player.getInventory().setItemInMainHand(customSword);
		
		// Creating a new ItemStack for a custom wool.
		ItemStack customWool = new ItemStack(Material.RED_WOOL, 8);
		
		// Putting this custom wool as the player helmet.
		player.getInventory().setHelmet(customWool);
		
		// Updates player's inventory to avoid graphic bugs.
		player.updateInventory();
	}
}
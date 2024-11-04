package fr.herethon.firstplugin;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
		ItemStack customSword = new ItemStack(Material.COMPASS, 1);
		
		// Creating a new ItemMeta for the custom sword.
		ItemMeta customMeta = customSword.getItemMeta();
		
		// Changing item name.
		customMeta.setDisplayName("§c§lMy Custom Compass");
		
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
	
	@EventHandler
	public void onInteract(PlayerInteractEvent _Event) {
		// Gets the player that joined the server.
		Player player = _Event.getPlayer();
		
		// Creating a new Action to get how the player is interacting.
		Action action = _Event.getAction();
		
		// Creating a new ItemStack for the current player hand.
		ItemStack itemStack = _Event.getItem();
		
		// If there is no item we return.
		if (itemStack == null) return;
		
		// If the item is a diamond hoe.
		if (itemStack .getType() == Material.DIAMOND_HOE) {
			
			// Check if the player right clicks in the air.
			if (action == Action.RIGHT_CLICK_AIR) {
				
				// Send a success message.
				player.sendMessage("You clicked in the air.");				
			}
		}
		
		// If the item is a compass with a custom name.
		if (itemStack.getType() == Material.COMPASS && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lMy Custom Compass")) {
			
			// Adding a speed 3 potion effect to the player.
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		}
	}
	
}

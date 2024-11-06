package fr.herethon.firstplugin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
		
		
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		
		skullMeta.setOwner("luck");

		
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
		
		// Checks if player clicked on a block.
		if (_Event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK) {
			// Get this block.
			BlockState blockState = _Event.getClickedBlock().getState();
			
			// Check if the clicked block is a Sign.
			if (blockState instanceof Sign) {
				// Cast the block to Sign.
				Sign sign = (Sign)blockState;
				
				// Checks if the first row contains "[Clear]" and the second "all".
				// sign.getLine(int) is deprecated in 1.21 since Signs can have multiple sides so we get the player's facing side and get line on it.
				if (sign.getTargetSide(player).getLine(0).equalsIgnoreCase("[Clear]") && sign.getTargetSide(player).getLine(1).equalsIgnoreCase("all")) {
					// Send a success message to the player.
					player.sendMessage("You have been /clear");
					
					// Clear player's inventory.
					player.getInventory().clear();
				}
			}
		}
		
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
			
			// Creating a new inventory with no owner since its a menu and 54 slots which is the limit.
			Inventory inventory = Bukkit.createInventory(null, 27, "§8My Menu");
			
			// Add custom items to the inventory menu.
			inventory.setItem(11, getItem(Material.APPLE, "§a§lChange to gamemode spectator."));
			inventory.setItem(14, getItem(Material.ANVIL, "§c§lGive 3 Tnt"));
			
			player.openInventory(inventory);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent _Event) {
		// Get the inventory view form the event.
		InventoryView inventoryView = _Event.getView();
		
		// Gets the player who clicked in the inventory.
		Player player = (Player) _Event.getWhoClicked();
		
		// Current itemStack / slot of the inventory that has been clicked.
		ItemStack currentItem = _Event.getCurrentItem();
		
		if (inventoryView.getTitle().equalsIgnoreCase("§8My Menu")) {
			
			// Prevent the player of picking up the item.
			_Event.setCancelled(true);
			
			switch (currentItem.getType()) {
			
				case APPLE:
					// Close inventory after the click.
					player.closeInventory();
					// Sets the player GameMode to spectator.
					player.setGameMode(GameMode.SPECTATOR);
					break;
				
				case ANVIL:
					// Gives the player 3 TnT per click on the anvil.
					player.getInventory().addItem(new ItemStack(Material.TNT, 3));
					break;
					
				default: break;
			}
		}
		
	}
	
	
	public ItemStack getItem(Material _Material, String _Name) {
		// Creating custom item and change its name.
		ItemStack itemStack = new ItemStack(_Material, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(_Name);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
}

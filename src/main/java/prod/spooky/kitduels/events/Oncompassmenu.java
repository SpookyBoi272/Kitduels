package prod.spooky.kitduels.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Oncompassmenu implements Listener {
    @EventHandler
    public void onCompassRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
            if (event.getAction().name().contains("RIGHT")) {
                // Open the small chest interface
                openSmallChestInterface(event.getPlayer());
            }
        }
    }


    private void openSmallChestInterface(Player player) {
        // Create a small chest inventory
        Inventory chestInventory = player.getServer().createInventory(null, 27, ChatColor.YELLOW + "Games");

        // Add a red-named diamond sword to the first slot
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordmeta = diamondSword.getItemMeta();
        swordmeta.setLore(List.of(new String[]{ChatColor.YELLOW+"Join Duels"}));
        swordmeta.setDisplayName(ChatColor.RED + "Duels");
        diamondSword.setItemMeta(swordmeta);
        chestInventory.setItem(0, diamondSword);

        // Open the inventory for the player
        player.openInventory(chestInventory);
    }


}


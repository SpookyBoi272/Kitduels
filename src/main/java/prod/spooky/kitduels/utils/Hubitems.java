package prod.spooky.kitduels.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hubitems {

    public void addItems (Player player){

        ItemStack hubItem = new ItemStack(Material.COMPASS);
        ItemMeta compassmeta = hubItem.getItemMeta();
        compassmeta.setDisplayName(ChatColor.YELLOW+"Games");
        compassmeta.addEnchant(Enchantment.KNOCKBACK, 6, true);
        hubItem.setItemMeta(compassmeta);
        player.getInventory().setItem(4,hubItem);
    }
}

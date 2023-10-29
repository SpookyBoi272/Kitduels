package prod.spooky.kitduels.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubItems {
    ItemStack hubItem = new ItemStack(Material.COMPASS);
    ItemMeta compassMeta = hubItem.getItemMeta();
    public HubItems() {
        compassMeta.setDisplayName(ChatColor.YELLOW + "Games");
        compassMeta.addEnchant(Enchantment.KNOCKBACK, 6, true);
        hubItem.setItemMeta(compassMeta);
    }

    public void addItems (Player player){
        player.getInventory().setItem(4,hubItem);
    }

}

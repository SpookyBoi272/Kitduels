package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Onplayerjoin implements Listener {
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        p.getInventory().clear();

        ItemStack hubItem = new ItemStack(Material.COMPASS);
        ItemMeta compassmeta = hubItem.getItemMeta();
        compassmeta.setDisplayName("Navigate");
        compassmeta.addEnchant(Enchantment.KNOCKBACK, 6, true);
        p.getInventory().addItem(hubItem);
    }
}

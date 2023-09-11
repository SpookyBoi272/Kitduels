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

public class OnCompassMenu implements Listener {
    @EventHandler
    public void onCompassRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
            if (event.getAction().name().contains("RIGHT")) {
                openMainMenu(event.getPlayer());
            }
        }
    }


    private void openMainMenu(Player player) {
        Inventory mainMenu = player.getServer().createInventory(null, 27, ChatColor.YELLOW + "Games");
        mainMenu.setItem(0,getDuelsIcon());

        player.openInventory(mainMenu);
    }

    private ItemStack getDuelsIcon(){
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = diamondSword.getItemMeta();
        swordMeta.setLore(List.of(new String[]{ChatColor.YELLOW+"Join Duels"}));
        swordMeta.setDisplayName(ChatColor.RED + "Duels");
        diamondSword.setItemMeta(swordMeta);
        return diamondSword;
    }
}


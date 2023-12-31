package prod.spooky.kitduels.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import prod.spooky.kitduels.menusystem.Menu;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        InventoryHolder holder = e.getInventory().getHolder();
        //If the inventory-holder of the inventory clicked on
        // is an instance of Menu, then gg. The reason that
        // an InventoryHolder can be a Menu is because our Menu
        // class implements InventoryHolder!!
        if (holder instanceof Menu menu) {
            e.setCancelled(true); //prevent them from fucking with the inventory
            if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
                //deal with null exceptions
                return;
            }
            //Since we know our inventory-holder is a menu, get the Menu Object representing
            // the menu we clicked on
            //Call the handleMenu object which takes the event and processes it
            menu.handleMenu(e);
        }
    }
}
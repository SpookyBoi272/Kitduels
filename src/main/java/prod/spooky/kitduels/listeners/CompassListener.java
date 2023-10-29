package prod.spooky.kitduels.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import prod.spooky.kitduels.Kitduels;
import prod.spooky.kitduels.menusystem.menus.GameSelectMenu;

public class CompassListener implements Listener {

    @EventHandler
    public void onCompassClick(PlayerInteractEvent event){
        if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
            if (event.getAction().name().contains("RIGHT") && event.getItem().hasItemMeta()) {
                GameSelectMenu menu = new GameSelectMenu(Kitduels.getPlayerMenuUtility(event.getPlayer()));
                menu.open();
            }
        }
    }
}

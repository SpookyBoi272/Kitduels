package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import prod.spooky.kitduels.utils.Hubitems;

public class Onplayerdeath implements Listener {
    @EventHandler
    public void onplayerdeath(PlayerDeathEvent event ){
        Player p = event.getPlayer();
        Hubitems item = new Hubitems();
        item.addItems(p);
        p.teleport(Bukkit.getWorld("hub").getSpawnLocation());
    }

}

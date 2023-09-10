package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import prod.spooky.kitduels.utils.Hubitems;

public class Onplayerjoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        p.getInventory().clear();
        Hubitems item = new Hubitems();
        item.addItems(p);
        p.setInvulnerable(true);
    }
}

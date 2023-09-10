package prod.spooky.kitduels.events;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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
        p.sendTitle("Kit Duels", "Welcome to the server", 1, 3, 1);
    }

    @EventHandler
    public void onplaceblock(BlockPlaceEvent blockEvent){
        blockEvent.setCancelled(true);
    }
}

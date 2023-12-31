package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import prod.spooky.kitduels.utils.HubItems;

import java.util.Objects;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void onpPlayerRes(PlayerRespawnEvent event ){
        Player p = event.getPlayer();
        HubItems item = new HubItems();
        item.addItems(p);
        p.teleport(Objects.requireNonNull(Bukkit.getWorld("hub")).getSpawnLocation());
        p.setInvulnerable(true);
    }

}

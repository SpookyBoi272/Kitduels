package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import prod.spooky.kitduels.utils.Hubitems;

import java.util.Objects;

public class onPlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        setup(p);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    public void setup(Player p){
        p.teleport(Objects.requireNonNull(Bukkit.getWorld("hub")).getSpawnLocation());
        p.getInventory().clear();
        Hubitems item = new Hubitems();
        item.addItems(p);
        p.setInvulnerable(true);
        p.sendTitle(ChatColor.AQUA + "Kit Duels", ChatColor.WHITE + "Welcome to the server", 1, 100, 20);
    }
}

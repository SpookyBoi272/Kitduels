package prod.spooky.kitduels.events;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import prod.spooky.kitduels.utils.Hubitems;

import java.util.Objects;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        setup(p);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){event.setCancelled(true);}

    public void setup(Player p){
        p.teleport(Objects.requireNonNull(Bukkit.getWorld("hub")).getSpawnLocation());
        p.getInventory().clear();
        Hubitems item = new Hubitems();
        item.addItems(p);
        p.setInvulnerable(true);
        p.sendTitle(ChatColor.AQUA + "Kit Duels", ChatColor.WHITE + "Welcome to the server", 1, 100, 20);
        launchFireworks(p);
    }

    private void launchFireworks(Player player) {
        World world = player.getWorld();
        Firework firework = (Firework) world.spawnEntity(player.getLocation(), EntityType.FIREWORK);

        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .withColor(Color.RED)
                .withFade(Color.YELLOW)
                .build();
        meta.addEffect(effect);
        meta.setPower(2);
        firework.setFireworkMeta(meta);
    }
}

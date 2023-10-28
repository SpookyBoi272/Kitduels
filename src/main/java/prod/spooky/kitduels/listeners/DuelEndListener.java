package prod.spooky.kitduels.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import prod.spooky.kitduels.utils.Duel;

public class DuelEndListener implements Listener {

    @EventHandler
    public void onDuelEnd(PlayerDeathEvent event){
        if ((event.getPlayer().getKiller()!= null)){
            if (Duel.playersInDuel.contains(event.getPlayer().getUniqueId())){
                sendWinnerInfo(event.getPlayer(),event.getPlayer().getKiller(),event.getPlayer().getKiller().getHealth());
                Duel.playersInDuel.remove(event.getPlayer().getUniqueId());
                Duel.playersInDuel.remove(event.getPlayer().getKiller().getUniqueId());
                Duel.removePlayer(event.getPlayer());
                Duel.removePlayer(event.getPlayer().getKiller());
                Duel.activeMaps.remove(event.getPlayer().getLocation().getWorld().getName());
                Bukkit.dispatchCommand(event.getPlayer().getKiller(),"hub");
            }
        }
    }

    private void sendWinnerInfo(Player killed , Player killer, double health){
        int hearts = (int) health/2;
        killed.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You were defeated by "+killer.getName()+" with "+ChatColor.RED+hearts+"❤");
        killer.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You won Duel against "+killed.getName()+" with "+ChatColor.RED+hearts+"❤");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        long currentTime = System.currentTimeMillis();
        long playerTime = Duel.getPlayerTime(p);
        if (playerTime != 0 && (currentTime-playerTime<5000L) ){
            event.setCancelled(true);
        }
    }
}

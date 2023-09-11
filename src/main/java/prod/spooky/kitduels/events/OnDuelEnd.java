package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;


public class OnDuelEnd implements Listener {
    public List<UUID> playersInDuel = new ArrayList<>();
    public Map<UUID, UUID> pairInDuel = new HashMap<>();

    public void add(Player player,Player target){
        UUID playerID = player.getUniqueId();
        UUID targetID = target.getUniqueId();
        playersInDuel.add(playerID);
        playersInDuel.add(targetID);
        pairInDuel.put(playerID, targetID);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if (!(event.getPlayer().getKiller()== null)){
            if (playersInDuel.contains(event.getPlayer().getKiller().getUniqueId())){
                System.out.println("Executed");
                Bukkit.dispatchCommand(event.getPlayer().getKiller(),"hub");
            }
        }
    }

    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}

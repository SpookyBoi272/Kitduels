package prod.spooky.kitduels.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;


public class OnDuelEnd implements Listener {
    public List<UUID> playersInDuel = new ArrayList<>();

    public void add(Player player,Player target){
        UUID playerID = player.getUniqueId();
        UUID targetID = target.getUniqueId();
        playersInDuel.add(playerID);
        playersInDuel.add(targetID);
    }

    public boolean checkInDuel(Player player){
        return playersInDuel.contains(player.getUniqueId());
    }

    public void removePlayers(Player player, Player target){
        playersInDuel.remove(player.getUniqueId());
        playersInDuel.remove(target.getUniqueId());
    }

}

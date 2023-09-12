package prod.spooky.kitduels.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import prod.spooky.kitduels.kits.DiamondKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Duel implements Listener {
    public List<UUID> playersInDuel = new ArrayList<>();

    @EventHandler
    public void onDuelEnd(PlayerDeathEvent event){
        if ((event.getPlayer().getKiller()!= null)){
            System.out.println(playersInDuel.contains(event.getPlayer().getUniqueId()));
            if (playersInDuel.contains(event.getPlayer().getUniqueId())){
                removePlayers(event.getPlayer(),event.getPlayer().getKiller());
                Bukkit.dispatchCommand(event.getPlayer().getKiller(),"hub");
                Bukkit.unloadWorld("Arena",true);
                WorldCreator worldCreator = new WorldCreator("Arena");
                worldCreator.createWorld();
            }
        }
    }

    public void  startDuel(Player player, Player target){
        loadArena();
        sendDuelMsg(player,target);
        tpPlayers(player,target);
        loadKit(player, target);
        addToList(player,target);
    }



    private void loadArena(){
        WorldCreator worldCreator = new WorldCreator("Arena");
        worldCreator.createWorld();
    }

    private void sendDuelMsg(Player player, Player target){
        player.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with " + target.getName());
        target.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with " + player.getName());
    }

    private void tpPlayers(Player player, Player target){
        World arena = Objects.requireNonNull(Bukkit.getWorld("Arena"));
        player.teleport(arena.getSpawnLocation());
        Location targetLocation = new Location(arena, 0, 69, -36);
        target.teleport(targetLocation);
    }

    private void loadKit(Player player, Player target){
        DiamondKit kit = new DiamondKit();
        kit.addItems(player);
        kit.addItems(target);
        player.setInvulnerable(false);
        target.setInvulnerable(false);
    }

    public void addToList(Player player, Player target){
        playersInDuel.add(player.getUniqueId());
        playersInDuel.add(target.getUniqueId());
    }

    public void removePlayers(Player player, Player target){
        playersInDuel.remove(player.getUniqueId());
        playersInDuel.remove(target.getUniqueId());
    }
}

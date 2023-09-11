package prod.spooky.kitduels.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import prod.spooky.kitduels.events.OnDuelEnd;
import prod.spooky.kitduels.kits.DiamondKit;

import java.util.Objects;


public class Duel {



    public Duel(Player player, Player target){
        loadArena();
        sendDuelMsg(player,target);
        tpPlayers(player,target);
        addToList(player, target);
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
        OnDuelEnd playersInDuel = new OnDuelEnd();
        playersInDuel.add(player,target);
    }

}

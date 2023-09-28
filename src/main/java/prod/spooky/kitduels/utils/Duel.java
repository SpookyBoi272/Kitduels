package prod.spooky.kitduels.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import prod.spooky.kitduels.kits.DiamondKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Duel implements Listener {
    public static List<UUID> playersInDuel = new ArrayList<>();

    public void startDuel(Player player, Player target) {
        sendDuelMsg(player, target);
        tpPlayers(player, target);
        loadKit(player, target);
    }

    private void sendDuelMsg(Player player, Player target) {
        player.sendMessage(ChatColor.RED + "[KitDuels] " + ChatColor.WHITE + "You have started a duel with " + target.getName());
        target.sendMessage(ChatColor.RED + "[KitDuels] " + ChatColor.WHITE + "You have started a duel with " + player.getName());
    }

    private void tpPlayers(Player player, Player target) {
        World arena = Objects.requireNonNull(Bukkit.getWorld("Arena"));
        player.teleport(arena.getSpawnLocation());
        Location targetLocation = new Location(arena, 0, 69, -36);
        target.teleport(targetLocation);
    }

    private void loadKit(Player player, Player target) {
        DiamondKit kit = new DiamondKit();
        kit.addItems(player);
        kit.addItems(target);
        player.setInvulnerable(false);
        target.setInvulnerable(false);
    }


}

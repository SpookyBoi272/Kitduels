package prod.spooky.kitduels.utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import prod.spooky.kitduels.Kitduels;
import prod.spooky.kitduels.kits.DiamondKit;
import java.util.*;

public class Duel implements Listener {
    public static List<UUID> playersInDuel = new ArrayList<>();
    public static Map<UUID, Long> playerTimeMap = new HashMap<>();
    Kitduels plugin = JavaPlugin.getPlugin(Kitduels.class);
//    BukkitRunnable countDown = new BukkitRunnable() {
//        @Override
//        public void run() {
//            long currentTime = System.currentTimeMillis();
//            long playerTime = Duel.getPlayerTime(p);
//            if
//        }
//    };

    // Method to add a player to the map with the current time
    public static void addPlayer(UUID playerUUID) {
        long currentTime = System.currentTimeMillis();
        playerTimeMap.put(playerUUID, currentTime);
    }

    // Method to retrieve the time for a player
    public static long getPlayerTime(Player player) {
        UUID playerUUID = player.getUniqueId();
        return playerTimeMap.getOrDefault(playerUUID, 0L); // Default value is 0 if not found
    }

    // Method to remove a player from the map
    public static void removePlayer(Player player) {
        UUID playerUUID = player.getUniqueId();
        playerTimeMap.remove(playerUUID);
    }

    public void startDuel(Player player, Player target) {
        sendDuelMsg(player, target);
        tpPlayers(player, target);
        loadKit(player, target);
        addPlayer(target.getUniqueId());
        addPlayer(player.getUniqueId());
//        startCountDown(player, target);
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

//    private void startCountDown(Player player, Player target){
//
//        countDown.runTaskTimer(plugin, 0, 20);
//
//    }
}

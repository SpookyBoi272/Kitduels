package prod.spooky.kitduels.utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import prod.spooky.kitduels.Kitduels;
import prod.spooky.kitduels.kits.AxeKit;
import prod.spooky.kitduels.kits.BuffKit;
import prod.spooky.kitduels.kits.DiamondKit;

import java.util.*;

public class Duel {
    public static List<UUID> playersInDuel = new ArrayList<>();
    public static Map<UUID, Long> playerTimeMap = new HashMap<>();
    public static List<String> activeMaps = new ArrayList<>();
    Kitduels plugin = JavaPlugin.getPlugin(Kitduels.class);

    public void startDuel(Player player, Player target, String kit, String map) {
        activeMaps.add(map);
        sendDuelMsg(player, target);
        setupPlayers(player, target, map);
        loadKit(player, target, kit);
        addPlayer(player,target);
        startCountDown(player, target);
    }

    // Method to add a player to the map with the current time and Also adds To the playersInDuel list
    public static void addPlayer(Player player, Player target) {
        UUID playerUUID = player.getUniqueId();
        UUID targetUUID = target.getUniqueId();
        long currentTime = System.currentTimeMillis();
        playerTimeMap.put(playerUUID, currentTime);
        playerTimeMap.put(targetUUID,currentTime);
        playersInDuel.add(playerUUID);
        playersInDuel.add(targetUUID);
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

    private void sendDuelMsg(Player player, Player target) {
        player.sendMessage(ChatColor.RED + "[KitDuels] " + ChatColor.AQUA + "You have started a duel with " + target.getName());
        target.sendMessage(ChatColor.RED + "[KitDuels] " + ChatColor.AQUA + "You have started a duel with " + player.getName());
    }

    private void setupPlayers(Player player, Player target, String map) {
        World arena = Objects.requireNonNull(Bukkit.getWorld(map));
        switch (map) {
            case "Arena" -> {
                Location spawnLocation = new Location(arena,0,68,34,180,0);
                player.teleport(spawnLocation);
                Location targetLocation = new Location(arena, 0, 68, -36);
                target.teleport(targetLocation);
            }
            case "Fractal" -> {
                Location spawnLocation = new Location(arena,0,65,-38,0,0);
                player.teleport(spawnLocation);
                Location targetLocation = new Location(arena, 0, 65, 38,180,0);
                target.teleport(targetLocation);
            }
            case "Museum" -> {
                Location spawnLocation = new Location(arena,0,71,-33,0,0);
                player.teleport(spawnLocation);
                Location targetLocation = new Location(arena, 0, 71, 33,180,0);
                target.teleport(targetLocation);
            }
            case "Highset" -> {
                Location spawnLocation = new Location(arena,36,72,0,90,0);
                player.teleport(spawnLocation);
                Location targetLocation = new Location(arena, -37, 72, 0,-90,0);
                target.teleport(targetLocation);
            }
        }
        player.getInventory().clear();
        player.setFoodLevel(2000);
        player.setHealth(20);
        target.getInventory().clear();
        target.setFoodLevel(2000);
        target.setHealth(20);
    }

    private void loadKit(Player player, Player target, String Kit) {
        switch (Kit) {
            case "Shield" -> {
                AxeKit kit = new AxeKit();
                kit.addItems(player);
                kit.addItems(target);
            }
            case "Sword" -> {
                DiamondKit kit = new DiamondKit();
                kit.addItems(player);
                kit.addItems(target);
            }
            case "Buff" -> {
                BuffKit kit = new BuffKit();
                kit.addItems(player);
                kit.addItems(target);
            }
        }
        player.setInvulnerable(false);
        target.setInvulnerable(false);
    }

    private void startCountDown(Player player, Player target){
        BukkitRunnable countDown = new BukkitRunnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long playerTime = Duel.getPlayerTime(player);
                long diff = currentTime - playerTime;
                int remTime = 3 - (((int)(diff)) / 1000);
                System.out.println("Diff:"+diff);
                System.out.println(remTime);
                System.out.println(playerTimeMap.size());
                if (remTime<1){
                    this.cancel();
                }
                player.sendTitle(ChatColor.AQUA + "Kit Duels", ChatColor.WHITE + "Duel is starting in "+remTime+" Seconds", 1, 20, 1);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                target.sendTitle(ChatColor.AQUA + "Kit Duels", ChatColor.WHITE + "Duel is starting in "+remTime+" Seconds", 1, 20, 1);
                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
            }
        };
        countDown.runTaskTimer(plugin, 0, 20);

    }
}

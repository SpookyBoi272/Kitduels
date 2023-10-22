package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.Duel;
import prod.spooky.kitduels.utils.DuelRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class DuelCommand implements CommandExecutor, Listener {
    public static Map<UUID, DuelRequest> pendingRequests = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player p){
            if (strings.length<1) {
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Please Select a player to duel.");
                return true;
            }
            if (Bukkit.getPlayer(strings[0])==null){
                p.sendMessage("Player is currently offline.");
                return true;
            }
            if (Duel.playersInDuel.contains(Bukkit.getPlayer(strings[0]).getUniqueId())){
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Player is already in a Duel.");
                return true;
            }
            Player target = Bukkit.getPlayer(strings[0]);
            if (!(Duel.playersInDuel.contains(target.getUniqueId()))){
                Duel.playersInDuel.add(p.getUniqueId());
                Duel.playersInDuel.add(target.getUniqueId());
                if (strings.length == 1){

                }
                sendDuelRequest(p,target);
            }
        }else {
            System.out.println("This command must be executed by a player");
        }
        return true;
    }

    public void sendDuelRequest(Player sender, Player receiver) {
        UUID senderUUID = sender.getUniqueId();
        UUID receiverUUID = receiver.getUniqueId();

        DuelRequest request = new DuelRequest(senderUUID, receiverUUID);
        DuelCommand.pendingRequests.put(receiverUUID, request);

        // Send a message to the receiver indicating the duel request
        receiver.sendMessage(sender.getName() + " has challenged you to a duel. Type "+ChatColor.GREEN+"/accept "+ChatColor.WHITE+"or "+ChatColor.RED+"/decline "+ChatColor.WHITE+"to respond.");
        sender.sendMessage("Sent Duel request to "+receiver.getName());
    }


    @EventHandler
    public void onDuelEnd(PlayerDeathEvent event){
        if ((event.getPlayer().getKiller()!= null)){
            if (Duel.playersInDuel.contains(event.getPlayer().getUniqueId())){
                sendWinnerInfo(event.getPlayer(),event.getPlayer().getKiller(),event.getPlayer().getKiller().getHealth());
                Duel.playersInDuel.remove(event.getPlayer().getUniqueId());
                Duel.playersInDuel.remove(event.getPlayer().getKiller().getUniqueId());
                Duel.removePlayer(event.getPlayer());
                Duel.removePlayer(event.getPlayer().getKiller());
                Bukkit.dispatchCommand(event.getPlayer().getKiller(),"hub");
                Bukkit.unloadWorld("Arena",false);
                WorldCreator arenaLoader = new WorldCreator("Arena");
                arenaLoader.createWorld();
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

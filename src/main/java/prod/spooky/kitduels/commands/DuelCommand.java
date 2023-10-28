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
//import prod.spooky.kitduels.events.OnCompassMenu;
import prod.spooky.kitduels.utils.Duel;
import prod.spooky.kitduels.utils.DuelRequest;

import java.util.*;


public class DuelCommand implements CommandExecutor {
    public static Map<UUID, DuelRequest> pendingRequests = new HashMap<>();
    public static ArrayList<String> kit = new ArrayList<>();
    public static ArrayList<String> map = new ArrayList<>();


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player p){
            kit.add("Shield"); kit.add("Buff"); kit.add("Sword");
            map.add("Museum"); map.add("Arena"); map.add("Highset"); map.add("Fractal");
            if (strings.length==3){
                if (Bukkit.getPlayer(strings[0])!=null && !Objects.equals(p,Bukkit.getPlayer(strings[0])) && kit.contains(strings[1]) && map.contains(strings[2])){
//                    OnCompassMenu menu = new OnCompassMenu();
//                    menu.sendDuelRequest(p, Objects.requireNonNull(Bukkit.getPlayer(strings[0])));
                    sendDuelRequest(p, Objects.requireNonNull(Bukkit.getPlayer(strings[0])),strings[1], strings[2]);
                }
            }else {
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Invalid Usage of Command. Use Compass For GUI");
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Type \"/help duel\" for further information");
            }
        }else {
            System.out.println("This command must be executed by a player");
        }
        return true;
    }

    public void sendDuelRequest(Player sender, Player receiver, String kit, String map) {
        UUID senderUUID = sender.getUniqueId();
        UUID receiverUUID = receiver.getUniqueId();

        DuelRequest request = new DuelRequest(senderUUID, receiverUUID, kit, map);
        DuelCommand.pendingRequests.put(receiverUUID, request);

        // Send a message to the receiver indicating the duel request
        receiver.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+sender.getName() + " has challenged you to a duel.");
        receiver.sendMessage(ChatColor.DARK_PURPLE+"Kit: "+kit+ChatColor.AQUA+"   Map: "+map);
        receiver.sendMessage("Type "+ChatColor.GREEN+"/accept"+ChatColor.WHITE+" or "+ChatColor.RED+"/decline "+ChatColor.WHITE+"to respond.");
        sender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Sent Duel request to "+receiver.getName());
    }

}

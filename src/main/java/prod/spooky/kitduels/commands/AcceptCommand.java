package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.Duel;
import prod.spooky.kitduels.utils.DuelRequest;

import java.util.Objects;
import java.util.UUID;

import static prod.spooky.kitduels.commands.DuelCommand.pendingRequests;

public class AcceptCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            if(strings.length<1){
                commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Invalid Usage of Command.");
            }else if (Bukkit.getPlayer(strings[0])==null){
                commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"This Player is Offline.");
            } else{
                if (Duel.playersInDuel.contains(((Player) commandSender).getUniqueId())){
                    commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You cannot accept requests while in a Duel.");
                    return true;
                }
                UUID senderID = ((Player) commandSender).getUniqueId();
                DuelRequest request = pendingRequests.get(senderID);
                if (request.getSender() == (Objects.requireNonNull(Bukkit.getPlayer(strings[0])).getUniqueId())){
                    pendingRequests.remove(senderID);
                    acceptDuelRequest(request);
                }else {
                    commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You don't have any pending duel request from this Player.");
                }
            }
        }else {
            System.out.println("This Command must be Executed by Player");
        }
        return true;
    }

    // Accept the Duel request
    public void acceptDuelRequest(DuelRequest request) {
        Player acceptingPlayer = Bukkit.getPlayer(request.getReceiver());
        Player sendingPlayer = Bukkit.getPlayer(request.getReceiver());

        if (sendingPlayer != null) {
            acceptingPlayer.sendMessage(ChatColor.RED + "[KitDuels] " + ChatColor.GREEN + "You have accepted the duel request from " + sendingPlayer.getName());

            Duel duel = new Duel();
            duel.startDuel(acceptingPlayer, Bukkit.getPlayer(request.getSender()), request.getKit(), request.getMap());
        } else {
            acceptingPlayer.sendMessage(ChatColor.RED + "[KitDuels] "+ChatColor.WHITE+"Player has left the Game.");
        }
    }

}

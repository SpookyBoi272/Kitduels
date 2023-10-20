package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
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
                commandSender.sendMessage("Invalid Usage of Command.");
            }else if (Bukkit.getPlayer(strings[0])==null){
                commandSender.sendMessage("This Player is Offline.");
            } else{
                acceptDuelRequest(Objects.requireNonNull(Bukkit.getPlayer(strings[0])));
            }
        }else {
            System.out.println("This Command must be Executed by Player");
        }
        return true;
    }

    // When accepting a duel request
    public void acceptDuelRequest(Player player) {
        UUID playerUUID = player.getUniqueId();
        DuelRequest request = pendingRequests.remove(playerUUID);

        if (request != null) {
            UUID senderUUID = request.getSender();
            if (senderUUID.equals(playerUUID)) {
                // The player is accepting their own request, handle it accordingly
                player.sendMessage("You can't accept your own duel request.");
            } else {
                // Handle accepting the duel request (start the duel)
                Duel duel = new Duel();
                duel.startDuel(player,Bukkit.getPlayer(request.getReceiver()));
                player.sendMessage("You have accepted the duel request from " + Bukkit.getOfflinePlayer(senderUUID).getName());
            }
        } else {
            // No pending request from the player
            player.sendMessage("You don't have any pending duel requests to accept.");
        }
    }

}

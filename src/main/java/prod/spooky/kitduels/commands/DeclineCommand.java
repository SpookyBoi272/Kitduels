package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.DuelRequest;

import java.util.Objects;
import java.util.UUID;

import static prod.spooky.kitduels.commands.DuelCommand.pendingRequests;

public class DeclineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            if(strings.length<1){
                commandSender.sendMessage("Invalid Usage of Command.");
            }else if (Bukkit.getPlayer(strings[0])== null){
                commandSender.sendMessage("This Player is Offline.");
            } else {
                declineDuelRequest(Objects.requireNonNull(Bukkit.getPlayer(strings[0])));
            }
        }else {
            System.out.println("This Command must be Executed by Player");
        }

        return true;
    }

    public void declineDuelRequest(Player player) {
        UUID playerUUID = player.getUniqueId();
        DuelRequest request = pendingRequests.remove(playerUUID);

        if (request != null) {
            // Handle declining the duel request
            player.sendMessage("You have declined the duel request from " + Bukkit.getOfflinePlayer(request.getSender()).getName());
        }
    }

}

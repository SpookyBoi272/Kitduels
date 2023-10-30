package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.DuelRequest;

import java.util.Objects;

import static prod.spooky.kitduels.commands.DuelCommand.pendingRequests;

public class DeclineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            if(strings.length!=1){
                commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Invalid Usage of Command.");
                return true;
            }
            if (Bukkit.getPlayer(strings[0])== null){
                commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"This Player is Offline.");
                return true;
            }
            DuelRequest request = pendingRequests.get(((Player) commandSender).getUniqueId());
            if (request.getSender() == (Objects.requireNonNull(Bukkit.getPlayer(strings[0])).getUniqueId())){
                pendingRequests.remove(request.getReceiver());
                informDeclineDuelRequest(request);
            }else {
                commandSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You don't have any pending duel request from this Player.");
            }
        }else {
            System.out.println("This Command must be Executed by Player");
        }

        return true;
    }

    public void informDeclineDuelRequest(DuelRequest request) {
        Player requestSender = Bukkit.getPlayer(request.getSender());
        Player requestReceiver = Bukkit.getPlayer(request.getReceiver());

        if (requestSender != null) {
            requestSender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+Bukkit.getOfflinePlayer(request.getReceiver()).getName()+" has declined your duel request.");
        }
        if (requestReceiver != null) {
            requestReceiver.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have declined the duel request from " + Bukkit.getOfflinePlayer(request.getSender()).getName());
        }
    }

}

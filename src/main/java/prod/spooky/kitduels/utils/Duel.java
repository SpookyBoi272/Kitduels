package prod.spooky.kitduels.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Duel {
    public Duel(Player player, Player target){
        player.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with" + target.getPlayer());
        target.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with" + player.getPlayer());

    }
}

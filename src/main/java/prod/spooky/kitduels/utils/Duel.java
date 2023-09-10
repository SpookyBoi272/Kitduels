package prod.spooky.kitduels.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import prod.spooky.kitduels.kits.DiamondKit;

public class Duel {
    public Duel(Player player, Player target){
        WorldCreator worldCreator = new WorldCreator("Arena");
        World arena = worldCreator.createWorld();



        player.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with " + target.getName());
        target.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"You have started Duel with " + player.getName());
        player.teleport(Bukkit.getWorld("Arena").getSpawnLocation());
        World world = Bukkit.getWorld("Arena");
        double x = 0.0;//0 67 -36
        double y = 69.0;
        double z = -36.0;
        Location targetLocation = new Location(world, x, y, z);
        target.teleport(targetLocation);

        DiamondKit kit = new DiamondKit();
        kit.addItems(player);
        kit.addItems(target);
        player.setInvulnerable(false);
        target.setInvulnerable(false);
    }
}

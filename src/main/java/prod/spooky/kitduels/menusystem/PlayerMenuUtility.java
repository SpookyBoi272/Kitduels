package prod.spooky.kitduels.menusystem;

import org.bukkit.entity.Player;

/*
Companion class to all menus. This is needed to pass information across the entire
 menu system no matter how many inventories are opened or closed.

 Each player has one of these objects, and only one.
*/

public class PlayerMenuUtility {

    private final Player owner;
    private String kit;
    private String map;
    private String opponent;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent.getName();
    }
}

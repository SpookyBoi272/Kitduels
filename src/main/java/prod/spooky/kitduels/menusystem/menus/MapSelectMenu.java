package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;
import prod.spooky.kitduels.utils.Duel;

import java.util.List;

public class MapSelectMenu extends Menu {
    public MapSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.YELLOW + "Map Selection";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        String map = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
        if (Duel.activeMaps.contains(map)){
            e.getWhoClicked().sendMessage(ChatColor.RED+"This Map is Currently not Available for Duel");
            return;
        }
        Player player = (Player) e.getWhoClicked();
        playerMenuUtility.setMap(map);

        player.closeInventory();
        System.out.println(playerMenuUtility.getKit()+playerMenuUtility.getMap()+playerMenuUtility.getOpponent());
        Bukkit.dispatchCommand(player,"duel "+playerMenuUtility.getOpponent()+" "+playerMenuUtility.getKit()+" "+playerMenuUtility.getMap());
    }

    @Override
    public void setMenuItems() {
        addItemToGUI(inventory, Material.MOSSY_STONE_BRICKS, "Museum", ChatColor.LIGHT_PURPLE);
        addItemToGUI(inventory, Material.ORANGE_WOOL, "Arena", ChatColor.GOLD);
        addItemToGUI(inventory, Material.SEA_LANTERN, "Fractal", ChatColor.AQUA);
        addItemToGUI(inventory, Material.OAK_LEAVES, "Highset", ChatColor.GREEN);
        setFillerGlass();
    }

    private void addItemToGUI(Inventory gui, Material material, String name, ChatColor color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        if (Duel.activeMaps.contains(name)){
            meta.setLore(List.of(new String[]{ChatColor.RED+"Not Available"}));
        }
        item.setItemMeta(meta);
        gui.addItem(item);
    }
}

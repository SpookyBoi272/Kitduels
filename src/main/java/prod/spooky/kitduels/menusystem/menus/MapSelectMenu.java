package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.Kitduels;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;

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
        Player player = (Player) e.getWhoClicked();
        playerMenuUtility.setMap(map);

        player.closeInventory();
        System.out.println(playerMenuUtility.getKit());
        Bukkit.dispatchCommand(player,"duel "+playerMenuUtility.getOpponent()+" "+playerMenuUtility.getKit()+" "+playerMenuUtility.getMap());
    }

    @Override
    public void setMenuItems() {
        addItemToGUI(inventory, Material.MOSSY_STONE_BRICKS, "Museum", ChatColor.LIGHT_PURPLE);
        addItemToGUI(inventory, Material.ORANGE_WOOL, "Arena", ChatColor.GOLD);
        addItemToGUI(inventory, Material.SEA_LANTERN, "SkyPort", ChatColor.AQUA);
        addItemToGUI(inventory, Material.OAK_LEAVES, "Highset", ChatColor.GREEN);
        setFillerGlass();
    }

    private void addItemToGUI(Inventory gui, Material material, String name, ChatColor color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        item.setItemMeta(meta);
        gui.addItem(item);
    }
}

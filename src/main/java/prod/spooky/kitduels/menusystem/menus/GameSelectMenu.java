package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;

import java.util.List;

public class GameSelectMenu extends Menu {
    public GameSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.YELLOW + "Games";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        if (e.getCurrentItem().getType()==Material.DIAMOND_SWORD){
            PlayerSelectMenu menu = new PlayerSelectMenu(playerMenuUtility);
            menu.open();
        }

    }

    @Override
    public void setMenuItems() {
        ItemStack duels = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = duels.getItemMeta();
        swordMeta.setLore(List.of(new String[]{ChatColor.YELLOW+"Join Duels"}));
        swordMeta.setDisplayName(ChatColor.RED + "Duels");
        duels.setItemMeta(swordMeta);

        inventory.setItem(0,duels);

        setFillerGlass();
    }
}

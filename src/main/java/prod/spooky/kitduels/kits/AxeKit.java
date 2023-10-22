package prod.spooky.kitduels.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AxeKit {
        ItemStack armor1 = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack armor2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack armor3 = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack armor4 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack armor5 = new ItemStack(Material.SHIELD);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack arrow = new ItemStack(Material.ARROW, 10);

        ItemStack[] axeKit = {sword, axe, bow, arrow, food};
        public AxeKit() {

        }

        public void addItems(Player p){
            p.getInventory().addItem(axeKit);
            p.getInventory().setHelmet(armor1);
            p.getInventory().setChestplate(armor2);
            p.getInventory().setLeggings(armor3);
            p.getInventory().setBoots(armor4);
            p.getInventory().setItemInOffHand(armor5);
        }
}

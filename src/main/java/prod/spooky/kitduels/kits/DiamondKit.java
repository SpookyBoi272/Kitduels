package prod.spooky.kitduels.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DiamondKit {
    ItemStack armor1 = new ItemStack(Material.DIAMOND_HELMET);
    ItemStack armor2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemStack armor3 = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemStack armor4 = new ItemStack(Material.DIAMOND_BOOTS);
    ItemStack armor5 = new ItemStack(Material.SHIELD);
    ItemStack blocks = new ItemStack(Material.STONE_BRICKS, 64);
    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
    ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
    ItemStack bow = new ItemStack(Material.BOW);
    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 5);
    ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
    ItemStack arrow = new ItemStack(Material.ARROW, 64);
    ItemStack water = new ItemStack(Material.WATER_BUCKET);
    ItemStack lava = new ItemStack(Material.LAVA_BUCKET);

    ItemStack[] diamondKit = {sword, axe, bow, arrow, apple, food, blocks, lava, water};
    public DiamondKit() {
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
    }

    public void addItems(Player p){
        p.getInventory().clear();
        p.setFoodLevel(2000);
        p.setHealth(20);
        p.getInventory().addItem(diamondKit);
        p.getInventory().setHelmet(armor1);
        p.getInventory().setChestplate(armor2);
        p.getInventory().setLeggings(armor3);
        p.getInventory().setBoots(armor4);
        p.getInventory().setItemInOffHand(armor5);
    }
}

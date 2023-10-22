package prod.spooky.kitduels.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;

public class NetheritePotKit {
    ItemStack armor1 = new ItemStack(Material.NETHERITE_HELMET);
    ItemStack armor2 = new ItemStack(Material.NETHERITE_CHESTPLATE);
    ItemStack armor3 = new ItemStack(Material.NETHERITE_LEGGINGS);
    ItemStack armor4 = new ItemStack(Material.NETHERITE_BOOTS);
    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
    ItemStack instantHealth = new ItemStack(Material.POTION);
    ItemStack speedPot = new ItemStack(Material.POTION);
    ItemStack strengthPot = new ItemStack(Material.POTION);
    ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);

    ItemStack[] axeKit = {sword, totem, instantHealth, speedPot, strengthPot, apple};
    public NetheritePotKit() {
        setPotionType(instantHealth, PotionEffectType.HEAL,100, 2 ,true );
        setPotionType(speedPot, PotionEffectType.SPEED, 1800, 2, true);
        setPotionType(strengthPot, PotionEffectType.INCREASE_DAMAGE, 1800 , 2, true);
    }

    public void addItems(Player p){
        p.getInventory().clear();
        p.setFoodLevel(2000);
        p.setHealth(20);
        p.getInventory().addItem(axeKit);
        p.getInventory().setHelmet(armor1);
        p.getInventory().setChestplate(armor2);
        p.getInventory().setLeggings(armor3);
        p.getInventory().setBoots(armor4);
        p.getInventory().setItemInOffHand(totem);
    }

    public void setPotionType(ItemStack potion,PotionEffectType type, int duration, int amplifier, boolean isSplash) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.addCustomEffect(new PotionEffect(type, duration, amplifier), isSplash);
        potion.setItemMeta(meta);
    }

}

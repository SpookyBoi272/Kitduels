package prod.spooky.kitduels.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import prod.spooky.kitduels.Kitduels;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class ConfigReader {
    static Kitduels plugin = Kitduels.getPlugin();

    public static ArrayList<String> mapList = new ArrayList<>();

    public static ArrayList<String> getMapsList(){
        FileConfiguration config = plugin.getConfig();
        mapList.add(config.getString("spawn-locations.Arena1.spawn1.world"));
        System.out.println(config.getString("spawn-locations.Arena1.spawn1.world"));
        mapList.add(config.getString("spawn-locations.Arena2.spawn1.world"));
        mapList.add(config.getString("spawn-locations.Arena3.spawn1.world"));
        mapList.add(config.getString("spawn-locations.Arena4.spawn1.world"));
        return mapList;
    }

    public static Location getHubSpawn(){
        return loadLocationFromConfig("spawn-locations.Hub");
    }

    public static Location getArena1PlayerSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena1.spawn1");
    }

    public static Location getArena1TargetSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena1.spawn2");
    }

    public static Location getArena2PlayerSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena2.spawn1");
    }

    public static Location getArena2TargetSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena2.spawn2");
    }

    public static Location getArena3PlayerSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena3.spawn1");
    }

    public static Location getArena3TargetSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena3.spawn2");
    }

    public static Location getArena4PlayerSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena4.spawn1");
    }

    public static Location getArena4TargetSpawn(){
        return loadLocationFromConfig("spawn-locations.Arena4.spawn2");
    }

    public static Location loadLocationFromConfig(String path) {
        FileConfiguration config = plugin.getConfig();
        String worldName = config.getString(path + ".world");
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(getServer().getWorld(worldName), x, y, z, yaw, pitch);
    }


}

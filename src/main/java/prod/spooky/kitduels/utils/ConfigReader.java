package prod.spooky.kitduels.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import prod.spooky.kitduels.Kitduels;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class ConfigReader {
    static Kitduels plugin = Kitduels.getPlugin();
    public static ArrayList<String> mapList = new ArrayList<>();
    private static final FileConfiguration config = plugin.getConfig();

    public static ArrayList<String> getMapsList(){
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
        String worldName = config.getString(path + ".world");
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(getServer().getWorld(worldName), x, y, z, yaw, pitch);
    }

    public static void setArenaSpawn(String arena, String spawnSide, Location location){
        String path = "spawn-locations." + arena + "." + spawnSide;
        saveLocationToConfig(path, location);
    }

//    public static void setArena1PlayerSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena1.spawn1",location);
//    }
//
//    public static void setArena1TargetSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena1.spawn2",location);
//    }
//
//    public static void setArena2PlayerSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena2.spawn1",location);
//    }
//
//    public static void setArena2TargetSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena2.spawn2",location);
//    }
//
//    public static void setArena3PlayerSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena3.spawn1",location);
//    }
//
//    public static void setArena3TargetSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena3.spawn2",location);
//    }
//
//    public static void setArena4PlayerSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena4.spawn1",location);
//    }
//
//    public static void setArena4TargetSpawn(Location location){
//        saveLocationToConfig("spawn-locations.Arena4.spawn2",location);
//    }

    public static void saveLocationToConfig(String path, Location location) {
        config.set(path + ".world", location.getWorld().getName());
        config.set(path + ".x", location.getX());
        config.set(path + ".y", location.getY());
        config.set(path + ".z", location.getZ());
        config.set(path + ".yaw", location.getYaw());
        config.set(path + ".pitch", location.getPitch());
        plugin.saveConfig();
    }

}

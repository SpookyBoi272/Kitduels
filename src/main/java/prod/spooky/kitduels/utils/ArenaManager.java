import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Location;
import org.bukkit.World;

public class ArenaManager {

    private EditSession editSession; // Store the EditSession

    public void captureArenaRegion(World world, Location pos1, Location pos2) {
        Region region = WorldEdit.getInstance().getRegion(pos1, pos2);
        editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(world), -1);
        try {
            editSession.enableQueue();
            editSession.copyFromWorld(region, BukkitAdapter.asBlockVector3(pos1));
            editSession.flushQueue();
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }

    public void resetArenaRegion(Location pos1, Location pos2) {
        Region region = WorldEdit.getInstance().getRegion(pos1, pos2);
        try {
            editSession.enableQueue();
            editSession.pasteToWorld(region, BukkitAdapter.asBlockVector3(pos1), false, true);
            editSession.flushQueue();
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }
}

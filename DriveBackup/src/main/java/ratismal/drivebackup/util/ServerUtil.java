package ratismal.drivebackup.util;

import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.World;

import ratismal.drivebackup.config.ConfigParser;
import ratismal.drivebackup.plugin.DriveBackup;

public class ServerUtil {
    /**
     * Turns the server auto save on/off
     * @param autoSave whether to save automatically
     */
    public static void setAutoSave(boolean autoSave) {
        if (!ConfigParser.getConfig().backupStorage.disableSavingDuringBackups) {
            return;
        }

        try {
            Bukkit.getScheduler().callSyncMethod(DriveBackup.getInstance(), new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    for (World world : Bukkit.getWorlds()) {
                        world.setAutoSave(autoSave);
                    }
                    return true;
                }
            }).get();
        } catch (Exception exception) { }
    }
}

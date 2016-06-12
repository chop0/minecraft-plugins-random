package firebow;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class template extends JavaPlugin implements Listener {
	
  public static Logger log = Logger.getLogger("Minecraft");
  public static boolean enabled = true;

  public void onLoad() {
    log.info("[Plugin] Server loaded.");
  }
    
  public void onEnable() {
    log.info("[Plugin] enabling.");
    getServer().getPluginManager().registerEvents(this, this);
  }

  public void onDisable() {
    log.info("[Plugin] Server stopping.");
  }
}
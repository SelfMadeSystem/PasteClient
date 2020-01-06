package uwu.smsgamer.PasteClient.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LandEvent implements Event {
    public World world;
    public Entity entity;
    public double newMotion;
    public double originalMotion;

    public LandEvent(World world, Entity entity, double newMotion, double originalMotion) {
        this.world = world;
        this.entity = entity;
        this.newMotion = newMotion;
        this.originalMotion = originalMotion;
    }
}

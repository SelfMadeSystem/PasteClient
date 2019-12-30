package uwu.smsgamer.PasteClient.scripting.runtime.events;

import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.network.Packet;
import uwu.smsgamer.PasteClient.events.PacketEvent;

public class ScriptPacketEvent {
    private final EventType eventType;
    private Packet packet;
    private boolean cancelled;

    public ScriptPacketEvent(EventType eventType, Packet packet, boolean cancelled) {
        this.eventType = eventType;
        this.packet = packet;
        this.cancelled = cancelled;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void apply(PacketEvent event) {
        event.setPacket(packet);
        event.setCancelled(cancelled);
    }
}

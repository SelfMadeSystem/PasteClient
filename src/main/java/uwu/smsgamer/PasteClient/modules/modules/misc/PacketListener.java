package uwu.smsgamer.PasteClient.modules.modules.misc;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import uwu.smsgamer.PasteClient.events.PacketEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;

public class PacketListener extends Module {
    public BooleanValue send = new BooleanValue("Send", true);
    public BooleanValue receive = new BooleanValue("Receive", true);
    public BooleanValue packetPlayerInfo = new BooleanValue("PacketPlayer Info", true);
    public PacketListener() {
        super("PacketListener", "Listens to packets. Used for debugging.", ModuleCategory.MISC);
    }

    @EventTarget
    public void onPacket(PacketEvent e){
        if(!getState())
            return;
        EntityPlayerSP p = mc.player;
        if(e.getEventType().equals(EventType.SEND) && send.getObject()){
            if(e.getPacket() instanceof CPacketPlayer && packetPlayerInfo.getObject()){
                CPacketPlayer packet = (CPacketPlayer) e.getPacket();
                ChatUtils.info(packet.getX(p.posX) + " " + packet.getY(p.posY) + " " + packet.getZ(p.posZ) + " " +
                        packet.getYaw(p.rotationYaw) + " " + packet.getPitch(p.rotationPitch) + " " + packet.isOnGround());
            } else if (!packetPlayerInfo.getObject()) {
                ChatUtils.info("Send: " + e.getPacket());
            }
        }
        if(e.getEventType().equals(EventType.RECIEVE) && receive.getObject()){
            if(e.getPacket() instanceof SPacketPlayerPosLook && packetPlayerInfo.getObject()){
                SPacketPlayerPosLook packet = (SPacketPlayerPosLook) e.getPacket();
                ChatUtils.info(packet.getX() + " " + packet.getY() + " " + packet.getZ() + " " +
                        packet.getYaw() + " " + packet.getPitch());
            } else if (!packetPlayerInfo.getObject()) {
                ChatUtils.info("Send: " + e.getPacket());
            }
        }
    }
}

package uwu.smsgamer.PasteClient.modules.modules.misc;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import uwu.smsgamer.PasteClient.events.PacketEvent;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Flagger extends Module {
    public BooleanValue ground = new BooleanValue("Ground", false);
    public NumberValue<Integer> delay = new NumberValue<>("Delay", 1, 1, 100);
    public NumberValue<Integer> amount = new NumberValue<>("Amount", 1, 1, 100);
    public NumberValue<Integer> amountE = new NumberValue<>("AmountExponent", 1, 1, 100);
    public ModeValue mode = new ModeValue("Mode", "Y Packet", "Y Packet", "XZ Packet", "XYZ Packet", "SendPlayerAbilities",
            "Y Spoof", "XZ Spoof", "XYZ Spoof");
    public ModeValue yMode = new ModeValue("YMode", "IMAX_VALUE", "IMAX_VALUE", "IMIN_VALUE", "Static", "Relative");
    public NumberValue<Double> yOffset = new NumberValue<>("YOffset", -255d, -255d, 255d);
    public ModeValue xzMode = new ModeValue("XZMode", "IMAX_VALUE", "IMAX_VALUE", "IMIN_VALUE", "Static", "Relative");
    public NumberValue<Double> xOffset = new NumberValue<>("XOffset", -255d, -255d, 255d);
    public NumberValue<Double> zOffset = new NumberValue<>("ZOffset", -255d, -255d, 255d);

    public Flagger() {
        super("Flagger", "Makes you flag on anticheats.", ModuleCategory.MISC);
    }

    public int ticks;

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (!getState())
            return;
        ticks++;
        EntityPlayerSP p = mc.player;
        if (ticks % delay.getObject() == 0) {
            double y = p.posY;
            double x = p.posX;
            double z = p.posZ;
            switch (yMode.getObject()) {
                case 0:
                    y = Integer.MAX_VALUE;
                    break;
                case 1:
                    y = Integer.MIN_VALUE;
                    break;
                case 2:
                    y = yOffset.getObject();
                    break;
                case 3:
                    y += yOffset.getObject();
                    break;
            }
            double playerYaw = ((p.rotationYawHead + 90) * Math.PI / 180);
            switch (xzMode.getObject()) {
                case 0:
                    x = Integer.MAX_VALUE;
                    z = Integer.MAX_VALUE;
                    break;
                case 1:
                    x = Integer.MIN_VALUE;
                    z = Integer.MIN_VALUE;
                    break;
                case 2:
                    x = xOffset.getObject();
                    z = zOffset.getObject();
                    break;
                case 3:
                    x = p.posX + (Math.cos(playerYaw) * xOffset.getObject());
                    z = p.posZ + (Math.sin(playerYaw) * zOffset.getObject());
                    break;
            }
            for (int i = 0; i < Math.pow(amount.getObject(), amountE.getObject()); i++) {
                switch (mode.getObject()) {
                    case 0:
                        p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, y, p.posZ, ground.getObject()));
                        break;
                    case 1:
                        p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(x, p.posY, y, ground.getObject()));
                        break;
                    case 2:
                        p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(x, y, z, ground.getObject()));
                        break;
                    case 3:
                        PlayerCapabilities pc = new PlayerCapabilities();
                        pc.isCreativeMode = true;
                        pc.isFlying = true;
                        pc.allowFlying = true;
                        pc.allowEdit = true;
                        pc.disableDamage = true;
                        p.connection.sendPacket(new CPacketPlayerAbilities(pc));
                }
            }
        }
    }

    @EventTarget
    public void onPacket(PacketEvent event){
        if (!getState())
            return;
        Packet packet = event.getPacket();// TODO: 2020-01-03 Implement Spoof packets
        EntityPlayerSP p = mc.player;
    }
}

package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Flight extends Module {
    public BooleanValue hypixelPacket = new BooleanValue("HypixelPacket", true);
    public BooleanValue hypixelGroundPackets = new BooleanValue("HypixelGroundPackets", true);
    public BooleanValue hypixelGround = new BooleanValue("HypixelGround", true);
    public NumberValue<Integer> hypixelTicks = new NumberValue<>("HypixelTicks", 1, 1, 20);
    public NumberValue<Integer> hypixelValue = new NumberValue<>("HypixelValue", 1, 0, 2048);
    public NumberValue<Integer> hypixelDivision = new NumberValue<>("HypixelDivision", 5, 0, 20);
    public NumberValue<Integer> yTicks = new NumberValue<>("YTicks", 1, 1, 20);
    public NumberValue<Double> yTimes = new NumberValue<>("YTimes", 1.0, -2.0, 2.0);
    public NumberValue<Double> yAdd = new NumberValue<>("YAdd", 0.0, -2.0, 2.0);
    public ModeValue mode = new ModeValue("Mode", "Creative", "Creative", "Hypixel", "MotionY");

    private EntityPlayerSP p;
    private int ticks;

    public Flight() {
        super("Flight", "Lets you fly while in survival.", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        switch (mode.getObject()) {
            case 2:
                ChatUtils.info("It's not my fault if your game crashes.");
                break;
        }
    }

    @Override
    protected void onDisable() {
        if (p == null)
            return;
        mc.timer.offset = 50;
        if (p.motionY > 0)
            p.motionY = 0;
        if (Math.abs(p.motionX) > 0.1)
            p.motionX = 0;
        if (Math.abs(p.motionZ) > 0.1)
            p.motionZ = 0;
        p.capabilities.allowFlying = false;
        p.capabilities.isFlying = false;
        if (p.capabilities.isCreativeMode)
            p.capabilities.allowFlying = true;
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        p = mc.player;
        ticks++;
        if (!getState())
            return;

        switch (mode.getObject()) {
            case 0:
                creative();
                break;
            case 1:
                hypixel();
                break;
            case 2:
                motionY();
                break;
        }
    }

    private void motionY() {
        if(ticks % yTicks.getObject() == 0){
            p.motionY += yAdd.getObject();
            p.motionY *= yTimes.getObject();
        }
    }

    private void creative() {
        p.capabilities.isFlying = true;
        p.sendPlayerAbilities();
        p.sendPlayerAbilities();
        p.sendPlayerAbilities();
        p.sendPlayerAbilities();
        p.sendPlayerAbilities();
        p.sendPlayerAbilities();
    }

    private void hypixel() {
        p.motionY = 0;
        //ChatUtils.info((hypixelValue.getObject() / (Math.pow(10, hypixelDivision.getObject())))+" "+hypixelValue.getObject()+" "+(Math.pow(10, hypixelDivision.getObject())));
        //ChatUtils.info(p.posY + "");
        if (hypixelGround.getObject())
            p.onGround = true;
        if (hypixelGroundPackets.getObject())
            p.connection.getNetworkManager().sendPacket(new CPacketPlayer(true));
        if (ticks % hypixelTicks.getObject() == 0)
            if (hypixelPacket.getObject())
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + (hypixelValue.getObject() / (Math.pow(10, hypixelDivision.getObject()))), p.posZ, hypixelGroundPackets.getObject()));
            else
                p.setPosition(p.posX,
                        p.posY + (hypixelValue.getObject() / (Math.pow(10, hypixelDivision.getObject()))),
                        p.posZ);

    }
}

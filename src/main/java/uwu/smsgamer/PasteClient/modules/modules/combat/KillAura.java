package uwu.smsgamer.PasteClient.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.*;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class KillAura extends Module {
    public ModeValue aimMode = new ModeValue("AimMode", "1", "1", "2", "3", "4");

    public NumberValue<Float> aimRange = new NumberValue<>("AimRange", 4f, 0f, 10f);
    public NumberValue<Float> aimSpeed = new NumberValue<>("AimSpeed", 4f, 0f, 90f);
    public NumberValue<Float> aimSpeedAimed = new NumberValue<>("AimSpeedAimed", 4f, 0f, 90f);
    public NumberValue<Float> yawRando = new NumberValue<>("YawRandom", 4f, 0f, 90f);
    public NumberValue<Float> pitchRando = new NumberValue<>("PitchRandom", 4f, 0f, 90f);
    public NumberValue<Float> yawRandoAimed = new NumberValue<>("YawRandomAimed", 4f, 0f, 90f);
    public NumberValue<Float> pitchRandoAimed = new NumberValue<>("PitchRandomAimed", 4f, 0f, 90f);
    private NumberValue<Integer> clickMin = new NumberValue<>("ClickMin", 0, 0, 19);
    private NumberValue<Integer> clickMax = new NumberValue<>("ClickMax", 1, 1, 20);
    private BooleanValue outborder = new BooleanValue("Outborder", false);
    private BooleanValue random = new BooleanValue("Random", false);
    private BooleanValue predict = new BooleanValue("Predict", false);
    private BooleanValue debug1 = new BooleanValue("Debug1", false);
    private BooleanValue debug2 = new BooleanValue("Debug2", false);
    private BooleanValue debug3 = new BooleanValue("Debug3", false);

    public KillAura() {
        super("KillAura", "Automatically hits entities around you.", ModuleCategory.COMBAT);
    }

    private int ticks;
    private int rando = 0;

    @Override
    protected void onEnable() {
        ticks = 0;
        if (RandomUtils.nextInt(0, 4) == 0) {
            rando = 20 - RandomUtils.nextInt(clickMin.getObject() + (clickMax.getObject() - ((clickMax.getObject() - clickMin.getObject()) / 2)), clickMax.getObject());//20 18 (20 - (18/2)) = (20 - 9) = 11
        } else if (RandomUtils.nextInt(0, 2) == 0) {
            rando = 20 - RandomUtils.nextInt(clickMin.getObject(), clickMax.getObject() - ((clickMax.getObject() - clickMin.getObject()) / 2));
        } else {
            rando = 20 - RandomUtils.nextInt(clickMin.getObject(), clickMax.getObject());
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (!getState())
            return;
        EntityPlayerSP p = mc.player;
        EntityLivingBase target = EntityUtils.getClosestEntity();
        if (target == null)
            return;
        float range = mc.player.getDistanceToEntity(target);
        if (range > aimRange.getObject())
            return;
        Rotation r = RUtils.limitAngleChange(new Rotation(p.rotationYaw, p.rotationPitch), RUtils.searchCenter(target.getEntityBoundingBox(), outborder.getObject(), random.getObject(), predict.getObject(), true).getRotation(), aimSpeed.getObject());
        if (aimMode.getObject() == 0) {
            if (RUtils.isFaced(target, aimRange.getObject())) {
                r = RUtils.limitAngleChange(new Rotation(p.rotationYaw, p.rotationPitch), RUtils.toRotation(RUtils.getCenter(target.getEntityBoundingBox()), true), aimSpeedAimed.getObject());
                r.yaw = p.rotationYaw - r.yaw;
                r.pitch = p.rotationPitch - r.pitch;
                r.yaw += RandomUtils.nextFloat(-yawRandoAimed.getObject(), yawRandoAimed.getObject());
                r.pitch += RandomUtils.nextFloat(-pitchRandoAimed.getObject(), pitchRandoAimed.getObject());

            } else {
                r.yaw = p.rotationYaw - r.yaw;
                r.pitch = p.rotationPitch - r.pitch;
                r.yaw += RandomUtils.nextFloat(-yawRando.getObject(), yawRando.getObject());
                r.pitch += RandomUtils.nextFloat(-pitchRando.getObject(), pitchRando.getObject());

            }
            p.rotationYaw -= r.yaw;
            p.rotationPitch -= r.pitch;
        } else if (aimMode.getObject() == 1) {

        } else if (aimMode.getObject() == 2) {

        } else if (aimMode.getObject() == 3) {

        }

        if (debug2.getObject())
            ChatUtils.info(rando + " " + ticks + " " + (ticks >= rando));

        ticks++;
        if (ticks >= rando) {
            ticks = 0;
            //if(RaycastUtils.raycastEntity(aimRange.getObject(), RaycastUtils.IEntityFilter))
            if (RUtils.isFaced(target, aimRange.getObject()))
                p.connection.getNetworkManager().sendPacket(new CPacketUseEntity(target));
            p.connection.getNetworkManager().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
            if (debug1.getObject())
                ChatUtils.info(((clickMax.getObject() - clickMin.getObject()) / 2) + " " + clickMin.getObject() + " " + clickMax.getObject());
            if (RandomUtils.nextInt(0, 4) == 0) {
                int ra = 20 - RandomUtils.nextInt(clickMin.getObject() + ((clickMax.getObject() - clickMin.getObject()) / 2), clickMax.getObject());
                if (debug3.getObject())
                    ChatUtils.info("A " + ra);
                rando = ra;
            } else if (RandomUtils.nextInt(0, 2) == 0) {
                int ra = 20 - RandomUtils.nextInt(clickMin.getObject(), clickMax.getObject() - ((clickMax.getObject() - clickMin.getObject()) / 2));
                if (debug3.getObject())
                    ChatUtils.info("B " + ra);
                rando = ra;
            } else {
                int ra = 20 - RandomUtils.nextInt(clickMin.getObject(), clickMax.getObject());
                if (debug3.getObject())
                    ChatUtils.info("C " + ra);
                rando = ra;
            }
        }
    }
}

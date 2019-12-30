package uwu.smsgamer.PasteClient.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.utils.EntityUtils;
import uwu.smsgamer.PasteClient.utils.RUtils;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class AimBot1 extends Module {
    private BooleanValue stopAim = new BooleanValue("StopAim", true);
    private BooleanValue mouseOnly = new BooleanValue("MouseOnly", true);
    private NumberValue<Integer> updateDirTicks = new NumberValue<>("UpdateDirectionTicks", 1, 1, 20);
    private NumberValue<Float> aimRange = new NumberValue<>("Range", 4f, 0f, 10f);
    private NumberValue<Float> yawSpeed = new NumberValue<>("YawSpeed", 4f, 0f, 180f);
    private NumberValue<Float> yawRandom = new NumberValue<>("YawRandom", 4f, 0f, 180f);
    private NumberValue<Float> pitchSpeed = new NumberValue<>("PitchSpeed", 4f, 0f, 180f);
    private NumberValue<Float> pitchRandom = new NumberValue<>("PitchRandom", 4f, 0f, 180f);
    private BooleanValue debug1 = new BooleanValue("Debug1", false);

    public AimBot1() {
        super("AimBot1", "Literally any aimbot, just better. 7/10 detectable", ModuleCategory.COMBAT);
    }

    private int ticks;
    private boolean yaw;
    private boolean pitch;

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        ticks++;
        if (!getState())
            return;
        if(mc.mouseHelper.deltaX == 0 && mc.mouseHelper.deltaY == 0 && mouseOnly.getObject()){
            return;
        }
        EntityPlayerSP p = mc.player;
        EntityLivingBase target = EntityUtils.getClosestEntity();
        float range = mc.player.getDistanceToEntity(target);
        if (range > aimRange.getObject())
            return;
        if (mc.objectMouseOver.entityHit != null && stopAim.getObject())
            return;
        float[] r = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true), 1, 1);
        r[0] = p.rotationYaw - r[0];
        r[1] = p.rotationPitch - r[1];
        if (ticks % updateDirTicks.getObject() == 0) {
            yaw = r[0] < 0;
            pitch = r[1] < 0;
        }
        if (debug1.getObject())
            ChatUtils.info(yaw + " " + pitch);
        if (yaw) {
            p.rotationYaw += (Math.random() * (yawSpeed.getObject() + yawRandom.getObject())) - yawRandom.getObject() / 2;
        } else {
            p.rotationYaw -= (Math.random() * (yawSpeed.getObject() + yawRandom.getObject())) - yawRandom.getObject() / 2;
        }
        if (pitch) {
            p.rotationPitch += (Math.random() * (pitchSpeed.getObject() + pitchRandom.getObject())) - pitchRandom.getObject() / 2;
        } else {
            p.rotationPitch -= (Math.random() * (pitchSpeed.getObject() + pitchRandom.getObject())) - pitchRandom.getObject() / 2;
        }
        if (p.rotationPitch < -90) {
            p.rotationPitch = -90;
        }
        if (p.rotationPitch > 90) {
            p.rotationPitch = 90;
        }
    }
}

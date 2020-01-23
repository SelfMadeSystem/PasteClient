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
    private NumberValue<Float> aimRange = new NumberValue<>("Range", 4f, 0f, 10f);
    private BooleanValue yawAlwaysRandom = new BooleanValue("YawAlwaysRandom", true);
    private NumberValue<Float> yawSpeed = new NumberValue<>("YawSpeed", 4f, 0f, 90f);
    private NumberValue<Float> yawRandom = new NumberValue<>("YawRandom", 4f, 0f, 90f);
    private BooleanValue pitchAlwaysRandom = new BooleanValue("PitchAlwaysRandom", true);
    private NumberValue<Float> pitchSpeed = new NumberValue<>("PitchSpeed", 4f, 0f, 90f);
    private NumberValue<Float> pitchRandom = new NumberValue<>("PitchRandom", 4f, 0f, 90f);

    public AimBot1() {
        super("AimBot1", "Literally any aimbot, just better. 7/10 detectable", ModuleCategory.COMBAT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (!getState())
            return;
        if (mc.mouseHelper.deltaX == 0 && mc.mouseHelper.deltaY == 0 && mouseOnly.getObject()) {
            return;
        }
        EntityPlayerSP p = mc.player;
        EntityLivingBase target = EntityUtils.getClosestEntity();
        float range = mc.player.getDistanceToEntity(target);
        if (range > aimRange.getObject())
            return;
        if (mc.objectMouseOver.entityHit != null && stopAim.getObject())
            return;
        float[] r = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true),
                (float) (yawSpeed.getObject() + (Math.random() * yawRandom.getObject()) - yawRandom.getObject() / 2),
                (float) (pitchSpeed.getObject() + (Math.random() * pitchRandom.getObject()) - pitchRandom.getObject() / 2));
        if(yawAlwaysRandom.getObject())
            r[0] = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true),
                    (float) (Math.random() * (yawSpeed.getObject() + yawRandom.getObject())) - yawRandom.getObject() / 2,
                    (float) (Math.random() * (pitchSpeed.getObject() + pitchRandom.getObject())) - pitchRandom.getObject() / 2)[0];
        if(pitchAlwaysRandom.getObject())
            r[1] = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true),
                    (float) (Math.random() * (yawSpeed.getObject() + yawRandom.getObject())) - yawRandom.getObject() / 2,
                    (float) (Math.random() * (pitchSpeed.getObject() + pitchRandom.getObject())) - pitchRandom.getObject() / 2)[1];
        //r[0] = p.rotationYaw - r[0];
        //r[1] = p.rotationPitch - r[1];
        p.rotationYaw = r[0];
        p.rotationPitch = r[1];
        if (p.rotationPitch < -90) {
            p.rotationPitch = -90;
        }
        if (p.rotationPitch > 90) {
            p.rotationPitch = 90;
        }
    }
}

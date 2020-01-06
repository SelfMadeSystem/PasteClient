package uwu.smsgamer.PasteClient.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.events.QuickEvent;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.fileSystem.Filer;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.utils.EntityUtils;
import uwu.smsgamer.PasteClient.utils.RUtils;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

import java.util.Arrays;
import java.util.Random;

public class AimBot3 extends Module {
    private BooleanValue aimSave = new BooleanValue("AimSavingValues", false);
    private BooleanValue aimSaveAbs = new BooleanValue("AimSaveAbs", false);

    private BooleanValue aimYaw = new BooleanValue("AimYaw", true);
    private BooleanValue aimYawNeg = new BooleanValue("AimYawNeg", false);
    private BooleanValue aimPitch = new BooleanValue("AimPitch", true);
    private BooleanValue aimPitchNeg = new BooleanValue("AimPitchNeg", false);

    private NumberValue<Float> aimRange = new NumberValue<>("AimRange", 4f, 0f, 10f);
    private BooleanValue stopAim = new BooleanValue("StopAim", true);

    private NumberValue<Float> aimYawBTimes = new NumberValue<>("AimYawBTimes", 1.2f, 1f, 5f); // Before multiplier
    private NumberValue<Float> aimYawBTimesRand = new NumberValue<>("AimYawBTimesRand", 0.1f, 0f, 4f); // Before multiplier randomizer
    private NumberValue<Float> aimPitchBTimes = new NumberValue<>("AimPitchBTimes", 1.2f, 1f, 5f);
    private NumberValue<Float> aimPitchBTimesRand = new NumberValue<>("AimPitchBTimesRand", 0.1f, 0f, 4f);

    private NumberValue<Float> aimYawBDivide = new NumberValue<>("AimYawBDivide", 0.9f, 0f, 1f); // Before multiplier
    private NumberValue<Float> aimYawBDivideRand = new NumberValue<>("AimYawBDivideRand", 0.1f, 0f, 4f); // Before multiplier randomizer
    private NumberValue<Float> aimPitchBDivide = new NumberValue<>("AimPitchBDivide", 0.9f, 0f, 1f);
    private NumberValue<Float> aimPitchBDivideRand = new NumberValue<>("AimPitchBDivideRand", 0.1f, 0f, 4f);

    private NumberValue<Float> aimYawMax = new NumberValue<>("AimYawMax", 3f, 0f, 180f); // Maximum value for Yaw.
    private NumberValue<Float> aimPitchMax = new NumberValue<>("AimPitchMax", 3f, 0f, 180f); // Maximum value for Pitch.

    private NumberValue<Float> aimYawMin = new NumberValue<>("AimYawMin", 3f, 0f, 180f); // Minimum value for Yaw.
    private NumberValue<Float> aimPitchMin = new NumberValue<>("AimPitchMin", 3f, 0f, 180f); // Minimum value for Pitch.

    private NumberValue<Float> aimYawATimes = new NumberValue<>("AimYawATimes", 1.2f, 0f, 5f); // After multiplier
    private NumberValue<Float> aimYawATimesRand = new NumberValue<>("AimYawATimesRand", 0.1f, 0f, 4f); // After multiplier randomizer
    private NumberValue<Float> aimPitchATimes = new NumberValue<>("AimPitchATimes", 1.2f, 0f, 5f);
    private NumberValue<Float> aimPitchATimesRand = new NumberValue<>("AimPitchATimesRand", 0.1f, 0f, 4f);

    private BooleanValue aimDebug1 = new BooleanValue("AimDebug1", false);
    private BooleanValue aimDebug2 = new BooleanValue("AimDebug2", false);
    private BooleanValue aimDebug3 = new BooleanValue("AimDebug3", false);

    public AimBot3() {
        super("AimBot3", "Aimbot that aims, but stops when aimed. 2/10 detectable", ModuleCategory.COMBAT);
    }

    /*public boolean getBool(String smth) {
        return Fusion3.theClient.setmgr.getSetting(this, "Aim" + smth).getValBoolean();
    }

    public double getDouble(String smth) {
        return Fusion3.theClient.setmgr.getSetting(this, "Aim" + smth).getValDouble();
    }*/

    private static float prevYaw;
    private static float prevPitch;
    private static int line;

    @EventTarget
    public void update(UpdateEvent event) {
        if (!getState())
            return;
        EntityPlayerSP p = mc.player;
        Filer aimbot = new Filer("AimBot", ClientBase.CLIENT_NAME);
        //ChatUtils.info(aimSavingValues") + " " + aimbot.read().get(line) + " " + Arrays.toString(aimbot.read().get(line).split(":")));
        if (aimSave.getObject()) {
            //ChatUtils.info("fuck");
            if ((Math.abs(p.rotationYaw - prevYaw)) != p.rotationYaw && (Math.abs(p.rotationYaw - prevYaw)) != 0) {
                if (aimSaveAbs.getObject()) {
                    aimbot.write((Math.abs(p.rotationYaw - prevYaw)) + ":" + (Math.abs(p.rotationPitch - prevPitch)));
                } else {
                    EntityLivingBase target = EntityUtils.getClosestEntity();
                    if (target == null) {
                        ChatUtils.info("You need to be near an entity.");
                        return;
                    }
                    float range = p.getDistanceToEntity(target);
                    if (range > aimRange.getObject()) {
                        ChatUtils.info("You need to be near an entity.");
                        return;
                    }
                    float[] r = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true), 1, 1);
                    r[0] = p.rotationYaw - r[0];
                    r[1] = p.rotationPitch - r[1];
                    float yaw;
                    float pitch;
                    if (aimDebug1.getObject())
                        acm(Arrays.toString(r));
                    if (r[0] > 0) {
                        yaw = p.rotationYaw - prevYaw;
                    } else {
                        yaw = -(p.rotationYaw - prevYaw);
                    }
                    if (r[1] > 0) {
                        pitch = p.rotationPitch - prevPitch;
                    } else {
                        pitch = -(p.rotationPitch - prevPitch);
                    }

                    aimbot.write(yaw + ":" + pitch);
                }
            }
            prevYaw = p.rotationYaw;
            prevPitch = p.rotationPitch;
        } else {
            EntityLivingBase target = EntityUtils.getClosestEntity();
            float range = mc.player.getDistanceToEntity(target);
            if (range > aimRange.getObject())
                return;
            String ln = aimbot.read().get(line);
            if (mc.objectMouseOver.entityHit != null)
                return;
            float yaw = Float.parseFloat(ln.split(":")[0]);
            float pitch = Float.parseFloat(ln.split(":")[1]);
            float[] r;
            try {
                r = RUtils.limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, RUtils.getNeededRotations(RUtils.getCenter(target.getEntityBoundingBox()), true), 1, 1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Murder me, please...");
                return;
            }
            r[0] = p.rotationYaw - r[0];
            r[1] = p.rotationPitch - r[1];
            int times = 0;
            while (Math.abs(yaw) > aimYawMax.getObject()) {
                yaw *= (aimYawBDivide.getObject() + (Math.random() * aimYawBDivideRand.getObject() - (aimYawBDivideRand.getObject() / 2)));
                times++;
                if (times >= 50)
                    break;
            }
            times = 0;
            while (Math.abs(pitch) > aimPitchMax.getObject()) {
                pitch *= (aimPitchBDivide.getObject() + (Math.random() * aimPitchBDivideRand.getObject() - (aimPitchBDivideRand.getObject() / 2)));
                times++;
                if (times >= 50)
                    break;
            }
            times = 0;
            while (Math.abs(yaw) < aimYawMin.getObject()) {
                yaw *= (aimYawBTimes.getObject() + (Math.random() * aimYawBTimesRand.getObject() - (aimYawBTimesRand.getObject() / 2)));
                times++;
                if (times >= 50)
                    break;
            }
            times = 0;
            while (Math.abs(pitch) < aimPitchMin.getObject()) {
                pitch *= (aimPitchBTimes.getObject() + (Math.random() * aimPitchBTimesRand.getObject() - (aimPitchBTimesRand.getObject() / 2)));
                times++;
                if (times >= 50)
                    break;
            }

            yaw *= (aimYawATimes.getObject() + (Math.random() * aimYawATimesRand.getObject() - (aimYawATimesRand.getObject() / 2)));
            pitch *= (aimPitchATimes.getObject() + (Math.random() * aimPitchATimesRand.getObject() - (aimPitchATimesRand.getObject() / 2)));
            if (aimDebug1.getObject())
                acm(Arrays.toString(r));
            if (aimDebug2.getObject())
                acm(pitch + " " + yaw);
            if (r[0] > 0 && aimYaw.getObject() || (!aimYaw.getObject() && new Random().nextBoolean())) {
                if (aimYawNeg.getObject())
                    p.rotationYaw += yaw;
                else
                    p.rotationYaw -= yaw;
            } else {
                if (aimYawNeg.getObject())
                    p.rotationYaw -= yaw;
                else
                    p.rotationYaw += yaw;
            }
            if (r[1] > 0 && aimPitch.getObject() || (!aimPitch.getObject() && new Random().nextBoolean())) {
                if (aimPitchNeg.getObject())
                    p.rotationPitch += pitch;
                else
                    p.rotationPitch -= pitch;
            } else {
                if (aimPitchNeg.getObject())
                    p.rotationPitch -= pitch;
                else
                    p.rotationPitch += pitch;
            }
            line++;
            //ChatUtils.info(aimbot.read().size() + " " + line);
            if (aimbot.read().size() <= line)
                line = 0;
        }
        if (p.rotationPitch < -90) {
            p.rotationPitch = -90;
        }
        if (p.rotationPitch > 90) {
            p.rotationPitch = 90;
        }
    }

    private static void acm(String msg) {
        ChatUtils.info(msg);
    }
}

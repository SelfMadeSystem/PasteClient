package uwu.smsgamer.PasteClient.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class AimCheckA extends Module {
    public AimCheckA() {
        super("AimCheckA", "NO U LOL", ModuleCategory.COMBAT);
    }

    private EntityPlayerSP p;

    private float prevYaw;
    private float aim1Avg;
    private float aim2Avg;
    private List<Float> aim1 = new ArrayList<>();
    private List<Float> aim2 = new ArrayList<>();

    @Override
    protected void onEnable() {
        aim1.add(0, 0f);
        aim1.add(1, 0f);
        aim1.add(2, 0f);
        aim1.add(3, 0f);
        aim1.add(4, 0f);
        aim2.add(0, 0f);
        aim2.add(1, 0f);
        aim2.add(2, 0f);
        aim2.add(3, 0f);
        aim2.add(4, 0f);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        try {
            if (!getState()) {
                //prevYaw = p.rotationYaw;
                return;
            }
            p = mc.player;
            if (prevYaw == p.rotationYaw && p.motionX == 0 && p.motionZ == 0)
                return;
            if (mc.objectMouseOver.entityHit == null) {
                //for (int i = 5; i >= 1; i--) {
                  //  aim1.add(i, aim1.get(i - 1));
                //}
                aim1.add(0, Math.abs(p.rotationYaw - prevYaw));
                if (aim1.size() > 5) {
                    aim1.remove(5);
                }
                aim1Avg = (aim1.get(0) + aim1.get(1) + aim1.get(2) + aim1.get(3) + aim1.get(4)) / 5;
            } else {
                aim2.add(0, Math.abs(p.rotationYaw - prevYaw));
                if (aim2.size() > 5) {
                    aim2.remove(5);
                }
                aim2Avg = (aim2.get(0) + aim2.get(1) + aim2.get(2) + aim2.get(3) + aim2.get(4)) / 5;
            }
            ChatUtils.send(aim1Avg + "\n" + aim2Avg);
            System.out.println(aim1 + " " + aim2);
            prevYaw = p.rotationYaw;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

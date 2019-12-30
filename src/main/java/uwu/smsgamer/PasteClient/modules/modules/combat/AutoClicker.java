package uwu.smsgamer.PasteClient.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.RandomUtils;
import uwu.smsgamer.PasteClient.utils.Timer;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class AutoClicker extends Module {
    private BooleanValue clickJitter = new BooleanValue("ClickJitter", false);
    private BooleanValue clickRight = new BooleanValue("ClickRight", false);
    private BooleanValue clickLeft = new BooleanValue("ClickLeft", true);
    private NumberValue<Integer> clickMin = new NumberValue<>("ClickMin", 0, 0, 19);
    private NumberValue<Integer> clickMax = new NumberValue<>("ClickMax", 1, 1, 20);

    public AutoClicker() {
        super("AutoClicker", "Automatically Clicks", ModuleCategory.COMBAT);
    }

    private long rightDelay;
    private long rightLastSwing;
    private long leftDelay;
    private long leftLastSwing;

    @Override
    protected void onEnable() {
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if(!getState())
            return;
        EntityPlayerSP p = mc.player;
        if (System.currentTimeMillis() - this.leftLastSwing >= this.leftDelay) {
            if (mc.gameSettings.keyBindAttack.isKeyDown() && clickLeft.getObject() && System.currentTimeMillis() - this.leftLastSwing >= this.leftDelay) {
                mc.clickMouse();
                this.leftLastSwing = System.currentTimeMillis();
                this.leftDelay = Timer.randomClickDelay(clickMin.getObject(), clickMax.getObject());
            }

            if (mc.gameSettings.keyBindUseItem.isKeyDown() && !p.isHandActive() && clickRight.getObject() && System.currentTimeMillis() - this.rightLastSwing >= this.rightDelay) {
                mc.rightClickMouse();
                this.rightLastSwing = System.currentTimeMillis();
                this.rightDelay = Timer.randomClickDelay(clickMin.getObject(), clickMax.getObject());
            }
        }

        if (clickJitter.getObject() && (clickLeft.getObject() && mc.gameSettings.keyBindAttack.isKeyDown() || clickRight.getObject() && mc.gameSettings.keyBindUseItem.isKeyDown() && !p.isHandActive())) {
            boolean yaw = RandomUtils.getRandom().nextBoolean();
            boolean pitch = RandomUtils.getRandom().nextBoolean();
            boolean yawNegative = RandomUtils.getRandom().nextBoolean();
            boolean pitchNegative = RandomUtils.getRandom().nextBoolean();

            if (yaw) {
                p.rotationYaw += yawNegative ? -RandomUtils.nextFloat(0.0F, 1.0F) : RandomUtils.nextFloat(0.0F, 1.0F);
            }

            if (pitch) {
                p.rotationPitch += pitchNegative ? -RandomUtils.nextFloat(0.0F, 1.0F) : RandomUtils.nextFloat(0.0F, 1.0F);
                if (p.rotationPitch > 90.0F) {
                    p.rotationPitch = 90.0F;
                } else if (p.rotationPitch < -90.0F) {
                    p.rotationPitch = -90.0F;
                }
            }
        }

    }
}

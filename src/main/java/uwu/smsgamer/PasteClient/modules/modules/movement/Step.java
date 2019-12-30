package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import uwu.smsgamer.PasteClient.events.StepConfirmEvent;
import uwu.smsgamer.PasteClient.events.StepEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Step extends Module {
    private NumberValue<Float> height = new NumberValue<>("StepHeight", 1f, 0f, 10f);
    private ModeValue mode = new ModeValue("Mode", "PacketNew", "PacketOld", "PacketNew", "NCP", "OldAAC", "Jump");
    private NumberValue<Integer> amount = new NumberValue<>("Amount", 7, 0, 10);

    public Step() {
        super("Step", "Steps up more than half a block. DOESN'T FUCKING WORK FOR SOME REASON.", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc.player == null)
            return;
        mc.player.stepHeight = height.getObject();
    }

    @Override
    public void onDisable() {
        if (mc.player == null)
            return;
        mc.player.stepHeight = 0.6f;
    }

    @EventTarget
    public void onStep(StepEvent e) {
        if (!getState())
            return;
        //ChatUtils.info(mode.getObject()+" "+e.stepHeight);
    }

    @EventTarget
    public void onStepConfirm(StepConfirmEvent e) {
        if (!getState())
            return;
        int amount = this.amount.getObject();
        EntityPlayerSP p = mc.player;
        ChatUtils.info(mode.getObject() + " " + amount);
        if (mode.getObject() == 0) {
        }
        if (mode.getObject() == 1) {
            if (amount >= 1)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 0.41999998688698, p.posZ, false));
            if (amount >= 2)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 0.7531999805212, p.posZ, false));
            if (amount >= 3)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 1.16610926093821, p.posZ, false));
            if (amount >= 4)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 1.24918707874468, p.posZ, false));
            if (amount >= 5)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 1.1767592750024, p.posZ, false));
            if (amount >= 6)
                p.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(p.posX, p.posY + 1.02442408821369, p.posZ, false));
        }
    }
    
    /*0.41999998688698
    0.7531999805212
    1.00133597911214
    1.16610926093821
    1.24918707874468
    1.1767592750024
    1.02442408821369
    0.79673560066871
    0.49520087700593
    0.1212968405392*/
}

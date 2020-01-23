/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.PasteClient.events.MotionUpdateEvent;
import uwu.smsgamer.PasteClient.events.PacketEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.notifications.Notification;
import uwu.smsgamer.PasteClient.notifications.NotificationManager;
import uwu.smsgamer.PasteClient.notifications.NotificationType;

import java.util.ArrayList;
import java.util.List;

public class SetbackDetector extends Module {
    public List<Vec3d> lastLocations = new ArrayList<>();
    public List<Long> lastSetBacks = new ArrayList<>();

    public SetbackDetector() {
        super("FlagDetector", "Detects flags", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onMove(MotionUpdateEvent event) {
        if (event.getEventType() != EventType.POST) return;

        List<Long> remove = new ArrayList<>();

        for (Long lastSetBack : lastSetBacks) {
            if (System.currentTimeMillis() - lastSetBack > 5000) {
                remove.add(lastSetBack);
            }
        }
        for (Long aLong : remove) {
            lastSetBacks.remove(aLong);
        }

//        System.out.println(lastSetBacks);

        lastLocations.add(new Vec3d(mc.player.posX, mc.player.posY, mc.player.posZ));

        while (lastLocations.size() > 30) {
            lastLocations.remove(0);
        }
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook p = (SPacketPlayerPosLook) event.getPacket();
            boolean setback = lastLocations.stream().anyMatch(loc -> p.getX() == loc.xCoord && p.getY() == loc.yCoord && p.getZ() == loc.zCoord);

            if (setback) {
                lastSetBacks.add(System.currentTimeMillis());
                if (lastSetBacks.size() < 3)
                    NotificationManager.show(new Notification(NotificationType.WARNING, getName(), "Flag detected", 1));
            }
        }
    }
}

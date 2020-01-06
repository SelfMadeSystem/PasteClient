package net.minecraft.util;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import uwu.smsgamer.PasteClient.events.QuickEvent;

public class Timer
{
    /**
     * How many full ticks have turned over since the last call to updateTimer(), capped at 10.
     */
    public int elapsedTicks;
    public float field_194147_b;
    public float field_194148_c;

    /**
     * The time reported by the system clock at the last sync, in milliseconds
     */
    private long lastSyncSysClock;
    public float offset;

    public Timer(float tps)
    {
        this.offset = 1000.0F / tps;
        this.lastSyncSysClock = Minecraft.getSystemTime();
    }

    /**
     * Updates all fields of the Timer using the current time
     */
    public void updateTimer()
    {
        final QuickEvent event = new QuickEvent();
        EventManager.call(event);
        long i = Minecraft.getSystemTime();
        this.field_194148_c = (float)(i - this.lastSyncSysClock) / this.offset;
        this.lastSyncSysClock = i;
        this.field_194147_b += this.field_194148_c;
        this.elapsedTicks = (int)this.field_194147_b;
        this.field_194147_b -= (float)this.elapsedTicks;
    }
}

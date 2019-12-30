// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.AltManager;

import java.util.ArrayList;

public class AltManager {
    public static Alt lastAlt;
    public static ArrayList<Alt> registry;

    static {
        AltManager.registry = new ArrayList<>();
    }

    public ArrayList<Alt> getRegistry() {
        return AltManager.registry;
    }

    public void setLastAlt(final Alt alt) {
        AltManager.lastAlt = alt;
    }
}

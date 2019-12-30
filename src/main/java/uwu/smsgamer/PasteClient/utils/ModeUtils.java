// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.utils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import uwu.smsgamer.PasteClient.modules.modules.misc.Targets;

public class ModeUtils {

    private static boolean getAuraP() {
        return Targets.players;
    }

    private static boolean getAuraM() {
        return Targets.mobs;
    }

    private static boolean getAuraA() {
        return Targets.animals;
    }

    public static boolean isValidForAura(final EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            return getAuraP();
        }
        if (entity instanceof EntityMob || entity instanceof EntitySlime) {
            return getAuraM();
        }
        return (entity instanceof EntityCreature || entity instanceof EntitySquid || entity instanceof EntityBat) && getAuraA();
    }
}

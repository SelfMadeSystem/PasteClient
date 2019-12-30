package uwu.smsgamer.PasteClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import uwu.smsgamer.PasteClient.modules.modules.misc.Targets;

public class EntityUtils {
    public static EntityLivingBase getClosestEntity() {
        WorldClient world = Minecraft.getMinecraft().world;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        EntityLivingBase closestEntity = null;
        for (final Object o : world.loadedEntityList) {
            if (o instanceof EntityLivingBase) {
                final EntityLivingBase en = (EntityLivingBase)o;
                boolean notABot;
                /*if (isAntiBot()) {
                    if (en instanceof EntityPlayer && en != player && en.isInvisible() && en.ticksExisted > 205) {
                        en.ticksExisted = -1;
                        en.setInvisible(en.isDead = true);
                    }
                    if (o instanceof EntityPlayer) {
                        final EntityPlayer o2 = (EntityPlayer)o;
                        if (o2.isSwingInProgress && !EntityUtils.invalid.contains(o2)) {
                            EntityUtils.invalid.add(o2);
                        }
                    }
                    notABot = (en.isEntityAlive() && en.ticksExisted > 100 && !en.isInvisible());
                    if (!EntityUtils.invalid.contains(o)) {
                        notABot = false;
                    }
                }*/
                //else {
                    notABot = true;
                //}
                boolean notTeammate = true;
                if (!Targets.teams && en instanceof EntityPlayer && (en.getDisplayName().toString().contains("§a") || !en.getDisplayName().toString().contains("§c"))) {
                    notTeammate = false;
                }
                if (en.getName().equals(player.getName()) || !ModeUtils.isValidForAura(en) || !notABot || (closestEntity != null && player.getDistanceToEntity(en) >= player.getDistanceToEntity(closestEntity))) {
                    continue;
                }
                closestEntity = en;
            }
        }
        //ChatUtils.info(closestEntity+"");
        return closestEntity;
    }
}

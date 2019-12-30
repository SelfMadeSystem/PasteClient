// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import uwu.smsgamer.PasteClient.events.Render3DEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.modules.modules.misc.Targets;
import uwu.smsgamer.PasteClient.utils.RenderUtils;

public class ESP extends Module {
    public ESP() {
        super("ESP", "Draws boxes round entities.", ModuleCategory.RENDER);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventTarget
    public void onRender3D(Render3DEvent e) {
        if (!getState())
            return;
        if (mc.ingameGUI != null) {
            return;
        }
        for (final Object theObject : mc.world.loadedEntityList) {
            if (!(theObject instanceof EntityLivingBase)) {
                continue;
            }
            final EntityLivingBase entity = (EntityLivingBase) theObject;
            if ((!entity.isEntityAlive() || entity.ticksExisted <= 100 || entity.isInvisible() || entity.getHealth() <= 0.0f)) {
                return;
            }
            if (entity instanceof EntityPlayer && Targets.players) {
                if (entity.posY - mc.player.posY == 3.609375) {
                    return;
                }
                if (entity == mc.player) {
                    continue;
                }
                this.player(entity);
            } else if (entity instanceof EntityMob && Targets.mobs) {
                this.mob(entity);
            } else {
                if (!(entity instanceof EntityAnimal) || !Targets.animals) {
                    continue;
                }
                this.animal(entity);
            }
        }
    }

    public void player(final EntityLivingBase entity) {
        final float red = 0.1f;
        final float green = 1.0f;
        final float blue = 1.0f;
        final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double xPos = n - RenderManager.renderPosX;
        final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double yPos = n2 - RenderManager.renderPosY;
        final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double zPos = n3 - RenderManager.renderPosZ;
        if (entity.hurtTime != 0) {
            this.render(255.0f, 0.0f, 0.0f, xPos, yPos, zPos, entity.width, entity.height);
        } else {
            this.render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
        }
    }

    public void mob(final EntityLivingBase entity) {
        final float red = 0.25f;
        final float green = 0.1f;
        final float blue = 1.0f;
        final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double xPos = n - RenderManager.renderPosX;
        final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double yPos = n2 - RenderManager.renderPosY;
        final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double zPos = n3 - RenderManager.renderPosZ;
        if (entity.hurtTime != 0) {
            this.render(255.0f, green, blue, xPos, yPos, zPos, entity.width, entity.height + entity.height / 5.0f);
        } else {
            this.render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height + entity.height / 5.0f);
        }
    }

    public void animal(final EntityLivingBase entity) {
        final float red = 0.0f;
        final float green = 0.5f;
        final float blue = 0.5f;
        final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double xPos = n - RenderManager.renderPosX;
        final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double yPos = n2 - RenderManager.renderPosY;
        final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.elapsedTicks;
        mc.getRenderManager();
        final double zPos = n3 - RenderManager.renderPosZ;
        RenderUtils.drawEsp(entity, 0.0f, 0, 0);
        if (entity.hurtTime != 0) {
            this.render(255.0f, green, blue, xPos, yPos, zPos, entity.width, entity.height);
        } else {
            this.render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
        }
    }

    public void render(final float red, final float green, final float blue, final double x, final double y, final double z, final float width, final float height) {
        RenderUtils.drawEntityESP(x, y, z, width, height, red, green, blue, 0.11f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    static String removeDuplicates(final String s) {
        final StringBuilder noDupes = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final String si = s.substring(i, i + 1);
            if (noDupes.indexOf(si) == -1) {
                noDupes.append(si);
            }
        }
        return noDupes.toString();
    }
}

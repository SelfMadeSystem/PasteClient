// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtils {

    public static int transparency(final int color, final float alpha) {
        final Color c = new Color(color);
        final float r = 0.003921569f * c.getRed();
        final float g = 0.003921569f * c.getGreen();
        final float b = 0.003921569f * c.getBlue();
        return new Color(r, g, b, alpha).getRGB();
    }

    public static void drawRect(final int x, final int y, final int x1, final int y1, final int color) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        Gui.drawRect(x, y, x1, y1, color);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void entityESPBox(final Entity entity, final int mode) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        if (mode == 0) {
            GL11.glColor4d(1.0f - Minecraft.getMinecraft().player.getDistanceToEntity(entity) / 40.0f, Minecraft.getMinecraft().player.getDistanceToEntity(entity) / 40.0f, 0.0, 0.5);
        } else if (mode == 1) {
            GL11.glColor4d(0.0, 0.0, 1.0, 0.5);
        } else if (mode == 2) {
            GL11.glColor4d(1.0, 1.0, 0.0, 0.5);
        } else if (mode == 3) {
            GL11.glColor4d(1.0, 0.0, 0.0, 0.5);
        } else if (mode == 4) {
            GL11.glColor4d(0.0, 1.0, 0.0, 0.5);
        }
        Minecraft.getMinecraft().getRenderManager();
        final double n = entity.boundingBox.minX - 0.05 - entity.posX;
        final double posX = entity.posX;
        Minecraft.getMinecraft().getRenderManager();
        final double x1 = n + (posX - RenderManager.renderPosX);
        final double n2 = entity.boundingBox.minY - entity.posY;
        final double posY = entity.posY;
        Minecraft.getMinecraft().getRenderManager();
        final double y1 = n2 + (posY - RenderManager.renderPosY);
        final double n3 = entity.boundingBox.minZ - 0.05 - entity.posZ;
        final double posZ = entity.posZ;
        Minecraft.getMinecraft().getRenderManager();
        final double z1 = n3 + (posZ - RenderManager.renderPosZ);
        final double n4 = entity.boundingBox.maxX + 0.05 - entity.posX;
        final double posX2 = entity.posX;
        Minecraft.getMinecraft().getRenderManager();
        final double x2 = n4 + (posX2 - RenderManager.renderPosX);
        final double n5 = entity.boundingBox.maxY + 0.1 - entity.posY;
        final double posY2 = entity.posY;
        Minecraft.getMinecraft().getRenderManager();
        final double y2 = n5 + (posY2 - RenderManager.renderPosY);
        final double n6 = entity.boundingBox.maxZ + 0.05 - entity.posZ;
        final double posZ2 = entity.posZ;
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x1, y1, z1, x2, y2, n6 + (posZ2 - RenderManager.renderPosZ)), -1, -1 ,-1 ,-1);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x1, y1, z1, x2, y2, n6 + (posZ2 - RenderManager.renderPosZ)), -1, -1 ,-1 ,-1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    public static void drawEsp(final EntityLivingBase ent, final float pTicks, final int hexColor, final int hexColorIn) {
        if (!ent.isEntityAlive()) {
            return;
        }
        final double x = getDiff(ent.lastTickPosX, ent.posX, pTicks, RenderManager.renderPosX);
        final double y = getDiff(ent.lastTickPosY, ent.posY, pTicks, RenderManager.renderPosY);
        final double z = getDiff(ent.lastTickPosZ, ent.posZ, pTicks, RenderManager.renderPosZ);
        boundingBox(ent, x, y, z, hexColor, hexColorIn);
    }

    public static void nukerBox(final BlockPos blockPos, final float damage) {
        final double n = blockPos.getX();
        Minecraft.getMinecraft().getRenderManager();
        final double x = n - RenderManager.renderPosX;
        final double n2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double y = n2 - RenderManager.renderPosY;
        final double n3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double z = n3 - RenderManager.renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glColor4d(damage, 1.0f - damage, 0.0, 0.15000000596046448);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x + 0.5 - damage / 2.0f, y + 0.5 - damage / 2.0f, z + 0.5 - damage / 2.0f, x + 0.5 + damage / 2.0f, y + 0.5 + damage / 2.0f, z + 0.5 + damage / 2.0f));
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x + 0.5 - damage / 2.0f, y + 0.5 - damage / 2.0f, z + 0.5 - damage / 2.0f, x + 0.5 + damage / 2.0f, y + 0.5 + damage / 2.0f, z + 0.5 + damage / 2.0f), -1, -1 ,-1 ,-1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    public static void searchBox(final BlockPos blockPos) {
        final double n = blockPos.getX();
        Minecraft.getMinecraft().getRenderManager();
        final double x = n - RenderManager.renderPosX;
        final double n2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double y = n2 - RenderManager.renderPosY;
        final double n3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double z = n3 - RenderManager.renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        final float sinus = 1.0f - MathHelper.abs(MathHelper.sin(Minecraft.getSystemTime() % 10000L / 10000.0f * 3.1415927f * 4.0f) * 1.0f);
        GL11.glColor4d(1.0f - sinus, sinus, 0.0, 0.15);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -1, -1 ,-1 ,-1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    private static double getDiff(final double lastI, final double i, final float ticks, final double ownI) {
        return lastI + (i - lastI) * ticks - ownI;
    }

    public static void boundingBox(final Entity entity, final double x, final double y, final double z, final int color, final int colorIn) {
        GlStateManager.pushMatrix();
        GL11.glLineWidth(1.0f);
        final AxisAlignedBB var11 = entity.getEntityBoundingBox();
        final AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - entity.posX + x, var11.minY - entity.posY + y, var11.minZ - entity.posZ + z, var11.maxX - entity.posX + x, var11.maxY - entity.posY + y, var11.maxZ - entity.posZ + z);
        if (color != 0) {
            GlStateManager.disableDepth();
            filledBox(var12, colorIn, true);
            disableLighting();
            Color color1 = new Color(color);
            RenderGlobal.drawSelectionBoundingBox(var12, color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
        }
        GlStateManager.popMatrix();
    }

    public static void disableLighting() {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
    }

    public static void filledBox(final AxisAlignedBB aa, final int color, final boolean shouldColor) {
        GlStateManager.pushMatrix();
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        final Tessellator var15 = Tessellator.getInstance();
        final BufferBuilder t = var15.getBuffer();
        if (shouldColor) {
            GlStateManager.color(var12, var13, var14, var11);
        }
        final byte draw = 7;
        t.begin(7, new VertexFormat());
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.endVertex();
        var15.draw();
        t.begin(7, new VertexFormat());
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        var15.draw();
        t.begin(7, new VertexFormat());
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        var15.draw();
        t.begin(7, new VertexFormat());
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        var15.draw();
        t.begin(7, new VertexFormat());
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        var15.draw();
        t.begin(7, new VertexFormat());
        t.pos(aa.minX, aa.maxY, aa.maxZ);
        t.pos(aa.minX, aa.minY, aa.maxZ);
        t.pos(aa.minX, aa.maxY, aa.minZ);
        t.pos(aa.minX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.minZ);
        t.pos(aa.maxX, aa.minY, aa.minZ);
        t.pos(aa.maxX, aa.maxY, aa.maxZ);
        t.pos(aa.maxX, aa.minY, aa.maxZ);
        var15.draw();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }

    public static void drawSelectionBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        BufferBuilder.begin(3, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        tessellator.draw();
        BufferBuilder.begin(3, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        tessellator.draw();
        BufferBuilder.begin(1, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        tessellator.draw();
    }

    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        tessellator.draw();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        tessellator.draw();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        tessellator.draw();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        tessellator.draw();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        tessellator.draw();
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.maxZ);
        BufferBuilder.pos(aa.minX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.minX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.minZ);
        BufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ);
        BufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ);
        tessellator.draw();
    }

    public static void drawOutlinedBlockESP(final double x, final double y, final double z, final float red, final float green, final float blue, final float alpha, final float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(red, green, blue, alpha);
        drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawBlockESP(final double x, final double y, final double z, final float red, final float green, final float blue, final float alpha, final float lineRed, final float lineGreen, final float lineBlue, final float lineAlpha, final float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidBlockESP(final double x, final double y, final double z, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawSelectionBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha, final float lineRed, final float lineGreen, final float lineBlue, final float lineAlpha, final float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawSelectionBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawTracerLine(final double x, final double y, final double z, final float red, final float green, final float blue, final float alpha, final float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, 0.0 + Minecraft.getMinecraft().player.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawCircle(final int x, final int y, final double r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawFilledCircle(final int x, final int y, final double r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void dr(double i, double j, double k, double l, final int i1) {
        if (i < k) {
            final double j2 = i;
            i = k;
            k = j2;
        }
        if (j < l) {
            final double k2 = j;
            j = l;
            l = k2;
        }
        final float f = (i1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (i1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (i1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (i1 & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        BufferBuilder.begin(7, new VertexFormat());
        BufferBuilder.pos(i, l, 0.0);
        BufferBuilder.pos(k, l, 0.0);
        BufferBuilder.pos(k, j, 0.0);
        BufferBuilder.pos(i, j, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawColorBox(final AxisAlignedBB axisalignedbb) {
        final Tessellator ts = Tessellator.getInstance();
        final BufferBuilder wr = ts.getBuffer();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        ts.draw();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        ts.draw();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        ts.draw();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        ts.draw();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        ts.draw();
        wr.begin(7, new VertexFormat());
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        ts.draw();
    }
}

package uwu.smsgamer.Paste;

import java.awt.Color;
import java.util.Comparator;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HudRenderer extends GuiIngame {
	
	public HudRenderer(Minecraft mcIn) {
		super(mcIn);
	}
	
	int count = 0;
    int MWidth;
	
	public void renderGameOverlay(float particalTicks) {
		super.renderGameOverlay(particalTicks);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        FontRenderer fontrenderer = mc.fontRendererObj;
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        
        drawRect(2, 2, 115, 22, 0xB3202020);
        GL11.glScalef(2f, 2f, 2f);
        mc.fontRendererObj.drawStringWithShadow("PasteClient", 2, 2, 0xffffffff);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        
        int[] counter = {1};
        int [] yDist2 = {24};
        PasteClient.instance.moduleManager.getModules().stream().filter(module -> module.isToggled()).sorted(Comparator.comparingInt(module -> -fontrenderer.getStringWidth(module.getDisplayName()))).forEach(module -> {
            mc.fontRendererObj.drawString(module.getDisplayName(), 2, yDist2[0], 0xffffffff, false); // 2
            count++;
            yDist2[0] += fontrenderer.FONT_HEIGHT;
            counter[0]++;
        });
	}
}

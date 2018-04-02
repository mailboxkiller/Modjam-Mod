package com.sandvoxel.immersivemagic.client.buttons;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AffinityButtons extends GuiButton {

    private int id = 0;
    private EntityPlayer player;
    final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/gui/affinity_list.png");
    private float texXStart = 0f;
    private float texYStart = 0f;

    public AffinityButtons(int buttonId, int x, int y, EntityPlayer player) {
        super(buttonId, x, y, "");
        id = buttonId;
        width = 16;
        height =16;
        visible = true;
        this.player = player;
    }

    public int getButtonID() {
        return id;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        IAffinities affinities = player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            texXStart = (float)id * 16.0f;
            //this.drawTexturedModalRect(this.x, this.y, texXStart, texYStart, this.width, this.height);
            this.drawModalRectWithCustomSizedTexture(this.x, this.y, texXStart, texYStart, this.width, this.height, 128.0f, 16.0f);
            //this.drawTexturedModalRect(this.x + this.width, this.y, texXStart, texYStart, this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            if(affinities.hasAffinity(AffinityTypes.getAffinity(id))){

            } else {
                this.drawGradientRect((int)texXStart - 2, (int)texYStart - 2, this.width + 2, this.height + 2, 10526880, 14737632);
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        super.mousePressed(mc, mouseX, mouseY);
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
}

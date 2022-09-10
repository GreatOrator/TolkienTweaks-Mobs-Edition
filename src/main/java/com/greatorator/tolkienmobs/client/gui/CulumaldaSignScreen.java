package com.greatorator.tolkienmobs.client.gui;

import com.greatorator.tolkienmobs.client.render.tile.RenderCulumaldaSignTile;
import com.greatorator.tolkienmobs.entity.tile.CulumaldaSignTile;
import com.greatorator.tolkienmobs.network.TTMNetwork;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.stream.IntStream;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class CulumaldaSignScreen extends Screen {
    private final RenderCulumaldaSignTile.SignModel signModel = new RenderCulumaldaSignTile.SignModel();
    private final CulumaldaSignTile sign;
    private int frame;
    private int line;
    private TextInputUtil signField;
    private final String[] messages;

    public CulumaldaSignScreen(CulumaldaSignTile p_i1097_1_) {
        super(new TranslationTextComponent("sign.edit"));
        IntStream var10001 = IntStream.range(0, 4);
        p_i1097_1_.getClass();
        this.messages = (String[])var10001.mapToObj(p_i1097_1_::getMessage).map(ITextComponent::getString).toArray((p_243354_0_) -> {
            return new String[p_243354_0_];
        });
        this.sign = p_i1097_1_;
    }

    @Override
    protected void init() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, DialogTexts.GUI_DONE, (p_238847_1_) -> {
            this.onDone();
        }));
        this.sign.setEditable(false);
        this.signField = new TextInputUtil(() -> {
            return this.messages[this.line];
        }, (p_238850_1_) -> {
            this.messages[this.line] = p_238850_1_;
            this.sign.setMessage(this.line, new StringTextComponent(p_238850_1_));
        }, TextInputUtil.createClipboardGetter(this.minecraft), TextInputUtil.createClipboardSetter(this.minecraft), (p_238848_1_) -> {
            return this.minecraft.font.width(p_238848_1_) <= 90;
        });
    }

    @Override
    public void removed() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
        ClientPlayNetHandler clientplaynethandler = this.minecraft.getConnection();
        if (clientplaynethandler != null) {
            TTMNetwork.sendSignUpdate(this.sign.getBlockPos(), this.messages[0], this.messages[1], this.messages[2], this.messages[3]);
        }

        this.sign.setEditable(true);
    }

    @Override
    public void tick() {
        ++this.frame;
        if (!this.sign.getType().isValid(this.sign.getBlockState().getBlock())) {
            this.onDone();
        }

    }

    private void onDone() {
        this.sign.setChanged();
        this.minecraft.setScreen((Screen)null);
    }

    @Override
    public boolean charTyped(char p_231042_1_, int p_231042_2_) {
        this.signField.charTyped(p_231042_1_);
        return true;
    }

    @Override
    public void onClose() {
        this.onDone();
    }

    @Override
    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        if (p_231046_1_ == 265) {
            this.line = this.line - 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        } else if (p_231046_1_ != 264 && p_231046_1_ != 257 && p_231046_1_ != 335) {
            return this.signField.keyPressed(p_231046_1_) ? true : super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
        } else {
            this.line = this.line + 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        }
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        RenderHelper.setupForFlatItems();
        this.renderBackground(p_230430_1_);
        drawCenteredString(p_230430_1_, this.font, this.title, this.width / 2, 40, 16777215);
        p_230430_1_.pushPose();
        p_230430_1_.translate((double)(this.width / 2), 0.0, 50.0);
        float f = 93.75F;
        p_230430_1_.scale(93.75F, -93.75F, 93.75F);
        p_230430_1_.translate(0.0, -1.3125, 0.0);
        BlockState blockstate = this.sign.getBlockState();
        boolean flag = blockstate.getBlock() instanceof StandingSignBlock;
        if (!flag) {
            p_230430_1_.translate(0.0, -0.3125, 0.0);
        }

        boolean flag1 = this.frame / 6 % 2 == 0;
        float f1 = 0.6666667F;
        p_230430_1_.pushPose();
        p_230430_1_.scale(0.6666667F, -0.6666667F, -0.6666667F);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = this.minecraft.renderBuffers().bufferSource();
        RenderMaterial rendermaterial = SignTileEntityRenderer.getMaterial(blockstate.getBlock());
        RenderCulumaldaSignTile.SignModel var10002 = this.signModel;
        var10002.getClass();
        IVertexBuilder ivertexbuilder = rendermaterial.buffer(irendertypebuffer$impl, var10002::renderType);
        this.signModel.sign.render(p_230430_1_, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY);
        if (flag) {
            this.signModel.stick.render(p_230430_1_, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY);
        }

        p_230430_1_.popPose();
        float f2 = 0.010416667F;
        p_230430_1_.translate(0.0, 0.3333333432674408, 0.046666666865348816);
        p_230430_1_.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = this.sign.getColor().getTextColor();
        int j = this.signField.getCursorPos();
        int k = this.signField.getSelectionPos();
        int l = this.line * 10 - this.messages.length * 5;
        Matrix4f matrix4f = p_230430_1_.last().pose();

        int i1;
        String s1;
        int k3;
        int l3;
        for(i1 = 0; i1 < this.messages.length; ++i1) {
            s1 = this.messages[i1];
            if (s1 != null) {
                if (this.font.isBidirectional()) {
                    s1 = this.font.bidirectionalShaping(s1);
                }

                float f3 = (float)(-this.minecraft.font.width(s1) / 2);
                this.minecraft.font.drawInBatch(s1, f3, (float)(i1 * 10 - this.messages.length * 5), i, false, matrix4f, irendertypebuffer$impl, false, 0, 15728880, false);
                if (i1 == this.line && j >= 0 && flag1) {
                    k3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
                    l3 = k3 - this.minecraft.font.width(s1) / 2;
                    if (j >= s1.length()) {
                        this.minecraft.font.drawInBatch("_", (float)l3, (float)l, i, false, matrix4f, irendertypebuffer$impl, false, 0, 15728880, false);
                    }
                }
            }
        }

        irendertypebuffer$impl.endBatch();

        for(i1 = 0; i1 < this.messages.length; ++i1) {
            s1 = this.messages[i1];
            if (s1 != null && i1 == this.line && j >= 0) {
                int j3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
                k3 = j3 - this.minecraft.font.width(s1) / 2;
                if (flag1 && j < s1.length()) {
                    fill(p_230430_1_, k3, l - 1, k3 + 1, l + 9, -16777216 | i);
                }

                if (k != j) {
                    l3 = Math.min(j, k);
                    int l1 = Math.max(j, k);
                    int i2 = this.minecraft.font.width(s1.substring(0, l3)) - this.minecraft.font.width(s1) / 2;
                    int j2 = this.minecraft.font.width(s1.substring(0, l1)) - this.minecraft.font.width(s1) / 2;
                    int k2 = Math.min(i2, j2);
                    int l2 = Math.max(i2, j2);
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferbuilder = tessellator.getBuilder();
                    RenderSystem.disableTexture();
                    RenderSystem.enableColorLogicOp();
                    RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    bufferbuilder.vertex(matrix4f, (float)k2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)l2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)l2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)k2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.end();
                    WorldVertexBufferUploader.end(bufferbuilder);
                    RenderSystem.disableColorLogicOp();
                    RenderSystem.enableTexture();
                }
            }
        }

        p_230430_1_.popPose();
        RenderHelper.setupFor3DItems();
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}

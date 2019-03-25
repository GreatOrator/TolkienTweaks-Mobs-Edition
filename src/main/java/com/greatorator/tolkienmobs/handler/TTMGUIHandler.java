package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.guis.GuiTMFireplace;
import com.greatorator.tolkienmobs.tile.TileTMFireplace;
import com.greatorator.tolkienmobs.tile.container.ContainerTMFireplace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TTMGUIHandler {
    public class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            if (ID == TolkienMobs.GUI_TMFIREPLACE)
                return new ContainerTMFireplace(player.inventory, (TileTMFireplace) world.getTileEntity(new BlockPos(x, y, z)));
            return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            if (ID == TolkienMobs.GUI_TMFIREPLACE)
                return new GuiTMFireplace(player.inventory, (TileTMFireplace) world.getTileEntity(new BlockPos(x, y, z)));
            return null;
        }
    }
}

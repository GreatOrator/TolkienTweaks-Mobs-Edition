//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.gui.GuiTMFireplace;
//import com.greatorator.tolkienmobs.tile.TileTMFireplace;
//import com.greatorator.tolkienmobs.tileentity.container.ContainerTMFireplace;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.network.IGuiHandler;
//import net.minecraftforge.fml.common.network.NetworkRegistry;
//
//public class TTMGUIHandler implements IGuiHandler {
//
//    public static final TTMGUIHandler instance = new TTMGUIHandler();
//    public static final int GUI_TMFIREPLACE = 1; //This belongs here
//
//    public static void initialize() {
//        NetworkRegistry.INSTANCE.registerGuiHandler(TolkienMobs.instance, instance);
//    }
//
//    @Override
//    public Object getServerGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z) {
//        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
//
//        if (ID == TTMGUIHandler.GUI_TMFIREPLACE && tile instanceof TileTMFireplace) {
//            return new ContainerTMFireplace(player, (TileTMFireplace) tile);
//        }
//        return null;
//    }
//
//    @Override
//    public Object getClientGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z) {
//        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
//
//        if (ID == TTMGUIHandler.GUI_TMFIREPLACE && tile instanceof TileTMFireplace) {
//            return new GuiTMFireplace(player, (TileTMFireplace) tile);
//        }
//        return null;
//    }
//}

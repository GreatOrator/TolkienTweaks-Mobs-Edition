package com.greatorator.tolkienmobs.commands;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMStructureSummon;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeBarrow;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeHobbitGrocer;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeHobbitHouse;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeRuin;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class TTMCommandSpawn extends CommandBase {
    private final List<String> aliases = Lists.newArrayList(TolkienMobs.MODID, "TMSpawn", "tmspawn", "tms", "TMS");

    @Override
    public String getName() {
        return "tmstructurespawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tmspawn.usage";
    }

    @Override
    public List<String> getAliases()
    {
        return aliases;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {

        if (argString.length <= 3) {
            sender.sendMessage(new TextComponentTranslation("commands.not_enough_parameters").setStyle(new Style().setColor(TextFormatting.RED)));
            throw new WrongUsageException("commands.tmspawn.usage", new Object[0]);
        }
        else {
            String structureName = argString[0];
            BlockPos blockpos = parseBlockPos(sender, argString, 1, false);
            int x = blockpos.getX();
            int y = blockpos.getY();
            int z = blockpos.getZ();

            if (argString[1] == null) {
                x = sender.getPosition().getX();
            }

            if (argString[2] == null) {
                y = sender.getPosition().getY();
            }

            if (argString[3] == null) {
                z = sender.getPosition().getZ();
            }

            if ("barrows".equals(argString[0])) {
                generateStructure(sender, sender.getEntityWorld(), x, y, z, WorldGenBiomeBarrow.INSTANCE);
            }
            else if ("hobbithouse".equals(argString[0])) {
                generateStructure(sender, sender.getEntityWorld(), x, y, z, WorldGenBiomeHobbitHouse.INSTANCE);
            }
            else if ("hobbitgrocer".equals(argString[0])) {
                generateStructure(sender, sender.getEntityWorld(), x, y, z, WorldGenBiomeHobbitGrocer.INSTANCE);
            }
            else if ("ruins".equals(argString[0])) {
                generateStructure(sender, sender.getEntityWorld(), x, y, z, WorldGenBiomeRuin.INSTANCE);
            }
            else {
                LogHelperTTM.info("Structure name or coordinates invalid, you sent " + structureName + "at position" + x + "," + y + "," + z + ".");
                sender.sendMessage(new TextComponentTranslation("commands.generate.unknown_structure").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, new String[] {"barrows", "hobbithouse", "hobbitgrocer", "ruins"});
        }
        else if (args.length > 1 && args.length <= 4) {
                return getTabCompletionCoordinate(args, 1, targetPos);
        }
        return Collections.emptyList();
    }

    private static void generateStructure(ICommandSender sender, World world, int x, int y, int z, ITTMStructureSummon structure) {
        BlockPos blockpos = new BlockPos(x,y,z);
        structure.generate(world, world.rand, blockpos);
    }
}

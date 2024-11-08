package tfar.erogare.datagen.data;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModBlocks;

public class ModBlockLoot extends VanillaBlockLoot {
    @Override
    protected void generate() {
        dropSelf(ModBlocks.MYSTERIOUS_FLESH);
        dropSelf(ModBlocks.RAW_CODE);
        dropSelf(ModBlocks.WATCHING_FLESH);
        add(ModBlocks.DARKNESS, noDrop());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Erogare.getKnownBlocks().toList();
    }
}

package tfar.erogare.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModBlocks;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Erogare.MOD_ID, exFileHelper);
    }
    ModelFile.ExistingModelFile cubic  = models().getExistingFile(modLoc("block/cubic"));

    @Override
    protected void registerStatesAndModels() {
        ModelFile.ExistingModelFile existingFile = models().getExistingFile(modLoc("block/darkness"));
        getVariantBuilder(ModBlocks.DARKNESS)
                .partialState().setModels(ConfiguredModel.builder().modelFile(existingFile).build());


        cubic(ModBlocks.RAW_CODE);
        cubic(ModBlocks.MYSTERIOUS_FLESH);
        cubic(ModBlocks.WATCHING_FLESH);

        //getVariantBuilder(ModBlocks.RAW_CODE).partialState().setModels(ConfiguredModel.builder().modelFile(cubic).build());
    }

    protected void cubic(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        ModelFile raw_code = models().getBuilder(name).parent(cubic).texture("1",modLoc("block/"+name))
                .texture("particle",modLoc("block/"+name));
        simpleBlock(block,raw_code);
    }

}

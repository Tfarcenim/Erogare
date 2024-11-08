package tfar.erogare.datagen;

import net.minecraft.data.PackOutput;
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

    @Override
    protected void registerStatesAndModels() {
        ModelFile.ExistingModelFile existingFile = models().getExistingFile(modLoc("block/darkness"));
        getVariantBuilder(ModBlocks.DARKNESS)
                .partialState().setModels(ConfiguredModel.builder().modelFile(existingFile).build());
    }
}

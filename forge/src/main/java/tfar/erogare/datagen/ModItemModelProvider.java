package tfar.erogare.datagen;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModBlocks;
import tfar.erogare.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) {
        super(output, Erogare.MOD_ID, existingFileHelper);
    }

    ModelFile.ExistingModelFile GENERATED = getExistingFile(mcLoc("item/generated"));
    ModelFile.ExistingModelFile HANDHELD = getExistingFile(mcLoc("item/handheld"));

    @Override
    protected void registerModels() {

        Erogare.getKnownItems().filter(item -> {
            return !(item instanceof BlockItem) && item != ModItems.CODE_SWORD_OP && item != ModItems.CODE_SWORD && item != ModItems.CODE_SHIELD
                    && item != ModItems.SHATTERED_BLADE && item != ModItems.SHATTERED_HILT && item != ModItems.SACRIFICIAL_BLADE;
        }).forEach(this::makeOneLayerItem);
        //getBuilder("code_sword_op").parent(getExistingFile(modLoc("item/code_sword")));
        makeSimpleBlockItem(ModBlocks.RAW_CODE.asItem());
        makeSimpleBlockItem(ModBlocks.WATCHING_FLESH.asItem());
        makeSimpleBlockItem(ModBlocks.MYSTERIOUS_FLESH.asItem());

        makeOneLayerItem(ModItems.SACRIFICIAL_BLADE,HANDHELD);

        specialModels();
    }


    protected void specialModels() {
        perspectiveModel("code_shield");
        perspectiveModel("code_sword");
        perspectiveModel("shattered_blade");
        perspectiveModel("shattered_hilt");
        getBuilder("code_sword_op").parent(new ModelFile.UncheckedModelFile(modLoc("item/code_sword")));

    }
    protected ItemModelBuilder makeSpriteModel(String name) {
        return getBuilder("item/" + name+"_sprite")
                .parent(GENERATED)
                .texture("layer0", "item/" + name+"_sprite");

    }
    private void perspectiveModel(String name) {
        ItemModelBuilder r3dFile = nested()
                .parent(getExistingFile(modLoc("item/" + name+"_3d")));

        ItemModelBuilder rSpriteFile = makeSpriteModel(name);

        getBuilder(name).guiLight(BlockModel.GuiLight.FRONT)
                .customLoader(SeparateTransformsModelBuilder::begin).base(rSpriteFile)
                .perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, r3dFile)
                .perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, r3dFile)
                .perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, r3dFile)
                .perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, r3dFile)
                .end();
    }

    protected void makeSimpleBlockItem(Item item, ResourceLocation loc) {
        String s = BuiltInRegistries.ITEM.getKey(item).toString();
        getBuilder(s)
                .parent(getExistingFile(loc));
    }

    protected void makeSimpleBlockItem(Item item) {
        makeSimpleBlockItem(item, Erogare.id("block/" + BuiltInRegistries.ITEM.getKey(item).getPath()));
    }


    protected void makeOneLayerItem(Item item, ResourceLocation texture) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists( texture,PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(GENERATED)
                    .texture("layer0", texture);
        } else {
            System.out.println("no texture " + texture+" found, skipping");
        }
    }

    protected void makeOneLayerItem(Item item, ResourceLocation texture,ModelFile file) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists( texture,PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(file)
                    .texture("layer0", texture);
        } else {
            System.out.println("no texture " + texture+" found, skipping");
        }
    }

    protected void makeOneLayerItem(Item item,ModelFile file) {
        ResourceLocation texture = BuiltInRegistries.ITEM.getKey(item);
        makeOneLayerItem(item, texture.withPrefix("item/"),file);
    }

    protected void makeOneLayerItem(Item item) {
        ResourceLocation texture = BuiltInRegistries.ITEM.getKey(item);
        makeOneLayerItem(item, texture.withPrefix("item/"));
    }
}

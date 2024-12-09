package tfar.erogare.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModBlocks;
import tfar.erogare.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) {
        super(output, Erogare.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        Erogare.getKnownItems().filter(item -> {
            return !(item instanceof BlockItem) && item != ModItems.CODE_SWORD_OP && item != ModItems.CODE_SWORD;
        }).forEach(this::makeOneLayerItem);

        getBuilder("code_sword_op").parent(getExistingFile(modLoc("item/code_sword")));

        makeSimpleBlockItem(ModBlocks.RAW_CODE.asItem());
        makeSimpleBlockItem(ModBlocks.WATCHING_FLESH.asItem());
        makeSimpleBlockItem(ModBlocks.MYSTERIOUS_FLESH.asItem());
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
            getBuilder(path).parent(getExistingFile(mcLoc("item/generated")))
                    .texture("layer0", texture);
        } else {
            System.out.println("no texture " + texture+" found, skipping");
        }
    }

    protected void makeOneLayerItem(Item item) {
        ResourceLocation texture = BuiltInRegistries.ITEM.getKey(item);
        makeOneLayerItem(item, texture.withPrefix("item/"));
    }
}

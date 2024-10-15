package tfar.erogare.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) {
        super(output, Erogare.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        makeOneLayerItem(ModItems.MEDAL_OF_ATROX);
        makeOneLayerItem(ModItems.MEDAL_OF_CONTENTIO);
        makeOneLayerItem(ModItems.MEDAL_OF_OBSCURUS);
        makeOneLayerItem(ModItems.MEDAL_OF_OCCIDERE);
        makeOneLayerItem(ModItems.MEDAL_OF_VICTORIA);

        makeOneLayerItem(ModItems.OVOS_CARD_RANK_1);
        makeOneLayerItem(ModItems.OVOS_CARD_RANK_2);
        makeOneLayerItem(ModItems.OVOS_CARD_RANK_3);
        makeOneLayerItem(ModItems.OVOS_CARD_RANK_4);
        makeOneLayerItem(ModItems.OVOS_CARD_RANK_5);
        makeOneLayerItem(ModItems.OVOS_CARD_RANK_6);
        makeOneLayerItem(ModItems.OVOS_BUSINESS_CARD);
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

package tfar.erogare.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModCreativeTabs;
import tfar.erogare.init.ModItems;

import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, Erogare.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(() -> ModItems.MEDAL_OF_ATROX,"Medal of Atrox");
        addItem(() -> ModItems.MEDAL_OF_CONTENTIO,"Medal of Contentio");
        addItem(() -> ModItems.MEDAL_OF_OBSCURUS,"Medal of Obscurus");
        addItem(() -> ModItems.MEDAL_OF_OCCIDERE,"Medal of Occidere");
        addItem(() -> ModItems.MEDAL_OF_VICTORIA,"Medal of Victoria");

        addItem(() -> ModItems.OVOS_CARD_RANK_1,"OVOS Card Rank 1");
        addItem(() -> ModItems.OVOS_CARD_RANK_2,"OVOS Card Rank 2");
        addItem(() -> ModItems.OVOS_CARD_RANK_3,"OVOS Card Rank 3");
        addItem(() -> ModItems.OVOS_CARD_RANK_4,"OVOS Card Rank 4");
        addItem(() -> ModItems.OVOS_CARD_RANK_5,"OVOS Card Rank 5");
        addItem(() -> ModItems.OVOS_CARD_RANK_6,"OVOS Card Rank 6");
        addItem(() -> ModItems.OVOS_BUSINESS_CARD,"OVOS Business Card");

        addTranslatableComponent(ModCreativeTabs.TITLE,Erogare.MOD_NAME);
    }


    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier,getNameFromBlock(supplier.get()));
    }

    protected void addDefaultEnchantment(Supplier<? extends Enchantment> supplier) {
        addEnchantment(supplier,getNameFromEnchantment(supplier.get()));
    }

    protected void addDefaultEntityType(Supplier<EntityType<?>> supplier) {
        addEntityType(supplier,getNameFromEntity(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEnchantment(Enchantment enchantment) {
        return StringUtils.capitaliseAllWords(enchantment.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEntity(EntityType<?> entity) {
        return StringUtils.capitaliseAllWords(entity.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    protected void addTranslatableComponent(MutableComponent component, String text) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(),text);
        } else {
            throw new UnsupportedOperationException(component +" is not translatable");
        }
    }
}

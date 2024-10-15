package tfar.erogare.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.CreativeModeTab;
import tfar.erogare.Erogare;

public class ModCreativeTabs {
    public static final MutableComponent TITLE = Component.translatable("itemGroup.erogare");
    public static final CreativeModeTab TAB = CreativeModeTab.builder(null,-1)
            .title(TITLE)
            .icon(ModItems.OVOS_BUSINESS_CARD::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                BuiltInRegistries.ITEM.stream().filter(item -> BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(Erogare.MOD_ID))
                        .forEach(output::accept);
            })
            .build();
}

package tfar.erogare.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tfar.erogare.Erogare;

import java.util.ArrayList;
import java.util.List;

public class ModCreativeTabs {
    public static final MutableComponent TITLE = Component.translatable("itemGroup.erogare");
    public static final CreativeModeTab TAB = CreativeModeTab.builder(null,-1)
            .title(TITLE)
            .icon(ModItems.OVOS_BUSINESS_CARD::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                output.acceptAll(
                BuiltInRegistries.ITEM.stream().filter(item -> BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(Erogare.MOD_ID) && item != ModItems.ID_CARD)
                        .map(Item::getDefaultInstance)
                        .toList());
                output.acceptAll(buildIdList());
            })
            .build();

    static List<ItemStack> buildIdList() {
        List<ItemStack> stacks = new ArrayList<>(ModItems.IDS.length);
        for (String id : ModItems.IDS) {
            ItemStack stack = ModItems.ID_CARD.getDefaultInstance();
            Erogare.addLore(stack,Component.literal(id));
            stacks.add(stack);
        }
        return stacks;
    }

}

package tfar.erogare.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LoreItem extends Item {

    final Component lore;

    public LoreItem(Properties $$0, Component lore) {
        super($$0);
        this.lore = lore;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        listTag.add(StringTag.valueOf(Component.Serializer.toJson(lore)));
        tag.put(ItemStack.TAG_LORE,listTag);

        stack.getOrCreateTag().put(ItemStack.TAG_DISPLAY,tag);
        return stack;
    }
}

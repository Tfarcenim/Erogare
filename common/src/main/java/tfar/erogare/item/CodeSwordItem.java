package tfar.erogare.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import tfar.erogare.Erogare;

public class CodeSwordItem extends SwordItem {
    public CodeSwordItem(Tier $$0, int $$1, float $$2, Properties $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        Erogare.addLore(stack, Component.literal("Wrought into two."));
        return stack;
    }
}

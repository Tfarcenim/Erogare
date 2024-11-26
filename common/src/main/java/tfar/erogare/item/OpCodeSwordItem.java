package tfar.erogare.item;

import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import tfar.erogare.init.ModItems;

public class OpCodeSwordItem extends SwordItem {
    public OpCodeSwordItem(Tier $$0, int $$1, float $$2, Properties $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Override
    public boolean hurtEnemy(ItemStack $$0, LivingEntity entity, LivingEntity attacker) {
        if (attacker.getRandom().nextBoolean() && !entity.getUseItem().is(ModItems.CODE_SHIELD)) {
            ItemStack stack = entity.getMainHandItem();
            Containers.dropItemStack(entity.level(),attacker.getX(),attacker.getY(),attacker.getZ(),stack);
            entity.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
        }
        return super.hurtEnemy($$0, entity, attacker);
    }
}

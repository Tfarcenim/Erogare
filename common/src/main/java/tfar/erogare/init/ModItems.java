package tfar.erogare.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item MEDAL_OF_ATROX = new Item(new Item.Properties());
    public static final Item MEDAL_OF_CONTENTIO = new Item(new Item.Properties());
    public static final Item MEDAL_OF_OBSCURUS = new Item(new Item.Properties());
    public static final Item MEDAL_OF_OCCIDERE = new Item(new Item.Properties());
    public static final Item MEDAL_OF_VICTORIA = new Item(new Item.Properties());

    public static final Item OVOS_CARD_RANK_1 = new Item(new Item.Properties());
    public static final Item OVOS_CARD_RANK_2 = new Item(new Item.Properties());
    public static final Item OVOS_CARD_RANK_3 = new Item(new Item.Properties());
    public static final Item OVOS_CARD_RANK_4 = new Item(new Item.Properties());
    public static final Item OVOS_CARD_RANK_5 = new Item(new Item.Properties());
    public static final Item OVOS_CARD_RANK_6 = new Item(new Item.Properties());
    public static final Item OVOS_BUSINESS_CARD = new Item(new Item.Properties());

    public static final Item GREEN_MYSTERIOUS_SUBSTANCE = new Item(new Item.Properties().food(
            new FoodProperties.Builder().effect(new MobEffectInstance(ModMobEffects.CORRUPTED,MobEffectInstance.INFINITE_DURATION,0,false,false), 1).build()));

    public static final Item BROWN_MYSTERIOUS_SUBSTANCE = new Item(new Item.Properties().food(
            new FoodProperties.Builder().effect(new MobEffectInstance(ModMobEffects.WATCHED,MobEffectInstance.INFINITE_DURATION,0,false,false), 1).build()));
}

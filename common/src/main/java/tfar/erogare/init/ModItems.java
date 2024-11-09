package tfar.erogare.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import tfar.erogare.item.OpCodeSwordItem;

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

    public static final Item CODE_SWORD_OP = new OpCodeSwordItem(Tiers.NETHERITE,27,-2.4f,new Item.Properties());
    public static final Item CODE_SWORD = new SwordItem(Tiers.NETHERITE,22,-2.4f,new Item.Properties());

    public static final Item MYSTERIOUS_FLESH = new BlockItem(ModBlocks.MYSTERIOUS_FLESH,new Item.Properties());
    public static final Item RAW_CODE = new BlockItem(ModBlocks.RAW_CODE,new Item.Properties());
    public static final Item WATCHING_FLESH = new BlockItem(ModBlocks.WATCHING_FLESH,new Item.Properties());
    public static final Item SHATTERED_HILT = new Item(new Item.Properties());
    public static final Item SHATTERED_BLADE = new Item(new Item.Properties());

}

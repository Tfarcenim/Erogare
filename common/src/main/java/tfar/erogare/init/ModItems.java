package tfar.erogare.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import tfar.erogare.item.CodeSwordItem;
import tfar.erogare.item.LoreItem;
import tfar.erogare.item.OpCodeSwordItem;

import java.util.Arrays;
import java.util.List;

public class ModItems {
    //cosmetics

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

    public static final Item Bear_Shaped_Chocolate = chocolate();
    public static final Item Dragonfly_Shaped_Chocolate = chocolate();
    public static final Item Butterfly_Shaped_Chocolate = chocolate();
    public static final Item Scorpion_Shaped_Chocolate = chocolate();
    public static final Item Beetle_Shaped_Chocolate = chocolate();

    public static final Item MYSTERIOUS_MOLT = new Item(new Item.Properties());
    public static final Item DISTRESSED_RED_BALL = new Item(new Item.Properties());

    public static final String[] IDS = new String[]{"BadBoyHalo","DanTDM","FitMC","FoolishGamers","Ironmouse","ItsFundy","JaidenAnimations","jschlatt",
            "Manepear","MissTrixtin","Philza","Skeppy","Slimecicle","Technoblade","Tinakitten","Tubbo_",
            "Wilbur","Carre","Doied","ElMariana","ElQuackity","German","Lenay","Luzuvlogs","Maximus3blog","Missasinfonia",
            "Polispol","Quackity","Riversgg","Roier","elrubius","SpreenDMC","VegettaGaymer","Willyrex","Cellbit","Felps",
            "Forever","mikethelink","oibagi","pactw","AntoineDaniel_","aypierre","BagheraJones","Etoiles","Kameto","Horty","악어","중력","콩콩","양띵","LetsHugo","Nihachu","Aimsey","aldo_geo","Axozer","Barcagamer","Bastet","Brunim", "Neets","Capitan", "Gato","Cherryrar","Condifiction","Crystalmolly","Duxo","Estailus","Ethan", "Nestor","Febatista","Gabepeixe","Goularte","Guaxinim","Guill","Guto","Himaru","Jack Manifold","Jimmyboyyy","Jinkiwinnki","JVNQ","KennyStream","Ljoga","Malena","Mynth0s_","Natalan","Rodezel","Scottonauta","Seapeekay","Shubble","Soarinng","Sneegsnag","Supninjaz","Tiba","VGumiho","Wuant"};

    //westernized names
    //악어/Acau
    //중력/Jungryeok
    //콩콩/KongKong
    //양띵/YD

    public static final Item ID_CARD = new Item(new Item.Properties());

    //functional

    public static final Item MYSTERIOUS_GEM = new LoreItem(new Item.Properties(),Component.literal("It hums with static."));

    public static final Item CODE_SWORD_OP = new OpCodeSwordItem(Tiers.NETHERITE,27,-2.4f,new Item.Properties());
    public static final Item CODE_SWORD = new CodeSwordItem(Tiers.NETHERITE,22,-2.4f,new Item.Properties());
    public static final Item SACRIFICIAL_BLADE = new SwordItem(Tiers.IRON,18,-2.4f,new Item.Properties());


    public static final Item MYSTERIOUS_FLESH = new BlockItem(ModBlocks.MYSTERIOUS_FLESH,new Item.Properties());
    public static final Item RAW_CODE = new BlockItem(ModBlocks.RAW_CODE,new Item.Properties());
    public static final Item WATCHING_FLESH = new BlockItem(ModBlocks.WATCHING_FLESH,new Item.Properties());
    public static final Item SHATTERED_HILT = new LoreItem(new Item.Properties(), Component.literal("This was something once"));
    public static final Item SHATTERED_BLADE = new LoreItem(new Item.Properties(), Component.literal("This was something once"));

    public static final Item CODE_SHIELD = new ShieldItem(new Item.Properties());

    public static final Item GREEN_MYSTERIOUS_SUBSTANCE = new LoreItem(new Item.Properties().food(
            new FoodProperties.Builder().effect(new MobEffectInstance(ModMobEffects.CORRUPTED,MobEffectInstance.INFINITE_DURATION,0,false,false), 1).build()),
            Component.literal("Staticky in your hands."));

    public static final Item BROWN_MYSTERIOUS_SUBSTANCE = new LoreItem(new Item.Properties().food(
            new FoodProperties.Builder().effect(new MobEffectInstance(ModMobEffects.WATCHED,MobEffectInstance.INFINITE_DURATION,0,false,false), 1).build()),
            Component.literal("You shouldn’t touch this."));

    static Item chocolate() {
        return new Item(new Item.Properties().food(Foods.DRIED_KELP));
    }

}

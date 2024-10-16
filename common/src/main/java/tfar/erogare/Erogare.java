package tfar.erogare;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import tfar.erogare.init.ModCreativeTabs;
import tfar.erogare.init.ModItems;
import tfar.erogare.init.ModMobEffects;
import tfar.erogare.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class Erogare {

    public static final String MOD_ID = "erogare";
    public static final String MOD_NAME = "Erogare";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        Services.PLATFORM.registerAll(ModMobEffects.class,BuiltInRegistries.MOB_EFFECT, MobEffect.class);
        Services.PLATFORM.registerAll(ModItems.class,BuiltInRegistries.ITEM, Item.class);
        Services.PLATFORM.registerAll(ModCreativeTabs.class,BuiltInRegistries.CREATIVE_MODE_TAB, CreativeModeTab.class);
    }

    public static ResourceLocation id(String path){
        return new ResourceLocation(MOD_ID,path);
    }
}
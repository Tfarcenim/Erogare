package tfar.erogare;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.erogare.client.ModClientForge;
import tfar.erogare.datagen.ModDatagen;
import tfar.erogare.init.ModMobEffects;
import tfar.erogare.network.client.S2CRemoveShaderPacket;
import tfar.erogare.network.client.S2CShaderPacket;
import tfar.erogare.platform.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(Erogare.MOD_ID)
public class ErogareForge {
    public static Map<Registry<?>, List<Pair<ResourceLocation, Supplier<?>>>> registerLater = new HashMap<>();

    public ErogareForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(ModDatagen::gather);
        bus.addListener(this::registerObjs);
        bus.addListener(this::onInitialize);
        if (FMLEnvironment.dist.isClient()) {
            ModClientForge.init(bus);
        }
        MinecraftForge.EVENT_BUS.addListener(this::effectAdded);
        MinecraftForge.EVENT_BUS.addListener(this::effectExpire);
        MinecraftForge.EVENT_BUS.addListener(this::effectRemove);
        // Use Forge to bootstrap the Common mod.
        Erogare.init();
        
    }

    void effectAdded(MobEffectEvent.Added event) {
        MobEffectInstance effectInstance = event.getEffectInstance();
        LivingEntity entity = event.getEntity();

        if (effectInstance.getEffect() == ModMobEffects.CORRUPTED) {
            //entity.addEffect(new MobEffectInstance(MobEffects.))
            if (entity instanceof ServerPlayer player) {
                Services.PLATFORM.sendToClient(new S2CShaderPacket(new ResourceLocation("shaders/post/creeper.json")), player);
            }
        } else if (effectInstance.getEffect() == ModMobEffects.WATCHED) {
            if (entity instanceof ServerPlayer player) {
                Services.PLATFORM.sendToClient(new S2CShaderPacket(new ResourceLocation("shaders/post/desaturated.json")), player);
            }
        }
    }

    void effectExpire(MobEffectEvent.Expired event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = event.getEffectInstance();
        if (entity instanceof ServerPlayer player) {
            checkAndClear(effectInstance,player);
        }
    }

    void effectRemove(MobEffectEvent.Remove event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = event.getEffectInstance();
        if (entity instanceof ServerPlayer player) {
            checkAndClear(effectInstance,player);
        }
    }

    void checkAndClear(MobEffectInstance mobEffectInstance,ServerPlayer player) {
        if (mobEffectInstance != null) {
            MobEffect mobEffect = mobEffectInstance.getEffect();
            if (mobEffect == ModMobEffects.CORRUPTED || mobEffect == ModMobEffects.WATCHED) {
                Services.PLATFORM.sendToClient(new S2CRemoveShaderPacket(), player);
            }
        }
    }

    public void registerObjs(RegisterEvent event) {
        for (Map.Entry<Registry<?>,List<Pair<ResourceLocation, Supplier<?>>>> entry : registerLater.entrySet()) {
            Registry<?> registry = entry.getKey();
            List<Pair<ResourceLocation, Supplier<?>>> toRegister = entry.getValue();
            for (Pair<ResourceLocation,Supplier<?>> pair : toRegister) {
                event.register((ResourceKey<? extends Registry<Object>>)registry.key(),pair.getLeft(),(Supplier<Object>)pair.getValue());
            }
        }
    }

    public void onInitialize(FMLCommonSetupEvent e) {
        registerLater.clear();
    }

}
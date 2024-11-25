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
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
import java.util.UUID;
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
        MobEffect effect = effectInstance.getEffect();
        addEffectSecondaries(entity,effectInstance);
        if (effectInstance.getAmplifier() > 2) {
            if (effect == ModMobEffects.CORRUPTED) {
                //entity.addEffect(new MobEffectInstance(MobEffects.))
                if (entity instanceof ServerPlayer player) {
                    Services.PLATFORM.sendToClient(new S2CShaderPacket(new ResourceLocation("shaders/post/creeper.json")), player);
                }
            } else if (effect == ModMobEffects.WATCHED) {
                if (entity instanceof ServerPlayer player) {
                    Services.PLATFORM.sendToClient(new S2CShaderPacket(new ResourceLocation("shaders/post/desaturated.json")), player);
                }
            }
        }
    }

    //Watched
    // Level 1
    //Strength 1
    //
    //Level 2
    //Strength 1, Speed 1
    //
    //Level 3
    //Strength 1, Speed 1, 0.03 attack speed increase
    //
    //Level 4
    //Strength 2, Speed 1, 0.05 attack speed increase + grayscale filter over screen'

    //Corrupted
    //Level 1
    //No effect
    //
    //Level 2
    //Strength 1
    //
    //Level 3
    //Strength 2
    //
    //Level 4
    //Resistance 1, Slowness 2, Strength 2 + Creeper filter over screen

    static final UUID WATCHED_BOOST = fromResourceLocation(Erogare.id("watched_boost"));

    static UUID fromResourceLocation(ResourceLocation id) {
        return new UUID(id.getNamespace().hashCode(),id.getPath().hashCode());
    }

    void addEffectSecondaries(LivingEntity entity,MobEffectInstance effectInstance) {
        MobEffect effect = effectInstance.getEffect();
        int amplifier = effectInstance.getAmplifier();
        if (effect == ModMobEffects.WATCHED) {
            MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST,effectInstance.getDuration(),amplifier / 4,effectInstance.isAmbient(),effectInstance.isVisible());
            entity.addEffect(strength);
            if(amplifier > 0) {
                MobEffectInstance speed = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effectInstance.getDuration(), 0, effectInstance.isAmbient(), effectInstance.isVisible());
                entity.addEffect(speed);
            }
            double attackSpeedBoost = switch (amplifier) {
                default -> 0;
                case 2 -> .03;
                case 3-> .05;
            };
            if (attackSpeedBoost > 0) {
                AttributeModifier modifier = new AttributeModifier(WATCHED_BOOST,"Watched Boost",attackSpeedBoost, AttributeModifier.Operation.ADDITION);
                addAttributeSafely(entity, Attributes.ATTACK_SPEED,modifier);
            }
        } else if (effect == ModMobEffects.CORRUPTED) {
            switch (amplifier) {
                default -> {}
                case 1 -> {
                    MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST, effectInstance.getDuration(), 0, effectInstance.isAmbient(), effectInstance.isVisible());
                    entity.addEffect(strength);
                }
                case 2 -> {
                    MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST, effectInstance.getDuration(), 1, effectInstance.isAmbient(), effectInstance.isVisible());
                    entity.addEffect(strength);
                }
                case 3 -> {
                    MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST, effectInstance.getDuration(), 1, effectInstance.isAmbient(), effectInstance.isVisible());
                    entity.addEffect(strength);

                    MobEffectInstance resistance = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, effectInstance.getDuration(), 0, effectInstance.isAmbient(), effectInstance.isVisible());
                    entity.addEffect(resistance);

                    MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, effectInstance.getDuration(), 1, effectInstance.isAmbient(), effectInstance.isVisible());
                    entity.addEffect(slowness);
                }
            }
        }
    }

    protected static boolean addAttributeSafely(LivingEntity entity, Attribute attribute, AttributeModifier modifier) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance == null) return false;
        if (attributeInstance.getModifier(modifier.getId()) != null) {
            attributeInstance.removeModifier(modifier.getId());
        }
        attributeInstance.addPermanentModifier(modifier);
        return true;
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
                player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(WATCHED_BOOST);
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
package tfar.erogare.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.IEventBus;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModMobEffects;

public class ModClientForge {
    protected static final ResourceLocation[] WATCHED_LOCATIONS = array("watched");
    protected static final ResourceLocation[] CORRUPTED_LOCATIONS = array("corrupted");

    static ResourceLocation[] array(String id) {
        int count = 4;
        ResourceLocation[] locations = new ResourceLocation[count];
        for (int i = 0; i < count;i++) {
            locations[i] = Erogare.id("textures/misc/"+id+"_"+i+".png");
        }
        return locations;
    }

    static final IGuiOverlay overlay = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player!= null) {
            if (player.hasEffect(ModMobEffects.WATCHED)) {
                int level = Math.min(player.getEffect(ModMobEffects.WATCHED).getAmplifier(), WATCHED_LOCATIONS.length-1);
                gui.renderTextureOverlay(guiGraphics, WATCHED_LOCATIONS[level], 1);
            }

            if (player.hasEffect(ModMobEffects.CORRUPTED)) {
                int level = Math.min(player.getEffect(ModMobEffects.CORRUPTED).getAmplifier(), CORRUPTED_LOCATIONS.length-1);
                gui.renderTextureOverlay(guiGraphics,CORRUPTED_LOCATIONS[level], 1);
            }
        }
    };

    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::customOverlay);
    }

    static void customOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HELMET.id(),"effect_overlay",overlay);
    }

}

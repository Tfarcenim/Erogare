package tfar.erogare.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.IEventBus;
import tfar.erogare.Erogare;
import tfar.erogare.ErogareForge;

public class ModClientForge {
    protected static final ResourceLocation WATCHED_LOCATION = Erogare.id("textures/misc/watched.png");

    static final IGuiOverlay overlay = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        gui.renderTextureOverlay(guiGraphics,WATCHED_LOCATION,1);
    };

    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::customOverlay);
    }

    static void customOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HELMET.id(),"corruption",overlay);
    }

}

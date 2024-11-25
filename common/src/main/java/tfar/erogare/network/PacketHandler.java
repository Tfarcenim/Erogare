package tfar.erogare.network;

import net.minecraft.resources.ResourceLocation;
import tfar.erogare.Erogare;
import tfar.erogare.network.client.S2CRemoveShaderPacket;
import tfar.erogare.network.client.S2CShaderPacket;
import tfar.erogare.platform.Services;


import java.util.Locale;

public class PacketHandler {

    public static void registerPackets() {
        Services.PLATFORM.registerClientPacket(S2CShaderPacket.class, S2CShaderPacket::new);
        Services.PLATFORM.registerClientPacket(S2CRemoveShaderPacket.class, S2CRemoveShaderPacket::new);
    }

    public static ResourceLocation packet(Class<?> clazz) {
        return Erogare.id(clazz.getName().toLowerCase(Locale.ROOT));
    }

}

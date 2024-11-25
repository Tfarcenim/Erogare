package tfar.erogare;

import net.minecraft.client.Minecraft;
import tfar.erogare.network.client.S2CRemoveShaderPacket;
import tfar.erogare.network.client.S2CShaderPacket;

public class ClientPacketHandler {
    public static void handleShaderPacket(S2CShaderPacket s2CShaderPacket) {
        Minecraft.getInstance().gameRenderer.loadEffect(s2CShaderPacket.shader());
    }

    public static void handleRemoveShaderPacket(S2CRemoveShaderPacket s2CRemoveShaderPacket) {
        Minecraft.getInstance().gameRenderer.shutdownEffect();
    }
}

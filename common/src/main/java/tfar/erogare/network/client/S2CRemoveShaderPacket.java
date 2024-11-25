package tfar.erogare.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.erogare.ClientPacketHandler;

public record S2CRemoveShaderPacket() implements S2CModPacket {

    public S2CRemoveShaderPacket(FriendlyByteBuf buf) {
        this();
    }

    @Override
    public void handleClient() {
        ClientPacketHandler.handleRemoveShaderPacket(this);
    }

    @Override
    public void write(FriendlyByteBuf to) {
        ;
    }
}

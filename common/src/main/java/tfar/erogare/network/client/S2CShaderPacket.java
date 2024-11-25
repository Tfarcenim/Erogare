package tfar.erogare.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.erogare.ClientPacketHandler;

public record S2CShaderPacket(ResourceLocation shader) implements S2CModPacket {

    public S2CShaderPacket(FriendlyByteBuf buf) {
        this(buf.readResourceLocation());
    }

    @Override
    public void handleClient() {
        ClientPacketHandler.handleShaderPacket(this);
    }

    @Override
    public void write(FriendlyByteBuf to) {
        to.writeResourceLocation(shader);
    }
}

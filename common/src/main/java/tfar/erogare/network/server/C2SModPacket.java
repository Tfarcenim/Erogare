package tfar.erogare.network.server;

import net.minecraft.server.level.ServerPlayer;
import tfar.erogare.network.ModPacket;

public interface C2SModPacket extends ModPacket {

    void handleServer(ServerPlayer player);

}

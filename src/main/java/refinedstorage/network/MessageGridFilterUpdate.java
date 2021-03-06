package refinedstorage.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import refinedstorage.container.ContainerGridFilter;
import refinedstorage.item.ItemGridFilter;

public class MessageGridFilterUpdate extends MessageHandlerPlayerToServer<MessageGridFilterUpdate> implements IMessage {
    private int compare;

    public MessageGridFilterUpdate() {
    }

    public MessageGridFilterUpdate(int compare) {
        this.compare = compare;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        compare = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(compare);
    }

    @Override
    public void handle(MessageGridFilterUpdate message, EntityPlayerMP player) {
        if (player.openContainer instanceof ContainerGridFilter) {
            ItemGridFilter.setCompare(((ContainerGridFilter) player.openContainer).getStack(), message.compare);
        }
    }
}

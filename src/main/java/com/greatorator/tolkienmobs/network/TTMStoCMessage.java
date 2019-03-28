package com.greatorator.tolkienmobs.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TTMStoCMessage implements IMessage{
	private byte[] data;
	
	public TTMStoCMessage()
	{
		this(new byte[]{0});
	}
	
	public TTMStoCMessage(ByteBuf dataToSet)
    {
        this(dataToSet.array());
    }

    public TTMStoCMessage(byte[] dataToSet)
    {
		if (dataToSet.length > 0x1ffff0)
        {
            throw new IllegalArgumentException("Payload may not be larger than 2097136 (0x1ffff0) bytes");
        }
        
        this.data = dataToSet;

    }

	@Override
	public void toBytes(ByteBuf buffer) {
		//System.out.println("Encoding General Packet!");
        
		if (data.length > 0x1ffff0)
        {
            throw new IllegalArgumentException("Payload may not be larger than 2097136 (0x1ffff0) bytes");
        }
		
        buffer.writeShort(this.data.length);
        buffer.writeBytes(this.data);
		
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		
		this.data = new byte[buffer.readShort()];
        buffer.readBytes(this.data);
	}
	
    public byte[] getData() {
        return this.data;
    }
}
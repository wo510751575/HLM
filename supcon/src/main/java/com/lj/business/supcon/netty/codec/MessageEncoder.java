package com.lj.business.supcon.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import com.lj.business.supcon.netty.common.MessageUtils;
import com.lj.business.supcon.netty.message.Message;

/**
 * 
 * 
 * 类说明：消息编码器
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   	
 * CreateDate: 2017年10月10日
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
		out.writeShort(message.getCode().getCode());	// 消息编码
		// 消息体
		if(message.getCode().isCompression()){  // 开启gzip压缩
			ByteBuf buf =  Unpooled.buffer();
			MessageUtils.writeUTF8(buf, message.getMsgId());			// 消息ID
			MessageUtils.writeUTF8ByLength(buf, message.getBody());		// 消息体
			byte[] content = new byte[buf.readableBytes()];
			buf.getBytes(0, content);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(content);
			gzip.close();
			byte[] destBytes = bos.toByteArray();
			out.writeInt(destBytes.length);
			out.writeBytes(destBytes);
			bos.close();
		}else{
			MessageUtils.writeUTF8(out, message.getMsgId());			// 消息ID
			MessageUtils.writeUTF8ByLength(out, message.getBody());		// 消息体
		}
	}

}

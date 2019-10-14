package com.lj.business.supcon.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.business.supcon.netty.common.MessageUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;

/**
 * 
 * 
 * 类说明：消息解码器
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年10月10日
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

	private final static Logger logger = LoggerFactory.getLogger(MessageDecoder.class);
	
	public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		try {
			logger.info("报文读状态：{}", in);
			ByteBuf frame = (ByteBuf) super.decode(ctx, in);
			if (frame == null || frame.readableBytes() <= 0) {
				/*logger.error("非法报文：{}", in);
				ByteBuf resultBuf = in.copy();
				int length = resultBuf.readableBytes();
				logger.error("length: " + length);
				byte[] content = new byte[length];
				resultBuf.readBytes(content);
				logger.error("非法报文内容 : {}", new String(content, "UTF-8"));*/
				return null;
			}
			short messageCode = frame.readShort();	// 消息编码
			Message message = MessageCode.createNewMessage(messageCode);	// 创建消息对象实例
			boolean useCompression = message.getCode().isCompression();
			ByteBuf realBuf = decompression(frame, useCompression);
			message.setMsgId(MessageUtils.readUTF8(realBuf, Message.MSGID_LENGTH));	// 消息ID
			message.setBody(MessageUtils.readUTF8ByLength(realBuf));	// 消息体
			return message;
		} catch(Exception e) {
			logger.error("解码失败:", e);
			throw e;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：解压缩
	 *
	 * @param sourceBuf
	 * @param useCompression
	 * @return
	 * @throws Exception
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月10日
	 *
	 */
	private ByteBuf decompression(ByteBuf sourceBuf, boolean useCompression) throws Exception {
		if (!useCompression) {
			return sourceBuf;
		}

		int bodyLength = sourceBuf.readInt(); 		// 先读压缩数据的长度
		byte[] sourceBytes = new byte[bodyLength];
		sourceBuf.readBytes(sourceBytes); 			// 得到压缩数据的字节数组

		// 解压缩
		ByteArrayInputStream bis = new ByteArrayInputStream(sourceBytes);
		GZIPInputStream gzip = new GZIPInputStream(bis);

		final int MAX_MSG_LENGTH = bodyLength * 2; // 假设压缩率最大为100%！！！！！
		byte[] content = new byte[MAX_MSG_LENGTH];
		int num = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((num = gzip.read(content, 0, content.length)) != -1) {
			baos.write(content, 0, num);
		}
		baos.flush();
		gzip.close();
		bis.close();

		// 重新封装成ByteBuf对象
		ByteBuf resultBuf = Unpooled.buffer();
		byte[] realBytes = baos.toByteArray(); // 压缩前的实际数据
		resultBuf.writeBytes(realBytes);
		baos.close();

		return resultBuf;
	}

}

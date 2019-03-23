package com.netty.sixthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Create by TaoTaoNing
 * 2019/3/23
 **/
public class ProtobufInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加四个 netty 对于protobuf 的四个支持handler
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // 这里的参数是 protobuf 生成的class文件的 外部类的getDefaultInstance
       // pipeline.addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance())); 只能传递一种数据类型时
        // 传递多种类型时
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.DataInfo.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());

        //添加自定义handler
        pipeline.addLast(new ProtobufServerHandler());

    }
}

package com.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * Create by TaoTaoNing
 * 2019/3/23
 **/
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.DataInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.DataInfo msg) throws Exception {

        switch (msg.getDataType()) {
            case PersonType:
                System.out.println(msg.getPerson().getName());
                System.out.println(msg.getPerson().getAeg());
                System.out.println(msg.getPerson().getAddress());
                break;
            case CatType:
                System.out.println(msg.getCat().getCatAge());
                System.out.println(msg.getCat().getCatName());
                break;
            case DogType:
                System.out.println(msg.getDog().getDogSex());
                System.out.println(msg.getDog().getDogName());
                break;
            default:
                System.out.println("不支持的消息类型！！");
                return;

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

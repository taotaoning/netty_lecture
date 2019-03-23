package com.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Create by TaoTaoNing
 * 2019/3/23
 **/
public class ProtobufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.DataInfo> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int random = new Random().nextInt(3);
        MyDataInfo.DataInfo dataInfo = null;
        if (0 == random){
            dataInfo = MyDataInfo.DataInfo.newBuilder()
                    .setDataType(MyDataInfo.DataInfo.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("王五").setAeg(23).setAddress("南昌").build())
                    .build();
        }else if (1 == random){
            dataInfo = MyDataInfo.DataInfo.newBuilder()
                    .setDataType(MyDataInfo.DataInfo.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setCatName("咪咪").setCatAge(2).build())
                    .build();
        }else {
            dataInfo = MyDataInfo.DataInfo.newBuilder()
                    .setDataType(MyDataInfo.DataInfo.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setDogName("旺财").setDogSex("公").build())
                    .build();
        }




        ctx.writeAndFlush(dataInfo);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.DataInfo msg) throws Exception {

    }
}

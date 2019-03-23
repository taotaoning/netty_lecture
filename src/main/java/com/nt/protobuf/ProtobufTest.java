package com.nt.protobuf;

import java.util.Arrays;

/**
 * protobuf 测试
 * Create by TaoTaoNing
 * 2019/3/23
 *
 **/
public class ProtobufTest {
    public static void main(String[] args) throws Exception {
        //生成student对象  是datainfo的内部类
        DataInfo.Student st = DataInfo.Student.newBuilder().setName("张三").setAeg(12).setAddress("南京").build();

        //转化为字节数组 （序列化）
        byte[] bytes = st.toByteArray();

        System.out.println(Arrays.toString(bytes));

        // 从字节数组解析出来   （反序列化）
        DataInfo.Student student = DataInfo.Student.parseFrom(bytes);
        System.out.println(student);


    }

}

package org.example;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //申请10空间

//        ByteBuffer buffer = ByteBuffer.allocate(10);
//        //除了直接丢byte进去之外，我们也可以丢其他的基本类型（注意容量消耗）
//        buffer.putInt(Integer.MAX_VALUE);  //丢个int的最大值进去，注意一个int占4字节
//        System.out.println("当前缓冲区剩余字节数：" + buffer.remaining());  //只剩6个字节了
//
//        //我们来尝试读取一下，记得先翻转
//        buffer.flip();
//        while (buffer.hasRemaining()) {
//            System.out.println(buffer.get());   //一共四个字节
////    }
//        }

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.put((byte) 1);
        buffer.put((byte) -1);

        buffer.flip();   //翻转一下
        System.out.println(buffer.getInt());  //以int形式获取，那么就是一次性获取4个字节

    }
}

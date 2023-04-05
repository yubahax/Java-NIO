package org.example.channel.channelsocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class server {
    public static void main(String[] args) {
        //创建一个新的ServerSocketChannel，一会直接使用SocketChannel进行网络IO操作
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()){
            //依然是将其绑定到8080端口
            serverChannel.bind(new InetSocketAddress(8080));
            //同样是调用accept()方法，阻塞等待新的连接到来
            SocketChannel socket = serverChannel.accept();
            //因为是通道，两端的信息都是可以明确的，这里获取远端地址，当然也可以获取本地地址
            System.out.println("客户端已连接，IP地址为："+socket.getRemoteAddress());
            //***************多通道通信，拒绝阻塞
//            new Thread(()-> {
//                try {
//                    ByteBuffer buffer = ByteBuffer.allocate(128);
//                    socket.read(buffer);   //SocketChannel同时实现了读写通道接口，所以可以直接进行双向操作
//                    buffer.flip();
//                    System.out.print("接收到客户端数据："+new String(buffer.array(), 0, buffer.remaining()));
//                    //直接向通道中写入数据就行
//                    socket.write(ByteBuffer.wrap("已收到！".getBytes()));
//                    //记得关
//                    socket.close();
//                } catch (IOException e) {
//                   throw new RuntimeException();
//                }
//            }).start();

            //使用缓冲区进行数据接收
            ByteBuffer buffer = ByteBuffer.allocate(128);
            socket.read(buffer);   //SocketChannel同时实现了读写通道接口，所以可以直接进行双向操作
            buffer.flip();
            System.out.print("接收到客户端数据："+new String(buffer.array(), 0, buffer.remaining()));
            //直接向通道中写入数据就行
            socket.write(ByteBuffer.wrap("已收到！".getBytes()));
            //记得关
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

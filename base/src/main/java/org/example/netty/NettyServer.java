package org.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //这里我们使用NioEventLoopGroup实现类即可，创建BossGroup和WorkerGroup
        //当然还有EpollEventLoopGroup，但是仅支持Linux，这是Netty基于Linux底层Epoll单独编写的一套本地实现，没有使用NIO那套
        EventLoopGroup bossGroup = new NioEventLoopGroup(), workerGroup = new NioEventLoopGroup();

        //创建服务端启动引导类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //可链式，就很棒
        bootstrap
                .group(bossGroup, workerGroup)   //指定事件循环组
                .channel(NioServerSocketChannel.class)   //指定为NIO的ServerSocketChannel
                .childHandler(new ChannelInitializer<SocketChannel>() {   //注意，这里的SocketChannel不是我们NIO里面的，是Netty的
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        //获取流水线，当我们需要处理客户端的数据时，实际上是像流水线一样在处理，这个流水线上可以有很多Handler
                        channel.pipeline()
                         .addLast(new StringDecoder())   //当客户端发送来的数据只是简单的字符串转换的ByteBuf时，我们直接使用内置的StringDecoder即可转换
                         .addLast(new ChannelInboundHandlerAdapter(){   //添加一个Handler，这里使用ChannelInboundHandlerAdapter
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {  //ctx是上下文，msg是收到的消息，默认以ByteBuf形式（也可以是其他形式，后面再说）
                                ByteBuf buf = (ByteBuf) msg;   //类型转换一下
                                System.out.println(Thread.currentThread().getName()+" >> 接收到客户端发送的数据："+buf.toString(StandardCharsets.UTF_8));
                                //通过上下文可以直接发送数据回去，注意要writeAndFlush才能让客户端立即收到
                                ctx.writeAndFlush(Unpooled.wrappedBuffer("已收到！".getBytes()));
                                ctx.fireChannelRead(msg);
                            }
                        })
                                .addLast(new ChannelInboundHandlerAdapter(){
                                             @Override
                                             public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                                 ByteBuf buf = (ByteBuf) msg;
                                                 System.out.println("2接收到客户端发送的数据："+buf.toString(StandardCharsets.UTF_8));
                                             }
                                         })
                        .addLast(new ChannelInboundHandlerAdapter(){   //第二个用于处理异常
                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        System.out.println("我是异常处理："+cause);
                                    }
                            })
                                .addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("1号出站："+msg);
                                    }
                                })
                                .addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("2号出站："+msg);
                                        ctx.write(msg);  //继续write给其他的出站Handler，不然到这里就断了
                                    }
                                })
                                .addLast(new StringEncoder());  //编码器安排;


                    }
                });
        //最后绑定端口，启动
        bootstrap.bind(8080);

        ChannelFuture future = bootstrap.bind(8080);
        future.sync();
        System.out.println("is " + future.isDone());
        //等待同步任务完成

//        future.addListener(f -> System.out.println("我是服务端启动完成之后要做的事情"));
        //直接添加监听器，当任务完成时自动执行，但是注意执行也是异步的，不是在当前线程
    }

}

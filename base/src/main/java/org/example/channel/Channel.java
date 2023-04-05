package org.example.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Channel {
    public static void main(String[] args) throws IOException {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//        ReadableByteChannel channel = Channels.newChannel(System.in);
//        while (true) {
//            channel.read(byteBuffer);
//            byteBuffer.flip();
//            //读写翻转
//            System.out.println("读到数据：" + new String(byteBuffer.array(),0,byteBuffer.remaining()));
//            byteBuffer.clear();
//            //回到最初状态
//        }

        //***************FileChannel读取文件（只读）*********************
//        FileInputStream in = new FileInputStream("test.txt");
//        //但是这里的通道只支持读取或是写入操作
//        FileChannel channel = in.getChannel();
//        //创建一个容量为128的缓冲区
//        ByteBuffer buffer = ByteBuffer.allocate(128);
//        //从通道中将数据读取到缓冲区中
//        channel.read(buffer);
//        //翻转一下，接下来要读取了
//        buffer.flip();
//        System.out.println(new String(buffer.array(), 0, buffer.remaining()));

        //**************FileChannel写入文件（只写）********************
        //1. 直接通过输入或输出流获取对应的通道
//        FileOutputStream out = new FileOutputStream("test.txt");
//        //但是这里的通道只支持读取或是写入操作
//        FileChannel channel = out.getChannel();
//        //尝试写入一下
//        channel.write(ByteBuffer.wrap("伞兵一号卢本伟准备就绪！".getBytes()));

        //************又读又写
//        try(RandomAccessFile f = new RandomAccessFile("test.txt", "rw");  //这里设定为支持读写，这样创建的通道才能具有这些功能
//            FileChannel channel = f.getChannel()){   //通过RandomAccessFile创建一个通道
//            channel.write(ByteBuffer.wrap("伞兵二号马飞飞准备就绪！".getBytes()));
//
//            System.out.println("写操作完成之后文件访问位置："+channel.position());
//            //注意读取也是从现在的位置开始
//            channel.position(0);
//            //需要将位置变回到最前面，这样下面才能从文件的最开始进行读取
//
//            ByteBuffer buffer = ByteBuffer.allocate(128);
//            channel.read(buffer);
//            buffer.flip();
//
//            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
        //***************mappedBuffer 将文件内容映射到内存，同步修改保存
//            try(RandomAccessFile f = new RandomAccessFile("test.txt", "rw");
//                FileChannel channel = f.getChannel()){
//
//                //通过map方法映射文件的某一段内容，创建MappedByteBuffer对象
//                //比如这里就是从第四个字节开始，映射10字节内容到内存中
//                //注意这里需要使用MapMode.READ_WRITE模式，其他模式无法保存数据到文件
//                MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 4, 10);
//
//                //我们可以直接对在内存中的数据进行编辑，也就是编辑Buffer中的内容
//                //注意这里写入也是从pos位置开始的，默认是从0开始，相对于文件就是从第四个字节开始写
//                //注意我们只映射了10个字节，也就是写的内容不能超出10字节了
//                buffer.put("yyds".getBytes());
//
//                //编辑完成后，通过force方法将数据写回文件的映射区域
//                buffer.force();

    }
}
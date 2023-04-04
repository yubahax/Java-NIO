package org.example;

import java.nio.IntBuffer;

public class Buffer {
    public static void main(String[] args) {
        //创建一个缓冲区不能直接new，而是需要使用静态方法去生成，有两种方式：
        //1. 申请一个容量为10的int缓冲区
//        IntBuffer buffer = IntBuffer.allocate(10);

        //2. 可以将现有的数组直接转换为缓冲区（包括数组中的数据）
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        IntBuffer buffer = IntBuffer.wrap(arr);
        buffer.put(0,9);
        System.out.println(buffer.get(0));
        System.out.println(buffer.get(1));

        //3.写操作
//        public abstract IntBuffer put(int i); - 在当前position位置插入数据，由具体子类实现
//        public abstract IntBuffer put(int index, int i); - 在指定位置存放数据，也是由具体子类实现
//        public final IntBuffer put(int[] src); - 直接存放所有数组中的内容（数组长度不能超出缓冲区大小）
//        public IntBuffer put(int[] src, int offset, int length); - 直接存放数组中的内容，同上，但是可以指定存放一段范围
//        public IntBuffer put(IntBuffer src); - 直接存放另一个缓冲区中的内容


        //4.读操作
//        public abstract int get(); - 直接获取当前position位置的数据，由子类实现
//        public abstract int get(int index); - 获取指定位置的数据，也是子类实现
//        public IntBuffer get(int[] dst) - 将数据读取到给定的数组中
//        public IntBuffer get(int[] dst, int offset, int length) - 同上，加了个范围

        //5.其他操作
//        public abstract IntBuffer compact() - 压缩缓冲区，由具体实现类实现
//        public IntBuffer duplicate() - 复制缓冲区，会直接创建一个新的数据相同的缓冲区
//        public abstract IntBuffer slice() - 划分缓冲区，会将原本的容量大小的缓冲区划分为更小的出来进行操作
//        public final Buffer rewind() - 重绕缓冲区，其实就是把position归零，然后mark变回-1
//        public final Buffer clear() - 将缓冲区清空，所有的变量变回最初的状态

        //**************只读缓冲区
//        IntBuffer buffer = IntBuffer.wrap(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
//        IntBuffer readBuffer = buffer.asReadOnlyBuffer();
//        System.out.println(readBuffer.isReadOnly());
//        System.out.println(readBuffer.get());
        //报错，只读缓冲区不能get
//        readBuffer.put(0, 666);



    }
}

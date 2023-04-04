package org.example;

import java.nio.CharBuffer;
import java.util.Arrays;

public class CharBufferTest {
    public static void main(String[] args) {
//        CharBuffer buffer = CharBuffer.allocate(10);
//        buffer.put("lbwnb");  //除了可以直接丢char之外，字符串也可以一次性丢进入
//        System.out.println(Arrays.toString(buffer.array()));
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("lbwnb");
        buffer.append("!");   //可以像StringBuilder一样使用append来继续添加数据

        System.out.println("剩余容量："+buffer.remaining());  //已经用了6个字符了

        buffer.flip();
        System.out.println("整个字符串为："+buffer);   //直接将内容转换为字符串
        System.out.println("第3个字符是："+buffer.charAt(2));  //直接像String一样charAt

        buffer   //也可以转换为IntStream进行操作
                .chars()
                .filter(i -> i < 'l')
                .forEach(i -> System.out.print((char) i));

    }
}

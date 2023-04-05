package org.example.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ReactorServer {
    public static void main(String[] args) {
        try (Reactor reactor = new Reactor()){
            reactor.run();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

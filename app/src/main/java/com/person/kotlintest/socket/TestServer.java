package com.person.kotlintest.socket;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;


public class TestServer {
    public static void main(String[] args) {
        NioSocketAcceptor acceptor = null;
        try {
            acceptor = new NioSocketAcceptor();
            acceptor.setHandler(new TestHandler());
            acceptor.getFilterChain().addLast("mFilter", new ProtocolCodecFilter(new TextLineCodecFactory()));
            acceptor.setReuseAddress(true);
            acceptor.bind(new InetSocketAddress(8989));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


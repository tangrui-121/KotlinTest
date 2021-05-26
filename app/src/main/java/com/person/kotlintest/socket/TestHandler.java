package com.person.kotlintest.socket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @anthor tr
 * @date 2021/5/26
 * @desc
 */

public class TestHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught: " + cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("recieve : " + (String) message);
        session.write("hello I am server");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpen");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

}


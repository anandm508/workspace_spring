package com.example.demo.listiner;

import javax.jms.Message;
import javax.jms.MessageListener;

public class TestMesagListiner2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("message from qeue3");
	}

}

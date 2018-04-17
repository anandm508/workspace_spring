package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	@Qualifier("Test")
	private JmsTemplate cf;

	@Autowired
	@Qualifier("Demo")
	private JmsTemplate cf1;

	@Autowired
	@Qualifier("Qeue3")
	private JmsTemplate cf2;

	@RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
	public void sendMesageToSecondQeue(@PathVariable String msg) {

		if ("Test".equals(msg)) {
			cf.send(session -> session.createObjectMessage(msg));
		} else if ("Demo".equals(msg)) {
			cf1.send(session -> session.createObjectMessage(msg));
		} else if ("Qeue3".equals(msg)) {
			cf2.send(session -> session.createObjectMessage(msg));
		}

	}

}

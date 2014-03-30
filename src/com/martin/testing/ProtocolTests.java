package com.martin.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.martin.packetchat.Action;
import com.martin.packetchat.Protocol;

public class ProtocolTests {

	Protocol proto;
	
	@Before
	public void setUp() throws Exception {
		proto = new Protocol();
	}

	@Test
	public void testGetAction() {
		assertEquals("Getting wrong action with unexactly filled String", proto.getAction("LOGOUTklaösdlfkjödjffkdkslksd"), Action.LOGOUT);
		assertEquals("Getting wrong action with precisely filled String", proto.getAction("MESSAGE   This is my message."), Action.MESSAGE);
		// assertEquals("Not getting undefined when sending garbage", proto.getAction(""), Action.UNDEFINED);
		assertEquals("Not getting undefined when sending garbage", proto.getAction("adslkfjasldkfjasldkfjaösdfjöasldfjköalsdkfjöasldk"), Action.UNDEFINED);
	}

	@Test
	public void testEncodeMessage() {
		assertEquals("Login not working", proto.encodeMessage(Action.LOGIN, "username"), "LOGIN     username");
		assertEquals("Logout not working", proto.encodeMessage(Action.LOGOUT, "Frank"), "LOGOUT    Frank");
		assertEquals("Message not working", proto.encodeMessage(Action.MESSAGE, "Hello, it's me!"), "MESSAGE   Hello, it's me!");
	}
	
	@Test
	public void testGetContent() {
		assertEquals(proto.getContent(proto.encodeMessage(Action.LOGIN, "username")), "username");
		assertEquals(proto.getContent(proto.encodeMessage(Action.MESSAGE, "Hello, it's me!")), "Hello, it's me!");
		assertEquals(proto.getContent(proto.encodeMessage(Action.LOGOUT, "Frank")), "Frank");
	}

}

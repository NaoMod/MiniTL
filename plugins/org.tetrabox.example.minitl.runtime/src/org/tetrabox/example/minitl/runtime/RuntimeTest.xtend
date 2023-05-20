package org.tetrabox.example.minitl.runtime

import org.junit.Test
import com.googlecode.jsonrpc4j.StreamServer
import java.net.InetAddress
import java.net.ServerSocket
import com.googlecode.jsonrpc4j.JsonRpcServer
import org.tetrabox.example.minitl.runtime.exceptions.CustomAnnotationsErrorResolver
import org.tetrabox.example.minitl.runtime.LRPHandler
import org.tetrabox.example.minitl.runtime.ILRPHandler

class RuntimeTest {
	
	static final int MAX_THREADS = 5;
	static final String HOST_NAME = "localhost";
	static final int port = 49152;
	static final int BACKLOG = 10;

	static StreamServer streamServer;
	
	@Test
	def void test() {
		var bindAddress = InetAddress.getByName(HOST_NAME);
		var lrpHandler = new LRPHandler();
		var serverSocket = new ServerSocket(port, BACKLOG, bindAddress);

		var jsonRpcServer = new JsonRpcServer(lrpHandler, typeof(ILRPHandler));
		jsonRpcServer.setErrorResolver(CustomAnnotationsErrorResolver.getInstance());
		streamServer = new StreamServer(jsonRpcServer, MAX_THREADS, serverSocket);
		streamServer.start();

		System.out.println(
				"Server running at " + serverSocket.getInetAddress() + ", port " + serverSocket.getLocalPort() + "...");
		
		Thread.sleep(Integer.MAX_VALUE);
	}
}
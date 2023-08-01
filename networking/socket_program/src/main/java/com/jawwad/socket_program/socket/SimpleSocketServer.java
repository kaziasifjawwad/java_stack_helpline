package com.jawwad.socket_program.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer extends Thread {
  private ServerSocket serverSocket;
  private final int port;
  private boolean running = false;

  public SimpleSocketServer(int port) {
    this.port = port;
  }

  public void startServer() {
    try {
      InetAddress localAddress = InetAddress.getByName("172.16.204.247");
      serverSocket = new ServerSocket(port, 0, localAddress);
      this.start();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void stopServer() {
    running = false;
    this.interrupt();
  }

  @Override
  public void run() {
    running = true;
    while (running) {
      try {
        System.out.println("Listening for a connection");
        Socket socket = serverSocket.accept();
        RequestHandler requestHandler = new RequestHandler(socket);
        requestHandler.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    int port = 8081; // Integer.parseInt(args[0]);
    System.out.println("Start server on port: " + port);

    SimpleSocketServer server = new SimpleSocketServer(port);
    server.startServer();

    // Automatically shutdown in 1 minute
    try {
      Thread.sleep(900000);
    } catch (Exception e) {
      e.printStackTrace();
    }

    server.stopServer();
  }
}

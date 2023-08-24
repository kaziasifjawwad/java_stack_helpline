package com.jawwad.socket_program.socket;

import java.io.BufferedReader;
import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putDouble(2147483647);
        buffer.flip();
        System.out.println(buffer.getInt());
    }
}

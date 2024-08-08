package com.jchen.memoryserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MemoryServer server = new MemoryServer();
        server.start(-1, false);
    }
}

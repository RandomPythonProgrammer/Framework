package org.firstinspires.ftc.teamcode.common.memory;

import android.content.Context;
import org.firstinspires.ftc.ftccommon.external.OnCreate;
import org.firstinspires.ftc.ftccommon.external.OnDestroy;

import java.io.IOException;

public class MemoryServerManager {
    private static MemoryServer server;

    @OnCreate
    public static void onCreate(Context context) {
        server = new MemoryServer();
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnDestroy
    public static void onDestroy(Context context) {
        server.stop();
    }
}

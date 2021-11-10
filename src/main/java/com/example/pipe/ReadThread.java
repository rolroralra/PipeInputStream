package com.example.pipe;

import java.io.InputStream;
import java.io.OutputStream;

public class ReadThread extends Thread {
    private final InputStream pi;
    private final OutputStream po;

    ReadThread(InputStream pi, OutputStream po) {
        this.pi = pi;
        this.po = po;
    }

    public void run() {
        byte[] buffer = new byte[512];
        int bytes_read;

        try {
            for(;;) {
                bytes_read = pi.read(buffer);

                if (bytes_read == -1) {
                    return;
                }

                po.write(buffer, 0, bytes_read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

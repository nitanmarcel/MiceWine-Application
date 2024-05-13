package com.micewine.emu.core;

import android.content.Context;

import com.micewine.emu.core.MainService;
import com.micewine.emu.core.RunServiceClass;

public class Init {
    private final RunServiceClass runServices = new RunServiceClass();
    private Context ctx;

    public void run(Context ctx) {
        this.ctx = ctx;
        //runServices.runService(OverlayService.class, this.ctx);
        runServices.runService(MainService.class, this.ctx);
    }

    public void stopAll() {
        runServices.stopService();
    }
}
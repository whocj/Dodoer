package com.summer.whm.ssh.command.utils.file;

public class FileMonitor {
    public static void main(String[] args) {
        FileSchedulerTask task = new FileSchedulerTask("D:\\Temp");
        FileScheduler monitor = new FileScheduler();
        monitor.schedule(task, new TimeStep());
    }
}

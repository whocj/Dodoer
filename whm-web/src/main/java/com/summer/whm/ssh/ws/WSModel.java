package com.summer.whm.ssh.ws;

import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.core.TailCommand;
import com.summer.whm.ssh.command.core.thread.ThreadOperate;
import com.summer.whm.ssh.web.SSHWebSocket;

public class WSModel {
    private String username;

    private SSHWebSocket webSocket;

    private CommandContext context;

    private ThreadOperate threadOperate;
    
    private TailCommand tailCommand;
    
    public WSModel() {
        super();
    }

    public WSModel(String username, SSHWebSocket webSocket, CommandContext context) {
        super();
        this.username = username;
        this.webSocket = webSocket;
        this.context = context;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SSHWebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(SSHWebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public CommandContext getContext() {
        return context;
    }

    public void setContext(CommandContext context) {
        this.context = context;
    }

    public ThreadOperate getThreadOperate() {
        return threadOperate;
    }

    public void setThreadOperate(ThreadOperate threadOperate) {
        this.threadOperate = threadOperate;
    }

    public TailCommand getTailCommand() {
        return tailCommand;
    }

    public void setTailCommand(TailCommand tailCommand) {
        this.tailCommand = tailCommand;
    }

    public void stopThread(){
        if(threadOperate != null){
            threadOperate.stop();
            threadOperate = null;
        }
        if(tailCommand != null){
            tailCommand.stop();
            tailCommand = null;
        }
    }
    
}

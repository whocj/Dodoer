package com.summer.whm.ssh.command.examples;

import java.util.concurrent.BlockingQueue;

import com.summer.whm.ssh.command.core.GrepCommand;
import com.summer.whm.ssh.command.core.TailCommand;

public class TestTail {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws Exception {
        TailCommand tailCommand = new TailCommand("D:/log.txt");
        GrepCommand grep = new GrepCommand("当地时间");
        tailCommand.setNextCommand(grep);
        BlockingQueue<String> queue = tailCommand.tail();
        String line = null;
        while ((line = queue.take()) != null) {
            System.out.print(line);
        }
    }

}

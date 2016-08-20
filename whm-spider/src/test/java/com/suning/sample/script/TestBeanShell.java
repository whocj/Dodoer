package com.suning.sample.script;

import org.springframework.scripting.bsh.BshScriptUtils;

import bsh.EvalError;

public class TestBeanShell {
    public void testShell() {
        String srciptText = "say(name){ return \"hello,\"+name;}";
        SayHello sh;
        try {
            sh = (SayHello) BshScriptUtils.createBshObject(srciptText, new Class[] { SayHello.class });
            String res = sh.say("vidy");
            System.out.println(res);
        } catch (EvalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new TestBeanShell().testShell();
    }
}

interface SayHello {
    public String say(String name);
}

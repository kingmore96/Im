package client.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadClient implements Runnable {

    public void run() {
        System.out.println("控制台线程开始执行");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            try {
                System.out.println("可以输入 单聊/登出/创建群聊/群聊聊天/退出群聊 指令");
                String s = br.readLine();

                if(s != null && s.length() > 0){
                    System.out.println("输入指令");
                }else{
                    System.out.println("输入错误，请重新输入");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package thread.multicore;

import thread.ThreadEx;

import javax.swing.*;

public class MultiThreadIO {
    public static void main(String[] args) throws Exception {
        TempThread t1 = new TempThread();
        t1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
    }

    public static class TempThread extends Thread {
        public void run() {
            for (int i=10; i>0; i--) {
                System.out.println(i);
                try {
                    sleep(1000);
                } catch (Exception e) {}
            }
        }
    } // run()
}

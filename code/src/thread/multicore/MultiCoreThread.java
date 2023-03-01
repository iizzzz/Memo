package thread.multicore;

public class MultiCoreThread {
    static long startTime = 0;

    public static void main(String[] args) {
        CountThreadMillis t1 = new CountThreadMillis();
        t1.start();

        startTime = System.currentTimeMillis();

        for (int i=0; i<300; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.print("소요시간 1:" + (System.currentTimeMillis() - MultiCoreThread.startTime));
    }

    public static class CountThreadMillis extends Thread {
        public void run() {
            for (int i=0; i<300; i++) {
                System.out.printf("%s", new String("|"));
            }
            System.out.print("소요시간 2:" + (System.currentTimeMillis() - MultiCoreThread.startTime));
        }
    }
}

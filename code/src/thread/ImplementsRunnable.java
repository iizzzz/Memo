package thread;

/* Implements Runnable Interface */
public class ImplementsRunnable implements Runnable{
    @Override
    public void run() {
        throwException();
        for (int i=0; i<5; i++) {
            // Thread.curruneThread() - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

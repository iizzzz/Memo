package thread;

public class ThreadEx {
    public static void main(String[] args) {
        // 1. Thread의 자손 클래스 인스턴스 생성
        ExtendsThread t1 = new ExtendsThread();

        // 2. Runnable을 구현한 클래스의 인스턴스 생성
        // 생성자 Thread(Runnable target)
        Thread t2 = new Thread(new ImplementsRunnable());

        t1.start();
    }
}

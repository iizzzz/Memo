package thread;

/* Thread의 run()을 오버라이딩 */
public class ExtendsThread extends Thread {
    public void run() {
        throwException();
        // 상속받은 Thread의 getName() 호출
        for (int i=0; i<5; i++) {
            System.out.println(getName());
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

```java

/* ----------------팩토리 메서드 사용 X--------------- */

// abstract 클래스를 정의하여 캡슐화
public abstract class Source {
    
}

public class ProcessA extends Source {
    public A() {
        System.out.println("Process A 생성");
    }
}

public class ProcessB extends Source {
    public B() {
        System.out.println("Process B 생성");
    }
}

public class ProcessC extends Source {
    public C() {
        System.out.println("Process C 생성");
    }
}

// 문자열에 따른 클래스 생성의 분기 처리
public class NotFactory {
    public Source create(String source) {
        Source returnSource = null;
        
        switch (source) {
            case "A": returnSource = new ProcessA();
            case "B": returnSource = new ProcessB();
            case "C": returnSource = new ProcessC();
        }
    }
}

public class Client {
    public static void main(String[] args) {
        NotFactory non = new NotFactory();
        non.create("A");
        non.create("B");
    }
}

/* ----------------팩토리 메서드 사용 --------------- */

// 리팩터링 포인트 1. 팩토리 메서드로 객체 생성 위임
public class Factory {
    public Source create(String source) {
        Source returnSource = null;
        
        switch (source) {
            case "A": returnSource = new ProcessA(); break;
            case "B": returnSource = new ProcessB(); break;
            case "C": returnSource = new ProcessC(); break;
        }
        return returnSource;
    }
}

public class ProcessA extends Source {
    public A() {
        System.out.println("Process A 생성");
    }
}

public class ProcessB extends Source {
    public B() {
        System.out.println("Process B 생성");
    }
}

public class ProcessC extends Source {
    public C() {
        System.out.println("Process C 생성");
    }
}

// 리팩터링 포인트 2. 팩토리 메서드에서 생성한 객체 리턴
public class UseFactory {
    public Source create(String source) {
        Factory factory = new Factory();
        Source returnSource = factory.create(source);
        
        return returnSource;
    }
}

public class Client {
    public static void main(String[] args) {
        UseFactory use = new NotFactory();
        use.create("A");
        use.create("B");
    }
}

```
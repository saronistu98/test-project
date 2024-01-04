package threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        System.out.println(Thread.activeCount());
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(thread.isAlive());

        MyThread thread = new MyThread();
        MyRunnable runnable = new MyRunnable();
        Thread thread2 = new Thread(runnable);
        thread.start();
        thread.join();
        thread2.start();


    }
}

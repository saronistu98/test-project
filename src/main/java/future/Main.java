package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<FutureCallable<String>> future = executorService.submit(() -> {
            Thread.sleep(1200);
            return new FutureCallable<>("Saron");
        });


        try {
            while (!future.isDone()) {
                System.out.println("Task still in progress...");
                Thread.sleep(200);
            }
            System.out.println("Task completed");
            System.out.println(future.get().t);
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}

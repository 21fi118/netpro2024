package thread;

public class ExThreadsMain {
    public static void main(String[] args) {
        // Runnableインターフェースを無名クラスで実装
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("AnonymousRunnable: i=" + i);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // 新しいスレッドで無名クラスの実行
        new Thread(anonymousRunnable).start();

        // CountNumbersRunnableの実行
        Thread countThread = new Thread(new CountNumbersRunnable(1, 10));
        countThread.start();

        // CountingThreadの実行
        CountingThread countingThread = new CountingThread();
        countingThread.start();

        // メインスレッドでカウントを出力する
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("MainThread: i=" + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // スレッドの終了を待つ
        try {
            countThread.join();
            countingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountNumbersRunnable implements Runnable {
    private final int start;
    private final int end;

    public CountNumbersRunnable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            for (int i = start; i <= end; i++) {
                System.out.println("CountNumbersRunnable: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}

class CountingThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customMethod();
        }
    }

    public synchronized void customMethod() {
        count++;
        System.out.println("CountingThread: Count increased to: " + count);
    }
}

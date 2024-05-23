public class TaskObject implements ITask {
    private int execNumber;
    private int result;

    @Override
    public void setExecNumber(int x) {
        this.execNumber = x;
    }

    @Override
    public int getExecNumber() {
        return this.execNumber;
    }

    @Override
    public void exec() {
        // サーバでの計算：入力x以下の最大素数を求める
        this.result = calculateMaxPrime(execNumber);
    }

    @Override
    public int getResult() {
        return result;
    }

    // 素数かどうかを判定するメソッド
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 入力x以下の最大素数を求めるメソッド
    private int calculateMaxPrime(int x) {
        for (int i = x; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // 素数が見つからなかった場合は-1を返す
    }
}

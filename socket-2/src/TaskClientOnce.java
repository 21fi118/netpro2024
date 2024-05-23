import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientOnce {

    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.print("計算する数値を入力してください → ");
            int number = scanner.nextInt();
            scanner.close();

            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            TaskObject task = new TaskObject();
            task.setExecNumber(number);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(task);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            task = (TaskObject) ois.readObject();

            int result = task.getResult();
            System.out.println("計算結果: " + number + "以下の最大の素数は " + result + " です");

            ois.close();
            oos.close();
            socket.close();

        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}

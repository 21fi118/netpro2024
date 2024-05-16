import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FanServiceTCPClient {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.nextLine();  // 改行を消費
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // サーバからのファンサービスリクエストを受け取る
            FanService request = (FanService) ois.readObject();
            System.out.println("サーバからのメッセージは " + request.getMessage());

            System.out.println("メッセージを入力してください(例:ファンサービスを送ります) ↓");
            String message = scanner.nextLine();
            System.out.println("ファンサービスの内容を入力してください(例:💋) ↓");
            String content = scanner.nextLine();

            FanService fanService = new FanService(message, content);

            oos.writeObject(fanService);
            oos.flush();

            // サーバからの返答を受け取る
            FanService response = (FanService) ois.readObject();

            String replayMsg = response.getMessage();
            System.out.println("サーバからのメッセージは " + replayMsg);
            String replayContent = response.getContent();
            System.out.println(replayContent + " をもらいました！");

            ois.close();
            oos.close();
            socket.close();
            scanner.close();

        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        }
    }
}

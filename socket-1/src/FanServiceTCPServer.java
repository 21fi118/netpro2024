import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FanServiceTCPServer {

    public static void main(String[] args) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");

            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う

            Socket socket = server.accept(); // クライアントからの接続要求を待ち、要求があればソケットを取得し接続を行う
            System.out.println("接続しました。相手の入力を待っています......");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // サーバからクライアントにファンサービスのリクエストを送る
            FanService request = new FanService("ファンです！ファンサービスをお願いします！", "");
            oos.writeObject(request);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // クライアントからの応答を受け取る
            FanService response = (FanService) ois.readObject();
            String msgPresent = response.getMessage();
            System.out.println("メッセージは " + msgPresent);
            String presentFromClient = response.getContent();
            System.out.println("ファンサービスの内容は " + presentFromClient);

            // クライアントに興奮していることを伝える
            FanService echoResponse = new FanService("ファンです。きゃー！" + presentFromClient + "を受け取りました！", presentFromClient);
            oos.writeObject(echoResponse);
            oos.flush();

            // close処理
            ois.close();
            oos.close();
            socket.close();
            server.close();

        } catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HowOldAreYou {
	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader というのは、データ読み込みのクラス(型)
		// クラスの変数を作るには、new を使う。

		// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
		// Java では、 try{ XXXXXXXX } catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
		while (true) {

			try {
				System.out.println("何歳ですか? (終了するには 'q' または 'e' を入力してください)");
				String line = reader.readLine();

				// プログラムの終了条件
				if (line.equals("q") || line.equals("e")) {
					break;
				}

				int age = Integer.parseInt(line);
				// 年齢が範囲内か確認
				if (age < 0 || age > 120) {
					System.out.println("無効な年齢です。再入力してください。");
					continue; // 再入力
				}

				System.out.println("あなたは" + age + "歳ですね。");

				int currentYear = 2024; // 現在の年
                
                int ageIn2030 = age + (2030 - currentYear);

                System.out.println("あなたは2030年に " + ageIn2030 + "歳です。");

				int birthYear = currentYear - age;
				// 和暦を計算
                String era = "";
                int eraYear = 0;

                if (birthYear >= 1868 && birthYear <= 1911) {
                    era = "明治";
                    eraYear = birthYear - 1867; // 明治元年は1868年
                } else if (birthYear >= 1912 && birthYear <= 1925) {
                    era = "大正";
                    eraYear = birthYear - 1911; // 大正元年は1912年
                } else if (birthYear >= 1926 && birthYear <= 1988) {
                    era = "昭和";
                    eraYear = birthYear - 1925; // 昭和元年は1926年
                } else if (birthYear >= 1989 && birthYear <= 2018) {
                    era = "平成";
                    eraYear = birthYear - 1988; // 平成元年は1989年
                } else if (birthYear >= 2019) {
                    era = "令和";
                    eraYear = birthYear - 2018; // 令和元年は2019年
                }

                System.out.println("あなたの生まれた年は " + era + " " + eraYear + "年です。");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}

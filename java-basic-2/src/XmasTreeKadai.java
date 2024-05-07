public class XmasTreeKadai {

	public static void main(String[] args) {

        int N=10;
        for (int j = 0; j < N; j++) {

            for (int i = 0; i <= N-j; i++) {
                System.out.print("@");
            }

            for (int i = 0; i <= j*2; i++) {
                System.out.print("*");
            }

            for (int i = 0; i <= N-j; i++) {
                System.out.print("@");
            }

            System.out.print("\n");
        }

        // 木の幹部分を生成
        int trunkSpacing = (N * 2) - 3; // 幹を中央に配置するためのスペース
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= trunkSpacing / 2; j++) {
                System.out.print("@");
            }
            for (int j = 0; j < 5; j++) {
                System.out.print("|");
            }
            for (int j = 0; j <= trunkSpacing / 2; j++) {
                System.out.print("@");
            }
            System.out.print("\n");
        }
        System.out.println("---------");

	}
}

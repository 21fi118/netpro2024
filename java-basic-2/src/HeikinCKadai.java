import java.util.Arrays;
import java.util.Random;

public class HeikinCKadai {
	public static final int N=100;
	Kamoku[] kamoku = new Kamoku[N];
	static String kamokuname="数学";

	public static void main(String[] args) {
		HeikinCKadai heikinc= new HeikinCKadai(kamokuname);
		heikinc.initalizeScores();
		heikinc.printAverage();
		heikinc.gokakusha();

	}
	
	HeikinCKadai(String s){
		this.kamokuname=s;
		
	}
	void initalizeScores(){
		Random r = new Random();

		for(int i=0;i<N;i++){
			int score = r.nextInt(N+1);
			kamoku[i]= new Kamoku(score);

		}
	
	}
	
	void printAverage(){
		int sum=0;
		for(int i=0;i<N;i++){
			sum+=kamoku[i].getScore();

		}
		System.out.println("平均点は"+sum/N);

	}

	void gokakusha(){
        Kamoku[] passedStudents = Arrays.stream(kamoku)
            .filter(k -> k.getScore() >= 80) // 80点以上
            .toArray(Kamoku[]::new);

        // 合格者を得点順にソート
        Arrays.sort(passedStudents, (a, b) -> Integer.compare(a.getScore(), b.getScore()));

        System.out.println("以下、合格者の点数です。");

        for (Kamoku k : passedStudents) {
            System.out.println("点数: " + k.getScore());
        }

	}//student idと点数をソートしてだせ。＞＝８０以上

}
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MyExceptionHoliday {
    private static final Set<Integer> holidays = new HashSet<>();
    static {
        // 2024年5月
        holidays.add(3);  // 憲法記念日
        holidays.add(4);  // みどりの日
        holidays.add(5);  // こどもの日
        holidays.add(6);  // 振替休日
        holidays.add(11);
        holidays.add(12);
        holidays.add(18);
        holidays.add(19);
        holidays.add(25);
        holidays.add(26);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("5月の日付を入力してください（1-31）、または終了するために-1を入力してください: ");
            int day = scanner.nextInt();

            if (day == -1) {
                break;
            }

            if (day < 1 || day > 31) {
                System.out.println("無効な日付です。");
                continue;
            }

            LocalDate date = LocalDate.of(LocalDate.now().getYear(), 5, day);

            try {
                checkHoliday(date);
                System.out.println(day + "日は土日か祝日です。学校お仕事おやすみ！");
            } catch (NoHolidayException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    public static void checkHoliday(LocalDate date) throws NoHolidayException {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || holidays.contains(date.getDayOfMonth())) {
            return;
        } else {
            throw new NoHolidayException();
        }
    }
}

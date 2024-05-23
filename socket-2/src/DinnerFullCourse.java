public class DinnerFullCourse {

    private Dish[] list = new Dish[5]; // [0]-[4]の計5個

    public static void main(String[] args) {
        DinnerFullCourse fullcourse = new DinnerFullCourse();
        fullcourse.eatAll();
    }

    public DinnerFullCourse() {
        // 5種類のDishの初期化
        list[0] = new Dish();
        list[0].setName("前菜：カプレーゼ");
        list[0].setValune(8);

        list[1] = new Dish();
        list[1].setName("スープ：オニオンスープ");
        list[1].setValune(12);

        list[2] = new Dish();
        list[2].setName("メイン：ステーキ");
        list[2].setValune(25);

        list[3] = new Dish();
        list[3].setName("サラダ：シーザーサラダ");
        list[3].setValune(10);

        list[4] = new Dish();
        list[4].setName("デザート：チーズケーキ");
        list[4].setValune(6);
    }

    public void eatAll() {
        for (Dish dish : list) {
            System.out.println(dish.getName() + " = " + dish.getValune());
        }
    }
}

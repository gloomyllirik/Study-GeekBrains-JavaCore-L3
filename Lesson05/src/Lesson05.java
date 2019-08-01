import java.util.concurrent.CountDownLatch;

public class Lesson05 {
    public static final int CARS_COUNT = 7;
    public static CountDownLatch cdl;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        // Ждем подготовки всех участников
        waitMain();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdl.countDown();

        waitMain();
        cdl.countDown();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    static void waitMain(){
        cdl = new CountDownLatch(CARS_COUNT + 1);
        while (true){
            if (cdl.getCount() == 1)
                break;
        }
    }
}
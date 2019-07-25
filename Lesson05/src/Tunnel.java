import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore smp = new Semaphore(Lesson05.CARS_COUNT / 2,true);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println("В туннеле на данный момент " + CARS_COUNT + " машин");
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                CARS_COUNT++;
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                smp.release();
                System.out.println(c.getName() + " закончил этап: " + description);
                CARS_COUNT--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
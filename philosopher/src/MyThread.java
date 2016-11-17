import java.util.Random;

/**
 * Created by Administrator on 2016/11/16.
 */
public class MyThread implements Runnable {
    private boolean waiter,feeling;
    public  MyThread(long t){
        Random random = new Random(t);
        waiter = random.nextBoolean();
    }
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName()+" thinking!");
        Random random1 = new Random();
        feeling = random1.nextBoolean();
        if (feeling) {
            System.out.println("            waiter，俺饿了！");
            if (waiter) {
                System.out.println("            eating!");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waiter = false;
            } else {
                System.out.println("            waiting!");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waiter = true;
            }
        }
        else {
            System.out.println("            俺还不饿呢！！！");
        }
    }
}


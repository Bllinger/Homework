/**
 * Created by Administrator on 2016/11/16.
 */
public class Test {
    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        MyThread myThread = new MyThread(t);

        for(int  i = 0;i<2;i++) {
            new Thread(myThread,"philosoper1").start();
            new Thread(myThread,"philosoper2").start();
            new Thread(myThread,"philosoper3").start();
            new Thread(myThread,"philosoper4").start();
            new Thread(myThread,"philosoper5").start();
        }
    }
}

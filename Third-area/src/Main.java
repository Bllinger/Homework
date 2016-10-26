import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Main {
    public static void main(String[] args) {
        //三角形
        Triangle triangle = new Triangle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入三角形长和高：");
        triangle.Long = scanner.nextDouble();
        triangle.height = scanner.nextDouble();
        triangle.area();
        //正方形
        Square square = new Square();
        System.out.println("请输入正方形的边长：");
        square.Long = scanner.nextDouble();
        square.area();
        //圆
        Circular circular =new Circular();
        System.out.println("请输入圆的半径：");
        circular.Long = scanner.nextDouble();
        circular.area();
    }
}

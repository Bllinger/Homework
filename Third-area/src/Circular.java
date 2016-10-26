/**
 * Created by Administrator on 2016/10/26.
 */
public class Circular extends Shape{
    final double PI = 3.14;
    public void area(){
        this.area = PI*this.Long*this.Long;
        System.out.println("圆的面积为："+this.area);
    }
}

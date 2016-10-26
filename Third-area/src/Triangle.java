/**
 * Created by Administrator on 2016/10/26.
 */
public class Triangle extends Shape {
    double height;
    public void area(){
        this.area=(this.Long*this.height)/2;
        System.out.println("三角形面积为："+this.area);
    }
}

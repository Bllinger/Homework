import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Test {
    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        Bag<Person> bag1 = new Bag<>();

        System.out.println(bag.isEmpty());

        bag.add("A");
        bag.add("b");
        bag.add("q");
        bag.add("q");
        bag.add("s");
        bag.add("l");

        System.out.println(bag);

        System.out.println(bag.contains("A"));

        bag.remove("A");

        System.out.println(bag);

        bag1.add(new Person("zhang",18));
        bag1.add(new Person("li",18));
        System.out.println(bag1);
        System.out.println(bag1.isEmpty());
        bag1.remove(new Person("zhang",18));
        System.out.println(bag1);
        System.out.println(bag1.contains(new Person("li",18)));
    }
}

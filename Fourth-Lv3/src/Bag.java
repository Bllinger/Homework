import java.util.*;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Bag<E> implements Begin<E>{
    List<E> list =new ArrayList<>();

    public boolean add(E o){
        return list.add(o);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean remove(E o) {
        return list.remove(o);
    }

    @Override
    public boolean contains(E o) {
        return list.contains(o);
    }

    public String toString(){
        return this.list.toString();
    }
}

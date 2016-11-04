/**
 * Created by Administrator on 2016/11/2.
 */
public class Person {
    private String p;
    private String name;
    private int age;

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String toString(){
        return this.name+","+this.age;
    }
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if (!(object instanceof Person)){
            return false;
        }
        Person person =(Person)object;
        if (this.name.equals(person.name)&&this.age == person.age){
            return true;
        }
        else {
            return false;
        }
    }
    public int hashCode(){
        return this.name.hashCode()*this.age;
    }
}

package UtilitiesClass;
import adt.*;

/**
 *
 * @author Lee
 */
public class Testing {
    public static void main(String[] args){
        ArrayList<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        System.out.println(array.size());
        System.out.println(array);
        System.out.println("-----------------------");
        array.add(1, "3");
        System.out.println("----------------");
        System.out.println(array.size());
        System.out.println(array);
        array.clear();
        System.out.println(array);
        System.out.println(array.size());
    }
}

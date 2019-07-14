/*      1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        2. Написать метод, который преобразует массив в ArrayList;
        3. Большая задача:
        Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
        Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну
        коробку нельзя сложить и яблоки, и апельсины;
        Для хранения фруктов внутри коробки можно использовать ArrayList;
        Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
        (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
        Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
        подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае
        (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
        Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку
        фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов
        не остается, а в другую перекидываются объекты, которые были в этой коробке;
        Не забываем про метод добавления фрукта в коробку.*/

import java.util.ArrayList;

public class Lesson01 {
    public static void main(String[] args) {

        System.out.println("ЗАДАНИЯ 1, 2: \n");
        Integer[] arr1 = {1, 2, 3, 4, 5};
        ArrayClass<Integer> intArr = new ArrayClass<>(arr1);
        String[] arr2 = {"str1", "str2", "str3", "str4", "str5"};
        ArrayClass<String> stringArr = new ArrayClass<>(arr2);
        Double[] arr3 = {1.5, 2.5, 3.5, 4.5, 5.5};
        ArrayClass<Double> doubleArr = new ArrayClass<>(arr3);
        ArrayList list;

        intArr.printArray();
        intArr.replaceArray(1,2);
        intArr.printArray();
        intArr.convertArray();
        intArr.printList();

        stringArr.printArray();
        stringArr.replaceArray(1,2);
        stringArr.printArray();
        stringArr.convertArray();
        stringArr.printList();

        doubleArr.printArray();
        doubleArr.replaceArray(1,2);
        doubleArr.printArray();
        doubleArr.convertArray();
        doubleArr.printList();

        System.out.println("\n\nЗАДАНИЕ 3:\n");
        Box<Fruit> appleBox1 = new Box<>(8);
        Box<Apple> appleBox2 = new Box<>(20, "Apple");
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>(30);
        appleBox1.info();
        appleBox2.info();
        orangeBox1.info();
        orangeBox2.info();

        for (int i=0; i < 6; i++){
            appleBox1.put(new Apple());
        }
        appleBox1.info();

        if (appleBox1.compare(appleBox2))
            System.out.println("Коробки весят одинаково\n");
        else
            System.out.println("Коробки имеют разный вес\n");

        appleBox1.pour(appleBox2);
        for (int i=0; i < 4; i++) {
            appleBox2.put(new Apple());
        }
        appleBox1.info();
        appleBox2.info();

        for (int i=0; i < 3; i++) {
            orangeBox1.put(new Orange());
        }
        orangeBox1.info();

        for (int i=0; i < 10; i++) {
            orangeBox2.put(new Orange());
        }
        orangeBox2.info();

        if (!appleBox2.pour(orangeBox1))
            System.out.println("Не удалось перекинуть из коробки с яблоками в коробку с апельсинами");
    }
}

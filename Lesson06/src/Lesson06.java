import java.util.ArrayList;

public class Lesson06 {

    /*
    1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере(запущен,
        произошла ошибка, клиент подключился, клиент прислал сообщение/команду).
    2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
        Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
        идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
        необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных
        данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или
        единицы, то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    */

    public static void main(String[] args) {
        Integer[] a1 = {0,1,2,3,5,6,7,8,9};
        Integer[] res = cutMass(a1);
        for (int i = 0; i < res.length; i++)
            System.out.print(res[i] + " ");
    }

    static Integer[] cutMass(Integer[] mass){
        ArrayList arrayList = new ArrayList();
        int length = mass.length;

        for (int i = length - 1; i >= 0; i--){
            if (mass[i] == 4)
                break;
            else if(i != 0)
                arrayList.add(0, mass[i]);
            else
                throw new RuntimeException();
        }
        Integer[] arr = (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);

        return arr;
    }

    static boolean checkMass(Integer[] mass){
        boolean result = false;
        int length = mass.length;

        for (int i = 0; i < length; i++){
            if (mass[i] == 1 || mass[i] == 4){
                result = true;
                break;
            }
        }

        return result;
    }
}

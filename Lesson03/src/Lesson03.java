import jdk.internal.util.xml.impl.ReaderUTF16;
import jdk.internal.util.xml.impl.ReaderUTF8;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class Lesson03 {

    public static void main(String[] args) {


        System.out.println("1. ПРОЧИТАТЬ ФАЙЛ (ОКОЛО 50 БАЙТ) В БАЙТОВЫЙ МАССИВ И ВЫВЕСТИ ЭТОТ МАССИВ В КОНСОЛЬ\n\n");
        try {
            File file1 = new File("FILES/TASK1.txt");
            FileInputStream in = new FileInputStream(file1);
            byte[] fileContent = new byte[(int)file1.length()];
            int x;
            while ((x = in.read(fileContent)) > 0) {
                System.out.print(new String(fileContent, 0 , x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("\n\n2. ПОСЛЕДОВАТЕЛЬНО СШИТЬ 5 ФАЙЛОВ В ОДИН (ФАЙЛЫ ПРИМЕРНО 100 БАЙТ). МОЖЕТ " +
                "ПРИГОДИТЬСЯ СЛЕДУЮЩАЯ КОНСТРУКЦИЯ: ArrayList<InputStream> al = new ArrayList<>(); ... " +
                "Enumeration<InputStream> e = Collections.enumeration(al);\n\n");
        try {
            File file2 = new File("FILES/TASK2.txt");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file2));

            ArrayList<InputStream> al = new ArrayList<>();
            al.add(new BufferedInputStream(new FileInputStream("FILES/TASK2-1.txt")));
            al.add(new BufferedInputStream(new FileInputStream("FILES/TASK2-2.txt")));
            al.add(new BufferedInputStream(new FileInputStream("FILES/TASK2-3.txt")));
            al.add(new BufferedInputStream(new FileInputStream("FILES/TASK2-4.txt")));
            al.add(new BufferedInputStream(new FileInputStream("FILES/TASK2-5.txt")));
            Enumeration<InputStream> en = Collections.enumeration(al);

            // поочередно склеиваем файлы
            while (en.hasMoreElements()){
                splitFile(out, en.nextElement());
            }
            out.close();

            // проверка склеенного файла
            File file_final = new File("FILES/TASK2.txt");
            InputStreamReader in1 = new InputStreamReader(new FileInputStream(file_final), "UTF-8");
            int x;
            while ((x = in1.read()) > 0) {
                System.out.print(( char )x);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("\n\n3. НАПИСАТЬ КОНСОЛЬНОЕ ПРИЛОЖЕНИЕ, КОТОРОЕ УМЕЕТ ПОСТРАНИЧНО ЧИТАТЬ ТЕКСТОВЫЕ ФАЙЛЫ " +
                "(РАЗМЕРОМ > 10 MB). ВВОДИМ СТРАНИЦУ (ЗА СТРАНИЦУ МОЖНО ПРИНЯТЬ 1800 СИМВОЛОВ), ПРОГРАММА ВЫВОДИТ ЕЕ " +
                "В КОНСОЛЬ. КОНТРОЛИРУЕМ ВРЕМЯ ВЫПОЛНЕНИЯ: ПРОГРАММА НЕ ДОЛЖНА ЗАГРУЖАТЬСЯ ДОЛЬШЕ 10 СЕКУНД, " +
                "А ЧТЕНИЕ – ЗАНИМАТЬ СВЫШЕ 5 СЕКУНД.\n\n");


    }

    static void splitFile(OutputStream out, InputStream in){
        int x;
        try {
            while ((x = in.read()) != - 1 ) {
                out.write(( char )x);
            }
            out.write('\n'); // Добавляем перенос строки на стыке склейки файлов (для наглядности)
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
package Lesson2;
//2. Считать данные из текстового файлы и добавить их в базу данных

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lesson2_2 {
    public static void main(String[] args) {

        //Прошу прощения, но с этим карантином ничего не успеваю закончить...
        //Вот просто последовательность шагов, которыми, как мен кажется можно будет решить задачу
        //1. Считываем Scanner'ом текстовый файл построчно
        //2. Создаем Список и каждую строку добавляем в список
        //3. Из Списка по индексу достаем только те значения, которые нас инетересуют
        //на мой взгляд это поля Name и Score
        //4. Производим сличение на предмет соответствия в базе данных (често признаюсь, пока точно не знаю как)
        //5.Если имя уже есть - производим Update БД (предварительно проверив на ID...)
        //6. Если имени нет - то добавляем в БД

        try {
            File file = new File("Changes.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line[] = input.nextLine().split("  ");
                List<String[]> array = new ArrayList();
                array.add(line);
                for (String[] strings : array) {
                    System.out.println(Arrays.toString(strings));
                }
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
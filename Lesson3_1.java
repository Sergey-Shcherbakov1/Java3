package Lesson3;
//Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;

import java.io.*;

public class Lesson3_1 {
    public static void main(String[] args) {

        String filePath = "D:\\Java - Факультет\\Java3\\pom.xml";
        File file = new File(filePath);
        int b;
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bufferedInput = new BufferedInputStream(fis)) {
            while ((b = bufferedInput.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//          то же самое, но с назначенным буфером
//        String filePath = "D:\\Java - Факультет\\Java3\\pom.xml";
//        File file = new File(filePath);
//        byte[] b = new byte[512];
//        int x;
//        try (FileInputStream fis = new FileInputStream(file)) {
//            while ((x = fis.read(b)) > 0) {
//
//                System.out.print(new String(b, 0, x));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
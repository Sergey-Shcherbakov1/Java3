package Lesson3;
//Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
//Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
//Программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Lesson3_3 {
    static final int PAGE_SIZE = 1800;
    static final String defaultPath = "allFiles.txt";
    static Scanner scanner = new Scanner(System.in);
    static boolean appClose;

    public static void main(String[] args) {

        System.out.println("Enter the path to the file you want to read");
        System.out.println("(if you want to quit press 'q')");
        String userFilePath = scanner.nextLine();
        if ( userFilePath.equalsIgnoreCase("q") ) {
            appClose = true;
            System.exit(0);
        }
        File file = new File(userFilePath);
        if ( file.exists() ) openFile(userFilePath);
        else openFile(defaultPath);
        scanner.close();
    }

    private static void openFile(String path) {
        while (!appClose) {
            try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
                System.out.println("Enter page:");
                String pageNumber = scanner.nextLine();
                if ( pageNumber.equalsIgnoreCase("q")) {
                    appClose = true;
                    System.exit(0);
                }
                byte[] buffer = new byte[PAGE_SIZE];
                raf.seek(((Integer.parseInt(pageNumber) - 1) * PAGE_SIZE));
                System.out.println((char) raf.read(buffer));
                System.out.println(new String(buffer, "UTF-8"));
                System.out.println("\n---------------------------\n" +
                        "Page number " + pageNumber);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}

//try (RandomAccessFile raf = new RandomAccessFile("123/2.txt", "r")) {
//            raf.seek(2);
//            System.out.println((char) raf.read());
//            raf.seek(0);
//            System.out.println((char) raf.read());
//        }
package Lesson3;
//Последовательно сшить 5 файлов в один(файлы примерно 100байт).
//Может пригодиться следующая конструкция:ArrayList<InputStream> al=new ArrayList<>();...Enumeration<InputStream> e=Collections.enumeration(al);

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson3_2 {
    public static void main(String[] args) throws IOException {
        var filesPath = String.format("files_to_get_glued/");
        var filesPathDestination = "files_to_get_glued/allFiles.txt";
        List<InputStream> listOfFiles = new ArrayList<>();
        listOfFiles.add(new FileInputStream(filesPath + "1.txt"));
        listOfFiles.add(new FileInputStream(filesPath + "2.txt"));
        listOfFiles.add(new FileInputStream(filesPath + "3.txt"));
        listOfFiles.add(new FileInputStream(filesPath + "4.txt"));
        listOfFiles.add(new FileInputStream(filesPath + "5.txt"));
        new File(filesPathDestination).createNewFile();

        byte[] buff = new byte[1024];
        try (SequenceInputStream in = new SequenceInputStream(Collections.enumeration(listOfFiles));
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(filesPathDestination))) {
            int len;
            while ((len = in.read(buff)) > 0)
                out.write(buff, 0, len);
        }
    }

}

//    ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("123/2.txt"));
//        ali.add(new FileInputStream("123/test1.txt"));
//        ali.add(new FileInputStream("123/test2.txt"));
//
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//
//        int x;
//
//        while ((x = in.read()) != -1) {
//            System.out.print((char)x);
//        }
//
//        in.close();

package com.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Wang Mingzhen om 2018/6/11
 */
public class FileUtil {

//    static{
//        List<String> filelist = new ArrayList<>();
//        filelist.add("jpg");
//        filelist.add("doc");
//        filelist.add("docx");
//    }

    private static String[] va={"jpg","dox","docx"};

    private static boolean valid(String suffix){
        for(String s:va){
            if(suffix.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public static void upLoadFile(byte[] file, String filePath, String filename) throws IOException {
        if(valid(filename.substring(filename.lastIndexOf(".")+1,filename.length()))){
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdir();
            }
            FileOutputStream out = new FileOutputStream(filePath+filename);
            out.write(file);
            out.flush();
            out.close();
        }
    }
}

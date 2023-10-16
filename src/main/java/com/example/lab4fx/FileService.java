package com.example.lab4fx;

import java.io.*;

public class FileService implements Serializable {
    public static <T> T readFromFile(T obj, String URL){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(URL))){
            obj = (T) ois.readObject();
            System.out.println("Файл успешно прочитан.");
        }catch(IOException ex){
            System.err.println("Ошибка чтения файлов либо файл пуст");
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return obj;
    }
    public static <T> void writeInFile(T obj, String URL){
        ObjectOutputStream ous = null;
        try{
            ous = new ObjectOutputStream(new FileOutputStream(URL));
            ous.writeObject(obj);
            System.out.println("Данные успешно сохранены.");
        }catch (IOException e){
            System.out.println(e);
        }finally {
            try{
                ous.close();
            }catch (IOException e) {
                System.out.println(e.getMessage());

            }
        }
    }
}

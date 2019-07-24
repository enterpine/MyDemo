package com.jinsong.javabase;


import java.io.*;

public class SerializDemo {
    private static final File SAVE_FILE = new File("D:"+File.separator+"serializaObject");

    public static void main(String[] args) throws Exception{
        saveObject(new Person("靳松",25));
        System.out.println(loadObject());
    }

    public static void saveObject(Object obj) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
        oos.writeObject(obj);
        oos.close();
    }
    public static Object loadObject() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

}
@SuppressWarnings("serial")
class Person implements Serializable{
    private transient String name;
    private int age;
    Person(String a,int b){
        this.name = a;
        this.age = b;
    }
    @Override
    public String toString(){
        return this.name+this.age;
    }
}
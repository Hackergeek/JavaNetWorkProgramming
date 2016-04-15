/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 对象序列化;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *对象的序列化和反序列化测试类
 * @author skyward
 */
public class ObjectSaver {

    /**
     *
     */
    public static void main(String[] args) {
        try {
            //序列化对象
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\objectFile.obj"));
            Customer customer = new Customer("张三", 28);
            out.writeObject("你好！");
            out.writeObject(new Date());
            out.writeObject(customer);
            //写入基本类型
            out.writeInt(123);
            out.close();
            //反序列化对象
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\objectFile.obj"));
            System.out.println("obj1=" + in.readObject());
            System.out.println("obj2=" + in.readObject());
            System.out.println("obj3=" + in.readObject());
            System.out.println("obj4=" + in.readInt());
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class Customer implements Serializable {

    private String name;
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}

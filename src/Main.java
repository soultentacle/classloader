import com.gw.lean.classloader.MyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        //test2();
        System.out.println("--------------");
        test1();
    }

    public static void test1() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader(null);
        Class clazz = myClassLoader.loadClass("HelloWord");
        System.out.println(clazz.getClassLoader());
        Class msgClazz = myClassLoader.loadClass("Message");
        System.out.println(msgClazz.getClassLoader());
        Class stringClazz = myClassLoader.loadClass("java.lang.String");
        System.out.println(stringClazz.getClassLoader());
        Class stringClazz1 = myClassLoader.loadClass("java.lang.String");
        System.out.println(stringClazz1.getClassLoader());
        Method method = clazz.getMethod("say",msgClazz);
        method.invoke(clazz.newInstance(),msgClazz.getConstructor(stringClazz).newInstance("xxxx"));
        System.out.println("Hello World!");
    }


    public static void test2() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.loadClass("HelloWord");
        System.out.println(clazz.getClassLoader());
//        Class msgClazz = myClassLoader.loadClass("Message");
//        System.out.println(msgClazz.getClassLoader());
//        Method method = clazz.getMethod("say",msgClazz);
//        method.invoke(clazz.newInstance(),msgClazz.getConstructor(String.class).newInstance("xxxx"));
        Method method = clazz.getMethod("say", Message.class);
        method.invoke(clazz.newInstance(), new Message("xxxx"));
        System.out.println("Hello World!");
    }
}

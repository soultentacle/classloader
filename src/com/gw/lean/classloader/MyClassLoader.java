package com.gw.lean.classloader;

import java.io.*;

/**
 * Created by dell on 2018/8/6.
 */
public class MyClassLoader extends ClassLoader {

    private String path = "e:\\classes";

    private String suffix = ".class";


    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
        super();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(new File(path + File.separator + name.replace(".", File.separator) + suffix));
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, i);
            }
            byte[] classData = bos.toByteArray();
            Class clazz = this.defineClass(name, classData,0,classData.length);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        } finally {
            try {
                fis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

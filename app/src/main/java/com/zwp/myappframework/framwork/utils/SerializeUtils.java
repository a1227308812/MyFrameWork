package com.zwp.myappframework.framwork.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wang-dev
 */
public class SerializeUtils {

    /**
     * 反序列化
     *
     * @param filePath
     * @return
     */
    public static Object deserialization(String filePath) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            if (in == null) {
                return null;
            }
            Object o = in.readObject();
            in.close();
            return o;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
//            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
//            throw new RuntimeException("ClassNotFoundException occurred. ", e);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
//                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
        return null;
    }

    /**
     * 序列化
     *
     * @param filePath
     * @param obj
     */
    public static void serialization(String filePath, Object obj) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
//                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }
}

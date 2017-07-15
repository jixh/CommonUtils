package com.jktaihe.utils;

import org.apache.commons.lang.SerializationUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jktaihe on 2016-5-13.
 */
public class CollectionsUtils {

    /**
     * 判断两集合是否相等
     * @param array1
     * @param array2
     * @return
     */
    public static boolean isEquals(List array1, List array2){

        if (array1 == array2) return  true;

        if (array1 == null || array2 == null) return false;

        if (array1.size() != array2.size()) return false;

        for (int i = 0,l = array1.size();i<l;i++)
            if (!array1.get(i).equals(array2.get(i)))
                return false;

        return true;
    }

    /**
     * collection copy
     * @param destination
     * @param source
     */
    public static void copy(List destination, List<? extends Serializable> source){

        if (source == null){
            return;
        }

        if (destination.size()!=0){
            destination.clear();
            for (int i = 0,l = source.size(); i < l ; i++) {
                destination.add(SerializationUtils.clone(source.get(i)));
            }
        }

    }

    /**
     * 获得完全相同的集合
     * @param destination
     * @param source
     */
    public static void clone(List destination, List<? extends Serializable> source){
        if (source == null){
            destination = null;
        }else {

            if (destination != null){
                destination.clear();
            }else {
                destination = new ArrayList();
            }

            for (int i = 0,l = source.size(); i < l ; i++) {
                destination.add(SerializationUtils.clone(source.get(i)));
            }
        }
    }


}

package net.acyuta.utils;

/**
 * Created by acyuta on 05.12.14.
 */
public class C {
    public static void pn(Object... objects) {
        for (Object obj : objects)
            System.out.println(obj);
    }

    public static void p(Object... objects) {
        for (Object obj : objects)
            System.out.println(obj);
    }


    public static void die(Object object) {
        pn(object);
        System.exit(1);
    }
}

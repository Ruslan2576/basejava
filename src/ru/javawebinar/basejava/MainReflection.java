package ru.javawebinar.basejava;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ru.javawebinar.basejava.model.Resume;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Resume resume = new Resume("Зохан");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        //
        field.set(resume, "new uuid");
        Method method1 = resume.getClass().getMethod("toString");
        System.out.println(method1.invoke(resume));
    }
}

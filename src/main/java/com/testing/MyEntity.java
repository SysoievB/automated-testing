package com.testing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyEntity {

    @MyAutowired
    private  String name;

    @MyAutowired
    private  int age;

    public static void main(String[] args) throws Exception {
        AnnotationSetter annotationSetter = new AnnotationSetter();
        MyEntity myEntity = new MyEntity();
        myEntity.setName("John");
        myEntity.setAge(25);
        MyEntity newEntity = (MyEntity) annotationSetter.setAutowiredToConstructor(myEntity);
        System.out.println(myEntity.toString());
        System.out.println(myEntity.getClass().getDeclaredConstructor());
        System.out.println(newEntity.getName()); // Output: John
        System.out.println(newEntity.getAge());  // Output: 25
    }
}

package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;
    InnerObject inner;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        TestObject testObj = (TestObject) super.clone();
        testObj.inner = inner.clone();
        return testObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject testObj1 = new TestObject();
        testObj1.num = 5;
        InnerObject inner = new InnerObject();
        inner.num = 15;
        testObj1.inner = inner;
        TestObject testObj2 = testObj1.clone();
        testObj2.num = 25;
        testObj2.inner.num = 35;
        System.out.println("Исходный класс: " + testObj1.num);
        System.out.println("Исходный класс: " + testObj1.inner.num);
        System.out.println("Скопированный класс: " + testObj2.num);
        System.out.println("Скопированный класс: " + testObj2.inner.num);
    }
}
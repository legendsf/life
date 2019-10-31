package com.sf.jkt.k.biz.aop.cglib;

public class FastClass {
    public static void main(String[] args) {
        Test tt = new Test();
        Test2 fc = new Test2();
        int index = fc.getIndex("f()v");
        fc.invoke(index, tt, null);
    }
}

class Test {
    public void f() {
        System.out.println("f method");
    }

    public void g() {
        System.out.println("g method");
    }
}

class Test2 {
    public Object invoke(int index, Object o, Object[] o1) {
        Test t = (Test) o;
        switch (index) {
            case 1:
                t.f();
                return null;
            case 2:
                t.g();
                return null;
        }
        return null;
    }

    public int getIndex(String signature) {
        switch (signature.hashCode()) {
            case 3078479:
                return 1;
            case 3108270:
                return 2;
        }
        return -1;
    }

}

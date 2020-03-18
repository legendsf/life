package com.sf.jkt.k.algorithm.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MutiThreadTest {
    /***
     * 4个线程 2个对i加,2个对i减
     * 1、用 atomicLong 或者 stampAtomicReference
     * 2、用一个lock 对象 synchronized(lock){ do add/subtract}
     * 3、synchronized 方法
     */

    /***
     * 3个线程 打印A B C
     */

    public static void main(String[] args) {
        printMsg();
    }

    public static void printMsg() {

    }

}

class CyclicBarrierTest {
    static CountDownLatch exambegin = new CountDownLatch(1);
    static CyclicBarrier barrier = new CyclicBarrier(11);
    static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 100,
            1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    public static void testCyclicBarrier() throws Exception {
        for (int i = 0; i < 10; i++) {
            Runnable r = () -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println("考生:" + name + "开始等待考试");
                    exambegin.await();
                    System.out.println("考生听到铃声:" + name + "开始答题");
                    TimeUnit.MILLISECONDS.sleep((long) Math.random() * 100);
                    System.out.println("考生：" + name + "交卷");
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            pool.execute(r);
        }
        System.out.println("准备考试");
        TimeUnit.MILLISECONDS.sleep((long) Math.random() * 100);
        exambegin.countDown();
        System.out.println("开始考试");
        barrier.await();
        System.out.println("考试开始结束");


    }

    public static void main(String[] args) throws Exception{
        testCyclicBarrier();
    }
}

class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
//        waitAlltoEnd();
        waitFatherAndChildren();
    }

    public static void waitFatherAndChildren() throws Exception {
        CountDownLatch father = new CountDownLatch(1);
        CountDownLatch children = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    father.await();
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName() + "当前子线程运行");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    children.countDown();
                }
            }).start();
        }

        System.out.println("父线程开始运行");
        TimeUnit.SECONDS.sleep(3);
        father.countDown();
        System.out.println("父线程运行结束");
        System.out.println("子线程开始运行");
        children.await();
        System.out.println("子线程运行结束");


    }

    public static void waitAlltoEnd() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "：运行");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {

                } finally {
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("等待子线程运行结束");
        latch.await(10, TimeUnit.SECONDS);
        System.out.println("子线程运行结束");
    }
}

class Solution5 implements Runnable {
    private String msg;
    private Object prev;
    private Object self;

    public Solution5(String msg, Object prev, Object self) {
        this.msg = msg;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(msg);
                    self.notifyAll();
                    count--;
                }
                try {
                    prev.wait();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Object c = new Object();
        Object a = new Object();
        Object b = new Object();
        Solution5 s1 = new Solution5("A", c, a);
        Solution5 s2 = new Solution5("B", a, b);
        Solution5 s3 = new Solution5("C", b, c);
        new Thread(s1).start();
        new Thread(s2).start();
        new Thread(s3).start();
    }
}

class Solution4 {
    public Semaphore s1 = new Semaphore(1);
    public Semaphore s2 = new Semaphore(0);
    public Semaphore s3 = new Semaphore(0);

    Thread ta = new Thread(() -> {
        while (true) {
            try {
                s1.acquire();
                System.out.println("A");
                s2.release();
            } catch (Exception e) {

            }
        }
    });

    Thread tb = new Thread(() -> {
        while (true) {
            try {
                s2.acquire();
                System.out.println("B");
                s3.release();
            } catch (Exception e) {

            }
        }
    });

    Thread tc = new Thread(() -> {
        while (true) {
            try {
                s3.acquire();
                System.out.println("C");
                s1.release();
            } catch (Exception e) {

            }
        }
    });

    public static void main(String[] args) {
        Solution4 s4 = new Solution4();
        s4.tb.start();
        s4.tc.start();
        s4.ta.start();
    }

}

class Solution3 {
    ReentrantLock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();
    int state = 0;

    Thread ta = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                while (state != 0) {
                    try {
                        a.await();
                    } catch (Exception e) {

                    }
                }

                System.out.println("A");
                state = 1;
                b.signalAll();
            } finally {
                lock.unlock();
            }
        }

    });

    Thread tb = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                while (state != 1) {
                    try {
                        b.await();
                    } catch (Exception e) {

                    }
                }

                System.out.println("B");
                state = 2;
                c.signalAll();
            } finally {
                lock.unlock();
            }
        }

    });


    Thread tc = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                while (state != 2) {
                    try {
                        c.await();
                    } catch (Exception e) {

                    }
                }

                System.out.println("C");
                state = 0;
                a.signalAll();
            } finally {
                lock.unlock();
            }
        }

    });

    public static void main(String[] args) {
        Solution3 s3 = new Solution3();
        s3.tb.start();
        s3.tc.start();
        s3.ta.start();
    }

}

class Solution2 {
    ReentrantLock lock = new ReentrantLock();
    int state = 0;


    Thread ta = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                if (state % 3 == 0) {
                    System.out.println("A");
                    state++;
                }
            } finally {
                lock.unlock();
            }
        }
    });

    Thread tb = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                if (state % 3 == 1) {
                    System.out.println("B");
                    state++;
                }
            } finally {
                lock.unlock();
            }
        }
    });

    Thread tc = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                if (state % 3 == 2) {
                    System.out.println("C");
                    state++;
                }
            } finally {
                lock.unlock();
            }
        }
    });

    public static void main(String[] args) {
        Solution2 s1 = new Solution2();
        s1.tb.start();
        s1.tc.start();
        s1.ta.start();
    }

}

class Solution1 {
    Object lock = new Object();
    AtomicInteger state = new AtomicInteger();

    Thread ta = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                if (state.get() % 3 == 0) {
                    System.out.println("A");
                    state.getAndIncrement();
                }
            }
        }
    });
    Thread tb = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                if (state.get() % 3 == 1) {
                    System.out.println("B");
                    state.getAndIncrement();
                }
            }
        }
    });
    Thread tc = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                if (state.get() % 3 == 2) {
                    System.out.println("C");
                    state.getAndIncrement();
                }
            }
        }
    });

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        s1.tb.start();
        s1.tc.start();
        s1.ta.start();
    }
}



















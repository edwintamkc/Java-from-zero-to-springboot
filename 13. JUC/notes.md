# 1. Introduction

## 1.1 What is JUC?

Java Util - Concurrent Collection (JUC) are a set of collections APIs that are designed and optimized specifically for synchronized multithreaded access. They are grouped under the *java.util.concurrent* package.

## 1.2 Process and thread

- Process means a program is in execution, whereas **thread means a segment of a process.**
- A Process is not Lightweight, whereas Threads are Lightweight.
- A **Process takes more time to terminate**, and the **thread takes less time to terminate**.
- **Process takes more time for creation**, whereas **Thread takes less time for creation**.
- **Process** likely **takes more time for context switching** whereas as **Threads takes less time for context switching**.
- A **Process is mostly isolated**, whereas **Threads share memory**.
- **Process does not share data, and Threads share data with each other**.

![image-20211229215842210](notes.assets/image-20211229215842210.png)

> One way to view a thread is as an independent program counter operating **within** a process.

## 1.3 Concurrency and parallelism

`Concurrent`

- CPU 1 core, switch **rapidly** between threads **as if** many threads running together

`Parallel`

- CPU > 1 core, threads could run simultaneously

## 1.4 Why we need high concurrency?

> **Maximize CPU usage**

## 1.5 Process state

![image-20211229222744851](notes.assets/image-20211229222744851.png)

- `New`

  When a process is first created, it occupies the "**new**" state. In this state, the process awaits admission to the "ready" state.

- `Ready`

  A "**ready**" or "waiting" process has been loaded into **main memory** and is awaiting execution on a CPU

- `Running`

  A process moves into the running state when it is chosen (by CPU) for execution.

- `Blocked`

  A process transitions to a "**blocked**" state when it **cannot carry on** without an external change in state **or event occurring**. For example, a process may block on a call to an I/O device such as a printer, if the printer is not available. Processes also commonly block when they require user input, or require access to a critical section which must be executed atomically. Such critical sections are protected using a synchronization object such as a semaphore or mutex.

- `Suspend`

  The process would change to **suspend** state in some situations, such as:

  - **Swapping**

    The OS needs to release sufficient main memory to bring in a process that is ready to execute.

  - **Parent Process Request**

    A parent process may wish to suspend execution of a descendent to examine or modify the suspended process, or to coordinate the activity of various descendants.

  - **Interactive User Request**

    User may wish to suspend execution of a program for purpose of debugging

  - **Timing**

    A process may be executed periodically (e.g., an accounting or system monitoring process) and may be suspended while waiting for the next time.

- `Terminated`

  A process may be terminated, either from the "running" state by completing its execution or by explicitly being killed. In either of these cases, the process moves to the "**terminated**" state.

## 1.6 Thread state

> Refer to java.lang.Thread.State, there are 6 states in a thread

```java
public enum State {
    /**
     * Thread state for a thread which has not yet started.
     */
    NEW,

    /**
     * Thread state for a runnable thread.  A thread in the runnable
     * state is executing in the Java virtual machine but it may
     * be waiting for other resources from the operating system
     * such as processor.
     */
    RUNNABLE,

    /**
     * Thread state for a thread blocked waiting for a monitor lock.
     * A thread in the blocked state is waiting for a monitor lock
     * to enter a synchronized block/method or
     * reenter a synchronized block/method after calling
     * {@link Object#wait() Object.wait}.
     */
    BLOCKED,

    /**
     * Thread state for a waiting thread.
     * A thread is in the waiting state due to calling one of the
     * following methods:
     * <ul>
     *   <li>{@link Object#wait() Object.wait} with no timeout</li>
     *   <li>{@link #join() Thread.join} with no timeout</li>
     *   <li>{@link LockSupport#park() LockSupport.park}</li>
     * </ul>
     *
     * <p>A thread in the waiting state is waiting for another thread to
     * perform a particular action.
     *
     * For example, a thread that has called <tt>Object.wait()</tt>
     * on an object is waiting for another thread to call
     * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
     * that object. A thread that has called <tt>Thread.join()</tt>
     * is waiting for a specified thread to terminate.
     */
    WAITING,

    /**
     * Thread state for a waiting thread with a specified waiting time.
     * A thread is in the timed waiting state due to calling one of
     * the following methods with a specified positive waiting time:
     * <ul>
     *   <li>{@link #sleep Thread.sleep}</li>
     *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
     *   <li>{@link #join(long) Thread.join} with timeout</li>
     *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
     *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
     * </ul>
     */
    TIMED_WAITING,

    /**
     * Thread state for a terminated thread.
     * The thread has completed execution.
     */
    TERMINATED;
}
```

# 2. Synchronized and lock

> Usually, there are 2 methods to write concurrent program
>
> - Use the synchronized key word provided by Java
> - Use the java concurrent lock library

`Example used in this chapter`

**multiple threads modifying same resource (ticket)**

```java
public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "C").start();
    }
}

class Ticket {
    private int tickets = 500;

    public void sell(){
        if(tickets > 0){
            System.out.println("Ticket " + (tickets--) + " is sold by " + Thread.currentThread().getName() + ". Ticket left: " + tickets);
        }
    }
}
```

In this example, three threads (A, B, C) want to buy some tickets from class Ticket. Let's see the output.

![image-20211231132607955](notes.assets/image-20211231132607955.png)

The output is quite messy since threads are competing against each other and they are modifying the same resource.

## 2.1 Synchronized

> We could use the keyword synchronized to make sure only one thread can access the resource at a given point in time.

All synchronized blocks synchronize on the same object can only have one thread executing inside them at a time. All **other threads attempting to enter the synchronized block are blocked until the thread inside the synchronized block exits the block**.

![image-20211231132800948](notes.assets/image-20211231132800948.png)

## 2.2 Lock

> We could also adopts the**java.util.concurrent.locks library**to make sure only one thread can access the resource at a given point in time.
>
> ```java
>      Lock l = ...;
>      l.lock();
>      try {
>          // access the resource protected by this lock
>      } finally {
>          l.unlock();
>      }
> ```

![image-20211231133553915](notes.assets/image-20211231133553915.png)

There are three implementations of the lock interface. We would use reentrant lock here

![image-20211231134617142](notes.assets/image-20211231134617142.png)

## 2.3 Output

![image-20211231133115464](notes.assets/image-20211231133115464.png)

Output after using **synchronized** or **lock**

# 3. Producer and consumer problem

> **This is a typical problem for multithreading programming.**
>
> Background: There is a producer and a consumer in the market. When number of resource != 0, consumer could buy it; when it is == 0, producer need to produce.

`Example (including synchronized version and lock version)`

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        Chicken chicken = new Chicken();

        // there are 2 producers and 2 consumers
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer2").start();
    }
}

// synchronized version
class Chicken{
    private int quantity = 0;

    public synchronized void increment() throws InterruptedException {
        while(quantity != 0) { // there are some chicken left, no need to produce, keep waiting
            this.wait();
        }
        quantity++; // quantity = 0 now, need to produce 1 chicken for consumer
        System.out.println(Thread.currentThread().getName() + " produced 1 chicken. Quantity => " + quantity);
        notifyAll(); // notify consumer it is done
    }

    public synchronized void decrement() throws InterruptedException {
        while(quantity == 0) { // no chicken, keep waiting
            this.wait();
        }
        quantity--; // quantity > 0 now, consumer could buy 1 chicken
        System.out.println(Thread.currentThread().getName() + " consumed 1 chicken. Quantity => " + quantity);
        notifyAll(); // notify producer
    }
}

// lock version
class Chicken2{
    private int quantity = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition(); // use condition to wait and notify

    public  void increment() throws InterruptedException {
        lock.lock(); // add lock
        try {
            while(quantity != 0) {
                condition.await(); // **
            }
            quantity++;
            System.out.println(Thread.currentThread().getName() + " produced 1 chicken. Quantity => " + quantity);
            condition.signalAll(); // **
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // unlock
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(quantity == 0) {
                condition.await();
            }
            quantity--;
            System.out.println(Thread.currentThread().getName() + " consumed 1 chicken. Quantity => " + quantity);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

`output`

![image-20211231143909045](notes.assets/image-20211231143909045.png)

# 4. Safe list

> List is the most commonly used data structures in working. When we need to create a String list, usually we would write
>
> ```java
> List<String> list = new ArrayList<>();
> ```
>
> It seems good, however, when many threads modifying the list at the same time. It would throw **ConcurrentModificationException**!

`Example`

```java
public class SafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(list);
            }).start();
        }
    }
}
```

In this example, we create 10 threads, each thread put a String into the list. When we run the program, it ouputs:

![image-20220101174912553](notes.assets/image-20220101174912553.png)

> Conclusion: ArrayList is **NOT** thread safe.
>
> ![image-20220101175057272](notes.assets/image-20220101175057272.png)
>
> Its add() function simply grow the list by 1 capacity and then put the data to it. When multiple threads putting elements into the same position, it would throws exception.

`Thread safe list`

To ensure the list is thread safe, we could use the following methods:

- **Adopts Vector**

  ```java
  List<String> list = new Vector<>();
  ```

  It is thread safe because it applies the reentrance lock (synchronized keyword)

  ![image-20220101175443016](notes.assets/image-20220101175443016.png)

- **Adopts Collections.synchronizedList()**

  ```java
  List<String> list = Collections.synchronizedList(new ArrayList<>());
  ```

  It is thread safe because it use the mutex lock

  ![image-20220101180114327](notes.assets/image-20220101180114327.png)

- **Adopts CopyOnWriteArrayList**

  ```java
  List<String> list = new CopyOnWriteArrayList<>();
  ```

  CopyOnWriteArrayList is a library under Java concurrent package. When a thread wants to add object to it, it would NOT modify the original array directly. Instead, it would copy the old array to a new array, and then put the newly added element to the new array. Then set the array pointer to the new array. 

  ```java
  public void add(int index, E element) {
      final ReentrantLock lock = this.lock;
      lock.lock();
      try {
          Object[] elements = getArray();
          int len = elements.length;
          if (index > len || index < 0)
              throw new IndexOutOfBoundsException("Index: "+index+
                                                  ", Size: "+len);
          Object[] newElements;
          int numMoved = len - index;
          if (numMoved == 0)
              newElements = Arrays.copyOf(elements, len + 1);
          else {
              newElements = new Object[len + 1];
              System.arraycopy(elements, 0, newElements, 0, index);
              System.arraycopy(elements, index, newElements, index + 1,
                               numMoved);
          }
          newElements[index] = element;
          setArray(newElements);
      } finally {
          lock.unlock();
      }
  }
  ```

# 5. Runnable and Callable

> Review: there are 3 types of method to create a thread
>
> - Use the thread class
> - Implement Runnable interface
> - Implement Callable interface

In previous examples,  method 1 is used commonly. In this chapter, we would go through Runnable and Callable methods.

## 5.1 Runnable

```java
public class MyRunnable {
    public static void main(String[] args) {
        new Thread(new MyThread2()).start();
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}
```

Step:

1. Write a class implement Runnable interface
2. Create a thread using new Thread()
3. Send a Runnable implementation to Thread constructor as a parameter

4. Call the start method

## 5.2 Callable

```java
public class MyCallable {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new MyThread());
        new Thread(task).start();
    }
}

class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "Call!";
    }
}
```

Step:

1. Write a class implement Callable interface

2. Create a FutureTask object, send Callable implementation as argument

3. Create thread object, send FutureTask as argument

   **Thread class constructor only accepts Runnable object, we could not use a Callable object to create a thread. However, we could use FutureTask as a medium, since future task class is one of the implementations of Callable interface and it accepts Callable as constructor argument.**


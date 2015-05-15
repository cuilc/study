package producerconsumer;

import java.util.Date;

public class Test {

   public static void main(String[] args) {
      EventStorage<Date> storage = new EventStorage<Date>();
      Producer producer = new Producer(storage);
      Thread thread1 = new Thread(producer);
      
      Consumer consumer = new Consumer(storage);
      Thread thread2 = new Thread(consumer);
      
      thread1.start();
      thread2.start();
      
//      try {
//         thread1.join();
//         thread2.join();
//      } catch (InterruptedException e) {
//         e.printStackTrace();
//      }
      
//      while(true) {
//         
//      }
   }

}

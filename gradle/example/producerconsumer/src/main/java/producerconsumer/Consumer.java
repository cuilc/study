package producerconsumer;

import java.util.Date;

public class Consumer implements Runnable {

   private EventStorage storage;

   public Consumer(EventStorage storage) {
      super();
      this.storage = storage;
   }

   @Override
   public void run() {
      for(int i=0; i<100; i++) {
         Date d = (Date) storage.get();
      }
   }


}

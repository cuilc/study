/**
 * 
 */
package producerconsumer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author cuiliangcai
 *
 */
public class EventStorage<T> {
   private int maxSize;
   private List<T> storage;
   
   public EventStorage() {
      maxSize = 10;
      storage = new LinkedList<T>();
   }
   
   public synchronized void set(T v) {
      while(storage.size() == maxSize) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      storage.add(v);
      System.out.printf("Set: %d: %s ", storage.size(), v);
      System.out.println();
      notifyAll();
   }
   
   public synchronized T get() {
      while(storage.isEmpty()) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      T d = (T)(((LinkedList) storage).poll());
      System.out.printf("  Get: %d: %s", storage.size(), d);
      System.out.println();
      notifyAll();
      return d;
   }
}

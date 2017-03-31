import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author andreis
 */

class MyInvalidPathException extends Exception {
	
	MyInvalidPathException() {}

	MyInvalidPathException(String mesaj) {
		super(mesaj);
	}
}

class MyPathTooLongException extends MyInvalidPathException{
	
	MyPathTooLongException() {}

	MyPathTooLongException(String mesaj) {
		super();
	}
}

class Subject {
	
   private static List<Observer> observers = new ArrayList<Observer>();
   private static Exception state;

   public static Exception getState() {
      return state;
   }

   public static void setState(Exception state) {
      Subject.state = state;
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);		
   }

   public static void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
}

abstract class Observer {
   protected Subject subject;
   public abstract void update();
}

class ExceptionObserver extends Observer{
	Logger l;
   public ExceptionObserver(Subject subject) throws IOException{
      this.subject = subject;
      this.subject.attach(this);
	  l = Logger.getLogger("logger");
	  Handler handler = new FileHandler("test.log");
	  handler.setFormatter(new SimpleFormatter());
	  l.addHandler(handler);
   }

   @Override
   public void update() {
		try {
			l.log(Level.SEVERE, subject.getState().getMessage());
			throw subject.getState();
		} catch (Exception ex) {}
   }
}
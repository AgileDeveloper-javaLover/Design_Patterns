package Behavioural.mediator;
import java.util.ArrayList;
import java.util.List;

class Participant
{
  private Mediator mediator;
  public int value;

  public Participant(Mediator mediator)
  {
    this.mediator = mediator;
    mediator.addListener(this);
  }

  public void say(int n)
  {
    mediator.broadcast(this, n);
  }

  public void notify(Object sender, int n)
  {
    if (sender != this)
      value += n;
  }
}

class Mediator
{
  private List<Participant> listeners = new ArrayList<>();

  public void broadcast(Object sender, int n)
  {
    for (Participant p : listeners)
      p.notify(sender, n);
  }

  public void addListener(Participant p)
  {
    listeners.add(p);
  }
}

public class Exercise {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Participant p1 = new Participant(mediator);
        Participant p2 = new Participant(mediator);
    
        System.out.println( p1.value);
        System.out.println(p2.value);
    
        p1.say(2);
    
        System.out.println(p1.value);
        System.out.println(p2.value);
    
        p2.say(4);
    
        System.out.println(p1.value);
        System.out.println(p2.value);
    }
    
}

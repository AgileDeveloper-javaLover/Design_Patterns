package Behavioural.state;


class CombinationLock
{
  private int [] combination;

  public String status;
  private int digitsEntered = 0;
  private boolean failed = false;

  public CombinationLock(int[] combination)
  {
    this.combination = combination;
    reset();
  }

  private void reset()
  {
    status = "LOCKED";
    digitsEntered = 0;
    failed = false;
  }

  public void enterDigit(int digit)
  {
    if (status.equals("LOCKED")) status = "";
    status += digit;
    if (combination[digitsEntered] != digit)
    {
      failed = true;
    }
    digitsEntered++;

    if (digitsEntered == combination.length)
      status = failed ? "ERROR" : "OPEN";
  }
}

public class Exercise {
    public static void main(String[] args) {
        CombinationLock cl = new CombinationLock(new int[]{1, 2, 3, 4});
        System.out.println(cl.status); //"LOCKED"
    
        cl.enterDigit(1);
        System.out.println(cl.status); //"1"
    
        cl.enterDigit(2);
        System.out.println(cl.status);  //"12"
    
        cl.enterDigit(3);
        System.out.println(cl.status); //"123"
    
        cl.enterDigit(4);
        System.out.println(cl.status);    //"OPEN"
      }
    }
}

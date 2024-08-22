package Behavioural.command;

class Command
{
  enum Action
  {
    DEPOSIT, WITHDRAW
  }

  public Action action;
  public int amount;
  public boolean success;

  public Command(Action action, int amount)
  {
    this.action = action;
    this.amount = amount;
  }
}

class Account
{
  public int balance;

  public void process(Command c)
  {
    switch (c.action)
    {
      case DEPOSIT:
        balance += c.amount;
        c.success = true;
        break;
      case WITHDRAW:
        c.success = balance >= c.amount;
        if (c.success) balance -= c.amount;
        break;
    }
  }
}

public class Exercise{
  public static void main(String[] args) {
    Account a = new Account();

    Command command = new Command(Command.Action.DEPOSIT, 100);
    a.process(command);

    System.out.println(a.balance);
    System.out.println(command.success);

    command = new Command(Command.Action.WITHDRAW, 50);
    a.process(command);

    System.out.println( a.balance);
    System.out.println(command.success);

    command = new Command(Command.Action.WITHDRAW, 150);
    a.process(command);

    System.out.println( a.balance);
    System.out.println(command.success);
  }
}
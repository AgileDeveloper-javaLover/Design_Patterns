package Behavioural.momento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token
{
  public int value = 0;

  public Token(int value)
  {
    this.value = value;
  }
}

class Memento
{
  public List<Token> tokens = new ArrayList<>();
}

class TokenMachine
{
  public List<Token> tokens = new ArrayList<>();

  public Memento addToken(int value)
  {
    return addToken(new Token(value));
  }

  public Memento addToken(Token token)
  {
    tokens.add(token);
    Memento m = new Memento();
    m.tokens = tokens.stream()
      .map(t -> new Token(t.value))
      .collect(Collectors.toList());
    return m;
  }

  public void revert(Memento m)
  {
    tokens = m.tokens.stream()
      .map(t -> new Token(t.value))
      .collect(Collectors.toList());
  }
}

public class Exercise {
    public static void main(String[] args) {

        //singleTokenTest
        TokenMachine tm = new TokenMachine();
        Memento m = tm.addToken(123);
        tm.addToken(456);
        tm.revert(m);
    
        System.out.println(tm.tokens.size());
        System.out.println(tm.tokens.get(0).value);


        //twoTokenTest
        tm = new TokenMachine();
        tm.addToken(1);
        m = tm.addToken(2);
        tm.addToken(3);
        tm.revert(m);
        System.out.println(tm.tokens.size());
        System.out.println(tm.tokens.get(0).value);
        System.out.println(tm.tokens.get(1).value);

        
        //fiddledTokenTest
        tm = new TokenMachine();
        System.out.println("Made a token with value 111 and kept a reference");
        Token token = new Token(111);
        System.out.println("Added this token to the list");
        tm.addToken(token);
        m = tm.addToken(222);
        System.out.println("Changed this token's value to 333 :)");
        token.value = 333;
        tm.revert(m);
    
        System.out.println( tm.tokens.size()
        );
    
        System.out.println(tm.tokens.get(0).value);
    }
    
}

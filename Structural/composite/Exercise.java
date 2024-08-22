import java.util.*;

interface ValueContainer extends Iterable<Integer> {}

class SingleValue implements ValueContainer
{
  public int value;

  public SingleValue(int value)
  {
    this.value = value;
  }

  @Override
  public Iterator<Integer> iterator()
  {
    return Collections.singleton(value).iterator();
  }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{
  public MyList(Collection<? extends ValueContainer> c)
  {
    super(c);
  }

  public int sum()
  {
    int result = 0;
    for (ValueContainer c : this)
    {
      for (int i : c)
      {
        result += i;
      }
    }
    return result;
  }
}

public class Exercise
{
  public static void main(String[] args)
  {
    SingleValue singleValue = new SingleValue(11);
    ManyValues otherValues = new ManyValues();
    otherValues.add(22);
    otherValues.add(33);
    System.out.println(new MyList(List.of(singleValue, otherValues)).sum());
  }
}

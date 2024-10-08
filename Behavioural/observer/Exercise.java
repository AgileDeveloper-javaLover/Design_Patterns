package Behavioural.observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

class Event<T> {
    private List<BiConsumer<Object, T>> consumers = new ArrayList<>();

    public void subscribe(BiConsumer<Object, T> consumer) {
        consumers.add(consumer);
    }

    public void invoke(Object sender, T arg) {
        for (BiConsumer<Object, T> consumer : consumers)
            consumer.accept(sender, arg);
    }
}

class Game {
    public Event<Void> ratEnters = new Event<>();
    public Event<Void> ratDies = new Event<>();
    public Event<Rat> notifyRat = new Event<>();
}

class Rat implements Closeable {
    private Game game;
    public int attack = 1;

    public Rat(Game game) {
        this.game = game;
        game.ratEnters.subscribe((sender, arg) -> {
            if (sender != this) {
                ++attack;
                game.notifyRat.invoke(this, (Rat) sender);
            }
        });
        game.notifyRat.subscribe((sender, rat) -> {
            if (rat == this)
                ++attack;
        });
        game.ratDies.subscribe((sender, arg) -> --attack);
        game.ratEnters.invoke(this, null);
    }

    @Override
    public void close() throws IOException {
        // rat dies ;(
        game.ratDies.invoke(this, null);
    }
}

public class Exercise {
    public static void main(String[] args) throws IOException {
        // two rats test
        Game game = new Game();
        Rat rat = new Rat(game);
        Rat rat2 = new Rat(game);
        System.out.println(rat.attack);
        System.out.println(rat2.attack);

        // three rats test

        game = new Game();

        rat = new Rat(game);
        System.out.println(rat.attack);

        rat2 = new Rat(game);
        System.out.println(rat.attack);
        System.out.println(rat2.attack);

        try (Rat rat3 = new Rat(game)) {
            System.out.println(rat.attack);
            System.out.println(rat2.attack);
            System.out.println(rat3.attack);
        }

        System.out.println(rat.attack);
        System.out.println(rat2.attack);
    }

}

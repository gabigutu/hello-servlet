package com.helloservlet.HelloServlet;

import java.util.ArrayList;
import java.util.List;

public class Basket<T> {

    private List<T> elements;

    public Basket() {
        elements = new ArrayList<>();
    }

    public void addToBasked(T element) {
        this.elements.add(element);
    }

    public T getElement(int index) {
        return elements.get(index);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : elements) {
            sb.append(element + ", ");
        }
        String str = sb.toString().trim();
        return str.substring(0, str.length() - 1);
    }

    public static void main() {
        Basket<Fruit> fruitBasket = new Basket<>();
        Apple apple = new Apple();
        Pear pear = new Pear();
        fruitBasket.addToBasked(apple);
        fruitBasket.addToBasked(pear);
        System.out.println(fruitBasket);
    }

}

class Fruit {
}

class Apple extends Fruit {
}

class Pear extends Fruit {
}

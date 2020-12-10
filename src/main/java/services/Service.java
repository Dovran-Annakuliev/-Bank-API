package services;

import java.util.List;

public interface Service<A, C, E> {
    public void createNewCard(A a);
    public List<C> getAllCards(A a);
    public void deposit(A a, E e);
    public E getBalance(A a);
}

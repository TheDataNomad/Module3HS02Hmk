package space.harbour.java.hm9;

public interface ContainerObservable {
    public void addObserver(AtmObserver observer);

    public void removeObserver(AtmObserver observer);

    public void notifyObservers(String message);

}

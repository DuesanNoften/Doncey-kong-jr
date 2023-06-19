package DataStructures;

class NodeList<T> {
    T data;
    NodeList<T> next;
    NodeList<T> prev;

    public NodeList(T data) {
        this.data = data;
    }
}

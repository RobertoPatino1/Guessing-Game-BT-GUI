
package TDAs;



public class CircularNode<E> {
    private E content;
    private CircularNode<E> next;
    private CircularNode<E> previous;
    public CircularNode(E data){
        this.content = data;
        this.next=null;
        this.previous=null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularNode<E> getNext() {
        return next;
    }

    public void setNext(CircularNode<E> next) {
        this.next = next;
    }

    public CircularNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CircularNode<E> previous) {
        this.previous = previous;
    }
    
}

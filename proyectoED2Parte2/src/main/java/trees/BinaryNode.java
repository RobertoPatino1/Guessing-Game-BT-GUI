
package trees;

public class BinaryNode<E> {
    private E content;
    private BinaryTree<E> left;
    private BinaryTree<E> right;
    
    public BinaryNode(E content){
        this.content = content;
        this.left = null;
        this.right = null;
    }

    public BinaryNode(E content, BinaryTree<E> left, BinaryTree<E> right) {
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }
    
    
}

package eg.edu.alexu.csd.filestructure.sort;

public class INodeImpl<T extends Comparable<T>> implements INode<T> {
    @Override
    public INode<T> getLeftChild() {
        return null;
    }

    @Override
    public INode<T> getRightChild() {
        return null;
    }

    @Override
    public INode<T> getParent() {
        return null;
    }

    @Override
    public T getValue() {
        return null;
    }

    @Override
    public void setValue(T value) {

    }
}

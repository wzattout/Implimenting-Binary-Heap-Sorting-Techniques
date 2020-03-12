package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Node<T extends Comparable<T>> implements INode<T> {
    private int index;
    private T value;
    private ArrayList<INode<T>> heapArray;
    public Node(T value,int index , ArrayList<INode<T>> heapArray){
        this.value=value;
        this.index=index;
        this.heapArray = heapArray;
    }
    @Override
    public INode<T> getLeftChild() {
        int leftChildIndex=2*this.index;
        if(leftChildIndex  >=  this.heapArray.size())
            return null;
        return this.heapArray.get(leftChildIndex);
    }

    @Override
    public INode<T> getRightChild() {
        int RightChildIndex=2*this.index+1;
        if(RightChildIndex  >=  this.heapArray.size())
            return null;
        return this.heapArray.get(RightChildIndex);
    }

    @Override
    public INode<T> getParent() {
        int parentIndex = this.index/2 ;
        if(parentIndex  < 1)
            return null;
        return this.heapArray.get(parentIndex);
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value=value;
    }
}

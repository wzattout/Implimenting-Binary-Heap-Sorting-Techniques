package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
    private ArrayList<INode<T>> heapArray;
    private int size;
    public Heap(){
        size=0;
        heapArray=new ArrayList<>(1000);
        heapArray.add(null);
    }
    @Override
    public INode<T> getRoot() {
        if( this.size() > 0)
            return heapArray.get(1);
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void heapify(INode<T> node) {
        if(node == null)
            return;
        INode<T> largest;
        INode<T> leftChild=node.getLeftChild();
        INode<T> rightChild=node.getRightChild();
        if(rightChild != null){

            if(leftChild.getValue().compareTo(rightChild.getValue()) > 0)
                largest = leftChild;
            else
                largest = rightChild;

        } else if(leftChild != null){

            largest=leftChild;

        }else{
            return;
        }

        if(node.getValue().compareTo(largest.getValue()) < 0){
            swapValue(node,largest);
            heapify(largest);
        }

    }

    @Override
    public T extract() {
        INode<T> root=this.getRoot();
        if(root == null)
            return null;
        T value=root.getValue();
        this.swapValue(root,heapArray.get(this.size()));
        this.size--;
        if(this.size()!=0)
            heapify(root);
        return value;
    }

    @Override
    public void insert(T element) {
        if(element == null)
            return;
        this.size++;
        if(this.size >= heapArray.size())
            heapArray.add(new Node<T>(element,this.size()));
        else
            heapArray.get(this.size()).setValue(element);

        INode<T> child = heapArray.get(this.size());
        INode<T> parent=child.getParent();
        while ( parent != null && child.getValue().compareTo(parent.getValue()) > 0 ){
            swapValue(child,parent);
            child=parent;
            parent=child.getParent();
        }
    }

    @Override
    public void build(Collection<T> unordered) {

        if(unordered == null)
            return;
        Iterator<T> iterator = unordered.iterator();
        while (iterator.hasNext()) {
            size++;
            heapArray.add(new Node<T>(iterator.next(),size)) ;
        }

        for(int i=this.size() ; i>0 ; i--)
            heapify(heapArray.get(i));
    }

    private void swapValue(INode<T> node1,INode<T> node2){
        T template=node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(template);
    }
    public class Node<T extends Comparable<T>> implements INode<T> {
        private int index;
        private T value;
        public Node(T value,int index ){
            this.value=value;
            this.index=index;
        }
        @Override
        public INode<T> getLeftChild() {
            int leftChildIndex=2*this.index;
            if(leftChildIndex  >  size)
                return null;
            return (INode<T>) heapArray.get(leftChildIndex);
        }

        @Override
        public INode<T> getRightChild() {
            int RightChildIndex=2*this.index+1;
            if(RightChildIndex  >  size )
                return null;
            return (INode<T>) heapArray.get(RightChildIndex);
        }

        @Override
        public INode<T> getParent() {
            int parentIndex = this.index/2 ;
            if(parentIndex  < 1)
                return null;
            return (INode<T>) heapArray.get(parentIndex);
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

}

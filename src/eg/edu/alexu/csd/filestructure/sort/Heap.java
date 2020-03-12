package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
    private ArrayList<INode<T>> heapArray;
    public Heap(){
        heapArray=new ArrayList<INode<T>>();
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
        return heapArray.size()-1;
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
        heapArray.remove(this.size());
        if(this.size()!=0)
            heapify(root);
        return value;
    }

    @Override
    public void insert(T element) {
        if(element == null)
            return;
        INode<T> child = new Node<T>(element,this.size()+1,heapArray);
        heapArray.add(child);
        INode<T> parent=child.getParent();
        while (parent != null && child.getValue().compareTo(parent.getValue()) > 0){
            swapValue(child,parent);
            child=parent;
            parent=child.getParent();
        }
    }

    @Override
    public void build(Collection<T> unordered) {
        heapArray=new ArrayList<INode<T>>();
        heapArray.add(null);
        if(unordered == null)
            return;
        Iterator<T> iterator = unordered.iterator();
        int index=1;
        while (iterator.hasNext()) {
            heapArray.add(new Node<T>(iterator.next(),index,heapArray)) ;
            index++;
        }

        for(int i=this.size() ; i>0 ; i--)
            heapify(heapArray.get(i));
    }

    private void swapValue(INode<T> node1,INode<T> node2){
        T template=node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(template);
    }
}

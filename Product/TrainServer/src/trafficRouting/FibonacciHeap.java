/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author JamesFoxes
 */
public class FibonacciHeap<E>
{

    private HeapElement<E> minimumHeapElement = null;
    private int heapSize = 0;

    public HeapElement<E> enqueueElement(HeapElement<E> element)
    {
        return enqueueElement(element.getNodeContents(), element.getNodePriority());
    }

    public HeapElement<E> enqueueElement(E value, double priority)
    {
        checkPriorityValidity(priority);

        HeapElement<E> newElement = new HeapElement<>(value, priority);
        minimumHeapElement = mergeListsAtMinPointer(minimumHeapElement, newElement);
        ++heapSize;

        return newElement;
    }

    private void checkPriorityValidity(double priority)
    {
        if (Double.isNaN(priority))
        {
            throw new IllegalArgumentException(priority + " is invalid.");
        }
    }

    public static <E> FibonacciHeap<E> mergeHeaps(FibonacciHeap<E> firstHeap, FibonacciHeap<E> secondHeap)
    {
        if (firstHeap == null && secondHeap == null)
        {
            return new FibonacciHeap<>();
        } else if (firstHeap != null && secondHeap == null)
        {
            return firstHeap;
        } else if (firstHeap == null && secondHeap != null)
        {
            return secondHeap;
        } else
        {
            FibonacciHeap<E> mergedHeap = new FibonacciHeap<E>();

            mergedHeap.minimumHeapElement = mergeListsAtMinPointer(firstHeap.minimumHeapElement, secondHeap.minimumHeapElement);
            mergedHeap.heapSize = firstHeap.heapSize + secondHeap.heapSize;

            firstHeap.heapSize = secondHeap.heapSize = 0;
            firstHeap.minimumHeapElement = null;
            secondHeap.minimumHeapElement = null;

            return mergedHeap;
        }
    }

    public HeapElement<E> dequeueMinElement()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Heap is empty.");
        }

        HeapElement<E> currentMinElement = minimumHeapElement;

        minimumHeapElement = removeAndReassignElement(minimumHeapElement);
        clearParentFromChildren(currentMinElement);

        minimumHeapElement = mergeListsAtMinPointer(minimumHeapElement, currentMinElement.childNode); //problem with delete unchecked. has no children

        if (isEmpty())
        {
            return currentMinElement;
        }

        restructureTrees();

        return currentMinElement;
    }

    public void deleteElementNoCheck(HeapElement<E> node)
    {
        decreaseKeyUnchecked(node, Double.NEGATIVE_INFINITY);
        dequeueMinElement();
    }

    public void decreaseKey(HeapElement<E> node, double newPriority)
    {
        checkPriorityValidity(newPriority);
        if (newPriority > node.priority)
        {
            throw new IllegalArgumentException("New priority exceeds old.");
        }
        decreaseKeyUnchecked(node, newPriority);
    }

    public HeapElement<E> getMinElement()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Heap is empty.");
        }
        return minimumHeapElement;
    }

    public boolean isEmpty()
    {
        return minimumHeapElement == null;
    }

    public int getHeapSize()
    {
        return heapSize;
    }

    private HeapElement<E> removeAndReassignElement(HeapElement<E> node)
    {
        if (node.nextNode == node)  //only one element existed
        {
            --heapSize;
            return null;
        } else
        {
            disconnectFromSiblings(node);
            --heapSize;
            return node.nextNode;
        }
    }

    private void disconnectFromSiblings(HeapElement<E> node)
    {
        node.previousNode.nextNode = node.nextNode;
        node.nextNode.previousNode = node.previousNode;
    }

    private void clearParentFromChildren(HeapElement<E> parent)
    {
        if (parent.childNode != null)
        {
            HeapElement<?> childOfCurrentCopy = parent.childNode;
            do
            {
                childOfCurrentCopy.parentNode = null;
                childOfCurrentCopy = childOfCurrentCopy.nextNode;
            } while (childOfCurrentCopy != parent.childNode);    //stop at full traversal
        }
    }

    private void restructureTrees()
    {
        //the sorted list of trees has trees of size i at index i, or null.
        List<HeapElement<E>> sortedListOfTrees = new ArrayList<>();
        List<HeapElement<E>> unsortedTreesToVisit = fillListOfCurrentRoots();

        for (HeapElement<E> tree : unsortedTreesToVisit)
        {
            while (true)    //Operate while more trees exist
            {
                int numberOfChildren = tree.numberOfChildren;
                while (numberOfChildren >= sortedListOfTrees.size())    //expand list depending on tree size. Has trees of size i at index i.
                {
                    sortedListOfTrees.add(null);
                }

                if (sortedListOfTrees.get(numberOfChildren) == null)    //get slot if available (no other tree this size exists)
                {
                    sortedListOfTrees.set(numberOfChildren, tree);
                    break;
                }

                HeapElement<E> existingTreeToMerge = sortedListOfTrees.get(numberOfChildren);
                sortedListOfTrees.set(numberOfChildren, null);

                tree = combineTrees(tree, existingTreeToMerge); //Never sets combined tree!!! INVESTIGATE.
            }

            if (tree.priority <= minimumHeapElement.priority)
            {
                minimumHeapElement = tree;
            }
        }
    }

    private List<HeapElement<E>> fillListOfCurrentRoots()
    {
        List<HeapElement<E>> roots = new ArrayList<HeapElement<E>>();
        for (HeapElement<E> currentRoot = minimumHeapElement; roots.isEmpty() || roots.get(0) != currentRoot; currentRoot = currentRoot.nextNode)
        {
            roots.add(currentRoot);
        }
        return roots;
    }

    private HeapElement<E> combineTrees(HeapElement<E> insertTree, HeapElement<E> existingTree)
    {
        HeapElement<E> smallestTree = (existingTree.priority < insertTree.priority) ? existingTree : insertTree;
        HeapElement<E> largestTree = (existingTree.priority < insertTree.priority) ? insertTree : existingTree;

        return putMaxTreeasMinRoot(largestTree, smallestTree);
    }

    private HeapElement<E> putMaxTreeasMinRoot(HeapElement<E> largestTree, HeapElement<E> smallestTree)
    {
        disconnectFromSiblings(largestTree);

        largestTree.nextNode = largestTree.previousNode = largestTree;
        smallestTree.childNode = mergeListsAtMinPointer(smallestTree.childNode, largestTree);

        largestTree.parentNode = smallestTree;
        largestTree.isMarked = false;
        ++smallestTree.numberOfChildren;

        return smallestTree;
    }

    private static <E> HeapElement<E> mergeListsAtMinPointer(HeapElement<E> firstList, HeapElement<E> secondList)
    {
        //null checks:
        if (firstList == null && secondList == null)
        {
            return null;
        } else if (firstList != null && secondList == null)
        {
            return firstList;
        } else if (firstList == null && secondList != null)
        {
            return secondList;
        } else
        {
            HeapElement<E> firstListNextNode = firstList.nextNode;

            firstList.nextNode = secondList.nextNode;
            firstList.nextNode.previousNode = firstList;    //secondList = first

            secondList.nextNode = firstListNextNode;
            secondList.nextNode.previousNode = secondList;

            return firstList.priority < secondList.priority ? firstList : secondList;
        }
    }

    private void decreaseKeyUnchecked(HeapElement<E> node, double newPriority)
    {
        node.priority = newPriority;

        if (node.parentNode != null && node.priority <= node.parentNode.priority)
        {
            cutNode(node);
        }

        if (node.priority <= minimumHeapElement.priority)
        {
            minimumHeapElement = node;
        }
    }

    private void cutNode(HeapElement<E> node)
    {
        node.isMarked = false;

        if (node.parentNode == null)
        {
            return;
        }

        if (node.nextNode != node)
        {
            disconnectFromSiblings(node);
        }

        if (node.parentNode.childNode == node)
        {
            if (node.nextNode != node)
            {
                node.parentNode.childNode = node.nextNode; //Rewire parent node
            } else
            {
                node.parentNode.childNode = null;
            }
        }

        --node.parentNode.numberOfChildren;

        node.previousNode = node.nextNode = node;   //clean up
        minimumHeapElement = mergeListsAtMinPointer(minimumHeapElement, node);

        //Fibonacci Heap cuts parent node when two children cut:
        if (node.parentNode.isMarked)
        {
            cutNode(node.parentNode);
        } else
        {
            node.parentNode.isMarked = true;
        }

        node.parentNode = null;
    }

    public static final class HeapElement<E>
    {

        private E nodeValue;
        private double priority;

        private int numberOfChildren = 0;
        private boolean isMarked = false;

        private HeapElement<E> nextNode;
        private HeapElement<E> previousNode;

        private HeapElement<E> parentNode;
        private HeapElement<E> childNode;

        public HeapElement(E elementContents, double priority)
        {
            nextNode = previousNode = this;
            nodeValue = elementContents;
            this.priority = priority;
        }

        public E getNodeContents()
        {
            return nodeValue;
        }

        public void setNodeContents(E value)
        {
            nodeValue = value;
        }

        public double getNodePriority()
        {
            return priority;
        }
    }
}

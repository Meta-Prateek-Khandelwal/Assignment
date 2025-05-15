import java.util.ArrayList;
import java.util.List;

interface Dictionary{
    void add(Pair pair);
    Node delete(int key);
    String getValue(int key);
    List<Pair> sortedKeyValuePair();
    List<Pair> keysBetweenRange(int k1, int k2);
}

class Pair{
    int key;
    String val;
    Pair(int key, String val){
        this.key = key;
        this.val = val;
    }
}

class Node{
    Pair pair;
    Node left;
    Node right;
    Node(Pair pair){
        this.pair = pair;
        left = null;
        right = null;
    }
}

class BST implements Dictionary{
    Node root;
    static List<Pair> dictionaryPairs;
    static List<Pair> dictionary;

    BST(){
       root = null; 
       dictionaryPairs = new ArrayList<>();
       dictionary = new ArrayList<>();
    }

    private Node insertUtil(Node curNode, Pair pair){
        if(curNode == null){
            return new Node(pair);
        }

        if(pair.key < curNode.pair.key){
            curNode.left = insertUtil(curNode.left, pair);
        }else if(pair.key > curNode.pair.key){
            curNode.right = insertUtil(curNode.right, pair);
        }

        return curNode;
    }

    @Override
    public void add(Pair pair) {
        root = insertUtil(root, pair);
    }

    private Node findInorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }

        return root;
    }

    private Node deleteUtil(Node curNode, int key){
        if(curNode == null){
            return curNode;
        }

        if(curNode.pair.key > key){
            curNode.left = deleteUtil(curNode.left, key);
        }else if(curNode.pair.key < key){
            curNode.right = deleteUtil(curNode.right, key);
        }else{
            // leaf node
            if(curNode.left == null && curNode.right == null) return null;
            // one child
            else if(curNode.left == null) {
                return curNode.right;
            }else if(curNode.right == null){
                return curNode.left;
            }
            // two child
            Node inorderSuccessorNode = findInorderSuccessor(curNode.right);
            curNode.pair.key = inorderSuccessorNode.pair.key;
            curNode.pair.val = inorderSuccessorNode.pair.val;
            curNode.right = deleteUtil(curNode.right, inorderSuccessorNode.pair.key);
        }
        return curNode;
    }

    @Override
    public Node delete(int key) {
        root = deleteUtil(root, key);
        return root;
    }

    private String getValueUtil(Node head, int key){
        if(head == null){
            return null;
        }

        if(head.pair.key == key){
            return head.pair.val;
        }else if(head.pair.key > key){
            return getValueUtil(head.left, key);
        }else {
            return getValueUtil(head.right, key);
        }
    }

    @Override
    public String getValue(int key) {
        String val = getValueUtil(root, key);
        return val != null ? val : "Key not found!";
    }

    private void inOrder(Node root){
        if(root == null){
            return ;
        }

        inOrder(root.left);
        dictionary.add(root.pair);
        inOrder(root.right);
    }

    @Override
    public List<Pair> sortedKeyValuePair() {
        dictionary.clear();
        inOrder(root);
        return dictionary;
    }  
    
    private void keysBetweenRangeUtil(Node root, int key1, int key2){
        if(root == null){
            return ;
        }

        if(root.pair.key >= key1){
            keysBetweenRangeUtil(root.left, key1, key2);
        }

        if(root.pair.key >= key1 && root.pair.key <= key2){
            dictionary.add(root.pair);
        }

        if(root.pair.key <= key2){
            keysBetweenRangeUtil(root.right,key1,key2);
        }
    }

    @Override
    public List<Pair> keysBetweenRange(int key1, int key2) {
        dictionary.clear();
        keysBetweenRangeUtil(root, key1, key2);
        return dictionary;
    }    
}

public class DictionaryImplement {
    public static void main(String[] args) {
        BST bst = new BST();
        Pair pair1 = new Pair(1, "Apple");
        Pair pair2 = new Pair(2, "Banana");
        Pair pair3 = new Pair(3, "Coconut");
        Pair pair4 = new Pair(4, "Dog");
        Pair pair5 = new Pair(5, "Egg");

        bst.add(pair2);
        bst.add(pair1);
        bst.add(pair5);
        bst.add(pair3);
        bst.add(pair4);

        List<Pair> sortedList = bst.keysBetweenRange(2,4);
        for(Pair pair: sortedList){
            System.out.println(pair.key+" "+pair.val);
        }
        
        Node deleteNode = bst.delete(2);
        if(deleteNode != null){
            System.out.println(deleteNode.pair.key);
        }else{
            System.out.println("Key Not found!");
        }

        List<Pair> sortedList1 = bst.keysBetweenRange(3,5);
        for(Pair pair: sortedList1){
            System.out.println(pair.key+" "+pair.val);
        }

        System.out.println();
        List<Pair> sortedList2 = bst.sortedKeyValuePair();
        for(Pair pair: sortedList2){
            System.out.println(pair.key+" "+pair.val);
        }
    }
}

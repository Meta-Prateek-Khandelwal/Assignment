class Node{
    String name;
    Node left;
    Node right;
    Node(String name){
        this.name = name;
        left = null;
        right = null;
    }
}

class Commands{

    void mkdir(Node Head){
        Node node = new Node("com");
        if(Head.left == null){
            Head.left = node;
        }else{
            Head.right = node;
        }
    }

    void cd(){

    }

    void bk(){

    }

    void ls(){

    }

    void find(){

    }

    void tree(){

    }

    void exit(){

    }
}

public class VCP {
    public static void main(String[] args) {
        Commands commands = new Commands();
        Node Head = new Node("Tree");
        commands.mkdir(Head);
    }
}

import java.util.ArrayList;
import java.util.List;

class Node{
    String name;
    List<Node> children;
    Node parent;

    Node(String name, Node parent){
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
}

class Commands{
    Node parentNode;

    Commands(Node root){
        this.parentNode = root;
    }

    boolean mkdir(String name){
        for(Node child: parentNode.children){
            if(child.name.equals(name)){
                System.out.println("Directory already exists: "+name);
                return false;
            }
        }

        Node node = new Node(name, parentNode);
        parentNode.children.add(node);
        return true;
    }

    Node cd(String name){
        for(Node child: parentNode.children){
            if(child.name.equals(name)){
                parentNode = child;
                return parentNode;
            }
        }

        System.out.println("Directory not found: "+name);
        return null;
    }

    Node bk(){
        if(parentNode != null){
            parentNode = parentNode.parent;
            return parentNode;
        }

        System.out.println("Already at root directory.");
        return null;
    }

    List<String> ls(){
        List<String> dirName = new ArrayList<>();

        for(Node child : parentNode.children){
            dirName.add(child.name);
        }
        return dirName;
    }

    List<Node> find(String name){
        List<Node> directory = new ArrayList<>();
        searchDirectory(parentNode, name, directory);
        return directory;
    }

    private void searchDirectory(Node node, String name, List<Node> directory) {
        if(node.name.equals(name)){
            directory.add(node);
        }

        for(Node child: node.children){
            searchDirectory(child, name, directory);
        }
    }

    void tree(){
        printTree(parentNode, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLast) {
        System.out.println(prefix + (isLast ? "\u2514\u2500 " : "\u251c\u2500 ") + node.name);
        List<Node> childrenList = node.children;
        for(int i = 0; i < childrenList.size(); i++){
            boolean lastChild = (i == childrenList.size() - 1);
            printTree(childrenList.get(i), prefix + (isLast ? " " : "\u2502 "), lastChild);
        }
    }

    void exit(){
        System.out.println("Exiting the virtual command line program...");
        System.exit(0);
    }
}

public class VCP {
    public static void main(String[] args) {
        Node root = new Node("Root", null);
        Commands commands = new Commands(root);
        
        commands.mkdir("com");
        commands.mkdir("bin");
        System.out.println("Directories: "+ commands.ls());

        commands.cd("com");
        commands.mkdir("src");
        commands.cd("main");
        commands.bk();
        commands.tree();
        System.out.println(commands.ls());
    }
}

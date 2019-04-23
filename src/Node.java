import java.util.*;
public class Node {
    int heuristic_value;
    Node parent;
    private List<Node> children = new ArrayList<>();

    public Node(){
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Node(int heuristic_value){
        this.heuristic_value = heuristic_value;
        this.parent = null;
        this.children = new ArrayList<>();
    }
    public Node(int heuristic_value,Node parent,List<Node> children){
        this.heuristic_value = heuristic_value;
        this.parent = parent;
        this.children = children;
    }

    public void addChild(Node child)
    {
        child.setParent(this);
        children.add(child);
    }
    public  void addChildren(ArrayList<Node> new_children){
        new_children.forEach(each -> each.setParent(this));
        this.children.addAll(new_children);

    }
    public List<Node> getChildren(){
        return children;
    }


    public int getValue(){
        return heuristic_value;
    }
    public void setValue(int value){
        this.heuristic_value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent(){
        return this.parent;
    }

    private static void printTree(Node node, String appender) {
        System.out.println(appender + node.getValue());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

    public static int minmax(Node node,int depth,boolean maximingzingPlayer){
        Integer positive_Inf = Integer.MAX_VALUE;
        Integer negative_Inf = Integer.MIN_VALUE;
        if (depth == 0 || node.getChildren().size() == 0){

            return node.getValue();
        }
        if (maximingzingPlayer){
            int value = negative_Inf;
            List<Node> allChildren = node.getChildren();
            for (Node child: allChildren){
                value = Math.max(value,minmax(child,depth-1,false));
            }
            node.setValue(value);
            //System.out.println(node.getValue());
            return value;

        }
        else{
            int value = positive_Inf;
            List<Node> allChildren = node.getChildren();
            for (Node child: allChildren){
                value = Math.min(value,minmax(child,depth-1,true));
            }
            node.setValue(value);
            //System.out.println(node.getValue());
            return value;

        }

    }

    public static int minmax_alphbeta(Node node,int depth,boolean maximingzingPlayer,int alpha,int beta){
        Integer positive_Inf = Integer.MAX_VALUE;
        Integer negative_Inf = Integer.MIN_VALUE;
        if (depth == 0 || node.getChildren().size() == 0){

            return node.getValue();
        }
        if (maximingzingPlayer){
            int value = negative_Inf;
            List<Node> allChildren = node.getChildren();
            for (Node child: allChildren){
                value = Math.max(value,minmax(child,depth-1,false));
                alpha = Math.max(alpha,value);
                if (beta <= alpha){
                    break;
                }
            }
            node.setValue(value);
            //System.out.println(node.getValue());
            return value;

        }
        else{
            int value = positive_Inf;
            List<Node> allChildren = node.getChildren();
            for (Node child: allChildren){
                value = Math.min(value,minmax(child,depth-1,true));
                beta = Math.min(beta,value);
                if (beta <= alpha){
                    break;
                }
            }
            node.setValue(value);
            //System.out.println(node.getValue());
            return value;

        }

    }


    public static void main(String [] args)
    {
        Node root = new Node(-9999);

        Node first_level1 = new Node(9999);
        Node first_level2 = new Node(2);
        Node first_level3 = new Node(-5);
        ArrayList<Node> first_children = new ArrayList<Node>(Arrays.asList(first_level1,first_level2,first_level3) );
        root.addChildren(first_children);

        Node second_level1 = new Node(-9999);
        Node second_level2 = new Node(6);
        Node second_level3 = new Node(3);
        ArrayList<Node> second_children = new ArrayList<Node>(Arrays.asList(second_level1,second_level2,second_level3) );
        first_level1.addChildren(second_children);

        Node third_level1 = new Node(11);
        Node third_level2 = new Node(-7);
        Node third_level3 = new Node(2);
        ArrayList<Node> third_children = new ArrayList<Node>(Arrays.asList(third_level1,third_level2,third_level3) );
        second_level1.addChildren(third_children);

        //printTree(root," ");
        //root.printTree(root);
        System.out.println("min max value " + minmax(root,3,true));
        System.out.println("min max value " + minmax_alphbeta(root,3,true,Integer.MAX_VALUE,Integer.MIN_VALUE));



    }


}

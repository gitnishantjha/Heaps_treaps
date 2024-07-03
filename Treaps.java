import java.util.Random;
import java.util.random.*;

class TreapNode {
    int data;
    int priority;
    TreapNode left, right;

    TreapNode(int data) {
        this.data = data;
        this.priority = new Random().nextInt(100);
        this.left = this.right = null;
    }
}

class Treaps{
    public static TreapNode Rotate_Left(TreapNode root){
        TreapNode R=root.right;
        TreapNode X=root.right.left;

        R.left=root;
        root.right=X;

        return R;
    }
    public static TreapNode Rotate_Right(TreapNode root){
        TreapNode L=root.left;
        TreapNode Y=root.left.right;

        L.right=root;
        root.left=Y;

        return L;
    }


    // Insertion
    public static TreapNode Insert_Node(TreapNode root,int data){
        if(root==null){
            return new TreapNode(data);
        }
        if(data<root.data){
            root.left=Insert_Node(root.left, data);

            if(root.left!=null && root.left.priority>root.priority){
                root=Rotate_Right(root);
            }
        }
        else{
            root.right=Insert_Node(root.right, data);
            if(root.right!=null && root.right.priority>root.priority){
                root=Rotate_Left(root);
            }
        }
        return root;
    }


    // Seaching
    public static boolean searchNode(TreapNode root, int key)
	{	
		if (root == null) {
			return false;
		}
		// if the key is found
		if (root.data == key) {
			return true;
		}
		if (key < root.data) {
			return searchNode(root.left, key);
		}
		return searchNode(root.right, key);
	}


    // Deletion
    public static TreapNode Delete_node(TreapNode root,int key){
        if(root==null){
            return null;
        }
        if(key<root.data){
            root.left=Delete_node(root.left,key);
        }
        else if(key>root.data){
            root.right=Delete_node(root.right, key);
        }
        else{
            if(root.left==null && root.right==null){
                root=null;
            }
            else if(root.left!=null && root.right!=null){
                if(root.left.priority<root.right.priority){
                    root=Rotate_Left(root);
                    root.left=Delete_node(root.left, key); 
                }
                else{
                    root=Rotate_Right(root);
                    root.right=Delete_node(root.right, key); 
                } 
            }
            else{
                TreapNode child=(root.left!=null) ?root.left:root.right;
                root=child;
            }
        }
        return root;
    }



    // Print Treap
    public static void Print_Treap(TreapNode root,int space){
        final int height=10;

        if(root==null){
            return ;
        }
        space+=height;
        Print_Treap(root.right, space);
       System.lineSeparator();

        for(int i= height;i<space;i++){
            System.out.print(' ');
        }
        System.out.println(root.data +"("+root.priority +")");

        System.lineSeparator();
        Print_Treap(root.left, space);

    }
    public static void main(String[] args) {
        int[] keys = { 5, 2, 1, 4, 9, 8, 10 };

        TreapNode root = null;
        for (int key: keys) {
            root = Insert_Node(root, key);
        }
        Print_Treap(root, 0);

        System.out.println("\nDeleting node 1:\n\n");
		root = Delete_node(root, 1);
		Print_Treap(root, 0);

        System.out.println("\nDeleting node 4:\n\n");
		root = Delete_node(root, 4);
		Print_Treap(root, 0);

        System.out.println("\nDeleting node 8:\n\n");
		root = Delete_node(root, 8);
		Print_Treap(root, 0);

    }
}



/*

OUTPUT :=


                    10(43)
          9(71)
                                        8(31)
                              5(35)
                    4(65)
2(74)
          1(63)


Deleting node 1:


                    10(43)
          9(71)
                                        8(31)
                              5(35)
                    4(65)
2(74)


Deleting node 4:


                    10(43)
          9(71)
                              8(31)
                    5(35)
2(74)


Deleting node 8:


                    10(43)
          9(71)
                    5(35)
2(74)
 */
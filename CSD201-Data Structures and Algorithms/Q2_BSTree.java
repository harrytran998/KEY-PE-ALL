import java.util.*;
import java.io.*;

public class Q2_BSTree {
    /**
     * Insert a Person
     */
    public void insert(Person x) {
        Node p = new Node(x);

        if (isEmpty()) {
            root = p;
            return;
        }

        Node f = null;
        Node q = root;
        while (q != null) {
            if (q.info.name.equals(x.name)) {
                System.out.println("Insertion failed, duplicated key");
                return;
            } else if (q.info.name.compareToIgnoreCase(x.name) > 0) {
                f = q;
                q = q.left;
            } else {
                f = q;
                q = q.right;
            }
        }
        if (f.info.name.compareToIgnoreCase(x.name) > 0) {
            f.left = p;
        } else {
            f.right = p;
        }
    }

    /*
    INSERT--- First NAME : B and xAge > 100
     */
    void insert(String xName, int xAge) {
        if (xAge.startsWith("B") || xAge > 100) {
            return;
        }
        Car c = new Car(xName, xAge);
        insert(c);
    }

    /**
     * Breath Change  Node n having age  >= xAge => yAge 
     */
    public void bfsChangeAge(Node p, int n, int xAge, int yAge) {
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            if (q.info.age >= xAge) {
                c++;
                if (c == n) {
                    q.info.age = yAge;
                    break;
                }
            }
        }
    }
    /**
     * Perform pre-order traversal from the root 
     * Having age > 4
     */

    /**
     * Find father
     */
    public Node findFather(int xPrice) {
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == xPrice) break;
            f = p;
            if (xPrice < p.info.price)
                p = p.left;
            else p = p.right;
        }
        return f;
    }

    /**
     * F4
     */
    public void xx(){
        Queue queue = new ArrayDeque();
        //Pair tempP=new Pair(root, null);
        Node save = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            //Pair tt=(Pair) queue.poll();
            Node temp = (Node) queue.poll();
            if (temp.left != null && temp.info.price < 7) {
                save = temp;
                break;
            }
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        int xPrice = save.info.price;
        Node r = rotateRight(save);
        Node q = findFather(xPrice);
        if (q == null) {
            root = r;
        } else {
            if (q.left == save) q.left = r;
            else q.right = r;
        }
    }

    public void deleHaving2Soon_PriceLess() {
        //f3
        Queue queue = new ArrayDeque();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = (Node) queue.poll();
            if (temp.left != null && temp.right != null && temp.info.price < 7) {
                deleteByCopying(temp.info.price);
                break;
            }
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }


    void preOrderf2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.age >= 4) {
            fvisit(p, f);
        }
        preOrderf6(p.left, f);
        preOrderf6(p.right, f);
    }

    void preOrder3(Node p, RandomAccessFile f, int minn, int maxx) throws Exception {
        if (p == null) return;
        if (minn <= p.info.price && p.info.price <= maxx) fvisit(p, f);
        preOrder3(p.left, f, minn, maxx);
        preOrder3(p.right, f, minn, maxx);
    }

    /**
     * In-order traversal from the root 
     * Delete Node Min K
     */
    int count = 0;
    public void indDeleNodeMinK(Node p, int xK) {
        if (p == null) {
            return;
        }
        if (count <= xK) {
            inorder(p.left);
            count++;
            if (count == 2) {
                deleteByCopy(p);
                return;
            }
            inorder(p.right);
        }
    }

    /**
     * Delete Node min K - Inorder
     */
    int c = 0;
    public void inorderDeleteNodeNhoNhatThu2CoKeyNhoHonRoot(Node p, int K) { // Ten ham khac"
        if (p == null) {
            return;
        }
        if (c < K) {
            inorder(p.left);
            String key = root.info.name;
            if (p != root && p.info.name.compareToIgnoreCase(key) < 0) {
                c++;
                if (c == 2) {
                    deleteByCopy(p);
                    return;
                }
            }
            inorder(p.right);
        }
    }

    /**
     * Delete By Copy - Int Price
     */
    void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.price == xPrice) break; //Found key x
            if (xPrice < p.info.price) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
                root = null;
            else {
                if (f.left == p)
                    f.left = null;
                else
                    f.right = null;
            }
            return;
        }

        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
                root = p.left;
            else {
                if (f.left == p) // p is a left child
                    f.left = p.left;
                else
                    f.right = p.left;
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
                root = p.right;
            else {
                if (f.left == p) // p is aleft child
                    f.left = p.right;
                else
                    f.right = p.right;
            }
            return;
        }
    }

    /**
     * Delete By Copy - String Name
     */
    void deleByCopy(String name) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (compareToIgnoreCase(p.info.name, name) == 0) break; //Found key x
            if (compareToIgnoreCase(p.info.name, name) < 0)) {
            f = p;
            p = p.left;
        } else {
            f = p;
            p = p.right;
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
                root = null;
            else {
                if (f.left == p)
                    f.left = null;
                else
                    f.right = null;
            }
            return;
        }

        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
                root = p.left;
            else {
                if (f.left == p) // p is a left child
                    f.left = p.left;
                else
                    f.right = p.left;
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
                root = p.right;
            else {
                if (f.left == p) // p is aleft child
                    f.left = p.right;
                else
                    f.right = p.right;
            }
            return;
        }
    }

    /**
     * Delete By Merge - int X
     */
    public void deleteByMerging(int x) {
        if (isEmpty()) return;
        Node p = getNode(x);
        if (p == null) {
            System.out.println("Key does not exists, deletin failed");
            return;
        }
        //find node f where f is father of p
        Node f = null, q = root;
        while (q != p) {
            if (q.info > x) {
                f = q;
                q = q.left;
            } else {
                f = q;
                q = q.right;
            }
        }
        //1. p is a leaf (no right and left child)
        if (p.left == null && p.right == null) {
            if (f == null) { //a bst has a node only
                root = null;
            } else if (f.left == p) f.left = null;
            else if (f.right == p) f.right = null;
        }
        //2. p has a left child only
        else if (p.left != null && p.right == null) {
            if (f == null) { //remove root
                root = p.left;
            } else if (f.right == p) f.right = p.left;
            else if (f.left == p) f.left = p.left;
        }
        //3. p has a right child only
        else if (p.right != null && p.left == null) {
            if (f == null) { //remove root
                root = p.right;
            } else if (f.right == p) f.right = p.right;
            else if (f.left == p) f.left = p.right;
        }
        //4. p has both left and right child
        else if (p.left != null && p.right != null) {
            //tim node lon nhat ben cay con trai cua p
            q = p.left;
            Node t = null;
            //t la ch cua node lon nhat ben con trai
            while (q.right != null) {
                t = q;
                q = q.right;
            }
            Node r = p.right;
            q.right = r;
            if (f == null) {
                root = p.left;
            } else {
                if (f.left == p) f.left = p.left;
                else f.right = p.left;
            }
        }
    }


    /**
     * Perform breadth-first traversal from the root and delete by copying 
     * the first node having both 2 sons and price < 7
     */
    Node temp = null;
    void breadth2(Node p) {
        if (p == null) return;
        BQueue q = new BQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null && r.right != null && r.info.price < 7) {
                temp = r;
                break;
            };
            if (r.left != null) q.enqueue(r.left);
            if (r.right != null) q.enqueue(r.right);
        }
    }

    /*
    Get Node with Person - String name
     */
    public Node getNode(Node p, Person x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        } else if (p.info.name.compareToIgnoreCase(x.name) > 0) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    /*
    Get Node with Person - int age
     */
    public Node getNode(Node p, Person x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        } else if (p.info.age > x.age) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    /*
    Search Person => return Node
     */
    public Node getNode(Person x) {
        return getNode(root, x);
    }

    /*
     COUNT node in tree
     */
    public int countNodeOfTree() {
        int c = 0;
        Node p = root;
        if (isEmpty()) {
            return c;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            c++;
        }
        return c;
    }

    /*
    Count HEIGHT of tree
     */
    public int heightOfTree(Node p) {
        if (p == null) {
            return 0;
        }
        int l = heightOfTree(p.left) + 1;
        int r = heightOfTree(p.right) + 1;
        return (l > r) ? l : r;
    }

    /*
    Copy all Nodes to tree by inorder traversal
     */
    public void buildArray(ArrayList a, Node p) {
        if (p == null) {
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

    /*
    BALANCE tree
     */
    public void balance(ArrayList a, int first, int last) {
        if (first > last) {
            return;
        }
        int m = (first + last) / 2;
        Person x = ((Node) a.get(m)).info;
        insert(x);
        balance(a, first, m - 1);
        balance(a, m + 1, last);
    }

    /**
     * BALANCE-Simple Array list
     * Goi ham can bang va duyet trong file de in ra
     * balance(root);
     *  breadth(root, f123);
     */

    public void balance(Node p) {
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int first = 0;
        int last = a.size() - 1;
        BSTree b = new BSTree(); // Tao 1 cay moi la cay b
        b.balance(a, first, last);
        root = b.root; // Cho goc tro? ve cay b
    }

    /**
     * ROTATE left
     * Quay NODE trai cua ROOT
     * root.left = rotateLeft(root.left);
     * */
    public Node rotateLeft(Node p) {
        if (p.right == null) {
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    /*
         ROTATE right
         */
    public Node rotateRight(Node p) {
        if (p.left == null) {
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }


    /**
     * Quay bat ki Node nao
     * Con trai cua Root ma khac NULL thi quay phai   
     * if(root.left != null){
     *    root = rotateRight(root);  
     * }
     */
    public void rotateAnyNode() {
        Node p = root;
        if (p ==
            null) {
            return;
        }
        BQueue m = new BQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue(); // Lay no ra
            if (q.left != null) { // Neu van con thi q.left
                m.enqueue(q.left);
            }
            if (q.right != null) { // Neu van con thi q.right
                m.enqueue(q.right);
            }
            if (q.left != null && q.info.price < 7) {
                System.out.println(q.info);
                // 1. case if q is root
                if (root == q) {
                    root = rotateRight(q);
                } else {
                    Node fq = null, focus = root;
                    while (focus != null) {
                        fq = focus;
                        if (focus.info.price > q.info.price) {
                            focus = focus.left;
                            // check if focus is q
                            if (focus.info.price == q.info.price) {
                                fq.left = rotateRight(q);
                                break;
                            }
                        } else if (focus.info.price < q.info.price) {
                            focus = focus.right;
                            // check if focus is q
                            if (focus.info.price == q.info.price) {
                                fq.right = rotateRight(q);
                                break;
                            }
                        }

                    }
                }
                break;
            }
        }
    }

    /*
    GETNODE = xNAME
     */
    public Node getNode(String xName) {
        Node p = root;
        while (p != null) {
            if (p.info.name.compareToIgnoreCase(xName) > 0) {
                p = p.left;
            } else if (p.info.name.compareToIgnoreCase(xName) < 0) {
                p = p.right;
            }
            return p;
        }
        return null;
    }


    /*
    GETNODE = xAge hoặc cái gì đó
     */
    public Node getNode(int x) {
        Node p = root;
        while (p != null) {
            if (p.info > x) {
                p = p.left;
            } else if (p.info < x) {
                p = p.right;
            }
            return p;
        }
        return null;
    }

    /*
    BALANCE-Caculate factor
     */
    public void calBalance(Node p) { // Changed
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            q.bal = heightOfTree(q.right) - heightOfTree(q.left); // bal no cho
            if (isAVL && q.bal < -1 || q.bal > 1) { // De cho  thuc hien nhieu
                isAVL = false;
            }
        }
    }

    boolean isAVL = true; // NOTE
    //    calBalance(root);
    //    breadthBal(root, f123);           // Ham nay cho no, de in ra vi o duoi k in vao file
    //    if(isAVL){
    //        f123.writeBytes("\r\nThe tree is an AVL tree\r\n");
    //    }
    //    else{
    //        f123.writeBytes("\r\nThe tree is not an AVL tree\r\n");
    //    }
    // ---- In ra calLevel cung tuong tu

    /**
     * BALANCE-Caculate level of Node
     */
    public void calLevel(Node p) { // Changed
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        p.level = 1; // ban dau do cao goc = 1
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                q.left.level = q.level + 1;
                m.enqueue(q.left);
            }
            if (q.right != null) {
                q.right.level = q.level + 1;
                m.enqueue(q.right);
            }

        }
    }

    /**
     * COUNT so node chi co 1 con
     */
    int countNodeCo1Con(Node p) {
        int n = 0;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            // If q has only one child, increment c by 1
            if (q.left == null && q.right != null) {
                n++;
            }
            if (q.right == null && q.left != null) {
                n++;
            }
        }
        return n;
    }

    /**
     * COUNT so node co chinh xac 2 con
     */
    int countNodeCo2con(Node p) {
        int n = 0;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            if (q.left != null && q.right != null) {
                n++;
            }
        }
        return n;
    }

    /**
     * Tang tuoi cua Node co AGE max nhat len 100
     */
    public void tangTuoiNodeAgeLonNhatLen100() {
        Node p = root;
        if (root == null) {
            return;
        }
        while (p.right != null) { // p.right != null ( p != null )
            p = p.right;
        }
        p.info.age += 1;
        //
        //        Node p = root;
        //        if (p == null) {
        //            return;
        //        }x
        MyQueue m = new MyQueue();
        m.enqueue(p);
        Node max = root;
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue(); // Lay no ra
            if (q.left != null) { // Neu van con thi q.left
                m.enqueue(q.left);
            }
            if (q.right != null) { // Neu van con thi q.right
                m.enqueue(q.right);
            }
            if (q.info.age > max.info.age) {
                max = q;
            }
        }
        Node p1 = root;
        if (p1 == null) {
            return;
        }
        MyQueue n = new MyQueue();
        m.enqueue(p1);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue(); // Lay no ra
            if (q.left != null) { // Neu van con thi q.left
                m.enqueue(q.left);
            }
            if (q.right != null) { // Neu van con thi q.right
                m.enqueue(q.right);
            }
            if (q.info.age == max.info.age) {
                q.info.age = 100;
                break;
            }
        }
    }

    boolean isAVL = true;
    public void checkAVL() {
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            r.bal = height(r.right) - height(r.left);
            if (r.bal >= 2 || r.bal <= -2) {
                isAVL = false;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }

        breadthBal(root, f123);
        if (!isAVL) {
            f123.writeBytes("\r\nThe tree is not an AVL tree\r\n");
        } else {
            f123.writeBytes("\r\nThe tree is an AVL tree\r\n");
        }
    }

}
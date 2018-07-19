import java.util.*;
import java.io.*;

public class MyList {

    public void addLast(Person c) {
        Node p = new Node(c);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    /*
    add last with xName, xAge
    */
    void addLast(String xName, int xAge) {
        if (xName.startsWith("B") || xAge > 100) {
            return;
        }
        Person c = new Person(xName, xAge);
        addLast(c);
    }


    /*
    add first
    */
    public void addFirst(Person c) {
        Node p = new Node(c);
        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    /*
    add first with xName, xAge
    */
    void addFirst(String xName, int xAge) {
        if (xName.startsWith("B") || xAge > 100) {
            return;
        }
        Person c = new Person(xName, xAge);
        addFirst(c);
    }

    /*
    insert position k
    */
    void addPosition(Person x, int pos) {
        if (isEmpty()) {
            head = tail = new Node(x);
        }
        int count = 1;
        Node p = head;
        while (p != null && count < pos) {
            p = p.next;
            count++;
        }
        Node temp = p.next;
        p.next = new Node(x, temp);
    }

    /*
    Insert position k
    As well as the method above
    */
    public void addAfterPosition(int k, Person c) {
        Node p = new Node(c);
        if (k == -1) {
            addFirst(c);
            return;
        }

        int count = 0;
        Node p1 = head;
        while (p1 != null && count < k) {
            p1 = p1.next;
            count++;
        }

        if (p1.next == null && count == k) { 
            addLast(c);
            return;
        }
        p.next = p1.next;
        p1.next = p;
    }

    /**
     * Add After
     */
    void addAfter(Node q, Person x) {
        if (q == null) {
            return;
        }
        if (q == tail) {
            addLast(x);
            return;
        }
        Node q1 = q.next;
        Node p = new Node(x, q1);
        q.next = p;
    }

    /**
     * Add Before
     */
    void addBefore(Node q, Person x) {
        if (q == null) {
            return;
        }
        if (q == head) {
            addFirst(x);
            return;
        }
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return;
            addAfter(f, x);
        }
    }

    /*
    Traversal a tree
    */
    public void traversal() {
        Node p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
    }

    /**
     * Remove Node
     */
    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        // fine the node before q
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return; // q is not found in the list
        }
        Node q1 = q.next;
        f.next = q1;
        if (f.next == null) {
            tail = f;
        }
    }

    /*
    Remove a Node have position K
    */
    public void removeAtPos(int k) {
        if (isEmpty()) {
            return;
        }
        if (k == 0) {
            Node p = head;
            head = head.next;
            p.next = null;

        } else {
            Node p = getNode(k);
            if (p == null) {
                return;
            }
            Node q = getNode(k - 1);
            // Remove p
            q.next = p.next;
            p.next = null;
            if (p == tail) {
                tail = q;
            }
        }
    }

    /**
     * Remove Node - String Name 
     */
    void removeWithName(String xName) {
        Node q = getNodeByName(xName);
        remove(q);
    }

    /**
     * Remove Node - int Age
     */
    void removeWithAge(int xAge) {
        Node q = getNodeByAge(xAge);
        remove(q);
    }

    /**
     * Remove all - int Age
     */
    void removeAll(int xAge) {
        Node q;
        while (true) {
            q = getNodeByAge(xAge);
            if (q == null) {
                break;
            }
            remove(q);
        }
    }

    /**
     * Remove all - String name
     */
    void removeAll(String name) {
        Node q;
        while (true) {
            q = getNodeByName(name);
            if (q == null) {
                break;
            }
            remove(q);
        }
    }

    /**
     * Remove after position K
     */
    public void removeAfterPos(int k) {
        if (isEmpty()) {
            return;
        }
        //remove head
        if (k == -1) {
            Node p = head;
            head = head.next;
            p.next = null;
        } else {
            Node p = getNode(k + 1);
            if (p == null) {
                return;
            }
            Node q = getNode(k);
            //remove p
            q.next = p.next;
            p.next = null;
            if (p == tail) {
                tail = q;
            }
        }
    }

    /**
     * Remove second Node after Node having age < K
     */
    public void removeSecondNode(int age) {
        Node p = head;
        while (p != null && p.info.age != age) {
            p = p.next;
        }
        if (p == null && p.next == null) {
            return;
        }
        remove(p.next.next);
    }

    /**
     * Remove Node after Node have age = first K
     */
    public void removeN_AftN_AgeK(int age) {
        Node p = head;
        while (p != null && p.info.age != age) {
            p = p.next;
        }
        if (p == null && p.next == null) {
            return;
        }
        remove(p.next.next);
    }

    /**
     * Remove node k of age less than X
     */
    public void removeNodeKAgeLessX(int k, int age) {
        Node p = head;
        int c = 0;
        while (p != null) {
            if (p.info.age < age) {
                c++;
            }
            if (c >= k) {
                break;
            }
            p = p.next;
        }
        remove(p);
    }

    /**
     * Remove first Node after node having position K
     */
    public void remove_fNodeAfterPos(int k) {
        Node p = head;
        int c = 0;
        while (p != null) {
            if (c >= k) {
                break;
            }
            c++;
            p = p.next;
        }
        if (p == null || p.next == null) { // Neu NODE k la NODE cuoi thi khong lam gi
            return;
        }
        Node q = p.next; // Node k la NODE gan cuoi va xoa NODE cuoi
        if (q.next == null) {
            tail = p;
            p.next = null;
        } else {
            p.next = q.next;
            q.next = null;
        }
    }

    /**
     * Remove Node having p.info.xPrice > xPrice
     */
    public void removeNodeValueMoreXPrice(double xPrice) {
        Node p = head;
        while (p != null && p.info.price < xPrice) {
            p = p.next;
        }
        if (p == null || p.next == null) {
            return;
        }
        Node q = p.next;
        if (q == tail) {
            tail = p;
        }
        p.next = q.next;
        q.next = null;
    }

    /**
     * Remove node xK after Node xN having age < xAge
     */
    public void removeNodeThuKSauNodeThu2AgeNhoHon6(int xK, int xN, int xAge) {
        Node p = head;
        int c = 0;
        while (p != null) {
            if (p.info.age < xAge) {
                break;
            }
            p = p.next;
        }
        p = p.next;
        while (p != null) {
            if (p.info.age < xAge) {
                c++;
            }
            if (c >= xK) {
                break;
            }
            p = p.next;
        }
        remove(p);
    }

    /**
     * Remove node xK after node Max 
     */
    public void removeNodeKAfterNodeMax(int k) {
        Person m = getMaxPerson();
        Node p = head;
        int c = 0;
        while (p != null) {
            if (p.info.age == m.age) {
                break;
            }
            p = p.next;
        }
        while (p != null) {

            if (c >= k) {
                break;
            }
            c++;
            p = p.next;
        }
        remove(p);
    }

    /**
     * Remove xK biggest Person
     */
    public void removeSecond() {
        Person firstMax = getMaxPerson();
        if (firstMax == null) {
            return;
        }
        int n = size();
        if (n <= 1) {
            return;
        }
        int imax = 0;
        Node p = head;
        while (p != null && p.info.age == firstMax.age) {
            imax++;
            p = p.next;
        }
        // Find second max starting from imax
        Person secondMax = (p != null) ? p.info : null;
        for (int i = imax + 1; i < n; i++) {
            Node pi = getNode(i);
            if (pi.info.age > secondMax.age && pi.info.age != firstMax.age) {
                imax = i;
                secondMax = pi.info;
            }
        }
        if (imax < n) {
            remove(imax);
        }
    }

    /**
     * Remove last Node K having Age > xAge
     */
    public void removeLastNodeKHavingAgeMore(int k, int xAge) {
        int c = 0;
        int sz = size();
        for (int i = sz - 1; i >= 0; i--) {
            Node p = getNode(i);
            if (p.info.age > xAge) {
                c++;
                remove(p);
                if (c >= k) {
                    break;
                }
            }
        }
    }

    /**
     * Get Node Max
     */
    public Node getMax() {
        if (isEmpty()) return null;
        Node max = head;
        Node p = head;
        while (p != null) {
            if (p.info.age > max.info.age) max = p;
            p = p.next;
        }
        return max;
    }
    /**
     * Get Node Min  
     */
    public Node getMin() {
        if (isEmpty()) return null;
        Node max = head;
        Node p = head;
        while (p != null) {
            if (p.info.age < max.info.age) max = p;
            p = p.next;
        }
        return max;
    }

    /**
     * Get Max Nod Person
     */
    public Person getMaxPerson() {
        if (isEmpty()) {
            return null;
        }
        Person max = head.info;
        Node p = head;
        while (p != null) {
            if (p.info.age > max.age) {
                max = p.info;
            }
            p = p.next;
        }
        return max;
    }


    /**
     * Get a Node at position K
     */
    public Node getNode(int k) {
        /
        int c = 0;
        Node p = head;
        while (p != null && c < k) {
            p = p.next;
            c++;
        }
        return p;
    }

    /**
     * Get Node by Name
     */
    public Node getNodeByName(String name) {
        Node p = head;
        if (p == null) {
            return null;
        }
        while (p != null) {
            if (p.info.name.equalsIgnoreCase(xName)) {
                break;
            }
            p = p.next;
        }
        return p;
    }
    /**
     * Get Node by xAge
     */
    public Node getNodeByAge(int xAge) {
        Node p = head;
        if (p == null) {
            return null;
        }
        while (p != null) {
            if (p.info.age == xAge) {
                break;
            }
            p = p.next;
        }
        return p;
    }


    /**
     * Swap Max Min Node
     */
    public void swapMaxMin() {
        Node min = getMin();
        Node max = getMax();
        Person t = min.info;
        min.info = max.info;
        max.info = t;
    }

    /**
     * Find Node Max N => Swap node Min
     */
    public void swapMax2Min1(int n) {
        Node max = getMax();
        Node min = getMin();
        Node p = head;
        int count = 0;
        while (p != null) {
            if (p.info.age == max.info.age) {
                count++;
            }
            if (count == n) {
                break;
            }
            p = p.next;
        }
        Person temp;
        temp = p.info;
        p.info = min.info;
        min.info = temp;
    }

    /**
     * Replace a Node with String xName
     */
    public void replace(String xName) {
        Node p = head;
        while (p != null) {
            if (p.info.name.equals(xName)) {
                break;
            }
            p = p.next;
        }
        if (p != null) {
            p.info.name = xName;
        }
    }

    /**
     * Count number of Node in the tree
     */
    public int size() {
        int c = 0;
        Node p = head;
        while (p != null) {
            p = p.next;
            c++;
        }
        return c;
    }

    /**
     * Reverse the TREE
     */
    public void reverse() {
        int n = size(), i, j;
        for (i = 0, j = n - 1; i < j; i++, j--) {
            Node pi = getNode(i);
            Node pj = getNode(j);
            Person temp = pi.info;
            pi.info = pj.info;
            pj.info = temp;
        }
    }

    /**
     * Sort Tree
     */
    public void sortASC() {
        Node pi;
        Node pj;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.info.age > pj.info.age) {
                    Person temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    /**
     * Sort first K elements with NAME 
     * Using while Loop
     */
    public void sortFirstK(int k) {
        Node pi, pj;
        pi = head;
        int count = 0;
        while (pi != null) {
            count++;
            pj = pi.next;
            int count1 = 0;
            while (pj != null) {
                count1++;
                if (pi.info.name.compareToIgnoreCase(pj.info.name) < 0) {
                    Person t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
                pj = pj.next;
                if (count1 == k - count) {
                    break;
                }
            }
            pi = pi.next;
            if (count == k - 1) {
                break;
            }
        }
    }

    /**
     * Sort 4 last Node
     */
    public void sort4Last() {
        int n = size();
        for (int i = n - 4; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node pi = getNode(i), pj = getNode(j);
                if (pi.info.name.compareToIgnoreCase(pj.info.name) > 0) {
                    Person t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
            }
        }
    }

    /**
     * Sort 3 first Node
     */
    public sort3First() {
        int n = size();
        //for(int i = n - 3; i < n; i++) {//3 last nodes
        for (int i = 0; i < 3 - 1; i++) { //3 first nodes
            for (int j = i + 1; j < 3; j++) { //3 first nodes
                //for(int j = i + 1; j < n; j++)//3 last nodes
                Node pi = getNode(i), pj = getNode(j);
                if (pi.info.name.compareToIgnoreCase(pj.info.name) > 0) {
                    Person t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
            }
        }
    }


}
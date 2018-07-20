/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) //do not edit this function
    {
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("graph.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i]);
    }

    void fvisitDeg(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i] + "(" + deg[i] + ")");
    }

    void fdispAdj(RandomAccessFile f) throws Exception {
        int i, j;
        f.writeBytes("n = " + n + "\r\n");
        for (i = 0; i < n; i++) {
            f.writeBytes("\r\n");
            for (j = 0; j < n; j++) {
                f.writeBytes("  " + a[i][j]);
            }
        }
        f.writeBytes("\r\n");
    }

    void fbreadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        MyQueue q = new MyQueue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void fbreadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        fbreadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                fbreadth(en, i, f);
            }
        }
    }

    void fdepth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                fdepth(visited, i, f);
            }
        }
    }

    void fdepth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        fdepth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                fdepth(visited, i, f);
            }
        }

    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//=================================================================
    void calDeg() {
        for (int i = 0; i < n; i++) {
            deg[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += a[i][j];
            }
        }
    }

    void fbreadthDeg(boolean[] en, int i, RandomAccessFile f) throws Exception {
        MyQueue q = new MyQueue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisitDeg(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void fbreadthDeg(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        fbreadthDeg(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                fbreadthDeg(en, i, f);
            }
        }
    }

    void f1() throws Exception {
        loadData(10);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        fbreadth(0, f123);// fbreadth first traverse from the vertex 0 (A)
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        calDeg();
        fbreadthDeg(0, f123);

        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//=================================================================
    void fdepthDeg(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisitDeg(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                fdepthDeg(visited, i, f);
            }
        }
    }

    void fdepthDeg(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        fdepthDeg(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                fdepthDeg(visited, i, f);
            }
        }

    }

    void f2() throws Exception {
        loadData(10);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        fdepth(0, f123);// fdepth first traverse from the vertex 0 (A)
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        calDeg();
        fdepthDeg(0, f123);

        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//=================================================================
    boolean free[];

    void dfs(int u) {
        free[u] = false;
        for (int i = 0; i < n; i++) {
            if (a[u][i] != 0 && free[i] == true) {
                dfs(i);
            }
        }
    }

    void f3() throws Exception {
        loadData(21);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        fdispAdj(f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        free = new boolean[n];
        for (int i = 0; i < n; i++) {
            free[i] = true;
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (free[i] == true) {
                k++;
                dfs(i);
            }
        }
        f123.writeBytes(" k = " + k + "\r\n");
        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//=================================================================
    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int i = 0; i < n; i++) {
            if (sptSet[i] == false && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    void dijkstra(int src, int to, RandomAccessFile f123) throws IOException {
        int dist[] = new int[n];
        Boolean sptSet[] = new Boolean[n];
        int pre[] = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            pre[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        pre[src] = -1;
        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && a[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + a[u][v] < dist[v]) {
                    dist[v] = dist[u] + a[u][v];
                    pre[v] = u;
                }
            }
        }
        System.out.println("Shortest path from " + src + ":");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + dist[i]);
        }
        f123.writeBytes("The length of shortest path from " + v[src] + " to " + v[to] + " is " + dist[to]);
        Stack<Integer> st = new Stack();
        f123.writeBytes("\r\nPath:\r\n");
        while (to != -1) {
            st.push(to);
            to = pre[to];
        }
        st.pop();
        String path = "A";
        while (!st.isEmpty()) {
            path += " -> " + v[st.pop()];
        }
        f123.writeBytes(path);

    }

    void f4() throws Exception {
        loadData(33);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        dijkstra(0, 5, f123);

        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//=================================================================
    Boolean isUndirect() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    void checkEulerCycle(RandomAccessFile f123) throws IOException {
        Boolean checkUndirect = isUndirect();
        int k = 0;
        free = new boolean[n];
        for (int i = 0; i < n; i++) {
            free[i] = true;
        }
        for (int i = 0; i < n; i++) {
            if (free[i] == true) {
                dfs(i);
                k++;
            }
        }
        Boolean isConnected, hasAllEvenEdge;
        if (k > 1) {
            isConnected = false;
        } else {
            isConnected = true;
        }
        calDeg();
        hasAllEvenEdge = true;
        for (int i = 0; i < n; i++) {
            if (deg[i] % 2 == 1) {
                hasAllEvenEdge = false;
            }
        }

        if (checkUndirect) {
            f123.writeBytes("The graph is undirected.\n");
        } else {
            f123.writeBytes("The graph is directed.\n");
        }
        if (isConnected) {
            f123.writeBytes("The graph is connected.\n");
        } else {
            f123.writeBytes("The graph is not connected\n");
        }
        if (hasAllEvenEdge) {
            f123.writeBytes("All vertices have even degree.\n");
        } else {
            f123.writeBytes("The graph has a vertex with odd degree.\n");
        }
        if (isConnected && checkUndirect && hasAllEvenEdge) {
            f123.writeBytes("Conditions for Euler's cycle are satisfied.\n");
        } else {
            f123.writeBytes("Conditions for Euler's cycle are not satisfied.\n");
        }
    }

    void f5() throws Exception {//You do not need to edit this file
        //Your task is to complete the checkEulerCycle(...) function above only
        loadData(42);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");

        checkEulerCycle(f123);
        loadData(49);
        f123.writeBytes("\r\n");
        checkEulerCycle(f123);

        f123.writeBytes("\r\n");
        f123.close();
    }

//=================================================================
    void f6() throws Exception {
        loadData(42);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        int eu[] = new int[100];
        int temp[][] = a;
        int m = 0;
        int i, r;
        Stack<Integer> st = new Stack<>();
        st.push(0);
        while (!st.isEmpty()) {
            r = st.peek();
            for (i = 0; i < n; i++) {
                if (a[r][i] > 0) {
                    break;
                }
            }
            if (i == n) {
                eu[m++] = r;
                st.pop();
            } else {
                st.push(i);
                a[r][i]--;
                a[i][r]--;
            }
        }
        a = temp;
        String ans = "";
        ans += v[eu[0]];
        for (int j = 1; j < m; j++) {
            ans += " -> " + v[eu[j]];
        }
        f123.writeBytes(ans);

        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

}
//=================================================================

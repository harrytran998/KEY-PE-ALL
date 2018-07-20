
public class Q2_Graph{
    int minDistance(int dist[], Boolean sptSet[]){
        int min=Integer.MAX_VALUE, min_index=-1;
        for (int i=0;i<n;i++){
            if (sptSet[i]==false && dist[i]<=min){
                min=dist[i];
                min_index=i;
            }
        }
        return min_index;
    }
  void dijkstra(int src, int to, RandomAccessFile f) throws IOException{
        int dist[]=new int[n];
        Boolean sptSet[]=new Boolean[n];
        int pre[]=new int[n];
        for (int i=0;i<n;i++){
            dist[i]=Integer.MAX_VALUE;
            sptSet[i]=false;
            pre[i]=Integer.MAX_VALUE;
        }
        dist[src]=0;
        pre[src]=-1;
        for (int count=0;count<n-1;count++){
            int u=minDistance(dist, sptSet);
            sptSet[u]=true;
            for (int v=0;v<n;v++){
                if (!sptSet[v] && a[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + a[u][v]< dist[v]){
                    dist[v]=dist[u]+a[u][v];
                    pre[v]=u;
                }
            }
        }
        
        //System.out.println("Shortest path from "+src+":");
        //for (int i=0;i<n;i++) System.out.println(i+": "+dist[i]);
        Stack<Integer> st=new Stack();
        //System.out.println("Shortest path from "+src+" to "+to+":");
        while (to!=-1){
            st.push(to);
            to=pre[to];
        }
        ArrayList<Integer> kk=new ArrayList();
        while (!st.isEmpty()){
            int top=st.pop();
            f.writeBytes(v[top]+" ");
            kk.add(dist[top]);
            //System.out.print(st.pop()+" ");
        }
        f.writeBytes("\r\n");
        for (int temp: kk){
            f.writeBytes(temp+" ");
        }
        //System.out.println("");
    }

    void dijkstra(int fro, int to, RandomAccessFile f) throws IOException {
        boolean[] S = new boolean[n];
        int[] d = new int[n];
        int[] p = new int[n];
        int INF = 99;
        int i, j, k, t;
        for (i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i];
            p[i] = fro;
        }
        S[fro] = true;
        while (true) {
            // find d[k] = min {d[i}}
            t = INF;
            k = -1;
            for (i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] < t) {
                    t = d[i];
                    k = i;
                }
            }
            if (k == -1) {
                System.out.println("No solution");
                return;
            }
            // add k to S
            S[k] = true;
            if (k == to) {
                break;
            }
            // update d[i] & p[i]
            for (i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }
        System.out.println("The shortest distance is " + d[to]);
        i = to;
        
        GStack s = new GStack();
        while (true) {
            s.push(i);
            if (i == fro) {
                break;
            }
            i = p[i];
        }
        
        System.out.print(i);
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        while (!s.isEmpty()) {
            i = s.pop();
            a.add(d[i]);
            b.add(i);
        }
    }
    /*
    for (int l = 0; l < b.size(); l++) {
            f.writeBytes(b.get(l) + " ");
        }
        f.writeBytes("\n");
        for (int l = 0; l < b.size(); l++) {
            f.writeBytes(v[l] + " ");
        }
        f.writeBytes("\n");
        for (int l = 0; l < a.size(); l++) {
            f.writeBytes(a.get(l) + " ");
        }
    */

    private void preEuler(RandomAccessFile f) throws IOException {
        int eu[]=new int[100];
        int temp[][]=a;
        int m=0;
        int i,r;
        Stack<Integer> st=new Stack<>();
        st.push(1);
        while (!st.isEmpty()){
            r=st.peek();
            for (i=0;i<n;i++){
                if (a[r][i]>0) break;
            }
            if (i==n){
                eu[m++]=r;
                st.pop();
            }else{
                st.push(i);
                a[r][i]--;
                a[i][r]--;
            }
        }
        a=temp;
        //System.out.print("The Euler's cycle is ");
        //System.out.print(eu[0]);
        f.writeBytes(v[eu[0]]+" ");
        for(i=1;i<m;i++){
            f.writeBytes(v[eu[i]]+" ");
            //System.out.print(" -> " + eu[i]);
        }
    }
    /**
     * F6
     */
    int deg(int i) {
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return (s);
    }

    boolean hasIsolated() {
        for (int i = 0; i < n; i++) {
            if (deg(i) == 0) {
                return (true);
            }
        }
        return (false);
    }

    boolean isConnected() {
        boolean[] p = new boolean[n];
        int i, j, r;
        for (i = 0; i < n; i++) {
            p[i] = false;
        }
        MyStack s = new MyStack();
        s.push(0);
        p[0] = true;
        while (!s.isEmpty()) {
            r = s.pop();
            for (i = 0; i < n; i++) {
                if (!p[i] && a[r][i] > 0) {
                    s.push(i);
                    p[i] = true;
                }
            }
        }
        for (i = 0; i < n; i++) {
            if (!p[i]) {
                return (false);
            }
        }
        return (true);
    }

    boolean isUnDirected() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    return (false);
                }
            }
        }
        return (true);
    }

    boolean allDegEven() {
        for (int i = 0; i < n; i++) {
            if (deg(i) % 2 == 1) {
                return (false);
            }
        }
        return (true);
    }

    boolean hasEulerCycle() {
        if (!hasIsolated() && isUnDirected() && isConnected() && allDegEven()) {
            return (true);
        } else {
            return (false);
        }
    }

    void eulerCycle(int fro) {
        if (!hasEulerCycle()) {
            System.out.println("The conditions for Euler's cycle are not satisfied!");
            return;
        }
        int[] eu = new int[100];
        int m, i, j, r;
        MyStack s = new MyStack();
        s.push(fro);
        j = 0;
        while (!s.isEmpty()) {
            r = s.top();
            for (i = 0; i < n; i++) {
                if (a[r][i] > 0) {
                    break;
                }
            }
            if (i == n) { //r is isolated
                s.pop();
                eu[j++] = r;

            } else {
                s.push(i);
                a[r][i]--;
                a[i][r]--;
            }
        }
        m = j;
        System.out.println("The Euler's cycle is: ");
        System.out.print(v[eu[0]]);
        for (i = 1; i < m; i++) {
            System.out.print(" -> " + v[eu[i]]);
        }
    }
}
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Mohamed Hashem
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        int all;
        int[] parent;
        int[] rnk;

        int findparent(int x) {
            if (parent[x] == x) return x;
            return parent[x] = findparent(parent[x]);
        }

        boolean IsConnected(int a, int b) {
            if (findparent(a) == findparent(b)) return true;
            return false;
        }

        void connect(int x, int y) {
            int tx = findparent(x);
            int ty = findparent(y);
            if (rnk[tx] > rnk[ty]) parent[ty] = tx;
            else if (rnk[tx] < rnk[ty]) parent[tx] = ty;
            else {
                parent[tx] = ty;
                ++rnk[ty];
            }
            --all;
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n, m;
            int c = 1;
            while (true) {
                n = in.nextInt();
                m = in.nextInt();
                if (n == 0 && m == 0) break;
                else {
                    all = n;
                    parent = new int[(int) 1e5 + 6];
                    rnk = new int[(int) 1e5 + 6];
                    for (int i = 0; i < (int) 1e5 + 6; ++i)
                        parent[i] = i;
                    for (int i = 0; i < (int) 1e5 + 6; ++i)
                        rnk[i] = 0;
                    for (int i = 0; i < m; ++i) {
                        int a, b;
                        a = in.nextInt();
                        b = in.nextInt();
                        if (!IsConnected(a, b)) {
                            connect(a, b);
                        }
                    }
                    out.printf("Case %d: %d\n", c, all);
                    ++c;
                }
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}


package cnt;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;


/*
 * 
 * Does the solution work for larger graphs?
 *		I believe so. I think the test data is decently large.
 * Can you think of any optimizations?
 *		For larger datasets, it could make sense to have a set number of threads
 *		exploring simultaneously, as long as the lookup table is threadsafe
 * What’s the computational complexity of your solution?
 * 		O(n^2), I believe. The closer to a Tree the DAG is, the less helpful caching is.
 * Are there any unusual cases that aren't handled?
 * 		None that I can think of
 *  
 */
public class Main {

	private static Map<Long, Integer> longestPathLookupTable;
	private static int opCount;	
	private static Vertex z = new Vertex(26);
	private static Vertex y = new Vertex(25);
	private static Vertex x = new Vertex(24);
	private static Vertex w = new Vertex(23);
	private static Vertex v = new Vertex(22, z);
	private static Vertex u = new Vertex(21, y, z);
	private static Vertex t = new Vertex(20, w, x);
	private static Vertex s = new Vertex(19, u, w, z);
	private static Vertex r = new Vertex(18, s);
	private static Vertex q = new Vertex(17, v, z);
	private static Vertex p = new Vertex(16, s, u, v, z);
	private static Vertex o = new Vertex(15, r, x, y);
	private static Vertex n = new Vertex(14, p, r, s, v, w, z);
	private static Vertex m = new Vertex(13, n,o,p,q,r,s,t,u,v,w,x,y,z);
	private static Vertex l = new Vertex(12, o, p, t, u, y);
	private static Vertex k = new Vertex(11, x, m, t, u, w);
	private static Vertex j = new Vertex(10, n, o, q);
	private static Vertex i = new Vertex(9, j, p, t, x);
	private static Vertex h = new Vertex(8, m, n, y, z);
	private static Vertex g = new Vertex(7, m, l, r, q, v, w, x, y);
	private static Vertex f = new Vertex(6, g, k, s, u, v);
	private static Vertex e = new Vertex(5, n, p, q, s, z);
	private static Vertex d = new Vertex(4, f, h, k, r, u);
	private static Vertex c = new Vertex(3, e, n, l, r, s, t);
	private static Vertex b = new Vertex(2, d, i, k, t);
	private static Vertex a = new Vertex(1, b, c, y, z);

	public static void main(String args[]) {
		solve(a);
	}
	
	private static void solve(Vertex origin) {
		longestPathLookupTable = new HashMap<>();
		opCount = 0;
		System.out.println("Longest possible path: " + (travel(origin)-1));
		System.out.println("Number of calls to the recursive graph traveling function: " + opCount);
	}
	
	private static int travel(Vertex origin) {
		opCount++;
		if (origin.children.size() == 0) { return 1; }
		Integer cachedPathLength = longestPathLookupTable.get(origin.id);
		if (cachedPathLength != null) { return cachedPathLength; }
		int descendantPath = origin.children.stream()
				.mapToInt(vertex -> travel(vertex))
				.max().orElseThrow(NoSuchElementException::new);
		longestPathLookupTable.put(origin.id, descendantPath+1);
		return descendantPath+1;
	}
}

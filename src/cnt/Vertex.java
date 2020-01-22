package cnt;

import java.util.Arrays;
import java.util.List;

public class Vertex {

	long id;
	List<Vertex> children;
	
	public Vertex(long id, Vertex...children) {
		this.id = id;
		this.children = Arrays.asList(children);
	}
}

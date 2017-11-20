import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

@RunWith(Parameterized.class)
public class GraphTest {

//   @Rule
//   public Timeout globalTimeout = Timeout.seconds(30);

    // Graph aGraph = new AdjacencyMatrixGraph();
    Graph aGraph;

    public GraphTest(Graph anInterface) {
        this.aGraph = anInterface;
    }

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        this.aGraph = aGraph.getClass().newInstance();
    }

    @Test(timeout=3000)
    public void addVertexAndGetVerticesTest() {
        Vertex a = new Vertex("a");
        aGraph.addVertex(a);
        assertEquals(a, aGraph.getVertices().get(0));

        Vertex b = new Vertex("b");
        aGraph.addVertex(b);
        assertEquals(2, aGraph.getVertices().size());
    }

    @Test(timeout=3000)
    public void edgeTest1() {
        // Graph empty at initialization
        assertEquals(0, aGraph.getVertices().size());
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");

        // Test basic edgeExists()
        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);
        assertEquals(true, aGraph.edgeExists(a, b));
        assertEquals(false, aGraph.edgeExists(b, a));
    }


    @Test(timeout=3000)
    public void edgeTest2() {
        // Graph empty at initialization
        assertEquals(0, aGraph.getVertices().size());
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");

        // Test basic edgeExists()
        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addVertex(c);
        aGraph.addEdge(a, b);

        assertEquals(false, aGraph.edgeExists(a, c)); //Should not throw an exception
    }


    @Test(timeout=3000)
    public void getUpstreamNeighborsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);

        // 0 upstream
        assertEquals(0, aGraph.getUpstreamNeighbors(a).size());

        // 1 >= upsteams
        assertEquals(1, aGraph.getUpstreamNeighbors(b).size());

        // 1 > = upstreams with loops
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        aGraph.addVertex(c);
        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addEdge(a, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(b, e);
        aGraph.addEdge(e, a);
        aGraph.addEdge(c, d);
        assertEquals(2, aGraph.getUpstreamNeighbors(d).size());
        assertEquals(1, aGraph.getUpstreamNeighbors(c).size());

        // Test the traversal(s)
        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
                add(c);
            }
        }, new HashSet<Vertex>(aGraph.getUpstreamNeighbors(d)));
    }

    @Test(timeout=3000)
    public void getDownstreamNeighborsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);

        // 0 downstream
        assertEquals(0, aGraph.getDownstreamNeighbors(b).size());

        // 1 >= downstreams
        assertEquals(1, aGraph.getDownstreamNeighbors(a).size());

        // 1 > = downstreams with loops
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        aGraph.addVertex(c);
        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addEdge(a, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(b, e);
        aGraph.addEdge(e, a);
        assertEquals(1, aGraph.getDownstreamNeighbors(e).size());
        assertEquals(2, aGraph.getDownstreamNeighbors(b).size());

        // Test the traversal(s)
        assertEquals(new HashSet<Vertex>() {
            {
                add(d);
                add(e);
            }
        }, new HashSet<Vertex>(aGraph.getDownstreamNeighbors(b)));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new AdjacencyListGraph() }, new Object[] { new AdjacencyMatrixGraph() });
    }

}

package aoc22.utils


// FROM: https://www.programiz.com/dsa/graph-bfs


/*
import java.util.*

class Graph internal constructor(private val V: Int) {
    private val adj: Array<LinkedList<Int>>

    // Create a graph
    init {
        adj = arrayOfNulls<LinkedList<*>>(V)
        for (i in 0 until V) adj[i] = LinkedList<Any?>()
    }

    // Add edges to the graph
    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
    }

    // BFS algorithm
    fun BFS(s: Int) {
        var s = s
        val visited = BooleanArray(V)
        val queue: LinkedList<Int?> = LinkedList<Any?>()
        visited[s] = true
        queue.add(s)
        while (queue.size != 0) {
            s = queue.poll()!!
            print("$s ")
            val i: Iterator<Int> = adj[s].listIterator()
            while (i.hasNext()) {
                val n = i.next()
                if (!visited[n]) {
                    visited[n] = true
                    queue.add(n)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val g = Graph(4)
            g.addEdge(0, 1)
            g.addEdge(0, 2)
            g.addEdge(1, 2)
            g.addEdge(2, 0)
            g.addEdge(2, 3)
            g.addEdge(3, 3)
            println("Following is Breadth First Traversal " + "(starting from vertex 2)")
            g.BFS(2)
        }
    }
}
 */

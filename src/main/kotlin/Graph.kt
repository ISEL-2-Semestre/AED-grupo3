interface Graph<I> : Iterable<Graph.Vertex<I>> {
    interface Vertex<I> {
        val id: I
        fun getAdjacencies(): MutableList<I>
    }

    val size: Int
    fun addVertex(id: I): Boolean
    fun addEdge(id1: I, idAdj: I): I?
    fun getVertex(id: I): Vertex<I>?
}

class SimpleGraph<I> : Graph<I> {
    private val vertices = mutableMapOf<I, Graph.Vertex<I>>()

    override val size: Int
        get() = vertices.size

    override fun addVertex(id: I): Boolean {
        if (vertices.containsKey(id)) {
            return false
        }
        vertices[id] = SimpleVertex(id)
        return true
    }

    override fun addEdge(id1: I, idAdj: I): I? {
        val vertex1 = vertices[id1] as? SimpleVertex ?: return null
        val vertex2 = vertices[idAdj] as? SimpleVertex ?: return null
        vertex1.addAdjacency(vertex2.id)
        vertex2.addAdjacency(vertex1.id)
        return vertex2.id
    }

    override fun getVertex(id: I): Graph.Vertex<I>? {
        return vertices[id]
    }

    override fun iterator(): Iterator<Graph.Vertex<I>> {
        return vertices.values.iterator()
    }

    private inner class SimpleVertex(override val id: I) : Graph.Vertex<I> {
        private val adjacencies = mutableListOf<I>()

        override fun getAdjacencies(): MutableList<I> {
            return adjacencies
        }

        fun addAdjacency(idAdj: I) {
            adjacencies.add(idAdj)
        }
    }
}

fun main() {
    // Criando um grafo simples
    val graph = SimpleGraph<Int>()

    // Adicionando vértices ao grafo
    graph.addVertex(1)
    graph.addVertex(2)
    graph.addVertex(3)
    graph.addVertex(4)
    graph.addVertex(5)
    graph.addVertex(6)

    // Adicionando arestas ao grafo
    graph.addEdge(1, 3)
    graph.addEdge(1, 2)
    graph.addEdge(2, 3)
    graph.addEdge(1, 5)
    graph.addEdge(5, 6)
    graph.addEdge(6, 4)

    // Imprimindo os vértices e suas adjacências
    for (vertex in graph) {
        println("Vértice ${vertex.id} tem adjacências: ${vertex.getAdjacencies()}")
    }

    // Verificando a existência de uma aresta
    val hasEdge = graph.addEdge(3, 1) != null
    println("A aresta entre 3 e 1 foi adicionada? $hasEdge")

    // Obtendo um vértice específico e imprimindo suas adjacências
    val vertex = graph.getVertex(2)
    println("Adjacências do vértice 2: ${vertex?.getAdjacencies()}")
}

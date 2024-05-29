import java.io.File

// Classe para representar o grafo de De Bruijn
class DeBruijnGraph(val k: Int) {
    val edges = mutableMapOf<String, MutableList<String>>()

    fun addKmer(kmer: String) {
        val prefix = kmer.dropLast(1)
        val suffix = kmer.drop(1)
        edges.getOrPut(prefix) { mutableListOf() }.add(suffix)
    }

    fun findEulerianPath(): List<String> {
        val path = mutableListOf<String>()
        val stack = mutableListOf<String>()
        val startVertex = edges.keys.first() // Assumindo que sempre haverá pelo menos um vértice para começar
        stack.add(startVertex)

        while (stack.isNotEmpty()) {
            val vertex = stack.last()
            if (edges[vertex]?.isNotEmpty() == true) {
                val nextVertex = edges[vertex]!!.removeAt(0)
                stack.add(nextVertex)
            } else {
                path.add(stack.removeAt(stack.size - 1))
            }
        }

        return path.reversed()
    }
}

fun main(args: Array<String>) {
    val k = args[0].toInt()
    val inputFileName = args[1]
    val outputFileName = args[2]

    val graph = DeBruijnGraph(k)
    File(inputFileName).forEachLine { line ->
        graph.addKmer(line)
    }

    val eulerianPath = graph.findEulerianPath()

    File(outputFileName).bufferedWriter().use { out ->
        out.write("DeBruijnGraph:\n")
        graph.edges.forEach { (key, value) ->
            out.write("$key=[${value.joinToString(",")}]\n")
        }
        out.write("\nEurelianPath:\n")
        out.write(eulerianPath.joinToString(" -> "))
    }
}

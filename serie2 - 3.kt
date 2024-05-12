class HashNode<K, V>(override val key: K, override var value: V) : MutableEntry<K, V> {
    var next: HashNode<K, V>? = null
    override fun setValue(newValue: V): V {
        val old = value
        value = newValue
        return old
    }
}

interface MyMutableMap<K, V> : Iterable<MutableEntry<K, V>> {
    val size: Int
    var capacity: Int  // Mudado de 'val' para 'var'
    operator fun get(key: K): V?
    fun put(key: K, value: V): V?
}

interface MutableEntry<K, V> {
    val key: K
    var value: V
    fun setValue(newValue: V): V
}

class MyHashMap<K, V> : MyMutableMap<K, V> {
    private var _size = 0
    private var table = arrayOfNulls<HashNode<K, V>>(INITIAL_CAPACITY)

    override val size: Int
        get() = this._size

    override var capacity: Int
        get() = table.size
        set(value) {
            if (value > table.size) {
                val newTable = arrayOfNulls<HashNode<K, V>>(value)
                table.copyInto(newTable)
                table = newTable
            }
        }

    override fun put(key: K, value: V): V? {
        val hash = key.hashCode() % capacity
        var node = table[hash]
        while (node != null) {
            if (node.key == key) {
                val oldValue = node.value
                node.value = value
                return oldValue
            }
            node = node.next
        }
        node = HashNode(key, value)
        node.next = table[hash]
        table[hash] = node
        _size++
        if (_size >= capacity * LOAD_FACTOR) {
            expandTable()
        }
        return null
    }

    override fun get(key: K): V? {
        val hash = key.hashCode() % capacity
        var node = table[hash]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return null
    }

    override fun iterator(): Iterator<MutableEntry<K, V>> {
        val entries = mutableListOf<MutableEntry<K, V>>()
        for (node in table) {
            var currentNode = node
            while (currentNode != null) {
                entries.add(currentNode)
                currentNode = currentNode.next
            }
        }
        return entries.iterator()
    }

    private fun expandTable() {
        val oldTable = table
        table = arrayOfNulls(table.size * 2)
        _size = 0
        for (node in oldTable) {
            var currentNode = node
            while (currentNode != null) {
                put(currentNode.key, currentNode.value)
                currentNode = currentNode.next
            }
        }
    }

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}


fun main() {
    val myMap: MyMutableMap<String, Int> = MyHashMap()

    // Colocando alguns valores
    myMap.put("Um", 1)
    myMap.put("Dois", 2)
    myMap.put("Três", 3)

    // Imprimindo o tamanho e a capacidade do mapa
    println("Tamanho: ${myMap.size}, Capacidade: ${myMap.capacity}")

    // Obtendo e imprimindo alguns valores
    println("Um: ${myMap.get("Um")}")
    println("Dois: ${myMap.get("Dois")}")
    println("Três: ${myMap.get("Três")}")

    // Iterando sobre as entradas do mapa
    for (entry in myMap) {
        println("${entry.key}: ${entry.value}")
    }
}

package weeks_With_KOTLIN.examination.dataStruktur

class HierarchyTree(private val root: ElfNode) {

    fun getSubordinates(elfName: String): List<ElfNode> {
        val result = mutableListOf<ElfNode>()
        val elf = findElf(elfName, root)
        if (elf != null) {
            getSubordinatesRecursive(elf, result)
        }
        return result
    }

    private fun getSubordinatesRecursive(elfNode: ElfNode, result: MutableList<ElfNode>) {
        for (child in elfNode.children) {
            result.add(child)
            getSubordinatesRecursive(child, result)
        }
    }

    private fun findElf(name: String, node: ElfNode): ElfNode? {
        if (node.name == name) {
            return node
        }
        for (child in node.children) {
            val result = findElf(name, child)
            if (result != null) {
                return result
            }
        }
        return null
    }
}

fun createHierarchyTree(hierarchy: Map<String, List<String>>, rootName: String): HierarchyTree {

    val elfNodes = hierarchy.keys.associateWithTo(mutableMapOf()) { ElfNode(it) }

    for ((elfName, subordinates) in hierarchy) {
        val elfNode = elfNodes[elfName]
        for (subordinate in subordinates) {
            val subordinateNode = elfNodes[subordinate]
            if (subordinateNode != null) {
                elfNode?.addChild(subordinateNode)
            }
        }
    }
    return HierarchyTree(elfNodes[rootName] ?: error("No root node found in hierarchy"))
}
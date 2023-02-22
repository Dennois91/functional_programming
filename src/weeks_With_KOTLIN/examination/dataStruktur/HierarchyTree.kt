package weeks_With_KOTLIN.examination.dataStruktur

class HierarchyTree(val root: ElfNode) {

    fun getSubordinates(elfName: String): List<ElfNode> {
        val subordinatesList = mutableListOf<ElfNode>()

        fun getSubordinatesRecursive(elfNode: ElfNode) {
            for (child in elfNode.children) {
                subordinatesList.add(child)
                getSubordinatesRecursive(child)
            }
        }

        val elf = findElf(elfName, root)
        if (elf != null) {
            getSubordinatesRecursive(elf)
        }
        return subordinatesList.toList()
    }

    private fun findElf(name: String, node: ElfNode): ElfNode? {
        return when (node.name) {
            name -> node
            else -> node.children.firstNotNullOfOrNull { findElf(name, it) }
        }
    }
}

/*
Skapar en hierarkisk trädstruktur från en map av noder och deras underordnade noder.
Funktionen skapar en ny instans av ElfNode för varje alv i mappen
lägger till alla underordnade noder för varje nod och returnerar en ny instans av -
HierarchyTree som innehåller hela trädstrukturen med rot-noden som specificerats.
*/

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
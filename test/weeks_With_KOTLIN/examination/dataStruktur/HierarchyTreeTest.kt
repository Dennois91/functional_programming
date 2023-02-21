package weeks_With_KOTLIN.examination.dataStruktur

import org.junit.jupiter.api.Test

class HierarchyTreeTest {


    private val hierarchy = mapOf(
        "Master" to listOf("Middleman", "Slave"),
        "Middleman" to listOf("Slave"),
        "Slave" to emptyList()
    )
    private val hierarchyTree = createHierarchyTree(hierarchy, "Master")


    @Test
    fun testCreateHierarchyTree() {
        assert(hierarchyTree is HierarchyTree)
    }

    @Test
    fun testGetSubordinates_MasterContainsSubordinates() {
        hierarchyTree.getSubordinates("Middleman").forEach { subject -> assert(subject.children.isEmpty()) }
        hierarchyTree.getSubordinates("Master").forEach { subject -> assert(subject.name.isNotEmpty()) }
    }

    @Test
    fun testGetSubordinates_MasterIsRoot(){
        hierarchyTree.getSubordinates("Master").forEach { subject -> assert(subject.name != "Master") }
    }

    @Test
    fun testGetSubordinates_SlaveIsLast(){
        hierarchyTree.getSubordinates("Middleman").forEach { subject -> assert(subject.name == "Slave") }
    }
}
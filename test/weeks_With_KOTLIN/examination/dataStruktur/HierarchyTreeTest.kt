package weeks_With_KOTLIN.examination.dataStruktur

import org.junit.jupiter.api.Test

class HierarchyTreeTest {


    private val hierarchy = mapOf(
        "Master" to listOf("Middleman"),
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
        val subordinatesOfSlave = hierarchyTree.getSubordinates("Slave")
        val subordinatesOfMiddleman = hierarchyTree.getSubordinates("Middleman")
        val subordinatesOfMaster = hierarchyTree.getSubordinates("Master")

        assert(subordinatesOfSlave.isEmpty())
        assert(subordinatesOfMiddleman.size == 1 && subordinatesOfMiddleman[0].name == "Slave")
        assert(subordinatesOfMaster.size == 2 && subordinatesOfMaster[0].name == "Middleman")
    }

    @Test
    fun testGetSubordinates_MasterIsRoot() {
        val rootName = hierarchyTree.root.name
        assert(rootName == "Master")
    }


    @Test
    fun testGetSubordinates_SlaveIsLast() {
        val subordinatesOfMiddleman = hierarchyTree.getSubordinates("Middleman")
        val lastSubordinate = subordinatesOfMiddleman.last()
        assert(lastSubordinate.name == "Slave")
    }
}
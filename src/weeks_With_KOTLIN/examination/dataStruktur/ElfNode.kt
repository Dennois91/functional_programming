package weeks_With_KOTLIN.examination.dataStruktur

class ElfNode(val name: String) {
    val children = mutableListOf<ElfNode>()

    fun addChild(child: ElfNode) {
        children.add(child)
    }
}

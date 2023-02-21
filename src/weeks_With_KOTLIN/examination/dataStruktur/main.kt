package weeks_With_KOTLIN.examination.dataStruktur

/*
Tomtarna på Nordpolen har en strikt chefs-hierarki:
Högsta chefen för allt är "Tomten"
Under "Tomten" jobbar "Glader" och "Butter"
Under "Glader" jobbar "Tröger", "Trötter" och "Blyger"
Under "Butter" jobbar "Rådjuret", "Nyckelpigan", "Haren" och "Räven"
Under "Trötter" jobbar "Skumtomten"
Under "Skumtomten" jobbar "Dammråttan"
Under "Räven" jobbar "Gråsuggan" och "Myran"
Under "Myran" jobbar "Bladlusen"

 */
/* Tröger, Blyger, Rådjuret, Nyckelpigan, Haren, Dammråttan, Gråsuggan, Bladlusen
 */
fun main() {

    val hierarchy = mapOf(
        "Tomten" to listOf("Glader", "Butter"),
        "Glader" to listOf("Tröger", "Trötter", "Blyger"),
        "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),
        "Trötter" to listOf("Skumtomten"),
        "Skumtomten" to listOf("Dammråttan"),
        "Räven" to listOf("Gråsuggan", "Myran"),
        "Myran" to listOf("Bladlusen"),
        "Tröger" to emptyList(),
        "Blyger" to emptyList(),
        "Rådjuret" to emptyList(),
        "Nyckelpigan" to emptyList(),
        "Haren" to emptyList(),
        "Dammråttan" to emptyList(),
        "Gråsuggan" to emptyList(),
        "Bladlusen" to emptyList()
    )

    val hierarchyTree = createHierarchyTree(hierarchy,"Tomten")
    hierarchyTree.getSubordinates("Butter").forEach { elf -> println(elf.name) }

}



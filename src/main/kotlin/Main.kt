import rssfeeder.RssFeeder
import rssfeeder.sort.SortStrategy

fun main() {
    rssFeederNarrowingQuery()
    rssFeederSortingByAttrHrefValue()
    rssFeederCompoundedSortingStrategy()
}

fun rssFeederNarrowingQuery() {

    val feeder = RssFeeder.getInstance("https://en.wikipedia.org")
    val rssRef = feeder.getReference().childOf("li").childOf("b").childOf("a")

    println("<<rssFeederNarrowingQuery>>")

    println(rssRef.isConstrained())
    println(rssRef.isEvaluated())
    println(rssRef.elems().count().toString() + "개")
    println(rssRef.isConstrained())
    println(rssRef.isEvaluated())

    rssRef.elems().forEach {
        println(it.parent().html())
    }
}

fun rssFeederSortingByAttrHrefValue() {
    val feeder = RssFeeder.getInstance("https://en.wikipedia.org")
    val rssRef = feeder.getReference().childOf("li").childOf("b").childOf("a")

    val strategyHrefValueDescending = SortStrategy.AttrValue("href", false)
    val elems = rssRef.enableSort(strategyHrefValueDescending).elems()

    println("<<rssFeederSortingByAttrHrefValue>>")
    elems.forEach {
        println(it.attr("href"))
    }
}

//TODO: Implement compounded sorting strategy feature.
fun rssFeederCompoundedSortingStrategy() {
    val feeder = RssFeeder.getInstance("https://en.wikipedia.org")
    val strategyChilrentCount = SortStrategy.ChildrenCount()
    val strategyHrefValueDescending = SortStrategy.AttrValue("href", false)
    val rssRef = feeder.getReference("li").enableSort(strategyChilrentCount)
        .childOf("b")
        .childOf("a").enableSort(strategyHrefValueDescending)
    val elems = rssRef.elems()

    println("<<rssFeederCompoundedSortingStrategy>>")
    elems.forEach {
        println(it.attr("href"))
    }
}

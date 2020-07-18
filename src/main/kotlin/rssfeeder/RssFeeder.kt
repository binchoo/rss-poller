package rssfeeder

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class RssFeeder private constructor(val document: Document) {
    private lateinit var rssReference: RssReference

    fun getReference(): RssReference {
        rssReference = RssReference(document)
        return rssReference
    }

    fun getReference(cssQuery: String): RssReference {
        rssReference = RssReference(document, cssQuery)
        return rssReference
    }

    companion object {
        fun getInstance(urlstr: String): RssFeeder {
            return getInstance(Jsoup.connect(urlstr))
        }

        fun getInstance(jsoupConnection: Connection): RssFeeder {
            val instance = RssFeeder(jsoupConnection.get())
            return instance
        }
    }
}
package ntnk.sample.readrssexample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler extends DefaultHandler {
    private List<RssItem> list;
    RssItem rssItem;

    private boolean atItem;
    private boolean atTitle = false;
    private  boolean atDescription = false;
    private  boolean atPublishDate = false;

    public ItemHandler() {
        list = new ArrayList<>();
        rssItem = new RssItem();

        atItem = false;
        atTitle = false;
        atDescription = false;
       atPublishDate = false;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("item")){
            atItem = true;
            RssItem rssItem = new RssItem();
        }
        if(atItem){
        if(qName.equals("title")){
            atTitle = true;
        }
        if(qName.equals("description")){
            atDescription = true;
        }
        if(qName.equals("pubDate")){
            atPublishDate = true;
        }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(atTitle){

        }
    }
}

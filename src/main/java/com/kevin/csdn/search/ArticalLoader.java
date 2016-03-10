package com.kevin.csdn.search;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ArticalLoader {

	public static void main(String[] args) throws Exception{
//		getList();
//		getRawContent();
		getList();
	}
	
	static String listUrl = "http://blog.csdn.net/shikaiwencn?viewmode=contents";
	
	public static String getRawContent(String url ) throws IOException{
//		HttpURLConnection conn = (HttpURLConnection)new URL("http://www.csdn.net/").openConnection();
		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
//		conn.setRequestProperty("Accept", "text/html, application/xhtml+xml, */*");
//		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-Hans-CN;q=0.5,zh-Hans;q=0.3");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		conn.setRequestProperty("Host", "blog.csdn.net");
		
		InputStream is = conn.getInputStream();
		byte [] bytes = new byte[100];
		int len = 0;
		StringBuilder content = new StringBuilder();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while( (len=is.read(bytes)) > 0 ){
			content.append( new String(bytes,0,len,"utf-8")  );
			
			baos.write(bytes, 0, len);
			
		}
		
//		System.out.println( content.toString() );
		String str = new String(baos.toByteArray(),"UTF-8");
		System.out.println(str);
		return str;
	}
	
	public static void getList() throws IOException, Exception{
//		Document doc = Jsoup.connect("http://blog.csdn.net/shikaiwencn?viewmode=contents").get();
		Document doc = Jsoup.parse( getRawContent(listUrl) );
		
		Elements elts = doc.select("#article_list .list_item");
		Iterator<Element> iter = elts.iterator();
		for(;iter.hasNext();){
			
			Element elt = iter.next();
			Elements contentItem = elt.getElementsByClass("link_title");
			Element a = contentItem.get(0).child(0);
			String href = a.attr("href");
			String title = a.html();
			System.out.println(title + "\t" + href );
			
			
			String urlPrefix = "http://blog.csdn.net";
			handleItem(title,urlPrefix + href);
		}
			
		
		IndexManager.getIndexWriter().commit();
		IndexManager.getIndexWriter().close();
		
		IndexReader reader = IndexManager.getIndexReader();
		QueryParser parser = new QueryParser(Version.LUCENE_4_9,"content",new StandardAnalyzer(Version.LUCENE_4_9));
		Query query = parser.parse("php其实还挺好用的");
		
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs topDoc = searcher.search(query, 10);
		int hits = topDoc.totalHits;
		if(hits > 0){
			ScoreDoc [] scoreDocs =  topDoc.scoreDocs;
			for(int i = 0 ;i < scoreDocs.length; i++){
				int docId = scoreDocs[i].doc;
				org.apache.lucene.document.Document hitDoc = searcher.doc(docId);
				String title = hitDoc.get("title");
				System.out.println("检索到的title:"+title);
			}
		}
	}
	
	static int articleCount = 0;
	
	public static void handleItem(String title,String href) throws IOException{
		
		String articlePageContentStr =  getRawContent(href);
		Document articlePageDoc = Jsoup.parse(articlePageContentStr);
		
		// to get he article content 
		String articleContent = articlePageDoc.select("#article_content").get(0).html();
		
		IndexWriter writer = IndexManager.getIndexWriter();
		
		org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
		StringField idField = new StringField("id", ++articleCount+"", Store.YES);
		StringField titleField = new StringField("title",title,Store.YES);
		
		TextField contentField = new TextField("content",articleContent,Store.YES);
		
		doc.add(idField);
		doc.add(titleField);
		doc.add(contentField);
		
		writer.addDocument(doc);
		
	}
	
	
	
	
	
}

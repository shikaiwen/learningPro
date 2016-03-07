package com.kevin.learning.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * 学习地址:http://oak.cs.ucla.edu/cs144/projects/lucene/
 * 不能重发构建索引，否则结果会重复,只在第一次运行时构建
 * @author Administrator
 *
 */

class Hotel{
	private String id;
	private String name;
	private String city;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}


public class HotelSearch {

	// 是否创建索引
	static boolean createIndex = false;
	
	public static String indexPath = "d:/tmp/index-dir";
	
	public static void main(String[] args) throws Exception {
		
		indexHotels();
		 search("beijing",10);

	}
	
	
	public static IndexWriter getIndexWriter() {
		Directory indexDir;
		IndexWriter writer = null;
		try {
			indexDir = FSDirectory.open(new File(indexPath));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9,new StandardAnalyzer(Version.LUCENE_4_9));
			writer = new IndexWriter(indexDir,config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	
	public static ScoreDoc[] search(String queryStr,int n){
		
 		ScoreDoc [] result = null;
		try{
			Directory dir = FSDirectory.open(new File(indexPath));
			DirectoryReader reader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			
			QueryParser parser = new QueryParser(Version.LUCENE_4_9,"content",new StandardAnalyzer(Version.LUCENE_4_9));
			
			Query query = parser.parse(queryStr);
			TopDocs docs = searcher.search(query, n);
			ScoreDoc [] hits = docs.scoreDocs;
			
			for(int i = 0; i < hits.length ;i ++){
				int docId = hits[i].doc;
 				Document d = searcher.doc(docId );
				String id = d.get("id");
				String name = d.get("name");
				String city = d.get("city");
				String desc = d.get("description");
				System.out.println("id:"+ id +  ",name:" + name + ", city:"+city + ",desc=" + desc);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void indexHotels(){
		
		IndexWriter writer = getIndexWriter();
		
		if(!createIndex) return;
		
		List<Hotel> hotels = getHotels();
		
		for(int i =0 ;i < hotels.size();i ++){
			Hotel h = hotels.get(i);
			Document doc = new Document();
			StringField idF = new StringField("id",h.getId(),Store.YES);
			StringField nameF = new StringField("name", h.getName(),Store.YES);
			StringField citySF = new StringField("city", h.getCity(),Store.YES);
			
			StringField descTF = new StringField("description",h.getDescription(),Store.YES);
			
			
//			String fullText = h.getName() + " " + h.getCity() + " " + h.getDescription();
			String fullText = h.getName() + " " + h.getCity() + " " + h.getId() + " " + h.getDescription();
			TextField contentTF = new TextField("content",fullText ,Store.NO);
			
			
			doc.add(idF);
			doc.add(nameF);
			doc.add(citySF);
			doc.add(descTF);
			doc.add(contentTF);
			try {
				writer.addDocument( doc );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static List<Hotel> getHotels(){
		
		List<Hotel> list = new ArrayList<Hotel>();
		Hotel t1 = new Hotel();
		t1.setId("1");
		t1.setCity("shenzhen");
		t1.setDescription("good climate");
		t1.setName("7day");
		
		Hotel t2 = new Hotel();
		t2.setId("2");
		t2.setCity("guangzhou");
		t2.setDescription("it's ok ");
		t2.setName("7day");
		
		Hotel t3 = new Hotel();
		t3.setId("3");
		t3.setCity("beijing");
		t3.setDescription(" rujia bei");
		t3.setName("rujia");
		
		Hotel t4 = new Hotel();
		t4.setId("4");
		t4.setCity("beijing");
		t4.setDescription("capital city");
		t4.setName("huatian");
		
		Hotel t5 = new Hotel();
		t5.setId("5");
		t5.setCity("上海");
		t5.setDescription("five start");
		t5.setName("rujia");
		
		
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		return list;
	}
	
	
}

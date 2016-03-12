package com.kevin.csdn.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexManager {


	public static final String fileDir = "j:/tmp/articalIndex";
	
	static IndexWriter indexWriter = null;
	static IndexReader indexReader = null;
	
	public static IndexWriter getIndexWriter(){
		try {
			if(indexWriter != null) return indexWriter;
			
			Directory directory = FSDirectory.open(new File(fileDir));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9, new StandardAnalyzer(Version.LUCENE_4_9));
			IndexWriter writer = new IndexWriter(directory,config);
			indexWriter = writer;
			return indexWriter;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static IndexReader getIndexReader(){
		try {
			if(indexReader != null) return indexReader;
			
			Directory dir = FSDirectory.open(new File(fileDir));
			DirectoryReader reader = DirectoryReader.open(dir);
			indexReader = reader;
			return indexReader ; 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

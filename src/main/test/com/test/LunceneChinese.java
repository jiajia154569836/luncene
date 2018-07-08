//package com.test;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
//import org.apache.lucene.analysis.core.SimpleAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//
//public class LunceneChinese {
//
//    public static String PATH = "E:\\workspace\\git\\luncene\\index\\hello002";
//    String doc1 = "哈哈";
//    String doc2 ="哈哈 祖国";
//    String doc3 ="哈哈 源码";
//
//    /**
//     * 1 indexwriter
//     * 2 document
//     * 3 document 写入 index
//     */
//
//    @Test
//    public  void testCreate() throws IOException {
//        Directory d = FSDirectory.open(Paths.get(PATH));
//        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
//        IndexWriter indexWriter = new IndexWriter(d,conf);
//
//        Document document1= new Document();
//        document1.add(new TextField("id","1",Field.Store.YES));
//        document1.add(new TextField("title","doc1",Field.Store.YES));
//        document1.add(new TextField("content",doc1,Field.Store.YES));
//        Document document2= new Document();
//        document2.add(new TextField("id","2",Field.Store.YES));
//        document2.add(new TextField("title","doc2",Field.Store.YES));
//        document2.add(new TextField("content",doc2,Field.Store.YES));
//
//        Document document3= new Document();
//        document3.add(new TextField("id","3",Field.Store.YES));
//        document3.add(new TextField("title","doc3",Field.Store.YES));
//        document3.add(new TextField("content",doc3,Field.Store.YES));
//
//
//        indexWriter.addDocument(document1);
//        indexWriter.addDocument(document2);
//        indexWriter.addDocument(document3);
//        indexWriter.commit();
//        indexWriter.close();
//
//    }
//
//    @Test
//    public void testSearch() throws IOException, ParseException {
//        Directory d = FSDirectory.open(Paths.get(PATH));
//        IndexReader reader = DirectoryReader.open(d);
//        IndexSearcher searcher = new IndexSearcher(reader);
//
//        String parse = "content:哈哈";
//
//        Analyzer analyzer = new SmartChineseAnalyzer();
//        String defa = "content";
//        QueryParser pa = new QueryParser(defa,analyzer);
//
//        Query query = pa.parse(parse);
//
//        TopDocs topDocs = searcher.search(query,10);
//
//        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
//        for (ScoreDoc s : scoreDoc)
//        {
//            int docid = s.doc;
//            Document document = searcher.doc(docid);
//            System.out.println(document.get("id"));
//            System.out.println(document.get("title"));
//            System.out.println(document.get("content"));
//
//        }
//
//
//    }
//}

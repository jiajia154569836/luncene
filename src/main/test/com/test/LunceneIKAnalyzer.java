//package com.test;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.TokenStream;
////import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
////import org.apache.lucene.analysis.core.SimpleAnalyzer;
////import org.apache.lucene.document.Document;
////import org.apache.lucene.document.Field;
////import org.apache.lucene.document.TextField;
////import org.apache.lucene.index.DirectoryReader;
////import org.apache.lucene.index.IndexReader;
////import org.apache.lucene.index.IndexWriter;
////import org.apache.lucene.index.IndexWriterConfig;
////import org.apache.lucene.queryparser.classic.ParseException;
////import org.apache.lucene.queryparser.classic.QueryParser;
////import org.apache.lucene.search.IndexSearcher;
////import org.apache.lucene.search.Query;
////import org.apache.lucene.search.ScoreDoc;
////import org.apache.lucene.search.TopDocs;
////import org.apache.lucene.store.Directory;
////import org.apache.lucene.store.FSDirectory;
//
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.nio.file.Paths;
//
//public class LunceneIKAnalyzer {
//
//    public static String PATH = "E:\\workspace\\git\\luncene\\index\\hello003";
//    String doc1 = "源代码的开源  dadad  hahaada 哈哈";
//    String doc2 ="哈哈 dadads";
//    String doc3 ="wrwrwr 源码";
//
//    /**
//     * 1 indexwriter
//     * 2 document
//     * 3 document 写入 index
//     */
//
//   /* @Test
//    public  void testCreate() throws IOException {
//        Directory d = FSDirectory.open(Paths.get(PATH));
//        IndexWriterConfig conf = new IndexWriterConfig(new IKAnalyzer());
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
//*/
//
//    public void testAnalyzer(Analyzer analyzer,String str) throws IOException {
//        TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(str));
//        tokenStream.reset();
//        while (tokenStream.incrementToken())
//        {
//            System.out.println(tokenStream);
//        }
//    }
//
//
//    @Test
//    public void testIk () throws IOException {
//        //true------最大化原则
//        //不写------最小化原则-------细粒度划分
//        testAnalyzer(new IKAnalyzer(true),doc1);
//    }
//
//
////    @Test
////    public void testSimple () throws IOException {
////        testAnalyzer(new SimpleAnalyzer(),doc1);
////    }
//}

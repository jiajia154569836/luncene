package com.test;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class LunceneQuery {
    public static String PATH = "E:\\workspace\\git\\luncene\\index\\hello006";
    String doc1 = "hello haha abc";
    String doc2 = "hello haha java";
    String doc3 = "hello haha  源码";
    String doc4 = "hello haha  File练习";

    /**
     * 1 indexwriter
     * 2 document
     * 3 document 写入 indexwriter
     */

    @Test
    public void testCreate() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexWriterConfig conf = new IndexWriterConfig(new SimpleAnalyzer());
        IndexWriter indexWriter = new IndexWriter(d, conf);

        Document document1 = new Document();
        document1.add(new TextField("id", "1", Field.Store.YES));
        document1.add(new TextField("title", "doc1", Field.Store.YES));
        document1.add(new TextField("content", doc1, Field.Store.YES));
        Document document2 = new Document();
        document2.add(new TextField("id", "2", Field.Store.YES));
        document2.add(new TextField("title", "doc2", Field.Store.YES));
        document2.add(new TextField("content", doc2, Field.Store.YES));

        Document document3 = new Document();
        document3.add(new TextField("id", "3", Field.Store.YES));
        document3.add(new TextField("title", "doc3", Field.Store.YES));
        document3.add(new TextField("content", doc3, Field.Store.YES));


        Document document4 = new Document();
        document4.add(new TextField("id", "4", Field.Store.YES));
        document4.add(new TextField("title", "doc4", Field.Store.YES));
        document4.add(new TextField("content", doc4, Field.Store.YES));

        indexWriter.addDocument(document1);
        indexWriter.addDocument(document2);
        indexWriter.addDocument(document3);
        indexWriter.addDocument(document4);


        indexWriter.commit();
        indexWriter.close();

    }

    //查询短语  PhraseQuery
    @Test
    public void testPhraseQuery() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);
        //新建查询
        PhraseQuery query = new PhraseQuery();
        //设置分词之间的距离  hello (10之内都可以) abc
        query.setSlop(10);
        //添加前一个和后一个分词
        query.add(new Term("content", "hello"));
        query.add(new Term("content", "abc"));
        TopDocs topDocs = searcher.search(query, 10);
        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc) {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
    }

    //通配符查询  WildcardQuery
    @Test
    public void testWildcardQuery() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);
        //新建查询
        Term term = new Term("content", "he???");
        Query query = new WildcardQuery(term);

        TopDocs topDocs = searcher.search(query, 10);
        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc) {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
    }

    //模糊查询   容错查询  FuzzyQuery
    @Test
    public void testFuzzyQuery() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);
        //新建查询
        Term term = new Term("content", "hekko");
        FuzzyQuery query = new FuzzyQuery(term);

        TopDocs topDocs = searcher.search(query, 10);
        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc) {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
    }

    //组合查询  FuzzyQuery
    @Test
    public void testBooleanQuery() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);
        //新建查询
        BooleanQuery boolQuery = new BooleanQuery();
        Query query1 = new TermQuery(new Term("content", "hello"));
        Query query2 = new TermQuery(new Term("content", "java"));
        boolQuery.add(query1, BooleanClause.Occur.MUST);
        boolQuery.add(query2, BooleanClause.Occur.MUST);

        TopDocs topDocs = searcher.search(boolQuery, 10);
        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc) {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
    }

    @Test
    public void testSearch() throws IOException, ParseException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);

        String parse = "content:hello";

        Analyzer analyzer = new SimpleAnalyzer();
        String defa = "content";
        QueryParser pa = new QueryParser(defa, analyzer);

        Query query = pa.parse(parse);

        TopDocs topDocs = searcher.search(query, 10);

        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc) {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
    }

}

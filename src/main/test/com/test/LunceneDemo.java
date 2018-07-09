package com.test;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class LunceneDemo {
    public static String PATH = "E:\\workspace\\git\\luncene\\index\\hello001";
    String doc1 = "hello abc";
    String doc2 = "hello java";
    String doc3 = "hello 源码";
    String doc4 = "hello File练习";

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

        indexWriter.addDocument(document1);
        indexWriter.addDocument(document2);
        indexWriter.addDocument(document3);


        indexWriter.commit();
        indexWriter.close();

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

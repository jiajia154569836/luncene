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

public class LunceneField {

    public static String PATH = "E:\\workspace\\git\\luncene\\index\\hello004";
    String doc1 = "hello";
    String doc2 ="hello java";
    String doc3 ="hello 源码";
    String doc4 ="hello File练习";

    /**
     * 1 indexwriter
     * 2 document
     * 3 document 写入 indexwriter
     */

    @Test
    public  void testCreate() throws IOException {
        Directory d = FSDirectory.open(Paths.get(PATH));
        IndexWriterConfig conf = new IndexWriterConfig(new SimpleAnalyzer());
        IndexWriter indexWriter = new IndexWriter(d,conf);

        Document document4= new Document();
        document4.add(new TextField("id","4",Field.Store.YES));
        document4.add(new TextField("title","doc4",Field.Store.YES));

        FieldType type = new FieldType();
        type.setStored(true);//是否存储数据库
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);//该字段是否创建索引
        type.setTokenized(true);//是否分词
        Field field = new Field("content",doc4,type);
        document4.add(field);
        indexWriter.addDocument(document4);

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
        QueryParser pa = new QueryParser(defa,analyzer);

        Query query = pa.parse(parse);

        TopDocs topDocs = searcher.search(query,10);

        ScoreDoc[] scoreDoc = topDocs.scoreDocs;
        for (ScoreDoc s : scoreDoc)
        {
            int docid = s.doc;
            Document document = searcher.doc(docid);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));

        }


    }
}


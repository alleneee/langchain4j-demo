package com.langchain4jdemo.aiservice;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    private final RedisEmbeddingStore redisEmbeddingStore;

    public void parseAndStoreEmbeddings(){
        TextDocumentParser textDocumentParser =new TextDocumentParser();
        Document document = loadDocument(toPath(), textDocumentParser);
        DocumentSplitter splitter = DocumentSplitters.recursive(300, 0);
        List<TextSegment> segments = splitter.split(document);
        Response<List<Embedding>> embeddedRes = embeddingModel.embedAll(segments);
        List<Embedding> embeddings = embeddedRes.content();
        List<String> strings = redisEmbeddingStore.addAll(embeddings);
    }

    public static Path toPath() {
        try {
            URL fileUrl = Utils.class.getClassLoader().getResource("documents/biography-of-john-doe.txt");
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

package opennlp;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import opennlp.tools.chunker.Chunker;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.junit.Test;

public class OpenNLPTest {

	private static final String MODELS = "models";

	@Test
	public void Sentence_Detector_API() throws Exception {
		// Arrange
		File modelFile = new File(MODELS, "en-sent.bin");
		SentenceModel model = new SentenceModel(modelFile);
		SentenceDetector detector = new SentenceDetectorME(model);

		// Act
		String[] sentences = detector
				.sentDetect("First sentence. Second sentence.");

		// Assert
		assertThat(sentences.length, is(2));
	}

	@Test
	public void Tokenizer_API() throws Exception {
		// Arrange
		File modelFile = new File(MODELS, "en-token.bin");
		TokenizerModel model = new TokenizerModel(modelFile);
		Tokenizer tokenizer = new TokenizerME(model);

		// Act
		String[] tokens = tokenizer.tokenize("These are five tokens.");

		// Assert
		assertThat(tokens.length, is(5));
	}

	@Test
	public void Name_Finder_API() throws Exception {
		// Arrange
		File modelFile = new File(MODELS, "en-ner-person.bin");
		TokenNameFinderModel model = new TokenNameFinderModel(modelFile);
		TokenNameFinder finder = new NameFinderME(model);
		String[] tokens = new String[] { "My", "name", "is", "Thomas", "." };

		// Act
		Span[] findings = finder.find(tokens);

		// Assert
		String firstName = tokens[findings[0].getStart()];
		assertThat(firstName, is(equalTo("Thomas")));
	}

	@Test
	public void Document_Categorizer_API() throws Exception {
		fail();
	}

	@Test
	public void Part_of_Speech_Tagger_API() throws Exception {
		File modelFile = new File(MODELS, "en-pos-maxent.bin");
		String[] tokens = new String[] { "My", "name", "is", "Thomas", "." };
		String[] expectedTags = { "PRP$", "NN", "VBZ", "NNP", "." };
		POSModel model = new POSModel(modelFile);
		POSTagger tagger = new POSTaggerME(model);

		String[] tags = tagger.tag(tokens);

		assertThat(tags, is(equalTo(expectedTags)));
	}

	@Test
	public void Chunker_API() throws Exception {
		File modelFile = new File(MODELS, "en-chunker.bin");
		ChunkerModel model = new ChunkerModel(modelFile);
		Chunker chunker = new ChunkerME(model);
		String[] tokens = new String[] { "Rockwell", "International", "Corp."};
		String[] tags = new String[] { "NNP", "NNP", "NNP"};
		String[] expectedChunkerTags = new String[] {"B-NP", "I-NP", "I-NP"};
		
		String chunkTags[] = chunker.chunk(tokens, tags);
		
		assertThat(chunkTags, is(equalTo(expectedChunkerTags)));
	}

	@Test
	public void Parser_API() throws Exception {
		File modelFile = new File(MODELS, "en-parser-chunking.bin");
		ParserModel model = new ParserModel(modelFile);
		Parser parser = ParserFactory.create(model);
		String sentence = "The quick brown fox jumps over the lazy dog .";
		
		Parse parse = ParserTool.parseLine(sentence, parser, 1)[0];
		System.out.println("Parser_API():");
		parse.show();
	}

	@Test
	public void Coreference_Resolution_API() throws Exception {
		fail();
	}

}

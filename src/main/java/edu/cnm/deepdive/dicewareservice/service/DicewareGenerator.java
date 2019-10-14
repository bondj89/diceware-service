package edu.cnm.deepdive.dicewareservice.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicewareGenerator implements PassphraseGenerator {

  private final Random rng;
  private final List<String> words;


  @Autowired // Just for annotation.
  public DicewareGenerator(Random rng, WordProvider provider) {
    this.rng = rng;
    this.words = new ArrayList<>(provider.words());
  }


  @Override
  public String[] passphrase(int length) {
    return IntStream.generate(() -> rng.nextInt(words.size()))
        .limit(length)   //chop string off
        .mapToObj((value) -> words.get(value))
        .toArray(String[]::new); // gather all words together
  }
}

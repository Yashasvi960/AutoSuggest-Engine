package Trie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suggest")
public class TrieController {
  private final TrieService trieService;

  public TrieController(TrieService trieService) {
      this.trieService = trieService;
  }
  @GetMapping
  public List<String> getSuggestions(@RequestParam String query, @RequestParam(defaultValue = "5") int frequency ) {
    return trieService.getSuggestions(query, frequency);
  }
}

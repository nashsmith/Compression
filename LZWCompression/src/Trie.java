class Trie {

  TrieNode _root;

  public Trie(){
    //set root
    _root = new TrieNode(0);
    Byte b = new Byte(0);
    //prime the roots search tree with all byte patterns
    for(int i = 0; i < 256; i++){
      _root._values.insert(b);
      b++;
    }
  }

  class TrieNode {

    protected int _phraseKey;
    protected SearchTree _values;

    public TrieNode(int phraseKey){
      _phraseKey = phraseKey;
      _values = new SearchTree();
    }

    public int getPhraseKey(){
      return _phraseKey;
    }

    public SearchTree getValues(){
      return _phraseKey;
    }
  }

}

class Trie {

  TrieNode _root;

  /*  Constructor(boolean)
   *  Initialise the Trie, if the boolean is true, also prime the first trie node
   */
  public Trie(boolean prime){
    //set root
    _root = new TrieNode(0);

    if(prime){
      Byte[] bArray = new Byte[256];
      for(int i = -128; i < 128; i++){
        bArray[i + 128] = new Byte(Integer.toString(i));
      }
      //prime the roots search tree with all byte patterns
      _root._values = new SearchTree(bArray, 0, bArray.length - 1);
    }
  }

  /*  toString()
   *  Gets the toString value of the trienodes search tree.
   *  @returns String
   */
  public String toString(){
    return _root._values.toString();
  }

  class TrieNode {

    protected int _phraseKey;
    public SearchTree _values;

    public TrieNode(int phraseKey){
      _phraseKey = phraseKey;
      _values = new SearchTree();
    }

    public int getPhraseKey(){
      return _phraseKey;
    }

    public SearchTree getValues(){
      return _values;
    }

    /*  addPattern(Byte)
     *  Add a byte sequence to the tries list of patterns
     *  using SearchTree.insert()
     *  @returns void
     */
    public void addPattern(Byte pattern){
      _values.insert(pattern);
    }
  }

}

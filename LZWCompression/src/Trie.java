

class Trie {

  public TrieNode _root;

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

  public boolean contains(Byte[] pattern){
    TrieNode currentNode = _root;
    //System.out.println(_root);
    int count = 0;
    for(int i = 0; i < pattern.length; i++){
      if(currentNode == null){
        break;
      }
      if(currentNode.contains(pattern[i])){
        count++;
        currentNode = currentNode.findValue(pattern[i]).getNextTrieNode();
      }else{
        break;
      }
    }
    if(count == pattern.length){
      return true;
    }else{
      return false;
    }
  }

  /*  findPhrase(Byte[])
   *  Searches the for the longest matching phrasekey to the pattern.
   *  After each proceeding trienode is reached, the index is moved
   *  to the next byte pattern in the overall Byte[] pattern.
   *  @returns TrieNode
   */
  public TrieNode findPhrase(Byte[] pattern){
    int patternLength = pattern.length;
    TrieNode currentTrieNode = _root;
    int index = 0;

    while(currentTrieNode.contains(pattern[index])){
      if(currentTrieNode.getNextPhrase(pattern[index]) != null){
        currentTrieNode = currentTrieNode.getNextPhrase(pattern[index]);
      }else{
        break;
      }

      if(index < patternLength - 1){
        index++;
      }else{
        break;
      }
    }
    return currentTrieNode;
  }

  /*  findPhraseKey(Byte[])
   *  Searches the for the longest matching phrasekey to the pattern.
   *  After each proceeding trienode is reached, the index is moved
   *  to the next byte pattern in the overall Byte[] pattern.
   *  @returns int
   */
  // public int findPhraseKey(Byte[] pattern){
  //   int patternLength = pattern.length;
  //   TrieNode currentTrieNode = _root;
  //   int index = 0;
  //
  //   while(currentTrieNode.contains(pattern[index])){
  //     if(currentTrieNode.getNextPhrase(pattern[index]) != null){
  //       currentTrieNode = currentTrieNode.getNextPhrase(pattern[index]);
  //     }else{
  //       break;
  //     }
  //     if(index < patternLength - 1){
  //       index++;
  //     }else{
  //       break;
  //     }
  //   }
  //   return currentTrieNode._values.find(pattern[index]).getPhraseKey();
  // }

  /*  toString()
   *  Gets the toString value of the trienodes search tree.
   *  @returns String
   */
  public String toString(){
    return _root._values.toString();
  }



}

class TrieNode {

  public SearchTree _values;
  public int _phraseKey;

  public TrieNode(Byte pattern, int phraseKey){
    _values = new SearchTree(pattern);
    _phraseKey = phraseKey;
  }

  public TrieNode(int phraseKey){
    _values = new SearchTree();
    _phraseKey = phraseKey;
  }

  public SearchTree getValues(){
    return _values;
  }

  public String toString(){
    return "TrieNode: " + _values.toString();
  }

  public int getPhraseKey(){
    return _phraseKey;
  }

  /*  contains(Byte)
   *  Checks the trienodes values for the byte pattern.
   *  @returns boolean
   */
  public boolean contains(Byte pattern){
    if(_values.find(pattern) != null){
      return true;
    }
    return false;
  }

  public LeafNode findLeaf(Byte pattern){
    return _values.find(pattern);
  }

  /*  getNextPhrase(Byte)
   *  Gets the TrieNode for the next phrase. Null if the pattern isnt
   *  in this TrieNodes values
   *  @returns TrieNode
   */
  public TrieNode getNextPhrase(Byte pattern){
    LeafNode node = _values.find(pattern);
    if(node.getNextTrieNode() != null){
      return node.getNextTrieNode();
    }
    return null;
  }

  public LeafNode findValue(Byte pattern){
    return _values.find(pattern);
  }

  /*  addPattern(Byte)
   *  Add a byte sequence to the tries list of patterns
   *  using SearchTree.insert()
   *  @returns void
   */
  public void addPattern(Byte pattern, int phraseKey){
    _values.insert(pattern, phraseKey);
  }
}

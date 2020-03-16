
public class SearchTree {

  public LeafNode _root;

  /*Constructor setting value*/
  public SearchTree(){

    _root = new LeafNode(new Byte());

  }

/*  find(Byte)
 *  Finds a LeafNode conatining a byte pattern in the search tree.
 *  @returns LeafNode || null
 */
  public LeafNode find(Byte pattern){
    //set the current node to the root
		LeafNode currentNode = _root;

		//while we havent reached the end of the tree
		while(currentNode != null){
			//if the current node value is the byte passed in
			if(pattern == currentNode._value){
				//the tree contains the word
				return currentNode;
			}

			//go right if larger
			if(pattern.compareTo(currentNode._value) > 0 ){
				//current node is now the right subtree
				currentNode = currentNode._right;
			//go left
			}else{
				//current node is now the left subtree
				currentNode = currentNode._left;
			}
		}
		//if it hasnt returned true after going through whole tree then its not in the tree
		return null;
  }

  public void insert(LeafNode node){

  }

  //Search Tree Node
  class LeafNode {

		public Byte _value;
		public LeafNode _left;
		public LeafNode _right;
    public Trie _nextPhrase;

		/*Constructor setting value*/
		public LeafNode(Byte value){

			_value = value;
			_left = null;
			_right = null;
      _nextPhrase = null;

		}
	}
}

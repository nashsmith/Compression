
public class SearchTree {

  public LeafNode _root;

  /*Constructor setting value*/
 // public SearchTree(){

//    _root = new LeafNode(new Byte());

//  }

//  public int find(Byte pattern){
//
 // }

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

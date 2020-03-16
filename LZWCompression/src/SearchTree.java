
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


  /*  insert(LeafNode)
   *  Inserts a LeadNode into the tree depending on the nodes value
   *  @returns void
   */
  public void insert(LeafNode node){
    //currentNode to try insert into
    LeafNode currentNode;

    //if there is no value in the tree yet, the node goes into the root node
    if(_root == null){
      _root = node;
    }else {
      //otherwise set the currentNode to the root
      currentNode = _root;
    }

    //while the bottom of the tree hasnt been reached
    while(currentNode != null){

      //if the nodes value is less than the current node
      if(node._value.compareTo(currentNode) < 0){
        //check if the left subtree is empty. If empty, insert the node
        if(currentNode._left == null){
          currentNode._left = node;
          break;
        }else{
          //if not empty then traverse down the left branch
          currentNode = currentNode._left;
        }
      //if the nodes value is more than the current node
      }else{
        //check if the right subtree is empty. If empty, insert the node
        if(currentNode._right == null){
          currentNode._right = node;
          break;
        }else{
          //if not empty then traverse down the right branch
          currentNode = currentNode._right;
        }
      }

    }//end while
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

public class GlobalMembersDictionary
{

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
    //-----------------------------------------------------------------------------
    // Dictionary.c
    // Binary Search Tree implementation of the Dictionary ADT
    //-----------------------------------------------------------------------------


    // Dictionary.h
    // Header file for the Dictionary ADT
    //-----------------------------------------------------------------------------



    // Dictionary
    // Exported reference type

    // newDictionary()
    // constructor for the Dictionary type
    public static DictionaryObj newDictionary()
    {
//C++ TO JAVA CONVERTER TODO TASK: The memory management function 'malloc' has no equivalent in Java:
//C++ TO JAVA CONVERTER TODO TASK: There is no Java equivalent to 'sizeof':
       DictionaryObj D = malloc(sizeof(DictionaryObj));
       assert D!=null;
       D.root = null;
       D.numPairs = 0;
       return D;
    }

// freeDictionary()
// destructor for the Dictionary type

    // freeDictionary()
    // destructor for the Dictionary type
    public static void freeDictionary(struct[][] DictionaryObjpD)
    {
       if(pD!=null && *pD!=null)
       {
          GlobalMembersDictionary.makeEmpty(*pD);
//C++ TO JAVA CONVERTER TODO TASK: The memory management function 'free' has no equivalent in Java:
          free(*pD);
          *pD = null;
       }
    }

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none

    // isEmpty()
    // returns 1 (true) if D is empty, 0 (false) otherwise
    // pre: none
    public static int isEmpty(DictionaryObj D)
    {
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
          System.exit(1);
       }
       return (D.numPairs == 0);
    }

// size()
// returns the number of (key, value) pairs in D
// pre: none

    // size()
    // returns the number of (key, value) pairs in D
    // pre: none
    public static int size(DictionaryObj D)
    {
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
          System.exit(1);
       }
       return (D.numPairs);
    }

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none

    // lookup()
    // returns the value v such that (k, v) is in D, or returns NULL if no 
    // such value v exists.
    // pre: none
    public static String lookup(DictionaryObj D, tangible.RefObject<String> k)
    {
       NodeObj N;
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
          System.exit(1);
       }
   tangible.RefObject<String> tempRef_k = new tangible.RefObject<String>(k);
       N = GlobalMembersDictionary.findKey(D.root, tempRef_k);
       k.argValue = tempRef_k.argValue;
       return (N == null ? null : N.value);
    }

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL

    // insert()
    // inserts new (key,value) pair into D
    // pre: lookup(D, k)==NULL
    public static void insert(DictionaryObj D, tangible.RefObject<String> k, tangible.RefObject<String> v)
    {
       NodeObj N;
       NodeObj A;
       NodeObj B;
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
          System.exit(1);
       }
   tangible.RefObject<String> tempRef_k = new tangible.RefObject<String>(k);
       boolean tempVar = GlobalMembersDictionary.findKey(D.root, tempRef_k)!=null;
   k = tempRef_k.argValue;
   if (tempVar)
       {
          fprintf(stderr, "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k.argValue);
          System.exit(1);
       }
   tangible.RefObject<String> tempRef_k2 = new tangible.RefObject<String>(k);
   tangible.RefObject<String> tempRef_v = new tangible.RefObject<String>(v);
       N = GlobalMembersDictionary.newNode(tempRef_k2, tempRef_v);
       k.argValue = tempRef_k2.argValue;
       v.argValue = tempRef_v.argValue;
       B = null;
       A = D.root;
       while(A!=null)
       {
          B = A;
          if(strcmp(k.argValue, A.key)<0)
      {
              A = A.left;
      }
          else
      {
              A = A.right;
      }
       }
       if(B == null)
   {
           D.root = N;
   }
       else if(strcmp(k.argValue, B.key)<0)
   {
           B.left = N;
   }
       else
   {
           B.right = N;
   }
       D.numPairs++;
    }

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL

    // delete()
    // deletes pair with the key k
    // pre: lookup(D, k)!=NULL
    public static void delete(DictionaryObj D, tangible.RefObject<String> k)
    {
       NodeObj N;
       NodeObj P;
       NodeObj S;
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
          System.exit(1);
       }
   tangible.RefObject<String> tempRef_k = new tangible.RefObject<String>(k);
       N = GlobalMembersDictionary.findKey(D.root, tempRef_k);
       k.argValue = tempRef_k.argValue;
       if(N == null)
       {
          fprintf(stderr, "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k.argValue);
          System.exit(1);
       }
       if(N.left == null && N.right == null) // case 1 (no children)
       {
          if(N == D.root)
          {
             D.root = null;
             GlobalMembersDictionary.freeNode(N);
          }
          else
          {
             P = GlobalMembersDictionary.findParent(N, D.root);
             if(P.right == N)
         {
                 P.right = null;
         }
             else
         {
                 P.left = null;
         }
             GlobalMembersDictionary.freeNode(N);
          }
       } // case 2 (left but no right child)
       else if(N.right == null)
       {
          if(N == D.root)
          {
             D.root = N.left;
             GlobalMembersDictionary.freeNode(N);
          }
          else
          {
             P = GlobalMembersDictionary.findParent(N, D.root);
             if(P.right == N)
         {
                 P.right = N.left;
         }
             else
         {
                 P.left = N.left;
         }
             GlobalMembersDictionary.freeNode(N);
          }
       } // case 2 (right but no left child)
       else if(N.left == null)
       {
          if(N == D.root)
          {
             D.root = N.right;
             GlobalMembersDictionary.freeNode(N);
          }
          else
          {
             P = GlobalMembersDictionary.findParent(N, D.root);
             if(P.right == N)
         {
                 P.right = N.right;
         }
             else
         {
                 P.left = N.right;
         }
             GlobalMembersDictionary.freeNode(N);
          }
       } // case3: (two children: N->left!=NULL && N->right!=NULL)
       else
       {
          S = GlobalMembersDictionary.findLeftmost(N.right);
          N.key = (new Character((char)S.key)).toString();
          N.value = (new Character((char)S.value)).toString();
          P = GlobalMembersDictionary.findParent(S, N);
          if(P.right == S)
      {
              P.right = S.right;
      }
          else
      {
              P.left = S.right;
      }
          GlobalMembersDictionary.freeNode(S);
       }
       D.numPairs--;
    }

// makeEmpty()
// re-sets D to the empty state.
// pre: none

    // makeEmpty()
    // re-sets D to the empty state.
    // pre: none
    public static void makeEmpty(DictionaryObj D)
    {
       GlobalMembersDictionary.deleteAll(D.root);
       D.root = null;
       D.numPairs = 0;
    }

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out

    // printDictionary()
    // pre: none
    // prints a text representation of D to the file pointed to by out
    public static void printDictionary(FILE out, DictionaryObj D)
    {
       if(D == null)
       {
          fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL" + " Dictionary reference\n");
          System.exit(1);
       }
       GlobalMembersDictionary.printInOrder(out, D.root);
    }

    // Node

    // newNode()
    // constructor for private Node type
    public static NodeObj newNode(tangible.RefObject<String> k, tangible.RefObject<String> v)
    {
//C++ TO JAVA CONVERTER TODO TASK: The memory management function 'malloc' has no equivalent in Java:
//C++ TO JAVA CONVERTER TODO TASK: There is no Java equivalent to 'sizeof':
       NodeObj N = malloc(sizeof(NodeObj));
       assert N!=null;
       N.key = (new Character((char)k.argValue)).toString();
       N.value = (new Character((char)v.argValue)).toString();
       N.left = N.right = null;
       return (N);
    }

    // freeNode()
    // destructor for private Node type
    public static void freeNode(NodeObjpN[][] UnnamedParameter1)
    {
       if(pN!=null && *pN!=null)
       {
//C++ TO JAVA CONVERTER TODO TASK: The memory management function 'free' has no equivalent in Java:
          free(*pN);
          *pN = null;
       }
    }


    // findKey()
    // returns the Node containing key k in the subtree rooted at R, or returns
    // NULL if no such Node exists
    public static NodeObj findKey(NodeObj R, tangible.RefObject<String> k)
    {
       if(R == null || strcmp(k.argValue, R.key)==0)
   {
          return R;
   }
       if(strcmp(k.argValue, R.key)<0)
   {
   {
	  tangible.RefObject<String> tempRef_k = new tangible.RefObject<String>(k);
          return GlobalMembersDictionary.findKey(R.left, tempRef_k);
          k.argValue = tempRef_k.argValue;
       }
       else // strcmp(k, R->key)>0
   {
   {
	  tangible.RefObject<String> tempRef_k2 = new tangible.RefObject<String>(k);
          return GlobalMembersDictionary.findKey(R.right, tempRef_k2);
          k.argValue = tempRef_k2.argValue;
       }
    }

    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns NULL 
    // if N is equal to R. (pre: R != NULL)
    public static NodeObj findParent(NodeObj N, NodeObj R)
    {
       NodeObj P = null;
       if(N!=R)
       {
          P = R;
          while(P.left!=N && P.right!=N)
          {
             if(strcmp(N.key, P.key)<0)
         {
                P = P.left;
         }
             else
         {
                P = P.right;
         }
          }
       }
       return P;
    }

    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
    public static NodeObj findLeftmost(NodeObj R)
    {
       NodeObj L = R;
       if(L!=null)
   {
           for(; L.left!=null; L = L.left)
       {
               ;
       }
   }
       return L;
    }

    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R in order
    // of increasing keys to file pointed to by out.
    public static void printInOrder(FILE out, NodeObj R)
    {
       if(R!=null)
       {
          GlobalMembersDictionary.printInOrder(out, R.left);
          fprintf(out, "%s %s\n", R.key, R.value);
          GlobalMembersDictionary.printInOrder(out, R.right);
       }
    }

    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    public static void deleteAll(NodeObj N)
    {
       if(N!=null)
       {
          GlobalMembersDictionary.deleteAll(N.left);
          GlobalMembersDictionary.deleteAll(N.right);
          GlobalMembersDictionary.freeNode(N);
       }
    }
}

// private types and functions ------------------------------------------------

// NodeObj
public class NodeObj
{
   public String key;
   public String value;
   public NodeObj left;
   public NodeObj right;
}

// DictionaryObj
public class DictionaryObj
{
   public NodeObj root;
   public int numPairs;
}

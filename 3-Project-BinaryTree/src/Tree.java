public class Tree {
    private Node root;

    //INSERTS NEW NODES INTO THE TREE
    public void inserto(Node newVal) {
        //Checks if the tree is empty
        if (root == null) {
            root = newVal;
            root.setLeftThread(false);
            root.setRightThread(false);
        } else {
            Node focus = root;
            Node parent;
            while(true) {
                parent = focus;
                if(focus.getValue().compareTo(newVal.getValue()) > 0) {
                    //If the new value is less than the parent
                    focus = focus.getLeftChild();
                    if (focus == null) {
                        parent.setLeftChild(newVal);
                        parent.setLeftThread(false);
                        newVal.setParent(parent);
                        newVal.setSuccessor(inOrderSuccessor(newVal));
                        newVal.setPredecessor(inOrderPredecessor(newVal));
                        return;
                    }
                } else {
                    //If the new value is greater than or equal to the parent
                    focus = focus.getRightChild();
                    if (focus == null) {
                        parent.setRightChild(newVal);
                        parent.setRightThread(false);
                        newVal.setParent(parent);
                        newVal.setSuccessor(inOrderSuccessor(newVal));
                        newVal.setPredecessor(inOrderPredecessor(newVal));
                        return;
                    }
                }
            }
        }
    }

    //FINDS THE SUCCESSOR OF THE GIVEN NODE
    public Node inOrderSuccessor(Node n) {
        if (n.getRightChild() != null) {
            return minVal(n.getRightChild());
        }
        Node p = n.getParent();
        while (p != null && n == p.getRightChild()) {
            n = p;
            p = p.getParent();
        }
        return p;
    }

   //FINDS THE PREDECESSOR OF THE GIVEN NODE
    public Node inOrderPredecessor(Node n) {
        if(n.getLeftChild() != null) {
            return maxVal(n.getLeftChild());
        }
        Node p = n.getParent();
        while (p != null && n == p.getLeftChild()) {
            n = p;
            p = p.getParent();
        }
        return p;
    }

    //CHECKS FOR DUPLICATES
    public boolean duplicate(String key) {
        Node focus = root;
        if(root == null) {
            return false;
        }
        while (focus.getValue().compareTo(key) != 0) {
            if (focus.getValue().compareTo(key) > 0) {
                focus = focus.getLeftChild();
            } else {
                focus = focus.getRightChild();
            }
            if(focus == null) {
                return false;
            }
        }
        return true;
    }

    //FINDS THE ENTERED KEY
    public int find(String key) {
        int output = -1;
        Node focus = root;
        while(focus != null) {
            if(key.compareTo(focus.getValue()) == 0) {
                output = focus.getPosition();
                break;
            }
            if (focus.getValue().compareTo(key) > 0) {
                focus = focus.getLeftChild();
            } else {
                focus = focus.getRightChild();
            }
        }

        return output;
    }

    //RETURNS THE LOWEST VALUE OF TREE
    public Node miniMe() {
        return minVal(root);
    }

    //RETURNS THE LARGEST VALUE OF TREE
    public Node maxMe() {
        return maxVal(root);
    }

    //MINIMUM VALUE OF THE RIGHT TREE
    public Node minVal(Node n) {
        Node focus = n;
        while (focus.getLeftChild() != null) {
            focus = focus.getLeftChild();
        }
        return focus;
    }

    //MAXIMUM VALUE OF THE LEFT TREE
    public Node maxVal(Node n) {
        Node focus = n;
        while(focus != null && focus.getRightChild() != null) {
            focus = focus.getRightChild();
        }
        return focus;
    }

    //METHOD TO FEED ROOT INTO DELETION
    public void dede(String key) {
        delete(key,root);
    }

    //MAIN DELETE METHOD
    public void delete(String key, Node start) {
        Node focus = start;
        Node parent = null;
        boolean isLeft = false;
        while (focus != null) {
            if(key.compareTo(focus.getValue()) == 0) {
                if (parent == null) {
                    break;
                } else if (parent.getValue().compareTo(focus.getValue()) > 0) {
                    isLeft = true;
                    break;
                }
                break;
            } else if (focus.getValue().compareTo(key) > 0) {
                parent = focus;
                focus = focus.getLeftChild();
            } else {
                parent = focus;
                focus = focus.getRightChild();
            }
        }
        if(focus == null) {
            //ERROR CHECK
            System.out.println("Error Bro.");
        } else if((focus.getLeftChild() != null)&&(focus.getRightChild() == null)) {
            //Delete a node with a left child
            leftChildDelete(parent,focus,isLeft);
        } else if((focus.getLeftChild() == null)&&(focus.getRightChild() != null)) {
            //Delete a node with a right child
            rightChildDelete(parent,focus,isLeft);
        } else if(focus.getLeftChild() != null && focus.getRightChild() != null) {
            //Delete a node with two children
            twoChildDelete(parent,focus,isLeft);
        } else {
            //Delete a node with no children
            noChildDelete(parent,focus,isLeft);
        }

    }

    //METHOD FOR DELETING A NODE WITH NO CHILDREN
    public void noChildDelete(Node parentN, Node focus, boolean isleft) {
        if(isleft) {
            //Nodes on the left side of parent
            parentN.setLeftChild(null);
            parentN.setPredecessor(focus.getPredecessor());
            parentN.setLeftThread(true);
        } else {
            //Nodes on the right side of parent
            parentN.setRightChild(null);
            parentN.setSuccessor(focus.getSuccessor());
            parentN.setRightThread(true);
        }
    }

    //METHOD FOR DELETING A NODE WITH ONE CHILD ON THE LEFT
    public void leftChildDelete(Node parentN, Node focus, boolean isLeft) {
        if(isLeft) {
            //Nodes on the left side of parent
            parentN.setLeftChild(focus.getLeftChild());
        } else {
            //Nodes on the right side of parent
            parentN.setRightChild(focus.getLeftChild());
        }
        Node temp = maxVal(focus.getLeftChild());
        temp.setSuccessor(focus.getSuccessor());
    }

    //METHOD FOR DELETING A NODE WITH ONE CHILD ON THE RIGHT
    public void rightChildDelete(Node parentN, Node focus, boolean isLeft) {
        if(isLeft) {
            //Nodes on the left side of parent
            parentN.setLeftChild(focus.getRightChild());
        } else {
            //Nodes on the right side of parent
            parentN.setRightChild(focus.getRightChild());
        }
        Node temp = minVal(focus.getRightChild());
        temp.setPredecessor(focus.getPredecessor());
    }

    //METHOD FOR DELETING A NODE WITH TWO CHILDREN
    public void twoChildDelete(Node parentN, Node focus, boolean isLeft) {
        Node temp = maxVal(focus.getLeftChild());
        String val = temp.getValue();
        int pos = temp.getPosition();
        //Recursion to delete original node that got copied
        delete(temp.getValue(),root);
        //Assigns the copied node values to the deleted node
        focus.setValue(val);
        focus.setPosition(pos);
    }
}
//END OF CLASS


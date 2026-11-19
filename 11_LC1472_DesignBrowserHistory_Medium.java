class BrowserHistory {
    class Node{
        Node prev;
        Node next;
        String string;
        public Node(String string){
            prev = null;
            next = null;
            this.string = string;
        }
    }
    Node head;
    Node current;
    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        current = head;
    }
    
    public void visit(String url) {
        Node node = new Node(url);
        current.next = node;
        node.prev = current;
        current = current.next;
    }
    
    public String back(int steps) {
        while(current.prev != null && steps > 0){
            current = current.prev;
            steps--;
        }
        return current.string;
    }
    
    public String forward(int steps) {
        while(current.next != null && steps > 0){
            current = current.next;
            steps--;
        }
        return current.string;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */

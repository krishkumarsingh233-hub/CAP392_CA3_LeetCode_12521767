import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextElement;
    private boolean hasNextElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        advance();
    }

    private void advance() {
        if (iterator.hasNext()) {
            nextElement = iterator.next();
            hasNextElement = true;
        } else {
            nextElement = null;
            hasNextElement = false;
        }
    }

    public Integer peek() {
        return nextElement;
    }

    @Override
    public Integer next() {
        Integer current = nextElement;
        advance();
        return current;
    }

    @Override
    public boolean hasNext() {
        return hasNextElement;
    }
}

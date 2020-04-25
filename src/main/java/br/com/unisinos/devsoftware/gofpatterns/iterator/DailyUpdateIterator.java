package br.com.unisinos.devsoftware.gofpatterns.iterator;

import br.com.unisinos.devsoftware.gofpatterns.domain.DailyUpdate;

import java.util.Iterator;

public class DailyUpdateIterator implements Iterator {

    private DailyUpdate[] updates;
    int pos;

    public DailyUpdateIterator(DailyUpdate[] updates) {
        this.updates = updates;
    }

    @Override
    public boolean hasNext() {
        return pos != updates.length;
    }

    @Override
    public DailyUpdate next() {
        return updates[pos++];
    }
}

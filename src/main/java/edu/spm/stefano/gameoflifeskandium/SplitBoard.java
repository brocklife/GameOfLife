package edu.spm.stefano.gameoflifeskandium;

import cl.niclabs.skandium.muscles.Split;
import edu.spm.stefano.gameoflife.Board;

class SplitBoard implements Split<Board, Interval> {

    int numParts;

    public SplitBoard(int availableProcessors) {
        numParts = availableProcessors;
    }

    @Override
    public Interval[] split(Board param) throws Exception {
        Interval[] result = new Interval[numParts];
        int m = param.getHeight();
        int step = m / numParts;
        int extra = m % numParts;

        int start = 0, stop = 0, chunk = 0;

        for (int j = 0; j < numParts; j++) {
            start = start + chunk;
            chunk = step + (extra-- > 0 ? 1 : 0);
            result[j] = new Interval(start, chunk);

        }
        return result;
    }
}

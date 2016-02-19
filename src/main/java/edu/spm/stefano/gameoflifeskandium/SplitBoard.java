package edu.spm.stefano.gameoflifeskandium;

import cl.niclabs.skandium.muscles.Split;
import edu.spm.stefano.gameoflife.Board;
import edu.spm.stefano.gameoflife.Interval;

class SplitBoard implements Split<Board, Interval> {

    int numParts;

    public SplitBoard(int availableProcessors) {
        numParts = availableProcessors;
    }

    @Override
    public Interval[] split(Board param) throws Exception {
        Interval[] result;
        
        result = param.splitBoard(numParts);
        
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import cl.niclabs.skandium.muscles.Split;
import gameoflife.Board;

/**
 *
 * @author stefano
 */
class SplitBoard implements Split<Board, Interval> {
    int numParts;

    public SplitBoard(int availableProcessors) {
        numParts = availableProcessors;
    }

    @Override
    public Interval[] split(Board param) throws Exception{ 
        Interval[] result = new Interval[numParts];
        int m = param.getHeight();
        int step = m/numParts;
        
        for (int j = 0; j < numParts; j++) {
            if (j < numParts - 1) {
                result[j] = new Interval(j*step, step); 
            } else 
                result[j] = new Interval(j*step, m-(j*step)); 
            }
        return result;
    }

}

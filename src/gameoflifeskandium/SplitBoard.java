/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifeskandium;

import cl.niclabs.skandium.muscles.Split;

/**
 *
 * @author stefano
 */
class SplitBoard implements Split<Board, IntervalBoard> {
    
    int numParts;

    public SplitBoard(int availableProcessors) {
        numParts = availableProcessors;
    }

    @Override
    public IntervalBoard[] split(Board param) throws Exception{ 
        IntervalBoard[] result = new IntervalBoard[numParts];
        int m = param.getHeight();
        int step = m/numParts;
        
        for (int j = 0; j < numParts; j++) {
            if (j < numParts - 1) {
                result[j] = new IntervalBoard(j*step, step, param); 
            } else 
                result[j] = new IntervalBoard(j*step, m-(j*step), param); 
            }
        return result;
    }

}

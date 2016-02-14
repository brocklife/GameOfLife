/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifeCLI;

import edu.spm.stefano.gameoflife.GameOfLife;
import edu.spm.stefano.gameoflifemultithreaded.GameOfLifeMultiThreaded;
import edu.spm.stefano.gameoflifemuskel.GameOfLifeMuskel2;
import edu.spm.stefano.gameoflifeskandium.GameOfLifeSkandium;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author stefano
 */
public class GameOfLifeCLI {

    /**
     * @param args the command line arguments
     * @throws java.util.concurrent.BrokenBarrierException
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, ExecutionException {
        Options options = new Options();

        int m = 1000, n = 1000, t = 1000;
        boolean graphicBoard = false;
        boolean gliderConfig = false;
        String implementation = "seq";
        int NTHREADS = Runtime.getRuntime().availableProcessors();

        //number of rows
        Option rows = new Option("m", true, "number of rows for the GoL board");
        //number of columns
        Option columns = new Option("n", true, "number of columns for the GoL board");
        //times
        Option times = new Option("t", true, "number of cycles for GoL");
        //graphics
        Option graphics = new Option("g", false, "activate the graphics visualisation");
        //glider
        Option glider = new Option("glider", false, "use the \"glider\" initialisation");
        //framework
        Option framework = new Option("f", true, "select the GoL version to be run");
        //NTHREADS
        Option threads = new Option("N", true, "number of threads for parallel implementations");
        options
                .addOption(rows)
                .addOption(columns)
                .addOption(times)
                .addOption(graphics)
                .addOption(glider)
                .addOption(framework)
                .addOption(threads);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("m")) {
                m = Integer.parseInt(cmd.getOptionValue("m"));
            }
            if (cmd.hasOption("n")) {
                n = Integer.parseInt(cmd.getOptionValue("n"));
            }
            if (cmd.hasOption("t")) {
                t = Integer.parseInt(cmd.getOptionValue("t"));
            }
            if (cmd.hasOption("g")) {
                graphicBoard = true;
            }
            if (cmd.hasOption("glider")) {
                gliderConfig = true;
            }
            if(cmd.hasOption("N")){
                NTHREADS = Integer.parseInt(cmd.getOptionValue("N"));
            }
            if (cmd.hasOption("f")) {
                String s = cmd.getOptionValue("f");
                switch (s) {
                    case "seq":
                        implementation = "seq";
                        break;
                    case "mt":
                        implementation = "mt";
                        break;
                    case "sk":
                        implementation = "sk";
                        break;
                    case "mu2":
                        implementation = "mu2";
                        break;
                    default:
                        System.err.println("Wrong implementation code: seq, mt, sk, mu2 are allowed only.");
                        System.exit(1);
                        break;
                }
            }
        } catch (ParseException ex) {
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
        }

        String[] args2 = {
            Integer.toString(m), 
            Integer.toString(n), 
            Integer.toString(t), 
            Boolean.toString(graphicBoard), 
            Boolean.toString(gliderConfig), 
            Integer.toString(NTHREADS)
        };
        
        System.out.println(Arrays.toString(args2));
        
        switch (implementation) {
            case "seq":
                GameOfLife.main(args2);
                break;
            case "mt":
                GameOfLifeMultiThreaded.main(args2);
                break;
            case "sk":
                GameOfLifeSkandium.main(args2);
                break;
            case "mu2":
                GameOfLifeMuskel2.main(args2);
                break;
            default:
                System.err.println("Wrong implementation code: seq, mt, sk, mu2 are allowed only.");
                break;
        }
        
        System.exit(0);

    }

}

package nfm.open;

/* StageMaker - Decompiled by JODE extended
 * DragShot Software
 * JODE (c) 1998-2001 Jochen Hoenicke
 */
import nfm.open.music.RadicalMod;

import java.applet.Applet;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/* StageMaker - Decompiled by JODE extended
 * DragShot Software
 * JODE (c) 1998-2001 Jochen Hoenicke
 */
import nfm.open.music.RadicalMod;
import nfm.open.util.FileUtil;

public class StageMaker extends Applet implements Runnable {
    {new CheckPoints();
    new Trackers();}
    //////////////// ------------ REMEMBER NEW PARTS ARE ALWAYS -10 INDEXES SINCE YOU CAN'T SELECT CARS IN THE STAGEMAKER
    //////////////// ------------ REMEMBER ALL PARTS INDEX 67 AND UP ARE CUSTOM PARTS
    //////////////// ------------ IF THEY'RE NOT IN THE TREES MENU THEY SHOULD BE COMMENTED OUT
    /**
     *
     */
    private static final long serialVersionUID = 2444709970063151411L;
    /**
     * originally 65 ------ INCREMENT THIS WHEN YOU WANT A NEW PART
     */
    private final int maxpart = 65;
    /**
     * just here to avoid crashes
     */
    private final int bumppart = maxpart + 1;

    private final static byte PART_ROADS = 0;
    private final static byte PART_RAMPS = 1;
    private final static byte PART_OBSTACLES = 2;
    private final static byte PART_CHECKPOINTS = 3;
    private final static byte PART_FIXHOOPS = 4;
    private final static byte PART_TREES = 5;
    private final static byte PART_BUMP = 6;
    private final static byte PART_CUSTOM = 7;

    /**
     * leave at false unless you really know what you're doing!!!
     */
    private final boolean floats = false;

    /*if (sptyp == 0) // PART ROADS
    	partroads();
    if (sptyp == 1) // PART RAMPS
    	partramps();
    if (sptyp == 2) // PART OBSTACLES
    	partobst();
    if (sptyp == 5) // PART TREES
    	partrees();*/

    private final String[][] addeda = new String[20][5000];
    private int adrot = 0;
    private int apx = 0;
    private int apy = 0;
    private int arrcnt = 0;
    private boolean arrng = false;

    /*
     * "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn",
    			"nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30",
    			"cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "sofframp",
    			"halfpipe", "spikes", "rail", "thewall", "checkpoint", "fixpoint", "offcheckpoint",
     * */

    /**
     * ----------------- ATTACH POINTS <br>
     * ------------------ INCREMENT THIS WHEN YOU WANT A NEW PART<br>
     * Attach points are: x1, z1, x2, z2<br>
     */
    private final int[][] atp = {
            {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    1520, 2830, -1520, -2830
            }, {
                    -1520, 2830, 1520, -2830
            }, {
                    0, -1750, 1750, 0
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, -1750, 1750, 0
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, -1750, 1750, 0
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 560, 0, -560
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    385, 980, 385, -980
            }, {
                    0, 0, 0, -600
            }, {
                    0, 0, 0, 0
            }, {
                    0, 2164, 0, -2164
            }, {
                    0, 2164, 0, -2164
            }, {
                    0, 3309, 0, -1680
            }, {
                    0, 1680, 0, -3309
            }, {
                    350, 0, -350, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    1810, 980, 1810, -980
            }, {
                    0, 0, 0, 0
            }, {
                    0, 500, 0, -500
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 1680, 0, -3309
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    0, 2800, 0, -2800
            }, {
                    700, 1400, 700, -1400
            }, {
                    0, -1480, 0, -1480
            }, {
                    0, 0, 0, 0
            }, {
                    350, 0, -350, 0
            }, {
                    0, 0, 0, 0
            }, {
                    700, 0, -700, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, -2198, 0, 1482
            }, {
                    0, -1319, 0, 1391
            }, {
                    0, -1894, 0, 2271
            }, {
                    0, -826, 0, 839
            }, {
                    0, -1400, 0, 1400
            }, {
                    0, -1400, 0, 1400
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }, {
                    0, 0, 0, 0
            }
    };
    private int avon = 0;
    private final ContO[] bco = new ContO[maxpart + 5];
    private String bstage = "\r\nmaxr(11,28500,-5600)\r\nmaxb(9,-8000,-12300)\r\nmaxl(11,-14700,-5600)\r\nmaxt(9,44800,-12300)\r\n";
    private final Image[] btgame = new Image[2];
    // Removed unused code found by UCDetector
    // 	int btn = 0;
    // Removed unused code found by UCDetector
    // 	int[] bw = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    // Removed unused code found by UCDetector
    // 	int[] bx = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    // Removed unused code found by UCDetector
    // 	int[] by = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private final int[] cfade = {
            255, 220, 220
    };
    private final int[] cgrnd = {
            205, 200, 200
    };
    private int chi = -1;
    private final int[] cldd = {
            210, 210, 210, 1, -1000
    };
    private int cntout = 0;
    private final ContO[] co = new ContO[10000];
    private final int[] csky = {
            170, 220, 255
    };

    /**
     * ------------------ PART DESCRIPTIONS, INCREMENT WHEN YOU WANT A NEW PART<br>
     * ------------------ OR THE GAME WILL CRASH WHEN YOU READ THEM
     */
    private final String[] discp = {
            "NormalRoad :  Basic asphalt road.\nAttaches correctly to the following other parts :\n\n'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',  'NormalRoad Edged',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "NormalRoad Edged :  Asphalt road with edged side blocks (a destructive road).\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "NormalRoad TwistedRight :  Asphalt road twisted towards the right.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad Twistedleft',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "NormalRoad TwistedLeft :  Asphalt road twisted towards the left.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "NormalRoad Turn :  Asphalt corner road turn.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp', 'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "OffRoad :  Basic sandy dirt-road.\nAttaches correctly to the following other parts :\n\n'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
            "OffRoad BumpyGreen :  Dirt-road with bumpy greenery in the middle.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
            "OffRoad Turn :  Dirt-road corner turn.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad End',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand 'Normal-Off-Road Blend'\n\n",
            "HalfpipeRoad :  Basic road for the half-pipe ramp.\nAttaches correctly to the following other parts :\n\n'Off-Halfpipe-Road Blend',  'HalfpipeRoad',  'HalfpipeRoad Turn',  'HalfpipeRoad-Ramp Filler'\nand  'Halfpipe-Normal-Road Blend'\n\n",
            "HalfpipeRoad Turn :  Half-pipe corner road turn.\nAttaches correctly to the following other parts :\n\n'HalfpipeRoad',  'Off-Halfpipe-Road Blend',  'HalfpipeRoad'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "Normal-Off-Road Blend :  Road blend between the normal asphalt road and the dirt-road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'NormalRoad-Raised Ramp', 'Halfpipe-Normal-Road Blend' 'OffRoad',  'OffRoad Turn',\n'OffRoad End',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start'  and  'Off-Halfpipe-Road Blend'\n\n",
            "Off-Halfpipe-Road Blend :  Road blend between the dirt-road and the half-pipe road.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start',\n'HalfpipeRoad',  'HalfpipeRoad Turn',  'Halfpipe-Normal-Road Blend'  and  'Normal-Off-Road Blend'\n\n",
            "Halfpipe-Normal-Road Blend :  Road blend between the normal asphalt road and the half-pipe road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'NormalRoad-Raised Ramp',  'HalfpipeRoad',  'Off-Halfpipe-Road Blend',  'HalfpipeRoad'\nand  'Off-Halfpipe-Road Blend'\n\n",
            "NormalRoad End :  The end part of the normal asphalt road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad Edged',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
            "OffRoad End :  The end part of the dirt-road.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
            "HalfpipeRoad-Ramp Filler :  A part that gets placed between the half-pipe road and the half-pipe ramp to extend the distance in between.\nAttaches correctly to the following other parts :\n\n'HalfpipeRoad'  and  'Halfpipe'\n\n",
            "Basic Ramp :  Basic 30 degree asphalt ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Crash Ramp :  A 35 degree ramp with big side blocks for crashing into.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Two-Way Ramp :  Two way 15 degree inclined ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Two-Way High-Low Ramp :  Two way 15 degree inclined ramp, with peeked side for an optional higher car jump.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Landing Ramp :  A ramp that is both a landing inclination and an obstacle as well, it is usually placed just after another normal ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
            "Big-Takeoff Ramp:  A big takeoff ramp for getting huge heights with the cars.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
            "Small Ramp :  A small ramp that can be placed on either side of the road.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
            "Offroad Bump Ramp :  A small bump ramp that is to be placed over the off-road dirt tracks.\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
            "Offroad Big Ramp :  The big off-road dirt mountain like ramp!\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
            "Offroad Ramp :  Normal sized off-road dirt track ramp!\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
            "Halfpipe :  The Half-pipe ramp, two of these ramps opposite each other create a half-pipe for the cars!\nAttaches correctly over and to the following other parts :\n\n'HalfpipeRoad',  'HalfpipeRoad Turn'  and  'HalfpipeRoad-Ramp Filler'\n\n",
            "Spiky Pillars :  An obstacle that is usually placed after a ramp for the cars to crash onto if they did not jump high or far enough!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Rail Doorway :  A rail doorway that works as an obstacle for cars flying above it or cars driving through it!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "The Wall",
            "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
            "Fixing Hoop :  The fixing hoop that fixes a car when it flies through it! You can add a max of 5 fixing hoops per stage.\nPlace it anywhere in the stage at an height your choose, the only important thing is that it needs to be reachable by the cars.",
            "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
            "OffRoad BumpySides :  Off-road dirt track with bumpy sandbar sides.\nAttaches correctly to the following other parts :\n\n'OffRoad-BumpySides Start'\n\n",
            "OffRoad-BumpySides Start: The start of the off-road dirt track with bumpy sandbar sides.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides',\n'Off-Halfpipe-Road Blend'  and  'Normal-Off-Road Blend'\n\n",
            "NormalRoad-Raised Ramp:  The start of the raised above the ground road (NormalRoad Raised).\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight'  and  'NormalRoad Raised'\n\n",
            "NormalRoad Raised :  Normal road raised above the ground, cars must avoid falling off it when driving on it.\nAttaches correctly to the following other parts :\n\n'NormalRoad-Raised Ramp'\n\n",
            "The Start1", "The Start2",
            "Tunnel Side Ramp:  A ramp that can be used to create a tunnel like road with an open top or can be used as a wall ramp!\nAttaches correctly over only the 'NormalRoad' part.",
            "Launch Pad Ramp:  A ramp that launches your car fully upwards like a rocket, it also has sides to lock any car climbing it!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "The Net:  An obstacle part that is to be placed in the center of the road right after a ramp, the idea is that the\ncars jumping the ramp should try to go over it or through it without getting caught crashing (without getting\ncaught in it, getting caught in the net!).\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Speed Ramp:  A ramp that is designed to have the perfect angle to catapult your car the furthest when doing forward loops, it is half the roads width.\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Offroad Hill Ramp:  An offroad hill ramp that has two different inclines from the front and back to jump.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
            "Bump Slide:  A small bump obstacle that is to be placed on the sides of the road or in the center.\nAttaches correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
            "Offroad Big Hill Ramp:  An offroad big hill ramp that has two different inclines from the front and back to jump.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
            "Rollercoaster Start/End:  The ramp that starts the Rollercoaster Road and ends it.\nAttaches correctly over and to following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n 'NormalRoad TwistedRight'  and  'Rollercoaster Start/End'\n\n",
            "Rollercoaster Road1\nAttaches correctly to only 'Rollercoaster Start/End', 'Rollercoaster Road2' and itself.\n\n",
            "Rollercoaster Road3\nAttaches correctly to only 'Rollercoaster Road2', 'Rollercoaster Road4' and itself.\n\n",
            "Rollercoaster Road4\nAttaches correctly to only 'Rollercoaster Road3', 'Rollercoaster Road5' and itself.\n\n",
            "Rollercoaster Road2\nAttaches correctly to only 'Rollercoaster Road1', 'Rollercoaster Road3' and itself.\n\n",
            "Rollercoaster Road5\nAttaches correctly to only 'Rollercoaster Road4' and itself.\n\n",
            "Offroad Dirt-Pile:  A dirt pile obstacle that is to be placed anywhere in the middle of the road.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start'  and  'OffRoad-BumpySides'\n\n",
            "Offroad Dirt-Pile:  A dirt pile obstacle that is to be placed anywhere in the middle of the road.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start'  and  'OffRoad-BumpySides'\n\n",
            "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
            "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",

            "Ground Piles are to be paced outside the race track on the ground and NEVER on any road part or ramp!\nThey are to be used as ground decoration and out of race course obstacles (ground obstacles)!\n\n"
    };
    private boolean down = false;
    private int dtab = 0;
    private int dtabed = -1;
    private boolean epart = false;
    private int errd = 0;
    private final String[] errlo = {
            "The maximum allocated memory for the stage's part's details has been exerted.\nPlease decrease the amount of parts in the stage that have more details then average.",
            "The maximum amount of road points allowed in the track has been exceeded.\nPlease remove some of the road parts that are in the circler path of the track (the parts that are between the checkpoints).\nOr try to remove some of the extra checkpoints in the track as well.",
            "The maximum allowed area for a track (the area in between its walls) has been exceeded.\nPlease try to place parts only inside the current allowed area, inside the area between the current maximum wall placements.",
            "The maximum number of parts allowed per stage has been exceeded.\nPlease remove some of the already extra parts placed in order to make space.",
            "The maximum number of Fixing Hoops allowed per stage is 5!\nPlease remove the extra Fixing Hoops from your stage to have only 5 main ones left.",
            "Unknown Error, please make sure the stage you are handling is saved correctly.\nPlease go to the 'Build' tab and press 'Save & Preview'.",
            "There needs to be at least 2 checkpoints in the Stage in order for the game to work.\nPlease go to the 'Build' tab and select 'Checkpoint' in the Part Selection menu to add more cp.",
            "The name of the stage is too long!\nPlease go to the 'Stage' tab, click 'Rename Stage' and give your stage a shorter name."
    };
    private int esp = -1;
    private boolean exwist = false;
    private int fgen = 0;
    private final TextField fixh = new TextField("2000", 5);
    private int flyh = 0;
    private boolean focuson = true;
    private final int[] fogn = {
            60, 0
    };
    private FontMetrics ftm;
    private int hf = 2000;
    private int hi = -1;
    private final float[][] hsb = {
            {
                    0.5F, 0.875F, 0.5F
            }, {
                    0.5F, 0.875F, 0.5F
            }, {
                    0.5F, 0.875F, 0.5F
            }
    };
    private boolean left = false;
    private int logged = 0;
    private Image logo;
    private int lsp = -1;
    private String ltrackname = "";
    private int lxm = 0;
    // Removed unused code found by UCDetector
    // 	private int lym = 0;
    {new Medium();}
    private final String[] maker = new String[20];
    private final TextField mgen = new TextField("", 10);
    private boolean mousdr = false;
    private int mouseon = -1;
    private int mousePressed = 0;
    private final String[] mystages = new String[20];
    private final int[] nad = new int[20];
    private final Smenu nlaps = new Smenu(40);
    private int nms = 0;
    private int nob = 0;
    private int nundo = 0;
    private final int[] ocheckp = {
            5, 6, 7, 11, 14, 33, 34, 38
    };
    private Image offImage;
    private boolean onbtgame = false;
    // Removed unused code found by UCDetector
    private boolean onfly = false;
    private boolean onoff = false;
    private int origfade = 5000;
    private boolean overcan = false;
    private final Smenu part = new Smenu(500);
    // Removed unused code found by UCDetector
    // 	boolean[] pessd = { false, false, false, false, false, false, false, false, false, false, false, false, false,
    // 			false, false, false, false, false, false, false, false, false, false, false };
    private final Checkbox pfog = new Checkbox("Linked Blend");
    private boolean pgen = false;
    private float phd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
    private boolean preop = false;
    private final Smenu ptyp = new Smenu(40);
    private final int[] pubt = new int[20];
    private final Smenu pubtyp = new Smenu(40);
    private float pwd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
    private final int[] rcheckp = {
            0, 1, 2, 3, 4, 12, 13, 37
    };
    private Graphics2D rd;
    private boolean right = false;
    private int rot = 0;
    private final Image[] sd = new Image[2];
    private int seq = 0;
    private boolean seqn = false;
    private boolean setcur = false;
    private int sfase = 0;
    private final Image[] sl = new Image[2];
    private final Smenu slstage = new Smenu(2000);
    private final int[] snap = {
            50, 50, 50
    };
    private int selectedPart = 0;
    private int selectedMenuPart = 0;
    private int selectedPartType = 0;
    private final Image[] sr = new Image[2];
    private final TextField srch = new TextField("", 38);
    private String sstage = "";
    private String stagename = "";
    private final Smenu strtyp = new Smenu(40);
    private final Image[] su = new Image[2];
    private String suser = "Horaks";
    private int sx = 0;
    private int sy = -10000;
    private int sz = 1500;
    private int tab = 0;
    private int tabed = -1;
    private final int[] texture = {
            0, 0, 0, 10
    };
    private Thread thredo;
    private final TextField tnick = new TextField("", 15);
    private final TextField tpass = new TextField("", 15);
    private RadicalMod track = new RadicalMod();
    private String trackname = "";
    private final Smenu tracks = new Smenu(2000);
    private int tracksize = 111;
    private int trackvol = 200;
    private String tstage = "snap(0,0,0)\r\nsky(191,215,255)\r\nclouds(255,255,255,5,-1000)\r\nfog(195,207,230)\r\nground(192,194,202)\r\ntexture(0,0,0,50)\r\nfadefrom(5000)\r\ndensity(5)\n\rmountains(" + (int) (ThreadLocalRandom.current().nextDouble() * 100000.0) + ")\r\nnlaps(5)\r\n\r\n";
    private String ttstage = "";
    private final String[] undos = new String[5000];
    private boolean up = false;
    private int vx = 0;
    private int vxz = 0;
    private int vy = 0;
    private int vz = 0;
    private final Smenu witho = new Smenu(40);
    private int xm = 0;
    private int xnob = 0;
    private int ym = 0;
    private final Image[] zi = new Image[2];
    private final Image[] zo = new Image[2];
    private boolean zoomi = false;
    private boolean zoomo = false;

    private boolean button(final String string, final int i, final int i381, final int i382, final boolean bool) {
        rd.setFont(new Font("Arial", 1, 12));
        ftm = rd.getFontMetrics();
        final int i383 = ftm.stringWidth(string);
        boolean bool384;
        boolean bool385 = false;
        if (string.equals(" Cancel ") && epart && Math.abs(xm - i) < i383 / 2 + 12 && Math.abs(ym - i381 + 5) < 10) {
            overcan = true;
        }
        bool384 = Math.abs(xm - i) < i383 / 2 + 12 && Math.abs(ym - i381 + 5) < 10 && mousePressed == 1;
        if (Math.abs(xm - i) < i383 / 2 + 12 && Math.abs(ym - i381 + 5) < 10 && mousePressed == -1) {
            mousePressed = 0;
            bool385 = true;
        }
        boolean bool386 = false;
        if (bool) {
            if (tab == 0) {
                rd.setColor(new Color(207, 207, 207));
            }
            if (tab == 1) {
                rd.setColor(new Color(200, 200, 200));
            }
            if (tab == 2) {
                rd.setColor(new Color(170, 170, 170));
            }
            if (tab != 3) {
                rd.drawRect(i - i383 / 2 - 15, i381 - (22 - i382), i383 + 29, 34 - i382 * 2);
                if (i382 == 2 && tab == 1) {
                    rd.setColor(new Color(220, 220, 220));
                    rd.fillRect(i - i383 / 2 - 15, i381 - (22 - i382), i383 + 29, 34 - i382 * 2);
                }
            } else {
                bool386 = true;
            }
        }
        if (!bool384) {
            rd.setColor(new Color(220, 220, 220));
            if (bool386) {
                rd.setColor(new Color(230, 230, 230));
            }
            rd.fillRect(i - i383 / 2 - 10, i381 - (17 - i382), i383 + 20, 25 - i382 * 2);
            rd.setColor(new Color(240, 240, 240));
            if (bool386) {
                rd.setColor(new Color(255, 255, 255));
            }
            rd.drawLine(i - i383 / 2 - 10, i381 - (17 - i382), i + i383 / 2 + 10, i381 - (17 - i382));
            rd.drawLine(i - i383 / 2 - 10, i381 - (18 - i382), i + i383 / 2 + 10, i381 - (18 - i382));
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i383 / 2 - 9, i381 - (19 - i382), i + i383 / 2 + 9, i381 - (19 - i382));
            rd.setColor(new Color(200, 200, 200));
            if (bool386) {
                rd.setColor(new Color(192, 192, 192));
            }
            rd.drawLine(i + i383 / 2 + 10, i381 - (17 - i382), i + i383 / 2 + 10, i381 + 7 - i382);
            rd.drawLine(i + i383 / 2 + 11, i381 - (17 - i382), i + i383 / 2 + 11, i381 + 7 - i382);
            rd.setColor(new Color(200, 200, 200));
            if (bool386) {
                rd.setColor(new Color(192, 192, 192));
            }
            rd.drawLine(i + i383 / 2 + 12, i381 - (16 - i382), i + i383 / 2 + 12, i381 + 6 - i382);
            rd.drawLine(i - i383 / 2 - 10, i381 + 7 - i382, i + i383 / 2 + 10, i381 + 7 - i382);
            rd.drawLine(i - i383 / 2 - 10, i381 + 8 - i382, i + i383 / 2 + 10, i381 + 8 - i382);
            rd.setColor(new Color(200, 200, 200));
            rd.drawLine(i - i383 / 2 - 9, i381 + 9 - i382, i + i383 / 2 + 9, i381 + 9 - i382);
            rd.setColor(new Color(240, 240, 240));
            if (bool386) {
                rd.setColor(new Color(255, 255, 255));
            }
            rd.drawLine(i - i383 / 2 - 10, i381 - (17 - i382), i - i383 / 2 - 10, i381 + 7 - i382);
            rd.drawLine(i - i383 / 2 - 11, i381 - (17 - i382), i - i383 / 2 - 11, i381 + 7 - i382);
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i383 / 2 - 12, i381 - (16 - i382), i - i383 / 2 - 12, i381 + 6 - i382);
            rd.setColor(new Color(0, 0, 0));
            if (string.equals("  Keyboard Controls  ")) {
                rd.setColor(new Color(100, 100, 100));
            }
            rd.drawString(string, i - i383 / 2, i381);
        } else {
            rd.setColor(new Color(220, 220, 220));
            rd.fillRect(i - i383 / 2 - 10, i381 - (17 - i382), i383 + 20, 25 - i382 * 2);
            rd.setColor(new Color(192, 192, 192));
            rd.drawLine(i - i383 / 2 - 10, i381 - (17 - i382), i + i383 / 2 + 10, i381 - (17 - i382));
            rd.drawLine(i - i383 / 2 - 10, i381 - (18 - i382), i + i383 / 2 + 10, i381 - (18 - i382));
            rd.drawLine(i - i383 / 2 - 9, i381 - (19 - i382), i + i383 / 2 + 9, i381 - (19 - i382));
            rd.setColor(new Color(247, 247, 247));
            rd.drawLine(i + i383 / 2 + 10, i381 - (17 - i382), i + i383 / 2 + 10, i381 + 7 - i382);
            rd.drawLine(i + i383 / 2 + 11, i381 - (17 - i382), i + i383 / 2 + 11, i381 + 7 - i382);
            rd.drawLine(i + i383 / 2 + 12, i381 - (16 - i382), i + i383 / 2 + 12, i381 + 6 - i382);
            rd.drawLine(i - i383 / 2 - 10, i381 + 7 - i382, i + i383 / 2 + 10, i381 + 7 - i382);
            rd.drawLine(i - i383 / 2 - 10, i381 + 8 - i382, i + i383 / 2 + 10, i381 + 8 - i382);
            rd.drawLine(i - i383 / 2 - 9, i381 + 9 - i382, i + i383 / 2 + 9, i381 + 9 - i382);
            rd.setColor(new Color(192, 192, 192));
            rd.drawLine(i - i383 / 2 - 10, i381 - (17 - i382), i - i383 / 2 - 10, i381 + 7 - i382);
            rd.drawLine(i - i383 / 2 - 11, i381 - (17 - i382), i - i383 / 2 - 11, i381 + 7 - i382);
            rd.drawLine(i - i383 / 2 - 12, i381 - (16 - i382), i - i383 / 2 - 12, i381 + 6 - i382);
            rd.setColor(new Color(0, 0, 0));
            if (string.equals("  Keyboard Controls  ")) {
                rd.setColor(new Color(100, 100, 100));
            }
            rd.drawString(string, i - i383 / 2 + 1, i381 + 1);
        }
        return bool385;
    }

    private void copyesp(final boolean bool) {
        selectedPart = co[esp].colok;
        rot = co[esp].roofat;
        if (selectedPart == 2) {
            rot -= 30;
        }
        if (selectedPart == 3) {
            rot += 30;
        }
        if (selectedPart == 15) {
            rot += 90;
        }
        if (selectedPart == 20) {
            rot += 180;
        }
        if (selectedPart == 26) {
            rot -= 90;
        }
        if (selectedPart == 0) {
            selectedPartType = 0;
            selectedMenuPart = 0;
        }
        if (selectedPart == 4) {
            selectedPartType = 0;
            selectedMenuPart = 1;
        }
        if (selectedPart == 13) {
            selectedPartType = 0;
            selectedMenuPart = 2;
        }
        if (selectedPart == 3) {
            selectedPartType = 0;
            selectedMenuPart = 3;
        }
        if (selectedPart == 2) {
            selectedPartType = 0;
            selectedMenuPart = 4;
        }
        if (selectedPart == 1) {
            selectedPartType = 0;
            selectedMenuPart = 5;
        }
        if (selectedPart == 35) {
            selectedPartType = 0;
            selectedMenuPart = 6;
        }
        if (selectedPart == 36) {
            selectedPartType = 0;
            selectedMenuPart = 7;
        }
        if (selectedPart == 10) {
            selectedPartType = 0;
            selectedMenuPart = 8;
        }
        if (selectedPart == 5) {
            selectedPartType = 0;
            selectedMenuPart = 9;
        }
        if (selectedPart == 7) {
            selectedPartType = 0;
            selectedMenuPart = 10;
        }
        if (selectedPart == 14) {
            selectedPartType = 0;
            selectedMenuPart = 11;
        }
        if (selectedPart == 6) {
            selectedPartType = 0;
            selectedMenuPart = 12;
        }
        if (selectedPart == 34) {
            selectedPartType = 0;
            selectedMenuPart = 13;
        }
        if (selectedPart == 33) {
            selectedPartType = 0;
            selectedMenuPart = 14;
        }
        if (selectedPart == 11) {
            selectedPartType = 0;
            selectedMenuPart = 15;
        }
        if (selectedPart == 8) {
            selectedPartType = 0;
            selectedMenuPart = 16;
        }
        if (selectedPart == 9) {
            selectedPartType = 0;
            selectedMenuPart = 17;
        }
        if (selectedPart == 15) {
            selectedPartType = 0;
            selectedMenuPart = 18;
        }
        if (selectedPart == 12) {
            selectedPartType = 0;
            selectedMenuPart = 19;
        }
        if (selectedPart == 46) {
            selectedPartType = 0;
            selectedMenuPart = 20;
        }
        if (selectedPart == 47) {
            selectedPartType = 0;
            selectedMenuPart = 21;
        }
        if (selectedPart == 48) {
            selectedPartType = 0;
            selectedMenuPart = 23;
        }
        if (selectedPart == 49) {
            selectedPartType = 0;
            selectedMenuPart = 24;
        }
        if (selectedPart == 50) {
            selectedPartType = 0;
            selectedMenuPart = 22;
        }
        if (selectedPart == 51) {
            selectedPartType = 0;
            selectedMenuPart = 25;
        }
        if (selectedPart == 16) {
            selectedPartType = 1;
            selectedMenuPart = 0;
        }
        if (selectedPart == 18) {
            selectedPartType = 1;
            selectedMenuPart = 1;
        }
        if (selectedPart == 19) {
            selectedPartType = 1;
            selectedMenuPart = 2;
        }
        if (selectedPart == 22) {
            selectedPartType = 1;
            selectedMenuPart = 3;
        }
        if (selectedPart == 17) {
            selectedPartType = 1;
            selectedMenuPart = 4;
        }
        if (selectedPart == 21) {
            selectedPartType = 1;
            selectedMenuPart = 5;
        }
        if (selectedPart == 20) {
            selectedPartType = 1;
            selectedMenuPart = 6;
        }
        if (selectedPart == 39) {
            selectedPartType = 1;
            selectedMenuPart = 7;
        }
        if (selectedPart == 42) {
            selectedPartType = 1;
            selectedMenuPart = 8;
        }
        if (selectedPart == 40) {
            selectedPartType = 1;
            selectedMenuPart = 9;
        }
        if (selectedPart == 23) {
            selectedPartType = 1;
            selectedMenuPart = 10;
        }
        if (selectedPart == 25) {
            selectedPartType = 1;
            selectedMenuPart = 11;
        }
        if (selectedPart == 24) {
            selectedPartType = 1;
            selectedMenuPart = 12;
        }
        if (selectedPart == 43) {
            selectedPartType = 1;
            selectedMenuPart = 13;
        }
        if (selectedPart == 45) {
            selectedPartType = 1;
            selectedMenuPart = 14;
        }
        if (selectedPart == 26) {
            selectedPartType = 1;
            selectedMenuPart = 15;
        }
        if (selectedPart == 27) {
            selectedPartType = 2;
            selectedMenuPart = 0;
        }
        if (selectedPart == 28) {
            selectedPartType = 2;
            selectedMenuPart = 1;
        }
        if (selectedPart == 41) {
            selectedPartType = 2;
            selectedMenuPart = 2;
        }
        if (selectedPart == 44) {
            selectedPartType = 2;
            selectedMenuPart = 3;
        }
        if (selectedPart == 52) {
            selectedPartType = 2;
            selectedMenuPart = 4;
        }
        if (selectedPart == 53) {
            selectedPartType = 2;
            selectedMenuPart = 5;
        }
        if (selectedPart == 30 || selectedPart == 32 || selectedPart == 54) {
            selectedPartType = 3;
            selectedMenuPart = 0;
        }
        if (selectedPart == 31) {
            selectedPartType = 4;
            selectedMenuPart = 0;
        }

        if (selectedPart == 55) {
            selectedPartType = 5;
            selectedMenuPart = 0;
        }
        if (selectedPart == 56) {
            selectedPartType = 5;
            selectedMenuPart = 1;
        }
        if (selectedPart == 57) {
            selectedPartType = 5;
            selectedMenuPart = 2;
        }
        if (selectedPart == 58) {
            selectedPartType = 5;
            selectedMenuPart = 3;
        }
        if (selectedPart == 59) {
            selectedPartType = 5;
            selectedMenuPart = 4;
        }
        if (selectedPart == 60) {
            selectedPartType = 5;
            selectedMenuPart = 5;
        }
        if (selectedPart == 61) {
            selectedPartType = 5;
            selectedMenuPart = 6;
        }
        if (selectedPart == 62) {
            selectedPartType = 5;
            selectedMenuPart = 7;
        }
        if (selectedPart == 63) {
            selectedPartType = 5;
            selectedMenuPart = 8;
        }
        if (selectedPart == 64) {
            selectedPartType = 5;
            selectedMenuPart = 9;
        }
        if (selectedPart == 65) {
            selectedPartType = 5;
            selectedMenuPart = 10;
        }
        if (selectedPart == 66) {
            selectedPartType = 5;
            selectedMenuPart = 11;
        }

        // ------------------ NEW PARTS

        if (selectedPart > 66) {
            selectedPartType = 7;
            selectedMenuPart = selectedPart - 55;
        }

        if (selectedPart == bumppart) {
            if (bool) {
                fgen = co[esp].srz;
            } else {
                fgen = 0;
            }
            pwd = co[esp].srx;
            phd = co[esp].sry;
            pgen = false;
            selectedPartType = 6;
        }
        if (selectedPartType == PART_ROADS) {
            partroads();
            part.setVisible(true);
        }
        if (selectedPartType == PART_RAMPS) {
            partramps();
            part.setVisible(true);
        }
        if (selectedPartType == PART_OBSTACLES) {
            partobst();
            part.setVisible(true);
        }
        if (selectedPartType == PART_TREES) {
            partrees();
            part.setVisible(true);
        }
        if (selectedPartType == PART_CUSTOM) {
            partcustom();
            part.setVisible(true);
        }
        ptyp.select(selectedPartType);
        part.select(selectedMenuPart);
    }

    private void delstage(final String string) {
        try {
            final File file = new File("mystages/" + string + ".txt");
            file.delete();
            slstage.remove(string);
            slstage.select(0);
        } catch (final Exception exception) {
            JOptionPane.showMessageDialog(null, "Unable to delete file! Error Deatials:\n" + exception, "Stage Maker", 1);
        }
    }

    private void deltrack() {
        try {
            final File file = new File("mystages/mymusic/" + tracks.getSelectedItem() + ".zip");
            file.delete();
            if (trackname.equals(tracks.getSelectedItem())) {
                trackname = "";
                sortop();
                savefile();
            }
            tracks.remove(tracks.getSelectedItem());
            tracks.select(0);
        } catch (final Exception exception) {
            JOptionPane.showMessageDialog(null, "Unable to delete file! Error Deatials:\n" + exception, "Stage Maker", 1);
        }
    }

    private void drawms() {
        boolean bool = false;
        if (pubtyp.draw(rd, xm, ym, mousdr, 550, false)) {
            bool = true;
        }
        if (slstage.draw(rd, xm, ym, mousdr, 550, false)) {
            bool = true;
        }
        if (strtyp.draw(rd, xm, ym, mousdr, 550, false)) {
            bool = true;
        }
        int i = 0;
        if (preop) {
            i = -1000;
        }
        if (part.draw(rd, xm, ym + i, mousdr && !preop, 550, false)) {
            bool = true;
        }
        if (ptyp.draw(rd, xm, ym, mousdr, 550, false)) {
            bool = true;
            preop = true;
        } else {
            preop = false;
        }
        if (nlaps.draw(rd, xm, ym, mousdr, 550, true)) {
            bool = true;
        }
        if (tracks.draw(rd, xm, ym, mousdr, 550, true)) {
            bool = true;
        }
        if (witho.draw(rd, xm, ym, mousdr, 550, true)) {
            bool = true;
        }
        if (bool) {
            mousePressed = 0;
        }
    }

    private void fixtext(final TextField textfield) {
        String string = textfield.getText();
        string = string.replace('\"', '#');
        final String string330 = "\\";
        String string331 = "";
        int i = 0;
        int i332 = -1;
        rd.setFont(new Font("Arial", 1, 12));
        ftm = rd.getFontMetrics();
        for (/**/; i < string.length(); i++) {
            final String string333 = "" + string.charAt(i);
            if (string333.equals("|") || string333.equals(",") || string333.equals("(") || string333.equals(")") || string333.equals("#") || string333.equals(string330) || string333.equals("!") || string333.equals("?") || string333.equals("~") || string333.equals(".") || string333.equals("@") || string333.equals("$") || string333.equals("%") || string333.equals("^") || string333.equals("&") || string333.equals("*") || string333.equals("+") || string333.equals("=") || string333.equals(">") || string333.equals("<") || string333.equals("/") || string333.equals(";") || string333.equals(":") || ftm.stringWidth(string331) > 274) {
                i332 = i;
            } else {
                string331 = "" + string331 + string333;
            }
        }
        if (i332 != -1) {
            textfield.setText(string331);
            textfield.select(i332, i332);
        }
    }

    private Image getImage(final String string) {
        final Image image = Toolkit.getDefaultToolkit().createImage(string);
        final MediaTracker mediatracker = new MediaTracker(this);
        mediatracker.addImage(image, 0);
        try {
            mediatracker.waitForID(0);
        } catch (final Exception ignored) {

        }
        return image;
    }

    private int getint(final String string, final String string354, final int i) {
        int i355 = 0;
        String string356 = "";
        for (int i357 = string.length() + 1; i357 < string354.length(); i357++) {
            final String string358 = "" + string354.charAt(i357);
            if (string358.equals(",") || string358.equals(")")) {
                i355++;
                i357++;
            }
            if (i355 == i) {
                string356 = "" + string356 + string354.charAt(i357);
            }
        }
        return Integer.parseInt(string356);
    }

    private String getstring(final String string, final String string349, final int i) {
        int i350 = 0;
        String string351 = "";
        for (int i352 = string.length() + 1; i352 < string349.length(); i352++) {
            final String string353 = "" + string349.charAt(i352);
            if (string353.equals(",") || string353.equals(")")) {
                i350++;
                i352++;
            }
            if (i350 == i) {
                string351 = "" + string351 + string349.charAt(i352);
            }
        }
        return string351;
    }

    private String getSvalue(final String string, final String string376, final int i) {
        String string377 = "";
        int i378 = 0;
        for (int i379 = string.length() + 1; i379 < string376.length() && i378 <= i; i379++) {
            final String string380 = "" + string376.charAt(i379);
            if (string380.equals(",") || string380.equals(")")) {
                i378++;
            } else if (i378 == i) {
                string377 = "" + string377 + string380;
            }
        }
        return string377;
    }

    private void hidefields() {
        pubtyp.setVisible(false);
        tpass.setVisible(false);
        tnick.setVisible(false);
        witho.setVisible(false);
        strtyp.setVisible(false);
        srch.setVisible(false);
        slstage.setVisible(false);
        tracks.setVisible(false);
        nlaps.setVisible(false);
        pfog.setVisible(false);
        fixh.setVisible(false);
        mgen.setVisible(false);
        ptyp.setVisible(false);
        part.setVisible(false);
    }

    @Override
    public void init() {
        setBackground(new Color(0, 0, 0));
        offImage = new BufferedImage(800, 580, BufferedImage.TYPE_INT_RGB);
        if (offImage != null) {
            rd = (Graphics2D) offImage.getGraphics();
        }
        rd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        setLayout(null);
        slstage.setFont();
        slstage.add(rd, "Select a Stage...         ");
        slstage.setForeground(new Color(63, 80, 110));
        slstage.setBackground(new Color(209, 217, 230));
        srch.setFont(new Font("Arial", 1, 12));
        srch.setBackground(new Color(255, 255, 255));
        srch.setForeground(new Color(0, 0, 0));
        strtyp.setFont();
        strtyp.add(rd, "NormalRoad");
        strtyp.add(rd, "OffRoad");
        strtyp.setBackground(new Color(63, 80, 110));
        strtyp.setForeground(new Color(209, 217, 230));
        ptyp.setFont();
        ptyp.add(rd, "Roads");
        ptyp.add(rd, "Ramps");
        ptyp.add(rd, "Obstacles");
        ptyp.add(rd, "Checkpoint");
        ptyp.add(rd, "Fixing Hoop");
        ptyp.add(rd, "Trees");
        ptyp.add(rd, "Ground Pile");
        ptyp.add(rd, "Custom Parts");
        ptyp.setBackground(new Color(63, 80, 110));
        ptyp.setForeground(new Color(209, 217, 230));
        part.setFont();
        part.add(rd, "Halfpipe-Normal-Road Blend");
        part.setBackground(new Color(63, 80, 110));
        part.setForeground(new Color(209, 217, 230));
        fixh.setFont(new Font("Arial", 1, 12));
        fixh.setBackground(new Color(255, 255, 255));
        fixh.setForeground(new Color(0, 0, 0));
        mgen.setFont(new Font("Arial", 1, 12));
        mgen.setBackground(new Color(255, 255, 255));
        mgen.setForeground(new Color(0, 0, 0));
        pfog.setFont(new Font("Arial", 1, 12));
        pfog.setBackground(new Color(225, 225, 225));
        pfog.setForeground(new Color(0, 0, 0));
        nlaps.setFont();
        for (int i = 0; i < 15; i++) {
            nlaps.add(rd, " " + (i + 1) + " ");
        }
        nlaps.setBackground(new Color(63, 80, 110));
        nlaps.setForeground(new Color(209, 217, 230));
        tracks.setFont();
        tracks.add(rd, "Select MOD Track");
        tracks.setForeground(new Color(63, 80, 110));
        tracks.setBackground(new Color(209, 217, 230));
        witho.setFont();
        witho.add(rd, "With other cars");
        witho.add(rd, "Alone");
        witho.setBackground(new Color(63, 80, 110));
        witho.setForeground(new Color(209, 217, 230));
        tnick.setFont(new Font("Arial", 1, 13));
        tnick.setBackground(new Color(255, 255, 255));
        tnick.setForeground(new Color(0, 0, 0));
        tpass.setFont(new Font("Arial", 1, 13));
        tpass.setEchoChar('*');
        tpass.setBackground(new Color(255, 255, 255));
        tpass.setForeground(new Color(0, 0, 0));
        pubtyp.setFont();
        pubtyp.add(rd, "Private");
        pubtyp.add(rd, "Public");
        pubtyp.add(rd, "Super Public");
        pubtyp.setBackground(new Color(63, 80, 110));
        pubtyp.setForeground(new Color(209, 217, 230));
        add(tnick);
        add(tpass);
        add(srch);
        add(fixh);
        add(mgen);
        add(pfog);
        hidefields();
    }

    @Override
    public boolean keyDown(final Event event, final int i) {
        if (focuson) {
            if (i == 42 || i == 10 || i == 56 || i == 119 || i == 87 || i == 43 || i == 61) {
                zoomi = true;
            }
            if (i == 47 || i == 8 || i == 50 || i == 115 || i == 83 || i == 45) {
                zoomo = true;
            }
            if (i == 1006) {
                left = true;
            }
            if (i == 1007) {
                right = true;
            }
            if (i == 1005) {
                down = true;
            }
            if (i == 1004) {
                up = true;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(final Event event, final int i) {
        if (i == 42 || i == 10 || i == 56 || i == 119 || i == 87 || i == 43 || i == 61) {
            zoomi = false;
        }
        if (i == 47 || i == 8 || i == 50 || i == 115 || i == 83 || i == 45) {
            zoomo = false;
        }
        if (i == 1006) {
            left = false;
        }
        if (i == 1007) {
            right = false;
        }
        if (i == 1005) {
            down = false;
        }
        if (i == 1004) {
            up = false;
        }
        return false;
    }

    private void loadbase() {

        try {

            FileUtil.loadFiles("data/stageparts", GameSparker.stageRads, prep -> {
                return new File(prep.parent, prep.file + ".rad").toPath();
            }, (is, i) -> {
                bco[i] = new ContO(is);
                for (int j = 0; j < bco[i].npl; j++) {
                    bco[i].p[j].loadprojf();
                }
            });
            bco[bumppart] = new ContO((int) (10000.0 * ThreadLocalRandom.current().nextDouble()), (int) pwd, (int) phd, 0, 0, 0);
        } catch (final Exception exception) {
            JOptionPane.showMessageDialog(null, "Unable to load file 'data/models.zip'!\nError:\n" + exception, "Stage Maker", 1);
            exception.printStackTrace();
        }
        System.gc();
    }
    
    private void loadsettings() {
        try {
            final File file = new File("mystages/settings.data");
            if (file.exists()) {
                final BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
                String string = bufferedreader.readLine();
                if (string != null) {
                    sstage = string;
                    stagename = sstage;
                }
                string = bufferedreader.readLine();
                if (string != null) {
                    suser = string;
                    if (!suser.equals("Horaks")) {
                        tnick.setText(suser);
                    }
                }
                bufferedreader.close();
            }
        } catch (final Exception ignored) {

        }
    }

    @Override
    public boolean mouseDown(final Event event, final int x, final int y) {
        mousdr = true;
        xm = x - apx;
        ym = y - apy;
        mousePressed = 1;
        requestFocus();
        focuson = true;
        return false;
    }

    @Override
    public boolean mouseDrag(final Event event, final int x, final int y) {
        mousdr = true;
        xm = x - apx;
        ym = y - apy;
        return false;
    }

    @Override
    public boolean mouseMove(final Event event, final int x, final int y) {
        xm = x - apx;
        ym = y - apy;
        if (xm > 620 && xm < 774 && ym > 0 && ym < 23) {
            if (!onbtgame) {
                onbtgame = true;
                setCursor(new Cursor(12));
            }
        } else if (onbtgame) {
            onbtgame = false;
            setCursor(new Cursor(0));
        }
        return false;
    }

    @Override
    public boolean mouseUp(final Event event, final int i, final int i172) {
        mousdr = false;
        xm = i - apx;
        ym = i172 - apy;
        if (mousePressed == 1) {
            mousePressed = -1;
        }
        if (onbtgame) {
            Madness.game();
        }
        return false;
    }

    private void movefield(final Component component, int i, int i169, final int i170, final int i171) {
        i += apx;
        i169 += apy;
        if (component.getX() != i || component.getY() != i169 || component.getWidth() != i170 || component.getHeight() != i171) {
            component.setBounds(i, i169, i170, i171);
        }
    }

    private void newstage() {
        if (!srch.getText().equals("")) {
            final File file = new File("mystages/" + srch.getText() + ".txt");
            if (!file.exists()) {
                stagename = srch.getText();
                tstage = "snap(0,0,0)\r\nsky(191,215,255)\r\nclouds(255,255,255,5,-1000)\r\nfog(195,207,230)\r\nground(192,194,202)\r\ntexture(0,0,0,50)\r\nfadefrom(5000)\r\ndensity(5)\r\nmountains(" + (int) (ThreadLocalRandom.current().nextDouble() * 100000.0) + ")\r\nnlaps(5)\r\n\r\n";
                if (strtyp.getSelectedIndex() == 1) {
                    bstage = "set(48,0,0,0)\r\n";
                } else {
                    bstage = "set(47,0,0,0)\r\n";
                }

                bstage = "" + bstage + "\r\nmaxl(3,-7200,-4800)\r\nmaxb(3,-7200,-4800)\r\nmaxr(3,7200,-4800)\r\nmaxt(3,7200,-4800)\r\n";
                savefile();
                strtyp.setVisible(false);
                srch.setVisible(false);
                sfase = 0;
                tabed = -2;
            } else {
                JOptionPane.showMessageDialog(null, "A stage with that name already exists, please choose another name!", "Stage Maker", 1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a stage name first!", "Stage Maker", 1);
        }
    }

    private void openhlink() {
        Madness.openurl("http://www.needformadness.com/developer/");
    }

    private void openlink() {
        Madness.openurl("http://www.needformadness.com/developer/help.html");
    }

    private boolean ovbutton(final String string, final int i, final int i387) {
        rd.setFont(new Font("Arial", 0, 12));
        ftm = rd.getFontMetrics();
        if (string.equals("X") || string.equals("Download")) {
            rd.setFont(new Font("Arial", 1, 12));
            ftm = rd.getFontMetrics();
        }
        final int i388 = ftm.stringWidth(string);
        final int i389 = 4;
        boolean bool;
        boolean bool390 = false;
        bool = Math.abs(xm - i) < i388 / 2 + 12 && Math.abs(ym - i387 + 5) < 10 && mousePressed == 1;
        if (Math.abs(xm - i) < i388 / 2 + 12 && Math.abs(ym - i387 + 5) < 10 && mousePressed == -1) {
            mousePressed = 0;
            bool390 = true;
        }
        if (!bool) {
            rd.setColor(new Color(220, 220, 220));
            rd.fillRect(i - i388 / 2 - 10, i387 - (17 - i389), i388 + 20, 25 - i389 * 2);
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i388 / 2 - 10, i387 - (17 - i389), i + i388 / 2 + 10, i387 - (17 - i389));
            rd.drawLine(i - i388 / 2 - 10, i387 - (18 - i389), i + i388 / 2 + 10, i387 - (18 - i389));
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i388 / 2 - 9, i387 - (19 - i389), i + i388 / 2 + 9, i387 - (19 - i389));
            rd.setColor(new Color(200, 200, 200));
            rd.drawLine(i + i388 / 2 + 10, i387 - (17 - i389), i + i388 / 2 + 10, i387 + 7 - i389);
            rd.drawLine(i + i388 / 2 + 11, i387 - (17 - i389), i + i388 / 2 + 11, i387 + 7 - i389);
            rd.setColor(new Color(200, 200, 200));
            rd.drawLine(i + i388 / 2 + 12, i387 - (16 - i389), i + i388 / 2 + 12, i387 + 6 - i389);
            rd.drawLine(i - i388 / 2 - 10, i387 + 7 - i389, i + i388 / 2 + 10, i387 + 7 - i389);
            rd.drawLine(i - i388 / 2 - 10, i387 + 8 - i389, i + i388 / 2 + 10, i387 + 8 - i389);
            rd.setColor(new Color(200, 200, 200));
            rd.drawLine(i - i388 / 2 - 9, i387 + 9 - i389, i + i388 / 2 + 9, i387 + 9 - i389);
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i388 / 2 - 10, i387 - (17 - i389), i - i388 / 2 - 10, i387 + 7 - i389);
            rd.drawLine(i - i388 / 2 - 11, i387 - (17 - i389), i - i388 / 2 - 11, i387 + 7 - i389);
            rd.setColor(new Color(240, 240, 240));
            rd.drawLine(i - i388 / 2 - 12, i387 - (16 - i389), i - i388 / 2 - 12, i387 + 6 - i389);
            rd.setColor(new Color(0, 0, 0));
            if (string.equals("X")) {
                rd.setColor(new Color(255, 0, 0));
            }
            if (string.equals("Download")) {
                rd.setColor(new Color(0, 64, 128));
            }
            rd.drawString(string, i - i388 / 2, i387);
        } else {
            rd.setColor(new Color(220, 220, 220));
            rd.fillRect(i - i388 / 2 - 10, i387 - (17 - i389), i388 + 20, 25 - i389 * 2);
            rd.setColor(new Color(192, 192, 192));
            rd.drawLine(i - i388 / 2 - 10, i387 - (17 - i389), i + i388 / 2 + 10, i387 - (17 - i389));
            rd.drawLine(i - i388 / 2 - 10, i387 - (18 - i389), i + i388 / 2 + 10, i387 - (18 - i389));
            rd.drawLine(i - i388 / 2 - 9, i387 - (19 - i389), i + i388 / 2 + 9, i387 - (19 - i389));
            rd.setColor(new Color(247, 247, 247));
            rd.drawLine(i + i388 / 2 + 10, i387 - (17 - i389), i + i388 / 2 + 10, i387 + 7 - i389);
            rd.drawLine(i + i388 / 2 + 11, i387 - (17 - i389), i + i388 / 2 + 11, i387 + 7 - i389);
            rd.drawLine(i + i388 / 2 + 12, i387 - (16 - i389), i + i388 / 2 + 12, i387 + 6 - i389);
            rd.drawLine(i - i388 / 2 - 10, i387 + 7 - i389, i + i388 / 2 + 10, i387 + 7 - i389);
            rd.drawLine(i - i388 / 2 - 10, i387 + 8 - i389, i + i388 / 2 + 10, i387 + 8 - i389);
            rd.drawLine(i - i388 / 2 - 9, i387 + 9 - i389, i + i388 / 2 + 9, i387 + 9 - i389);
            rd.setColor(new Color(192, 192, 192));
            rd.drawLine(i - i388 / 2 - 10, i387 - (17 - i389), i - i388 / 2 - 10, i387 + 7 - i389);
            rd.drawLine(i - i388 / 2 - 11, i387 - (17 - i389), i - i388 / 2 - 11, i387 + 7 - i389);
            rd.drawLine(i - i388 / 2 - 12, i387 - (16 - i389), i - i388 / 2 - 12, i387 + 6 - i389);
            rd.setColor(new Color(0, 0, 0));
            if (string.equals("X")) {
                rd.setColor(new Color(255, 0, 0));
            }
            if (string.equals("Download")) {
                rd.setColor(new Color(0, 64, 128));
            }
            rd.drawString(string, i - i388 / 2 + 1, i387 + 1);
        }
        return bool390;
    }

    @Override
    public void paint(final Graphics graphics) {
        apx = getWidth() / 2 - 400;
        apy = getHeight() / 2 - 275;
        graphics.drawImage(offImage, apx, apy, this);
    }

    private void partobst() {
        part.removeAll();
        part.add(rd, "Spiky Pillars");
        part.add(rd, "Rail Doorway");
        part.add(rd, "The Net");
        part.add(rd, "Bump Slide");
        part.add(rd, "Offroad Dirt-Pile 1");
        part.add(rd, "Offroad Dirt-Pile 2");
    }

    private void partramps() {
        part.removeAll();
        part.add(rd, "Basic Ramp");
        part.add(rd, "Two-Way Ramp");
        part.add(rd, "Two-Way High-Low Ramp");
        part.add(rd, "Small Ramp");
        part.add(rd, "Crash Ramp");
        part.add(rd, "Big-Takeoff Ramp");
        part.add(rd, "Landing Ramp");
        part.add(rd, "Tunnel Side Ramp");
        part.add(rd, "Speed Ramp");
        part.add(rd, "Launch Pad Ramp");
        part.add(rd, "Offroad Bump Ramp");
        part.add(rd, "Offroad Ramp");
        part.add(rd, "Offroad Big Ramp");
        part.add(rd, "Offroad Hill Ramp");
        part.add(rd, "Offroad Big Hill Ramp");
        part.add(rd, "Halfpipe");
    }

    private void partrees() {
        part.removeAll();
        part.add(rd, "Tree 1");
        part.add(rd, "Tree 2");
        part.add(rd, "Tree 3");
        part.add(rd, "Tree 4");
        part.add(rd, "Tree 5");
        part.add(rd, "Palm Tree 1");
        part.add(rd, "Palm Tree 2");
        part.add(rd, "Palm Tree 3");
        part.add(rd, "Cactus 1");
        part.add(rd, "Cactus 2");
        part.add(rd, "Cactus 3");
    }

    /**
     * ------------------ ADD NEW PARTS HERE<br>
     * ------------------ THE NAMES IN QUOTES ARE THE NAMES THAT ARE DISPLAYED
     */
    private void partcustom() {
        part.removeAll();
        part.add(rd, "--- CUSTOM PARTS ---");
    }

    private void partroads() {
        part.removeAll();
        part.add(rd, "NormalRoad");
        part.add(rd, "NormalRoad Turn");
        part.add(rd, "NormalRoad End");
        part.add(rd, "NormalRoad TwistedLeft");
        part.add(rd, "NormalRoad TwistedRight");
        part.add(rd, "NormalRoad Edged");
        part.add(rd, "NormalRoad-Raised Ramp");
        part.add(rd, "NormalRoad Raised");
        part.add(rd, "Normal-Off-Road Blend");
        part.add(rd, "OffRoad");
        part.add(rd, "OffRoad Turn");
        part.add(rd, "OffRoad End");
        part.add(rd, "OffRoad BumpyGreen");
        part.add(rd, "OffRoad-BumpySides Start");
        part.add(rd, "OffRoad BumpySides");
        part.add(rd, "Off-Halfpipe-Road Blend");
        part.add(rd, "HalfpipeRoad");
        part.add(rd, "HalfpipeRoad Turn");
        part.add(rd, "HalfpipeRoad-Ramp Filler");
        part.add(rd, "Halfpipe-Normal-Road Blend");
        part.add(rd, "Rollercoaster Start/End");
        part.add(rd, "Rollercoaster Road1");
        part.add(rd, "Rollercoaster Road2");
        part.add(rd, "Rollercoaster Road3");
        part.add(rd, "Rollercoaster Road4");
        part.add(rd, "Rollercoaster Road5");
    }

    private int py(final int i, final int i343, final int i344, final int i345) {
        return (int) Math.sqrt((i - i343) * (i - i343) + (i344 - i345) * (i344 - i345));
    }

    private int pyn(final int i, final int i346, final int i347, final int i348) {
        return (i - i346) / 100 * ((i - i346) / 100) + (i347 - i348) / 100 * ((i347 - i348) / 100);
    }

    private void readstage(final int i) {
        errd = 0;
        trackname = "";
        Trackers.nt = 0;
        nob = 0;
        xnob = 0;
        CheckPoints.n = 0;
        CheckPoints.nsp = 0;
        CheckPoints.fn = 0;
        CheckPoints.haltall = false;
        CheckPoints.wasted = 0;
        CheckPoints.catchfin = 0;
        Medium.ground = 250;
        Medium.lightson = false;
        if (i == 0) {
            Medium.snap[0] = 0;
            Medium.snap[1] = 0;
            Medium.snap[2] = 0;
        }
        if (i == 3) {
            tstage = "";
            bstage = "";
        }
        String string = bstage;
        if (i == 1 || i == 2) {
            string = "" + tstage + "\r\n" + bstage + "";
        }
        int i181 = 0;
        int i182 = 100;
        int i183 = 0;
        int i184 = 100;
        boolean bool = true;
        boolean bool185 = true;
        String string186 = "";
        try {
            BufferedReader bufferedreader;
            if (i == 3) {
                final File file = new File("mystages/" + stagename + ".txt");
                bufferedreader = new BufferedReader(new FileReader(file));
                nundo = 0;
            } else {
                bufferedreader = new BufferedReader(new InputStreamReader(new DataInputStream(new ByteArrayInputStream(string.getBytes()))));
            }
            String string187;
            while ((string187 = bufferedreader.readLine()) != null) {
                string186 = "" + string187.trim();
                if (string186.startsWith("sky")) {
                    csky[0] = getint("sky", string186, 0);
                    csky[1] = getint("sky", string186, 1);
                    csky[2] = getint("sky", string186, 2);
                    Medium.setsky(csky[0], csky[1], csky[2]);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("ground")) {
                    cgrnd[0] = getint("ground", string186, 0);
                    cgrnd[1] = getint("ground", string186, 1);
                    cgrnd[2] = getint("ground", string186, 2);
                    Medium.setgrnd(cgrnd[0], cgrnd[1], cgrnd[2]);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("polys")) {
                    Medium.setpolys(getint("polys", string186, 0), getint("polys", string186, 1), getint("polys", string186, 2));
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("fog")) {
                    cfade[0] = getint("fog", string186, 0);
                    cfade[1] = getint("fog", string186, 1);
                    cfade[2] = getint("fog", string186, 2);
                    Medium.setfade(cfade[0], cfade[1], cfade[2]);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("texture")) {
                    texture[0] = getint("texture", string186, 0);
                    texture[1] = getint("texture", string186, 1);
                    texture[2] = getint("texture", string186, 2);
                    texture[3] = getint("texture", string186, 3);
                    Medium.setexture(texture[0], texture[1], texture[2], texture[3]);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("clouds")) {
                    cldd[0] = getint("clouds", string186, 0);
                    cldd[1] = getint("clouds", string186, 1);
                    cldd[2] = getint("clouds", string186, 2);
                    cldd[3] = getint("clouds", string186, 3);
                    cldd[4] = getint("clouds", string186, 4);
                    Medium.setcloads(cldd[0], cldd[1], cldd[2], cldd[3], cldd[4]);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (i != 2 && string186.startsWith("snap")) {
                    Medium.setsnap(getint("snap", string186, 0), getint("snap", string186, 1), getint("snap", string186, 2));
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("density")) {
                    Medium.fogd = (getint("density", string186, 0) + 1) * 2 - 1;
                    if (Medium.fogd < 1) {
                        Medium.fogd = 1;
                    }
                    if (Medium.fogd > 30) {
                        Medium.fogd = 30;
                    }
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("mountains")) {
                    Medium.mgen = getint("mountains", string186, 0);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("fadefrom")) {
                    Medium.fadfrom(getint("fadefrom", string186, 0));
                    origfade = Medium.fade[0];
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("lightson")) {
                    Medium.lightson = true;
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("nlaps")) {
                    CheckPoints.nlaps = getint("nlaps", string186, 0);
                    if (CheckPoints.nlaps < 1) {
                        CheckPoints.nlaps = 1;
                    }
                    if (CheckPoints.nlaps > 15) {
                        CheckPoints.nlaps = 15;
                    }
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("soundtrack")) {
                    trackname = getstring("soundtrack", string186, 0);
                    trackvol = getint("soundtrack", string186, 1);
                    tracksize = getint("soundtrack", string186, 2);
                    if (i == 3) {

                        tstage = "" + tstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("set")) {
                    int i201 = getint("set", string186, 0);
                    if (i201 >= 10 && i201 <= 25) {
                        Medium.loadnew = true;
                    }
                    i201 -= 10;
                    co[nob] = new ContO(bco[i201], getint("set", string186, 1), Medium.ground - bco[i201].grat, getint("set", string186, 2), getint("set", string186, 3));
                    co[nob].roofat = getint("set", string186, 3);
                    co[nob].colok = i201;
                    if (string186.contains(")p")) {
                        CheckPoints.x[CheckPoints.n] = getint("chk", string186, 1);
                        CheckPoints.z[CheckPoints.n] = getint("chk", string186, 2);
                        CheckPoints.y[CheckPoints.n] = 0;
                        CheckPoints.typ[CheckPoints.n] = 0;
                        if (string186.contains(")pt")) {
                            CheckPoints.typ[CheckPoints.n] = -1;
                        }
                        if (string186.contains(")pr")) {
                            CheckPoints.typ[CheckPoints.n] = -2;
                        }
                        if (string186.contains(")po")) {
                            CheckPoints.typ[CheckPoints.n] = -3;
                        }
                        if (string186.contains(")ph")) {
                            CheckPoints.typ[CheckPoints.n] = -4;
                        }
                        CheckPoints.n++;
                    }
                    xnob++;
                    nob++;
                    if (i == 3) {
                        if (bool185) {

                            bstage = "" + bstage + "\r\n";
                            bool185 = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                    if (Medium.loadnew) {
                        Medium.loadnew = false;
                    }
                }
                if (string186.startsWith("chk")) {
                    int i204 = getint("chk", string186, 0);
                    i204 -= 10;
                    int i205 = Medium.ground - bco[i204].grat;
                    if (i204 == 54) {
                        i205 = getint("chk", string186, 4);
                    }
                    co[nob] = new ContO(bco[i204], getint("chk", string186, 1), i205, getint("chk", string186, 2), getint("chk", string186, 3));
                    co[nob].roofat = getint("chk", string186, 3);
                    co[nob].colok = i204;
                    CheckPoints.x[CheckPoints.n] = getint("chk", string186, 1);
                    CheckPoints.z[CheckPoints.n] = getint("chk", string186, 2);
                    CheckPoints.y[CheckPoints.n] = i205;
                    if (getint("chk", string186, 3) == 0) {
                        CheckPoints.typ[CheckPoints.n] = 1;
                    } else {
                        CheckPoints.typ[CheckPoints.n] = 2;
                    }
                    CheckPoints.pcs = CheckPoints.n;
                    CheckPoints.n++;
                    co[nob].checkpoint = CheckPoints.nsp + 1;
                    if (string186.contains(")r")) {
                        co[nob].wh = CheckPoints.nsp + 1;
                    }
                    CheckPoints.nsp++;
                    xnob++;
                    nob++;
                    if (i == 3) {
                        if (bool185) {

                            bstage = "" + bstage + "\r\n";
                            bool185 = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("fix")) {
                    int i208 = getint("fix", string186, 0);
                    i208 -= 10;
                    co[nob] = new ContO(bco[i208], getint("fix", string186, 1), getint("fix", string186, 3), getint("fix", string186, 2), getint("fix", string186, 4));
                    co[nob].roofat = getint("fix", string186, 4);
                    co[nob].colok = i208;
                    CheckPoints.fx[CheckPoints.fn] = getint("fix", string186, 1);
                    CheckPoints.fz[CheckPoints.fn] = getint("fix", string186, 2);
                    CheckPoints.fy[CheckPoints.fn] = getint("fix", string186, 3);
                    co[nob].elec = true;
                    if (getint("fix", string186, 4) != 0) {
                        CheckPoints.roted[CheckPoints.fn] = true;
                        co[nob].roted = true;
                    } else {
                        CheckPoints.roted[CheckPoints.fn] = false;
                    }
                    CheckPoints.special[CheckPoints.fn] = string186.indexOf(")s") != -1;
                    CheckPoints.fn++;
                    xnob++;
                    nob++;
                    if (i == 3) {
                        if (bool185) {

                            bstage = "" + bstage + "\r\n";
                            bool185 = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("pile")) {
                    co[nob] = new ContO(getint("pile", string186, 0), getint("pile", string186, 1), getint("pile", string186, 2), getint("pile", string186, 3), getint("pile", string186, 4), Medium.ground);
                    co[nob].srz = getint("pile", string186, 0);
                    co[nob].srx = getint("pile", string186, 1);
                    co[nob].sry = getint("pile", string186, 2);
                    co[nob].colok = bumppart;
                    xnob++;
                    nob++;
                    if (i == 3) {
                        if (bool185) {

                            bstage = "" + bstage + "\r\n";
                            bool185 = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("maxr")) {
                    final int i213 = getint("maxr", string186, 0);
                    final int i214 = getint("maxr", string186, 1);
                    i181 = i214;
                    final int i215 = getint("maxr", string186, 2);
                    for (int i216 = 0; i216 < i213; i216++) {
                        co[nob] = new ContO(bco[29], i214, Medium.ground - bco[29].grat, i216 * 4800 + i215, 0);
                        if (i == 0) {
                            xnob++;
                        } else {
                            nob++;
                        }
                    }
                    if (i == 3) {
                        if (bool) {

                            bstage = "" + bstage + "\r\n";
                            bool = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("maxl")) {
                    final int i219 = getint("maxl", string186, 0);
                    final int i220 = getint("maxl", string186, 1);
                    i182 = i220;
                    final int i221 = getint("maxl", string186, 2);
                    for (int i222 = 0; i222 < i219; i222++) {
                        co[nob] = new ContO(bco[29], i220, Medium.ground - bco[29].grat, i222 * 4800 + i221, 180);
                        if (i == 0) {
                            xnob++;
                        } else {
                            nob++;
                        }
                    }
                    if (i == 3) {
                        if (bool) {

                            bstage = "" + bstage + "\r\n";
                            bool = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("maxt")) {
                    final int i225 = getint("maxt", string186, 0);
                    final int i226 = getint("maxt", string186, 1);
                    i183 = i226;
                    final int i227 = getint("maxt", string186, 2);
                    for (int i228 = 0; i228 < i225; i228++) {
                        co[nob] = new ContO(bco[29], i228 * 4800 + i227, Medium.ground - bco[29].grat, i226, 90);
                        if (i == 0) {
                            xnob++;
                        } else {
                            nob++;
                        }
                    }
                    if (i == 3) {
                        if (bool) {

                            bstage = "" + bstage + "\r\n";
                            bool = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
                if (string186.startsWith("maxb")) {
                    final int i231 = getint("maxb", string186, 0);
                    final int i232 = getint("maxb", string186, 1);
                    i184 = i232;
                    final int i233 = getint("maxb", string186, 2);
                    for (int i234 = 0; i234 < i231; i234++) {
                        co[nob] = new ContO(bco[29], i234 * 4800 + i233, Medium.ground - bco[29].grat, i232, -90);
                        if (i == 0) {
                            xnob++;
                        } else {
                            nob++;
                        }
                    }
                    if (i == 3) {
                        if (bool) {

                            bstage = "" + bstage + "\r\n";
                            bool = false;
                        }

                        bstage = "" + bstage + "" + string186 + "\r\n";
                    }
                }
            }
            bufferedreader.close();
            Medium.newpolys(i182, i181 - i182, i184, i183 - i184, nob);
            Medium.newclouds(i182, i181, i184, i183);
            Medium.newmountains(i182, i181, i184, i183);
            Medium.newstars();
        } catch (final Exception exception) {
            System.out.println("Error in stage " + stagename);
            System.out.println("" + exception);
            System.out.println("At line: " + string186);
            errd = 6;
            if (CheckPoints.fn >= 5) {
                errd = 5;
            }
            if (Trackers.nt >= 670000) {
                errd = 1;
            }
            if (CheckPoints.n >= 10000) {
                errd = 2;
            }
            if (nob >= 10000) {
                errd = 4;
            }
        }
        if (Medium.nrw * Medium.ncl >= 16000) {
            errd = 3;
        }
        if (xnob >= 10000) {
            errd = 4;
        }
        if (i == 3 && !bstage.contains("set(47,0,0,0)") && !bstage.contains("set(48,0,0,0)")) {

            bstage = "" + bstage + "set(47,0,0,0)\r\n";
        }
    }

    private void removesp() {
        if (nundo < 5000) {
            undos[nundo] = bstage;
            nundo++;
        }
        String string = "";
        System.out.println("roof: " + co[esp].roofat);
        if (!floats) {
            if (co[esp].colok != 30 && co[esp].colok != 31 && co[esp].colok != 32 && co[esp].colok != bumppart) {
                string = "set(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 31) {
                string = "fix(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].y + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 30 || co[esp].colok == 32) {
                string = "chk(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 54) {
                string = "chk(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].roofat + "," + co[esp].y + ")";
            }
            if (co[esp].colok == bumppart) {
                string = "pile(" + co[esp].srz + "," + co[esp].srx + "," + co[esp].sry + "," + co[esp].x + "," + co[esp].z + ")";
            }
        } else {
            if (co[esp].colok != 30 && co[esp].colok != 31 && co[esp].colok != 32 && co[esp].colok != bumppart) {
                string = "set(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].y + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 31) {
                string = "fix(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].y + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 30 || co[esp].colok == 32) {
                string = "chk(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].y + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == 54) {
                string = "chk(" + (co[esp].colok + 10) + "," + co[esp].x + "," + co[esp].z + "," + co[esp].y + "," + co[esp].roofat + ")";
            }
            if (co[esp].colok == bumppart) {
                string = "pile(" + co[esp].srz + "," + co[esp].srx + "," + co[esp].sry + "," + co[esp].x + "," + co[esp].z + ")";
            }
        }
        final int i = bstage.indexOf(string);
        int i166 = i + string.length();
        int i167 = -1;
        int i168 = bstage.indexOf("set", i166);
        if (i168 != -1) {
            i167 = i168;
        }
        i168 = bstage.indexOf("chk", i166);
        if (i168 != -1 && i168 < i167) {
            i167 = i168;
        }
        i168 = bstage.indexOf("fix", i166);
        if (i168 != -1 && i168 < i167) {
            i167 = i168;
        }
        if (i167 == -1) {
            i167 = bstage.indexOf("\r\n", i166);
            if (i167 != -1) {
                i167++;
            }
        }
        if (i167 != -1) {
            i166 = i167;
        }
        if (i != -1) {
            bstage = "" + bstage.substring(0, i) + "" + bstage.substring(i166, bstage.length()) + "";
        }
        readstage(0);
    }

    private void renstage(final String string) {
        if (string.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter a New Stage Name!\n", "Stage Maker", 1);
        } else {
            try {
                final File file = new File("mystages/" + stagename + ".txt");
                final File file329 = new File("mystages/" + string + ".txt");
                if (file.renameTo(file329)) {
                    stagename = string;
                    sfase = 0;
                    hidefields();
                    tabed = -2;
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to rename stage to: '" + string + "', possible reason: stage name already used!\n", "Stage Maker", 1);
                }
            } catch (final Exception exception) {
                JOptionPane.showMessageDialog(null, "Unable to rename file! Error Deatials:\n" + exception, "Stage Maker", 1);
            }
        }
    }

    private void rot(final int[] is, final int[] is334, final int i, final int i335, final int i336, final int i337) {
        if (i336 != 0) {
            for (int i338 = 0; i338 < i337; i338++) {
                final int i339 = is[i338];
                final int i340 = is334[i338];
                is[i338] = i + (int) ((i339 - i) * Medium.cos(i336) - (i340 - i335) * Medium.sin(i336));
                is334[i338] = i335 + (int) ((i339 - i) * Medium.sin(i336) + (i340 - i335) * Medium.cos(i336));
            }
        }
    }

    @Override
    public void run() {
        thredo.setPriority(10);
        btgame[0] = getImage("data/baseimages/backtogame1.gif");
        btgame[1] = getImage("data/baseimages/backtogame2.gif");
        logo = getImage("data/baseimages/stagemakerlogo.gif");
        for (int i = 0; i < 2; i++) {
            su[i] = getImage("data/baseimages/su" + (i + 1) + ".gif");
            sl[i] = getImage("data/baseimages/sl" + (i + 1) + ".gif");
            sd[i] = getImage("data/baseimages/sd" + (i + 1) + ".gif");
            sr[i] = getImage("data/baseimages/sr" + (i + 1) + ".gif");
            zi[i] = getImage("data/baseimages/zi" + (i + 1) + ".gif");
            zo[i] = getImage("data/baseimages/zo" + (i + 1) + ".gif");
        }
        loadbase();
        loadsettings();
        if (Madness.testdrive != 0) {
            if (Madness.testcar.equals("Failx12")) {
                JOptionPane.showMessageDialog(null, "Failed to load stage! Please make sure stage is saved properly before Test Drive.", "Stage Maker", 1);
                thredo.stop();
            } else {
                stagename = Madness.testcar;
                errd = 0;
                readstage(3);
                if (errd == 0) {
                    tab = 2;
                    dtab = 6;
                    witho.select(Madness.testdrive - 3);
                }
            }
            Madness.testcar = "";
            Madness.testdrive = 0;
        }
        requestFocus();
        // my code to print the mouse pos every second
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("x: " + ((xm - 505) * (Math.abs(sy) / Medium.focusPoint) + sx));
                    System.out.println("z: " + ((290 - ym) * (Math.abs(sy) / Medium.focusPoint) + sz));
                    System.out.println("y: " + (Medium.ground - bco[selectedPart].grat));
                    System.out.println("rot: " + (rot + adrot));
                    try {
                        sleep(1000L); //time in milisseconds it will wait before printing again
                                      //set this too low and the game will lag like hell
                    } catch (final InterruptedException e) {
                        /**/}
                }
            }
        }.start();

        while (!exwist) {
            rd.setColor(new Color(225, 225, 225));
            rd.fillRect(0, 25, 800, 525);
            rd.setColor(new Color(0, 0, 0));
            if (tab != tabed) {
                hidefields();
            }
            if (tab == 0) {
                if (tabed != tab) {
                    slstage.removeAll();
                    slstage.maxl = 360;
                    slstage.add(rd, "Select a Stage                      ");
                    final String[] strings = new File("mystages/").list();
                    if (strings != null) {
                        for (final String string : strings)
                            if (string.toLowerCase().endsWith(".txt")) {
                                slstage.add(rd, string.substring(0, string.length() - 4));
                            }
                    }
                    if (stagename.equals("")) {
                        slstage.select(0);
                    } else {
                        slstage.select(stagename);
                        if (stagename.equals(slstage.getSelectedItem())) {
                            readstage(3);
                            sx = 0;
                            sz = 1500;
                            sy = -10000;
                        } else {
                            stagename = "";
                            slstage.select(0);
                        }
                    }
                    mouseon = -1;
                    sfase = 0;
                }
                rd.drawImage(logo, 261, 35, null);
                if (xm > 261 && xm < 538 && ym > 35 && ym < 121) {
                    if (mouseon == -1) {
                        mouseon = 3;
                        setCursor(new Cursor(12));
                    }
                } else if (mouseon == 3) {
                    mouseon = -1;
                    setCursor(new Cursor(0));
                }
                if (mouseon == 3 && mousePressed == -1) {
                    openhlink();
                }
                rd.setFont(new Font("Arial", 1, 13));
                ftm = rd.getFontMetrics();
                if (xm > 200 && xm < 550 && ym > 467 && ym < 504) {
                    if (mouseon == -1) {
                        mouseon = 2;
                        setCursor(new Cursor(12));
                    }
                } else if (mouseon == 2) {
                    mouseon = -1;
                    setCursor(new Cursor(0));
                }
                if (mouseon == 2) {
                    rd.setColor(new Color(0, 64, 128));
                } else {
                    rd.setColor(new Color(0, 0, 0));
                }
                rd.drawString("For the Stage Maker Homepage, Development Center and Forums :", 400 - ftm.stringWidth("For the Stage Maker Homepage, Development Center and Forums :") / 2, 480);
                rd.setColor(new Color(0, 128, 255));
                String string = "http://www.needformadness.com/developer/";
                rd.drawString(string, 400 - ftm.stringWidth(string) / 2, 500);
                if (mouseon == 2) {
                    rd.setColor(new Color(0, 128, 255));
                } else {
                    rd.setColor(new Color(0, 64, 128));
                }
                rd.drawLine(400 - ftm.stringWidth(string) / 2, 501, 400 + ftm.stringWidth(string) / 2, 501);
                if (mouseon == 2 && mousePressed == -1) {
                    openhlink();
                }
                final int i = -110;
                if (xm > 150 && xm < 600 && ym > 467 + i && ym < 504 + i) {
                    if (mouseon == -1) {
                        mouseon = 1;
                        setCursor(new Cursor(12));
                    }
                } else if (mouseon == 1) {
                    mouseon = -1;
                    setCursor(new Cursor(0));
                }
                if (mouseon == 1) {
                    rd.setColor(new Color(0, 64, 128));
                } else {
                    rd.setColor(new Color(0, 0, 0));
                }
                rd.drawString("For help and a detailed step by step description on how to use the Stage Maker :", 400 - ftm.stringWidth("For help and a detailed step by step description on how to use the Stage Maker :") / 2, 480 + i);
                rd.setColor(new Color(0, 128, 255));
                string = "http://www.needformadness.com/developer/help.html";
                rd.drawString(string, 400 - ftm.stringWidth(string) / 2, 500 + i);
                if (mouseon == 1) {
                    rd.setColor(new Color(0, 128, 255));
                } else {
                    rd.setColor(new Color(0, 64, 128));
                }
                rd.drawLine(400 - ftm.stringWidth(string) / 2, 501 + i, 400 + ftm.stringWidth(string) / 2, 501 + i);
                if (mouseon == 1 && mousePressed == -1) {
                    openlink();
                }
                final int i0 = -60;
                final int i1 = 70;
                rd.setColor(new Color(0, 0, 0));
                rd.drawRect(227 - i1, 194 + i0, 346 + i1 * 2, 167 + i1 / 5);
                if (sfase == 0) {
                    rd.drawString("Select Stage to Edit", 400 - ftm.stringWidth("Select Stage to Edit") / 2, 230 + i0);
                    slstage.move(220, 240 + i0);
                    if (slstage.getWidth() != 360) {
                        slstage.setSize(360);
                    }
                    if (!slstage.isShowing()) {
                        slstage.setVisible(true);
                    }
                    if (button("    Make new Stage    ", 400, 296 + i0, 0, true)) {
                        srch.setText("");
                        slstage.setVisible(false);
                        sfase = 1;
                    }
                    if (button("     Rename Stage     ", 325, 336 + i0, 0, false))
                        if (!stagename.equals("")) {
                            slstage.setVisible(false);
                            srch.setText(stagename);
                            sfase = 2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select a stage to rename first.", "Stage Maker", 1);
                        }
                    if (button("      Delete Stage      ", 475, 336 + i0, 0, false))
                        if (!stagename.equals("")) {
                            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete this stage?\n\n" + stagename + "\n\n", "Stage Maker", 0) == 0) {
                                delstage(stagename);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select a stage to delete first.", "Stage Maker", 1);
                        }
                    if (slstage.getSelectedIndex() != 0) {
                        if (!stagename.equals(slstage.getSelectedItem())) {
                            stagename = slstage.getSelectedItem();
                            readstage(3);
                            sx = 0;
                            sz = 1500;
                            sy = -10000;
                            requestFocus();
                        }
                    } else {
                        stagename = "";
                    }
                }
                if (sfase == 1) {
                    rd.drawString("Make a new Stage", 400 - ftm.stringWidth("Make a new Stage") / 2, 220 + i0);
                    rd.setFont(new Font("Arial", 1, 12));
                    rd.drawString("New stage name :", 200, 246 + i0);
                    movefield(srch, 310, 231 + i0, 290, 23);
                    if (!srch.isShowing()) {
                        srch.setVisible(true);
                        srch.requestFocus();
                    }
                    fixtext(srch);
                    rd.drawString("Starting line type :", 293, 272 + i0);
                    strtyp.move(408, 256 + i0);
                    if (!strtyp.isShowing()) {
                        strtyp.setVisible(true);
                    }
                    if (button("    Make Stage    ", 400, 311 + i0, 0, true)) {
                        newstage();
                    }
                    if (button("  Cancel  ", 400, 351 + i0, 0, false)) {
                        strtyp.setVisible(false);
                        srch.setVisible(false);
                        sfase = 0;
                    }
                }
                if (sfase == 2) {
                    rd.drawString("Rename Stage :  " + stagename + "", 400 - ftm.stringWidth("Rename Stage :  " + stagename + "") / 2, 230 + i0);
                    rd.setFont(new Font("Arial", 1, 12));
                    rd.drawString("New name :", 218, 266 + i0);
                    if (!srch.isShowing()) {
                        srch.setVisible(true);
                        srch.requestFocus();
                    }
                    movefield(srch, 292, 251 + i0, 290, 23);
                    fixtext(srch);
                    if (button("    Rename Stage    ", 400, 306 + i0, 0, true)) {
                        renstage(srch.getText());
                    }
                    if (button("  Cancel  ", 400, 346 + i0, 0, false)) {
                        srch.setVisible(false);
                        sfase = 0;
                    }
                }
            }
            if (tab == 1) {
                if (tabed != tab) {
                    Medium.trk = 2;
                    readstage(0);
                    if (selectedPartType == PART_ROADS) {
                        partroads();
                    }
                    if (selectedPartType == PART_RAMPS) {
                        partramps();
                    }
                    if (selectedPartType == PART_OBSTACLES) {
                        partobst();
                    }
                    if (selectedPartType == PART_TREES) {
                        partrees();
                    }
                    if (selectedPartType == PART_CUSTOM) {
                        partcustom();
                    }
                    onoff = false;
                    setCursor(new Cursor(0));
                    setcur = false;
                    epart = false;
                    arrng = false;
                    if (nob == 1) {
                        selectedPartType = 0;
                        if (co[0].colok == 38) {
                            selectedMenuPart = 9;
                        } else {
                            selectedMenuPart = 0;
                        }
                    }
                    mouseon = -1;
                }
                if (selectedPartType == PART_ROADS) {
                    if (selectedMenuPart == 0) {
                        selectedPart = 0;
                    }
                    if (selectedMenuPart == 1) {
                        selectedPart = 4;
                    }
                    if (selectedMenuPart == 2) {
                        selectedPart = 13;
                    }
                    if (selectedMenuPart == 3) {
                        selectedPart = 3;
                    }
                    if (selectedMenuPart == 4) {
                        selectedPart = 2;
                    }
                    if (selectedMenuPart == 5) {
                        selectedPart = 1;
                    }
                    if (selectedMenuPart == 6) {
                        selectedPart = 35;
                    }
                    if (selectedMenuPart == 7) {
                        selectedPart = 36;
                    }
                    if (selectedMenuPart == 8) {
                        selectedPart = 10;
                    }
                    if (selectedMenuPart == 9) {
                        selectedPart = 5;
                    }
                    if (selectedMenuPart == 10) {
                        selectedPart = 7;
                    }
                    if (selectedMenuPart == 11) {
                        selectedPart = 14;
                    }
                    if (selectedMenuPart == 12) {
                        selectedPart = 6;
                    }
                    if (selectedMenuPart == 13) {
                        selectedPart = 34;
                    }
                    if (selectedMenuPart == 14) {
                        selectedPart = 33;
                    }
                    if (selectedMenuPart == 15) {
                        selectedPart = 11;
                    }
                    if (selectedMenuPart == 16) {
                        selectedPart = 8;
                    }
                    if (selectedMenuPart == 17) {
                        selectedPart = 9;
                    }
                    if (selectedMenuPart == 18) {
                        selectedPart = 15;
                    }
                    if (selectedMenuPart == 19) {
                        selectedPart = 12;
                    }
                    if (selectedMenuPart == 20) {
                        selectedPart = 46;
                    }
                    if (selectedMenuPart == 21) {
                        selectedPart = 47;
                    }
                    if (selectedMenuPart == 22) {
                        selectedPart = 50;
                    }
                    if (selectedMenuPart == 23) {
                        selectedPart = 48;
                    }
                    if (selectedMenuPart == 24) {
                        selectedPart = 49;
                    }
                    if (selectedMenuPart == 25) {
                        selectedPart = 51;
                    }
                }
                if (selectedPartType == PART_RAMPS) {
                    if (selectedMenuPart == 0) {
                        selectedPart = 16;
                    }
                    if (selectedMenuPart == 1) {
                        selectedPart = 18;
                    }
                    if (selectedMenuPart == 2) {
                        selectedPart = 19;
                    }
                    if (selectedMenuPart == 3) {
                        selectedPart = 22;
                    }
                    if (selectedMenuPart == 4) {
                        selectedPart = 17;
                    }
                    if (selectedMenuPart == 5) {
                        selectedPart = 21;
                    }
                    if (selectedMenuPart == 6) {
                        selectedPart = 20;
                    }
                    if (selectedMenuPart == 7) {
                        selectedPart = 39;
                    }
                    if (selectedMenuPart == 8) {
                        selectedPart = 42;
                    }
                    if (selectedMenuPart == 9) {
                        selectedPart = 40;
                    }
                    if (selectedMenuPart == 10) {
                        selectedPart = 23;
                    }
                    if (selectedMenuPart == 11) {
                        selectedPart = 25;
                    }
                    if (selectedMenuPart == 12) {
                        selectedPart = 24;
                    }
                    if (selectedMenuPart == 13) {
                        selectedPart = 43;
                    }
                    if (selectedMenuPart == 14) {
                        selectedPart = 45;
                    }
                    if (selectedMenuPart == 15) {
                        selectedPart = 26;
                    }
                }
                if (selectedPartType == PART_OBSTACLES) {
                    if (selectedMenuPart == 0) {
                        selectedPart = 27;
                    }
                    if (selectedMenuPart == 1) {
                        selectedPart = 28;
                    }
                    if (selectedMenuPart == 2) {
                        selectedPart = 41;
                    }
                    if (selectedMenuPart == 3) {
                        selectedPart = 44;
                    }
                    if (selectedMenuPart == 4) {
                        selectedPart = 52;
                    }
                    if (selectedMenuPart == 5) {
                        selectedPart = 53;
                    }
                }
                if (selectedPartType == PART_CHECKPOINTS)
                    if (onfly) {
                        selectedPart = 54;
                    } else if (!onoff) {
                        selectedPart = 30;
                    } else {
                        selectedPart = 32;
                    }
                if (selectedPartType == PART_FIXHOOPS) {
                    selectedPart = 31;
                }
                if (selectedPartType == PART_TREES) {
                    if (selectedMenuPart == 0) {
                        selectedPart = 55;
                    }
                    if (selectedMenuPart == 1) {
                        selectedPart = 56;
                    }
                    if (selectedMenuPart == 2) {
                        selectedPart = 57;
                    }
                    if (selectedMenuPart == 3) {
                        selectedPart = 58;
                    }
                    if (selectedMenuPart == 4) {
                        selectedPart = 59;
                    }
                    if (selectedMenuPart == 5) {
                        selectedPart = 60;
                    }
                    if (selectedMenuPart == 6) {
                        selectedPart = 61;
                    }
                    if (selectedMenuPart == 7) {
                        selectedPart = 62;
                    }
                    if (selectedMenuPart == 8) {
                        selectedPart = 63;
                    }
                    if (selectedMenuPart == 9) {
                        selectedPart = 64;
                    }
                    if (selectedMenuPart == 10) {
                        selectedPart = 65;
                    }
                    if (selectedMenuPart == 11) {
                        selectedPart = 66;
                    }

                }
                if (selectedPartType == PART_CUSTOM) { // --------------- NEW PARTS
                    selectedPart = selectedMenuPart + 67;
                    if (selectedMenuPart == 0) {
                        selectedPart = 0;
                    }
                }
                if (selectedPartType == PART_BUMP) {
                    if (!pgen) {
                        int i = (int) (10000.0 * ThreadLocalRandom.current().nextDouble());
                        if (fgen != 0) {
                            i = fgen;
                            fgen = 0;
                        }
                        bco[bumppart] = new ContO(i, (int) pwd, (int) phd, 0, 0, 0);
                        bco[bumppart].srz = i;
                        bco[bumppart].srx = (int) pwd;
                        bco[bumppart].sry = (int) phd;
                        pgen = true;
                        seq = 3;
                    }
                    selectedPart = bumppart;
                    rot = 0;
                } else if (pgen) {
                    pgen = false;
                    pwd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                    phd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                }
                if (selectedPart == 30 || selectedPart == 31 || selectedPart == 32 || selectedPart == 54) {
                    if (rot == -90) {
                        rot = 90;
                    }
                    if (rot == 180) {
                        rot = 0;
                    }
                }
                adrot = 0;
                if (selectedPart == 2) {
                    adrot = -30;
                }
                if (selectedPart == 3) {
                    adrot = 30;
                }
                if (selectedPart == 15) {
                    adrot = 90;
                }
                if (selectedPart == 20) {
                    adrot = 180;
                }
                if (selectedPart == 26) {
                    adrot = 90;
                }
                rd.setColor(new Color(200, 200, 200));
                rd.fillRect(248, 63, 514, 454);
                Medium.trk = 2;
                Medium.zy = 90;
                Medium.xz = 0;
                Medium.iw = 248;
                Medium.w = 762;
                Medium.ih = 63;
                Medium.h = 517;
                Medium.cx = 505;
                Medium.cy = 290;
                Medium.x = sx - Medium.cx;
                Medium.z = sz - Medium.cz;
                Medium.y = sy;
                int i = 0;
                final int[] is = new int[10000]; // stageselect limit
                for (int i2 = 0; i2 < nob; i2++)
                    if (co[i2].dist != 0) {
                        is[i] = i2;
                        i++;
                    } else {
                        co[i2].d(rd);
                    }
                final int[] is3 = new int[i];
                for (int i4 = 0; i4 < i; i4++) {
                    is3[i4] = 0;
                }
                for (int i5 = 0; i5 < i; i5++) {
                    for (int i6 = i5 + 1; i6 < i; i6++)
                        if (co[is[i5]].dist != co[is[i6]].dist) {
                            if (co[is[i5]].dist < co[is[i6]].dist) {
                                is3[i5]++;
                            } else {
                                is3[i6]++;
                            }
                        } else if (i6 > i5) {
                            is3[i5]++;
                        } else {
                            is3[i6]++;
                        }
                }
                for (int i7 = 0; i7 < i; i7++) {
                    for (int i8 = 0; i8 < i; i8++)
                        if (is3[i8] == i7) {
                            if (is[i8] == hi) {
                                Medium.trk = 3;
                            }
                            if (is[i8] == chi && !co[is[i8]].errd) {
                                final int i9 = Medium.cx + (int) ((co[is[i8]].x - Medium.x - Medium.cx) * Medium.cos(Medium.xz) - (co[is[i8]].z - Medium.z - Medium.cz) * Medium.sin(Medium.xz));
                                final int i10 = Medium.cz + (int) ((co[is[i8]].x - Medium.x - Medium.cx) * Medium.sin(Medium.xz) + (co[is[i8]].z - Medium.z - Medium.cz) * Medium.cos(Medium.xz));
                                final int i11 = Medium.cy + (int) ((co[is[i8]].y - Medium.y - Medium.cy) * Medium.cos(Medium.zy) - (i10 - Medium.cz) * Medium.sin(Medium.zy));
                                final int i12 = Medium.cz + (int) ((co[is[i8]].y - Medium.y - Medium.cy) * Medium.sin(Medium.zy) + (i10 - Medium.cz) * Medium.cos(Medium.zy));
                                final int i13 = 1000000 / Math.abs(sy);
                                final Graphics2D graphics2d = rd;
                                graphics2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
                                rd.setColor(new Color(0, 164, 255));
                                rd.fillOval(Utility.xs(i9, i12) - i13 / 2, Utility.ys(i11, i12) - i13 / 2, i13, i13);
                                graphics2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
                                rd.setColor(new Color(0, 0, 0));
                                rd.setFont(new Font("Arial", 1, 12));
                                ftm = rd.getFontMetrics();
                                rd.drawString("NO# " + (arrcnt + 1) + "", Utility.xs(i9, i12) - ftm.stringWidth("NO# " + (arrcnt + 1) + "") / 2, Utility.ys(i11, i12) - i13 / 2);
                            }
                            if (arrng && (co[is[i8]].colok == 30 || co[is[i8]].colok == 32 || co[is[i8]].colok == 54) && co[is[i8]].errd) {
                                final int i14 = Medium.cx + (int) ((co[is[i8]].x - Medium.x - Medium.cx) * Medium.cos(Medium.xz) - (co[is[i8]].z - Medium.z - Medium.cz) * Medium.sin(Medium.xz));
                                final int i15 = Medium.cz + (int) ((co[is[i8]].x - Medium.x - Medium.cx) * Medium.sin(Medium.xz) + (co[is[i8]].z - Medium.z - Medium.cz) * Medium.cos(Medium.xz));
                                final int i16 = Medium.cy + (int) ((co[is[i8]].y - Medium.y - Medium.cy) * Medium.cos(Medium.zy) - (i15 - Medium.cz) * Medium.sin(Medium.zy));
                                final int i17 = Medium.cz + (int) ((co[is[i8]].y - Medium.y - Medium.cy) * Medium.sin(Medium.zy) + (i15 - Medium.cz) * Medium.cos(Medium.zy));
                                final int i18 = 1000000 / Math.abs(sy);
                                final Graphics2D graphics2d = rd;
                                graphics2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
                                rd.setColor(new Color(255, 128, 0));
                                rd.fillOval(Utility.xs(i14, i17) - i18 / 2, Utility.ys(i16, i17) - i18 / 2, i18, i18);
                                graphics2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
                                rd.setColor(new Color(0, 0, 0));
                                rd.setFont(new Font("Arial", 1, 12));
                                ftm = rd.getFontMetrics();
                                rd.drawString("NO# " + co[is[i8]].wh + "", Utility.xs(i14, i17) - ftm.stringWidth("NO# " + co[is[i8]].wh + "") / 2, Utility.ys(i16, i17) - i18 / 2);
                            }
                            co[is[i8]].d(rd);
                            if (Medium.trk == 3) {
                                Medium.trk = 2;
                            }
                        }
                }
                if (xm > 248 && xm < 762 && ym > 63 && ym < 517) {
                    if (!epart && !arrng) { // CALCULATES MOUSE POSITION AND PLACES SHIT
                        bco[selectedPart].x = (xm - 505) * (Math.abs(sy) / Medium.focusPoint) + sx;
                        bco[selectedPart].z = (290 - ym) * (Math.abs(sy) / Medium.focusPoint) + sz;
                        bco[selectedPart].y = Medium.ground - bco[selectedPart].grat;
                        bco[selectedPart].xz = rot + adrot;
                        int i19 = 200;
                        int i20 = 0;
                        int i21 = 0;
                        final int[] is22 = {
                                bco[selectedPart].x + atp[selectedPart][0], bco[selectedPart].x + atp[selectedPart][2]
                        };
                        final int[] is23 = {
                                bco[selectedPart].z + atp[selectedPart][1], bco[selectedPart].z + atp[selectedPart][3]
                        };
                        rot(is22, is23, bco[selectedPart].x, bco[selectedPart].z, rot, 2);
                        int i24 = 0;
                        onfly = false;
                        int i25 = 500;
                        for (int i26 = 0; i26 < nob; i26++) {
                            final int[] is27 = {
                                    co[i26].x + atp[co[i26].colok][0], co[i26].x + atp[co[i26].colok][2]
                            };
                            final int[] is28 = {
                                    co[i26].z + atp[co[i26].colok][1], co[i26].z + atp[co[i26].colok][3]
                            };
                            int i29 = co[i26].roofat;
                            if (co[i26].colok == 2) {
                                i29 += 30;
                            }
                            if (co[i26].colok == 3) {
                                i29 -= 30;
                            }
                            if (co[i26].colok == 15) {
                                i29 -= 90;
                            }
                            if (co[i26].colok == 20) {
                                i29 -= 180;
                            }
                            if (co[i26].colok == 26) {
                                i29 -= 90;
                            }
                            rot(is27, is28, co[i26].x, co[i26].z, i29, 2);
                            if (selectedPart <= maxpart) {
                                int i30 = py(is27[0], is22[0], is28[0], is23[0]);
                                if (i30 < i19 && i30 != 0) {
                                    i19 = i30;
                                    i20 = is27[0] - is22[0];
                                    i21 = is28[0] - is23[0];
                                }
                                i30 = py(is27[1], is22[0], is28[1], is23[0]);
                                if (i30 < i19 && i30 != 0) {
                                    i19 = i30;
                                    i20 = is27[1] - is22[0];
                                    i21 = is28[1] - is23[0];
                                }
                                i30 = py(is27[1], is22[1], is28[1], is23[1]);
                                if (i30 < i19 && i30 != 0) {
                                    i19 = i30;
                                    i20 = is27[1] - is22[1];
                                    i21 = is28[1] - is23[1];
                                }
                                i30 = py(is27[0], is22[1], is28[0], is23[1]);
                                if (i30 < i19 && i30 != 0) {
                                    i19 = i30;
                                    i20 = is27[0] - is22[1];
                                    i21 = is28[0] - is23[1];
                                }
                            }
                            if (selectedPartType == PART_CHECKPOINTS && py(is27[0], is22[0], is28[0], is23[0]) != 0 && py(is27[1], is22[0], is28[1], is23[0]) != 0) {
                                for (final int element : rcheckp)
                                    if (co[i26].colok == element) {
                                        if (py(is27[0], is22[0], is28[0], is23[0]) <= i24 || i24 == 0) {
                                            i24 = py(is27[0], is22[0], is28[0], is23[0]);
                                            onoff = false;
                                        }
                                        if (py(is27[1], is22[0], is28[1], is23[0]) <= i24) {
                                            i24 = py(is27[1], is22[0], is28[1], is23[0]);
                                            onoff = false;
                                        }
                                    }
                                for (final int element : ocheckp)
                                    if (co[i26].colok == element) {
                                        if (py(is27[0], is22[0], is28[0], is23[0]) <= i24 || i24 == 0) {
                                            i24 = py(is27[0], is22[0], is28[0], is23[0]);
                                            onoff = true;
                                        }
                                        if (py(is27[1], is22[0], is28[1], is23[0]) <= i24) {
                                            i24 = py(is27[1], is22[0], is28[1], is23[0]);
                                            onoff = true;
                                        }
                                    }
                            }
                            if (selectedPart > 12 && selectedPart < 33 || selectedPart == 35 || selectedPart == 36 || selectedPart >= 39 && selectedPart <= 54) { //FIXME - possible overlook on my part?
                                if ((rot == 0 || rot == 180 || selectedPart == 26 || selectedPart == 15) && (i29 == 0 || i29 == 180 || selectedPart == 26 || selectedPart == 15)) {
                                    if (Math.abs(is27[0] - is22[0]) < 200) {
                                        i20 = is27[0] - is22[0];
                                    }
                                    if (Math.abs(is27[0] - is22[1]) < 200) {
                                        i20 = is27[0] - is22[1];
                                    }
                                    if (Math.abs(is27[1] - is22[1]) < 200) {
                                        i20 = is27[1] - is22[1];
                                    }
                                    if (Math.abs(is27[1] - is22[0]) < 200) {
                                        i20 = is27[1] - is22[0];
                                    }
                                }
                                if ((rot == 90 || rot == -90 || selectedPart == 26 || selectedPart == 15) && (i29 == 90 || i29 == -90 || selectedPart == 26 || selectedPart == 15)) {
                                    if (Math.abs(is28[0] - is23[0]) < 200) {
                                        i21 = is28[0] - is23[0];
                                    }
                                    if (Math.abs(is28[0] - is23[1]) < 200) {
                                        i21 = is28[0] - is23[1];
                                    }
                                    if (Math.abs(is28[1] - is23[1]) < 200) {
                                        i21 = is28[1] - is23[1];
                                    }
                                    if (Math.abs(is28[1] - is23[0]) < 200) {
                                        i21 = is28[1] - is23[0];
                                    }
                                }
                            }
                            if (selectedPartType == PART_CHECKPOINTS && co[i26].colok >= 46 && co[i26].colok <= 51) {
                                final int[] is33 = {
                                        2, 3, 5, 2, 3, 3
                                };
                                if ((Math.abs(co[i26].roofat) == 180 || co[i26].roofat == 0) && rot == 0 && Math.abs(bco[selectedPart].x - co[i26].x) < 500 && Math.abs(bco[selectedPart].z - co[i26].z) < 3000) {
                                    for (int i34 = 0; i34 < is33[co[i26].colok - 46]; i34++) {
                                        for (int i35 = 0; i35 < co[i26].p[i34].n; i35++)
                                            if (py(bco[selectedPart].x, co[i26].x, bco[selectedPart].z, co[i26].z + co[i26].p[i34].oz[i35]) < i25) {
                                                i25 = py(bco[selectedPart].x, co[i26].x, bco[selectedPart].z, co[i26].z + co[i26].p[i34].oz[i35]);
                                                flyh = co[i26].p[i34].oy[i35] - 28 + Medium.ground;
                                                i20 = co[i26].x - bco[selectedPart].x;
                                                i21 = co[i26].z + co[i26].p[i34].oz[i35] - bco[selectedPart].z;
                                                onfly = true;
                                            }
                                    }
                                }
                                if (Math.abs(co[i26].roofat) == 90 && rot == 90 && Math.abs(bco[selectedPart].z - co[i26].z) < 500 && Math.abs(bco[selectedPart].x - co[i26].x) < 3000) {
                                    for (int i36 = 0; i36 < is33[co[i26].colok - 46]; i36++) {
                                        for (int i37 = 0; i37 < co[i26].p[i36].n; i37++)
                                            if (py(bco[selectedPart].z, co[i26].z, bco[selectedPart].x, co[i26].x + co[i26].p[i36].ox[i37]) < i25) {
                                                i25 = py(bco[selectedPart].z, co[i26].z, bco[selectedPart].x, co[i26].x + co[i26].p[i36].ox[i37]);
                                                flyh = co[i26].p[i36].oy[i37] - 28 + Medium.ground;
                                                i21 = co[i26].z - bco[selectedPart].z;
                                                i20 = co[i26].x + co[i26].p[i36].ox[i37] - bco[selectedPart].x;
                                                onfly = true;
                                            }
                                    }
                                }
                            }
                        }
                        bco[selectedPart].x += i20;
                        bco[selectedPart].z += i21;
                        final int i38 = bco[selectedPart].xy;
                        final int i39 = bco[selectedPart].zy;
                        if (selectedPart == 31) {
                            bco[selectedPart].y = -hf;
                            if (bco[selectedPart].y > -500) {
                                bco[selectedPart].y = -500;
                            }
                        } else {
                            bco[selectedPart].xy = 0;
                        }
                        if (selectedPart == 54) {
                            bco[selectedPart].y = flyh;
                        }
                        bco[selectedPart].zy = 0;
                        if (cntout == 0) {
                            if (mouseon == -1) {
                                bco[selectedPart].d(rd);
                                if (!setcur) {
                                    setCursor(new Cursor(13));
                                    setcur = true;
                                }
                                if (mousePressed == -1) {
                                    if (nundo < 5000) {
                                        undos[nundo] = bstage;
                                        nundo++;
                                    }
                                    if (bco[selectedPart].xz == 270) {
                                        bco[selectedPart].xz = -90;
                                    }
                                    if (bco[selectedPart].xz == 360) {
                                        bco[selectedPart].xz = 0;
                                    }
                                    errd = 0;
                                    boolean bool = false;
                                    if (xnob < 10000) { //piece limit
                                        System.out.println("place check");
                                        if (selectedPart != 31 && selectedPart != 54 && selectedPart != bumppart) {
                                            try {
                                                System.out.println("placed");
                                                System.out.println("" + selectedPart);
                                                System.out.println("" + bco[selectedPart]);
                                                co[nob] = new ContO(bco[selectedPart], bco[selectedPart].x, Medium.ground - bco[selectedPart].grat, bco[selectedPart].z, bco[selectedPart].xz);
                                                co[nob].roofat = bco[selectedPart].xz;
                                                co[nob].colok = selectedPart;
                                                nob++;
                                            } catch (final Exception exception) {
                                                errd = 1;
                                            }
                                        }
                                        if (selectedPart == 31)
                                            if (CheckPoints.fn < 5) {
                                                co[nob] = new ContO(bco[selectedPart], bco[selectedPart].x, bco[selectedPart].y, bco[selectedPart].z, bco[selectedPart].xz);
                                                co[nob].roofat = bco[selectedPart].xz;
                                                co[nob].colok = selectedPart;
                                                nob++;
                                                fixh.setText("" + Math.abs(bco[selectedPart].y) + "");
                                            } else {
                                                errd = 5;
                                            }
                                        if (selectedPart == 54) {
                                            try {
                                                co[nob] = new ContO(bco[selectedPart], bco[selectedPart].x, bco[selectedPart].y, bco[selectedPart].z, bco[selectedPart].xz);
                                                co[nob].roofat = bco[selectedPart].xz;
                                                co[nob].colok = selectedPart;
                                                nob++;
                                            } catch (final Exception exception) {
                                                errd = 1;
                                            }
                                        }
                                        if (selectedPart == bumppart) {
                                            co[nob] = new ContO(bco[bumppart].srz, bco[bumppart].srx, bco[bumppart].sry, bco[bumppart].x, bco[bumppart].z, bco[selectedPart].y);
                                            co[nob].srz = bco[bumppart].srz;
                                            co[nob].srx = bco[bumppart].srx;
                                            co[nob].sry = bco[bumppart].sry;
                                            co[nob].colok = selectedPart;
                                            nob++;
                                        }
                                    } else {
                                        errd = 4;
                                    }
                                    if (errd == 0) {
                                        sortstage();
                                        readstage(0);
                                        bool = true;
                                        if (selectedPart == bumppart) {
                                            pgen = false;
                                        }
                                        if (selectedPart == 52 || selectedPart == 53 || selectedPart >= 55 && selectedPart <= 65) {
                                            seq = 3;
                                            bco[selectedPart].xy = 0;
                                            bco[selectedPart].zy = 0;
                                            boolean bool40 = false;
                                            if (rot == 0 && !bool40) {
                                                rot = 90;
                                                bool40 = true;
                                            }
                                            if (rot == 90 && !bool40) {
                                                rot = 180;
                                                bool40 = true;
                                            }
                                            if (rot == 180 && !bool40) {
                                                rot = -90;
                                                bool40 = true;
                                            }
                                            if (rot == -90 && !bool40) {
                                                rot = 0;
                                            }
                                        }
                                    }
                                    if (errd != 0) {
                                        JOptionPane.showMessageDialog(null, "Error!  Unable to place part!\nReason:\n" + errlo[errd - 1] + "\n\n", "Stage Maker", 0);
                                        if (bool) {
                                            nundo--;
                                            bstage = undos[nundo];
                                            readstage(0);
                                        }
                                    }
                                    lxm = bco[selectedPart].x;
                                    //lym = bco[selectedPart].z;
                                    cntout = 10;
                                }
                            }
                        } else {
                            if (lxm != bco[selectedPart].x && lxm != bco[selectedPart].z) {
                                cntout--;
                            }
                            if (setcur) {
                                setCursor(new Cursor(0));
                                setcur = false;
                            }
                        }
                        bco[selectedPart].xy = i38;
                        bco[selectedPart].zy = i39;
                    } else {
                        if (epart)
                            if (esp == -1 && !overcan) {
                                hi = -1;
                                int i41 = 0;
                                for (int i42 = 0; i42 < nob; i42++) {
                                    final int i43 = Medium.cx + (int) ((co[i42].x - Medium.x - Medium.cx) * Medium.cos(Medium.xz) - (co[i42].z - Medium.z - Medium.cz) * Medium.sin(Medium.xz));
                                    final int i44 = Medium.cz + (int) ((co[i42].x - Medium.x - Medium.cx) * Medium.sin(Medium.xz) + (co[i42].z - Medium.z - Medium.cz) * Medium.cos(Medium.xz));
                                    final int i45 = Medium.cy + (int) ((co[i42].y - Medium.y - Medium.cy) * Medium.cos(Medium.zy) - (i44 - Medium.cz) * Medium.sin(Medium.zy));
                                    final int i46 = Medium.cz + (int) ((co[i42].y - Medium.y - Medium.cy) * Medium.sin(Medium.zy) + (i44 - Medium.cz) * Medium.cos(Medium.zy));
                                    if (xm > Utility.xs(i43 - co[i42].maxR, i46) && xm < Utility.xs(i43 + co[i42].maxR, i46) && ym > Utility.ys(i45 - co[i42].maxR, i46) && ym < Utility.ys(i45 + co[i42].maxR, i46) && co[i42].colok != 37 && co[i42].colok != 38)
                                        if (hi == -1) {
                                            hi = i42;
                                            i41 = py(xm, Utility.xs(i43, i46), ym, Utility.ys(i45, i46));
                                        } else if (py(xm, Utility.xs(i43, i46), ym, Utility.ys(i45, i46)) <= i41) {
                                            hi = i42;
                                            i41 = py(xm, Utility.xs(i43, i46), ym, Utility.ys(i45, i46));
                                        }
                                }
                                if (hi != -1) {
                                    if (!setcur) {
                                        setCursor(new Cursor(13));
                                        setcur = true;
                                    }
                                    if (mousePressed == -1) {
                                        esp = hi;
                                        mousePressed = 0;
                                    }
                                } else if (setcur) {
                                    setCursor(new Cursor(0));
                                    setcur = false;
                                }
                            } else if (setcur) {
                                setCursor(new Cursor(0));
                                setcur = false;
                            }
                        if (arrng) {
                            chi = -1;
                            int i47 = 5000;
                            for (int i48 = 0; i48 < nob; i48++)
                                if ((co[i48].colok == 30 || co[i48].colok == 32 || co[i48].colok == 54) && !co[i48].errd) {
                                    final int i49 = Medium.cx + (int) ((co[i48].x - Medium.x - Medium.cx) * Medium.cos(Medium.xz) - (co[i48].z - Medium.z - Medium.cz) * Medium.sin(Medium.xz));
                                    final int i50 = Medium.cz + (int) ((co[i48].x - Medium.x - Medium.cx) * Medium.sin(Medium.xz) + (co[i48].z - Medium.z - Medium.cz) * Medium.cos(Medium.xz));
                                    final int i51 = Medium.cy + (int) ((co[i48].y - Medium.y - Medium.cy) * Medium.cos(Medium.zy) - (i50 - Medium.cz) * Medium.sin(Medium.zy));
                                    final int i52 = Medium.cz + (int) ((co[i48].y - Medium.y - Medium.cy) * Medium.sin(Medium.zy) + (i50 - Medium.cz) * Medium.cos(Medium.zy));
                                    if (xm > Utility.xs(i49 - co[i48].maxR, i52) && xm < Utility.xs(i49 + co[i48].maxR, i52) && ym > Utility.ys(i51 - co[i48].maxR, i52) && ym < Utility.ys(i51 + co[i48].maxR, i52) && py(xm, Utility.xs(i49, i52), ym, Utility.ys(i51, i52)) <= i47) {
                                        chi = i48;
                                        i47 = py(xm, Utility.xs(i49, i52), ym, Utility.ys(i51, i52));
                                    }
                                }
                            if (chi != -1) {
                                if (!setcur) {
                                    setCursor(new Cursor(13));
                                    setcur = true;
                                }
                                if (mousePressed == -1) {
                                    arrcnt++;
                                    co[chi].wh = arrcnt;
                                    co[chi].errd = true;
                                    mousePressed = 0;
                                }
                            } else if (setcur) {
                                setCursor(new Cursor(0));
                                setcur = false;
                            }
                        }
                    }

                } else if (setcur) {
                    setCursor(new Cursor(0));
                    setcur = false;
                }
                if (epart && esp != -1)
                    if (co[esp].dist != 0) {
                        Medium.cx = 505;
                        Medium.cy = 290;
                        Medium.x = sx - Medium.cx;
                        Medium.z = sz - Medium.cz;
                        Medium.y = sy;
                        final int i53 = Medium.cx + (int) ((co[esp].x - Medium.x - Medium.cx) * Medium.cos(Medium.xz) - (co[esp].z - Medium.z - Medium.cz) * Medium.sin(Medium.xz));
                        final int i54 = Medium.cz + (int) ((co[esp].x - Medium.x - Medium.cx) * Medium.sin(Medium.xz) + (co[esp].z - Medium.z - Medium.cz) * Medium.cos(Medium.xz));
                        final int i55 = Medium.cy + (int) ((co[esp].y - Medium.y - Medium.cy) * Medium.cos(Medium.zy) - (i54 - Medium.cz) * Medium.sin(Medium.zy));
                        final int i56 = Medium.cz + (int) ((co[esp].y - Medium.y - Medium.cy) * Medium.sin(Medium.zy) + (i54 - Medium.cz) * Medium.cos(Medium.zy));
                        final int i57 = Utility.xs(i53, i56);
                        final int i58 = Utility.ys(i55, i56);
                        rd.setColor(new Color(225, 225, 225));
                        rd.fillRect(i57, i58, 90, 88);
                        rd.setColor(new Color(138, 147, 160));
                        rd.drawRect(i57, i58, 90, 88);
                        if (button("   Edit   ", i57 + 45, i58 + 22, 3, false)) {
                            copyesp(true);
                            removesp();
                            lxm = 0;
                            //lym = 0;
                            cntout = 2;
                            epart = false;
                        }
                        if (button(" Remove ", i57 + 45, i58 + 49, 3, false)) {
                            removesp();
                            esp = -1;
                            mousePressed = 0;
                        }
                        if (button("  Copy  ", i57 + 45, i58 + 76, 3, false)) {
                            copyesp(false);
                            lxm = 0;
                            //lym = 0;
                            cntout = 2;
                            epart = false;
                        }
                        rd.setColor(new Color(255, 0, 0));
                        rd.drawString("x", i57 + 82, i58 - 2);
                        if (xm > 248 && xm < 762 && ym > 63 && ym < 517 && mousePressed == 1 && (xm < i57 || xm > i57 + 90 || ym < i58 || ym > i58 + 88)) {
                            esp = -1;
                            mousePressed = 0;
                        }
                    } else {
                        esp = -1;
                    }
                rd.setColor(new Color(225, 225, 225));
                rd.fillRect(248, 25, 514, 38);
                rd.fillRect(0, 25, 248, 530);
                rd.fillRect(248, 517, 514, 38);
                rd.fillRect(762, 25, 38, 530);
                if (selectedPartType == PART_BUMP) {
                    rd.setColor(new Color(0, 0, 0));
                    rd.setFont(new Font("Arial", 1, 12));
                    rd.drawString("Radius:", 11, 97);
                    rd.drawString("Height:", 14, 117);
                    boolean bool = false;
                    if (xm > 57 && xm < 204 && ym > 90 && ym < 99) {
                        bool = true;
                    }
                    rd.setColor(new Color(136, 148, 170));
                    if (bool || mouseon == 1) {
                        rd.drawRect(57, 90, 147, 8);
                        rd.setColor(new Color(0, 0, 0));
                    }
                    rd.drawLine(57, 94, 204, 94);
                    if (mouseon == 1) {
                        pwd = (xm - 57) / 36.75F + 2.0F;
                        if (pwd < 2.0F) {
                            pwd = 2.0F;
                        }
                        if (pwd > 6.0F) {
                            pwd = 6.0F;
                        }
                    }
                    rd.drawRect((int) (57.0F + (pwd - 2.0F) * 36.75F), 90, 2, 8);
                    boolean bool59 = false;
                    if (xm > 57 && xm < 204 && ym > 110 && ym < 119) {
                        bool59 = true;
                    }
                    rd.setColor(new Color(136, 148, 170));
                    if (bool59 || mouseon == 2) {
                        rd.drawRect(57, 110, 147, 8);
                        rd.setColor(new Color(0, 0, 0));
                    }
                    rd.drawLine(57, 114, 204, 114);
                    if (mouseon == 2) {
                        phd = (xm - 57) / 36.75F + 2.0F;
                        if (phd < 2.0F) {
                            phd = 2.0F;
                        }
                        if (phd > 6.0F) {
                            phd = 6.0F;
                        }
                    }
                    rd.drawRect((int) (57.0F + (phd - 2.0F) * 36.75F), 110, 2, 8);
                    if (mousePressed == 1) {
                        if (bool) {
                            mouseon = 1;
                        }
                        if (bool59) {
                            mouseon = 2;
                        }
                    } else {
                        if (mouseon == 1 || mouseon == 2) {
                            pgen = false;
                        }
                        mouseon = -1;
                    }
                }
                int i60 = 0;
                if (xm > 482 && xm < 529 && ym > 35 && ym < 61 || up) {
                    i60 = 1;
                    if (mousePressed == 1 || up) {
                        sz += 500;
                    }
                }
                rd.drawImage(su[i60], 482, 35, null);
                i60 = 0;
                if (xm > 482 && xm < 529 && ym > 519 && ym < 545 || down) {
                    i60 = 1;
                    if (mousePressed == 1 || down) {
                        sz -= 500;
                    }
                }
                rd.drawImage(sd[i60], 482, 519, null);
                i60 = 0;
                if (xm > 220 && xm < 246 && ym > 264 && ym < 311 || left) {
                    i60 = 1;
                    if (mousePressed == 1 || left) {
                        sx -= 500;
                    }
                }
                rd.drawImage(sl[i60], 220, 264, null);
                i60 = 0;
                if (xm > 764 && xm < 790 && ym > 264 && ym < 311 || right) {
                    i60 = 1;
                    if (mousePressed == 1 || right) {
                        sx += 500;
                    }
                }
                rd.drawImage(sr[i60], 764, 264, null);
                i60 = 0;
                if (xm > 616 && xm < 677 && ym > 30 && ym < 61 || zoomi) {
                    i60 = 1;
                    if (mousePressed == 1 || zoomi) {
                        sy += 500;
                        if (sy > -2500) {
                            sy = -2500;
                        }
                    }
                }
                rd.drawImage(zi[i60], 616, 30, null);
                i60 = 0;
                if (xm > 690 && xm < 751 && ym > 30 && ym < 61 || zoomo) {
                    i60 = 1;
                    if (mousePressed == 1 || zoomo) {
                        sy -= 500;
                        if (sy < -55000) {
                            sy = -55000;
                        }
                    }
                }
                rd.drawImage(zo[i60], 690, 30, null);
                if ((epart || arrng) && sy < -36000) {
                    sy = -36000;
                }
                rd.setFont(new Font("Arial", 1, 11));
                ftm = rd.getFontMetrics();
                rd.setColor(new Color(0, 0, 0));
                rd.drawString("Part Selection", 11, 47);
                rd.setFont(new Font("Arial", 1, 13));
                ftm = rd.getFontMetrics();
                ptyp.move(10, 50);
                if (!ptyp.isShowing()) {
                    ptyp.setVisible(true);
                    ptyp.select(selectedPartType);
                }
                if (selectedPartType != ptyp.getSelectedIndex()) {
                    selectedPartType = ptyp.getSelectedIndex();
                    if (selectedPartType == PART_ROADS) {
                        partroads();
                        part.setVisible(true);
                    }
                    if (selectedPartType == PART_RAMPS) {
                        partramps();
                        part.setVisible(true);
                    }
                    if (selectedPartType == PART_OBSTACLES) {
                        partobst();
                        part.setVisible(true);
                    }
                    if (selectedPartType == PART_TREES) {
                        partrees();
                        part.setVisible(true);
                    }
                    if (selectedPartType == PART_CUSTOM) {
                        partcustom();
                        part.setVisible(true);
                    }
                    selectedMenuPart = 0;
                    part.select(selectedMenuPart);
                    requestFocus();
                    fixh.setText("2000");
                    focuson = false;
                }
                part.move(10, 80);
                part.setSize(200);
                if (selectedPartType == PART_ROADS || selectedPartType == PART_RAMPS || selectedPartType == PART_OBSTACLES || selectedPartType == PART_TREES || selectedPartType == PART_CUSTOM) {
                    if (!part.isShowing()) {
                        part.setVisible(true);
                        part.select(selectedMenuPart);
                    }
                } else if (part.isShowing()) {
                    part.setVisible(false);
                }
                if (selectedMenuPart != part.getSelectedIndex()) {
                    selectedMenuPart = part.getSelectedIndex();
                    focuson = false;
                }
                if (selectedPartType == PART_CHECKPOINTS) {
                    rd.drawString("Checkpoint", 110 - ftm.stringWidth("Checkpoint") / 2, 120);
                }
                if (selectedPartType == PART_FIXHOOPS) {
                    rd.drawString("Fixing Hoop", 110 - ftm.stringWidth("Fixing Hoop") / 2, 120);
                }
                if (lsp != selectedPart) {
                    seq = 3;
                    bco[selectedPart].xy = 0;
                    bco[selectedPart].zy = 0;
                    lsp = selectedPart;
                    epart = false;
                    arrng = false;
                }
                if (xm > 10 && xm < 210 && ym > 130 && ym < 334) {
                    if (seq >= 3)
                        if (seq == 20 || !seqn) {
                            seq = 0;
                            bco[selectedPart].xy = 0;
                            bco[selectedPart].zy = 0;
                        } else {
                            seq++;
                        }
                    seqn = true;
                    rd.setColor(new Color(210, 210, 210));
                } else {
                    rd.setColor(new Color(200, 200, 200));
                    seqn = false;
                }
                rd.fillRect(10, 130, 200, 200);
                if ((selectedPart == 30 || selectedPart == 32 || selectedPart == 54) && button("  Rearrange Checkpoints  >  ", 110, 315, 2, true)) {
                    mousePressed = 0;
                    epart = false;
                    if (!arrng) {
                        arrcnt = 0;
                        for (int i61 = 0; i61 < nob; i61++)
                            if (co[i61].colok == 30 || co[i61].colok == 32 || co[i61].colok == 54) {
                                co[i61].errd = false;
                            }
                        arrng = true;
                    } else {
                        arrng = false;
                    }
                }
                if (seqn && mousePressed == -1)
                    if (selectedPart != bumppart) {
                        boolean bool = false;
                        if (rot == 0 && !bool) {
                            rot = 90;
                            bool = true;
                        }
                        if (rot == 90 && !bool) {
                            rot = 180;
                            bool = true;
                        }
                        if (rot == 180 && !bool) {
                            rot = -90;
                            bool = true;
                        }
                        if (rot == -90 && !bool) {
                            rot = 0;
                        }
                        if (selectedPart == 30 || selectedPart == 31 || selectedPart == 32) {
                            if (rot == -90) {
                                rot = 90;
                            }
                            if (rot == 180) {
                                rot = 0;
                            }
                        }
                        seq = 5;
                        bco[selectedPart].xy = 0;
                        bco[selectedPart].zy = 0;
                        epart = false;
                        arrng = false;
                    } else {
                        pgen = false;
                        pwd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                        phd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                    }
                if (selectedPart == 31) {
                    rd.setFont(new Font("Arial", 1, 12));
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Height:", 62, 280);
                    movefield(fixh, 107, 266, 50, 20);
                    if (fixh.hasFocus()) {
                        focuson = false;
                    }
                    if (!fixh.isShowing()) {
                        fixh.setVisible(true);
                    }
                    rd.setFont(new Font("Arial", 0, 11));
                    ftm = rd.getFontMetrics();
                    rd.drawString("(Height off the ground... )", 110 - ftm.stringWidth("(Height off the ground... )") / 2, 300);
                    if (fixh.getText().equals("")) {
                        fixh.setText("0");
                        fixh.select(0, 0);
                    }
                    try {
                        hf = Integer.parseInt(fixh.getText());
                        if (hf > 8000) {
                            hf = 8000;
                            fixh.setText("8000");
                        }
                    } catch (final Exception exception) {
                        hf = 2000;
                        fixh.setText("2000");
                    }
                } else if (fixh.isShowing()) {
                    fixh.setVisible(false);
                }
                // CAMERA POSITION FOR THE CONTO PREVIEW SHIT
                Medium.trk = 2;
                Medium.zy = 90;
                Medium.xz = 0;
                Medium.iw = 10;
                Medium.w = 210;
                Medium.ih = 130;
                Medium.h = 330;
                Medium.cx = 110;
                Medium.cy = 230;
                Medium.x = -110;
                Medium.z = -230;
                Medium.y = -15000;
                if (selectedPartType == PART_RAMPS && selectedPart != 20 && selectedPart != 21 && selectedPart != 43 && selectedPart != 45) {
                    Medium.y = -10000;
                }
                if (selectedPartType == PART_OBSTACLES && selectedPart != 41) {
                    Medium.y = -7600;
                }
                if (selectedPartType == PART_CHECKPOINTS || selectedPartType == PART_FIXHOOPS) {
                    Medium.y = -5000;
                }
                if (selectedPartType == PART_TREES) {
                    Medium.y = -3000;
                    Medium.z = 150;
                }
                if (selectedPartType == PART_BUMP) {
                    Medium.y = -7600;
                }
                if (selectedPart == 31) {
                    Medium.z = -500;
                    bco[selectedPart].roted = rot != 0;
                }
                bco[selectedPart].x = 0;
                bco[selectedPart].y = 0;
                bco[selectedPart].z = 0;
                bco[selectedPart].xz = rot + adrot;
                bco[selectedPart].d(rd);
                int i62 = 1;
                if (selectedPartType == PART_ROADS || selectedPartType == PART_RAMPS || selectedPartType == PART_CUSTOM) {
                    if (selectedPart != 26 && selectedPart != 20) {
                        if (rot == -90 || rot == 0) {
                            i62 = -1;
                        }
                    } else {
                        if (selectedPart == 26 && (rot == -90 || rot == 180)) {
                            i62 = -1;
                        }
                        if (selectedPart == 20 && (rot == 90 || rot == 180)) {
                            i62 = -1;
                        }
                    }
                    if (seq == 2) {
                        bco[selectedPart].xy -= 5 * i62;
                        if (bco[selectedPart].xy == 0) {
                            seq = 3;
                        }
                    }
                    if (seq == 1) {
                        seq = 2;
                    }
                    if (seq == 0) {
                        bco[selectedPart].xy += 5 * i62;
                        if (bco[selectedPart].xy == 85 * i62) {
                            seq = 1;
                        }
                    }
                }
                if (selectedPartType == PART_OBSTACLES || selectedPartType == PART_CHECKPOINTS || selectedPartType == PART_FIXHOOPS || selectedPartType == PART_BUMP) {
                    if (rot == -90 || rot == 180) {
                        i62 = -1;
                    }
                    if (seq == 2) {
                        bco[selectedPart].zy += 5 * i62;
                        if (bco[selectedPart].zy == 0) {
                            seq = 3;
                        }
                    }
                    if (seq == 1) {
                        seq = 2;
                    }
                    if (seq == 0) {
                        bco[selectedPart].zy -= 5 * i62;
                        if (bco[selectedPart].zy == -(85 * i62)) {
                            seq = 1;
                        }
                    }
                }
                if (selectedPartType == PART_TREES) {
                    if (rot == -90 || rot == 180) {
                        i62 = -1;
                    }
                    boolean bool = false;
                    if (rot == -90 || rot == 90) {
                        bool = true;
                    }
                    if (!bool) {
                        bco[selectedPart].xy = 0;
                    } else {
                        bco[selectedPart].zy = 0;
                    }
                    if (seq == 2)
                        if (!bool) {
                            bco[selectedPart].zy += 5 * i62;
                            if (bco[selectedPart].zy == 0) {
                                seq = 3;
                            }
                        } else {
                            bco[selectedPart].xy -= 5 * i62;
                            if (bco[selectedPart].xy == 0) {
                                seq = 3;
                            }
                        }
                    if (seq == 1) {
                        seq = 2;
                    }
                    if (seq == 0)
                        if (!bool) {
                            bco[selectedPart].zy -= 5 * i62;
                            if (bco[selectedPart].zy == -(85 * i62)) {
                                seq = 1;
                            }
                        } else {
                            bco[selectedPart].xy += 5 * i62;
                            if (bco[selectedPart].xy == 85 * i62) {
                                seq = 1;
                            }
                        }
                }
                if (selectedPart != bumppart) {
                    if (button("  Rotate  ", 110, 348, 3, true)) {
                        boolean bool = false;
                        if (rot == 0 && !bool) {
                            rot = 90;
                            bool = true;
                        }
                        if (rot == 90 && !bool) {
                            rot = 180;
                            bool = true;
                        }
                        if (rot == 180 && !bool) {
                            rot = -90;
                            bool = true;
                        }
                        if (rot == -90 && !bool) {
                            rot = 0;
                        }
                        if (selectedPart == 30 || selectedPart == 31 || selectedPart == 32) {
                            if (rot == -90) {
                                rot = 90;
                            }
                            if (rot == 180) {
                                rot = 0;
                            }
                        }
                        seq = 3;
                        bco[selectedPart].xy = 0;
                        bco[selectedPart].zy = 0;
                        epart = false;
                        arrng = false;
                    }
                } else if (button("  Generate New  ", 110, 348, 3, true)) {
                    pgen = false;
                    pwd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                    phd = 2L + Math.round(ThreadLocalRandom.current().nextDouble() * 4.0);
                }
                if (button(">", 191, 348, 3, true) && (selectedPartType == PART_ROADS || selectedPartType == PART_RAMPS || selectedPartType == PART_OBSTACLES || selectedPartType == PART_TREES || selectedPartType == PART_CUSTOM)) {
                    selectedMenuPart++;
                    if (selectedMenuPart == part.getItemCount()) {
                        selectedMenuPart = 0;
                    }
                    part.select(selectedMenuPart);
                    epart = false;
                    arrng = false;
                }
                if (button("<", 28, 348, 3, true) && (selectedPartType == PART_ROADS || selectedPartType == PART_RAMPS || selectedPartType == PART_OBSTACLES || selectedPartType == PART_TREES || selectedPartType == PART_CUSTOM)) {
                    selectedMenuPart--;
                    if (selectedMenuPart == -1) {
                        selectedMenuPart = part.getItemCount() - 1;
                    }
                    part.select(selectedMenuPart);
                    epart = false;
                    arrng = false;
                }
                if (button("   <  Undo   ", 204, 404, 0, true)) {
                    epart = false;
                    arrng = false;
                    if (nundo > 0) {
                        nundo--;
                        bstage = undos[nundo];
                        readstage(0);
                    }
                }
                if (button("   Remove / Edit  Part   ", 172, 454, 0, true)) {
                    epart = !epart;
                    arrng = false;
                    esp = -1;
                }
                if (button("   Go to >  Startline   ", 175, 504, 0, true)) {
                    sx = 0;
                    sz = 1500;
                }
                if (button(" About Part ", 164, 66, 3, false)) {
                    JOptionPane.showMessageDialog(null, discp[selectedPart], "Stage Maker", 1);
                }
                if (button("  Keyboard Controls  ", 691, 536, 3, false)) {
                    JOptionPane.showMessageDialog(null, "Instead of clicking the triangular buttons around the Building Area to scroll, you can use:\n[ Keyboard Arrows ]\n\nYou can also zoom in and out using the following keys:\n[+] & [-]  or  [8] & [2]  or  [Enter] & [Backspace]\n\n", "Stage Maker", 1);
                }
                if (button("  Save  ", 280, 50, 0, false)) {
                    epart = false;
                    arrng = false;
                    savefile();
                }
                if (button("  Save & Preview  ", 380, 50, 0, false)) {
                    epart = false;
                    arrng = false;
                    savefile();
                    hidefields();
                    tab = 2;
                }
                rd.setFont(new Font("Arial", 1, 12));
                ftm = rd.getFontMetrics();
                rd.setColor(new Color(0, 0, 0));
                int i63 = 0;
                final int i64 = (int) (xnob / 10000.0F * 200.0F); // limits
                int i65 = i64;
                final int i66 = (int) (Trackers.nt / 670000.0F * 200.0F); // limits
                if (i66 > i65) {
                    i65 = i66;
                    i63 = 1;
                }
                final int i67 = (int) (CheckPoints.n / 10000.0F * 200.0F); // limits
                if (i67 > i65) {
                    i65 = i67;
                    i63 = 2;
                }
                final int i68 = (int) (Medium.nrw * Medium.ncl / 9999999.0F * 200.0F); // medium
                // limit...does
                // it
                // exist?
                if (i68 > i65) {
                    i65 = i68;
                    i63 = 3;
                }
                if (i65 > 200) {
                    i65 = 200;
                }
                if (i65 <= 100) {
                    rd.setColor(new Color(100 + i65, 225, 30));
                } else {
                    rd.setColor(new Color(200, 325 - i65, 30));
                }
                rd.fillRect(167, 531, i65, 9);
                if (button("Memory Consumption :", 85, 540, 3, false)) {
                    JOptionPane.showMessageDialog(null, "Memory Consumption Details\n\nNumber of Parts:  " + i64 / 2 + " %\nPart's Details:  " + i66 / 2 + " %\nRoad Points:  " + i67 / 2 + " %\nStage Area:  " + i68 / 2 + " %\n \n", "Stage Maker", 1);
                }
                rd.setColor(new Color(0, 0, 0));
                rd.drawRect(167, 531, 200, 9);
                final String[] strings = {
                        "Number of Parts", "Part's Details", "Road Points", "Stage Area"
                };
                rd.drawString(strings[i63], 267 - ftm.stringWidth(strings[i63]) / 2, 540);
                rd.drawString("" + i65 / 2 + " %  used", 375, 540);
                if (overcan) {
                    overcan = false;
                }
                if (epart) {
                    if (esp == -1) {
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString("Click on any part to Edit >", 257, 454);
                        if (button(" Cancel ", 323, 474, 4, false)) {
                            epart = false;
                        }
                    }
                } else {
                    if (hi != -1) {
                        hi = -1;
                    }
                    if (esp != -1) {
                        esp = -1;
                    }
                }
                if (arrng) {
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Click on Checkpoint NO# " + (arrcnt + 1) + "  >", 257, 80);
                    if (button(" Cancel ", 330, 100, 4, false)) {
                        arrng = false;
                    }
                    if (arrcnt == CheckPoints.nsp) {
                        sortstage();
                        JOptionPane.showMessageDialog(null, "Checkpoints Arranged!\nPress Save and Test Drive to check the new checkpoint order.\n", "Stage Maker", 1);
                        arrng = false;
                    }
                } else if (chi != -1) {
                    chi = -1;
                }
            }
            if (tab == 2) {
                if (tabed != tab) {
                    Medium.trk = 0;
                    readstage(1);
                    setCursor(new Cursor(0));
                    setcur = false;
                    vxz = 0;
                    vx = sx - 400;
                    vz = sz - Medium.cz - 8000;
                    vy = -1500;
                    dtabed = -1;
                }
                Medium.trk = 0;
                Medium.zy = 6;
                Medium.iw = 10;
                Medium.w = 790;
                Medium.ih = 35;
                Medium.h = 445;
                Medium.cx = 400;
                Medium.cy = 215;
                Medium.xz = vxz;
                Medium.x = vx;
                Medium.z = vz;
                Medium.y = vy;
                Medium.d(rd);
                int i = 0;
                final int[] is = new int[10000]; // stageselect limit
                for (int i69 = 0; i69 < nob; i69++)
                    if (co[i69].dist != 0) {
                        is[i] = i69;
                        i++;
                    } else {
                        co[i69].d(rd);
                    }
                final int[] is70 = new int[i];
                for (int i71 = 0; i71 < i; i71++) {
                    is70[i71] = 0;
                }
                for (int i72 = 0; i72 < i; i72++) {
                    for (int i73 = i72 + 1; i73 < i; i73++)
                        if (co[is[i72]].dist != co[is[i73]].dist) {
                            if (co[is[i72]].dist < co[is[i73]].dist) {
                                is70[i72]++;
                            } else {
                                is70[i73]++;
                            }
                        } else if (i73 > i72) {
                            is70[i72]++;
                        } else {
                            is70[i73]++;
                        }
                }
                for (int i74 = 0; i74 < i; i74++) {
                    for (int i75 = 0; i75 < i; i75++)
                        if (is70[i75] == i74) {
                            if (is[i75] == hi) {
                                Medium.trk = 3;
                            }
                            co[is[i75]].d(rd);
                            if (Medium.trk == 3) {
                                Medium.trk = 2;
                            }
                        }
                }
                if (up) {
                    vz += 500.0F * Medium.cos(Medium.xz);
                    vx += 500.0F * Medium.sin(Medium.xz);
                }
                if (down) {
                    vz -= 500.0F * Medium.cos(Medium.xz);
                    vx -= 500.0F * Medium.sin(Medium.xz);
                }
                if (left) {
                    vxz -= 5;
                }
                if (right) {
                    vxz += 5;
                }
                if (zoomi) {
                    vy += 100;
                    if (vy > -500) {
                        vy = -500;
                    }
                }
                if (zoomo) {
                    vy -= 100;
                    if (vy < -5000) {
                        vy = -5000;
                    }
                }
                rd.setColor(new Color(225, 225, 225));
                rd.fillRect(0, 25, 10, 525);
                rd.fillRect(790, 25, 10, 525);
                rd.fillRect(10, 25, 780, 10);
                rd.fillRect(10, 445, 780, 105);
                rd.setFont(new Font("Arial", 1, 12));
                ftm = rd.getFontMetrics();
                final String[] strings = {
                        "Controls", "Atmosphere", "Colors", "Scenery", "Laps", "Sound Track", "Test Drive"
                };
                final int[] is76 = {
                        10, 10, 121, 111
                };
                final int[] is77 = {
                        425, 445, 445, 425
                };
                for (int i78 = 0; i78 < 7; i78++) {
                    rd.setColor(new Color(170, 170, 170));
                    if (xm > is76[0] && xm < is76[3] && ym > 425 && ym < 445) {
                        rd.setColor(new Color(190, 190, 190));
                    }
                    if (dtab == i78) {
                        rd.setColor(new Color(225, 225, 225));
                    }
                    rd.fillPolygon(is76, is77, 4);
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString(strings[i78], i78 * 111 + 62 - ftm.stringWidth(strings[i78]) / 2, 439);
                    if (xm > is76[0] && xm < is76[3] && ym > 425 && ym < 445 && mousePressed == -1 && mouseon == -1) {
                        dtab = i78;
                    }
                    for (int i79 = 0; i79 < 4; i79++) {
                        is76[i79] += 111;
                    }
                }
                if (tabed == tab && dtab != dtabed) {
                    if (!ttstage.equals("")) {
                        tstage = ttstage;
                        ttstage = "";
                    }
                    readstage(1);
                    hidefields();
                }
                if (dtab == 0) {
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Use the [ Keyboard Arrows ] to navigate through the stage.", 20, 470);
                    rd.drawString("[Left] & [Right] arrows are for rotating.  [Up] & [Down] arrows are for moving forwards and backwards.", 20, 490);
                    rd.drawString("For moving vertically down and up use the following keys:  [+] & [-]  or  [8] & [2]  or  [Enter] & [Backspace]", 20, 520);
                }
                if (dtab == 2) {
                    if (dtabed != dtab) {
                        Color.RGBtoHSB(csky[0], csky[1], csky[2], hsb[0]);
                        Color.RGBtoHSB(cfade[0], cfade[1], cfade[2], hsb[1]);
                        Color.RGBtoHSB(cgrnd[0], cgrnd[1], cgrnd[2], hsb[2]);
                        for (int i80 = 0; i80 < 3; i80++) {
                            final float f = hsb[i80][1];
                            hsb[i80][1] = hsb[i80][2];
                            hsb[i80][2] = f;
                        }
                        if (hsb[1][1] == (hsb[0][1] + hsb[2][1]) / 2.0F && hsb[1][0] == hsb[2][0] && hsb[1][2] == hsb[2][2]) {
                            pfog.setState(true);
                        } else {
                            pfog.setState(false);
                        }
                        ttstage = "";
                        mouseon = -1;
                    }
                    if (mousePressed != 1) {
                        if ((mouseon >= 6 || mouseon < 3) && mouseon != -1) {
                            if (ttstage.equals("")) {
                                ttstage = tstage;
                            }
                            sortop();
                            readstage(1);
                        }
                        mouseon = -1;
                    }
                    final String[] strings81 = {
                            "Sky", "Dust / Fog", "Ground"
                    };
                    for (int i82 = 0; i82 < 3; i82++) {
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString(strings81[i82], 107 + 195 * i82 - ftm.stringWidth(strings81[i82]) / 2, 461);
                        for (int i83 = 0; i83 < 150; i83++) {
                            rd.setColor(Color.getHSBColor((float) (i83 * 0.006667), 1.0F, 1.0F));
                            rd.drawLine(32 + i83 + 195 * i82, 467, 32 + i83 + 195 * i82, 474);
                        }
                        for (int i84 = 0; i84 < 150; i84++) {
                            rd.setColor(Color.getHSBColor(0.0F, 0.0F, 0.5F + i84 * 0.00333F));
                            rd.drawLine(32 + i84 + 195 * i82, 483, 32 + i84 + 195 * i82, 490);
                        }
                        for (int i85 = 0; i85 < 150; i85++) {
                            rd.setColor(Color.getHSBColor(hsb[i82][0], 0.0F + (float) (i85 * 0.001667), hsb[i82][1]));
                            rd.drawLine(32 + i85 + 195 * i82, 499, 32 + i85 + 195 * i82, 506);
                        }
                        for (int i86 = 0; i86 < 3; i86++) {
                            rd.setColor(new Color(0, 0, 0));
                            float f = hsb[i82][i86] * 150.0F;
                            if (i86 == 1) {
                                float f87 = 0.75F;
                                if (i82 == 0) {
                                    f87 = 0.85F;
                                }
                                if (i82 == 1) {
                                    f87 = 0.8F;
                                }
                                f = (hsb[i82][i86] - f87) / 0.001F;
                            }
                            if (i86 == 2) {
                                f = hsb[i82][i86] * 600.0F;
                            }
                            if (f < 0.0F) {
                                f = 0.0F;
                            }
                            if (f > 150.0F) {
                                f = 150.0F;
                            }
                            rd.drawLine((int) (32 + 195 * i82 + f), 467 + i86 * 16, (int) (32 + 195 * i82 + f), 474 + i86 * 16);
                            rd.drawLine((int) (33 + 195 * i82 + f), 467 + i86 * 16, (int) (33 + 195 * i82 + f), 474 + i86 * 16);
                            rd.fillRect((int) (31 + 195 * i82 + f), 475 + i86 * 16, 4, 2);
                            rd.drawLine((int) (30 + 195 * i82 + f), 477 + i86 * 16, (int) (35 + 195 * i82 + f), 477 + i86 * 16);
                            if (xm > 29 + 195 * i82 && xm < 185 + 195 * i82 && ym > 468 + i86 * 16 && ym < 477 + i86 * 16 && mousePressed == 1 && mouseon == -1) {
                                mouseon = i86 + i82 * 3;
                            }
                            if (mouseon == i86 + i82 * 3) {
                                if (i86 == 0) {
                                    hsb[i82][i86] = (xm - (32 + 195 * i82)) / 150.0F;
                                }
                                if (i86 == 1) {
                                    float f88 = 0.75F;
                                    if (i82 == 0) {
                                        f88 = 0.85F;
                                    }
                                    if (i82 == 1) {
                                        f88 = 0.8F;
                                    }
                                    hsb[i82][i86] = f88 + (xm - (32 + 195 * i82)) * 0.001F;
                                    if (hsb[i82][i86] < f88) {
                                        hsb[i82][i86] = f88;
                                    }
                                    if (hsb[i82][i86] > f88 + 0.15F) {
                                        hsb[i82][i86] = f88 + 0.15F;
                                    }
                                }
                                if (i86 == 2) {
                                    hsb[i82][i86] = (xm - (32 + 195 * i82)) / 600.0F;
                                    if (hsb[i82][i86] > 0.25) {
                                        hsb[i82][i86] = 0.25F;
                                    }
                                }
                                if (hsb[i82][i86] > 1.0F) {
                                    hsb[i82][i86] = 1.0F;
                                }
                                if (hsb[i82][i86] < 0.0F) {
                                    hsb[i82][i86] = 0.0F;
                                }
                            }
                        }
                    }
                    movefield(pfog, 258, 511, 200, 23);
                    if (!pfog.isShowing()) {
                        pfog.setVisible(true);
                    }
                    if (pfog.getState()) {
                        rd.setComposite(AlphaComposite.getInstance(3, 0.25F));
                        rd.setColor(new Color(0, 0, 0));
                        rd.fillRect(215, 464, 175, 47);
                        rd.setComposite(AlphaComposite.getInstance(3, 1.0F));
                        hsb[1][1] = (hsb[0][1] + hsb[2][1]) / 2.0F;
                        hsb[1][0] = hsb[2][0];
                        hsb[1][2] = hsb[2][2];
                    }
                    Color color = Color.getHSBColor(hsb[0][0], hsb[0][2], hsb[0][1]);
                    Medium.setsky(color.getRed(), color.getGreen(), color.getBlue());
                    csky[0] = color.getRed();
                    csky[1] = color.getGreen();
                    csky[2] = color.getBlue();
                    color = Color.getHSBColor(hsb[1][0], hsb[1][2], hsb[1][1]);
                    Medium.setfade(color.getRed(), color.getGreen(), color.getBlue());
                    cfade[0] = color.getRed();
                    cfade[1] = color.getGreen();
                    cfade[2] = color.getBlue();
                    color = Color.getHSBColor(hsb[2][0], hsb[2][2], hsb[2][1]);
                    Medium.setgrnd(color.getRed(), color.getGreen(), color.getBlue());
                    cgrnd[0] = color.getRed();
                    cgrnd[1] = color.getGreen();
                    cgrnd[2] = color.getBlue();
                    if (button(" Reset ", 650, 510, 0, true)) {
                        if (!ttstage.equals("")) {
                            tstage = ttstage;
                            ttstage = "";
                        }
                        readstage(1);
                        dtabed = -2;
                    }
                    if (button("        Save        ", 737, 510, 0, true)) {
                        sortop();
                        ttstage = "";
                        savefile();
                    }
                }
                if (dtab == 3) {
                    if (dtabed != dtab) {
                        Color.RGBtoHSB(cldd[0], cldd[1], cldd[2], hsb[0]);
                        Color.RGBtoHSB(texture[0], texture[1], texture[2], hsb[1]);
                        mgen.setText("" + Medium.mgen + "");
                        mouseon = -1;
                        ttstage = "";
                    }
                    if (mousePressed != 1) {
                        if (mouseon == 0 || mouseon == 1 || mouseon == 2 || mouseon == 6) {
                            if (ttstage.equals("")) {
                                ttstage = tstage;
                            }
                            sortop();
                            readstage(1);
                        }
                        mouseon = -1;
                    }
                    rd.setFont(new Font("Arial", 1, 12));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Clouds", 32, 461);
                    for (int i89 = 0; i89 < 150; i89++) {
                        rd.setColor(Color.getHSBColor(i89 * 0.006667F, 1.0F, 1.0F));
                        rd.drawLine(32 + i89, 467, 32 + i89, 474);
                    }
                    for (int i90 = 0; i90 < 150; i90++) {
                        rd.setColor(Color.getHSBColor(0.0F, 0.0F, 0.75F + i90 * 0.001667F));
                        rd.drawLine(32 + i90, 483, 32 + i90, 490);
                    }
                    for (int i91 = 0; i91 < 150; i91++) {
                        rd.setColor(Color.getHSBColor(hsb[0][0], i91 * 0.003333F, hsb[0][2]));
                        rd.drawLine(32 + i91, 499, 32 + i91, 506);
                    }
                    rd.setFont(new Font("Arial", 0, 11));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Blend:", 32, 529);
                    rd.setColor(new Color(0, 0, 0));
                    rd.fillRect(70, 522, 112, 2);
                    rd.fillRect(70, 528, 112, 2);
                    float f = 0.0F;
                    int i92;
                    for (int i93 = 0; i93 < 112; i93++) {
                        i92 = (int) (255.0F / (f + 1.0F));
                        if (i92 > 255) {
                            i92 = 255;
                        }
                        if (i92 < 0) {
                            i92 = 0;
                        }
                        f += 0.02F;
                        rd.setColor(new Color(i92, i92, i92));
                        rd.drawLine(70 + i93, 524, 70 + i93, 527);
                    }
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Height", 202 - ftm.stringWidth("Height") / 2, 461);
                    rd.drawLine(202, 467, 202, 530);
                    for (int i94 = 0; i94 < 8; i94++) {
                        rd.drawLine(202, 466 + i94 * 8, 202 + 8 - i94, 466 + i94 * 8);
                    }
                    rd.setFont(new Font("Arial", 1, 12));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Ground Texture", 257, 471);
                    for (int i95 = 0; i95 < 150; i95++) {
                        rd.setColor(Color.getHSBColor(i95 * 0.006667F, 1.0F, 1.0F));
                        rd.drawLine(32 + i95 + 225, 477, 32 + i95 + 225, 484);
                    }
                    for (int i96 = 0; i96 < 150; i96++) {
                        rd.setColor(Color.getHSBColor(hsb[1][0], i96 * 0.006667F, i96 * 0.006667F));
                        rd.drawLine(32 + i96 + 225, 493, 32 + i96 + 225, 500);
                    }
                    rd.setFont(new Font("Arial", 0, 11));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Blend:", 257, 523);
                    rd.setColor(new Color(0, 0, 0));
                    rd.fillRect(295, 516, 112, 2);
                    rd.fillRect(295, 522, 112, 2);
                    f = 0.0F;
                    for (int i97 = 0; i97 < 112; i97++) {
                        i92 = (int) (255.0F / (f + 1.0F));
                        if (i92 > 255) {
                            i92 = 255;
                        }
                        if (i92 < 0) {
                            i92 = 0;
                        }
                        f += 0.02F;
                        rd.setColor(new Color(i92, i92, i92));
                        rd.drawLine(70 + i97 + 225, 518, 70 + i97 + 225, 521);
                    }
                    for (int i98 = 0; i98 < 2; i98++) {
                        int i99 = 3;
                        if (i98 == 1) {
                            i99 = 2;
                        }
                        for (int i100 = 0; i100 < i99; i100++) {
                            int i101 = i100;
                            if (i100 == 1) {
                                i101 = 2;
                            }
                            if (i100 == 2) {
                                i101 = 1;
                            }
                            rd.setColor(new Color(0, 0, 0));
                            float f102 = hsb[i98][i101] * 150.0F;
                            if (i100 == 1 && i98 == 0) {
                                final float f103 = 0.75F;
                                f102 = (hsb[i98][i101] - f103) / 0.001667F;
                            }
                            if (i100 == 2 && i98 == 0) {
                                f102 = hsb[i98][i101] / 0.003333F;
                            }
                            if (f102 < 0.0F) {
                                f102 = 0.0F;
                            }
                            if (f102 > 150.0F) {
                                f102 = 150.0F;
                            }
                            rd.drawLine((int) (32 + 225 * i98 + f102), 467 + i100 * 16 + 10 * i98, (int) (32 + 225 * i98 + f102), 474 + i100 * 16 + 10 * i98);
                            rd.drawLine((int) (33 + 225 * i98 + f102), 467 + i100 * 16 + 10 * i98, (int) (33 + 225 * i98 + f102), 474 + i100 * 16 + 10 * i98);
                            rd.fillRect((int) (31 + 225 * i98 + f102), 475 + i100 * 16 + 10 * i98, 4, 2);
                            rd.drawLine((int) (30 + 225 * i98 + f102), 477 + i100 * 16 + 10 * i98, (int) (35 + 225 * i98 + f102), 477 + i100 * 16 + 10 * i98);
                            if (xm > 29 + 225 * i98 && xm < 185 + 225 * i98 && ym > 468 + i100 * 16 + 10 * i98 && ym < 477 + i100 * 16 + 10 * i98 && mousePressed == 1 && mouseon == -1) {
                                mouseon = i100 + i98 * 3;
                            }
                            if (mouseon == i100 + i98 * 3) {
                                hsb[i98][i101] = (xm - (32 + 225 * i98)) * 0.006667F;
                                if (i100 == 1 && i98 == 1) {
                                    hsb[i98][1] = (xm - (32 + 225 * i98)) * 0.006667F;
                                    if (hsb[i98][1] > 1.0F) {
                                        hsb[i98][1] = 1.0F;
                                    }
                                    if (hsb[i98][1] < 0.0F) {
                                        hsb[i98][1] = 0.0F;
                                    }
                                }
                                if (i100 == 1 && i98 == 0) {
                                    final float f104 = 0.75F;
                                    hsb[i98][i101] = f104 + (xm - (32 + 225 * i98)) * 0.001667F;
                                    if (hsb[i98][i101] < f104) {
                                        hsb[i98][i101] = f104;
                                    }
                                }
                                if (i100 == 2 && i98 == 0) {
                                    hsb[i98][i101] = (xm - (32 + 225 * i98)) * 0.003333F;
                                    if (hsb[i98][i101] > 0.5) {
                                        hsb[i98][i101] = 0.5F;
                                    }
                                }
                                if (hsb[i98][i101] > 1.0F) {
                                    hsb[i98][i101] = 1.0F;
                                }
                                if (hsb[i98][i101] < 0.0F) {
                                    hsb[i98][i101] = 0.0F;
                                }
                            }
                        }
                        rd.setColor(new Color(0, 0, 0));
                        float f105 = (texture[3] - 20) * 2.8F;
                        if (i98 == 0) {
                            f105 = cldd[3] * 11.2F;
                        }
                        if (f105 < 0.0F) {
                            f105 = 0.0F;
                        }
                        if (f105 > 112.0F) {
                            f105 = 112.0F;
                        }
                        rd.drawLine((int) (70 + 225 * i98 + f105), 522 - 6 * i98, (int) (70 + 225 * i98 + f105), 529 - 6 * i98);
                        rd.drawLine((int) (71 + 225 * i98 + f105), 522 - 6 * i98, (int) (71 + 225 * i98 + f105), 529 - 6 * i98);
                        rd.fillRect((int) (69 + 225 * i98 + f105), 530 - 6 * i98, 4, 2);
                        rd.drawLine((int) (68 + 225 * i98 + f105), 532 - 6 * i98, (int) (73 + 225 * i98 + f105), 532 - 6 * i98);
                        if (xm > 67 + 225 * i98 && xm < 185 + 225 * i98 && ym > 522 - 6 * i98 && ym < 532 - 6 * i98 && mousePressed == 1 && mouseon == -1) {
                            mouseon = 6 + i98;
                        }
                    }
                    if (mouseon == 6) {
                        cldd[3] = (int) ((xm - 70) / 11.2F);
                        if (cldd[3] < 0) {
                            cldd[3] = 0;
                        }
                        if (cldd[3] > 10) {
                            cldd[3] = 10;
                        }
                    }
                    if (mouseon == 7) {
                        texture[3] = (int) ((xm - 70 - 225) / 2.8 + 20.0);
                        if (texture[3] < 20) {
                            texture[3] = 20;
                        }
                        if (texture[3] > 60) {
                            texture[3] = 60;
                        }
                    }
                    rd.setColor(new Color(0, 128, 255));
                    float f106 = (1500 - Math.abs(cldd[4])) / 15.625F;
                    if (f106 > 64.0F) {
                        f106 = 64.0F;
                    }
                    if (f106 < 0.0F) {
                        f106 = 0.0F;
                    }
                    rd.drawRect(199, (int) (465.0F + f106), 12, 2);
                    if (xm > 197 && xm < 213 && ym > 463 && ym < 533 && mousePressed == 1 && mouseon == -1) {
                        mouseon = 8;
                    }
                    if (mouseon == 8) {
                        cldd[4] = -(int) ((530 - ym) * 15.625F + 500.0F);
                        if (cldd[4] > -500) {
                            cldd[4] = -500;
                        }
                        if (cldd[4] < -1500) {
                            cldd[4] = -1500;
                        }
                    }
                    Color color = Color.getHSBColor(hsb[0][0], hsb[0][1], hsb[0][2]);
                    Medium.setcloads(color.getRed(), color.getGreen(), color.getBlue(), cldd[3], cldd[4]);
                    cldd[0] = color.getRed();
                    cldd[1] = color.getGreen();
                    cldd[2] = color.getBlue();
                    color = Color.getHSBColor(hsb[1][0], hsb[1][1], hsb[1][2]);
                    Medium.setexture(color.getRed(), color.getGreen(), color.getBlue(), texture[3]);
                    texture[0] = color.getRed();
                    texture[1] = color.getGreen();
                    texture[2] = color.getBlue();
                    rd.setFont(new Font("Arial", 1, 12));
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Mountains", 452, 465);
                    rd.setFont(new Font("Arial", 0, 11));
                    rd.drawString("Mountain Generator Key:", 452, 480);
                    movefield(mgen, 452, 484, 120, 20);
                    if (mgen.hasFocus()) {
                        focuson = false;
                    }
                    if (!mgen.isShowing()) {
                        mgen.setVisible(true);
                    }
                    if (button("  Generate New  ", 512, 525, 3, true)) {
                        Medium.mgen = (int) (ThreadLocalRandom.current().nextDouble() * 100000.0);
                        mgen.setText("" + Medium.mgen + "");
                        if (ttstage.equals("")) {
                            ttstage = tstage;
                        }
                        sortop();
                        readstage(1);
                    }
                    if (!mgen.getText().equals("" + Medium.mgen + "")) {
                        try {
                            Medium.mgen = Integer.parseInt(mgen.getText());
                            if (ttstage.equals("")) {
                                ttstage = tstage;
                            }
                            sortop();
                            readstage(1);
                        } catch (final Exception exception) {
                            mgen.setText("" + Medium.mgen + "");
                        }
                    }
                    if (button(" Reset ", 650, 510, 0, true)) {
                        if (!ttstage.equals("")) {
                            tstage = ttstage;
                            ttstage = "";
                        }
                        readstage(1);
                        dtabed = -2;
                    }
                    if (button("        Save        ", 737, 510, 0, true)) {
                        sortop();
                        ttstage = "";
                        savefile();
                    }
                }
                if (dtab == 1) {
                    if (dtabed != dtab) {
                        for (int i108 = 0; i108 < 3; i108++) {
                            snap[i108] = (int) (Medium.snap[i108] / 1.2F + 50.0F);
                        }
                        fogn[0] = (8 - ((Medium.fogd + 1) / 2 - 1)) * 20;
                        fogn[1] = (Medium.fade[0] - 5000) / 30;
                    }
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Atmosphere RGB Mask", 20, 461);
                    rd.setColor(new Color(128, 128, 128));
                    rd.drawLine(10, 457, 17, 457);
                    rd.drawLine(260, 457, 152, 457);
                    rd.drawLine(10, 457, 10, 546);
                    rd.drawLine(260, 457, 260, 527);
                    rd.drawLine(260, 527, 360, 527);
                    rd.drawLine(10, 546, 360, 546);
                    rd.drawLine(360, 527, 360, 546);
                    final String[] strings109 = {
                            "Red", "Green", "Blue"
                    };
                    final int[] is110 = {
                            32, 20, 29
                    };
                    int i111 = 38;
                    int i112 = -70;
                    for (int i113 = 0; i113 < 3; i113++) {
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString("" + strings109[i113] + " :", is110[i113], 447 + i113 * 24 + i111);
                        rd.drawLine(140 + i112, 443 + i113 * 24 + i111, 230 + i112, 443 + i113 * 24 + i111);
                        for (int i114 = 1; i114 < 10; i114++) {
                            rd.drawLine(140 + 10 * i114 + i112, 443 - i114 + i113 * 24 + i111, 140 + 10 * i114 + i112, 443 + i114 + i113 * 24 + i111);
                        }
                        rd.setColor(new Color(255, 0, 0));
                        final int i115 = (int) (snap[i113] / 1.1111F / 10.0F);
                        rd.fillRect(138 + (int) (snap[i113] / 1.1111F) + i112, 443 - i115 + i113 * 24 + i111, 5, i115 * 2 + 1);
                        rd.setColor(new Color(255, 128, 0));
                        rd.drawRect(139 + (int) (snap[i113] / 1.1111F) + i112, 434 + i113 * 24 + i111, 2, 18);
                        if (button(" - ", 260 + i112, 447 + i113 * 24 + i111, 4, false)) {
                            snap[i113] -= 2;
                            if (snap[i113] < 0) {
                                snap[i113] = 0;
                            }
                        }
                        if (button(" + ", 300 + i112, 447 + i113 * 24 + i111, 4, false)) {
                            if (snap[0] + snap[1] + snap[2] > 200) {
                                for (int i116 = 0; i116 < 3; i116++)
                                    if (i116 != i113) {
                                        snap[i116]--;
                                        if (snap[i116] < 0) {
                                            snap[i116] = 0;
                                        }
                                    }
                            }
                            snap[i113] += 2;
                            if (snap[i113] > 100) {
                                snap[i113] = 100;
                            }
                        }
                    }
                    if (Medium.snap[0] != (int) (snap[0] * 1.2F - 60.0F) || Medium.snap[1] != (int) (snap[1] * 1.2F - 60.0F) || Medium.snap[2] != (int) (snap[2] * 1.2F - 60.0F)) {
                        for (int i117 = 0; i117 < 3; i117++) {
                            Medium.snap[i117] = (int) (snap[i117] * 1.2F - 60.0F);
                        }
                        readstage(2);
                    }
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Car Lights :", 265, 541);
                    if (snap[0] + snap[1] + snap[2] > 110) {
                        rd.drawString("Off", 335, 541);
                        Medium.lightson = false;
                    } else {
                        rd.setColor(new Color(0, 200, 0));
                        rd.drawString("On", 335, 541);
                        Medium.lightson = true;
                    }
                    final int i118 = 33;
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Dust/Fog Properties", 280 + i118, 461);
                    rd.setColor(new Color(128, 128, 128));
                    rd.drawLine(270 + i118, 457, 277 + i118, 457);
                    rd.drawLine(540 + i118, 457, 393 + i118, 457);
                    rd.drawLine(270 + i118, 457, 270 + i118, 522);
                    rd.drawLine(540 + i118, 457, 540 + i118, 522);
                    rd.drawLine(270 + i118, 522, 540 + i118, 522);
                    final String[] strings119 = {
                            "Density", "Near / Far"
                    };
                    final int[] is120 = {
                            292 + i118, 280 + i118
                    };
                    final int[] is121 = {
                            20, 10
                    };
                    i111 = 38;
                    i112 = 210 + i118;
                    for (int i122 = 0; i122 < 2; i122++) {
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString("" + strings119[i122] + " :", is120[i122], 447 + i122 * 24 + i111);
                        rd.drawLine(140 + i112, 443 + i122 * 24 + i111, 230 + i112, 443 + i122 * 24 + i111);
                        for (int i123 = 1; i123 < 10; i123++) {
                            rd.drawLine(140 + 10 * i123 + i112, 443 - i123 + i122 * 24 + i111, 140 + 10 * i123 + i112, 443 + i123 + i122 * 24 + i111);
                        }
                        rd.setColor(new Color(255, 0, 0));
                        final int i124 = (int) (fogn[i122] / 1.1111F / 10.0F);
                        rd.fillRect(138 + (int) (fogn[i122] / 1.1111F) + i112, 443 - i124 + i122 * 24 + i111, 5, i124 * 2 + 1);
                        rd.setColor(new Color(255, 128, 0));
                        rd.drawRect(139 + (int) (fogn[i122] / 1.1111F) + i112, 434 + i122 * 24 + i111, 2, 18);
                        if (button(" - ", 260 + i112, 447 + i122 * 24 + i111, 4, false)) {
                            fogn[i122] -= is121[i122];
                            if (fogn[i122] < 0) {
                                fogn[i122] = 0;
                            }
                        }
                        if (button(" + ", 300 + i112, 447 + i122 * 24 + i111, 4, false)) {
                            fogn[i122] += is121[i122];
                            if (fogn[i122] > 100) {
                                fogn[i122] = 100;
                            }
                        }
                    }
                    Medium.fogd = (8 - fogn[0] / 20 + 1) * 2 - 1;
                    Medium.fadfrom(5000 + fogn[1] * 30);
                    origfade = Medium.fade[0];
                    if (button(" Reset ", 650, 510, 0, true)) {
                        dtabed = -2;
                    }
                    if (button("        Save        ", 737, 510, 0, true)) {
                        sortop();
                        savefile();
                    }
                }
                if (dtab == 4) {
                    if (dtabed != dtab && CheckPoints.nlaps - 1 >= 0 && CheckPoints.nlaps - 1 <= 14) {
                        nlaps.select(CheckPoints.nlaps - 1);
                    }
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Set the number of laps for this stage:", 130, 496);
                    nlaps.move(348, 480);
                    if (!nlaps.isShowing()) {
                        nlaps.setVisible(true);
                    }
                    if (CheckPoints.nlaps != nlaps.getSelectedIndex() + 1) {
                        CheckPoints.nlaps = nlaps.getSelectedIndex() + 1;
                        requestFocus();
                    }
                    if (button(" Reset ", 530, 496, 0, true)) {
                        dtabed = -2;
                    }
                    if (button("        Save        ", 617, 496, 0, true)) {
                        sortop();
                        savefile();
                    }
                }
                if (dtab == 5) {
                    if (dtabed != dtab) {
                        tracks.removeAll();
                        tracks.maxl = 200;
                        tracks.add(rd, "The Play List  -  MOD Tracks");
                        final String[] strings125 = new File("mystages/mymusic/").list();
                        if (strings125 != null) {
                            for (final String element : strings125)
                                if (element.toLowerCase().endsWith(".zip")) {
                                    tracks.add(rd, element.substring(0, element.length() - 4));
                                }
                        }
                        if (ltrackname.equals("")) {
                            if (trackname.equals("")) {
                                tracks.select(0);
                            } else {
                                tracks.select(trackname);
                            }
                        } else {
                            tracks.select(ltrackname);
                        }
                        mouseon = -1;
                    }
                    tracks.move(10, 450);
                    if (tracks.getWidth() != 200) {
                        tracks.setSize(200);
                    }
                    if (!tracks.isShowing()) {
                        tracks.setVisible(true);
                    }
                    if (track.playing && track.loaded == 2) {
                        if (button("      Stop      ", 110, 495, 2, false)) {
                            track.setPaused(true);
                        }
                        if (!ltrackname.equals(tracks.getSelectedItem())) {
                            track.setPaused(true);
                        }
                        if (xm > 10 && xm < 210 && ym > 516 && ym < 534) {
                            if (mousePressed == 1) {
                                mouseon = 1;
                            }
                            rd.setColor(new Color(0, 164, 242));
                        } else {
                            rd.setColor(new Color(120, 210, 255));
                        }
                        rd.drawRect(10, 516, 200, 18);
                        rd.setColor(new Color(200, 200, 200));
                        rd.drawLine(10, 523, 210, 523);
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawLine(10, 524, 210, 524);
                        rd.drawLine(10, 525, 210, 525);
                        rd.drawLine(10, 526, 210, 526);
                        rd.setColor(new Color(255, 255, 255));
                        rd.drawLine(10, 527, 210, 527);
                        int i127 = (int) ((1.0F - (float) track.sClip.stream.available() / (float) avon) * 200.0F);
                        if (mouseon == 1) {
                            i127 = xm - 10;
                            if (i127 < 0) {
                                i127 = 0;
                            }
                            if (i127 > 200) {
                                i127 = 200;
                            }
                            if (mousePressed != 1) {
                                track.sClip.stream.reset();
                                track.sClip.stream.skip((long) (i127 / 200.0F * avon));
                                mouseon = -1;
                            }
                        }
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawRect(8 + i127, 516, 4, 18);
                        rd.setColor(new Color(0, 164, 242));
                        rd.drawLine(10 + i127, 520, 10 + i127, 518);
                        rd.drawLine(10 + i127, 530, 10 + i127, 532);
                    } else if (tracks.getSelectedIndex() != 0 && button("      Play  >      ", 110, 495, 2, false)) {
                        if (!ltrackname.equals(tracks.getSelectedItem())) {
                            track.unload();
                            track = new RadicalMod("mystages/mymusic/" + tracks.getSelectedItem() + ".zip", 300, 8000, 125, true, false);
                            if (track.loaded == 2) {
                                avon = track.sClip.stream.available();
                                ltrackname = tracks.getSelectedItem();
                            } else {
                                ltrackname = "";
                            }
                        }
                        if (!ltrackname.equals("")) {
                            track.play();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to load '" + tracks.getSelectedItem() + "', please make sure it is a valid MOD Track!", "Stage Maker", 1);
                        }
                    }
                    if (tracks.getSelectedIndex() != 0) {
                        if (button("   Set as the stage's Sound Track  >   ", 330, 466, 2, false)) {
                            if (!ltrackname.equals(tracks.getSelectedItem())) {
                                track.unload();
                                track = new RadicalMod("mystages/mymusic/" + tracks.getSelectedItem() + ".zip", 300, 8000, 125, true, false);
                                if (track.loaded == 2) {
                                    avon = track.sClip.stream.available();
                                    ltrackname = tracks.getSelectedItem();
                                } else {
                                    ltrackname = "";
                                }
                            }
                            if (!ltrackname.equals("")) {
                                trackname = ltrackname;
                                trackvol = (int) (220.0F / (track.rvol / 3750.0F));
                                try {
                                    final File file = new File("mystages/mymusic/" + trackname + ".zip");
                                    tracksize = (int) (file.length() / 1024L);
                                    if (tracksize > 250) {
                                        JOptionPane.showMessageDialog(null, "Cannot use '" + tracks.getSelectedItem() + "' as the sound track!\nIts file size is bigger then 250KB.\n\n", "Stage Maker", 1);
                                        trackname = "";
                                    }
                                } catch (final Exception exception) {
                                    tracksize = 111;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to load '" + tracks.getSelectedItem() + "', please make sure it is a valid MOD Track!", "Stage Maker", 1);
                            }
                        }
                        if (button("   X Delete   ", 258, 495, 2, false) && JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete this MOD Track from your Play List?\n\n" + tracks.getSelectedItem() + "\n\n>  If you delete this Track from the Play List you will not be able to use it for other stages as well!     \n\n", "Stage Maker", 0) == 0) {
                            deltrack();
                        }
                    }
                    if (button("   Add a new MOD Track from file . . .  ", 330, 530, 0, false) && JOptionPane.showConfirmDialog(null, "The game only accepts MOD format music files for the game ('.mod' file extension).\nA good place to find MOD Tracks is the modarchive.com, all the current MOD Tracks\nthat are distributed with the game are from the modarchive.com.\n\nTo find out more about MOD Tracks and to learn how to compose & remix your own\nmusic, please read the section of the Stage Maker help about it.\n\nThe MOD Track needs to be compressed in a zip file to be added here, please make\nsure the MOD Track you wish to add to your stages sound track play list is zipped before\nadding it here.\nThe ZIP file must also be less then 250KB in size.\n\nIs the track you are about to insert a MOD Track in a ZIP file that is less then 250KB?\n", "Stage Maker", 0) == 0) {
                        File file = null;
                        final FileDialog filedialog = new FileDialog(new Frame(), "Stage Maker - Add MOD Track file to stage sound track play list!");
                        filedialog.setFile("*.zip");
                        filedialog.setMode(0);
                        filedialog.setVisible(true);
                        try {
                            if (filedialog.getFile() != null) {
                                file = new File("" + filedialog.getDirectory() + "" + filedialog.getFile() + "");
                            }
                        } catch (final Exception ignored) {

                        }
                        if (file != null) {
                            try {
                                if (file.length() / 1024L < 250L) {
                                    File file128 = new File("mystages/mymusic/");
                                    if (!file128.exists()) {
                                        file128.mkdirs();
                                    }
                                    file128 = new File("mystages/mymusic/" + file.getName() + "");
                                    final FileInputStream fileinputstream = new FileInputStream(file);
                                    final FileOutputStream fileoutputstream = new FileOutputStream(file128);
                                    final byte[] is129 = new byte[1024];
                                    int i130;
                                    while ((i130 = fileinputstream.read(is129)) > 0) {
                                        fileoutputstream.write(is129, 0, i130);
                                    }
                                    fileinputstream.close();
                                    fileoutputstream.close();
                                    tracks.removeAll();
                                    tracks.add(rd, "Select MOD Track                      ");
                                    final String[] strings131 = new File("mystages/mymusic/").list();
                                    if (strings131 != null) {
                                        for (final String element : strings131)
                                            if (element.toLowerCase().endsWith(".zip")) {
                                                tracks.add(rd, element.substring(0, element.length() - 4));
                                            }
                                    }
                                    tracks.select(file.getName().substring(0, file.getName().length() - 4));
                                } else {
                                    JOptionPane.showMessageDialog(null, "The selected file is larger then 250KB in size and therefore cannot be added!", "Stage Maker", 1);
                                }
                            } catch (final Exception exception) {
                                JOptionPane.showMessageDialog(null, "Unable to copy file! Error Deatials:\n" + exception, "Stage Maker", 1);
                            }
                        }
                    }
                    final int i133 = 200;
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Sound Track", 280 + i133, 461);
                    String string = trackname;
                    if (string.equals("")) {
                        string = "No Sound Track set.";
                    } else if (button("   <  Remove Track   ", 378, 495, 2, false)) {
                        trackname = "";
                    }
                    rd.drawString(string, 629 - ftm.stringWidth(string) / 2, 482);
                    rd.setColor(new Color(128, 128, 128));
                    rd.drawLine(270 + i133, 457, 277 + i133, 457);
                    rd.drawLine(589 + i133, 457, 353 + i133, 457);
                    rd.drawLine(270 + i133, 457, 270 + i133, 497);
                    rd.drawLine(589 + i133, 457, 589 + i133, 497);
                    rd.drawLine(270 + i133, 497, 589 + i133, 497);
                    if (button(" Reset ", 576, 530, 0, true)) {
                        ltrackname = "";
                        dtabed = -2;
                    }
                    if (button("        Save        ", 663, 530, 0, true)) {
                        sortop();
                        savefile();
                    }
                }
                if (dtab == 6) {
                    rd.setColor(new Color(0, 0, 0));
                    rd.setFont(new Font("Arial", 1, 13));
                    ftm = rd.getFontMetrics();
                    rd.drawString("Test Drive the Stage", 400 - ftm.stringWidth("Test Drive the Stage") / 2, 470);
                    witho.move(342, 480);
                    if (!witho.isShowing()) {
                        witho.setVisible(true);
                    }
                    if (button("     TEST DRIVE!     ", 400, 530, 0, true)) {
                        savefile();
                        errd = 0;
                        readstage(3);
                        if (CheckPoints.nsp < 2) {
                            errd = 7;
                        }
                        if (errd == 0) {
                            Madness.testcar = stagename;
                            Madness.testdrive = witho.getSelectedIndex() + 3;
                            Madness.game();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error!  This stage is not ready for a test drive!\nReason:\n" + errlo[errd - 1] + "\n\n", "Stage Maker", 0);
                        }
                    }
                }
                if (dtabed != dtab)
                    if (dtabed == -2) {
                        dtabed = -1;
                    } else {
                        dtabed = dtab;
                    }
            }
            if (tab == 3) {
                rd.setFont(new Font("Arial", 1, 13));
                rd.setColor(new Color(0, 0, 0));
                rd.drawString("Publish Stage :  [ " + stagename + " ]", 30, 50);
                rd.drawString("Publishing Type :", 30, 80);
                pubtyp.move(150, 63);
                if (!pubtyp.isShowing()) {
                    pubtyp.setVisible(true);
                    pubtyp.select(1);
                }
                rd.setColor(new Color(0, 0, 0));
                rd.setFont(new Font("Arial", 0, 12));
                if (pubtyp.getSelectedIndex() == 0) {
                    rd.drawString("Private :  This means only you can have your stage in your account and no one else can add", 268, 72);
                    rd.drawString("it to their account to play it!", 268, 88);
                }
                if (pubtyp.getSelectedIndex() == 1) {
                    rd.drawString("Public :  This means anyone can add this stage to their account to play it, but only you can", 268, 72);
                    rd.drawString("download it to your Stage Maker and edit it (no one else but you can edit it).", 268, 88);
                }
                if (pubtyp.getSelectedIndex() == 2) {
                    rd.drawString("Super Public :  This means anyone can add this stage to their account to play it and can also", 268, 72);
                    rd.drawString("download it to their stage Maker, edit it and publish it.", 268, 88);
                }
                rd.setFont(new Font("Arial", 1, 12));
                ftm = rd.getFontMetrics();
                rd.drawString("Stage Name", 180 - ftm.stringWidth("Stage Name") / 2, 138);
                rd.drawString("Created By", 400 - ftm.stringWidth("Created By") / 2, 138);
                rd.drawString("Added By", 500 - ftm.stringWidth("Added By") / 2, 138);
                rd.drawString("Publish Type", 600 - ftm.stringWidth("Publish Type") / 2, 138);
                rd.drawString("Options", 720 - ftm.stringWidth("Options") / 2, 138);
                rd.drawLine(350, 129, 350, 140);
                rd.drawLine(450, 129, 450, 140);
                rd.drawLine(550, 129, 550, 140);
                rd.drawLine(650, 129, 650, 140);
                rd.drawRect(10, 140, 780, 402);
                if (button("       Publish  >       ", 102, 110, 0, true)) {
                    if (logged == 0) {
                        JOptionPane.showMessageDialog(null, "Please login to retrieve your account first before publishing!", "Stage Maker", 1);
                    }
                    if (logged == 3 || logged == -1) {
                        savefile();
                        errd = 0;
                        readstage(3);
                        if (CheckPoints.nsp < 2) {
                            errd = 7;
                        }
                        rd.setFont(new Font("Arial", 1, 12));
                        ftm = rd.getFontMetrics();
                        if (ftm.stringWidth(stagename) > 274) {
                            errd = 8;
                        }
                        if (errd == 0) {
                            int i = 0;
                            for (int i134 = 0; i134 < nms; i134++)
                                if (mystages[i134].equals(stagename) && maker[i134].equalsIgnoreCase(tnick.getText())) {
                                    i = JOptionPane.showConfirmDialog(null, "Replace your already online stage '" + stagename + "' with this one?", "Stage Maker", 0);
                                }
                            if (i == 0) {
                                setCursor(new Cursor(3));
                                rd.setFont(new Font("Arial", 1, 13));
                                ftm = rd.getFontMetrics();
                                rd.setColor(new Color(225, 225, 225));
                                rd.fillRect(11, 141, 779, 401);
                                rd.setColor(new Color(0, 0, 0));
                                rd.drawString("Connecting to Server...", 400 - ftm.stringWidth("Connecting to Server...") / 2, 250);
                                repaint();
                                int i135 = -1;
                                try {
                                    final Socket socket = new Socket("multiplayer.needformadness.com", 7061);
                                    final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                    final PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);
                                    printwriter.println("20|" + tnick.getText() + "|" + tpass.getText() + "|" + stagename + "|" + pubtyp.getSelectedIndex() + "|");
                                    String string = bufferedreader.readLine();
                                    if (string != null) {
                                        i135 = servervalue(string, 0);
                                    }
                                    if (i135 == 0) {
                                        String string136 = " Publishing Stage ";
                                        final String string137 = "" + tstage + "\r\n" + bstage + "";
                                        final BufferedReader stagebufferedreader = new BufferedReader(new StringReader(string137));
                                        String string139;
                                        while ((string139 = stagebufferedreader.readLine()) != null) {
                                            string139 = string139.trim();
                                            printwriter.println(string139);
                                            rd.setColor(new Color(225, 225, 225));
                                            rd.fillRect(11, 141, 779, 401);
                                            rd.setColor(new Color(0, 0, 0));
                                            rd.drawString(string136, 400 - ftm.stringWidth(string136) / 2, 250);
                                            string136 = "| " + string136 + " |";
                                            if (string136.equals("| | | | | | | | | | | | | | | | | | | | | | | |  Publishing Stage  | | | | | | | | | | | | | | | | | | | | | | | |")) {
                                                string136 = " Publishing Stage ";
                                            }
                                            repaint();
                                            try {
                                                if (thredo != null) {

                                                }
                                                Thread.sleep(10L);
                                            } catch (final InterruptedException ignored) {

                                            }
                                        }
                                        printwriter.println("QUITX1111");
                                        rd.setColor(new Color(225, 225, 225));
                                        rd.fillRect(11, 141, 779, 401);
                                        rd.setColor(new Color(0, 0, 0));
                                        rd.drawString("Creating the stage online...", 400 - ftm.stringWidth("Creating the stage online...") / 2, 250);
                                        rd.drawString("This may take a couple of minutes, please wait...", 400 - ftm.stringWidth("This may take a couple of minutes, please wait...") / 2, 280);
                                        repaint();
                                        string = bufferedreader.readLine();
                                        if (string != null) {
                                            i135 = servervalue(string, 0);
                                        } else {
                                            i135 = -1;
                                        }
                                        if (i135 == 0) {
                                            rd.setColor(new Color(225, 225, 225));
                                            rd.fillRect(11, 141, 779, 401);
                                            rd.setColor(new Color(0, 0, 0));
                                            rd.drawString("Uploading stage's sound track...", 400 - ftm.stringWidth("Uploading Stage's Sound Track...") / 2, 250);
                                            rd.drawString("This may take a couple of minutes, please wait...", 400 - ftm.stringWidth("This may take a couple of minutes, please wait...") / 2, 280);
                                            repaint();
                                            final File file = new File("mystages/mymusic/" + trackname + ".zip");
                                            if (!trackname.equals("") && file.exists()) {
                                                final int i140 = (int) file.length();
                                                printwriter.println("track|" + trackname + "|" + i140 + "|");
                                                string = bufferedreader.readLine();
                                                if (string != null) {
                                                    i135 = servervalue(string, 0);
                                                } else {
                                                    i135 = -2;
                                                }
                                                if (i135 == 0) {
                                                    final FileInputStream fileinputstream = new FileInputStream(file);
                                                    final byte[] is = new byte[i140];
                                                    fileinputstream.read(is);
                                                    fileinputstream.close();
                                                    final DataOutputStream dataoutputstream = new DataOutputStream(socket.getOutputStream());
                                                    dataoutputstream.write(is, 0, i140);
                                                    string = bufferedreader.readLine();
                                                    if (string != null) {
                                                        i135 = servervalue(string, 0);
                                                    } else {
                                                        i135 = -2;
                                                    }
                                                }
                                                if (i135 == -67) {
                                                    i135 = 0;
                                                }
                                            } else {
                                                printwriter.println("END");
                                                string = bufferedreader.readLine();
                                            }
                                        }
                                    }
                                    socket.close();
                                } catch (final Exception exception) {
                                    i135 = -1;
                                }
                                setCursor(new Cursor(0));
                                boolean bool = false;
                                if (i135 == 0) {
                                    logged = 1;
                                    bool = true;
                                }
                                if (i135 == 3) {
                                    JOptionPane.showMessageDialog(null, "Unable to publish stage.\nReason:\n" + errlo[6] + "\n\n", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 == 4) {
                                    JOptionPane.showMessageDialog(null, "Unable to publish stage.\nReason:\nStage name used (" + stagename + ").\nThe name '" + stagename + "' is already used by another published stage.\nPlease rename your stage.\n\n", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 == 5) {
                                    JOptionPane.showMessageDialog(null, "Unable to create stage online!  Unknown Error.  Please try again later.", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 > 5) {
                                    JOptionPane.showMessageDialog(null, "Unable to publish stage fully!  Unknown Error.  Please try again later.", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 == -4) {
                                    logged = 1;
                                    JOptionPane.showMessageDialog(null, "Unable to upload sound track!\nReason:\nAnother MOD Track is already uploaded with the same name, please rename your Track.\nOpen your 'mystages' folder then open 'mymusic' to find your MOD Track to rename it.\n\n", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 == -3) {
                                    logged = 1;
                                    JOptionPane.showMessageDialog(null, "Unable to upload sound track!\nReason:\nYour MOD Track\u2019s file size is too large, Track file size must be less then 250KB to be accepted.\n\n", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (i135 == -2) {
                                    logged = 1;
                                    JOptionPane.showMessageDialog(null, "Unable to upload sound track!  Unknown Error.  Please try again later.", "Stage Maker", 1);
                                    bool = true;
                                }
                                if (!bool) {
                                    JOptionPane.showMessageDialog(null, "Unable to publish stage!  Unknown Error.", "Stage Maker", 1);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error!  This stage is not ready for publishing!\nReason:\n" + errlo[errd - 1] + "\n\n", "Stage Maker", 0);
                        }
                    }
                }
                if (logged == 3) {
                    for (int i = 0; i < nms; i++) {
                        rd.setColor(new Color(235, 235, 235));
                        if (xm > 11 && xm < 789 && ym > 142 + i * 20 && ym < 160 + i * 20) {
                            rd.setColor(new Color(255, 255, 255));
                        }
                        rd.fillRect(11, 142 + i * 20, 778, 18);
                        rd.setFont(new Font("Arial", 0, 12));
                        ftm = rd.getFontMetrics();
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString(mystages[i], 180 - ftm.stringWidth(mystages[i]) / 2, 156 + i * 20);
                        rd.setColor(new Color(155, 155, 155));
                        rd.drawLine(350, 145 + i * 20, 350, 157 + i * 20);
                        if (pubt[i] != -1) {
                            rd.drawLine(450, 145 + i * 20, 450, 157 + i * 20);
                            rd.drawLine(550, 145 + i * 20, 550, 157 + i * 20);
                            rd.drawLine(650, 145 + i * 20, 650, 157 + i * 20);
                            boolean bool = false;
                            if (maker[i].equalsIgnoreCase(tnick.getText())) {
                                bool = true;
                                rd.setColor(new Color(0, 64, 0));
                                rd.drawString("You", 400 - ftm.stringWidth("You") / 2, 156 + i * 20);
                            } else {
                                rd.setColor(new Color(0, 0, 64));
                                rd.drawString(maker[i], 400 - ftm.stringWidth(maker[i]) / 2, 156 + i * 20);
                            }
                            if (nad[i] > 1) {
                                if (ovbutton("" + nad[i] + " Players", 500, 156 + i * 20)) {
                                    String string = "[ " + mystages[i] + " ]  has been added by the following players to their accounts:     \n\n";
                                    int i141 = 0;
                                    for (int i142 = 0; i142 < nad[i]; i142++) {
                                        if (++i141 == 17) {
                                            string = "" + string + "\n";
                                            i141 = 1;
                                        }
                                        string = "" + string + addeda[i][i142];
                                        if (i142 != nad[i] - 1)
                                            if (i142 != nad[i] - 2) {
                                                string = "" + string + ", ";
                                            } else if (i141 == 16) {
                                                string = "" + string + "\nand ";
                                                i141 = 0;
                                            } else {
                                                string = "" + string + " and ";
                                            }
                                    }
                                    string = "" + string + "\n \n \n";
                                    JOptionPane.showMessageDialog(null, string, "Stage Maker", 1);
                                }
                            } else {
                                rd.setColor(new Color(0, 0, 64));
                                rd.drawString("None", 500 - ftm.stringWidth("None") / 2, 156 + i * 20);
                            }
                            if (pubt[i] == 0) {
                                rd.setColor(new Color(0, 0, 64));
                                rd.drawString("Private", 600 - ftm.stringWidth("Private") / 2, 156 + i * 20);
                            }
                            if (pubt[i] == 1) {
                                rd.setColor(new Color(0, 0, 64));
                                rd.drawString("Public", 600 - ftm.stringWidth("Public") / 2, 156 + i * 20);
                            }
                            if (pubt[i] == 2) {
                                rd.setColor(new Color(0, 64, 0));
                                rd.drawString("Super Public", 600 - ftm.stringWidth("Super Public") / 2, 156 + i * 20);
                            }
                            if ((pubt[i] == 2 || bool) && ovbutton("Download", 700, 156 + i * 20)) {
                                int i143 = 0;
                                for (int i144 = 0; i144 < slstage.getItemCount(); i144++)
                                    if (mystages[i].equals(slstage.getItem(i144))) {
                                        i143 = JOptionPane.showConfirmDialog(null, "Replace the local " + mystages[i] + " in your 'mystages' folder with the published online copy?", "Stage Maker", 0);
                                    }
                                if (i143 == 0) {
                                    setCursor(new Cursor(3));
                                    rd.setFont(new Font("Arial", 1, 13));
                                    ftm = rd.getFontMetrics();
                                    rd.setColor(new Color(225, 225, 225));
                                    rd.fillRect(11, 141, 779, 401);
                                    rd.setColor(new Color(0, 0, 0));
                                    rd.drawString("Downloading stage, please wait...", 400 - ftm.stringWidth("Downloading stage, please wait...") / 2, 250);
                                    repaint();
                                    try {
                                        String string = "http://multiplayer.needformadness.com/tracks/" + mystages[i] + ".radq?reqlo=" + (int) (ThreadLocalRandom.current().nextDouble() * 1000.0) + "";
                                        string = string.replace(' ', '_');
                                        URL url = new URL(string);
                                        int i145 = url.openConnection().getContentLength();
                                        DataInputStream datainputstream = new DataInputStream(url.openStream());
                                        byte[] is = new byte[i145];
                                        datainputstream.readFully(is);
                                        datainputstream.close();
                                        ZipInputStream zipinputstream;
                                        if (is[0] == 80 && is[1] == 75 && is[2] == 3) {
                                            zipinputstream = new ZipInputStream(new ByteArrayInputStream(is));
                                        } else {
                                            final byte[] is146 = new byte[i145 - 40];
                                            for (int i147 = 0; i147 < i145 - 40; i147++) {
                                                int i148 = 20;
                                                if (i147 >= 500) {
                                                    i148 = 40;
                                                }
                                                is146[i147] = is[i147 + i148];
                                            }
                                            zipinputstream = new ZipInputStream(new ByteArrayInputStream(is146));
                                        }
                                        final ZipEntry zipentry = zipinputstream.getNextEntry();
                                        if (zipentry != null) {
                                            String string149 = "";
                                            int i150 = Integer.parseInt(zipentry.getName());
                                            final byte[] is151 = new byte[i150];
                                            int i152 = 0;
                                            int i153;
                                            for (/**/; i150 > 0; i150 -= i153) {
                                                i153 = zipinputstream.read(is151, i152, i150);
                                                i152 += i153;
                                            }
                                            String string154 = new String(is151);
                                            string154 = "" + string154 + "\n";
                                            String string155 = "";
                                            int i156 = 0;
                                            int i157 = string154.indexOf('\n', 0);
                                            while (i157 != -1 && i156 < string154.length()) {
                                                String string158 = string154.substring(i156, i157);
                                                string158 = string158.trim();
                                                i156 = i157 + 1;
                                                i157 = string154.indexOf('\n', i156);
                                                if (!string158.startsWith("stagemaker(") && !string158.startsWith("publish(")) {
                                                    string155 = "" + string155 + "" + string158 + "\r\n";
                                                } else {
                                                    string155 = string155.trim();
                                                    string155 = "" + string155 + "\r\n";
                                                }
                                                if (string158.startsWith("soundtrack")) {
                                                    string149 = getstring("soundtrack", string158, 0);
                                                }
                                            }
                                            string155 = string155.trim();
                                            string155 = "" + string155 + "\r\n\r\n";
                                            File file = new File("mystages/");
                                            if (!file.exists()) {
                                                file.mkdirs();
                                            }
                                            file = new File("mystages/" + mystages[i] + ".txt");
                                            final BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                                            bufferedwriter.write(string155);
                                            bufferedwriter.close();
                                            zipinputstream.close();
                                            if (!string149.equals("")) {
                                                try {
                                                    rd.setColor(new Color(0, 0, 0));
                                                    rd.drawString("Downloading stage's sound track...", 400 - ftm.stringWidth("Downloading stage's sound track...") / 2, 280);
                                                    repaint();
                                                    string = "http://multiplayer.needformadness.com/tracks/music/" + string149 + ".zip";
                                                    string = string.replace(' ', '_');
                                                    url = new URL(string);
                                                    i145 = url.openConnection().getContentLength();
                                                    file = new File("mystages/mymusic/" + string149 + ".zip");
                                                    if (file.exists())
                                                        if (file.length() == i145) {
                                                            i143 = 1;
                                                        } else {
                                                            i143 = JOptionPane.showConfirmDialog(null, "Another track named '" + string149 + "' already exists in your Sound Tracks folder!\nReplace it with the one attached to this stage?", "Stage Maker", 0);
                                                        }
                                                    if (i143 == 0) {
                                                        datainputstream = new DataInputStream(url.openStream());
                                                        is = new byte[i145];
                                                        datainputstream.readFully(is);
                                                        datainputstream.close();
                                                        final FileOutputStream fileoutputstream = new FileOutputStream(file);
                                                        fileoutputstream.write(is);
                                                        fileoutputstream.close();
                                                    }
                                                } catch (final Exception ignored) {

                                                }
                                            }
                                            setCursor(new Cursor(0));
                                            JOptionPane.showMessageDialog(null, "" + mystages[i] + " has been successfully downloaded!", "Stage Maker", 1);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Unable to download stage.  Unknown Error!     \nPlease try again later.", "Stage Maker", 1);
                                        }
                                    } catch (final Exception exception) {
                                        JOptionPane.showMessageDialog(null, "Unable to download stage.  Unknown Error!     \nPlease try again later.", "Stage Maker", 1);
                                    }
                                }
                            }
                        } else {
                            rd.drawString("-    Error Loading this stage's info!    -", 550 - ftm.stringWidth("-    Error Loading this stage's info!    -") / 2, 156 + i * 20);
                        }
                        if (ovbutton("X", 765, 156 + i * 20) && JOptionPane.showConfirmDialog(null, "Remove " + mystages[i] + " from your account?", "Stage Maker", 0) == 0) {
                            setCursor(new Cursor(3));
                            int i160 = -1;
                            try {
                                final Socket socket = new Socket("multiplayer.needformadness.com", 7061);
                                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                final PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);
                                printwriter.println("19|" + tnick.getText() + "|" + tpass.getText() + "|" + mystages[i] + "|");
                                final String string = bufferedreader.readLine();
                                if (string != null) {
                                    i160 = servervalue(string, 0);
                                }
                                socket.close();
                            } catch (final Exception exception) {
                                i160 = -1;
                            }
                            if (i160 == 0) {
                                logged = 1;
                            } else {
                                setCursor(new Cursor(0));
                                JOptionPane.showMessageDialog(null, "Failed to remove " + mystages[i] + " from your account.  Unknown Error!     \nPlease try again later.", "Stage Maker", 1);
                            }
                        }
                    }
                }
                if (logged == 2) {
                    for (int i = 0; i < nms; i++) {
                        rd.setFont(new Font("Arial", 1, 13));
                        ftm = rd.getFontMetrics();
                        rd.setColor(new Color(225, 225, 225));
                        rd.fillRect(50, 150, 600, 150);
                        rd.setColor(new Color(0, 0, 0));
                        rd.drawString("Loading " + mystages[i] + "\u2018s info...", 400 - ftm.stringWidth("Loading " + mystages[i] + "\u2018s info...") / 2, 220);
                        repaint();
                        maker[i] = "Unkown";
                        pubt[i] = -1;
                        nad[i] = 0;
                        String string;
                        try {
                            String string161 = "http://multiplayer.needformadness.com/tracks/" + mystages[i] + ".txt?reqlo=" + (int) (ThreadLocalRandom.current().nextDouble() * 1000.0) + "";
                            string161 = string161.replace(' ', '_');
                            final URL url = new URL(string161);
                            final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new DataInputStream(url.openStream())));
                            while ((string = bufferedreader.readLine()) != null) {
                                string = "" + string.trim();
                                if (string.startsWith("details")) {
                                    maker[i] = getSvalue("details", string, 0);
                                    pubt[i] = Utility.getvalue("details", string, 1);
                                    boolean bool = false;
                                    while (!bool) {
                                        addeda[i][nad[i]] = getSvalue("details", string, 2 + nad[i]);
                                        if (addeda[i][nad[i]].equals("")) {
                                            bool = true;
                                        } else {
                                            nad[i]++;
                                        }
                                    }
                                }
                            }
                        } catch (final Exception ignored) {

                        }
                    }
                    setCursor(new Cursor(0));
                    logged = 3;
                }
                if (logged == -1) {
                    rd.setFont(new Font("Arial", 1, 13));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Account empty, no published stages found.", 400 - ftm.stringWidth("Account empty, no published stages found.") / 2, 220);
                    rd.drawString("Click \u2018Publish\u2019 above to begin.", 400 - ftm.stringWidth("Click \u2018Publish\u2019 above to begin.") / 2, 280);
                    rd.setFont(new Font("Arial", 0, 12));
                    ftm = rd.getFontMetrics();
                    rd.drawString("The maximum number of stages your account can have at once is 20 stages.", 400 - ftm.stringWidth("The maximum number of stages your account can have at once is 20 stages.") / 2, 320);
                }
                if (logged == 1) {
                    rd.setColor(new Color(225, 225, 225));
                    rd.fillRect(11, 141, 779, 401);
                    rd.setFont(new Font("Arial", 1, 13));
                    ftm = rd.getFontMetrics();
                    rd.setColor(new Color(0, 0, 0));
                    rd.drawString("Loading your account's stage list...", 400 - ftm.stringWidth("Loading your account's stage list...") / 2, 220);
                    repaint();
                    nms = 0;
                    String string;
                    try {
                        final URL url = new URL("http://multiplayer.needformadness.com/tracks/lists/" + tnick.getText() + ".txt?reqlo=" + (int) (ThreadLocalRandom.current().nextDouble() * 1000.0) + "");
                        final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new DataInputStream(url.openStream())));
                        while ((string = bufferedreader.readLine()) != null) {
                            string = "" + string.trim();
                            if (string.startsWith("mystages")) {
                                boolean bool = true;
                                while (bool && nms < 20) {
                                    mystages[nms] = getSvalue("mystages", string, nms);
                                    if (mystages[nms].equals("")) {
                                        bool = false;
                                    } else {
                                        nms++;
                                    }
                                }
                            }
                        }
                        if (nms > 0) {
                            logged = 2;
                        } else {
                            setCursor(new Cursor(0));
                            logged = -1;
                        }
                        bufferedreader.close();
                    } catch (final Exception exception) {
                        final String string162 = "" + exception;
                        if (string162.contains("FileNotFound")) {
                            setCursor(new Cursor(0));
                            logged = -1;
                        } else {
                            logged = 0;
                            JOptionPane.showMessageDialog(null, "Unable to connect to server at this moment, please try again later.", "Stage Maker", 1);
                        }
                    }
                }
                if (logged == 0) {
                    rd.setFont(new Font("Arial", 0, 12));
                    ftm = rd.getFontMetrics();
                    rd.drawString("The maximum number of stages your account can have at once is 20 stages.", 400 - ftm.stringWidth("The maximum number of stages your account can have at once is 20 stages.") / 2, 180);
                    rd.setFont(new Font("Arial", 1, 13));
                    ftm = rd.getFontMetrics();
                    rd.drawString("Login to Retrieve your Account Stages", 400 - ftm.stringWidth("Login to Retrieve your Account Stages") / 2, 220);
                    rd.drawString("Nickname:", 376 - ftm.stringWidth("Nickname:") - 14, 266);
                    if (!tnick.isShowing()) {
                        tnick.setVisible(true);
                    }
                    movefield(tnick, 376, 250, 129, 23);
                    rd.drawString("Password:", 376 - ftm.stringWidth("Password:") - 14, 296);
                    if (!tpass.isShowing()) {
                        tpass.setVisible(true);
                    }
                    movefield(tpass, 376, 280, 129, 23);
                    if (button("       Login       ", 400, 340, 0, true)) {
                        setCursor(new Cursor(3));
                        int i = -1;
                        try {
                            final Socket socket = new Socket("multiplayer.needformadness.com", 7061);
                            final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            final PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);
                            printwriter.println("1|" + tnick.getText().toLowerCase() + "|" + tpass.getText() + "|");
                            final String string = bufferedreader.readLine();
                            if (string != null) {
                                i = servervalue(string, 0);
                            }
                            socket.close();
                        } catch (final Exception exception) {
                            i = -1;
                        }
                        if (i == 0 || i == 3 || i > 10) {
                            tnick.setVisible(false);
                            tpass.setVisible(false);
                            logged = 1;
                            savesettings();
                        }
                        if (i == 1 || i == 2) {
                            setCursor(new Cursor(0));
                            JOptionPane.showMessageDialog(null, "Sorry.  Incorrect Nickname or Password!", "Stage Maker", 0);
                        }
                        if (i == -167) {
                            setCursor(new Cursor(0));
                            JOptionPane.showMessageDialog(null, "Sorry.  Trial accounts are not allowed to publish cars & stages, please register a full account!", "Stage Maker", 0);
                        }
                        if (i == -1) {
                            setCursor(new Cursor(0));
                            JOptionPane.showMessageDialog(null, "Unable to connect to server at this moment, please try again later.", "Stage Maker", 1);
                        }
                    }
                    rd.setFont(new Font("Arial", 1, 13));
                    ftm = rd.getFontMetrics();
                    rd.drawString("Not registered yet?", 400 - ftm.stringWidth("Not registered yet?") / 2, 450);
                    if (button("   Register Now!   ", 400, 480, 0, true)) {
                        Madness.openurl("http://multiplayer.needformadness.com/register.html");
                    }
                    rd.setFont(new Font("Arial", 0, 12));
                    ftm = rd.getFontMetrics();
                    rd.drawString("Register to publish your stages to the multiplayer game!", 400 - ftm.stringWidth("Register to publish your stages to the multiplayer game!") / 2, 505);
                }
            }
            if (tabed != tab)
                if (tabed == -2) {
                    tabed = -1;
                } else {
                    tabed = tab;
                }
            rd.setColor(new Color(0, 0, 0));
            rd.fillRect(0, 0, 800, 25);
            if (!onbtgame) {
                rd.drawImage(btgame[0], 620, 0, null);
            } else {
                rd.drawImage(btgame[1], 620, 0, null);
            }
            rd.setFont(new Font("Arial", 1, 13));
            ftm = rd.getFontMetrics();
            final String[] strings = {
                    "Stage", "Build", "View & Edit", "Publish"
            };
            final int[] is = {
                    0, 0, 100, 90
            };
            final int[] is163 = {
                    0, 25, 25, 0
            };
            int i = 4;
            if (stagename.equals("") || sfase != 0) {
                tab = 0;
                i = 1;
            }
            for (int i164 = 0; i164 < i; i164++) {
                rd.setColor(new Color(170, 170, 170));
                if (xm > is[0] && xm < is[3] && ym > 0 && ym < 25) {
                    rd.setColor(new Color(200, 200, 200));
                }
                if (tab == i164) {
                    rd.setColor(new Color(225, 225, 225));
                }
                rd.fillPolygon(is, is163, 4);
                rd.setColor(new Color(0, 0, 0));
                rd.drawString(strings[i164], i164 * 100 + 45 - ftm.stringWidth(strings[i164]) / 2, 17);
                if (xm > is[0] && xm < is[3] && ym > 0 && ym < 25 && mousePressed == -1) {
                    tab = i164;
                }
                for (int i165 = 0; i165 < 4; i165++) {
                    is[i165] += 100;
                }
            }
            if (mousePressed == -1) {
                mousePressed = 0;
            }
            drawms();
            repaint();
            if (!exwist) {
                try {
                    if (thredo != null) {

                    }
                    Thread.sleep(40L);
                } catch (final InterruptedException ignored) {

                }
            }
        }
        track.unload();
        track = null;
        rd.dispose();
        System.gc();
        //bco[selectedPart].x = ;
        //bco[selectedPart].z = ;
        //bco[selectedPart].y =;
        //bco[selectedPart].xz = ;
    }

    private void savefile() {
        try {
            File file = new File("mystages/");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File("mystages/" + stagename + ".txt");
            final BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
            bufferedwriter.write(tstage);
            bufferedwriter.write(bstage);
            bufferedwriter.close();
        } catch (final Exception exception) {
            JOptionPane.showMessageDialog(null, "Unable to save file! Error Deatials:\n" + exception, "Stage Maker", 1);
        }
        savesettings();
    }

    private void savesettings() {
        if (!sstage.equals(stagename) || !suser.equals(tnick.getText())) {
            final String string = "" + stagename + "\n" + tnick.getText() + "\n\n";
            sstage = stagename;
            suser = tnick.getText();
            try {
                File file = new File("mystages/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File("mystages/settings.data");
                final BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                bufferedwriter.write(string);
                bufferedwriter.close();
            } catch (final Exception ignored) {

            }
        }
    }

    // Removed unused code found by UCDetector
    // 	public String serverSvalue(final String string, final int i) {
    // 		String string365 = "";
    // 		try {
    // 			int i366 = 0;
    // 			int i367 = 0;
    // 			int i368 = 0;
    // 			String string369 = "";
    // 			String string370 = "";
    // 			for (/**/; i366 < string.length() && i368 != 2; i366++) {
    // 				string369 = "" + ("") + (string.charAt(i366));
    // 				if (string369.equals("|")) {
    // 					i367++;
    // 					if (i368 == 1 || i367 > i)
    // 						i368 = 2;
    // 				} else if (i367 == i) {
    // 					string370 = "" + (string370) + (string369);
    // 					i368 = 1;
    // 				}
    // 			}
    // 			string365 = string370;
    // 		} catch (final Exception exception) {
    //
    // 		}
    // 		return string365;
    // 	}

    private int servervalue(final String string, final int i) {
        int i359 = -1;
        try {
            int i360 = 0;
            int i361 = 0;
            int i362 = 0;
            String string363;
            String string364 = "";
            for (/**/; i360 < string.length() && i362 != 2; i360++) {
                string363 = "" + string.charAt(i360);
                if (string363.equals("|")) {
                    i361++;
                    if (i362 == 1 || i361 > i) {
                        i362 = 2;
                    }
                } else if (i361 == i) {
                    string364 = "" + string364 + string363;
                    i362 = 1;
                }
            }
            if (string364.equals("")) {
                string364 = "-1";
            }
            i359 = Integer.parseInt(string364);
        } catch (final Exception ignored) {

        }
        return i359;
    }

    private void sortop() {
        tstage = "snap(" + Medium.snap[0] + "," + Medium.snap[1] + "," + Medium.snap[2] + ")\r\nsky(" + csky[0] + "," + csky[1] + "," + csky[2] + ")\r\nfog(" + cfade[0] + "," + cfade[1] + "," + cfade[2] + ")\r\nclouds(" + cldd[0] + "," + cldd[1] + "," + cldd[2] + "," + cldd[3] + "," + cldd[4] + ")\r\nground(" + cgrnd[0] + "," + cgrnd[1] + "," + cgrnd[2] + ")\r\ntexture(" + texture[0] + "," + texture[1] + "," + texture[2] + "," + texture[3] + ")\r\nfadefrom(" + origfade + ")\r\ndensity(" + ((Medium.fogd + 1) / 2 - 1) + ")\r\nmountains(" + Medium.mgen + ")\r\nnlaps(" + CheckPoints.nlaps + ")\r\n";
        if (!trackname.equals("")) {

            tstage = "" + tstage + "soundtrack(" + trackname + "," + trackvol + "," + tracksize + ")\r\n";
        }
        for (int i = 0; i < 3; i++) {
            snap[i] = (int) (Medium.snap[i] / 1.2F + 50.0F);
        }
        if (snap[0] + snap[1] + snap[2] <= 110) {

            tstage = "" + tstage + "lightson()\r\n";
        }

        tstage = "" + tstage + "\r\n";
    }

    private void sortstage() {
        final int[] is = new int[nob * 2];
        final int[] is242 = new int[nob * 2];
        for (int i = 0; i < nob; i++) {
            is[i] = 0;
        }
        int i = 0;
        int i243 = 0;
        is242[i243] = 0;
        i243++;
        boolean bool = false;
        int i244 = 0;
        while (!bool) {
            final int[] is245 = {
                    co[i].x + atp[co[i].colok][0], co[i].x + atp[co[i].colok][2]
            };
            final int[] is246 = {
                    co[i].z + atp[co[i].colok][1], co[i].z + atp[co[i].colok][3]
            };
            int i247 = co[i].roofat;
            if (co[i].colok == 2) {
                i247 += 30;
            }
            if (co[i].colok == 3) {
                i247 -= 30;
            }
            if (co[i].colok == 15) {
                i247 -= 90;
            }
            if (co[i].colok == 20) {
                i247 -= 180;
            }
            if (co[i].colok == 26) {
                i247 -= 90;
            }
            rot(is245, is246, co[i].x, co[i].z, i247, 2);
            int i248 = -1;
            int i249 = -1;
            if (i244 != 0) {
                for (int i250 = 0; i250 < nob; i250++) {
                    boolean bool251 = false;
                    if (i243 == 2 && i250 == 0) {
                        bool251 = true;
                    }
                    if (i != i250 && !bool251 && is[i250] == 0 && (co[i250].colok <= 14 || co[i250].colok >= 33) && (co[i250].colok < 39 || co[i250].colok >= 46) && co[i250].colok < 52) {
                        int i252 = 0;
                        if (co[i250].colok != 2 && co[i250].colok != 3 && co[i250].colok != 4 && co[i250].colok != 7 && co[i250].colok != 9) {
                            if (i244 == 1 && co[i250].z > co[i].z && Math.abs(co[i250].x - co[i].x) < 1000 && (co[i250].roofat == 180 || co[i250].roofat == 0)) {
                                i252 = 1;
                            }
                            if (i244 == 2 && co[i250].z < co[i].z && Math.abs(co[i250].x - co[i].x) < 1000 && (co[i250].roofat == 180 || co[i250].roofat == 0)) {
                                i252 = 1;
                            }
                            if (i244 == 3 && co[i250].x > co[i].x && Math.abs(co[i250].z - co[i].z) < 1000 && (co[i250].roofat == 90 || co[i250].roofat == -90)) {
                                i252 = 1;
                            }
                            if (i244 == 4 && co[i250].x < co[i].x && Math.abs(co[i250].z - co[i].z) < 1000 && (co[i250].roofat == 90 || co[i250].roofat == -90)) {
                                i252 = 1;
                            }
                        } else {
                            i252 = 2;
                        }
                        if (i252 != 0) {
                            final int[] is253 = {
                                    co[i250].x + atp[co[i250].colok][0], co[i250].x + atp[co[i250].colok][2]
                            };
                            final int[] is254 = {
                                    co[i250].z + atp[co[i250].colok][1], co[i250].z + atp[co[i250].colok][3]
                            };
                            i247 = co[i250].roofat;
                            if (co[i250].colok == 2) {
                                i247 += 30;
                            }
                            if (co[i250].colok == 3) {
                                i247 -= 30;
                            }
                            if (co[i250].colok == 15) {
                                i247 -= 90;
                            }
                            if (co[i250].colok == 20) {
                                i247 -= 180;
                            }
                            if (co[i250].colok == 26) {
                                i247 -= 90;
                            }
                            rot(is253, is254, co[i250].x, co[i250].z, i247, 2);
                            if (i250 != 0) {
                                final int i256 = pyn(is253[0], is245[0], is254[0], is246[0]);
                                if (i256 >= 0 && (i256 < 100 || i252 != 2) && (i256 < i248 || i248 == -1)) {
                                    i248 = i256;
                                    i249 = i250;
                                }
                            }
                            int i257 = pyn(is253[1], is245[0], is254[1], is246[0]);
                            if (i257 >= 0 && (i257 < 100 || i252 != 2) && (i257 < i248 || i248 == -1)) {
                                i248 = i257;
                                i249 = i250;
                            }
                            if (i != 0) {
                                if (i250 != 0) {
                                    i257 = pyn(is253[0], is245[1], is254[0], is246[1]);
                                    if (i257 >= 0 && (i257 < 100 || i252 != 2) && i257 < i248) {
                                        i248 = i257;
                                        i249 = i250;
                                    }
                                }
                                i257 = pyn(is253[1], is245[1], is254[1], is246[1]);
                                if (i257 >= 0 && (i257 < 100 || i252 != 2) && i257 < i248) {
                                    i248 = i257;
                                    i249 = i250;
                                }
                            }
                        }
                    }
                }
            }
            if (i249 == -1) {
                for (int i258 = 0; i258 < nob; i258++) {
                    boolean bool259 = false;
                    if (i243 == 2 && i258 == 0) {
                        bool259 = true;
                    }
                    if (i != i258 && !bool259 && is[i258] == 0 && (co[i258].colok <= 14 || co[i258].colok >= 33) && (co[i258].colok < 39 || co[i258].colok >= 46) && co[i258].colok < 52) {
                        final int[] is260 = {
                                co[i258].x + atp[co[i258].colok][0], co[i258].x + atp[co[i258].colok][2]
                        };
                        final int[] is261 = {
                                co[i258].z + atp[co[i258].colok][1], co[i258].z + atp[co[i258].colok][3]
                        };
                        i247 = co[i258].roofat;
                        if (co[i258].colok == 2) {
                            i247 += 30;
                        }
                        if (co[i258].colok == 3) {
                            i247 -= 30;
                        }
                        if (co[i258].colok == 15) {
                            i247 -= 90;
                        }
                        if (co[i258].colok == 20) {
                            i247 -= 180;
                        }
                        if (co[i258].colok == 26) {
                            i247 -= 90;
                        }
                        rot(is260, is261, co[i258].x, co[i258].z, i247, 2);
                        if (i258 != 0) {
                            final int i263 = pyn(is260[0], is245[0], is261[0], is246[0]);
                            if (i263 >= 0 && (i263 < i248 || i248 == -1)) {
                                i248 = i263;
                                i249 = i258;
                            }
                        }
                        int i264 = pyn(is260[1], is245[0], is261[1], is246[0]);
                        if (i264 >= 0 && (i264 < i248 || i248 == -1)) {
                            i248 = i264;
                            i249 = i258;
                        }
                        if (i != 0) {
                            if (i258 != 0) {
                                i264 = pyn(is260[0], is245[1], is261[0], is246[1]);
                                if (i264 >= 0 && i264 < i248) {
                                    i248 = i264;
                                    i249 = i258;
                                }
                            }
                            i264 = pyn(is260[1], is245[1], is261[1], is246[1]);
                            if (i264 >= 0 && i264 < i248) {
                                i248 = i264;
                                i249 = i258;
                            }
                        }
                    }
                }
            }
            if (i249 != -1) {
                i244 = 0;
                if (co[i249].colok != 2 && co[i249].colok != 3 && co[i249].colok != 4 && co[i249].colok != 7 && co[i249].colok != 9) {
                    if ((co[i249].roofat == 180 || co[i249].roofat == 0) && co[i249].z > co[i].z) {
                        i244 = 1;
                    }
                    if ((co[i249].roofat == 180 || co[i249].roofat == 0) && co[i249].z < co[i].z) {
                        i244 = 2;
                    }
                    if ((co[i249].roofat == 90 || co[i249].roofat == -90) && co[i249].x > co[i].x) {
                        i244 = 3;
                    }
                    if ((co[i249].roofat == 90 || co[i249].roofat == -90) && co[i249].x < co[i].x) {
                        i244 = 4;
                    }
                }
                if (co[i249].colok == 4 || co[i249].colok == 7 || co[i249].colok == 9) {
                    is[i249] = 2;
                } else {
                    is[i249] = 1;
                }
                if (co[i249].colok >= 46 && co[i249].colok <= 51) {
                    is[i249] = 6;
                }
                i = i249;
                if (i249 == 0) {
                    is[0] = 1;
                    bool = true;
                } else {
                    is242[i243] = i249;
                    i243++;
                }
            } else {
                is[0] = 1;
                bool = true;
            }
        }
        for (int i265 = 0; i265 < nob; i265++)
            if (is[i265] == 0 && (co[i265].colok <= 14 || co[i265].colok >= 33) && (co[i265].colok < 39 || co[i265].colok >= 46) && co[i265].colok < 52) {
                is242[i243] = i265;
                i243++;
            }
        for (int i266 = 0; i266 < i243; i266++)
            if (co[is242[i266]].colok >= 46 && co[is242[i266]].colok <= 51) {
                for (int i267 = i266 + 1; i267 < i243; i267++) {
                    final int i268 = pyn(co[is242[i266]].x, co[is242[i267]].x, co[is242[i266]].z, co[is242[i267]].z);
                    if (i268 >= 0 && (co[is242[i267]].colok < 46 || co[is242[i266]].colok > 51) && i268 < (co[is242[i266]].maxR + co[is242[i267]].maxR) / 100 * ((co[is242[i266]].maxR + co[is242[i267]].maxR) / 100)) {
                        final int i269 = is242[i267];
                        System.arraycopy(is242, i266, is242, i266 + 1, i267 - i266);
                        is242[i266] = i269;
                        is[is242[i266]] = 0;
                        i266++;
                    }
                }
            }
        int i271 = 1;
        for (int i272 = 0; i272 < CheckPoints.nsp; i272++) {
            for (int i273 = 0; i273 < nob; i273++)
                if (co[i273].wh == i272 + 1 && (co[i273].colok == 30 || co[i273].colok == 32 || co[i273].colok == 54)) {
                    int i274 = -1;
                    int i275 = -1;
                    for (int i276 = i271; i276 < i243; i276++)
                        if (co[is242[i276]].colok != 30 && co[is242[i276]].colok != 32 && co[is242[i276]].colok != 54) {
                            final int i277 = pyn(co[i273].x, co[is242[i276]].x, co[i273].z, co[is242[i276]].z);
                            if (i277 >= 0 && (i277 < i274 || i274 == -1)) {
                                i274 = i277;
                                i275 = i276;
                            }
                        }
                    if (i275 != -1) {
                        is[is242[i275]] = 0;
                        System.arraycopy(is242, i275, is242, i275 + 1, i243 - i275);
                        is242[i275 + 1] = i273;
                        i271 = i275 + 1;
                        i243++;
                    } else {
                        is242[i243] = i273;
                        i271 = i243;
                        i243++;
                    }
                }
        }
        for (int i279 = 0; i279 < nob; i279++)
            if (co[i279].wh == 0 && (co[i279].colok == 30 || co[i279].colok == 32 || co[i279].colok == 54)) {
                int i280 = -1;
                int i281 = -1;
                for (int i282 = i271; i282 < i243; i282++)
                    if (co[is242[i282]].colok != 30 && co[is242[i282]].colok != 32 && co[is242[i282]].colok != 54) {
                        final int i283 = pyn(co[i279].x, co[is242[i282]].x, co[i279].z, co[is242[i282]].z);
                        if (i283 >= 0 && (i283 < i280 || i280 == -1)) {
                            i280 = i283;
                            i281 = i282;
                        }
                    }
                if (i281 != -1) {
                    is[is242[i281]] = 0;
                    System.arraycopy(is242, i281, is242, i281 + 1, i243 - i281);
                    is242[i281 + 1] = i279;
                    i243++;
                } else {
                    is242[i243] = i279;
                    i243++;
                }
            }
        for (int i285 = 0; i285 < nob; i285++)
            if (co[i285].colok == 31) {
                int i286 = -1;
                int i287 = -1;
                for (int i288 = 0; i288 < i243; i288++) {
                    final int i289 = pyn(co[i285].x, co[is242[i288]].x, co[i285].z, co[is242[i288]].z);
                    if (i289 >= 0 && (i289 < i286 || i286 == -1)) {
                        i286 = i289;
                        i287 = i288;
                    }
                }
                if (i287 != -1) {
                    System.arraycopy(is242, i287, is242, i287 + 1, i243 - i287);
                    is242[i287] = i285;
                    i243++;
                } else {
                    is242[i243] = i285;
                    i243++;
                }
            }
        for (int i291 = 0; i291 < nob; i291++)
            if (co[i291].colok == 15 || co[i291].colok == 27 || co[i291].colok == 28 || co[i291].colok == 41 || co[i291].colok == 44 || co[i291].colok == 52 || co[i291].colok == 53) {
                int i292 = -1;
                for (int i293 = 0; i293 < i243; i293++)
                    if ((co[is242[i293]].colok <= 14 || co[is242[i293]].colok >= 33) && co[is242[i293]].colok < 39) {
                        final int i294 = pyn(co[i291].x, co[is242[i293]].x, co[i291].z, co[is242[i293]].z);
                        if (i294 >= 0 && i294 < (co[i291].maxR + co[is242[i293]].maxR) / 100 * ((co[i291].maxR + co[is242[i293]].maxR) / 100)) {
                            i292 = i293;
                        }
                    }
                if (i292 != -1) {
                    System.arraycopy(is242, i292, is242, i292 + 1, i243 - i292);
                    is242[i292 + 1] = i291;
                    i243++;
                } else {
                    is242[i243] = i291;
                    i243++;
                }
            }
        for (int i296 = 0; i296 < nob; i296++)
            if (co[i296].colok >= 16 && co[i296].colok <= 25 || co[i296].colok == 40 || co[i296].colok == 42 || co[i296].colok == 43 || co[i296].colok == 45) {
                int i297 = -1;
                for (int i298 = 0; i298 < i243; i298++)
                    if ((co[is242[i298]].colok <= 14 || co[is242[i298]].colok >= 33) && co[is242[i298]].colok < 39) {
                        final int i299 = pyn(co[i296].x, co[is242[i298]].x, co[i296].z, co[is242[i298]].z);
                        if (i299 >= 0 && i299 < (co[i296].maxR + co[is242[i298]].maxR) / 100 * ((co[i296].maxR + co[is242[i298]].maxR) / 100)) {
                            if (is[is242[i298]] != 0) {
                                is[is242[i298]] = 0;
                                if (co[i296].colok != 20) {
                                    is[i296] = 3;
                                } else {
                                    is[i296] = 5;
                                }
                            }
                            i297 = i298;
                        }
                    }
                if (i297 != -1) {

                }
                if (i297 != -1) {
                    System.arraycopy(is242, i297, is242, i297 + 1, i243 - i297);
                    is242[i297 + 1] = i296;
                    i243++;
                } else {
                    is242[i243] = i296;
                    i243++;
                }
            }
        for (int i301 = 0; i301 < nob; i301++)
            if (co[i301].colok == 26 || co[i301].colok == 39) {
                boolean bool302 = false;
                if (ThreadLocalRandom.current().nextDouble() > ThreadLocalRandom.current().nextDouble()) {
                    bool302 = true;
                    if (co[i301].colok == 39)
                        if (ThreadLocalRandom.current().nextDouble() > ThreadLocalRandom.current().nextDouble()) {
                            bool302 = false;
                        } else if (ThreadLocalRandom.current().nextDouble() > ThreadLocalRandom.current().nextDouble()) {
                            bool302 = false;
                        }
                }
                int i303 = -1;
                for (int i304 = 0; i304 < i243; i304++)
                    if ((co[is242[i304]].colok <= 14 || co[is242[i304]].colok >= 33) && co[is242[i304]].colok < 39) {
                        final int i305 = pyn(co[i301].x, co[is242[i304]].x, co[i301].z, co[is242[i304]].z);
                        if (i305 >= 0 && i305 < (co[i301].maxR + co[is242[i304]].maxR) / 100 * ((co[i301].maxR + co[is242[i304]].maxR) / 100)) {
                            boolean bool306 = false;
                            if (co[i301].colok == 26) {
                                if (co[i301].roofat == 90 && co[is242[i304]].x > co[i301].x) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == -90 && co[is242[i304]].x < co[i301].x) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == 0 && co[is242[i304]].z < co[i301].z) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == 180 && co[is242[i304]].z > co[i301].z) {
                                    bool306 = true;
                                }
                            }
                            if (co[i301].colok == 39) {
                                if (co[i301].roofat == 90 && co[is242[i304]].z > co[i301].z) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == -90 && co[is242[i304]].z < co[i301].z) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == 0 && co[is242[i304]].x > co[i301].x) {
                                    bool306 = true;
                                }
                                if (co[i301].roofat == 180 && co[is242[i304]].x < co[i301].x) {
                                    bool306 = true;
                                }
                            }
                            if (bool306) {
                                if (is[is242[i304]] == 1 && bool302) {
                                    is[is242[i304]] = 0;
                                    is[i301] = 4;
                                }
                                i303 = i304;
                            }
                        }
                    }
                if (i303 != -1) {
                    System.arraycopy(is242, i303, is242, i303 + 1, i243 - i303);
                    is242[i303 + 1] = i301;
                    i243++;
                } else {
                    is242[i243] = i301;
                    i243++;
                }
            }
        for (int i308 = 0; i308 < nob; i308++) //FIXME: Does this cause duplicate parts?
            if (co[i308].colok >= 55 && co[i308].colok <= maxpart || co[i308].colok == bumppart) { //TODO: this has to be changed for new placeable checkpoints/non-set()s
                is242[i243] = i308;
                i243++;
            }
        int i309 = 0;
        int i310 = 0;
        int i311 = 0;
        int i312 = 0;
        bstage = "";
        for (int i313 = 0; i313 < i243; i313++) {
            if (co[is242[i313]].colok != 30 && co[is242[i313]].colok != 31 && co[is242[i313]].colok != 32 && co[is242[i313]].colok != 54 && co[is242[i313]].colok != bumppart) {
                String string = "";
                if (is[is242[i313]] == 1) {
                    string = "p";
                }
                if (is[is242[i313]] == 2) {
                    string = "pt";
                }
                if (is[is242[i313]] == 3) {
                    string = "pr";
                }
                if (is[is242[i313]] == 4) {
                    string = "ph";
                }
                if (is[is242[i313]] == 5) {
                    string = "pl";
                }
                if (is[is242[i313]] == 6) {
                    string = "pr";
                }

                System.out.println("placing");
                System.out.println("roof2: " + co[is242[i313]].roofat);
                if (co[is242[i313]].roofat == 250) {

                }
                if (!floats) {
                    bstage = "" + bstage + "set(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                } else {
                    bstage = "" + bstage + "set(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].y + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                }
            }
            if (co[is242[i313]].colok == 30 || co[is242[i313]].colok == 32) {
                if (co[is242[i313]].roofat == 180) {
                    co[is242[i313]].roofat = 0;
                }
                String string = "";
                if (co[is242[i313]].wh != 0) {
                    string = "r";
                }

                if (floats) {
                    bstage = "" + bstage + "chk(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].y + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                } else {
                    bstage = "" + bstage + "chk(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                }
            }
            if (co[is242[i313]].colok == 54) {
                if (co[is242[i313]].roofat == 180) {
                    co[is242[i313]].roofat = 0;
                }
                String string = "";
                if (co[is242[i313]].wh != 0) {
                    string = "r";
                }

                //   this.bstage = "" + (this.bstage) + ("chk(")
                //		 + (co[is242[i313]].colok + 10) + (",") + (co[is242[i313]].x) + (",")
                //		 + (co[is242[i313]].z) + (",") + (co[is242[i313]].y) + (",") + (co[is242[i313]].roofat)
                //		 + (")") + (string) + ("\r\n");
                if (floats) {
                    bstage = "" + bstage + "chk(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].y + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                } else {
                    bstage = "" + bstage + "chk(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].roofat + ")" + string + "\r\n";
                }
            }
            if (co[is242[i313]].colok == 31) {

                bstage = "" + bstage + "fix(" + (co[is242[i313]].colok + 10) + "," + co[is242[i313]].x + "," + co[is242[i313]].z + "," + co[is242[i313]].y + "," + co[is242[i313]].roofat + ")\r\n";
            }
            if (co[is242[i313]].colok == bumppart) {

                bstage = "" + bstage + "pile(" + co[is242[i313]].srz + "," + co[is242[i313]].srx + "," + co[is242[i313]].sry + "," + co[is242[i313]].x + "," + co[is242[i313]].z + ")\r\n";
            }
            if (co[is242[i313]].x + co[is242[i313]].maxR > i309) {
                i309 = co[is242[i313]].x + co[is242[i313]].maxR;
            }
            if (co[is242[i313]].x - co[is242[i313]].maxR < i311) {
                i311 = co[is242[i313]].x - co[is242[i313]].maxR;
            }
            if (co[is242[i313]].z + co[is242[i313]].maxR > i310) {
                i310 = co[is242[i313]].z + co[is242[i313]].maxR;
            }
            if (co[is242[i313]].z - co[is242[i313]].maxR < i312) {
                i312 = co[is242[i313]].z - co[is242[i313]].maxR;
            }
        }
        int i319 = i311;
        int i320 = i309;
        final int i321 = (int) ((i320 - i319) / 4800.0F) + 1;
        int i322 = (i321 * 4800 - (i320 - i319)) / 2;
        i319 -= i322;
        i320 += i322;
        final int i323 = i319 + 2400;
        int i324 = i312;
        int i325 = i310;
        final int i326 = (int) ((i325 - i324) / 4800.0F) + 1;
        i322 = (i326 * 4800 - (i325 - i324)) / 2;
        i324 -= i322;
        i325 += i322;
        final int i327 = i324 + 2400;

        bstage = "" + bstage + "\r\nmaxl(" + i326 + "," + i319 + "," + i327 + ")\r\nmaxb(" + i321 + "," + i324 + "," + i323 + ")\r\nmaxr(" + i326 + "," + i320 + "," + i327 + ")\r\nmaxt(" + i321 + "," + i325 + "," + i323 + ")\r\n";
    }

    @Override
    public void start() {
        if (thredo == null) {
            thredo = new Thread(this);
        }
        thredo.start();
    }

    @Override
    public void stop() {
        exwist = true;
    }

    @Override
    public void update(final Graphics graphics) {
        paint(graphics);
    }

    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Stage Maker");
        StageMaker stageMaker = new StageMaker();
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension screenSize = tk.getScreenSize();
        final Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(gameFrame.getGraphicsConfiguration());
        gameFrame.add(stageMaker, BorderLayout.CENTER);
        gameFrame.setSize(800, 580);
        gameFrame.setResizable(false);
        gameFrame.setPreferredSize(new Dimension(800, 580));

        gameFrame.setLocation(screenSize.width / 2 - 700 / 2, (screenSize.height - scnMax.bottom) / 2 - 450 / 2);
        /**
         * make sure everything closes on close
         */
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setIconImage(Toolkit.getDefaultToolkit().createImage(Madness.fpath + "data/iconsm.png"));
        gameFrame.setIgnoreRepaint(true);

        stageMaker.init();
        stageMaker.start();
        
        gameFrame.pack();
        stageMaker.setSize(new Dimension(800, 580));
        stageMaker.setMinimumSize(new Dimension(800, 580));
        stageMaker.setPreferredSize(new Dimension(800, 580));
        gameFrame.setVisible(true);
    
    }
}

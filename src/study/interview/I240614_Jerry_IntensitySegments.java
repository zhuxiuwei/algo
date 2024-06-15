package study.interview;

import javafx.collections.transformation.SortedList;

import java.util.*;

/***
 * *
 * *
 *
 * -----------
 * Intensity Segments
 * Guidelines
 * In this part of the interview process, we’d like you to come up with an algorithm to solve the problem as
 * described below. The problem itself is quite simple to solve. What we are mainly looking for in this test
 * (other than that the solution should work) is, how well you actually write the code. We want to see how you
 * write production-quality code in a team setting where multiple developers will be collaborating on the
 * codebase.
 * Specifically, we are looking for: simple, clean, readable and maintainable code, for example:
 * • Code organization and submission format. Things like code organization, readability, documentation,
 * testing and deliverability are most important here.
 * • Your mastery of idiomatic programming.
 * If you choose to use a programming language other than JS, please still make sure you stick to the idiomatic
 * way of that programming language.
 *
 * Problem Set：
 * Please use the exact class name, method name and input/output formats in this code template.
 * We are looking for a program that manages “intensity” by segments. Segments are intervals from -infinity to
 * infinity, we’d like you to implement functions that updates intensity by an integer amount for a given range.
 * All intensity starts with 0. Please implement these three functions:
 * /**
 * **
 * NOTE: Feel free to add any extra member variables/functions you like.
// * */
// *export
//
//class IntensitySegments {
// * /**
//  * *
//  * */
//         *
//
//    add(from, to, amount) {
// * // TODO: implement this
// *}
// * /**
//  * *
//  * */
//         *
//
//    set(from, to, amount) {
// * // TODO: implement this
// *}
// * /**
//  * *
//  * */
//         *
//
//    toString() {
// * // TODO: implement this
// *}
// *
//}
// *
//         * // Here is an example sequence:
//         * // (data stored as an array of start point and value for each segment.)
//         *const segments=new IntensitySegments();
//         *segments.toString(); // Should be "[]"
//         *segments.add(10,30,1);
//         *segments.toString(); // Should be: "[[10,1],[30,0]]"
//         *segments.add(20,40,1);
//         *segments.toString(); // Should be: "[[10,1],[20,2],[30,1],[40,0]]"
//         *segments.add(10,40,-2);
//         *segments.toString(); // Should be: "[[10,-1],[20,0],[30,-1],[40,0]]"
//         * // Another example sequence:
//         *const segments=new IntensitySegments();
//         *segments.toString(); // Should be "[]"
//         *segments.add(10,30,1);
//         *segments.toString(); // Should be "[[10,1],[30,0]]"
//         *segments.add(20,40,1);
//         *segments.toString(); // Should be "[[10,1],[20,2],[30,1],[40,0]]"
//         *segments.add(10,40,-1);
//         *segments.toString(); // Should be "[[20,1],[30,0]]"
//         *segments.add(10,40,-1);
//         *segments.toString(); // Should be "[[10,-1],[20,0],[30,-1],[40,0]]"
// *
// */
public class I240614_Jerry_IntensitySegments {

    private Set<Segment> segments = new TreeSet<Segment>();

    public void add(int start, int end, int value) {
        if(value == 0 || start > end){   //invalid input
            return;
        }
        Segment newSeg = new Segment(start, end - 1, value);
        Iterator <Segment> iterator = segments.iterator();
        boolean found = false;  // if the new segment found a suitable place
        Set<Segment> removedSegments = new HashSet<>(); //use this set to record the removed segments during add process.
        Set<Segment> newSegments = new HashSet<>(); //use this set to record the newly generated segments during add process.
        while (iterator.hasNext() && !found) {
            Segment seg = iterator.next();
            if (newSeg.getEnd() < seg.getStart()) {
                //new segment is on left side of current segment and no intersection, a suitable place found, put the new segment in set.
                //         [    ]  <-- current segment
                //  [   ]          <-- new segment
                newSegments.add(newSeg);
                found = true;
            }else if(newSeg.getStart() > seg.getEnd()){
                //new segment is on right side of current segment and no intersection, so continue to compare.
                //  [    ]          <-- current segment
                //          [   ]   <-- new segment
                continue;
            }else if(newSeg.getStart() <= seg.getStart() && newSeg.getEnd() <= seg.getEnd()){
                // the two segment has intersection. like:
                //    [    ] <-- current segment
                //  [   ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getStart() < seg.getStart())
                    newSegments.add(new Segment(newSeg.getStart(), seg.getStart() - 1, newSeg.getValue()));    //left part
                newSegments.add(new Segment(seg.getStart(), newSeg.getEnd(), newSeg.getValue() + seg.getValue())); //intersection part
                if(newSeg.getEnd() < seg.getEnd())
                    newSegments.add(new Segment(newSeg.getEnd() + 1, seg.getEnd(), seg.getValue()));   //right part
                found = true;
             }else if(newSeg.getStart() >= seg.getStart() && newSeg.getEnd() >= seg.getEnd()){
                // the two segment has intersection. like:
                // [    ]     <-- current segment
                //   [    ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getStart() > seg.getStart())
                    newSegments.add(new Segment(seg.getStart(), newSeg.getStart() - 1, seg.getValue()));   //left part
                newSegments.add(new Segment(newSeg.getStart(), seg.getEnd(), newSeg.getValue() + seg.getValue())); //intersection part
                if(newSeg.getEnd() == seg.getEnd()){
                    found = true;   //there is no right part
                }else {
                    newSeg = new Segment(seg.getEnd() + 1, newSeg.getEnd(), newSeg.getValue());    //right part exists, continue compare
                }
            }else if(newSeg.getStart() >= seg.getStart() && newSeg.getEnd() <= seg.getEnd()){
                // the two segment has intersection. like:
                // [        ]     <-- current segment
                //   [    ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getStart() > seg.getStart())
                    newSegments.add(new Segment(seg.getStart(), newSeg.getStart() - 1, seg.getValue()));   //left part
                newSegments.add(new Segment(newSeg.getStart(), newSeg.getEnd(), newSeg.getValue() + seg.getValue())); //intersection part
                if(newSeg.getEnd() < seg.getEnd())
                    newSegments.add(new Segment(newSeg.getEnd() + 1, seg.getEnd(), seg.getValue()));    //right part
                found = true;
            }else if(newSeg.getStart() <= seg.getStart() && newSeg.getEnd() >= seg.getEnd()){
                // the two segment has intersection. like:
                //    [   ]      <-- current segment
                //  [       ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getStart() < seg.getStart())
                    newSegments.add(new Segment(newSeg.getStart(), seg.getStart() - 1, newSeg.getValue()));   //left part
                newSegments.add(new Segment(seg.getStart(), seg.getEnd(), newSeg.getValue() + seg.getValue())); //intersection part
                if(newSeg.getEnd() == seg.getEnd()){
                    found = true;   //there is no right part
                }else {
                    newSeg = new Segment(seg.getEnd() + 1, newSeg.getEnd(), newSeg.getValue()); //right part exists, continue compare
                }
            }
        }
        if(!found){   //in case the new segment's correct location is the most right side
            newSegments.add(newSeg);
        }
        for(Segment segment: removedSegments){
            segments.remove(segment);
        }
        for(Segment segment: newSegments){
            segments.add(segment);
        }
    }

    public void set(int start, int end, int value) {
        if( start > end){   //invalid input
            return;
        }
        Segment newSeg = new Segment(start, end - 1, value);
        Iterator <Segment> iterator = segments.iterator();
        boolean found = false;  // if the new segment found a suitable place
        Set<Segment> removedSegments = new HashSet<>(); //use this set to record the removed segments during set process.
        Set<Segment> newSegments = new HashSet<>(); //use this set to record the newly generated segments during set process.
        while (iterator.hasNext() && !found) {
            Segment seg = iterator.next();
            if (newSeg.getEnd() < seg.getStart()) {
                //new segment is on left side of current segment and no intersection, a suitable place found, put the new segment in set.
                //         [    ]  <-- current segment
                //  [   ]          <-- new segment
                newSegments.add(newSeg);
                found = true;
            }else if(newSeg.getStart() > seg.getEnd()){
                //new segment is on right side of current segment and no intersection, so continue to compare.
                //  [    ]          <-- current segment
                //          [   ]   <-- new segment
                continue;
            }else if(newSeg.getStart() <= seg.getStart() && newSeg.getEnd() <= seg.getEnd()){
                // the two segment has intersection. like:
                //    [    ] <-- current segment
                //  [   ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getValue() == seg.getValue()) {    //two segments should be combined
                    newSegments.add(new Segment(newSeg.getStart(), seg.getEnd(), newSeg.getValue()));
                }else {
                    newSegments.add(newSeg);    //left part
                    if(newSeg.getEnd() < seg.getEnd())
                        newSegments.add(new Segment(newSeg.getEnd() + 1, seg.getEnd(), seg.getValue()));    //right part
                }
                found = true;
            }else if(newSeg.getStart() >= seg.getStart() && newSeg.getEnd() >= seg.getEnd()){
                // the two segment has intersection. like:
                // [    ]     <-- current segment
                //   [    ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getValue() == seg.getValue()) {    // two segments should be combined
                    if(newSeg.getEnd() == seg.getEnd()){    // No need continue compare
                        found = true;
                        newSegments.add(seg);
                    }else {     // need continue compare
                        newSeg = new Segment(seg.getStart(), newSeg.getEnd(), newSeg.getValue());
                    }
                }else {
                    if(newSeg.getStart() > seg.getStart())
                        newSegments.add(new Segment(seg.getStart(), newSeg.getStart() - 1, seg.getValue()));   //left part
                    if(newSeg.getEnd() == seg.getEnd()){    //right part, no need continue compare
                        found = true;
                        newSegments.add(newSeg);
                    }
                    // else, continue compare with current segment
                }
            }else if(newSeg.getStart() >= seg.getStart() && newSeg.getEnd() <= seg.getEnd()){
                // the two segment has intersection. like:
                // [        ]     <-- current segment
                //   [    ]    <-- new segment
                if(newSeg.getValue() != seg.getValue()) {
                    removedSegments.add(seg);
                    if (newSeg.getStart() > seg.getStart())
                        newSegments.add(new Segment(seg.getStart(), newSeg.getStart() - 1, seg.getValue()));   //left part
                    newSegments.add(newSeg); //intersection part
                    if (newSeg.getEnd() < seg.getEnd())
                        newSegments.add(new Segment(newSeg.getEnd() + 1, seg.getEnd(), seg.getValue()));    //right part
                    found = true;
                }
            }else if(newSeg.getStart() <= seg.getStart() && newSeg.getEnd() >= seg.getEnd()){
                // the two segment has intersection. like:
                //    [   ]      <-- current segment
                //  [       ]    <-- new segment
                removedSegments.add(seg);
                if(newSeg.getEnd() == seg.getEnd()){    // no need continue compare, use newSeg to replace current segment
                    found = true;
                    newSegments.add(newSeg);
                }
                // else, continue compare with current segment
            }
        }
        if(!found){   //in case the new segment's correct location is the most right side
            newSegments.add(newSeg);
        }
        for(Segment segment: removedSegments){
            segments.remove(segment);
        }
        for(Segment segment: newSegments){
            segments.add(segment);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Set<Segment> removedSegments = new HashSet<>(); //use this set to record the removed segments during toString process.
        if(segments.size() > 0) {
            Iterator <Segment> iterator = segments.iterator();
            int count = 0, lastIdx = -1;
            while (iterator.hasNext()) {
                count++;
                Segment segment = iterator.next();
                if ((segment.getValue() == 0 && count == 1) || (segment.getValue() == 0 && count == segments.size())) {
                    removedSegments.add(segment);
                }else {
                    sb.append("[");
                    sb.append(segment.getStart());
                    sb.append(",");
                    sb.append(segment.getValue());
                    sb.append("]");
                    sb.append(",");
                }
                if(segment.getValue() != 0) {
                    lastIdx = segment.getEnd() + 1;
                }
            }
            sb.append("[");
            sb.append(lastIdx);
            sb.append(",0]");
        }
        sb.append("]");
        for(Segment segment: removedSegments){
            segments.remove(segment);
        }
        return sb.toString();
    }

    /**
     * For test.
     * For convenience, here we are using the main function with printing to verify the results.
     * In production environment, it is recommended to use unit testing frameworks along with assertions techniques.
     */
    public static void main(String[] args) {

        /**
         * Test add
         */
        System.out.println("----Test Add------");
        I240614_Jerry_IntensitySegments intensitySegments = new I240614_Jerry_IntensitySegments();
        System.out.println(intensitySegments.toString());   //[]
        intensitySegments.add(10, 30, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[30,0]]
        intensitySegments.add(20, 40, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[20,2],[30,1],[40,0]]
        intensitySegments.add(10, 40, -1);
        System.out.println(intensitySegments.toString());   //[[20,1],[30,0]]
        intensitySegments.add(10, 40, -1);
        System.out.println(intensitySegments.toString());   //[[10,-1],[20,0],[30,-1],[40,0]]

        intensitySegments = new I240614_Jerry_IntensitySegments();
        intensitySegments.add(10, 30, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[30,0]]
        intensitySegments.add(29, 40, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[29,2],[30,1],[40,0]]

        /**
         * Test set
         */
        System.out.println("----Test Set------");
        intensitySegments = new I240614_Jerry_IntensitySegments();
        System.out.println(intensitySegments.toString());   //[]
        intensitySegments.set(10, 30, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[30,0]]
        intensitySegments.set(20, 40, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[40,0]]
        intensitySegments.set(10, 40, -1);
        System.out.println(intensitySegments.toString());   //[[10,-1],[40,0]]
        intensitySegments.set(10, 40, -2);
        System.out.println(intensitySegments.toString());  //[[10,-2],[40,0]]

        intensitySegments = new I240614_Jerry_IntensitySegments();
        intensitySegments.set(10, 30, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[30,0]]
        intensitySegments.set(29, 40, 1);
        System.out.println(intensitySegments.toString());   //[[10,1],[40,0]]
        intensitySegments.set(20, 21, 0);
        System.out.println(intensitySegments.toString());   //[[10,1],[20,0],[21,1],[40,0]]
    }
}

/**
 * the segment class.
 * A comparable class that can be compared by 'start' property in asc order.
 */
class Segment implements Comparable<Segment>{
    public Segment(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
    private int start;
    private int end;
    private int value;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        return "[" + start + "," + end + "," + value + "]";
    }

    @Override
    public int compareTo(Segment other) {
        return this.start - other.start;
    }
}

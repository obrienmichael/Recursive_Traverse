
//This is my code
//Michael O'Brien
//CS312

package asn2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Class that serves as a map to traverse through
 */

public class Map
{
    protected Point [] [] map;
    protected Point goal;
    protected Point start;
    protected int width;
    protected int height;

    public Map(String fileName, Integer startRow, Integer startCol, Integer goalRow, Integer goalCol)
    {
        BufferedReader buffRead = null;
        try
        {
            buffRead = new BufferedReader(new FileReader(fileName));
            String line = buffRead.readLine();
            StringTokenizer tok = new StringTokenizer(line, " ");
            if (tok.countTokens() != 2)
            {
                System.out.println("oops bad line 1");
            }

            width = Integer.parseInt(tok.nextToken().toString());
            height = Integer.parseInt(tok.nextToken().toString());

            System.out.println("wid = " + width + " ht = " + height);

            map = new Point[width][height];

            int i, j;
            for(i=0; i<width; i++)
            {
                line = buffRead.readLine();
                System.out.println(line);
                tok = new StringTokenizer(line, " ");

                for(j=0; j<height; j++)
                {
                    int v = Integer.parseInt(tok.nextToken().toString());
                    map[i][j] = new Point(i, j, v);
                }
                start = map[startRow][startCol];
                goal = map[goalRow][goalCol];

            }
            System.out.println(start);
        }
        catch(Exception E)
        {
            System.out.println("OOPS bad sponge!@" + E);

        }
    }

    public boolean inBounds(int x, int y)
    {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    /*
    *returns the path of the recursive search method
    */
    public String recursiveSearch()
    {
        Deque<Point> path = visit(start, new ArrayDeque<Point>());
        System.out.println("path = " + path);
        return path.toString();
    }

    /*
    *method that recursively works to create a path from start to end
    */
    public Deque<Point> visit(Point p, Deque<Point> path)
    {
        p.changeToVisited();
        if (p.equals(goal))
        {
            path.add(p);
            return path;
        }
        for(Point neighbor : p)
        {
            if (neighbor.canVisit())
            {
                p.changeToVisited();
                path.add(p);
                Deque<Point> returnedPath = visit(neighbor,  path);
                if (returnedPath != null)
                    return returnedPath;
            }
        }

        return null;
    }

    public String toString()
    {
        String s = "";
        int i, j;
        for(i=0; i<width; i++)
        {
            for(j=0; j<height; j++)
            {
                s = s + map[i][j];
            }
            s = s + "\n";
        }
        return s;

    }
    //boolean method to check whether a point is the end point the programming is looking for
    public boolean goalTest(Point p)
    {
        if(p.equals(goal))
        {
            return true;
        }
        return false;
    }

    private class Point implements Iterable<Point>
    {
        protected boolean visited;
        protected int myRow;
        protected int myCol;
        protected int theValue;

        public Point(int x, int y, int value)
        {
            visited = false;
            myRow = x;
            myCol = y;
            theValue = value;
        }

        public boolean canVisit()
        {
            return theValue == 0 && visited==false;
        }

        public Iterator<Point> iterator()
        {
            return new PointIterator();
        }

        public String toString()
        {
            return "("+myRow + ", " + myCol + "[" + theValue + "])";
        }

        //method can be applied to a point to indicate that it has been visited
        public boolean isVisited()
        {
            return visited == true;
        }

        //assign the boolean value visited of an object to true
        public void changeToVisited()
        {
            visited = true;
        }

        private class PointIterator implements Iterator<Point>
        {
            private Point [] myNeighbors;
            private int current;

            public PointIterator()
            {
                current = 0;
                myNeighbors = new Point[9];

                int i, j;
                for(i=-1; i<=1; i++)
                {
                    for(j=-1; j<=1; j++)
                    {
                        if (i != 0 || j != 0)
                            if (inBounds(myRow+i, myCol+j))
                                myNeighbors[current++] = map[myRow+i][myCol+j];
                    }
                }

                myNeighbors[current] = null;
                current = 0;
            }

            public boolean hasNext()
            {
                return myNeighbors[current] != null;
            }

            public Point next()
            {
                if(!hasNext())
                    throw new NoSuchElementException();

                Point p = myNeighbors[current];
                current++;
                return p;
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    public void depthFirstSearch()
    {
        Deque<Point> path = DFS(start, new ArrayDeque());
        System.out.println(path);
    }

    /*
    *DFS search method
    */
    public Deque<Point> DFS(Point p, Deque<Point> path)
    {
        boolean added = false;
        p.changeToVisited();
        path.addFirst(p);
        //as long as the point is not the goal point
        while(!p.equals(goal))
        {
            added = false;
            for(Point neighbor: p)
            {

                if(neighbor.canVisit())
                {
                    added = true;
                    path.addFirst(neighbor);
                    neighbor.changeToVisited();
                    p = neighbor;
                    break;
                }
            }
            if(added!=true)
            {
                path.removeFirst();
                p = path.peekFirst();
            }

        }
        System.out.println("it's over");
        return path;
    }

     /*
  *calls the BFS search
  */

    public void breadthFirstSearch()
    {
        Deque<Point> bfsPath = BFS(start, new ArrayDeque());
    }

    /*
    *non recursive BFS search
    */
    public void BFS(Point p, Deque<Point> bfsPath)
    {
        p = start;
        bfsPath.addLast(start);     //add or enqueue start position
        start.changeToVisited();

        while(!bfsPath.isEmpty())
        {
            if (start == end)
            {
                System.out.print(bfsPath);
            }
            for(Point neighbor : p)
            {
                if(neighbor.canVisit())
                {
                    bfsPath.addLast(neighbor);
                    neighbor.changeToVisited();
                    p = neighbor;
                    break;
                }
            }
        }
    }


}
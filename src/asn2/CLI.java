//This is my code
//Michael O'Brien
//CS312

package asn2;

public class CLI
{

    public static void main(String [] args)
    {
        if (args.length != 5)
        {
            System.out.println("no!");
            System.exit(-1);
        }

        int startRow = Integer.parseInt(args[0]);
        int startCol = Integer.parseInt(args[1]);
        int endRow = Integer.parseInt(args[2]);
        int endCol = Integer.parseInt(args[3]);

        Map map = new Map(args[4], startRow, startCol, endRow, endCol);

        System.out.println(" ");
        System.out.println("this is printing"   +   "\n"  + map);
        System.out.println(" ");

        map.recursiveSearch();

        map.depthFirstSearch();

        map.breadthFirstSearch();
    }

}
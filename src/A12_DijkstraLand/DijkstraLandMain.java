package A12_DijkstraLand;


import java.util.List;

public class DijkstraLandMain {
    public static void main(String[] args) {
        Graph g = new ListGraph(14, false);
        g.setLand("ST",0);
        g.setLand("K",1);
        g.setLand("NÖ",2);
        g.setLand("OÖ",3);
        g.setLand("S",4);
        g.setLand("B",5);
        g.setLand("T",6);
        g.setLand("VO",7);
        g.setLand("W",8);
        g.setLand("DE",9);
        g.setLand("CZE",10);
        g.setLand("SLO",11);
        g.setLand("IT",12);
        g.setLand("H",13);

        g.addEdge(0, 5, 5);
        g.addEdge(0, 2, 4);
        g.addEdge(0, 3, 3);
        g.addEdge(0, 4, 7);
        g.addEdge(0, 1, 10);
        g.addEdge(4, 6, 2);
        g.addEdge(6, 7, 6);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 10, 3,true);
        g.addEdge(5, 13, 5,true);
        g.addEdge(0, 11, 1,true);
        g.addEdge(1, 12, 2,true);
        g.addEdge(6, 12, 8,true);
        g.addEdge(6, 9, 5,true);
        g.addEdge(4, 9, 3,true);
        g.addEdge(3, 9, 3,true);
        g.addEdge(7, 9, 4,true);
        List<Integer> way = Dijkstra.dijkstra(g,7,13);
        printWay(way,g);
    }
    public static void printWay(List<Integer> way, Graph graph) {
        if (way == null) {
            System.out.println("Kein Weg gefunden.");
            return;
        }
        for (int i=0; i < way.size(); i++) {
            if (i != 0)
                System.out.print(" -> ");
            System.out.print("[Land: " + graph.getLand(way.get(i)) + " | Stationsnummer: " + way.get(i) + "]");
        }
    }
}

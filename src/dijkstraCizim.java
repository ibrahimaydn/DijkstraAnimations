
import java.awt.*;
//import java.awt.Stroke;
//import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class dijkstraCizim {

    static class Kenar {

        int hedef;
        int ağırlık;

        public Kenar(int hedef, int ağırlık) {
            this.hedef = hedef;
            this.ağırlık = ağırlık;
        }
    }

    static class dugum implements Comparable<dugum> {

        int id;
        int mesafe;

        public dugum(int id, int mesafe) {
            this.id = id;
            this.mesafe = mesafe;
        }

        public int compareTo(dugum diğer) {
            return Integer.compare(mesafe, diğer.mesafe);
        }
    }

    static class GraphPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        List<List<Kenar>> graf;
        int[] onceki;
        Map<Integer, Integer> xKordinat;
        Map<Integer, Integer> yKordinat;
        int baslangic;

        public GraphPanel(List<List<Kenar>> graf, int baslangic) {
            this.graf = graf;
            this.baslangic = baslangic;
            int n = graf.size();
            onceki = new int[n];
            xKordinat = new HashMap<>();
            yKordinat = new HashMap<>();
            int geciciX;
            int geciciY;
            for (int i = 0; i < n; i++) {
                geciciX = (int) (Math.random() * 550); // düğümlerin konumu random oluşşur
                geciciY = (int) (Math.random() * 550);
                xKordinat.put(i, geciciX);
                yKordinat.put(i, geciciY);

            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < graf.size(); i++) {
                for (Kenar edge : graf.get(i)) {
                    int x1 = xKordinat.get(i);
                    int y1 = yKordinat.get(i);
                    int x2 = xKordinat.get(edge.hedef);
                    int y2 = yKordinat.get(edge.hedef);
                    g.drawLine(x1, y1, x2, y2);
                    if (i == 0) {
                        g.drawString("0. düğüm", x2 + 10, y2);
                    }
                    if (i == 1) {
                        g.drawString("1. düğüm", x2 + 10, y2);
                    }
                    if (i == 2) {
                        g.drawString("2. düğüm", x2 + 10, y2);
                    }
                    if (i == 3) {
                        g.drawString("3. düğüm", x2 + 10, y2);
                    }
                    if (i == 4) {
                        g.drawString("4. düğüm", x2 + 10, y2);
                    }
                    if (i == 5) {
                        g.drawString("5. düğüm", x2 + 10, y2);
                    }
                    if (i == 6) {
                        g.drawString("6. düğüm", x2 + 10, y2);
                    }
                    if (i == 7) {
                        g.drawString("7. düğüm", x2 + 10, y2);
                    }
                    if (i == 8) {
                        g.drawString("8. düğüm", x2 + 10, y2);
                    }
                    if (i == 9) {
                        g.drawString("9. düğüm", x2 + 10, y2);
                    }
                    if (i == 10) {
                        g.drawString("10. düğüm", x2 + 10, y2);
                    }
                    if (i == 11) {
                        g.drawString("11. düğüm", x2 + 10, y2);
                    }
                    if (i == 12) {
                        g.drawString("12. düğüm", x2 + 10, y2);
                    }
                    //  g.drawString("düğüm", x2, y2);

                }
            }
            for (int i = 0; i < graf.size(); i++) {
                int x = xKordinat.get(i);
                int y = yKordinat.get(i);
                g.setColor(i == baslangic + 1 ? Color.GREEN : Color.BLACK);
                g.fillOval(x - 5, y - 5, 10, 10);

            }
            for (int i = 0; i < graf.size(); i++) {
                if (i == baslangic) {
                    continue;
                }
                int x = xKordinat.get(i);
                int y = yKordinat.get(i);
                if (onceki[i] != -1) {
                    int x2 = xKordinat.get(onceki[i]);
                    int y2 = yKordinat.get(onceki[i]);
//                       Stroke str = new BasicStroke(2f);

                    g.setColor(Color.RED);
                    g.drawLine(x, y, x2, y2);
//                    g.drawLine(x-1, y-1, x2-1, y2-1);
//                    g.drawLine(x+1, y+1, x2+1, y2+1);
                }
            }

        }
    }

    public static Map<Integer, Integer> dijkstra(List<List<Kenar>> graph, int source, GraphPanel panel) {
        int n = graph.size();
        int[] distances = new int[n];
        panel.onceki = new int[n];
        PriorityQueue<dugum> kuyruk = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            panel.onceki[i] = -1;
        }
        distances[source] = 0;
        kuyruk.add(new dugum(source, 0));
        while (!kuyruk.isEmpty()) {
            dugum current = kuyruk.poll();
            if (current.mesafe == Integer.MAX_VALUE) {
                continue;
            }
            int currentId = current.id;
            for (Kenar kenar : graph.get(currentId)) {
                int id = kenar.hedef;
                int newDistance = distances[currentId] + kenar.ağırlık;
                if (newDistance < distances[id]) {
                    distances[id] = newDistance;
                    panel.onceki[id] = currentId;
                    kuyruk.add(new dugum(id, newDistance));
                    panel.repaint();
                    try {
                        Thread.sleep(1000); //çizgileri ekleme hızı
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(i, distances[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 13;
        List<List<Kenar>> graf = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        graf.get(0).add(new Kenar(1, 7)); //0. düğümü 1. düğüme 7 ağırlıkla bağlı
        graf.get(1).add(new Kenar(2, 9));//2. düğüme 1. düğüme 9 ağırlıkla bağlı
        graf.get(2).add(new Kenar(3, 14));
        graf.get(3).add(new Kenar(4, 15));
        graf.get(4).add(new Kenar(5, 10));
        graf.get(5).add(new Kenar(6, 15));
        graf.get(6).add(new Kenar(7, 11));
        graf.get(7).add(new Kenar(8, 5));
        graf.get(8).add(new Kenar(9, 5));
        graf.get(9).add(new Kenar(10, 15));
        graf.get(10).add(new Kenar(0, 8));
        graf.get(11).add(new Kenar(12, 8));
        graf.get(12).add(new Kenar(11, 8));

        int baslangic = 0;  // ilk düğüm        

        GraphPanel panel = new GraphPanel(graf, baslangic);
        panel.setPreferredSize(new Dimension(600, 600));
        JFrame frame = new JFrame("Dijkstra Ödev");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        Map<Integer, Integer> sonuç = dijkstra(graf, baslangic, panel);
        for (int i = 0; i < n; i++) {
            if (sonuç.get(i) == 2147483647) {
                System.out.println(baslangic + "'dan " + i + "'e gidilemez");
            } else {
                System.out.println(baslangic + "'dan " + i + "'e ağrırlık= " + sonuç.get(i));
            }
        }

    }
}

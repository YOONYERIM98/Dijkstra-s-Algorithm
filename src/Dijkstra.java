import java.util.Vector;

public class Dijkstra {
	final static int INFINITY = Integer.MAX_VALUE;

	int n = 0;       
	int data[][];   
	boolean visit[]; 
	int dis[];       
	int prev[];     
	int s, e;        
	int stack[];    

	Vector<Integer> stackV;

	public void init(int dataI[][]) 
	{
		data = dataI;
		n = data.length;
		dis = new int[n];
		visit = new boolean[n];
		prev = new int[n];
		stack = new int[n];
		stackV = new Vector<Integer>();
	}

	public int theLeastDistance() { return dis[e - 1]; }


	public void start(int start, int end) {
		System.out.println("==========================================================");
		System.out.println("다익스트라");
		System.out.println("시작점: " + start);
		System.out.println("도착점: " + end);
		System.out.println("===========================================================");
		s = start;
		e = end;

		int k = 0;
		int min = 0;


		for (int i = 0; i < n; i++) { 
			dis[i] = INFINITY;
			prev[i] = 0;
			visit[i] = false;
		}

		dis[s - 1] = 0; 
		for (int i = 0; i < n; i++) {
			min = INFINITY;
			for (int j = 0; j < n; j++) { 
				if (visit[j] == false && dis[j] < min) {
					k = j;
					min = dis[j];
				}
			}

			visit[k] = true; 
			if (min == INFINITY) break; 


			for (int j = 0; j < n; j++) {
				if (dis[k] + data[k][j] < dis[j]) {
					dis[j] = dis[k] + data[k][j]; 
					prev[j] = k; 
				}
			}
		}

		nowLeastDistance();   
		inverseFind();       
	}



	public void nowLeastDistance() {
		System.out.printf("최단거리:  %10d \n", dis[e-1]);
	}


	public void inverseFind() {
		int tmp = 0;
		int top = -1;
		tmp = e - 1;
		while (true) {
			stack[++top] = tmp + 1;
			if (tmp == s - 1) break; 
			tmp = prev[tmp];
		}


		stackV.removeAllElements();
		for (int i = top; i > -1; i--) {
			System.out.printf("%d", stack[i]);
			stackV.add(stack[i]);
			if (i != 0)System.out.printf(" -> ");
		}
		System.out.printf("\n");
	}

	public Vector<Integer> getStack() {
		return stackV;
	}

	public static void main(String[] args) {

		int m = 30000;

		int[][] data = new int[][]{

			{0, 2, 5, 1, m, m},
			{2, 0, 3, 2, m, m},
			{8, 6, 0, 3, 1, 5},
			{7, 2, 3, 0, 1, m},
			{m, m, 1, 1, 0, 2},
			{m, m, 8, m, 4, 0}};

			Dijkstra k = new Dijkstra();
			k.init(data);
			k.start(1, 6);
	}
}
package CA;
public class Main {

	public static void main(String[] args) {
		final int vertexCount = 500;
		final int mapSize = 650;
		
		CityGenerator cityGenerator = new CityGenerator(vertexCount, mapSize);
		Graph pairwiseGraph = cityGenerator.generatePairwiseGraph();
		
		ChristofidesAlgorithm christofidesAlgorithm = new ChristofidesAlgorithm(vertexCount);
		Graph tspTour = christofidesAlgorithm.generateTour(pairwiseGraph);
		
		TSPLowerBound tspLowerBoundComputor = new TSPLowerBound();
		double tspLowerBound = tspLowerBoundComputor.findTSPLowerBound(pairwiseGraph, vertexCount);
		
		GraphicsManager graphicsManager = new GraphicsManager();
		graphicsManager.showImage(graphicsManager.generateGraphImage(cityGenerator.getCities(), tspTour, mapSize, tspLowerBound));
		
	}

}

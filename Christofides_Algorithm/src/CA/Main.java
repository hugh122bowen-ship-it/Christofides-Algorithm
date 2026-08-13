package CA;
public class Main {
	public static void main(String[] args) {
		final int vertexCount = validateVertexCount(500);
		final int mapSize = validateMapSize(650);
		
		CityGenerator cityGenerator = new CityGenerator(vertexCount, mapSize);
		Graph pairwiseGraph = cityGenerator.generatePairwiseGraph();
		
		ChristofidesAlgorithm christofidesAlgorithm = new ChristofidesAlgorithm(vertexCount);
		Graph tspTour = christofidesAlgorithm.generateTour(pairwiseGraph);
		
		TSPLowerBound tspLowerBoundComputor = new TSPLowerBound();
		double tspLowerBound = tspLowerBoundComputor.findTSPLowerBound(pairwiseGraph, vertexCount);
		
		GraphicsManager graphicsManager = new GraphicsManager();
		graphicsManager.showImage(graphicsManager.generateGraphImage(cityGenerator.getCities(), tspTour, mapSize, tspLowerBound));
		
	}
	
	
	public static int validateVertexCount(int _vertexCount) {
		if(_vertexCount <= 1) {
			return 2;
		}else {
			return _vertexCount;
		}
	}
	
	public static int validateMapSize(int _mapSize) {
		if(_mapSize < 50) {
			return 50;
		}else {
			return _mapSize;
		}
	}

}
